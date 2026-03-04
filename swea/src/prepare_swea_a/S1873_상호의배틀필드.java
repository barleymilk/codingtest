package prepare_swea_a;

import java.io.*;
import java.util.*;

public class S1873_상호의배틀필드 {
	static int H, W, N;
	static char[][] map;
	static char[] order;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static int[] now;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			now = new int[3]; // x, y, 방향: 0위, 1아래, 2왼, 3오
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == '^') now = new int[] {i, j, 0};
					if (map[i][j] == 'v') now = new int[] {i, j, 1};
					if (map[i][j] == '<') now = new int[] {i, j, 2};
					if (map[i][j] == '>') now = new int[] {i, j, 3};
				}
			}
			
			N = Integer.parseInt(br.readLine());
			order = new char[N];
			String line = br.readLine();
			for (int i = 0; i < N; i++) {
				order[i] = line.charAt(i);
			}
			
			// 명령 순서대로 작동
			for (char o : order) {
				switch(o) {
					case 'S': {
						// 방향부터 보기
						int dir = now[2];
						int sx = now[0];
						int sy = now[1];
						
						// 그 방향으로 쭉 보내기
						while(true) {
							sx += dx[dir];
							sy += dy[dir];
							
							// 범위 벗어나거나 강철벽에 맞으면 소멸
							if (!checkMap(sx, sy) || map[sx][sy] == '#') break;
							
							// 벽돌벽에 맞으면 벽돌 부수고 break;
							if (map[sx][sy] == '*') {
								map[sx][sy] = '.';
								break;
							}
						}
						break;
					}
					default: {
						move(o);
						break;
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			
			System.out.print("#" + tc + " " + sb);
		}
	}
	
	static void move(char dir) {
		// 방향 전환
		char tank = ' ';
		if (dir == 'U') {
			tank = '^'; now[2] = 0;
		}
		else if (dir == 'D') {
			tank = 'v'; now[2] = 1;
		}
		else if (dir == 'L') {
			tank = '<'; now[2] = 2;
		}
		else if (dir == 'R') {
			tank = '>'; now[2] = 3;
		}
		map[now[0]][now[1]] = tank;
		
		// 다음 좌표
		int nx = now[0] + dx[now[2]];
		int ny = now[1] + dy[now[2]];
		
		// 이동할 수 있다면 이동
		if (checkMap(nx, ny) && map[nx][ny] == '.') {
			map[now[0]][now[1]] = '.'; // 탱크가 있던 곳은 평지로 바꾸기
			map[nx][ny] = tank; // 탱크 이동
			now[0] = nx;
			now[1] = ny;
		}
	}
	
	static boolean checkMap(int x, int y) {
		if (x >= 0 && y >= 0 && x < H && y < W) return true;
		return false;
	}
}
