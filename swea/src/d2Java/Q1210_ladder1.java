package d2Java;

import java.io.*;
import java.util.*;

public class Q1210_ladder1 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    for (int tc = 1; tc <= 10; tc++) {
      int num = sc.nextInt();
      int[][] data = new int[100][100];
      
      // 도착점(2) 좌표 저장
      int r = 99, c = 0;
      for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
          data[i][j] = sc.nextInt();
          if (i == 99 && data[i][j] == 2) c = j;
        }
      }

      // 역추적 시작 (행이 0이 될 때까지)
      while (r > 0) {
        // 1. 왼쪽 확인 (범위 내에 있고 길이 있으면)
        if (c - 1 >= 0 && data[r][c - 1] == 1) {
          while (c - 1 >= 0 && data[r][c - 1] == 1) {
            c--; // 왼쪽 끝까지 이동
          }
        } 
        // 2. 오른쪽 확인
        else if (c + 1 < 100 && data[r][c + 1] == 1) {
          while (c + 1 < 100 && data[r][c + 1] == 1) {
            c++; // 오른쪽 끝까지 이동
          }
        }
        // 3. 좌우에 길이 없으면 위로 한 칸 이동
        r--;
      }

      System.out.println("#" + num + " " + c);
    }

    sc.close();
  }
}
