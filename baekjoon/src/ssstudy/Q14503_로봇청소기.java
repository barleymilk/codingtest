package ssstudy;

import java.io.*;
import java.util.*;

public class Q14503_로봇청소기 {
  static int N, M, r, c, d, cnt;
  static int[][] map;
  static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1}; // 북, 동, 남, 서

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    // 방 크기 N x M
    N = Integer.parseInt(st.nextToken()); 
    M = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine());
    // 처음 로봇청소기가 있는 칸 (r, c)
    // 로봇 청소기가 바라보는 방향 d : 0(북), 1(동), 2(남), 3(서)
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    cnt = 0;
    operateRobot(r, c);
    System.out.println(cnt);
  }

  static void operateRobot(int x, int y) {
    // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
    if (map[x][y] == 0) {
      map[x][y] = 2;
      cnt++;
    }

    // 주변 4칸에서 청소가 필요한 부분이 있는지 확인
    boolean flag = false;
    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
        if (map[nx][ny] == 0) {
          flag = true;
          break;
        }
      }
    }

    // 2. 현재 칸의 주변 4칸이 모두 청소가 필요 없는 경우
    // - 후진할 수 있다면 한 칸 후진하고 1번으로 돌아감
    // - 후진할 수 없으면 작동 멈춤
    if (!flag) {
      int backD = (d + 2) % 4; // 후진 방향
      int nx = x + dx[backD];
      int ny = y + dy[backD];

      if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 1) {
        operateRobot(nx, ny);
      }
      else {
        return;
      }
    }
    // 3. 현재 칸의 주변 4칸 중 청소가 필요한 곳이 있는 경우
    // - 반시계 방향으로 90도 회전
    // - 바라보는 방향 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
    // - 1번으로 돌아감.
    else {
      for (int i = 0; i < 4; i++) {
        d = d - 1 < 0 ? 3 : d - 1; // 반시계 방향 90도 회전
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
          operateRobot(nx, ny);
          return;
        }
      }
    }
  }
}
