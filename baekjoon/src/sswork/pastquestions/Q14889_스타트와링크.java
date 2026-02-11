package sswork.pastquestions;

import java.io.*;
import java.util.*;

public class Q14889_스타트와링크 {
  static int N;
  static int[][] synergyTable;
  static boolean[] isSelected;
  static int minDifference;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    StringTokenizer st;
    synergyTable = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        synergyTable[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    isSelected = new boolean[N];
    minDifference = Integer.MAX_VALUE;
    dfs(0, 0);

    System.out.println(minDifference);
  }

  static void dfs(int start, int depth) {
    if (depth == N / 2) {
      int difference = calc();
      minDifference = Math.min(minDifference, difference);
      return;
    }

    for (int i = start; i < N; i++) {
      isSelected[i] = true;
      dfs(i + 1, depth + 1);
      isSelected[i] = false;
    }
  }

  static int calc() {
    int synergyA = 0;
    int synergyB = 0;
    
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (i == j) continue;

        if (isSelected[i] && isSelected[j]) {
          synergyA += synergyTable[i][j];
        }
        else if (!isSelected[i] && !isSelected[j]) {
          synergyB += synergyTable[i][j];
        }
      }
    }

    return Math.abs(synergyA - synergyB);
  }
}
