package unrank;

import java.io.*;
import java.util.*;

public class Q4008_숫자만들기_1 {
  static int N, minN, maxN;
  static int[] nums, ops;
  static int[] perm;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      N = Integer.parseInt(br.readLine());

      // 각 연산자 개수 입력 받아오기 ('+', '-', '*', '/') 
      ops = new int[4];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < 4; i++) {
        ops[i] = Integer.parseInt(st.nextToken());
      }

      // 숫자 입력 받아오기 
      st = new StringTokenizer(br.readLine());
      nums = new int[N];
      for (int i = 0; i < N; i++) {
        nums[i] = Integer.parseInt(st.nextToken());
      }

      minN = Integer.MAX_VALUE;
      maxN = Integer.MIN_VALUE;

      perm = new int[N - 1];
      dfs(0);

      System.out.println("#" + tc + " " + (maxN - minN));
    }
  }

  static void dfs(int depth) {
    if (depth == N - 1) {
      int op0 = 0, op1 = 0, op2 = 0, op3 = 0;

      for (int i = 0; i < N - 1; i++) {
        int op = perm[i];
        if (op == 0) op0++;
        else if (op == 1) op1++;
        else if (op == 2) op2++;
        else op3++;
      }
      
      // 중복순열로 만든 연산자가 입력받은 각 연산자의 개수를 만족할 때에만 계산 
      if (ops[0] == op0 && ops[1] == op1 && ops[2] == op2 && ops[3] == op3) calc(perm);
      return;
    }

    for (int i = 0; i < 4; i++) {
      perm[depth] = i;
      dfs(depth + 1);
    }
  }

  static void calc(int[] opnums) {
    int answer = nums[0];
    
    for (int i = 0; i < N - 1; i++) {
      switch(opnums[i]) {
        case 0: answer += nums[i + 1]; break;
        case 1: answer -= nums[i + 1]; break;
        case 2: answer *= nums[i + 1]; break;
        case 3: answer /= nums[i + 1]; break;
      }
    }
    
    if (answer < minN) minN = answer;
    if (answer > maxN) maxN = answer;
  }
}
