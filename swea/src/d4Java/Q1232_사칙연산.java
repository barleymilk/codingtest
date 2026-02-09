package d4Java;

import java.io.*;
import java.util.*;

public class Q1232_사칙연산 {
  static Node[] tree;

  static class Node {
  String val; // 연산자 또는 숫자 문자열 저장
  int left, right;

  public Node (String val) {
    this.val = val;
    this.left = -1;
    this.right = -1;
  }
}

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    for (int tc = 1; tc <= 10; tc++) {
      int N = Integer.parseInt(br.readLine());
      tree = new Node[N + 1];

      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int idx = Integer.parseInt(st.nextToken());
        String val = st.nextToken();
        tree[idx] = new Node(val);

        // 연산자인 경우 자식 노드 번호가 추가로 들어옴
        if (st.hasMoreTokens()) {
          tree[idx].left = Integer.parseInt(st.nextToken());
        }
        if (st.hasMoreTokens()) {
          tree[idx].right = Integer.parseInt(st.nextToken());
        }
      }

      // 계산 시작 -> 후위 순회 결과 반환
      double result = calc(1);

      System.out.println("#" + tc + " " + (int)result);
    }
  }

  // 후위 순회
  static double calc(int current) {
    Node node = tree[current];
    
    // 1. 숫자인 경우 (자식이 없는 리프 노드)
    if (node.left == -1 && node.right == -1) {
      return Double.parseDouble(node.val);
    }

    // 2. 연산자인 경우 자식들의 값을 먼저 구해옴 (재귀)
    double leftVal = calc(node.left);
    double rightVal = calc(node.right);

    // 3. 내 노드의 연산자로 계산하여 결과 반환
    switch (node.val) {
      case "+": return leftVal + rightVal;
      case "-": return leftVal - rightVal;
      case "*": return leftVal * rightVal;
      case "/": return leftVal / rightVal;
    }
    
    return 0;
  }
}