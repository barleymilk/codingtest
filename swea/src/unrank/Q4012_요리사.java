package unrank;

import java.io.*;
import java.util.*;

public class Q4012_요리사 {
  static int N, minDifference;
  static int[][] table;
  static boolean[] isSelected;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    
    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());
      table = new int[N][N];

      StringTokenizer st;
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          table[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      isSelected = new boolean[N]; // A식재료(true), B식재료(false)
      minDifference = Integer.MAX_VALUE;
      dfs(0, 0);

      System.out.println("#" + tc + " " + minDifference);
    }
  }

  static void dfs(int start, int depth) {
    if (depth == N / 2) {
      // minDifference 갱신하기
      int difference = calculate();
      minDifference = Math.min(minDifference, difference);
      return;
    }

    for (int i = start; i < N; i++) { // 모든 식재료(N개)에서 조함 뽑기
      isSelected[i] = true;
      dfs(i + 1, depth + 1);
      isSelected[i] = false;
    }
  }

  static int calculate() {
    int synergyA = 0, synergyB = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        // 같은 재료끼리는 시너지 0 -> 통과
        if (i == j) continue;

        // A 식재료 시너지 합
        if (isSelected[i] && isSelected[j]) {
          synergyA += table[i][j];
        }
        // B 식재료 시너지 합
        else if (!isSelected[i] && !isSelected[j]) {
          synergyB += table[i][j];
        }
      }
    }

    return Math.abs(synergyA - synergyB);
  }
}
