const fs = require('fs');
const isLocal = process.platform !== 'linux';
const filePath = isLocal ? './res/b1000.txt' : '/dev/stdin';
const input = fs.readFileSync(filePath).toString().trim().split(/\s+/);

const a = parseInt(input[0]);
const b = parseInt(input[1]);

console.log(a + b);