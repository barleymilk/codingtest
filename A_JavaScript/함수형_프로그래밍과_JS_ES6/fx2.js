

const L = {};

L.range = function *(l) {
  let i = -1;
  while(++i < l) {
    yield i;
  }
};

// L.map
// 평가를 미루는 성질을 가지고 평가 순서를 달리 조작할 수 있는 준비가 되어있는 이터레이터를 반환하는 제너레이터 함수
L.map = function *(iter) {
  iter = iter[Symbol.iterator]();
  let cur;
  while(!(cur = iter.next()).done) {
    const a = cur.value;
    yield a
  }
}

// L.filter
L.filter = function *(f, iter) {
  iter = iter[Symbol.iterator]();
  let cur;
  while(!(cur = iter.next()).done) {
    const a = cur.value;
    if (f(a)) yield a
  }
}