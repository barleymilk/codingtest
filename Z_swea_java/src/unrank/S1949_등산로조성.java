package unrank;

import java.io.*;
import java.util.*;

public class S1949_등산로조성 {
	static int N, K, maxDist;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1949.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이
			
			map = new int[N][N];
			List<int[]> start = new ArrayList<>();
			int maxHeight = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
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
			
			maxDist = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < start.size(); i++) {
				int[] pos = start.get(i);
				dfs(pos[0], pos[1], maxHeight, false, 1);
			}
			
			System.out.println("#" + tc + " " + maxDist);
		}
	}
	
	static void dfs(int x, int y, int h, boolean used, int dist) {
		maxDist = Math.max(maxDist, dist);
		visited[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
			
			// 안 깎고 갈 수 있을 때 
			if (map[nx][ny] < h) {
				dfs(nx, ny, map[nx][ny], used, dist + 1);
			}
			// 깎아야 갈 수 있을 때
			else if (!used && (map[nx][ny] - K < h)) {
				dfs(nx, ny, h - 1, true, dist + 1);
			}
		}
		
		visited[x][y] = false;
	}
}

