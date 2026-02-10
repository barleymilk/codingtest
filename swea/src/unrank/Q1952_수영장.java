package unrank;

import java.io.*;
import java.util.*;

public class Q1952_수영장 {
  static int[] prices, plans;
  static int minCost;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      // 1일, 1달, 3달, 1년 이용권 요금 입력받기
      prices = new int[4];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 4; i++) {
        prices[i] = Integer.parseInt(st.nextToken());
      }
      
      // 12개월 이용 계획 입력받기
      plans = new int[12];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 12; i++) {
        plans[i] = Integer.parseInt(st.nextToken());
      }

      // 1년 이용권 가격으로 최솟값 초기화(나올 수 있는 최대 비용)
      minCost = prices[3];

      // 0월부터 0원의 비용으로 시작
      dfs(0, 0);

      System.out.println("#" + tc + " " + minCost);
    }
  }

  static void dfs(int month, int currentSum) {
    // 현재 계산 중인 비용이 최솟값보다 크면 가지치기 해버린다!
    if (currentSum >= minCost) return; 

    // 12개월 탐색 끝 -> 최솟값 경신
    if (month >= 12) {
      minCost = Math.min(minCost, currentSum);
      return;
    }

    // 해당 달에 이용 계획이 없다면 비용 추가 없이 다음 달로 이동
    if (plans[month] == 0) {
      dfs(month + 1, currentSum);
    }
    else {
      // 1. 1일권 사용
      dfs(month + 1, currentSum + (plans[month] * prices[0]));

      // 2. 1달권 사용
      dfs(month + 1, currentSum + prices[1]);

      // 3. 3달권 사용(현재 월부터 3개월 스킵)
      dfs(month + 3, currentSum + prices[2]);
    }
  }
}
