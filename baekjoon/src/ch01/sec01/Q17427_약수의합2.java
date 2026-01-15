package ch01.sec01;

import java.io.*;
import java.util.*;

public class Q17427_약수의합2 {

	public static void main(String[] args) throws Exception {
		// 약수의 합 2
		// https://www.acmicpc.net/problem/17427
		
		int MAX = 1_000_000;
		long[] f = new long[MAX+1];
		long[] g = new long[MAX+1];
		
		// 1. f(n) 구하기
		Arrays.fill(f,  1);
		for (int i = 2; i <= MAX; i++) {
			for (int j = 1; i * j <= MAX; j++) {
				f[i * j] += i;
			}
		}
		
		// 2. g(n) 구하기
		for (int i = 1; i <= MAX; i++) {
			g[i] = g[i - 1] + f[i];
		}
		
		// 3. 입출력 처리 
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		System.out.println(g[N]);
	}

}
