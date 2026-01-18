package ch01.sec01;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Q1978_소수 {

	public static void main(String[] args) throws Exception {
		// 소수 찾기 
		// https://www.acmicpc.net/problem/1978
		
		int MAX = 1_000;
		boolean[] isPrime = new boolean[MAX+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		// 에라토스테네스의 체
		for (int i = 2; i * i <= MAX; i++) {
			if (isPrime[i]) {
				// i의 배수들을 모두 false로 변경
				for (int j = i * i; j <= MAX; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int answer = 0;
		for (int tc = 1; tc <= N; tc++) {
			int num = sc.nextInt();
			if (isPrime[num]) answer++;
		}
		
		System.out.println(answer);

	}

}
