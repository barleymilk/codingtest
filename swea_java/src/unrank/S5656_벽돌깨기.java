package unrank;

import java.io.*;
import java.util.*;

public class S5656_벽돌깨기 {
	static int N, W, H, minRemain;
	static int[][] board, copiedBoard;
	static int[] perm;
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
			
			board = new int[H][W];
			copiedBoard = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
				copiedBoard[i] = board[i].clone();
			}
			
			// 벽돌 N개를 떨어뜨리는 중복순열을 구함 -> 벽돌 깨기 -> 남아 있는 벽돌의 최소 개수 구하기
			perm = new int[N];
			minRemain = Integer.MAX_VALUE;
			dfs(0);
			
			System.out.println("#" + tc + " " + minRemain);
		}
	}
	
	static void dfs(int depth) {
		if (depth == N) {	
			for (int i = 0; i < N; i++) {
				bomb(perm[i]);
				down();
			}
			
			// 남은 벽돌 세기 -> 최솟값 갱신 
			int remain = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (copiedBoard[i][j] != 0) remain += 1;
				}
			}
			minRemain = Math.min(minRemain, remain);
			
			// 보드 초기화
			for (int i = 0; i < H; i++) {
				copiedBoard[i] = board[i].clone();
			}
			
			return;
		}
		
		for (int i = 0; i < W; i++) {
			perm[depth] = i;
			dfs(depth + 1);
		}
	}
	
	static void bomb(int col) {	
		// 터질 벽돌을 q에 저장 
		Queue<int[]> q = new LinkedList<>();
		
		// 구슬이 벽돌을 처음 만났을 때 -> q 처음 값 삽입 
		for (int i = 0; i < H; i++) {
			if (copiedBoard[i][col] != 0) {
				int val = copiedBoard[i][col];
				q.add(new int[] {i, col, val});
				break;
			}
		}
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int y = curr[1];
			int size = curr[2];
			copiedBoard[x][y] = 0; // 터뜨림
			
			for (int i = 0; i < 4; i++) {
				for (int v = 1; v < size; v++) { // 상하좌우 value - 1만큼의 범위 폭발 
					int nx = x + dx[i] * v;
					int ny = y + dy[i] * v;
					
					if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
					
					// 추가적으로 폭발할 벽돌이라면 -> q에 추가
					if (copiedBoard[nx][ny] != 0) {
						q.add(new int[] {nx, ny, copiedBoard[nx][ny]});
						copiedBoard[nx][ny] = 0; // 터뜨림 
					}
				}
			}
		}
	}
	
	static void down() {
		// stack에다가 한 column씩 저장하고 아랫줄부터 윗줄까지 채워넣기
		for (int j = 0; j < W; j++) {
			// 한 컬럼마다 stack을 구하기
			Stack<Integer> stack = new Stack<>();
			
			// stack 채우기
			for (int i = 0; i < H; i++) {
				int val = copiedBoard[i][j];
				if (val != 0) {
					stack.push(val);
					copiedBoard[i][j] = 0; // 0으로 초기화
				}
			}
			
			// 컬럼에다가 stack으로 다시 채워주기
			int rowIdx = H-1;
			while(!stack.isEmpty()) {
				copiedBoard[rowIdx--][j] = stack.pop();
			}
		}
	}
	
}
