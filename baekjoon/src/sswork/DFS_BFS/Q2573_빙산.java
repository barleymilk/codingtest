package sswork.DFS_BFS;

import java.io.*;
import java.util.*;

public class Q2573_빙산 {
  static int N; static int M;
  static int[][] board;
  static boolean[][] visited;
  static int[] dx = {-1,1,0,0};
  static int[] dy = {0,0,-1,1};

  static int[][] getNextBoard() {
    int[][] nextBoard = new int[N][M];
    for (int x = 0; x < N; x++) {
      for (int y = 0; y < M; y++) {
        int sea = 0;
        for (int i = 0; i < 4; i++) {
          if (board[x][y] != 0) { // 빙산인 경우
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 유효한 범위, 바다인 경우
            if (nx >= 0 && ny >= 0 && nx < N && ny < M && board[nx][ny] == 0) sea++;
          }
        }
        nextBoard[x][y] = Math.max(0, board[x][y] - sea); // 빙산 높이 업데이트(0 이상)
      }
    }
    return nextBoard;
  }

  static void bfs(int x, int y) {
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{x, y});
    visited[x][y] = true;
    
    while(!q.isEmpty()) {
      int[] curr = q.poll();
      for (int i = 0; i < 4; i++) {
        int nx = curr[0] + dx[i];
        int ny = curr[1] + dy[i];
        // 유효한 범위이고, 방문한 적 없는 경우 
        if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
          if (board[nx][ny] > 0) { // 빙산인 경우
            visited[nx][ny] = true;
            q.offer(new int[]{nx, ny});
          }
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int year = 0;
    while(true) {
      // 1. 덩어리 개수 세기
      int chunks = 0;
      visited = new boolean[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          // 방문한 적 없는 빙산 발견 -> 덩어리 탐색 -> 덩어리 + 1
          if (board[i][j] > 0 && !visited[i][j]) {
            bfs(i, j);
            chunks++;
          }
        }
      }

      // 2. 조건 체크 -> 해당하면 조건에 따라 출력, break
      if (chunks >= 2) { // (1) 2개 이상으로 분리된 경우
        System.out.println(year);
        break;
      }
      if (chunks == 0) { // (2) 다 녹을 때까지 분리되지 않은 경우 
        System.out.println(0);
        break;
      }

      // 3. 빙산 녹이기 (아직 1덩어리면 녹이고 다음 해로)
      board = getNextBoard();
      year++;
    }
  }
}
