package sswork.pastquestions;

import java.io.*;
import java.util.*;

public class Q16234_인구이동 {
  static int N, L, R;
  static int[][] map;
  static boolean[][] visited;
  static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    int days = 0;
    while (true) {
      visited = new boolean[N][N];
      boolean isMoved = false;

      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (!visited[i][j]) {
            if (movePopulation(i, j)) { // 하나의 연합이라도 인구 이동이 있었다면
              isMoved = true;
            }
          }
        }
      }

      if (!isMoved) break;
      days++;
    }

    System.out.println(days);
  }

  static boolean movePopulation(int x, int y) {
    Queue<int[]> q = new LinkedList<>();
    ArrayList<int[]> federation = new ArrayList<>(); // 연합 국가들의 좌표 저장

    q.add(new int[]{x, y});
    federation.add(new int[]{x, y});
    visited[x][y] = true;

    int sum = map[x][y];

    while(!q.isEmpty()) {
      int[] curr = q.poll();

      for (int i = 0; i < 4; i++) {
        int nx = curr[0] + dx[i];
        int ny = curr[1] + dy[i];

        if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
          // 인접 국가와의 인구 차이 계산
          int diff = Math.abs(map[curr[0]][curr[1]] - map[nx][ny]);
          if (diff >= L && diff <= R) {
            visited[nx][ny] = true;
            q.add(new int[]{nx, ny});
            federation.add(new int[]{nx, ny});
            sum += map[nx][ny];
          }
        }
      }
    }

    // 연합 형성되었다면 인구 이동시키기
    if (federation.size() > 1) {
      int avg = sum / federation.size();
      for (int[] pos: federation) {
        map[pos[0]][pos[1]] = avg;
      }
      return true;
    }

    return false;
  }
}
