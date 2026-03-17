package d4Java;

import java.io.*;
import java.util.*;

public class S1218_괄호짝짓기 {
  static Stack<Integer> stack;
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    for (int tc = 1; tc <= 10; tc++) {
      int N = Integer.parseInt(br.readLine());
      int answer = 0;
      stack = new Stack<>();
      String line = br.readLine();
      boolean flag = true;
      for (int i = 0; i < N; i++) {
        char bracket = line.charAt(i);
        // System.out.println(bracket);
        switch (bracket) {
          // 왼쪽 괄호 -> 저장
          case '(': stack.add(0); break;
          case '[': stack.add(1); break;
          case '{': stack.add(2); break;
          case '<': stack.add(3); break;
          // 오른쪽 괄호 -> 왼쪽 괄호 있는지 확인, 왼쪽 괄호와 짝이 맞는지 확인 -> 빼기
          case ')': {
            if (!stack.isEmpty()) {
              int leftBracket = stack.pop();
              if (leftBracket != 0) flag = false; break;
            }
            break;
          }
          case ']': {
            if (!stack.isEmpty()) {
              int leftBracket = stack.pop();
              if (leftBracket != 1) flag = false; break;
            }
          }
          case '}': {
            if (!stack.isEmpty()) {
              int leftBracket = stack.pop();
              if (leftBracket != 2) flag = false; break;
            }
          }
          case '>': {
            if (!stack.isEmpty()) {
              int leftBracket = stack.pop();
              if (leftBracket != 3) flag = false; break;
            }
          }
        }
      }
      if (flag) answer = 1;

      System.out.println("#" + tc + " " + answer);
    }
  }
}