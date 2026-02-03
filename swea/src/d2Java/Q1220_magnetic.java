package d2Java;

import java.io.*;
import java.util.*;

public class Q1220_magnetic {
  public static void printBoard(int[][] board) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        sb.append(board[i][j]).append(" ");
      }
      sb.append("\n");
    }
    System.out.println(sb);
    return;
  }
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);
    
    for (int tc = 1; tc <= 10; tc++) {
      int dummy = sc.nextInt();
      int[][] board = new int[100][100];
      for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
          board[i][j] = sc.nextInt();
        }
      }

      int count = 0;
      for (int i = 0; i < 100; i++) {
        boolean isN = false;
        for (int j = 0; j < 100; j++) {
          // N이 현재 열에 존재하는가?
          if (board[j][i] == 1) isN = true;
          // S를 만나면 교착
          if (isN && board[j][i] == 2) {
            count++;
            isN = false;
          }
        }
      }

      System.out.println("#" + tc + " " + count);
    }
    sc.close();
  }
}
