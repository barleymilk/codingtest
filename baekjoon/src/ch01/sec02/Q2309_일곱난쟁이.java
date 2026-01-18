package ch01.sec02;

import java.io.*;
import java.util.*;

public class Q2309_일곱난쟁이 {
	
	static int totalHeights;
	static boolean found = false; // 정답을 찾았는지 체크하는 플래그 

	public static void main(String[] args) throws Exception {
		// https://www.acmicpc.net/problem/2309
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = 9;
		int[] dwarfsHeights = new int[T];
		for (int i = 0; i < T; i++) dwarfsHeights[i] = sc.nextInt();
		
		// 미리 오름차순 정렬 (출력 조건 해결) 
		Arrays.sort(dwarfsHeights);
				
		// 일곱난쟁이들의 키 합은 100
		// 가짜 2명 조합 구해서 총 난쟁이 키에서 빼서 100인지 확인하기 
		totalHeights = Arrays.stream(dwarfsHeights).sum();
		boolean[] visited = new boolean[T];
		
		// 9명 중 가짜 2명을 뽑는 조합 시작 
		combination(dwarfsHeights, visited, 0, 9, 2);
	}
	
	public static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
		if (found) return; // 이미 정답을 찾았다면 재귀 종료 
		
		if (r == 0) {
			// 2명을 다 뽑았을 때 로직 처리(합이 100인지 확인)
			int fakeSum = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i]) fakeSum += arr[i];
			}
			
			// 전체 합 - 가짜 2명 합 = 100인지 확인 
			if (totalHeights - fakeSum == 100) {
				for (int i = 0; i < n; i++) {
					if (!visited[i]) {
						System.out.println(arr[i]);
					}
				}
				found = true;
			}
			return;
		}
		
		for (int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i + 1, n, r - 1);
			visited[i] = false;
		}
	}

}
