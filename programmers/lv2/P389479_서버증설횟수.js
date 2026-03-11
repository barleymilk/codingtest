function solution(players, m, k) {
  let totalPlusCnt = 0; // 총 증설한 서버 수
  let currentServers = 0; // 현재 운영 중인 증설 서버 수

  // 각 시간(0-23)마다 반납될 서버 수를 기록하는 배열
  // k시간 뒤에 반납되므로 넉넉하게 24 + k 크기로 만듦
  const expireSchedule = new Array(24 + k).fill(0);

  for (let i = 0; i < 24; i++) {
    // 1. 서버 반납
    currentServers -= expireSchedule[i];

    const playerNum = players[i];
    // 2. 현재 필요한 서버 수
    const needTotal = Math.floor(playerNum / m);

    // 3. 서버가 부족할 때
    if (needTotal > currentServers) {
      const diff = needTotal - currentServers;

      totalPlusCnt += diff;
      currentServers += diff;

      // 4. i + k 시점에 증설한 수 만큼 반납하도록 기록
      expireSchedule[i + k] += diff;
    }
  }

  return totalPlusCnt;
}

let players = [0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5];
let m = 3;
let k = 5;
const result = solution(players, m, k);
console.log("걸과값:", result);