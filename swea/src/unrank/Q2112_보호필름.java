package unrank;

import java.io.*;
import java.util.*;

public class Q2112_보호필름 {
  static int D, W, K, minInput;
  static int[][] film;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    
    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      D = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      film = new int[D][W];
      for (int i = 0; i < D; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < W; j++) {
          film[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      minInput = D; // 초기화: 약품을 투입하지 않음
      dfs(0,0);
      
      System.out.println("#" + tc + " " + minInput);
    }
  }

  static void dfs(int row, int cnt) {
    if (cnt >= minInput) return;

    if (row == D) {
      if (check()) {
        minInput = Math.min(minInput, cnt);
      }
      return;
    }

    // 1) 투입 안 함
    dfs(row + 1, cnt);

    int[] temp = film[row].clone(); // 원본 행 백업
    
    // 2) 0 투입
    Arrays.fill(film[row], 0);
    dfs(row + 1, cnt + 1);

    // 3) 1 투입
    Arrays.fill(film[row], 1);
    dfs(row + 1, cnt + 1);

    film[row] = temp; // 원상복구(백트래킹)
  }

  static boolean check() {
    for (int c = 0; c < W; c++) {
      boolean pass = false;
      int count = 1;
      
      for (int r = 0; r < D - 1; r++) {
        if (film[r][c] == film[r+1][c]) {
          count++;
        }
        else {
          count = 1;
        }

        if (count >= K) {
          pass = true;
          break;
        }
      }

      // K가 1인 경우는 무조건 통과
      if (K == 1) pass = true;
      // 한 컬럼이라도 통과 못하면 실패
      if (!pass) return false;
    }
    return true;
  }
}
