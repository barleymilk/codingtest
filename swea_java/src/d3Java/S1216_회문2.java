package d3Java;

import java.io.*;
import java.util.*;

public class S1216_회문2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; tc++) {
			String T = br.readLine();
			char[][] board = new char[100][100];
			for (int i = 0; i < 100; i++) {
				board[i] = br.readLine().toCharArray();
			}
						
			// 가로, 세로를 모두 보아 가장 긴 회문의 길이
			// 길이 100부터 1까지 가기
			// 가로, 길이 회문 확인 함수
			int maxLen = 0;
			for (int len = 100; len > 0; len--) {
				if (solve(len, board)) {
					maxLen = len;
					break;
				}
			}
			
			System.out.println("#" + T + " " + maxLen);
		}
	}
	
	static boolean solve(int len, char[][] board) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j <= 100 - len; j++) {
				if(isPalindromeRow(i, j, len, board)) return true;
				if(isPalindromeCol(j, i, len, board)) return true;
			}
		}
		return false;
	}
	
	static boolean isPalindromeRow(int r, int c, int len, char[][] board) {
		for (int i = 0; i < len / 2; i++) {
			if (board[r][c + i] != board[r][c + len - i - 1]) return false;
		}
		return true;
	}
	
	static boolean isPalindromeCol(int r, int c, int len, char[][] board) {
		for (int i = 0; i < len / 2; i++) {
			if (board[r + i][c] != board[r + len - i - 1][c]) return false;
		}
		return true;
	}
}
