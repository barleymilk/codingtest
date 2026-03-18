const fs = require('fs');
const isLocal = process.platform !== 'linux';
const filePath = isLocal ? './res/b1260.txt' : '/dev/stdin';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

const [N, M, V] = input[0].split(' ').map(Number);
const adj = Array.from({length: N + 1}, () => []);
for (let i = 1; i <= M; i++) {
  const [u, v] = input[i].split(' ').map(Number);
  adj[u].push(v);
  adj[v].push(u);
}

// 방문할 수 있는 정점이 여러 개인 경우 번호가 작은 것을 먼저 방문
adj.forEach(list => list.sort((a, b) => a - b));

const visitedDfs = new Array(N+1).fill(false);
const dfsResult = [];
dfs(V);
console.log(dfsResult.join(' '));

const bfsResult = [];
bfs(V);
console.log(bfsResult.join(' '));

// 함수 정의
function dfs (curr) {
  visitedDfs[curr] = true;
  dfsResult.push(curr);

  for (const next of adj[curr]) {
    if (!visitedDfs[next]) {
      dfs(next);
    }
  }
}

function bfs (startNode) {
  const visitedBfs = new Array(N+1).fill(false);
  const q = [startNode];
  visitedBfs[startNode] = true;

  while(q.length != 0) {
    const curr = q.shift();
    bfsResult.push(curr);

    for (const next of adj[curr]) {
      if (!visitedBfs[next]) {
        visitedBfs[next] = true;
        q.push(next);
      }
    }
  }
}