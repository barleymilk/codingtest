package d4Java;

import java.io.FileInputStream;
import java.util.*;

public class Q1218_괄호짝짓기 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    Map<Character, Character> bracketMap = new HashMap<>();
    bracketMap.put(')', '(');
    bracketMap.put(']', '[');
    bracketMap.put('}', '{');
    bracketMap.put('>', '<');

    for (int tc = 1; tc <= 10; tc++) {
      int N = sc.nextInt();
      String line = sc.next(); // 문자열 전체를 한 번에 읽음
      char[] stack = new char[N];
      
      int answer = 1;
      int top = -1;
      for (int i = 0; i < N; i++) {
        char bracket = line.charAt(i); // i번째 글자를 하나씩 꺼냄

        if (bracket == '(' || bracket == '[' || bracket == '{' || bracket=='<') {
          stack[++top] = bracket;
        }
        else {
          // 오른쪽 괄호 -> stack이 비었거나 짝이 안 맞으면 실패
          if (top < 0 || stack[top] != bracketMap.get(bracket)) {
            answer = 0;
            break;
          }
          top--; // 매칭 성공하면 pop
        }
      }
      // 모든 검사가 끝난 후에도 stack이 비지 않았으면 return 0
      if (top != -1) answer = 0;

      System.out.println("#" + tc + " " + answer);
    }
    
    sc.close();
  }
}
