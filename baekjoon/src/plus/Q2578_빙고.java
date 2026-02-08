package plus;

import java.io.*;
import java.util.*;

public class Q2578_빙고 {
  static int[][] board;
  static int[][] call;
  
  static int bingo() {
    int count = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        count++;
        int num = call[i][j];
        mark(num);
        if (checkBingo() >= 3) return count;
      }
    }
    return 0;
  }
  
  static void mark(int num) {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (board[i][j] == num) board[i][j] = 0;
      }
    }
  }

  static int checkBingo() {
    int totalBingo = 0;
    // 행
    for (int i = 0; i < 5; i++) {
      int count = 0;
      for (int j = 0; j < 5; j++) {
        if (board[i][j] == 0) count++;
      }
      if (count == 5) totalBingo++;
    }

    // 열
    for (int i = 0; i < 5; i++) {
      int count = 0;
      for (int j = 0; j < 5; j++) {
        if (board[j][i] == 0) count++;
      }
      if (count == 5) totalBingo++;
    }

    // 대각선
    int count1 = 0;
    int count2 = 0;
    
    for (int i = 0; i < 5; i++) {
      if (board[i][i] == 0) count1++;
    }
    if (count1 == 5) totalBingo++;

    for (int i = 0; i < 5; i++) {
      if (board[4-i][i] == 0) count2++;
    }
    if (count2 == 5) totalBingo++;

    return totalBingo;
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    board = new int[5][5];
    for (int i = 0; i < 5; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 5; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    call = new int[5][5];
    for (int i = 0; i < 5; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < 5; j++) {
        call[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = bingo();
    System.out.println(answer);
  }
}
