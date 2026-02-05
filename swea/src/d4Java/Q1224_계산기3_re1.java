package d4Java;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

public class Q1224_계산기3_re1 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    for (int tc = 1; tc <= 10; tc++) {
      int len = sc.nextInt();
      String str = sc.next();

      Stack<Character> signStack = new Stack<>();
      StringBuilder postfix = new StringBuilder();

      // 1. 후위 연산식 만들기
      for (int i = 0; i < len; i++) {
        char c = str.charAt(i);

        // (1) 숫자 0-9 처리하기
        if (Character.isDigit(c)) {
          postfix.append(c);
        }
        // (2) 여는 괄호 '(' 처리하기
        else if (c == '(') {
          signStack.push(c);
        }
        // (3) 닫는 괄호 ')' 처리하기
        else if (c == ')') {
          // '('를 만날 때까지 다 끄집어내기
          while (!signStack.isEmpty() && signStack.peek() != '(') {
            postfix.append(signStack.pop());
          }
          signStack.pop(); // '(' 제거
        }
        // (4) 연산자 '+', '-', '*', '/' 처리하기
        else {
          // 우선순위 높거나 같으면 끄집어내기
          while (!signStack.isEmpty() && getPriority(signStack.peek()) >= getPriority(c)) {
            postfix.append(signStack.pop());
          }
          signStack.push(c); // 다 끄집어낸 후 들어가기
        }
      }

      // (5) 남은 연산자들 다 끄집어내기
      while (!signStack.isEmpty()) {
        postfix.append(signStack.pop());
      }

      // 2. 후위 연산식(postfix)으로 계산하기
      int answer = calculatePostfix(postfix);

      System.out.println("#" + tc + " " + answer);
    }
    sc.close();
  }

  static int getPriority(char operator) {
    if (operator == '*' || operator == '/') return 2;
    if (operator == '+' || operator == '-') return 1;
    if (operator == '(') return 0;
    return -1;
  }

  static int calculatePostfix(StringBuilder postfix) {
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < postfix.length(); i++) {
      char c = postfix.charAt(i);

      if (Character.isDigit(c)) stack.push(c - '0');
      else {
        int op2 = stack.pop();
        int op1 = stack.pop();

        switch (c) {
          case '+': stack.push(op1 + op2); break;
          case '-': stack.push(op1 - op2); break;
          case '*': stack.push(op1 * op2); break;
          case '/': stack.push(op1 / op2); break;
        }
      }
    }

    return stack.pop();
  }
}
