package d4Java;

import java.io.FileInputStream;
import java.util.*;

public class Q1219_길찾기 {
  static int[][] adj;
  static boolean[] visited;
  static int found;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    for (int tc = 1; tc <= 10; tc++) {
      int T = sc.nextInt(); // 테스트 케이스 번호
      int E = sc.nextInt(); // 간선 개수
      
      adj = new int[100][2];
      for (int i = 0; i < 100; i++) Arrays.fill(adj[i], -1); // 길 정보 -1로 초기화 (-1: 길 없음)

      for (int i = 0; i < E; i++) {
        int start = sc.nextInt();
        int end = sc.nextInt();

        if (adj[start][0] == -1) adj[start][0] = end;
        else adj[start][1] = end;
      }

      visited = new boolean[100];
      found = 0;
      dfs(0);

      System.out.println("#" + T + " " + found);
    }

    sc.close();
  }

  static void dfs(int current) {
    if (current == 99) {
      found = 1; // 99번에 도착하면 성공(1)
      return;
    }

    visited[current] = true;

    // 하나의 정점에서 선택할 수 있는 길의 개수는 2개까지 
    for (int i = 0; i < 2; i++) {
      int next = adj[current][i];
      if (next != -1 && !visited[next]) {
        dfs(next);
        if (found == 1) return; 
      }
    }
  }
}
