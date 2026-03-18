package unrank;

import java.io.*;
import java.util.*;

public class S5656_벽돌깨기_2 {
	static int N, W, H, minRemain;
	static int[][] originalBoard;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			originalBoard = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					originalBoard[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			minRemain = H * W;
			dfs(0, originalBoard);
			
			System.out.println("#" + tc + " " + minRemain);
		}
		
	}
	
	static void dfs(int depth, int[][] currBoard) {
		int remain = countBricks(currBoard);
		
		// 가지치기
		if (remain == 0) {
			minRemain = 0;
			return;
		}
		
		if (depth == N) {
			minRemain = Math.min(minRemain, remain);
			return;
		}
		
		for (int c = 0; c < W; c++) {
			// 보드 카피
			int[][] nextBoard = copy(currBoard);
			
			// 폭발, 중력 
			bomb(c, nextBoard);
			down(nextBoard);
			
			// 다음 단계
			dfs(depth + 1, nextBoard);
			
			// 가지치기
			if (minRemain == 0) return;
			
		}
	}
	
	static int[][] copy(int[][] board) {
		int[][] newBoard = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			newBoard[i] = board[i].clone();
		}
		
		return newBoard;
	}
	
	static void bomb(int c, int[][] board) {
		// q에다가 터질 벽돌 넣기.
		Queue<int[]> q = new LinkedList<>();
		// q 처음 요소 넣기
		for (int r = 0; r < H; r++) {
			if (board[r][c] != 0) {
				q.offer(new int[] {r, c, board[r][c]});
				break;
			}
		}
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int y = curr[1];
			int size = curr[2];
			board[x][y] = 0; // 현재 자리 터뜨림
			
			for (int i = 0; i < 4; i++) {
				for (int s = 1; s < size; s++) {
					int nx = x + dx[i] * s;
					int ny = y + dy[i] * s;
					
					if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
					
					if (board[nx][ny] != 0) {
						q.offer(new int[] {nx, ny, board[nx][ny]});
						board[nx][ny] = 0; // 주변 자리 터뜨림
					}
				}
			}
		}
	}
	
	static void down(int[][] board) {
		// 열마다 stack -> 다시 아래부터 채워넣기
		for (int j = 0; j < W; j++) {
			Stack<Integer> stack = new Stack<>();
			for (int i = 0; i < H; i++) {
				if (board[i][j] != 0) {
					stack.push(board[i][j]);
					board[i][j] = 0; // 지우기
				}
			}
			
			int row = H - 1;
			while(!stack.isEmpty()) {
				board[row--][j] = stack.pop();
			}
		}
	}
	
	static int countBricks(int[][] board) {
		int bricks = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (board[i][j] != 0) bricks++;
			}
		}
		return bricks;
	}
}
