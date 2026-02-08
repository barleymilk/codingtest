package plus;

import java.io.*;
import java.util.*;

public class Q16214_N과M_1 {
  static int N, M;
  static int[] result;
  static boolean[] visited;
  static StringBuilder sb = new StringBuilder();

  static void dfs(int depth) {
    if (depth == M) {
      for (int i = 0; i < depth; i++) {
        sb.append(result[i]).append(" ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 1; i <= N; i++) {
      if (!visited[i]) {
        visited[i] = true;
        result[depth] = i;
        dfs(depth + 1);
        visited[i] = false;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    result = new int[M];
    visited = new boolean[N + 1];
    dfs(0);
    System.out.println(sb);
  }
}
