package d4Java;

import java.io.*;
import java.util.*;

public class Q_수영대회결승전_re1 {
  static int[] dx = {-1,1,0,0};
  static int[] dy = {0,0,-1,1};

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine().trim());
    for (int tc = 1; tc <= T; tc++) {
      // 1. input값 받아오기 
      int N = Integer.parseInt(br.readLine().trim());
      int[][] ocean = new int[N][N];
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          ocean[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      StringTokenizer st = new StringTokenizer(br.readLine());
      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      int C = Integer.parseInt(st.nextToken());
      int D = Integer.parseInt(st.nextToken());

      // 2. bfs 준비 - PriorityQueue (시간순 정렬)
      PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
      int[][] visited = new int[N][N];
      for (int i = 0; i < N; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);

      pq.offer(new int[]{A, B, 0});
      visited[A][B] = 0;
      int answer = -1;

      while(!pq.isEmpty()) {
        int[] curr = pq.poll();
        int x = curr[0];
        int y = curr[1];
        int time = curr[2];

        // 목적지 도달 시 즉시 종료
        if (x == C && y == D) {
          answer = time;
          break;
        }

        // 이미 더 짧은 시간에 방문했다면 스킵
        if (visited[x][y] < time) continue;

        for (int i = 0; i < 4; i++) {
          int nx = x + dx[i];
          int ny = y + dy[i];

          if (nx >= 0 && nx < N && ny >= 0 && ny < N && ocean[nx][ny] != 1) {
            int nextTime;

            if (ocean[nx][ny] == 2) {
                int wait = (time % 3 == 0) ? 2 : (time % 3 == 1 ? 1 : 0);
                nextTime = time + wait + 1;
            } else {
                nextTime = time + 1;
            }

            if (visited[nx][ny] > nextTime) {
              visited[nx][ny] = nextTime;
              pq.offer(new int[]{nx, ny, nextTime});
            }
          }
        }
      }

      System.out.println("#" + tc + " " + answer);
    }

    br.close();
  }
}
