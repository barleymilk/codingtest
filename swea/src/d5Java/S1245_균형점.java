package d5Java;

import java.io.*;
import java.util.*;

public class S1245_균형점 {
	static int N;
	static int[] magnet, m;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_s1245.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 자성체 개수
			
			magnet = new int[N]; // 자석의 x좌표
			m = new int[N]; // 질량 
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) magnet[i] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) m[i] = Integer.parseInt(st.nextToken());
			
			System.out.print("#" + tc + " ");
			calcF();
			System.out.println();
		}
	}
	
	static void calcF () {
		// 균형점 개수만큼 반복
		for (int i = 0; i < N - 1; i++) {
			double low = magnet[i];
			double high = magnet[i+1];
			double ans = 0;
			
			for (int k = 0; k < 100; k++) {
				double mid = (low + high) / 2.0;
				double sumF = 0;
				
				// 왼쪽 자성체들의 힘 합산
				for (int j = 0; j <= i; j++) {
					sumF += m[j] / Math.pow(mid - magnet[j],  2);
				}
				// 오른쪽 자성체들의 힘 차감
				for (int j = i + 1; j < N; j++) {
					sumF -= m[j] / Math.pow(mid - magnet[j],  2);
				}
				
				if (sumF > 0) low = mid;
				else high = mid;
				ans = mid;
			}
			System.out.printf("%.10f ", ans);
		}
	}
}
