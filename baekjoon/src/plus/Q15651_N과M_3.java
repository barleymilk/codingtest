package plus;

import java.io.*;
import java.util.*;

public class Q15651_N과M_3 {
  static int N, M;
  static int[] result;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    result = new int[M];
    dfs(0);
    System.out.println(sb);
  }

  static void dfs(int depth) {
    if (depth == M) {
      for (int i = 0; i < M; i++) {
        sb.append(result[i]).append(" ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 1; i <= N; i++) {
      result[depth] = i;
      dfs(depth + 1);
    }
  }
}
