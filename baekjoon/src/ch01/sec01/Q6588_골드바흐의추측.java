package ch01.sec01;

import java.io.*;
import java.util.*;

public class Q6588_골드바흐의추측 {

	public static void main(String[] args) throws Exception {
		// 골드바흐의 추측 
		// https://www.acmicpc.net/problem/6588
		// 4보다 큰 모든 짝수는 두 홀수 소수의 합으로 나타낼 수 있다.
		int MAX = 1_000_000;
		boolean[] isPrime = new boolean[MAX+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		// 에라토스테네스의 체 
		for (int i = 2; i * i <= MAX; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j <= MAX; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			String line = br.readLine();
			if (line == null) break;
			int n = Integer.parseInt(line);
			if (n == 0) break;
			
			boolean found = false;
			for (int a = 3; a <= n / 2; a += 2) {
				if (isPrime[a] && isPrime[n - a]) {
					sb.append(n).append(" = ").append(a).append(" + ").append(n - a).append("\n");
					found = true;
					break;
				}
			}
			
			if (!found) {
				sb.append("Glodbach's conjecture is wrong.\n");
			}
		}
		System.out.print(sb);
	}

}
