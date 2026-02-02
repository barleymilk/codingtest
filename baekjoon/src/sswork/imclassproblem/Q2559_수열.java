package sswork.imclassproblem;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q2559_수열 {
  public static void main(String[] args) throws Exception {
    // https://www.acmicpc.net/problem/2559
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int K = sc.nextInt();
    int[] temperatures = new int[N];
    for (int i = 0; i < N; i++) temperatures[i] = sc.nextInt();

    int maxSum = Integer.MIN_VALUE;
    for (int i = 0; i <= N - K; i++) {
      int temp = 0;
      for (int j = 0; j < K; j++) {
        temp += temperatures[i + j];
      }
      if (temp > maxSum) maxSum = temp;
    }

    System.out.println(maxSum);

    sc.close();
  }
}
