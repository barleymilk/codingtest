package d4Java;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Q1226_미로1 {
  static int[][] board;
  static int[][] adj;
  static boolean[] visited;
  static int found;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};

    for (int tc = 0; tc < 10; tc++) {
      int T = sc.nextInt();
      board = new int[16][16];
      for (int i = 0; i < 16; i++) {
        for (int j = 0; j < 16; j++) {
          board[i][j] = sc.nextInt();
        }
      }

      adj = new int[16*16+1][4];
      for (int i = 0; i < 16*16+1; i++) Arrays.fill(adj[i], -1); // 길 정보 -1로 초기화 (-1: 길 없음)

      // 벽에 둘러쌓임 -> (1, 1) ~ (14, 14) 범위만 확인하면 됨
      for (int i = 1; i < 15; i++) {
        for (int j = 1; j < 15; j++) {
          if(board[i][j] != 1) {
            // 벽이 아닐 때 -> + 모양으로 갈 수 있는 곳 확인
            for (int k = 0; k < 4; k++) {
              int nextX = i + dx[k];
              int nextY = j + dy[k];
              int u = i * 16 + j + 1;
              int v = nextX * 16 + nextY + 1;
              if (board[nextX][nextY] != 1) {
                adj[u][k] = v;
              }
            }
          }
        }
      }

      visited = new boolean[16*16+1];
      found = 0;
      dfs(1);

      System.out.println("#" + T + " " + found);
    }

    sc.close();
  }

  static void dfs(int current) {
    int x = current / 16;
    int y = current % 16;
    if (board[x][y] == 3) {
      found = 1;
      return;
    }

    visited[current] = true;

    for (int i = 0; i < 4; i++) {
      int next = adj[current][i];
      if (board[x][y] != 1) {
        dfs(next);
        if (found == 1) return;
      }
    }
  }
}
