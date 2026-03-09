package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S2117_홈방범서비스 {
	static int N, M, K;
	static int maxHome;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s2117.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// K(방범 범위) 초기값은 최댓값으로 설정
			if (N % 2 == 0) {
				K = N * 2 + 1;
			}
			else {
				K = N * 2 - 1;
			}
			
			maxHome = 0;
			for (int k = K; k >= 1; k--) {
				// 손해만 안 보면 집 수 세기
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						// 중심점 (i, j)
						int cnt = countHome(k, i, j); // 중심점을 기준으로 범위에 해당하는 집 수 세기
						int profit = calcProfit(k, cnt); // 얻는 이익
						if (profit >= 0) {
							maxHome = Math.max(maxHome, cnt);
						}
						
					}
				}
				if (maxHome != 0) break;
			}
			
			System.out.println("#" + tc + " " + maxHome);
		}
	}
	
	public static int countHome(int k, int x, int y) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {				
				if (manhatton(x, y, i, j) < k && map[i][j] == 1) cnt++;
			}
		}
		return cnt;
	}
	
	public static int manhatton(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	public static int calcCost(int k) {
		return k * k + (k-1) * (k-1);
	}
	
	public static int calcProfit(int k, int cnt) {
		int cost = calcCost(k);
		return M * cnt - cost;
	}
}
