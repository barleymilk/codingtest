package d3Java;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Q1234_비밀번호 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);
    for (int tc = 1; tc <= 10; tc++) {
      int N = sc.nextInt(); // 비밀번호 길이
      String str = sc.next(); // 입력받은 번호 문자열
      
      int[] stack = new int[N];
      Arrays.fill(stack, -1);

      int top = -1;
      for (int i = 0; i < N; i++) {
        int temp = str.charAt(i) - '0';
        
        if (top != -1 && stack[top] == temp) {
          stack[top] = -1;
          top--;
        }
        else {
          stack[++top] = temp;
        }
      }
      String answer = "";
      for (int i = 0; i <= top; i++) answer += stack[i];
      System.out.println("#" + tc + " " + answer);
    }
    sc.close();
  }
}
