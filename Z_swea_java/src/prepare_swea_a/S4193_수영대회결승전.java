package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S4193_수영대회결승전 {
	static int N, time, minTime;
	static int[][] pool;
	static int[] start, end;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s4193.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			pool = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					pool[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine().trim());
			start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			st = new StringTokenizer(br.readLine().trim());
			end = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			minTime = Integer.MAX_VALUE;
			time = 0;
			bfs();
			
			System.out.println("#" + tc + " " + minTime);
		}
	}
	
	static void bfs() {
	    Queue<int[]> q = new LinkedList<>();
	    // x, y, time 저장
	    q.offer(new int[] {start[0], start[1], 0});
	    boolean[][] visited = new boolean[N][N];
	    visited[start[0]][start[1]] = true;
	    
	    while(!q.isEmpty()) {
	        int[] curr = q.poll();
	        int r = curr[0];
	        int c = curr[1];
	        int t = curr[2];
	        
	        // 목적지 도착 시 최솟값 갱신 후 종료 (BFS이므로 처음 도착이 곧 최소)
	        if (r == end[0] && c == end[1]) {
	            minTime = t;
	            return;
	        }
	        
	        for (int i = 0; i < 4; i++) {
	            int nx = r + dx[i];
	            int ny = c + dy[i];
	            
	            if (nx < 0 || ny < 0 || nx >= N || ny >= N || pool[nx][ny] == 1 || visited[nx][ny]) continue;
	            
	            // 소용돌이를 만난 경우
	            if (pool[nx][ny] == 2) {
	                // 소용돌이가 사라질 때까지 기다려야 하는 시간 계산
	                // t % 3 == 0 (2초 남음), t % 3 == 1 (1초 남음), t % 3 == 2 (바로 통과)
	                int wait = 2 - (t % 3); 
	                q.offer(new int[] {nx, ny, t + wait + 1});
	                visited[nx][ny] = true;
	            } 
	            // 일반 공간인 경우
	            else {
	                q.offer(new int[] {nx, ny, t + 1});
	                visited[nx][ny] = true;
	            }
	        }
	    }
	    // 도착할 수 없는 경우
	    minTime = -1;
	}
}
