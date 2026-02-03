package d2Java;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q1209_sum {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    for (int tc = 0; tc < 10; tc++) {
      int N = sc.nextInt();
      int[][] arr = new int[100][100];
      for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
          arr[i][j] = sc.nextInt();
        }
      }

      int maxSum = 0;
      // 행
      for (int i = 0; i < 100; i++) {
        int temp = 0;
        for (int j = 0; j < 100; j++) {
          temp += arr[i][j];
        }
        if (maxSum < temp) maxSum = temp;
      }

      // 열
      for (int i = 0; i < 100; i++) {
        int temp = 0;
        for (int j = 0; j < 100; j++) {
          temp += arr[j][i];
        }
        if (maxSum < temp) maxSum = temp;
      }

      // 대각선
      int d1temp = 0;
      int d2temp = 0;
      for (int i = 0; i < 100; i++) {
        d1temp += arr[i][i];
      }
      for (int i = 0; i < 100; i++) {
        d2temp += arr[i][99-i];
      }
      if (maxSum < d1temp) maxSum = d1temp;
      if (maxSum < d2temp) maxSum = d2temp;

      System.out.println("#" + N + " " + maxSum);
    }
    sc.close();
  }
}
