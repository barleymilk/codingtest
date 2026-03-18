const fs = require('fs');
const isLocal = process.platform !== 'linux';
const filePath = isLocal ? './res/b1278.txt' : '/dev/stdin';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

// [ 변수 정의 ]
const dx = [-1,1,0,0], dy = [0,0,-1,1];

// [ 입력값 받기 ]
const [N, M] = input[0].split(" ").map(Number);
const maze = input.slice(1, N+1).map(line => line.trim().split('').map(Number));

// [ MAIN ]
bfs();

// [ 함수 정의 ]
function bfs () {
  const q = [[0, 0]]; // 시작점
  const dist = Array.from(Array(N), () => Array(M).fill(0));
  dist[0][0] = 1; // 시작지점 거리

  while (q.length > 0) {
    const [x, y] = q.shift();

    // 도착 지점 도달 -> 즉시 종료
    if (x === N-1 && y === M-1) {
      console.log(dist[x][y]);
      break;
    }

    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
        if (maze[nx][ny] === 1 && dist[nx][ny] === 0) {
          dist[nx][ny] = dist[x][y] + 1; // 이전 칸 거리 + 1
          q.push([nx, ny]);
        }
      }
    }
  }
}