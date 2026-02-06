package sswork.DFS_BFS;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q2636_치즈 {
  static int height; static int width;
  static int[][] board;
  static boolean[][] visited;
  static int[] dx = {-1,1,0,0};
  static int[] dy = {0,0,-1,1};

  static void bfs() {
    // (0, 0)은 무조건 바깥 공기
    // 1. 공기인 부분만 탐색
    // 2. 치즈 경계 표시
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{0, 0});
    visited = new boolean[height][width];
    visited[0][0] = true;

    while(!q.isEmpty()) {
      int[] curr = q.poll();
      for (int i = 0; i < 4; i++) {
        int nx = curr[0] + dx[i];
        int ny = curr[1] + dy[i];

        if (nx < 0 || nx >= height || ny < 0 || ny >= width || visited[nx][ny]) continue;
        
        // 바깥 공기
        if (board[nx][ny] == 0) {
          visited[nx][ny] = true;
          q.offer(new int[]{nx, ny});
        }
        // 치즈 -> 녹을 치즈만 마킹
        else if (board[nx][ny] == 1) {
          visited[nx][ny] = true;
          board[nx][ny] = 2; // 1시간 후 녹을 치즈(2)
        }
      }
    }
  }

  static int melting() {
    int count = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (board[i][j] == 2) {
          board[i][j] = 0;
          count++;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    height = sc.nextInt();
    width = sc.nextInt();

    board = new int[height][width];
    
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        board[i][j] = sc.nextInt();
      }
    }

    int time = 0;
    int lastCheeseCount = 0;
    while (true) {
      bfs();
      int meltedCount = melting();
      if (meltedCount == 0) break;

      lastCheeseCount = meltedCount;
      time++;
    }

    System.out.println(time);
    System.out.println(lastCheeseCount);

    sc.close();
  }
}
