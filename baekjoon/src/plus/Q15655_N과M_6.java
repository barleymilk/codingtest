package plus;

import java.io.*;
import java.util.*;

public class Q15655_N과M_6 {
  static int N, M;
  static int[] arr;
  static int[] result;
  static StringBuilder sb = new StringBuilder();
  
  static void dfs(int start, int depth) {
    if (depth == M) {
      for (int i = 0; i < M; i++) {
        sb.append(result[i]).append(" ");
      }
      sb.append("\n");
      return;
    }

    for (int i = start; i < N; i++) {
      result[depth] = arr[i];
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
    arr = new int[N];
    
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
    Arrays.sort(arr);

    dfs(0, 0);
    System.out.println(sb);
  }
}
