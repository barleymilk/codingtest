package ssstudy;

import java.io.*;

public class Q16637_괄호추가하기 {
  static int maxResult = Integer.MIN_VALUE;
  static char[] operations;
  static int[] numbers;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    // 연산자(+, -, *), 숫자끼리 모아서 저장
    operations = new char[(N - 1) / 2];
    numbers = new int[(N + 1) / 2];
    int operationIdx = 0;
    int numberIdx = 0;
    String str = br.readLine();
    for (int i = 0; i < N; i++) {
      char temp = str.charAt(i);
      if (temp == '+' || temp == '-' || temp == '*') { 
        operations[operationIdx] = temp;
        operationIdx++;
      }
      else {
        numbers[numberIdx] = temp - '0';
        numberIdx++;
      }
    }

    dfs(0, numbers[0]);

    System.out.println(maxResult);
  }

  static void dfs(int depth, int currentSum) {
    // 모든 연산자를 다 사용했다면 최댓값 갱신
    if (depth >= operations.length) {
      maxResult = Math.max(maxResult, currentSum);
      return;
    }

    // 1. 괄호 없이 계산 -> a ? b
    int res1 = calc(currentSum, numbers[depth + 1], operations[depth]);
    dfs(depth + 1, res1);

    // 2. 괄호 있게 계산 -> a ? (b ? c)
    if (depth + 1 < operations.length) {
      int bracketRes = calc(numbers[depth + 1], numbers[depth + 2], operations[depth + 1]);
      int res2 = calc(currentSum, bracketRes, operations[depth]);
      dfs(depth + 2, res2);
    }
  }

  static int calc(int n1, int n2, char op) {
    if (op == '+') return n1 + n2;
    if (op == '-') return n1 - n2;
    if (op == '*') return n1 * n2;
    return 0;
  }
}
