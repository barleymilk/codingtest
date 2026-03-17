package d4Java;

import java.io.*;
import java.util.*;

public class S1251_하나로 {
	static int N;
	static int[][] islands;
	static double E;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			// input 받아오기 
			N = Integer.parseInt(br.readLine().trim());
			islands = new int[N][2];
			
			StringTokenizer st;
			// X 좌표 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) islands[i][0] = Integer.parseInt(st.nextToken());

			// Y 좌표 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) islands[i][1] = Integer.parseInt(st.nextToken());
			
			// 세율
			E = Double.parseDouble(br.readLine().trim());
			
			double result = prim();
			System.out.println("#" + tc + " " + Math.round(result));
		}
		
	}
	
	static double prim() {
		double[] minEdge = new double[N]; // 각 섬까지의 최소 비용 저장
		boolean[] visited = new boolean[N];
		Arrays.fill(minEdge,  Double.MAX_VALUE);
		
		minEdge[0] = 0; // 0번 섬부터 시작
		double result = 0;
		
		for (int i = 0; i < N; i++) {
			double min = Double.MAX_VALUE;
			int minIdx = -1;
			
			// 1. 아직 연결되지 않은 섬 중 비용이 가장 저렴한 섬 선택
			for (int j = 0; j < N; j++) {
				if (!visited[j] && minEdge[j] < min) {
					min = minEdge[j];
					minIdx = j;
				}
			}
			
			if (minIdx == -1) break; // 연결이 안 됐으면 중단
			
			visited[minIdx] = true; // 방문 처리
			result += min;
			
			// 2. 선택된 섬을 기준으로 남은 다른 섬들과의 거리 갱신
			for (int j = 0; j < N; j++) {
				if (!visited[j]) {
					double distSq = getDistSq(minIdx, j);
					double cost = E * distSq;
					if (cost < minEdge[j]) { // 새로 계산한 비용이 기존보다 싸면 업데이트
						minEdge[j] = cost;
					}
				}
			}
		}
		
		return result;
	}
	
	static double getDistSq(int i, int j) {
		long dx = islands[i][0] - islands[j][0];
		long dy = islands[i][1] - islands[j][1];
		return (double)(dx * dx) + (double)(dy * dy);
	}

}