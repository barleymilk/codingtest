package plus;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15650_N과M_2 {
  static int N, M;
  static int[] result;
  static boolean[] visited;
  static StringBuilder sb = new StringBuilder();

  static void dfs(int start, int depth) {
    if (depth == M) {
      for (int i = 0; i < M; i++) {
        sb.append(result[i]).append(" ");
      }
      sb.append("\n");
      return;
    }

    for (int i = start; i <= N; i++) {
      result[depth] = i;
      dfs(i + 1, depth + 1);
    }
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    result = new int[M];
    dfs(1, 0);
    System.out.println(sb);
  }
}
