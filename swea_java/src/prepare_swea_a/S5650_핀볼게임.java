package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S5650_핀볼게임 {
	static int N, maxScore;
	static int[][] board;
	static int[][] wormHoles;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s5650.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			
			board = new int[N][N];
			
			wormHoles = new int[11][4];
			for (int i = 6; i <= 10; i++) Arrays.fill(wormHoles[i], -1);
			
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] >= 6 && board[i][j] <= 10) {
						int type = board[i][j];
						if (wormHoles[type][0] == -1) {
							wormHoles[type][0] = i;
							wormHoles[type][1] = j;
						}
						else {
							wormHoles[type][2] = i;
							wormHoles[type][3] = j;
						}
					}
				}
			}

			maxScore = 0;
			int score = 0;
			for (int d = 0; d < 4; d++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						// 빈 공간에서만 시작할 수 있다.
						if (board[i][j] == 0) {
							score = startGame(i, j, d);
							maxScore = Math.max(maxScore, score);
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + maxScore);
		}
	}
	
	public static int startGame(int x, int y, int d) {
		int score = 0;
		int nx = x;
		int ny = y;
		int dir = d;

		while(true) {	
			// 다음으로 움직이기
			nx += dx[dir];
			ny += dy[dir];
			
			// 벽에 부딪쳤을 때 -> 방향 반대로 바꾸기
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				score++;
				dir = changeDir(5, dir);
				nx += dx[dir];
				ny += dy[dir];
			}
			
			// 시작 지점으로 돌아오거나 블랙홀에 닿으면 게임 종료
			if ((nx == x && ny == y) || board[nx][ny] == -1) break;
			
			// 블록에 부딪쳤을 때(1-5)
			if (board[nx][ny] >= 1 && board[nx][ny] <= 5) {
				score++;
				dir = changeDir(board[nx][ny], dir);
			}
			// 웜홀에 들어갔을 때 (6-10)
			else if (board[nx][ny] >= 6 && board[nx][ny] <= 10) {
				int type = board[nx][ny];
				if (wormHoles[type][0] == nx && wormHoles[type][1] == ny) {
					nx = wormHoles[type][2];
					ny = wormHoles[type][3];
				} else {
					nx = wormHoles[type][0];
					ny = wormHoles[type][1];
				}
			}
		}
		
		return score;
	}

	public static int changeDir(int type, int currDir) {
		switch (type) {
			case 1: {
				if (currDir == 0) return 1;
				if (currDir == 1) return 3;
				if (currDir == 2) return 0;
				if (currDir == 3) return 2;
				break;
			}
			case 2: {
				if (currDir == 0) return 3;
				if (currDir == 1) return 0;
				if (currDir == 2) return 1;
				if (currDir == 3) return 2;
				break;
			}
			case 3: {
				if (currDir == 0) return 2;
				if (currDir == 1) return 0;
				if (currDir == 2) return 3;
				if (currDir == 3) return 1;
				break;
			}
			case 4: {
				if (currDir == 0) return 1;
				if (currDir == 1) return 2;
				if (currDir == 2) return 3;
				if (currDir == 3) return 0;
				break;
			}
			case 5: {
				if (currDir == 0) return 1;
				if (currDir == 1) return 0;
				if (currDir == 2) return 3;
				if (currDir == 3) return 2;
				break;
			}
		}
		return currDir;
	}
}
