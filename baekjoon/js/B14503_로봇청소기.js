const fs = require('fs');
const isLocal = process.platform !== 'linux';
const filePath = isLocal ? './res/b14503.txt' : '/dev/stdin';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

// [ 변수 정의 ]
const dr = [-1,0,1,0], dc = [0,1,0,-1]; // 북,동,남,서
const UNCLEANED = 0;
const WALL = 1;
let answer = 0;

// [ 입력값 받기 ]
const [N, M] = input[0].split(" ").map(Number); // 맵 크기 (N x M)
const [r, c, d] = input[1].split(" ").map(Number); // 로봇청소기 위치(r, c), 방향 d
const board = input.slice(2).map(line => line.trim().split(" ").map(Number));

// [ MAIN ]
clean(r, c, d);
console.log(answer);

// [ 함수 ]
function clean(r, c, d) {
  // 1. 현재 칸 청소
  if (board[r][c] === UNCLEANED) {
    board[r][c] = 2; 
    answer++;
  }
  
  // 2. 주변 4칸 중 청소되지 않은 빈 칸이 있는지 확인
  if (findUncleaned(r, c)) {
    for (let i = 0; i < 4; i++) {
      // 반시계 방향 90도 회전
      d = (d + 3) % 4;
      let nr = r + dr[d], nc = c + dc[d];

      // 청소되지 않은 곳이면 전진
      if (nr >= 0 && nr < N && nc >= 0 && nc < M && board[nr][nc] === UNCLEANED) {
        clean(nr, nc, d);
        return; // 종료
      }
    }
  } 
  // 3. 주변 4칸이 모두 청소되었거나 벽인 경우
  else {
    const backD = (d + 2) % 4; // 후진 방향 계산
    const nr = r + dr[backD], nc = c + dc[backD];

    // 후진 가능한 경우
    if (nr >= 0 && nr < N && nc >= 0 && nc < M && board[nr][nc] !== WALL) {
      clean(nr, nc, d);
      return; // 종료
    } 
    // 후진 불가능하면 종료
    else {
      return;
    }
  }
}

// 주변 4방향에 청소가 안된 곳이 있는지 여부
function findUncleaned(r, c) {
  for (let i = 0; i < 4; i++) {
    let nr = r + dr[i];
    let nc = c + dc[i];

    // 범위를 벗어나거나 벽 또는 이미 청소한 칸인 경우 통과
    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
    if (board[nr][nc] !== 0) continue;

    if (board[nr][nc] === 0) return true;
  }
  return false;
}