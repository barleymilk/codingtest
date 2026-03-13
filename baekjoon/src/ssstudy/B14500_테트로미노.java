package ssstudy;

import java.io.*;
import java.util.*;

public class B14500_테트로미노 {
  static int N, M, maxResult;
  static int[][] board;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input_b14500.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine().trim());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine().trim());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // 테트로미노 종류
    // ㅡ : 2개
    // ㅁ : 1개
    // ㄴ : 8개
    // 번개 : 4개
    // ㅏ : 4개
    maxResult = 0;
    solve(new boolean[][]{{true,true,true,true}}, new int[]{1, 4});
    solve(new boolean[][]{{true},{true},{true},{true}}, new int[]{4, 1});

    solve(new boolean[][]{{true,true},{true,true}}, new int[]{2, 2});

    solve(new boolean[][]{{true,false}, {true,false}, {true,true}}, new int[]{3, 2});
    solve(new boolean[][]{{true,true,true},{true,false,false}}, new int[]{2, 3});
    solve(new boolean[][]{{true,true},{false,true},{false,true}}, new int[]{3, 2});
    solve(new boolean[][]{{false,false,true},{true,true,true}}, new int[]{2, 3});
    solve(new boolean[][]{{false,true}, {false,true}, {true,true}}, new int[]{3, 2});
    solve(new boolean[][]{{true,true}, {true,false}, {true,false}}, new int[]{3, 2});
    solve(new boolean[][]{{true,false,false}, {true,true,true}}, new int[]{2, 3});
    solve(new boolean[][]{{true,true,true}, {false,false,true}}, new int[]{2, 3});
    
    solve(new boolean[][]{{true,false},{true,true},{false,true}}, new int[]{3, 2});
    solve(new boolean[][]{{false,true,true},{true,true,false}}, new int[]{2, 3});
    solve(new boolean[][]{{true,true,false},{false,true,true}}, new int[]{2, 3});
    solve(new boolean[][]{{false,true},{true,true},{true,false}}, new int[]{3, 2});

    solve(new boolean[][]{{true,true,true},{false,true,false}}, new int[]{2, 3});
    solve(new boolean[][]{{false,true},{true,true},{false,true}}, new int[]{3, 2});
    solve(new boolean[][]{{false,true,false},{true,true,true}}, new int[]{2, 3});
    solve(new boolean[][]{{true,false},{true,true},{true,false}}, new int[]{3, 2});

    System.out.println(maxResult);
  }

  static void solve(boolean[][] tetromino, int[] tetrominoSize) {
    int teR = tetrominoSize[0];
    int teC = tetrominoSize[1];
    int rRange = N - teR + 1;
    int cRange = M - teC + 1;

    for (int r = 0; r < rRange; r++) {
      for (int c = 0; c < cRange; c++) {
        int result = 0;

        for (int i = 0; i < teR; i++) {
          for (int j = 0; j < teC; j++) {
            if (tetromino[i][j]) {
              result += board[r+i][c+j];
            }
          }
        }

        maxResult = Math.max(maxResult, result);
      }
    }
  }
}
