package ch01.sec02;

import java.io.*;
import java.util.*;

public class Q3085_사탕게임 {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		// https://www.acmicpc.net/problem/3085
		
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		char[][] board = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String row = sc.next();
			board[i] = row.toCharArray();
		}
		
		int maxCandy = 1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 오른쪽과 바꾸기
				if (c + 1 < N) {
					swap(board, r, c, r, c + 1);
					maxCandy = Math.max(countMax(board), maxCandy);
					swap(board, r, c, r, c + 1);
				}
				
				// 아래쪽과 바꾸기 
				if (r + 1 < N) {
					swap(board, r, c, r + 1, c);
					maxCandy = Math.max(countMax(board), maxCandy);
					swap(board, r, c, r + 1, c);
				}
			}
		}
		
		System.out.println(maxCandy);
	}
	
	public static int countMax(char[][] board) {
	    int answer = 1;

	    // 가로 기준 체크
	    for (int r = 0; r < N; r++) {
	        int candySum = 1;
	        for (int c = 1; c < N; c++) { 
	            if (board[r][c] == board[r][c - 1]) {
	                candySum++;
	                answer = Math.max(answer, candySum);
	            } else {
	                candySum = 1;
	            }
	        }
	    }

	    // 세로 기준 체크
	    for (int c = 0; c < N; c++) {
	        int candySum = 1;
	        for (int r = 1; r < N; r++) { 
	            if (board[r][c] == board[r - 1][c]) {
	                candySum++;
	                answer = Math.max(answer, candySum);
	            } else {
	                candySum = 1;
	            }
	        }
	    }
	    return answer;
	}
	
	public static void swap(char[][] board, int r1, int c1, int r2, int c2) {
		char temp = board[r1][c1];
		board[r1][c1] = board[r2][c2];
		board[r2][c2] = temp;
	}

}
