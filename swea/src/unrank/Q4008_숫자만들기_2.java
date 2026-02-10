package unrank;

import java.io.*;
import java.util.*;

public class Q4008_숫자만들기_2 {
  static int N;
  static int minResult, maxResult;
  static int[] numbers;
  static int[] opCounts;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());
      
      // 연산자 개수 배열 입력 받아오기
      opCounts = new int[4];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 4; i++) {
        opCounts[i] = Integer.parseInt(st.nextToken());
      }

      // 숫자 배열 입력 받아오기
      numbers = new int[N];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
        numbers[i] = Integer.parseInt(st.nextToken());
      }

      minResult = Integer.MAX_VALUE;
      maxResult = Integer.MIN_VALUE;

      dfs(1, numbers[0]);

      System.out.println("#" + tc + " " + (maxResult - minResult));
    }
  } 

  static void dfs(int nextIdx, int currentTotal) {
    // 숫자 전부 사용 -> 최댓값/최솟값 경신
    if (nextIdx == N) {
      maxResult = Math.max(maxResult, currentTotal);
      minResult = Math.min(minResult, currentTotal);
      return;
    }

    // 4종류의 연산자를 하나씩 시도
    for (int op = 0; op < 4; op++) {
      // opCounts에 해당 연산자의 카운트가 남아 있어야 함. (가지치기)
      if (opCounts[op] > 0) {
        opCounts[op]--; // 연산자 사용

        int calculatedVal = calc(currentTotal, numbers[nextIdx], op);
        dfs(nextIdx + 1, calculatedVal);
        opCounts[op]++; // 백트래킹
      }
    }
  }

  static int calc(int left, int right, int operatorType) {
    switch (operatorType) {
      case 0: return left + right;
      case 1: return left - right;
      case 2: return left * right;
      case 3: return left / right;
      default: return 0;
    }
  }
}
