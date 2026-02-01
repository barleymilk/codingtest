package ch01.sec02;

import java.io.*;
import java.util.*;

public class Q1107_리모컨 {

	static boolean[] broken = new boolean[10];
	
	public static void main(String[] args) throws Exception {
		// https://www.acmicpc.net/problem/1107
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		for (int i = 0; i < m; i++) {
			int b = sc.nextInt();
			broken[b] = true;
		}
		
		// 1. 숫자 버튼을 누르지 않고 +, -로만 이동하는 경우 (초기값)
		int minPress = Math.abs(n - 100);
		
		// 2. 모든 채널(0 ~ 1,000,000)을 순회하며 최소 클릭 수 탐색
		// 목표가 500,00이지만 1,000,000에서 내려오는 게 더 빠를 수 있음
		for (int i = 0; i < 1_000_000; i++) {
			int len = check(i); // 숫자 버튼으로 이동 가능한지 확인
			if (len > 0) {
				// 이동 가능하다면
				int press = Math.abs(i - n); // + 또는 - 누르는 횟수
				if (minPress > len + press) {
					minPress = len + press;
				}
			}
		}
		
		System.out.println(minPress);

	}
	
	static int check(int c) {
		if (c == 0) return broken[0] ? 0 : 1;
		
		int len = 0;
		while(c > 0) {
			if (broken[c % 10]) return 0; // 고장난 버튼이 포함됨
			len++;
			c /= 10;
		}
		return len;
	}

}
