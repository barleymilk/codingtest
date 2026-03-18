package d6Java;

import java.io.*;
import java.util.*;

public class S1260_화학물질2_개선 {
  static int N, size, minResult;
  static int[][] map;
  static List<int[]> matrixSize;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input_s1260.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine().trim());

    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine().trim());
      map = new int[N][N];
      StringTokenizer st;
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine().trim());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      matrixSize = new ArrayList<>();
      boolean[][] visited = new boolean[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (!visited[i][j] && map[i][j] != 0) {
            int maxR = 0, maxC = 0;
            for (int currR = i; currR < N; currR++) {
              if (map[currR][j] == 0) break;
              maxR = currR;
            }
            for (int currC = j; currC < N; currC++) {
              if (map[i][currC] == 0) break;
              maxC = currC;
            }

            // 행렬 크기 저장
            matrixSize.add(new int[]{maxR - i + 1, maxC - j + 1});

            // 남은 visited 채우기
            for (int r = i; r <= maxR; r++) {
              for (int c = j; c <= maxC; c++) {
                visited[r][c] = true;
              }
            }
          }
        }
      }
      
      size = matrixSize.size();
      minResult = Integer.MAX_VALUE;

      // 1. 행렬 정렬 (시작 행렬 찾기)
      int[][] sortedMatrix = new int[size][2];
      int startIdx = 0;
      for (int i = 0; i < size; i++) {
        boolean isStart = true;
        for (int j = 0; j < size; j++) {
          if (matrixSize.get(i)[0] == matrixSize.get(j)[1]) {
            isStart = false;
            break;
          }
        }
        if (isStart) {
          startIdx = i;
          break;
        }
      }

      // 2. 체인 완성
      sortedMatrix[0] = matrixSize.get(startIdx);
      for (int i = 1; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (sortedMatrix[i-1][1] == matrixSize.get(j)[0]) {
            sortedMatrix[i] = matrixSize.get(j);
            break;
          }
        }
      }

      // 3. 차원 배열 p 구성 (p0, p1, ..., pn)
      int[] p = new int[size + 1];
      p[0] = sortedMatrix[0][0];
      for (int i = 0; i < size; i++) {
        p[i+1] = sortedMatrix[i][1];
      }

      // 4. 연쇄 행렬 곱셈 DP (O(N^3))
      int[][] dp = new int[size + 1][size + 1];
      for (int len = 2; len <= size; len++) { // 구간 길이
        for (int i = 1; i <= size - len + 1; i++) {
          int j = i + len - 1;
          dp[i][j] = Integer.MAX_VALUE;
          for (int k = i; k < j; k++) {
            int cost = dp[i][k] + dp[k+1][j] + (p[i-1] * p[k] * p[j]);
            dp[i][j] = Math.min(dp[i][j], cost);
          }
        }
      }
      minResult = dp[1][size];

      System.out.println("#" + tc + " " + minResult);
    }
  }
}
