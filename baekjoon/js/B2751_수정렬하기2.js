const fs = require('fs');
const isLocal = process.platform !== 'linux';
const filePath = isLocal ? './res/b2751.txt' : '/dev/stdin';

const [N, ...arr] = fs.readFileSync(filePath).toString().trim().split(/\s+/);

arr.sort((a, b) => a - b); // 오름차순
console.log(arr.join('\n'));