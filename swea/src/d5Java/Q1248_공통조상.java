package d5Java;

import java.io.*;
import java.util.*;

public class Q1248_공통조상 {
  static class Node {
    int left = 0, right = 0; // 자식 노드 번호 (0: 자식 없음)
  }

  static Node[] tree;
  static int[] parent;
  static boolean[] visited;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int V = Integer.parseInt(st.nextToken());
      int E = Integer.parseInt(st.nextToken());
      int target1 = Integer.parseInt(st.nextToken());
      int target2 = Integer.parseInt(st.nextToken());

      tree = new Node[V + 1];
      parent = new int[V + 1];
      for (int i = 1; i <= V; i++) tree[i] = new Node();

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < E; i++) {
        int p = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        parent[c] = p; // 부모 정보 저장
        if (tree[p].left == 0) tree[p].left = c; // 왼쪽 자식으로 연결
        else tree[p].right = c; // 왼쪽이 있으면 오른쪽으로 연결 
      }

      // 1. 공통 조상 찾기
      visited = new boolean[V + 1];
      int lca = 0;

      // target1의 조상을 모두 방문 표시
      int curr = target1;
      while (curr != 0) {
        visited[curr] = true;
        curr = parent[curr];
      }

      // target2가 올라가면서 처음 만나는 방문지가 LCA
      curr = target2;
      while (curr != 0) {
        if (visited[curr]) {
          lca = curr;
          break;
        }
        curr = parent[curr];
      }

      // 2. lca를 루트로 하는 서브트리 크기 구하기
      int subtreeSize = countNodes(lca);
      System.out.println("#" + tc + " " + lca + " " + subtreeSize);
    }
  }

  // 서브트리의 노드 개수를 세는 재귀 함수
  static int countNodes(int node) {
    if (node == 0) return 0; // 자식이 없으면 return 

    // 나 자신(1) + 왼쪽 자식들의 합 + 오른쪽 자식들의 합
    return 1 + countNodes(tree[node].left) + countNodes(tree[node].right);
  }
}
