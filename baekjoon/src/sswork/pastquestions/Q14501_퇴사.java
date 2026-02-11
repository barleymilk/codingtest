package sswork.pastquestions;

import java.io.*;
import java.util.*;

public class Q14501_퇴사 {
  static int N;
  static int[][] schedule;
  static int maxPrice;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    // 초기화
    N = Integer.parseInt(br.readLine());
    schedule = new int[N][2];

    // 스케줄 입력 받아오기
    StringTokenizer st;
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      schedule[i][0] = Integer.parseInt(st.nextToken());
      schedule[i][1] = Integer.parseInt(st.nextToken());
    }

    // 상담 분기 시작
    maxPrice = 0;
    dfs(0, 0);

    System.out.println(maxPrice);
  }

  static void dfs(int depth, int currentPrice) {
    if (depth == N) {
      maxPrice = Math.max(maxPrice, currentPrice);
      return;
    }
    if (depth > N) {
      return;
    }

    // 분기 1) 상담을 함
    int time = schedule[depth][0];
    int price = schedule[depth][1];
    if (depth + time <= N) { // 기간 내 상담이 가능한 경우 
      dfs(depth + time, currentPrice + price);
    }

    // 분기 2) 상담을 하지 않음 
    dfs(depth + 1, currentPrice);
  }
}
