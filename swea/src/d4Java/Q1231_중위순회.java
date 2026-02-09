package d4Java;

import java.io.*;
import java.util.*;

class Node {
  char data;
  int left, right;

  public Node(char data) {
    this.data = data;
    this.left = -1; // 자식이 없음을 -1로 표시
    this.right = -1;
  }
}

public class Q1231_중위순회 {
  static Node[] tree;
  static StringBuilder sb;
  
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    for (int tc = 1; tc <= 10; tc++) {
      int N = Integer.parseInt(br.readLine());
      tree = new Node[N + 1];
      
      for (int i = 0 ; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int idx = Integer.parseInt(st.nextToken());
        char data = st.nextToken().charAt(0);
        
        tree[idx] = new Node(data);
        
        // 왼쪽 자식이 있다면 연결
        if (st.hasMoreTokens()) {
          tree[idx].left = Integer.parseInt(st.nextToken());
        }
        // 오른쪽 자식이 있다면 연결
        if (st.hasMoreTokens()) {
          tree[idx].right = Integer.parseInt(st.nextToken());
        }
      }
      
      // 중위 순회 시작(1번부터)
      sb = new StringBuilder();
      inOrder(1);
      System.out.println("#" + tc + " " + sb);
    }
  }

  static void inOrder(int current) {
    if (current == -1) return; // 자식이 없으면 돌아감

    inOrder(tree[current].left); //왼쪽
    sb.append(tree[current].data);
    inOrder(tree[current].right); // 오른쪽
  }
}
