const fs = require('fs');
const isLocal = process.platform !== 'linux';
const filePath = isLocal ? './res/b1181.txt' : '/dev/stdin';
const [N, ...arr] = fs.readFileSync(filePath).toString().trim().split(/\s+/);

// 중복 제거
const uniqueArr = [...new Set(arr)];

// 정렬
uniqueArr.sort((a,b) => {
  if (a.length !== b.length) {
    return a.length - b.length
  }
  return a.localeCompare(b); // 길이가 같으면 사전 순 정렬
});

// 출력
console.log(uniqueArr.join('\n'));

// const result = [...new Set(arr)]
//   .sort((a, b) => a.length - b.length || a.localeCompare(b))
//   .join('\n');

// console.log(result);