package d4Java;

import java.io.*;
import java.util.*;

public class S1210_Ladder1 {
  static int[][] board;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    for (int tc = 1; tc <= 10; tc++) {
      int N = Integer.parseInt(br.readLine());
      board = new int[100][100];
      StringTokenizer st;
      for (int i = 0; i < 100; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 100; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int answer = -1;
      for (int i = 0; i < 100; i++) {
        // 출발점 찾기
        if (board[99][i] == 2) {
          answer = up(99, i);
        }
      }
      System.out.println("#" + tc + " " + answer);
    }
  }

  static int up(int x, int y) {
    if (x == 0) {
      return y;
    }
    
    // 왼쪽
    if (y - 1 >= 0 && y - 1 < 100  && board[x][y - 1] == 1) {
      board[x][y - 1] = 2;
      return up(x, y - 1);
    }
    // 오른쪽
    else if (y + 1 >= 0 && y + 1 < 100 && board[x][y + 1] == 1) {
      board[x][y + 1] = 2;
      return up(x, y + 1);
    }
    // 위쪽
    else {
      board[x - 1][y] = 2;
      return up(x - 1, y);
    }
  }
}
