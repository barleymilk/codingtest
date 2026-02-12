package unrank;

import java.io.*;
import java.util.*;

public class Q1949_등산로조성 {
  static int N, K, maxDistance;
  static int[][] map;
  static boolean[][] visited;
  static boolean isConstructed;
  static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    
    for (int tc = 1; tc <= T; tc++) {
      // 데이터 입력 받아오기
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      map = new int[N][N];
      visited = new boolean[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      // 가장 높은 봉우리 시작
      // 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결이 되어야 함.
      // 딱 한 곳을 정해서 최대 K 깊이만큼 지형을 깎는 공사 가능
      maxDistance = 0;
      int maxHeight = 0;

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          maxHeight = findMaxHeight();
          if (map[i][j] == maxHeight) { // 가장 높은 봉우리에서 시작
            visited[i][j] = true; // 방문 처리
            isConstructed = false;
            dfs(i, j, map[i][j], 1);
            visited[i][j] = false; // 백트래킹
          }
        }
      }

      System.out.println("#" + tc + " " + maxDistance);
    }
  }

  static int findMaxHeight() {
    int maxHeight = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        maxHeight = Math.max(maxHeight, map[i][j]);
      }
    }
    return maxHeight;
  }

  static void dfs(int x, int y, int currentHeight, int currentDistance) {
    maxDistance = Math.max(maxDistance, currentDistance);
    
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (ny < 0 || nx < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

      // 분기 1) 깎지 않고 갈 때
      if (map[nx][ny] < currentHeight) {
        visited[nx][ny] = true;
        dfs(nx, ny, map[nx][ny], currentDistance + 1);
        visited[nx][ny] = false;
      }
      // 분기 2) 깎고 갈 때
      else if (!isConstructed) {
        int difference = map[nx][ny] - currentHeight + 1; // 공사가 필요한 최소 높이
        if (difference <= K) { // 높이 차이가 K 이하여야 공사 가능
          visited[nx][ny] = true;
          isConstructed = true;
          dfs(nx, ny, currentHeight - 1, currentDistance + 1);
          isConstructed = false;
          visited[nx][ny] = false;
        }
      }
    }
  }
}
