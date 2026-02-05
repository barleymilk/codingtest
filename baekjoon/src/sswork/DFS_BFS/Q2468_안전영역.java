package sswork.DFS_BFS;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q2468_안전영역 {
  static int N;
  static int minHeight;
  static int maxHeight;
  static int[][] map;
  static boolean[][] visited;
  static int rainfall;
  static int[] dx = {-1,1,0,0};
  static int[] dy = {0,0,-1,1};
  static int count;

  static void dfs(int x, int y) {
    // 종결 조건
    if (visited[x][y]) return;

    visited[x][y] = true;

    // 잠기지 않은 영역
    if (map[x][y] > rainfall) {
      count++;
      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
          dfs(nx, ny);
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    minHeight = Integer.MAX_VALUE;
    maxHeight = Integer.MIN_VALUE;
    N = sc.nextInt();
    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        map[i][j] = sc.nextInt();
        if (map[i][j] < minHeight) minHeight = map[i][j];
        if (map[i][j] > maxHeight) maxHeight = map[i][j];
      }
    }

    int maxRegions = 0;
    int regions = 0;
    rainfall = minHeight - 1;

    // 강수량에 따른 dfs
    while (rainfall < maxHeight) {
      visited = new boolean[N][N];
      regions = 0;

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          count = 0;
          dfs(i, j);
          if (count != 0) regions++;
        }
      }

      if (regions > maxRegions) maxRegions = regions;
      // System.out.println("강수량: " + rainfall + " " + "영역 수: " + regions + " " + "최대 영역 수: " + maxRegions);
      rainfall++;
    }

    System.out.println(maxRegions);

    sc.close();
  }
}
