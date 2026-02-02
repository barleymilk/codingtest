package sswork.imclassproblem;

import java.io.*;
import java.util.*;

public class Q2578_빙고 {
  static Scanner sc;
  
  // public static void printBingo(int[][] bingo) {
  //   StringBuilder sb = new StringBuilder();
  //   for (int x = 0; x < 5; x++) {
  //     for (int y = 0; y < 5; y++) {
  //       sb.append(bingo[x][y]).append(" ");
  //     }
  //     sb.append("\n");
  //   }
  //   System.out.println(sb);
  // }

  public static boolean checkBingo(int[][] bingo) {
    int bingoNum = 0;

    // 세로 5개
    for (int i = 0; i < 5; i++) {
      int count = 0;
      for (int j = 0; j < 5; j++) {
        if (bingo[i][j] == 0) count++;
      }
      if (count == 5) bingoNum++;
    }
    
    // 가로 5개
    for (int i = 0; i < 5; i++) {
      int count = 0;
      for (int j = 0; j < 5; j++) {
        if (bingo[j][i] == 0) count++;
      }
      if (count == 5) bingoNum++;
    }
    
    // 대각선 2개
    int d1Count = 0;
    int d2Count = 0;
    for (int i = 0; i < 5; i++) {
      if (bingo[i][i] == 0) d1Count++;
      if (bingo[i][4-i] == 0) d2Count++;
    }
    if (d1Count == 5) bingoNum++;
    if (d2Count == 5) bingoNum++;

    return bingoNum >= 3;
  }

  public static int playBingo(int[][] bingo) {
    int count = 0;
    for (int i = 0; i < 25; i++) {
      int num = sc.nextInt(); // 사회자 - 숫자 호명
      count++;

      search:
      for (int x = 0; x < 5; x++) {
        for (int y = 0; y < 5; y++) {
          if (bingo[x][y] == num) {
            bingo[x][y] = 0; // 맞춘 칸은 0으로 채움

            // 빙고 성립 -> 즉시 메소드 종료
            if (checkBingo(bingo)) {
              return count;
            }

            break search;
          }
        }
      }
    }

    return count;
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    sc = new Scanner(System.in);

    // 빙고판 채우기
    int[][] bingo = new int[5][5];
    for (int x = 0; x < 5; x++) {
      for (int y = 0; y < 5; y++) {
        bingo[x][y] = sc.nextInt();
      }
    }

    System.out.println(playBingo(bingo));

    sc.close();
  }
}
