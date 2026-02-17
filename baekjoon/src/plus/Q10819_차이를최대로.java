package plus;

import java.io.*;
import java.util.*;

public class Q10819_차이를최대로 {
  static int N, maxResult;
  static int[] nums, perm;
  static boolean[] visited;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    N = Integer.parseInt(br.readLine());
    nums = new int[N];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
    
    maxResult = Integer.MIN_VALUE;
    perm = new int[N];
    visited = new boolean[N];
    dfs(0);
    
    System.out.println(maxResult);
  }

  static void dfs(int depth) {
    if (depth == N) {
      calc();
      return;
    }

    for (int i = 0; i < N; i++) {
      if (!visited[i]) {
        visited[i] = true;
        perm[depth] = nums[i];
        dfs(depth + 1);
        visited[i] = false;
      }
    }
  }

  static void calc() {
    int result = 0;
    for (int i = 0; i < N-1; i++) {
      result += Math.abs(perm[i] - perm[i+1]);
    }
    maxResult = Math.max(maxResult, result);
  }
}
