const fs = require('fs');
const isLocal = process.platform !== 'linux';
const filePath = isLocal ? './res/b10814.txt' : '/dev/stdin';
const input = fs.readFileSync(filePath).toString().trim().split(/\s+/);

const N = Number(input[0]);
const members = [];

for (let i = 0; i < N; i++) {
  const age = Number(input[i * 2 + 1]);
  const name = input[i * 2 + 2];

  members.push({ age, name, id: i });
}

members.sort((a, b) => {
  if (a.age !== b.age) {
    return a.age - b.age;
  }
  return a.id - b.id;
});

console.log(members.map(m => `${m.age} ${m.name}`).join('\n'));