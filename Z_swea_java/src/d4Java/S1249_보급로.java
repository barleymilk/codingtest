package d4Java;

import java.io.*;
import java.util.*;

public class S1249_보급로 {
	static int N, minTime;
	static int[][] map, dist;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1249.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			
			minTime = Integer.MAX_VALUE;
			bfs();
			
			System.out.println("#" + tc + " " + minTime);
		}
	}
	
	static void bfs() {		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // 오름차순 정렬
		pq.offer(new int[] {0, 0, 0}); // x, y, cost
		
		dist = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
		dist[0][0] = 0;
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int x = curr[0]; int y = curr[1]; int time = curr[2];
			
			// 목적지 도착한 경우
			if (x == N - 1 && y == N -1) {
				minTime = time;
				return;
			}
			
			if (time > dist[x][y]) continue;
			
			// 사방 탐색
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				
				if (time + map[nx][ny] < dist[nx][ny]) {
					dist[nx][ny] = time + map[nx][ny];
					pq.offer(new int[] {nx, ny, dist[nx][ny]});
				}
			}
		}
	}
}
