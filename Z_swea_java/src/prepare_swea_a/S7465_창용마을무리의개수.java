package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S7465_창용마을무리의개수 {
	static int N, M, cnt;
	static ArrayList<Integer>[] adj;
	static boolean[] people;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s7465.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			people = new boolean[N + 1];
			adj = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				// 무방향 그래프
				adj[u].add(v);
				adj[v].add(u);
			}
			
			cnt = 0;
			bfs();
			
			System.out.println("#" + tc + " " + cnt);
		}
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if(!people[i]) {
				cnt++;
				q.offer(i);
				people[i] = true;
				
				while(!q.isEmpty()) {
					int curr = q.poll();
					
					for (int neighbor : adj[curr]) {
						if (!people[neighbor]) {
							people[neighbor] = true;
							q.add(neighbor);
						}
					}
				}
			}
		}
	}
}
