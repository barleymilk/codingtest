package sswork.imclassproblem;

import java.io.FileInputStream;
import java.util.*;

public class Q2477_참외밭 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    int K = sc.nextInt(); // 1m2 참외의 개수
    
    // 1: 동쪽, 2: 서쪽, 3: 남쪽, 4: 북쪽
    int[][] path = new int[6][2];
    for (int i = 0; i < 6; i++) {
      int dir = sc.nextInt();
      int len = sc.nextInt();
      path[i][0] = dir;
      path[i][1] = len;
    }

    // 가장 긴 가로, 세로 찾기
    int maxW = 0, maxH = 0;
    for (int[] p : path) {
      if (p[0] == 1 || p[0] == 2) maxW = Math.max(maxW, p[1]);
      else maxH = Math.max(maxH, p[1]);
    }

    int minW = 0;
    int minH = 0;
    // 작은 사각형 찾기
    for (int i = 0; i < 6; i++) {
      // 이전 변과 이후 변을 더했을 때 최대 세로 길이 혹은 최대 가로 길이와 같아야 함.
      int prev;
      int next;
      if (i == 0) prev = path[5][1];
      else prev = path[i-1][1];
      if (i == 5) next = path[0][1];
      else next = path[i+1][1];

      if (path[i][0] == 1 || path[i][0] == 2) {
        // 가로변인 경우
        if (prev + next == maxH) minW = path[i][1];
      }
      else {
        // 세로변인 경우
        if (prev + next == maxW) minH = path[i][1];
      }
    }

    int answer = ((maxH * maxW) - (minH * minW)) * K;
    System.out.println(answer);

    sc.close();
  }
}
