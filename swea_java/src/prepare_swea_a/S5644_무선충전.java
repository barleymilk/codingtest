package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S5644_무선충전 {
	static int M, A;
	static int[] moveA, moveB;
	static List<BC> bcList;
	static int[] dx = {0,0,1,0,-1}, dy = {0,-1,0,1,0}; // 제자리, 상, 우, 하, 좌 
	
	public static class BC {
		int x, y, c, p;
		
		public BC (int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s5644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			moveA = new int[M];
			moveB = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			bcList = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				bcList.add(new BC(x, y, c, p));
			}
			
			int[] nowA = new int[] {1,1};
			int[] nowB = new int[] {10,10};
			int totalSum = 0;
			// 시간의 흐름
			for (int time = 0; time <= M; time++) {
				// 현재 위치에서 최대 충전량 합산 
				totalSum += getCharge(nowA[0], nowA[1], nowB[0], nowB[1]);
				if (time == M) break;
				
				// 다음으로 이동 (M-1까지)
				int dA = moveA[time];
				int dB = moveB[time];
				
				nowA[0] += dx[dA];
				nowA[1] += dy[dA];
				nowB[0] += dx[dB];
				nowB[1] += dy[dB];
			}
			
			System.out.println("#" + tc + " " + totalSum);
		}
	}
	
	static int getCharge(int x1, int y1, int x2, int y2) {
		int max = 0;
		
		for (int i = 0; i < A; i++) {
			for (int j = 0; j < A; j++) {
				int sum = 0;
				int chargeA = check(i, x1, y1);
				int chargeB = check(j, x2, y2);
				
				if (i == j) {
					sum = Math.max(chargeA,  chargeB);
				} else {
					sum = chargeA + chargeB;
				}
				max = Math.max(max,  sum);
			}
		}
		return max;
	}
	
	public static int check(int bcIdx, int ux, int uy) {
		BC bc = bcList.get(bcIdx);
		if (Math.abs(bc.x - ux) + Math.abs(bc.y - uy) <= bc.c) return bc.p;
		return 0;
	}
}
