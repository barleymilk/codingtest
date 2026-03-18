// math 모듈
export function add(a, b) {
  return a + b;
}

export function sub(a, b) {
  return a - b;
}

// export default -> math 모듈을 대표하는 기본값
// 이름은 마음대로 불러와도 됨. 
// -> import multiply from "./math.js"
// -> import mul from "./math.js"
export default function multiply(a, b) {
  return a * b;
}

// commonjs
// module.exports = {
//   add,
//   sub,
// }

// package.json -> "type":"module"
// export {add, sub};
