package unrank;

import java.io.*;
import java.util.*;

public class S2112_보호필름 {
	static int D, W, K;
	static int minInject;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken()); // 최소 통과 기준
			
			int[][] board = new int[D][W];
			for (int i = 0 ; i < D; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			minInject = Integer.MAX_VALUE;
			// 부분집합
			dfs(0, 0, board);
			
			System.out.println("#" + tc + " " + minInject);
		}
	}
	
	static void dfs(int depth, int injectCnt, int[][] board) {
		if (depth == D) {
			// 합격 기준(K) 통과 -> 최소 투입 횟수 비교 및 갱신 
			if (check(board)) {
				minInject = Math.min(minInject, injectCnt);
			}
			return;
		}
		
		int[] temp = board[depth].clone(); // 원본 저장
		
		// 1-1) 약물 0 주입
		Arrays.fill(board[depth], 0);
		dfs(depth + 1, injectCnt + 1, board);

		// 1-2) 약물 1 주입
		Arrays.fill(board[depth], 1);
		dfs(depth + 1, injectCnt + 1, board);
		
		board[depth] = temp; // 백트래킹
		
		// 2) 약물 주입하지 않음
		dfs(depth + 1, injectCnt, board);
	}
	
	static boolean check(int[][] board) {
		if (K == 1) return true;
		
	    for (int c = 0; c < W; c++) {
	        boolean pass = false;
	        int count = 1; // 연속된 개수
	        
	        for (int r = 1; r < D; r++) {
	            if (board[r][c] == board[r-1][c]) {
	                count++;
	            } else {
	                count = 1;
	            }
	            
	            if (count >= K) {
	                pass = true;
	                break;
	            }
	        }
	        
	        if (!pass && K > 1) return false;
	    }
	    
	    return true;
	}
}
