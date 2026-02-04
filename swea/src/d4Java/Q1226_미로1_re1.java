package d4Java;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q1226_미로1_re1 {
  static int[][] board;
  static boolean[][] visited;
  static int found;
  static int[] dx = {1, -1, 0, 0};
  static int[] dy = {0, 0, 1, -1};

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    for (int tc = 1; tc <= 10; tc++) {
      int T = sc.nextInt();
      board = new int[16][16];
      visited = new boolean[16][16];
      found = 0;

      int startX = 0, startY = 0;

      for (int i = 0; i < 16; i++) {
        String line = sc.next(); // 1. 한 줄을 문자열로 읽기
        for (int j = 0; j < 16; j++) {
          board[i][j] = line.charAt(j) - '0'; // 2. 문자를 숫자로 변환
          if (board[i][j] == 2) { // 시작점 저장
            startX = i;
            startY = j;
          }
        }
      }

      dfs(startX, startY);
      System.out.println("#" + T + " " + found);
    }
    sc.close();
  }

  static void dfs(int x, int y) {
    if (board[x][y] == 3) { // 목적지 도달
      found = 1;
      return;
    }

    visited[x][y] = true;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      // 범위 안이고, 벽이 아니며, 방문하지 않은 경우만 탐색
      if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16) {
        if (board[nx][ny] != 1 && !visited[nx][ny]) {
          dfs(nx, ny);
          if (found == 1) return; // 찾았으면 빠른 탈출
        }
      }
    }
  }
}
