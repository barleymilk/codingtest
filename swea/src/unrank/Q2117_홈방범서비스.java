package unrank;

import java.io.*;
import java.util.*;

public class Q2117_홈방범서비스 {
  static int N, M, maxHomeCount;
  static int[][] map;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      map = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      
      maxHomeCount = 0;
      int homeCount = 0, expense = 0, fee = 0;
      for (int k = 1; k <= N+1; k++) { // k: 범위
        expense = calcExpense(k); // 보안회사에서 지불하는 비용

        for (int i = 0; i < N; i++) { // i: 행
          for (int j = 0; j < N; j++) { // j: 열
            homeCount = countHome(i, j, k);

            // 이익
            fee = homeCount * M - expense;

            // 손해가 나지 않는다면 집 최대 개수 갱신
            if (fee >= 0) {
              maxHomeCount = Math.max(maxHomeCount, homeCount);
            }
          }
        }
      }

      System.out.println("#" + tc + " " + maxHomeCount);
    }
  }

  static int countHome(int i, int j, int scope) {
  int count = 0;
  int limit = scope - 1; 

  for (int dx = -limit; dx <= limit; dx++) {
    int dyLimit = limit - Math.abs(dx);
    for (int dy = -dyLimit; dy <= dyLimit; dy++) {
      int nx = i + dx;
      int ny = j + dy;

      if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
        if (map[nx][ny] == 1) count++;
      }
    }
  }
  return count;
}

  // 보안회사의 비용 
  static int calcExpense(int K) {
    return K * K + (K - 1) * (K - 1);
  }
}
