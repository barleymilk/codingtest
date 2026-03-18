const log = console.log;

const curry = f => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

const map = curry((f, iter) => {
  let res = [];
  for (const a of iter) {
    res.push(f(a));
  }
  return res;
});

const filter = curry((f, iter) => {
  let res = [];
  for (const a of iter) {
    if (f(a)) res.push(a);
  }
  return res;
});

const reduce = curry((f, acc, iter) => {
  if (!iter) {
    iter = acc[Symbol.iterator]();
    acc = iter.next().value;
  }
  for (const a of iter) {
    acc = f(acc, a);
  }
  return acc;
});

// 첫번째 인자를 그 다음 인자인 함수에 적용하여 값을 반환하는 과정을 반복
const go = (...args) => reduce((a, f) => f(a), args);

// 함수를 reudce로 연속적으로 실행, pipe는 함수를 return
const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);