// package.json에 해당 코드 추가 : "start": "node src/index.js"
// 터미널에서 코드 실행 : npm run start

// const moduleData = require("./math");
// console.log(moduleData); // { add: [Function: add], sub: [Function: sub] }
// console.log(moduleData.add(1,2)); // 3
// console.log(moduleData.sub(1,2)); // -1

const {add, sub} = require("./math");
console.log(add(2,4)); // 6
console.log(sub(2,4)); // -2
