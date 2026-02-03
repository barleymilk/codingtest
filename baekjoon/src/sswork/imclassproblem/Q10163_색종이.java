package sswork.imclassproblem;

import java.io.*;
import java.util.*;

public class Q10163_색종이 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[][] board = new int[1001][1001];

    for (int n = 1; n <= N; n++) {
      int lx = sc.nextInt();
      int ly = sc.nextInt();
      int width = sc.nextInt();
      int height = sc.nextInt();

      for (int x = lx; x < lx + width; x++) {
        for (int y = ly; y < ly + height; y++) {
          board[x][y] = n;
        }
      }
    }

    int[] answers = new int[N+1];
    for (int i = 0; i < 1001; i++) {
      for (int j = 0; j < 1001; j++) {
        if (board[i][j] > 0) {
          answers[board[i][j]]++;
        }
      }
    }

    for (int n = 1; n <= N; n++) {
      System.out.println(answers[n]);
    }

    sc.close();
  }
}
