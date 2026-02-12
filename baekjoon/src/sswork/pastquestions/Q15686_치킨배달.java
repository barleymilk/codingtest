package sswork.pastquestions;

import java.io.*;
import java.util.*;

public class Q15686_치킨배달 {
  static int N, M, finalMinDistSum;
  static int[][] map;
  static ArrayList<int[]> chickenShops, houses;
  static boolean[] visited;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken()); // 도시 크기 (N x N)
    M = Integer.parseInt(st.nextToken()); // 최대 치킨집 개수
    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // 가정집들, 치킨집들 좌표 저장하기
    houses = new ArrayList<>();
    chickenShops = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (map[i][j] == 1) {
          houses.add(new int[]{i, j});
        }
        else if (map[i][j] == 2) {
          chickenShops.add(new int[]{i, j});
        }
      }
    }
    // 치킨집 크기만큼 visited 배열 초기화 
    visited = new boolean[chickenShops.size()];

    finalMinDistSum = Integer.MAX_VALUE;
    dfs(0, 0);

    System.out.println(finalMinDistSum);
  }

  static void dfs (int start, int depth) {
    if (depth == M) {
      int minDistSum = getDistanceSum();
      finalMinDistSum = Math.min(finalMinDistSum, minDistSum);
      return;
    }

    for (int i = start; i < chickenShops.size(); i++) {
      visited[i] = true;
      dfs(i + 1, depth + 1);
      visited[i] = false;
    }
  }

  static int getDistanceSum() {
    int totalCityDist = 0;
    
    for (int[] house : houses) { // 모든 집 순회
      int minHouseDist = Integer.MAX_VALUE;
      
      for (int i = 0; i < chickenShops.size(); i++) { // 모든 치킨집 순회
        if (visited[i]) { // 선택된 치킨집만 계산
          int[] shop = chickenShops.get(i);
          int dist = calcDistance(house[0], house[1], shop[0], shop[1]);
          
          // 이 집에서 가장 가까운 치킨집 거리 갱신
          minHouseDist = Math.min(minHouseDist, dist);
        }
      }
      // 이 집의 최종 치킨 거리를 도시 합계에 누적
      totalCityDist += minHouseDist;
    }

    return totalCityDist;
  }

  // 두 칸 (r1, c1), (r2, c2) 사이의 거리
  static int calcDistance(int r1, int c1, int r2, int c2) {
    return Math.abs(r1 - r2) + Math.abs(c1 - c2);
  }
}
