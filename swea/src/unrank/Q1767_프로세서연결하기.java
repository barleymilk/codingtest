package unrank;

import java.io.*;
import java.util.*;

public class Q1767_프로세서연결하기 {
  static int N, totalCores, maxCoreCnt, minWireCnt;
  static int[][] board, corePos;
  static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input2.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());
      board = new int[N][N];
      corePos = new int[12][2]; // 코어는 최대 12개
      totalCores = 0;

      StringTokenizer st;
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());

          // 코어 위치 저장 (가장자리 코어 제외)
          if (i != 0 && j != 0 && i != N-1 && j != N-1 && board[i][j] == 1) {
            corePos[totalCores++] = new int[]{i, j};
          }
        }
      }

      maxCoreCnt = 0;
      minWireCnt = 0;
      dfs(0, 0, 0);

      System.out.println("#" + tc + " " + minWireCnt);
    }
  }

  static void dfs(int coreIdx, int coreCnt, int wireCnt) {
    // 모든 코어를 확인했을 때
    if (coreIdx == totalCores) {
      // 연결된 코어 수 최댓값 갱신 & 연결된 전선 수 갈아치우기
      if (coreCnt > maxCoreCnt) {
        maxCoreCnt = coreCnt;
        minWireCnt = wireCnt;
      }
      // 연결된 전선 수 최솟값 갱신
      else if (coreCnt == maxCoreCnt) {
        minWireCnt = Math.min(minWireCnt, wireCnt);
      }
      return;
    }

    // 코어 위치
    int x = corePos[coreIdx][0];
    int y = corePos[coreIdx][1];

    // 1) 코어 선택 O
    for (int d = 0; d < 4; d++) {
      int len = getWireLength(x, y, d); // 설치 가능한 전선 수
      if (len > 0) {
        fillWire(x, y, d, 2); // 전선 설치
        dfs(coreIdx + 1, coreCnt + 1, wireCnt + len); // 연결된 코어 수 += 1, 설치된 전선 수 += len
        fillWire(x, y, d, 0); // 전선 제거(백트래킹)
      }
    }

    // 2) 코어 선택 X
    dfs(coreIdx + 1, coreCnt, wireCnt);
  }

  static int getWireLength(int x, int y, int d) {
    int len = 0;
    int nx = x, ny = y;

    // 해당 방향으로 한 칸씩 계속 전진
    while(true) {
      nx += dx[d];
      ny += dy[d];
      if (nx < 0 || ny < 0 || nx >= N || ny >= N) break; // 범위 벗어나면 중지
      if (board[nx][ny] != 0) return -1; // 전선 설치가 불가능하면 -1 리턴
      len++;
    }

    return len;
  }

  static void fillWire(int x, int y, int d, int val) {
    int nx = x, ny = y;

    // 해당 방향으로 한 칸씩 계속 전진
    while(true) {
      nx += dx[d];
      ny += dy[d];
      if (nx < 0 || ny < 0 || nx >= N || ny >= N) break; // 범위 벗어나면 중지
      board[nx][ny] = val; // 전선 설치(val=2) 혹은 제거(val=0)
    }
  }
}
