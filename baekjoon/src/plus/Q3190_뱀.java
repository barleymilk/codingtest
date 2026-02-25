package plus;

import java.io.*;
import java.util.*;

public class Q3190_뱀 {
  static int N, K, L;
  static int[][] board; // 0: 빈칸, 1: 사과, 2: 뱀의 몸
  static Map<Integer, String> rotateMap = new HashMap<>();
  static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상 (시계방향)
  static int[] dy = {1, 0, -1, 0};

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    board = new int[N][N];

    K = Integer.parseInt(br.readLine());
    for (int i = 0; i < K; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      board[r][c] = 1; // 사과 표시
    }

    L = Integer.parseInt(br.readLine());
    for (int i = 0; i < L; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken());
      String d = st.nextToken();
      rotateMap.put(t, d); // 시간별 방향 전환 정보 저장
    }

    System.out.println(solve());
  }

  static int solve() {
    Deque<int[]> snake = new ArrayDeque<>();
    snake.addFirst(new int[]{0, 0});
    board[0][0] = 2; // 시작지점 뱀 표시

    int time = 0;
    int d = 0; // 초기 방향: 오른쪽(0)
    int x = 0, y = 0;

    while (true) {
      time++;

      // 1. 머리 다음 칸 이동
      int nx = x + dx[d];
      int ny = y + dy[d];

      // 2. 종료 조건: 벽이나 몸에 부딪힘
      if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 2) {
          return time;
      }

      // 3. 사과 여부에 따른 꼬리 처리
      if (board[nx][ny] != 1) { // 사과가 없으면
          int[] tail = snake.pollLast(); // 꼬리 제거
          board[tail[0]][tail[1]] = 0;
      }
      
      // 4. 머리 이동 완료
      board[nx][ny] = 2;
      snake.addFirst(new int[]{nx, ny});
      x = nx; 
      y = ny;

      // 5. 방향 전환 체크 (X초가 끝난 뒤)
      if (rotateMap.containsKey(time)) {
          if (rotateMap.get(time).equals("L")) d = (d + 3) % 4;
          else d = (d + 1) % 4;
      }
    }
  }
}