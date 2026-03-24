package ssstudy;

import java.io.*;
import java.util.*;

public class B15685_드래곤커브 {
  static int[] dx = {1,0,-1,0}, dy = {0,-1,0,1}; // 우상좌하

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/b15685.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[][] board = new int[101][101];
    int N = Integer.parseInt(br.readLine().trim());
    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine().trim());
      // 1. 드래곤 커브 정보 받아오기
      // x, y : 드래곤 커브 시작점, d : 시작 방향, g : 세대
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());

      List<Integer> directions = new ArrayList<>();
      directions.add(d); // 0세대 방향

      // g세대만큼 방향 리스트 추가
      for (int j = 0; j < g; j++) {
        for (int k = directions.size() - 1; k >= 0; k--) {
          directions.add((directions.get(k) + 1) % 4);
        }
      }

      // 2. 보드에 칠하기
      board[x][y] = 1;
      for (int dir : directions) {
        x += dx[dir];
        y += dy[dir];
        board[x][y] = 1;
      }
    }

    // 3. 꼭짓점 네 개가 드래곤 커브에 속하는 정사각형 개수 세기
    int answer = 0;
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        if (
          board[i][j] == 1 && 
          board[i + 1][j] == 1 && 
          board[i][j + 1] == 1 && 
          board[i + 1][j + 1] == 1) {
            answer++;
          }
      }
    }
    System.out.println(answer);
  }
}
