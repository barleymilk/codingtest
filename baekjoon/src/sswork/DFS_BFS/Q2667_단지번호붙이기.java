package sswork.DFS_BFS;

import java.io.FileInputStream;
import java.util.*;

public class Q2667_단지번호붙이기 {
  static int N;
  static boolean[][] visited;
  static int[][] map;
  static Stack<Integer> complex;
  static int[] dx = {0,0,-1,1};
  static int[] dy = {-1,1,0,0};
  static int count;

  static void dfs(int x, int y) {
    // 종결 조건
    if (visited[x][y]) return;

    visited[x][y] = true; // 방문
    
    if (map[x][y] == 1) { // 1인 경우 이동하며 dfs해야 함.
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

    N = sc.nextInt();
    visited = new boolean[N][N];
    map = new int[N][N];
    for (int i = 0; i < N; i++) {
      String str = sc.next();
      for (int j = 0; j < N; j++) {
        map[i][j] = str.charAt(j) - '0';
      }
    }
    
    complex = new Stack<>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        count = 0;
        dfs(i, j);
        if (count != 0) complex.push(count);
      }
    }


    int num = complex.size();
    int[] answer = new int[num];
    for (int i = 0; i < num; i++) {
      answer[i] = complex.pop();
    }
    Arrays.sort(answer);

    System.out.println(num);
    for (int i = 0; i < num; i++) System.out.println(answer[i]);

    sc.close();
  }
}
