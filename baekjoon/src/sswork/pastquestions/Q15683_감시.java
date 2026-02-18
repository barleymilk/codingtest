package sswork.pastquestions;

import java.io.*;
import java.util.*;

public class Q15683_감시 {
  static int N, M, minResult;
  static int[][] originalBoard;
  static ArrayList<int[]> CCTV = new ArrayList<>();
  static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; // U, R, D, L
  
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    originalBoard = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        originalBoard[i][j] = Integer.parseInt(st.nextToken());
        if (originalBoard[i][j] != 0 && originalBoard[i][j] != 6) {
          CCTV.add(new int[]{originalBoard[i][j], i, j});
        }
      }
    }

    minResult = N * M; // 최댓값으로 초기화
    dfs(0, originalBoard);

    System.out.println(minResult);
  }

  static void dfs(int cctvIdx, int[][] board) {
    if (cctvIdx == CCTV.size()) {
      minResult = Math.min(minResult, countZero(board));
      return;
    }

    int[] curr = CCTV.get(cctvIdx);
    int type = curr[0];
    int r = curr[1];
    int c = curr[2];

    for (int d = 0; d < 4; d++) {
      int[][] nextBoard = copyBoard(board);

      switch (type) {
        case 1: {
          watch(nextBoard, r, c, d); 
          break;
        }
        case 2: {
          watch (nextBoard, r, c, d);
          watch (nextBoard, r, c, d + 2);
          break;
        }
        case 3: {
          watch (nextBoard, r, c, d);
          watch (nextBoard, r, c, d + 1);
          break;
        }
        case 4: {
          watch (nextBoard, r, c, d);
          watch (nextBoard, r, c, d + 1);
          watch (nextBoard, r, c, d + 2);
          break;
        }
        case 5: {
          watch (nextBoard, r, c, 0);
          watch (nextBoard, r, c, 1);
          watch (nextBoard, r, c, 2);
          watch (nextBoard, r, c, 3);
          break;
        }
      }
      dfs(cctvIdx + 1, nextBoard);
    }
  }

  static void watch(int[][] board, int r, int c, int d) {
    d %= 4;
    int nr = r, nc = c;
    
    while(true) {
      nr += dr[d];
      nc += dc[d];

      // 영역을 벗어나거나 벽을 만나면 중지
      if (nr < 0 || nc < 0 || nr >= N || nc >= M || board[nr][nc] == 6) break;
      
      // 감시 영역 칠하기
      if (board[nr][nc] == 0) board[nr][nc] = 9;
    }
  }

  static int[][] copyBoard(int[][] board) {
    int[][] newBoard = new int[N][M];
    for (int i = 0; i < N; i++) newBoard[i] = board[i].clone();
    return newBoard;
  }

  static int countZero(int[][] board) {
    int count = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (board[i][j] == 0) count++;
      }
    }
    return count;
  }

}
