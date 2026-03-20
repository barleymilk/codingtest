const log = console.log;

const curry = f => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

// 첫번째 인자를 그 다음 인자인 함수에 적용하여 값을 반환하는 과정을 반복
const go = (...args) => reduce((a, f) => f(a), args);

// 함수를 reudce로 연속적으로 실행, pipe는 함수를 return
const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);

const map = curry((f, iter) => {
  let res = [];
  iter = iter[Symbol.iterator](); // iterable에서 iterator로 변경됨 -> next() 메서드를 가짐
  let cur;
  while(!(cur = iter.next()).done) {
    const a = cur.value;
    res.push(f(a));
  }
  return res;
});

const filter = curry((f, iter) => {
  let res = [];
  iter = iter[Symbol.iterator]();
  let cur;
  while(!(cur = iter.next()).done) {
    const a = cur.value;
    if (f(a)) res.push(a);
  }
  return res;
});

const reduce = curry((f, acc, iter) => {
  if (!iter) {
    iter = acc[Symbol.iterator]();
    acc = iter.next().value;
  } else {
    iter = iter[Symbol.iterator]();
  }
  while(!(cur = iter.next()).done) {
    const a = cur.value;
    acc = f(acc, a);
  }
  return acc;
});

const range = l => {
  let i = -1;
  let res = [];
  while(++i < l) {
    res.push(i);
  }
  return res;
};

const take = curry((l, iter) => {
  let res = [];
  iter = iter[Symbol.iterator]();
  let cur;
  while(!(cur = iter.next()).done) {
    const a = cur.value;
    res.push(a);
    if (res.length == l) return res;
  }
  return res;
});