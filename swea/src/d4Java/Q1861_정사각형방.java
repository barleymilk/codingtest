package d4Java;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q1861_정사각형방 {
  static int N;
  static int[][] rooms;
  static int[] dx = {0, 0, -1, 1};
  static int[] dy = {-1, 1, 0, 0};

  static int dfs(int x, int y) {
    int currentNum = rooms[x][y];
    
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
        if (rooms[nx][ny] == currentNum + 1) {
          return dfs(nx, ny);
        }
      }
    }
    // 아무 방향에도 못감.
    return currentNum;
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();

    for (int tc = 1; tc <= T; tc++) {
      N = sc.nextInt();
      int maxCount = 0;
      int minStart = Integer.MAX_VALUE;

      rooms = new int[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          rooms[i][j] = sc.nextInt();
        }
      }

      // 0,0 부터 시작 -> DFS를 한다. (+1인 곳으로만 옮기기 가능)
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          int from = rooms[i][j];
          int to = dfs(i, j);
          int count = to - from + 1;

          if (maxCount < count) {
            maxCount = count;
            minStart = from;
          } else if (maxCount == count) {
            minStart = Math.min(minStart, from);
          }
        }
      }
      
      System.out.println("#" + tc + " " + minStart + " " + maxCount);
    }

    sc.close();
  }
}
