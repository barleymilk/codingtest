/**
 * [그룹 1] 유틸리티 및 함수 합성을 위한 기초 도구
 * 함수를 값으로 다루며 조합성을 높여주는 도구들입니다.
 */
export const log = console.log;

// 커링: 인자를 나누어 받아 함수를 실행할 수 있게 함
export const curry = f =>
  (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

// 이터러블(반복 가능) 객체인지 확인
export const isIterable = a => a && a[Symbol.iterator];

// 동기와 비동기(Promise)를 동일한 인터페이스로 실행
export const go1 = (a, f) => a instanceof Promise ? a.then(f) : f(a);

// 파이프라인의 핵심: 인자들을 순차적으로 함수에 전달
export const go = (...args) => reduce((a, f) => f(a), args);

// 함수를 합성하여 새로운 함수를 생성
export const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);

export const add = (a, b) => a + b;


/**
 * [그룹 2] 비동기 제어 및 핵심 리듀서 (Internal Logic)
 * Promise와 'nop'을 이용해 비동기 상황에서의 필터링과 흐름을 제어합니다.
 */
// 필터링 시 '무시'해야 할 값을 나타내는 고유 심볼
export const nop = Symbol('nop');

// reduce 내부에서 Promise의 상태(정상 값 vs nop)에 따라 다음 동작 결정
const reduceF = (acc, a, f) =>
  a instanceof Promise ?
    a.then(a => f(acc, a), e => e == nop ? acc : Promise.reject(e)) :
    f(acc, a);

// 이터레이터의 첫 번째 값을 가져옴
export const head = iter => go1(take(1, iter), ([h]) => h);


/**
 * [그룹 3] 지연 평가 (Lazy Evaluation) 레이어
 * 'L' 객체에 담긴 함수들은 제너레이터(yield)를 사용하여 
 * 실제 결과가 필요한 시점까지 연산을 미룹니다.
 */
export const L = {};

// 0부터 l까지의 숫자를 하나씩 생성
L.range = function* (l) {
  let i = -1;
  while (++i < l) yield i;
};

// 지연 평가 맵: 값을 하나씩 꺼내어 함수 f를 적용
L.map = curry(function* (f, iter) {
  for (const a of iter) {
    yield go1(a, f);
  }
});

// 지연 평가 필터: 조건 f가 맞지 않으면 Promise.reject(nop)을 던져 skip 유도
L.filter = curry(function* (f, iter) {
  for (const a of iter) {
    const b = go1(a, f);
    if (b instanceof Promise) yield b.then(b => b ? a : Promise.reject(nop));
    else if (b) yield a;
  }
});

L.entries = function* (obj) {
  for (const k in obj) yield [k, obj[k]];
};

// 중첩된 이터러블을 한 단계 평탄화
L.flatten = function* (iter) {
  for (const a of iter) {
    if (isIterable(a) && typeof a != 'string') yield* a;
    else yield a;
  }
};

// 깊은 중첩까지 모두 평탄화
L.deepFlat = function* f(iter) {
  for (const a of iter) {
    if (isIterable(a) && typeof a != 'string') yield* f(a);
    else yield a;
  }
};

L.flatMap = curry(pipe(L.map, L.flatten));


/**
 * [그룹 4] 즉시 실행 및 결과 산출 레이어 (Main API)
 * 지연 평가된 이터레이터에서 실제로 값을 꺼내어 배열로 만들거나 결과를 도출합니다.
 */
// 원하는 개수(l)만큼만 값을 뽑아냄 (비동기 대응 로직 포함)
export const take = curry((l, iter) => {
  let res = [];
  iter = iter[Symbol.iterator]();
  return function recur() {
    let cur;
    while (!(cur = iter.next()).done) {
      const a = cur.value;
      if (a instanceof Promise) {
        return a
          .then(a => (res.push(a), res).length == l ? res : recur())
          .catch(e => e == nop ? recur() : Promise.reject(e));
      }
      res.push(a);
      if (res.length == l) return res;
    }
    return res;
  }();
});

export const takeAll = take(Infinity);

// 비동기 루프를 안전하게 순회하는 reduce 구현
export const reduce = curry((f, acc, iter) => {
  if (!iter) return reduce(f, head(iter = acc[Symbol.iterator]()), iter);

  iter = iter[Symbol.iterator]();
  return go1(acc, function recur(acc) {
    let cur;
    while (!(cur = iter.next()).done) {
      acc = reduceF(acc, cur.value, f);
      if (acc instanceof Promise) return acc.then(recur);
    }
    return acc;
  });
});

// 즉시 평가용 함수들 (L시리즈 + takeAll 조합)
export const map = curry(pipe(L.map, takeAll));
export const filter = curry(pipe(L.filter, takeAll));
export const flatten = pipe(L.flatten, takeAll);
export const flatMap = curry(pipe(L.map, flatten));

// 조건에 맞는 첫 번째 요소만 찾고 즉시 종료
export const find = curry((f, iter) => go(
  iter,
  L.filter(f),
  take(1),
  ([a]) => a));

// 즉시 배열을 생성하는 일반적인 range
export const range = l => {
  let i = -1;
  let res = [];
  while (++i < l) { res.push(i); }
  return res;
};


/**
 * [그룹 5] 병렬 처리 (Concurrency) 레이어
 * 순차적으로 기다리지 않고 여러 Promise를 동시에 출발시켜 성능을 높입니다.
 */
export const C = {};

export function noop() {}

// 병렬 처리 시 에러 로그가 콘솔에 남지 않도록 임시 catch 처리
const catchNoop = ([...arr]) =>
  (arr.forEach(a => a instanceof Promise ? a.catch(noop) : a), arr);

C.reduce = curry((f, acc, iter) => iter ?
  reduce(f, acc, catchNoop(iter)) :
  reduce(f, catchNoop(acc)));

C.take = curry((l, iter) => take(l, catchNoop(iter)));
C.takeAll = C.take(Infinity);
C.map = curry(pipe(L.map, C.takeAll));
C.filter = curry(pipe(L.filter, C.takeAll));