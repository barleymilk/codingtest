package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S2382_미생물격리 {
	static int N, M, K;
	static List<Microbe> list;
	static int[] dx = {0,-1,1,0,0}, dy = {0,0,0,-1,1};
	
	public static class Microbe implements Comparable<Microbe> {
		int r, c, cnt, dir;
		
		public Microbe (int r, int c, int cnt, int dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}
		
		@Override
		public int compareTo(Microbe o) {
			if (this.r != o.r) return this.r - o.r; // 오름차순
			if (this.c != o.c) return this.c - o.c; // 오름차순
			return o.cnt - this.cnt; // 내림차순
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s2382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				list.add(new Microbe(r, c, cnt, dir));
			}
			
			// 정해진 시간 동안 미생물 이동 + 병합
			for (int time = 0; time < M; time++) {
				move();
				merge();
			}
			
			// 남은 미생물 수 구하기
			int answer = countMicrobe();
			
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	public static void move() {
		// 군집 돌기
		for (int i = 0; i < list.size(); i++) {
			Microbe o = list.get(i);
			
			// 다음 이동
			int nx = o.r + dx[o.dir];
			int ny = o.c + dy[o.dir];
			
			// 만약 약품 구역에 도달한다면 -> 미생물 수 절반, 이동방향 반대
			if (nx == 0 || ny == 0 || nx == N-1 || ny == N-1) {
				// 미생물 수 절반
				o.cnt = o.cnt / 2;
				
				// 미생물이 사라지면 삭제해주기
				if (o.cnt == 0) {
					list.remove(i);
					i--;
					continue;
				}
				
				// 이동방향 반대
				switch (o.dir) {
					case 1: o.dir = 2; break;
					case 2: o.dir = 1; break;
					case 3: o.dir = 4; break;
					case 4: o.dir = 3; break;
				}
			}
			
			o.r = nx;
			o.c = ny;
		}
	}
	
	public static void merge() {
		Collections.sort(list);
		// 군집 돌기, 짝꿍끼리 계산(compareTo를 통해 바로 옆에 있는 애들끼리 좌표가 같은지 알 수 있음)
		for (int i = 0; i < list.size() - 1; i++) {
			Microbe o1 = list.get(i);
			Microbe o2 = list.get(i + 1);
			
			// 좌표가 같은지 확인
			if (o1.r == o2.r && o1.c == o2.c) {
				// 미생물 수가 큰 애한테 더해주기
				o1.cnt += o2.cnt;
				list.remove(i+1); // 작은 애는 병합 후 지워주기
				i--; // 인덱스 보정
			}
		}
	}
	
	public static int countMicrobe() {
		int cnt = 0;
		for (int i = 0; i < list.size(); i++) {
			Microbe o = list.get(i);
			cnt += o.cnt;
		}
		return cnt;
	}
}
