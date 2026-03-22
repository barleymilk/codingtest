import java.io.*;
import java.util.*;

public class B17144_미세먼지안녕 {
  static int R, C, T;
  static int[][] board;
  static int[] dr = {-1, 1, 0, 0};
  static int[] dc = {0, 0, -1, 1};
  static int airUpper = 2, airLower = 3; // 공기청정기 위, 아래 행 위치

  public static void main(String[] args) throws Exception {
      System.setIn(new FileInputStream("res/b17144.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      T = Integer.parseInt(st.nextToken());
      board = new int[R][C];

      for (int r = 0; r < R; r++) {
        st = new StringTokenizer(br.readLine());
        for (int c = 0; c < C; c++) {
          board[r][c] = Integer.parseInt(st.nextToken());
        }
      }

      for (int time = 0; time < T; time++) {
        spread();
        operatePurifier();
      }

      System.out.println(getTotalDust());
  }

  static void spread() {
    int[][] addBoard = new int[R][C]; // 확산되는 양만 기록

    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (board[r][c] > 0) {
          int dust = board[r][c] / 5;
          int cnt = 0;
          for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nc >= 0 && nr < R && nc < C && board[nr][nc] != -1) {
              addBoard[nr][nc] += dust;
              cnt++;
            }
          }
          board[r][c] -= dust * cnt;
        }
      }
    }

    // 확산된 양 합치기
    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
            board[r][c] += addBoard[r][c];
        }
    }
  }

  static void operatePurifier() {
    // 1. 위쪽 공기청정기 (반시계: 위 -> 왼 -> 아래 -> 오 순서로 당기기)
    // 아래로 당기기
    for (int r = airUpper - 1; r > 0; r--) board[r][0] = board[r - 1][0];
    // 오른쪽에서 왼쪽으로 당기기
    for (int c = 0; c < C - 1; c++) board[0][c] = board[0][c + 1];
    // 위로 당기기
    for (int r = 0; r < airUpper; r++) board[r][C - 1] = board[r + 1][C - 1];
    // 왼쪽에서 오른쪽으로 당기기
    for (int c = C - 1; c > 1; c--) board[airUpper][c] = board[airUpper][c - 1];
    board[airUpper][1] = 0; // 청정기에서 나온 깨끗한 공기

    // 2. 아래쪽 공기청정기 (시계: 아래 -> 왼 -> 위 -> 오 순서로 당기기)
    // 위로 당기기
    for (int r = airLower + 1; r < R - 1; r++) board[r][0] = board[r + 1][0];
    // 오른쪽에서 왼쪽으로 당기기
    for (int c = 0; c < C - 1; c++) board[R - 1][c] = board[R - 1][c + 1];
    // 아래로 당기기
    for (int r = R - 1; r > airLower; r--) board[r][C - 1] = board[r - 1][C - 1];
    // 왼쪽에서 오른쪽으로 당기기
    for (int c = C - 1; c > 1; c--) board[airLower][c] = board[airLower][c - 1];
    board[airLower][1] = 0; // 청정기에서 나온 깨끗한 공기
  }

  static int getTotalDust() {
    int sum = 0;
    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (board[r][c] > 0) sum += board[r][c];
      }
    }
    return sum;
  }
}