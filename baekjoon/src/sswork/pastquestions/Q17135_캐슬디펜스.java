package sswork.pastquestions;

import java.io.*;
import java.util.*;

public class Q17135_캐슬디펜스 {
  static int N, M, D, totalKill;
  static int[][] board;
  static ArrayList<int[]> originalEnemies = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());

    board = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
        if (board[i][j] == 1) originalEnemies.add(new int[]{i, j});
      }
    }

    totalKill = 0;
    locateArchers(0, 0, new int[3]);
    System.out.println(totalKill);
  }

  static void locateArchers(int start, int cnt, int[] archers) {
    if (cnt == 3) { // m개 자리 중 3개를 뽑음(조합)
      startGame(archers);
      return;
    }

    for (int i = start; i < M; i++) {
      archers[cnt] = i;
      locateArchers(i + 1, cnt + 1, archers);
    }
  }

  static void startGame(int[] archers) {
    int kill = 0;

    ArrayList<int[]> enemies = new ArrayList<>();
    for (int[] e : originalEnemies) {
      enemies.add(new int[]{e[0], e[1]});
    }

    while(!enemies.isEmpty()) {
      // 해당 스테이지에 제거될 적들
      Set<int[]> targets = new HashSet<>();

      // 각 궁수의 타겟 정하기
      for (int archer : archers) {
        int minDist = M + 1;
        int[] target = null;

        // 모든 적에 대해서 거리 계산
        for (int[] enemy : enemies) {
          int dist = Math.abs(N - enemy[0]) + Math.abs(archer - enemy[1]);
          if (dist <= D) { // 공격 가능한 거리에 적이 있는 경우
            // 더 가까운 적 혹은 거리가 같은 적 중에서 가장 왼쪽에 있는 적 선택
            if (dist < minDist || (target != null && dist == minDist && enemy[1] < target[1])) {
              minDist = dist;
              target = enemy;
            }
          }
        }
        
        if (target != null) {
          targets.add(target);
        }
      }

      // 적 죽이기
      for (int[] t : targets) {
        for (int i = 0; i < enemies.size(); i++) {
          if (enemies.get(i) == t) {
            enemies.remove(i);
            kill++;
            break;
          }
        }
      }

      // 적 -> 한 칸 아래 이동
      ArrayList<int[]> nextEnemies = new ArrayList<>();
      for (int[] e : enemies) {
        if (e[0] + 1 < N) {
          nextEnemies.add(new int[]{e[0] + 1, e[1]});
        }
      }
      enemies = nextEnemies;
    }
    
    totalKill = Math.max(totalKill, kill);
  }
}
