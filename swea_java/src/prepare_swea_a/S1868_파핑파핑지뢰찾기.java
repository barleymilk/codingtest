package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S1868_파핑파핑지뢰찾기 {
	static int N, cnt;
	static char[][] board;
	static int[] dx = {-1,1,0,0,-1,-1,1,1}, dy = {0,0,-1,1,-1,1,-1,1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1868.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new char[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					board[i][j] = line.charAt(j);
				}
			}
			
			// 1. 주변 지뢰 개수 미리 계산하기
			int[][] bombCntBoard = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '.') bombCntBoard[i][j] = countBombs(i, j);
				}
			}
			
			// 2. 주변 지뢰가 0인 칸들을 먼저 클릭
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '.' && bombCntBoard[i][j] == 0) {
						cnt++;
						bfs(i, j, bombCntBoard);
					}
				}
			}
			
			// 3. 아직 열리지 않은 칸 클릭
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '.') {
						cnt++;
					}
				}
			}
			
			System.out.println("#" + tc + " " + cnt);
		}
	}
	
	static void bfs(int x, int y, int[][] bombCntBoard) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		board[x][y] = 'v'; // 방문 표시
		
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			
			for (int i = 0; i < 8; i++) { // 8 방향
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];
				
				// 범위를 벗어나거나 이미 방문한 곳이면 통과
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] != '.') continue;
				
				// 8 방향 탐색 
				if (bombCntBoard[nx][ny] == 0) {
					q.offer(new int[] {nx, ny});
				}
				board[nx][ny] = 'v';
			}
		}
	}
	
	static int countBombs(int x, int y) {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < N && ny < N && board[nx][ny] == '*') {
				count++;
			}
		}
		return count;
	}
}
