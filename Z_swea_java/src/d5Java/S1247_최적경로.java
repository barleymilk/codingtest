package d5Java;

import java.io.*;
import java.util.*;

public class S1247_최적경로 {
	static int N, minDistance;
	static int[] companyPos, homePos;
	static int[][] customerPos;
	static boolean[] visited;
	static int[] perm;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1247.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			companyPos = new int[2];
			homePos = new int[2];
			customerPos = new int[N][2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			companyPos[0] = Integer.parseInt(st.nextToken());
			companyPos[1] = Integer.parseInt(st.nextToken());
			homePos[0] = Integer.parseInt(st.nextToken());
			homePos[1] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 2; j++) {
					customerPos[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// [ 회사 -> N명 고객의 순열 -> 집 ] 최소거리 구하기
			minDistance = Integer.MAX_VALUE;
			visited = new boolean[N];
			perm = new int[N];
			dfs(0, 0, companyPos[0], companyPos[1]);
			
			System.out.println("#" + tc + " " + minDistance);
		}
		
	}
	
	public static void dfs(int depth, int currDistanceSum, int currX, int currY) {
		if (currDistanceSum > minDistance) return;
		
		if (depth == N) {
			currDistanceSum += calcDistance(currX, currY, homePos[0], homePos[1]);
			minDistance = Math.min(minDistance, currDistanceSum);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				
				int nextX = customerPos[i][0];
				int nextY = customerPos[i][1];
				int distance = calcDistance(currX, currY, nextX, nextY); // 현재 위치 ~ 다음 위치 거리 계산
				
				dfs(depth + 1, currDistanceSum + distance, nextX, nextY);
				
				visited[i] = false;
			}
		}
	}
	
	public static int calcDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
