package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S12712_파리퇴치3 {
	static int N, M, maxFlies;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s12712.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); // 스프레이 세기
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxFlies = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					spray(i, j);
				}
			}
			
			System.out.println("#" + tc + " " + maxFlies);
		}
	}
	
	static void spray(int x, int y) {
		int[] dx_plus = {-1,1,0,0}, dy_plus = {0,0,-1,1};
		int[] dx_mult = {-1,-1,1,1}, dy_mult = {-1,1,-1,1};
		
		int flies_plus = board[x][y];
		int flies_mult = board[x][y];
		for (int i = 0; i < 4; i++) {
			// 더하기 모양 살포
			for (int size = 1; size < M; size++) {
				int nx = x + dx_plus[i] * size;
				int ny = y + dy_plus[i] * size;
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				
				flies_plus += board[nx][ny];
			}
			// 곱하기 모양 살포
			for (int size = 1; size < M; size++) {
				int nx = x + dx_mult[i] * size;
				int ny = y + dy_mult[i] * size;
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				
				flies_mult += board[nx][ny];
			}
		}
		
		maxFlies = Math.max(maxFlies, Math.max(flies_plus, flies_mult));
		return;
	}
}
