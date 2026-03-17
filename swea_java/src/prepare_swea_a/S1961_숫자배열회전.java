package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S1961_숫자배열회전 {
	static int N;
	static int[][] originalBoard;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1961.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			originalBoard = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					originalBoard[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] r90 = rotate90(originalBoard);
			int[][] r180 = rotate90(r90);
			int[][] r270 = rotate90(r180);
			
			System.out.println("#" + tc);
			
			for (int i = 0; i < N; i++) {
				StringBuilder sb = new StringBuilder();
				
				for (int j = 0; j < N; j++) sb.append(r90[i][j]);
				sb.append(" ");
				for (int j = 0; j < N; j++) sb.append(r180[i][j]);
				sb.append(" ");
				for (int j = 0; j < N; j++) sb.append(r270[i][j]);

				System.out.println(sb);
			}
		}
	}
	
	static int[][] rotate90(int[][] board) {
		int[][] rotatedBoard = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rotatedBoard[j][N-i-1] = board[i][j];
			}
		}
		
		return rotatedBoard;
	}
}
