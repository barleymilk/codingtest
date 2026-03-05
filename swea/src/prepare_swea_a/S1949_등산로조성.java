package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S1949_등산로조성 {
	static int N, K, maxLen;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1949.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken()); // 공사 가능 깊이
			
			map = new int[N][N];
			List<int[]> start = new ArrayList<>();
			int maxHeight = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > maxHeight) {
						maxHeight = map[i][j];
						start.clear();
						start.add(new int[] {i, j});
					} else if (map[i][j] == maxHeight) {
						start.add(new int[] {i, j});
					}
				}
			}
			
			maxLen = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < start.size(); i++) {
				int[] curr = start.get(i);
				dfs(false, curr[0], curr[1], maxHeight, 1);
			}
			
			System.out.println("#" + tc + " " + maxLen);
		}
	}
	
	static void dfs(boolean used, int x, int y, int height, int len) {
		maxLen = Math.max(maxLen, len);
		visited[x][y] = true; 
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 범위를 벗어나거나 이미 방문한 곳이면 통과
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
			
			int nextHeight = map[nx][ny];
			// 깎지 않고 이동 가능한 경우
			if (nextHeight < height) {
				dfs(used, nx, ny, nextHeight, len + 1);
			}
			// 깎아야만 이동 가능한 경우
			else if (!used && (nextHeight - K < height)) {
				dfs(true, nx, ny, height - 1, len + 1);
			}
		}
		
		visited[x][y] = false; 
	}
}
