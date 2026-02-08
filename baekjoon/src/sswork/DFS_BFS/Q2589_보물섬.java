package sswork.DFS_BFS;

import java.io.*;
import java.util.*;

public class Q2589_보물섬 {
  static int row; static int col;
  static char[][] board;
  static boolean[][] visited;
  static int[] dx = {-1,1,0,0};
  static int[] dy = {0,0,-1,1};

  static int bfs(int x, int y) {
    int maxDistance = 0;

    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{x, y, 0}); // x좌표, y좌표, 거리
    visited[x][y] = true;

    while(!q.isEmpty()) {
      int[] curr = q.poll();
      int cx = curr[0];
      int cy = curr[1];
      int distance = curr[2];

      maxDistance = Math.max(maxDistance, distance);

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        // 범위 내에 있고, 방문하지 않았고, 육지('L')인 경우
        if (nx >= 0 && ny >= 0 && nx < row && ny < col && !visited[nx][ny] && board[nx][ny] == 'L') {
          visited[nx][ny] = true;
          q.offer(new int[]{nx, ny, distance + 1});          
        }
      }
    }
    return maxDistance;
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    row = Integer.parseInt(st.nextToken());
    col = Integer.parseInt(st.nextToken());

    board = new char[row][col];
    for (int i = 0; i < row; i++) {
      String line = br.readLine();
      for (int j = 0; j < col; j++) {
        board[i][j] = line.charAt(j);
      }
    }

    int answer = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        // 모든 육지에 대해서 bfs를 돌려봄 
        if (board[i][j] == 'L') {
          visited = new boolean[row][col];
          int result = bfs(i, j);
          answer = Math.max(answer, result);
        }
      }
    }

    System.out.println(answer);
  }
}
