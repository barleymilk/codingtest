package plus;

import java.io.*;
import java.util.*;

public class Q1987_알파벳 {
  static int R, C, maxCnt;
  static char[][] board;
  static boolean[] alphabet = new boolean[26];
  static int[] dx = {0,0,-1,1}, dy = {-1,1,0,0};

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    
    board = new char[R][C];
    for (int i = 0; i < R; i++) {    
      board[i] = br.readLine().toCharArray();
    }

    maxCnt = 0;
    alphabet[board[0][0] - 'A'] = true;
    dfs(0, 0, 1);

    System.out.println(maxCnt);
  }
  
  static void dfs(int x, int y, int cnt) {
    // 매 순간 최댓값 갱신
    maxCnt = Math.max(maxCnt, cnt);

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
        int nextIdx = board[nx][ny] - 'A';

        if (!alphabet[nextIdx]) {
          alphabet[nextIdx] = true;
          dfs(nx, ny, cnt + 1);
          alphabet[nextIdx] = false;
        }
      }
    }
  }
}
