package unrank;

import java.io.*;
import java.util.*;

public class Q4014_활주로건설 {
  static int N, X, maxCnt;
  static int[][] map, mapTrans;

  public static void main(String[] args) throws Exception {
    // System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      X = Integer.parseInt(st.nextToken());

      map = new int[N][N];
      mapTrans = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          int h = Integer.parseInt(st.nextToken());
          map[i][j] = h;
          mapTrans[j][i] = h; // 세로 검사를 위해 행과 열을 바꾼 배열 생성
        }
      }

      maxCnt = 0;
      for (int i = 0; i < N; i++) {
        if (canBuild(map[i])) maxCnt++;      // 가로 한 줄 체크
        if (canBuild(mapTrans[i])) maxCnt++; // 세로 한 줄 체크
      }

      System.out.println("#" + tc + " " + maxCnt);
    }
  }

  // 한 줄(road)이 활주로 건설이 가능한지 체크
  static boolean canBuild(int[] road) {
    boolean[] occupied = new boolean[N]; // 경사로가 이미 설치된 칸인지 체크

    for (int i = 0; i < N - 1; i++) {
      // 1. 높이가 같으면 통과
      if (road[i] == road[i + 1]) continue;

      // 2. 높이 차이가 1보다 크면 절대 건설 불가
      if (Math.abs(road[i] - road[i + 1]) > 1) return false;

      // 3. 오르막길 설치 조건 (현재 < 다음)
      if (road[i] + 1 == road[i + 1]) {
        // 현재 칸(i)을 포함하여 뒤로 X만큼 같은 높이가 이어져야 함
        for (int j = i; j > i - X; j--) {
          if (j < 0 || road[j] != road[i] || occupied[j]) return false;
          occupied[j] = true; // 경사로 설치 표시
        }
      } 
      // 4. 내리막길 설치 조건 (현재 > 다음)
      else if (road[i] - 1 == road[i + 1]) {
        // 다음 칸(i+1)을 포함하여 앞으로 X만큼 같은 높이가 이어져야 함
        for (int j = i + 1; j <= i + X; j++) {
          if (j >= N || road[j] != road[i + 1] || occupied[j]) return false;
          occupied[j] = true; // 경사로 설치 표시
        }
      }
    }

    return true;
  }
}