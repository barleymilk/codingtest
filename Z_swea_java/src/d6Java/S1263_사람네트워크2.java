package d6Java;

import java.io.*;
import java.util.*;

public class S1263_사람네트워크2 {
  static final int INF = 100_000_000; // 충분히 큰 값

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input_s1263.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine().trim());

    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int[][] dist = new int[N][N];

      // 1. 인접 행렬 입력
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          int val = Integer.parseInt(st.nextToken());
          if (i != j && val == 0) dist[i][j] = INF;
          else dist[i][j] = val;
        }
      }

      // 2. 플로이드-워셜 (O(N^3))
      for (int k = 0; k < N; k++) {
        for (int i = 0; i < N; i++) {
          // i에서 k로 가는 길이 없으면 j를 볼 필요 없음 (성능 최적화)
          if (dist[i][k] == INF) continue; 
          for (int j = 0; j < N; j++) {
            if (dist[i][j] > dist[i][k] + dist[k][j]) {
                dist[i][j] = dist[i][k] + dist[k][j];
            }
          }
        }
      }

      // 3. 각 행의 합 중 최솟값 찾기
      int minCC = Integer.MAX_VALUE;
      for (int i = 0; i < N; i++) {
        int sum = 0;
        for (int j = 0; j < N; j++) {
          sum += dist[i][j];
        }
        minCC = Math.min(minCC, sum);
      }

      System.out.println("#" + tc + " " + minCC);
    }
  }
}
