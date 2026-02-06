package d4Java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Q1226_미로1_re2 {
  static int N = 16;
  static int[][] board;
  static boolean[][] visited;
  static int[] dx = {-1,1,0,0};
  static int[] dy = {0,0,-1,1};
  static int found;

  static void dfs(int x, int y) {
    if (found == 1) return;

    visited[x][y] = true;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
        if (board[nx][ny] == 0) {
          dfs(nx, ny);
        } else if (board[nx][ny] == 3) {
          found = 1;
          return;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    for (int tc = 1; tc <= 10; tc++) {
      String tcNum = br.readLine();

      int startX = -1, startY = -1;
      found = 0;
      board = new int[N][N];
      visited = new boolean[N][N];

      for (int i = 0; i < N; i++) {
        String line = br.readLine();
        for (int j = 0; j < N; j++) {
          board[i][j] = line.charAt(j) - '0';
          if (board[i][j] == 2) {
            startX = i;
            startY = j;
          }
        }
      }

      dfs(startX, startY);

      System.out.println("#" + tc + " " + found);

    }
  }
}
