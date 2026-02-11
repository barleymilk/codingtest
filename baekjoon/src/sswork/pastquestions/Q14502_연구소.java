package sswork.pastquestions;

import java.io.*;
import java.util.*;

public class Q14502_연구소 {
  static int N, M, maxSafeCount = 0;
  static int[][] map;
  static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    buildWall(0, 0);

    System.out.println(maxSafeCount);
  }

  static void buildWall(int start, int count) {
    if (count == 3) { // 벽은 3개 세울 수 있음 
      spreadAndCount(); // 바이러스 퍼뜨리기 + 안전 영역 세기 
      return;
    }
    
    for (int i = start; i < N * M; i++) {
      int r = i / M;
      int c = i % M;

      if (map[r][c] == 0) { // 가지치기
        map[r][c] = 1;
        buildWall(i + 1, count + 1);
        map[r][c] = 0;
      }
    }
  }

  static void spreadAndCount() {
    int[][] tempMap = new int[N][M];
    Queue<int[]> q = new LinkedList<>();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        tempMap[i][j] = map[i][j];
        if (tempMap[i][j] == 2) {
          q.offer(new int[]{i, j}); // 바이러스 위치 큐 저장
        }
      }
    }

    // BFS로 바이러스 확산
    while(!q.isEmpty()) {
      int[] curr = q.poll();
      
      for (int i = 0; i < 4; i++) {
        int nx = curr[0] + dx[i];
        int ny = curr[1] + dy[i];

        if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
          if (tempMap[nx][ny] == 0) {
            tempMap[nx][ny] = 2;
            q.offer(new int[]{nx, ny});
          }
        }
      }
    }

    // 3. 안전 영역(0) 카운트
    int safe = 0;
    for (int[] row: tempMap) {
      for (int val: row) {
        if (val == 0) safe++;
      }
    }
    maxSafeCount = Math.max(maxSafeCount, safe);
  }
}
