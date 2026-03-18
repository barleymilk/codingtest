// package.json에 해당 코드 추가 : "start": "node src/index.js"
// 터미널에서 코드 실행 : npm run start

// const moduleData = require("./math");
// console.log(moduleData); // { add: [Function: add], sub: [Function: sub] }
// console.log(moduleData.add(1,2)); // 3
// console.log(moduleData.sub(1,2)); // -1

// package.json -> "type":"commonjs"
// const {add, sub} = require("./math");

// package.json -> "type":"module"
import {add, sub} from "./math.js"; // 확장자(.js)까지 명시해야 함.
import mul from "./math.js";

console.log(add(2,4)); // 6
console.log(sub(2,4)); // -2
console.log(mul(2,4)); // 8
