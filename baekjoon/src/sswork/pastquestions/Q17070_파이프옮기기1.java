package sswork.pastquestions;

import java.io.*;
import java.util.*;

public class Q17070_파이프옮기기1 {
  static int N, answer;
  static int[][] map;
  static int[] dx = {0, 1, 1}, dy = {1, 0, 1}; // 가로, 세로, 대각선 방향

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    N = Integer.parseInt(br.readLine());
    
    map = new int[N][N];
    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    answer = 0; // 이동시킬 수 없는 경우 0 출력
    dfs(0, 1, 0);

    System.out.println(answer);
  }

  static void dfs(int x, int y, int d) {
    if (x == N-1 && y == N-1) {
      answer += 1;
      return;
    }

    for (int i = 0; i < 3; i++) {
      // 가로 방향일 때 -> 세로 방향으로 못 감
      if (d == 0 && i == 1) continue;
      // 세로 방향일 때 -> 가로 방향으로 못 감
      if (d == 1 && i == 0) continue;

      int nx = x + dx[i];
      int ny = y + dy[i];

      // 범위를 벗어나면 패스
      if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
      // 벽을 만나면 패스
      if (i != 2 && map[nx][ny] == 1) continue;
      else if (i == 2 && (map[nx][ny] == 1 || map[nx-1][ny] == 1 || map[nx][ny-1] == 1)) continue;

      dfs(nx, ny, i);
    }
  }
}
