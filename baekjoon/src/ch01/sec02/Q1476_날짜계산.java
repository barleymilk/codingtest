package ch01.sec02;

import java.io.*;
import java.util.*;

public class Q1476_날짜계산 {

	public static void main(String[] args) throws Exception {
		// https://www.acmicpc.net/problem/1476
		
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();
		
		int e = 1, s = 1, m = 1;
		for (int year = 1; ; year++) {
			if (e == E && s == S && m == M) {
				System.out.println(year);
				break;
			}
			e++; s++; m++;
			if (e > 15) e = 1;
			if (s > 28) s = 1;
			if (m > 19) m = 1;
		}
		
	}

}
