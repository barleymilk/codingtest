package unrank;

import java.io.*;
import java.util.*;

public class Q2115_벌꿀채취 {
  static int N, M, C;
  static int[][] hive, maxCostTable;
  static int maxCost; // 선택된 M개 구역에서의 최대 가치

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken()); // 벌통 크기(N x N)
      M = Integer.parseInt(st.nextToken()); // 수확 범위(1명)
      C = Integer.parseInt(st.nextToken()); // 최대 수확량(1명)

      // 벌통 데이터 입력 받기
      hive = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          hive[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      // 구역마다 벌꿀의 가격 (열: 해당 구역의 가장 왼쪽 idx)
      maxCostTable = new int[N][N - M + 1];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j <= N-M; j++) {
          maxCostTable[i][j] = getMaxCost(i, j);
        }
      }

      // 두 일꾼의 최대 합 구하기
      int finalCost = 0;
      for (int i1 = 0; i1 < N; i1++) {
          for (int j1 = 0; j1 <= N - M; j1++) {
              // 일꾼 1의 수익
              int worker1 = maxCostTable[i1][j1];

              // 일꾼 2는 일꾼 1 이후의 좌표부터 탐색
              for (int i2 = i1; i2 < N; i2++) {
                  for (int j2 = 0; j2 <= N - M; j2++) {
                      // 구역이 겹치면 패스
                      if (i1 == i2 && j2 < j1 + M) continue;
                      
                      finalCost = Math.max(finalCost, worker1 + maxCostTable[i2][j2]);
                  }
              }
          }
      }

      System.out.println("#" + tc + " " + finalCost);
    }
  }

  static int getMaxCost(int x, int y) {
    maxCost = 0; // 초기화 

    // 일꾼 한 명이 채집 가능한 구역
    int[] selectedHives = new int[M];
    for (int i = 0; i < M; i++) {
      selectedHives[i] = hive[x][y+i];
    }

    // 부분집합 구해서 최고 가격 구하기
    calcMaxCostWithSubset(0, 0, 0, selectedHives);

    return maxCost;
  }

  static void calcMaxCostWithSubset(int depth, int currentHoneySum, int currentPowSum, int[] data) {
    // 꿀 양 초과 시 가지치기
    if (currentHoneySum > C) return;

    if (depth == M) {
      maxCost = Math.max(maxCost, currentPowSum); // 최댓값 갱신
      return;
    }

    // 분기 1) 현재 벌통의 꿀을 채취
    int honey = data[depth];
    calcMaxCostWithSubset(depth + 1, currentHoneySum + honey, currentPowSum + (honey * honey), data);

    // 분기 2) 현재 벌퉁의 꿀을 채취하지 않음
    calcMaxCostWithSubset(depth + 1, currentHoneySum, currentPowSum, data);
  }
}
