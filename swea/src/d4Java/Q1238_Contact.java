package d4Java;

import java.io.FileInputStream;
import java.util.*;

public class Q1238_Contact {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    for (int tc = 1; tc <= 10; tc++) {
      int N = sc.nextInt();
      int start = sc.nextInt();

      int[][] adj = new int[101][101];
      for (int i = 0; i < N; i += 2) {
        int from = sc.nextInt();
        int to = sc.nextInt();
        adj[from][to] = 1;
      }

      int[] queue = new int[101];
      int[] visited = new int[101];
      int front = 1;
      int rear = 1;
      queue[front] = start;
      visited[start] = 1;
      int maxDepth = 1; // 가장 깊은 단계

      while (true) {
        if (front > rear) break;

        int q = queue[front++];

        for (int i = 1; i <= 100; i++) {
          if (adj[q][i] == 1 && visited[i] == 0) {
            visited[i] = visited[q] + 1;
            queue[++rear] = i;
            maxDepth = Math.max(maxDepth, visited[i]);
          }
        }
      }

      int answer = 0;
      for (int i = 1; i <= 100; i++) {
        if (visited[i] == maxDepth) {
          answer = Math.max(answer, i);
        }
      }

      System.out.println("#" + tc + " " + answer);
    }
    sc.close();
  }
}