package unrank;

import java.io.*;
import java.util.*;

public class Q2105_디저트카페 {
  static int N, maxCount, startX, startY;
  static int[][] map;
  static boolean[] dessertVisited;
  static int[] dx = {1, 1, -1, -1}, dy = {1, -1, -1, 1}; // RD, LD, LU, RU

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      // 데이터 입력받기
      N = Integer.parseInt(br.readLine());

      map = new int[N][N];
      StringTokenizer st;
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      maxCount = -1;
      // 시작점 (i, j)
      for (int i = 0; i < N - 2; i++) {
        for (int j = 1; j < N - 1; j++) {
          startX = i;
          startY = j;
          maxCount = Math.max(maxCount, -1);
          dessertVisited = new boolean[101]; // 먹은 디저트 종류

          dfs(i, j, 0, 1); // 시작점이므로 dir 0, count 1로 시작
        }
      }

      System.out.println("#" + tc + " " + maxCount);
    }
  }

  static void dfs(int x, int y, int dir, int count) {
    // 디저트 먹음
    dessertVisited[map[x][y]] = true;

    // 가던 방향으로 가거나 한 번만 꺾기 가능
    for (int d = dir; d <= dir + 1 && d < 4; d++) {
      int nx = x + dx[d];
      int ny = y + dy[d];

      // 한 바퀴 댜 돌았을 때 -> maxCount값 갱신
      if (nx == startX && ny == startY && count >= 4) {
        maxCount = Math.max(maxCount, count);
      }

      // 범위 안에 있고, 처음 본 디저트일 때
      if (nx >= 0 && nx < N && ny >= 0 && ny < N && !dessertVisited[map[nx][ny]]) {
        dfs(nx, ny, d, count + 1);
      }
    }

    // 디저트 뱉음ㅋㅋ (백트래킹)
    dessertVisited[map[x][y]] = false;
  }
}
