package d2Java;

import java.util.*;
import java.io.*;

public class Q1961_numArr_rotate {
	
	public static int[][] rotate90(int[][] arr) {
		int N = arr.length;
		int[][] result = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				result[c][N - 1 - r] = arr[r][c];
			}
		}
		return result;
	}
	
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					arr[r][c] = sc.nextInt();
				}
			}
			
			int[][] r90 = rotate90(arr);
			int[][] r180 = rotate90(r90);
			int[][] r270 = rotate90(r180);

			System.out.println("#" + tc);
			for (int r = 0; r < N; r++) {
				StringBuilder sb = new StringBuilder();
				
				for (int c = 0; c < N; c++) sb.append(r90[r][c]);
				sb.append(" ");
				for (int c = 0; c < N; c++) sb.append(r180[r][c]);
				sb.append(" ");
				for (int c = 0; c < N; c++) sb.append(r270[r][c]);
				
				System.out.println(sb);
			}
		}
		sc.close();
	}

}
