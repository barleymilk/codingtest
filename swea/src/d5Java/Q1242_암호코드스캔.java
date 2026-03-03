package d5Java;

import java.io.*;
import java.util.*;

public class Q1242_암호코드스캔 {
	static String[] hexToBin = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"}; // 16진수(0~15) --( hexToBin[0-15] )--> 2진수
	static int[][] binaryMap;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1242.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 16진수 -> 2진수 비트 4개와 똑같은 정보를 담음 => M*4
			binaryMap = new int[N][M*4];
			
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					char hex = line.charAt(j);
					// String.valueOf() -> 무엇이든 String 타입으로 변환
					int hexVal = Integer.parseInt(String.valueOf(hex), 16); // 16진수 문자열 -> 10진수 정수
					String bin = hexToBin[hexVal];
					
					for (int k = 0; k < 4; k++) {
						binaryMap[i][j * 4 + k] = bin.charAt(k) - '0';
					}
				}
			}
			
			long totalSum = solve();
			System.out.println("#" + tc + " " + totalSum);
		}
	}
	
	static long solve() {
		long totalSum = 0;
		Set<String> processedCodes = new HashSet<>();
			
		for (int i = 0; i < N; i++) {
			for (int j = M * 4 - 1; j >= 0; j--) {
				// 암호의 끝은 항상 1
				if (binaryMap[i][j] == 1) {
					int[] code = new int[8];
									
					// 8자리 숫자를 뒤에서부터 추출
					for (int idx = 7; idx >= 0; idx--) { 
						int w2 = 0, w3 = 0, w4 = 0;
											
						while (j >= 0 && binaryMap[i][j] == 1) { w4++; j--; }
						while (j >= 0 && binaryMap[i][j] == 0) { w3++; j--; }
						while (j >= 0 && binaryMap[i][j] == 1) { w2++; j--; }
						
						int K = Math.min(Math.min(w2, w3), w4);
						
						code[idx] = getDigit(w2 / K, w3 / K, w4 / K);
						
						// 숫자 사이의 공백(w1) 건너뛰기
						if (idx > 0) {
							while (j >= 0 && binaryMap[i][j] == 0) { j--; }
						}
					}
									
					String codeStr = Arrays.toString(code);
									
					if (!processedCodes.contains(codeStr)) {
						processedCodes.add(codeStr);
									
						// 1-based 인덱스 기준으로 홀수/짝수 위치 계산
						// code[0]이 1번째(홀수), code[1]이 2번째(짝수)...
						int oddSum = code[0] + code[2] + code[4] + code[6];
						int evenSum = code[1] + code[3] + code[5];
						int checkDigit = code[7];
										
						// 공식: (홀수합 * 3) + 짝수합 + 검증코드
						if ((oddSum * 3 + evenSum + checkDigit) % 10 == 0) {
							for (int val : code) totalSum += val;
						}
					}
									
					j++; // j를 다시 현재 위치로 보정 (for문의 j--와 만나서 제자리 유지)
				}
			}
		}
		return totalSum;
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