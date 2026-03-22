package unrank;

import java.io.*;
import java.util.*;

public class S2383_점심식사시간 {
  static int N, peopleCnt, minResult;
  static List<Point> peoplePos;
  static List<Stair> stairs;
  static int[] selectedStair; // 각 사람이 선택한 계단 인덱스 (0 또는 1)

  static class Point {
    int r, c;

    Point(int r, int c) { this.r = r; this.c = c; }
  }

  static class Stair {
    int r, c, k;

    Stair(int r, int c, int k) {
      this.r = r;
      this.c = c;
      this.k = k;
    }
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/s2383.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine().trim());

    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine().trim());
      peoplePos = new ArrayList<>();
      stairs = new ArrayList<>();
      
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          int val = Integer.parseInt(st.nextToken());
          if (val == 1) { // 사람
            peoplePos.add(new Point(i, j));
          } else if (val > 1) { // 계단
            stairs.add(new Stair(i, j, val));
          }
        }
      }

      peopleCnt = peoplePos.size();
      selectedStair = new int[peopleCnt];
      minResult = Integer.MAX_VALUE;

      dfs(0);

      System.out.println("#" + tc + " " + minResult);
    }
  }

  static void dfs(int depth) {
    if (depth == peopleCnt) {
      minResult = Math.min(minResult, simulation());
      return;
    }

    // 분기 1) 0번 계단 선택
    selectedStair[depth] = 0;
    dfs(depth + 1);

    // 분기 2) 1번 계단 선택
    selectedStair[depth] = 1;
    dfs(depth + 1);
  }

  static int simulation() {
    int maxTime = 0; // 계단 0, 1 중 더 늦게 끝난 시간 갱신

    for (int s = 0; s < 2; s++) { // 계단 0, 1
      List<Integer> arrivalTimes = new ArrayList<>(); // 해당 계단 입구까지의 거리 저장

      for (int i = 0; i < peopleCnt; i++) {
        if (selectedStair[i] == s) {
          // 계단 입구까지의 거리
          int dist = Math.abs(peoplePos.get(i).r - stairs.get(s).r) + Math.abs(peoplePos.get(i).c - stairs.get(s).c);
          arrivalTimes.add(dist);
        }
      }

      // 계단을 선택한 사람이 없는 경우 통과
      if (arrivalTimes.isEmpty()) continue;

      // 빨리 도착하는 순서대로 정렬(거리만 보면 되기 때문에 누가 누구인지는 상관 없음)
      Collections.sort(arrivalTimes);

      // 해당 계단을 빠져나오는 시간 계산
      int[] exitTime = new int[arrivalTimes.size()];
      int k = stairs.get(s).k;

      for (int i = 0; i < arrivalTimes.size(); i++) {
        int arrive = arrivalTimes.get(i);
        
        if (i < 3) {
          // 처음 3명 : 도착 시간 + 1분 대기 + 계단 길이
          exitTime[i] = arrive + 1 + k;
        } else {
          // 도착 후 1분 대기 vs 내 앞 3번째 사람 탈출 시간 중 늦은 시간
          int startTime = Math.max(arrive + 1, exitTime[i - 3]); 
          exitTime[i] = startTime + k; // + 계단 길이
        }
      }
      
      // 0, 1 두 계단 중 더 오래 걸린 시간 -> maxTime 갱신
      maxTime = Math.max(maxTime, exitTime[exitTime.length - 1]);
    }
    
    return maxTime;
  }
}
