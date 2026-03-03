package d3Java;

import java.io.*;
import java.util.*;

public class Q1240_단순2진암호코드 {
  static int N, M;
  static int[][] binaryMap;

  public static void main(String[] args) throws Exception{
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      binaryMap = new int[N][M];
      for (int i = 0; i < N; i++) {
        String line = br.readLine();
        for (int j = 0; j < M; j++) {
          int temp = line.charAt(j) - '0';
          binaryMap[i][j] = temp;
        }
      }

      long totalSum = solve();

      System.out.println("#" + tc + " " + totalSum);
    }
  }

  static int solve() {
    for (int i = 0; i < N; i++) {
      for (int j = M - 1; j >= 55; j--) { // 최소 56칸은 있어야 하므로 j >= 55
        if (binaryMap[i][j] == 1) {
          int startY = j - 55;
          int[] code = new int[8];
          boolean isValidPattern = true;

          for (int k = 0; k < 8; k++) {
            int currentPos = startY + (k * 7);
            code[k] = parseDigit(i, currentPos);
            if (code[k] == -1) { // 매핑되지 않는 이상한 패턴이면 실패
                isValidPattern = false;
                break;
            }
          }

          if (isValidPattern) {
            int oddSum = code[0] + code[2] + code[4] + code[6];
            int evenSum = code[1] + code[3] + code[5];
            int checkDigit = code[7];

            if ((oddSum * 3 + evenSum + checkDigit) % 10 == 0) {
              int sum = 0;
              for (int val : code) sum += val;
              return sum; // 정상 암호 찾으면 즉시 종료
            }
          }

          j = startY; // 현재 암호 구간은 패스
        }
      }
    }
    return 0;
  }

  static int parseDigit(int r, int c) {
		int w2 = 0, w3 = 0, w4 = 0;
    int cur = c + 6;

    while (binaryMap[r][cur] == 1) { w4++; cur--; }
    while (binaryMap[r][cur] == 0) { w3++; cur--; }
    while (binaryMap[r][cur] == 1) { w2++; cur--; }

    return getDigit(w2, w3, w4);
	}

  static int getDigit(int w2, int w3, int w4) {
		if (w2 == 2 && w3 == 1 && w4 == 1) return 0;
		if (w2 == 2 && w3 == 2 && w4 == 1) return 1;
		if (w2 == 1 && w3 == 2 && w4 == 2) return 2;
		if (w2 == 4 && w3 == 1 && w4 == 1) return 3;
		if (w2 == 1 && w3 == 3 && w4 == 2) return 4;
		if (w2 == 2 && w3 == 3 && w4 == 1) return 5;
		if (w2 == 1 && w3 == 1 && w4 == 4) return 6;
		if (w2 == 3 && w3 == 1 && w4 == 2) return 7;
		if (w2 == 2 && w3 == 1 && w4 == 3) return 8;
		if (w2 == 1 && w3 == 1 && w4 == 2) return 9;
		return -1;
	}
}
