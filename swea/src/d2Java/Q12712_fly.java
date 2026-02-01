package d2Java;

import java.io.*;
import java.util.*;

public class Q12712_fly {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		// + directions
		int[] dxPlus = {-1, 1, 0, 0};
		int[] dyPlus = {0, 0, -1, 1};
		
		// x directions
		int[] dxX = {-1, -1, 1, 1};
		int[] dyX = {-1, 1, -1, 1};
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] map = new int[N][N];
			
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					map[x][y] = sc.nextInt();
				}
			}
			
			int maxCount = 0;
			// 중심 옮겨가며 확인 
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					// +
					int sumPlus = map[x][y]; // 중심값으로 초기화
					for (int d = 0; d < 4; d++) { // 방향 
						for (int m = 1; m < M; m++) { // 범위 
							int nx = x + dxPlus[d] * m;
							int ny = y + dyPlus[d] * m;
							if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
							sumPlus += map[nx][ny];
						}
					}
					maxCount = Math.max(maxCount, sumPlus);
					
					// x
					int sumX = map[x][y]; // 중심값으로 초기화
					for (int d = 0; d < 4; d++) {
						for (int m = 1; m < M; m++) {
							int nx = x + dxX[d] * m;
							int ny = y + dyX[d] * m;
							if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
							sumX += map[nx][ny];
						}
					}
					maxCount = Math.max(maxCount, sumX);
				}
			}
			
			System.out.println("#" + tc + " " + maxCount);
		}

	}

}
