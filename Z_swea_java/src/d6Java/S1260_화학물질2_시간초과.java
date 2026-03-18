package d6Java;

import java.io.*;
import java.util.*;

public class S1260_화학물질2_시간초과 {
  static int N, size, minResult;
  static int[][] map;
  static List<int[]> matrixSize;

  static boolean[] selected;
  static int[] perm;

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
      // 구역 확인
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
      selected = new boolean[size];
      perm = new int[size];
      minResult = Integer.MAX_VALUE;
      dfs(0);

      System.out.println("#" + tc + " " + minResult);
    }
  }

  static void dfs(int depth) {
    if (depth == matrixSize.size()) {
      // 연결 가능한지 확인
      if (isPossible()) {
        int result = calc();
        minResult = Math.min(minResult, result);
      }
      return;
    }

    for (int i = 0; i < matrixSize.size(); i++) {
      if (!selected[i]) {
        selected[i] = true;
        perm[depth] = i;
        dfs(depth + 1);
        selected[i] = false;
      }
    }
  }

  static boolean isPossible() {
    int[] left, right;
    left = matrixSize.get(perm[0]);
    for (int i = 1; i < size; i++) {
      right = matrixSize.get(perm[i]);

      if (left[1] != right[0]) {
        return false;
      }
      else {
        left = new int[]{left[0], right[1]};
      }
    }

    return true;
  }

  static int calc() {
    int result = 0;
    int[] left, right;

    left = matrixSize.get(perm[0]);
    for (int i = 1; i < size; i++) {
      right = matrixSize.get(perm[i]);
      result += left[0] * right[0] * right[1];
      left = new int[]{left[0], right[1]};
    }

    return result;
  }
}
