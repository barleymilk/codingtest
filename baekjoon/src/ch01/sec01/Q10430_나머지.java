package ch01.sec01;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q10430_나머지 {

	public static void main(String[] args) throws Exception {
		// 나머지 
		// https://www.acmicpc.net/problem/10430
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		System.out.println((A+B)%C);
		System.out.println(((A%C) + (B%C))%C);
		System.out.println((A*B)%C);
		System.out.println(((A%C) * (B%C))%C);
		
	}

}
