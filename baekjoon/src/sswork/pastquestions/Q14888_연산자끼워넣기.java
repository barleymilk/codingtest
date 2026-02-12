package sswork.pastquestions;

import java.io.*;
import java.util.*;

public class Q14888_연산자끼워넣기 {
  static int N, maxResult, minResult;
  static int[] A, ops;
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 데이터 입력받기
    N = Integer.parseInt(br.readLine());
    
    A = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }

    ops = new int[4];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < 4; i++) {
      ops[i] = Integer.parseInt(st.nextToken());
    }

    // 초기화
    maxResult = Integer.MIN_VALUE;
    minResult = Integer.MAX_VALUE;

    dfs(0, A[0]);

    // 결과값 출력
    System.out.println(maxResult);
    System.out.println(minResult);
  }

  static void dfs(int depth, int currentResult) {
    if (depth == N - 1) {
      maxResult = Math.max(maxResult, currentResult);
      minResult = Math.min(minResult, currentResult);
      return;
    }

    for (int i = 0; i < 4; i++) {
      if (ops[i] != 0) {
        ops[i]--;
        dfs(depth + 1, calc(currentResult, A[depth+1], i));
        ops[i]++;
      }
    }
  }

  static int calc(int left, int right, int op) {
    switch (op) {
      case 0: return left + right;
      case 1: return left - right;
      case 2: return left * right;
      default: return left / right;
    }
  }
}
