package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S1767_프로세서연결하기 {
	static int N, totalCores, maxCnt, minLen;
	static int[][] map;
	static List<int[]> cores;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1767.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			cores = new ArrayList<>();
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i != 0 && i != N-1 && j != 0 && j != N-1 && map[i][j] == 1) {
						cores.add(new int[] {i, j});
					}
				}
			}
			totalCores = cores.size();
			
			maxCnt = 0;
			minLen = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			
			System.out.println("#" + tc + " " + minLen);
		}
	}

	static void dfs(int depth, int connected, int lenSum) {
		if (depth == totalCores) {
			if (connected > maxCnt) {
				maxCnt = connected;
				minLen = lenSum;
			}
			else if (connected == maxCnt) {
				minLen = Math.min(minLen, lenSum);
			}
			return;
		}
		
		// 연결 시도
		int[] curr = cores.get(depth);
		int x = curr[0];
		int y = curr[1];
		for (int i = 0; i < 4; i++) {
			if (isPossible(x, y, i)) {
				int len = paintMap(x, y, i, 2);
				dfs(depth + 1, connected + 1, lenSum + len);
				paintMap(x, y, i, 0);
			}
		}
		
		// 연결 안 함
		dfs(depth + 1, connected, lenSum);
	}
	
	static boolean isPossible(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		while(nx >= 0 && ny >= 0 && nx <= N-1 && ny <= N-1) {
			if (map[nx][ny] != 0) return false;
			nx += dx[dir];
			ny += dy[dir];
		}
		return true;
	}
	
	static int paintMap(int x, int y, int dir, int val) {
		int len = 0;
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		while(nx >= 0 && ny >= 0 && nx <= N-1 && ny <= N-1) {
			map[nx][ny] = val;
			len++;
			nx += dx[dir];
			ny += dy[dir];
		}
		return len;
	}
}
