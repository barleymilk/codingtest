function solution(info, n, m) {
  const infoCnt = info.length;
  let minCnt = Infinity;
  const visited = new Set(); // 이미 방문한 상태 기록 -> 'depth-aCnt-bCnt' 형태의 문자열로 저장

  function dfs(depth, aCnt, bCnt) {
    // 가지치기
    if (aCnt >= minCnt) return;
    if (aCnt >= n || bCnt >= m) return;

    // 메모이제이션
    const state = `${depth}-${aCnt}-${bCnt}`;
    if (visited.has(state)) return;
    visited.add(state);

    // 목적지 도달
    if (depth == infoCnt) {
      minCnt = Math.min(minCnt, aCnt);
      return;
    }

    // 1. A도둑이 훔침
    dfs(depth + 1, aCnt + info[depth][0], bCnt);

    // 2. B도둑이 훔침
    dfs(depth + 1, aCnt, bCnt + info[depth][1]);
  }

  dfs(0, 0, 0);

  return minCnt === Infinity ? -1 : minCnt;
}

const info = [[1, 2], [2, 3], [2, 1]];
const n = 4;
const m = 4;

const result = solution(info, n, m);
console.log("걸과값:", result);