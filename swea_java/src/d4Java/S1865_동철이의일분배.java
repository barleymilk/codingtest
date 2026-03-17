package d4Java;

import java.io.*;
import java.util.*;

public class S1865_동철이의일분배 {
  static int N;
  static double maxResult;
  static int[][] success;
  static boolean[] selected;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());
      success = new int[N + 1][N + 1];
      selected = new boolean[N + 1];
      StringTokenizer st;
      for (int i = 1; i <= N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 1; j <= N; j++) {
          success[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      maxResult = 0.0;
      dfs(1, 1.0);
      System.out.println("#" + tc + " " + String.format("%.6f", maxResult * 100));
    }
  }

  static void dfs(int person, double currentProb) {
    // 가지치기: 현재까지 계산된 확률이 현재 최대 확률보다 작거나 같으면 더 이상 탐색 안 함
    if (currentProb <= maxResult) {
      return;
    }

    // 리턴 조건
    if (person == N + 1) {
      maxResult = Math.max(maxResult, currentProb);
      return;
    }

    for (int work = 1; work <= N; work++) {
      if (!selected[work]) {
        selected[work] = true;
        dfs(person + 1, currentProb * (success[person][work] * 0.01));
        selected[work] = false;
      }
    }
  }
}
