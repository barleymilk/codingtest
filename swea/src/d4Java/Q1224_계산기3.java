package d4Java;

import java.io.FileInputStream;
import java.util.*;

public class Q1224_계산기3 {
  static int getPriority(Character op) {
    if (op == '*' || op == '/') return 2;
    if (op == '+' || op == '-') return 1;
    if (op == '(') return 0;
    return -1;
  }

  static int calculatePostfix(StringBuilder postfix) {
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < postfix.length(); i++) {
      Character c = postfix.charAt(i);

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

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    for (int tc = 1; tc <= 10; tc++) {

      int len = sc.nextInt();
      Stack<Character> stack = new Stack<>();
      StringBuilder postfix = new StringBuilder();

      String str = sc.next();
      for (int i = 0; i < len; i++) {
        Character c = str.charAt(i);
        
        // 1. 숫자
        if (Character.isDigit(c)) postfix.append(c);

        // 2. '('
        else if (c == '(') stack.push(c);

        // 3. ')'
        else if (c == ')') {
          while (!stack.isEmpty() && stack.peek() != '(') postfix.append(stack.pop());
          stack.pop();
        }

        // 4. '+', '-', '*', '/'
        else {
          while (!stack.isEmpty() && getPriority(c) <= getPriority(stack.peek())) postfix.append(stack.pop());
          stack.push(c);
        }
      }
      // 5. stack 나머지 처리
      while (!stack.isEmpty()) postfix.append(stack.pop());

      int answer = calculatePostfix(postfix);
      System.out.println("#" + tc + " " + answer);
    }

    sc.close();
  }
}
