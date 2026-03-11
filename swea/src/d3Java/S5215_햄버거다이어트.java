package d3Java;

import java.io.*;
import java.util.*;

public class S5215_햄버거다이어트 {
	static int N, L, maxScore;
	static int[][] table;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken()); // 재료 슈
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			
			table = new int[N][2]; // [맛, 칼로리]
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < 2; j++) {
					table[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxScore = 0;
			// 중복은 안 됨 -> subset
			makeBurger(0, 0, 0);
			
			System.out.println("#" + tc + " " + maxScore);
		}
	}
	
	static void makeBurger(int depth, int score, int calories) {
		// 칼로리(calories)가 제한 칼로리(L)을 넘기면 가지치기
		if (calories > L) return;
		
		if (depth == N) {
			// 최고의 맛 갱신
			maxScore = Math.max(maxScore, score);
			return;
		}
		
		// 1) 재료 사용 O
		makeBurger(depth + 1, score + table[depth][0], calories + table[depth][1]);
		// 2) 재료 사용 X
		makeBurger(depth + 1, score, calories);
	}
}
