package d4Java;

import java.io.*;
import java.util.*;

public class S1211_Ladder2 {
  static int minCount, answer;
  static int[][] board;
  static boolean[][] visited;
  static List<int[]> start;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    for (int tc = 1; tc <= 10; tc++) {
      int N = Integer.parseInt(br.readLine());
      board = new int[100][100];
      start = new ArrayList<>();
      StringTokenizer st;
      for (int i = 0; i < 100; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < 100; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
          if (i == 99 && board[i][j] == 1) start.add(new int[]{i, j});
        }
      }

      minCount = Integer.MAX_VALUE;
      answer = 0;
      for (int i = 0; i < start.size(); i++) {
        visited = new boolean[100][100];
        int[] curr = start.get(i);
        visited[curr[0]][curr[1]] = true;
        go(curr[0], curr[1], 0);
      }
      
      System.out.println("#" + tc + " " + answer);
      // break;
    }
  }

  static void go(int x, int y, int cnt) {
    if (cnt > minCount) return; // 가지치기

    if (x == 0) {
      if (Integer.compare(minCount, cnt) >= 0) {
        minCount = cnt;
        answer = y;
      }
      return;
    }

    // 왼쪽
    if (y - 1 >= 0 && !visited[x][y - 1] && board[x][y - 1] == 1) {
      visited[x][y - 1] = true;
      go(x, y - 1, cnt + 1);
    }
    // 오른쪽
    else if (y + 1 < 100 && !visited[x][y + 1] && board[x][y + 1] == 1) {
      visited[x][y + 1] = true;
      go(x, y + 1, cnt + 1);
    }
    // 위쪽
    else {
      visited[x - 1][y] = true;
      go(x - 1, y, cnt + 1);
    }
  }
}
