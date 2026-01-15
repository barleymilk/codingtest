package ch01.sec01;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q4375_1 {

	public static void main(String[] args) throws Exception {
		// 1
		// https://www.acmicpc.net/problem/4375
		
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNextInt()) {
			int N = sc.nextInt();
			int remain = 1 % N;
			int answer = 1;
			
			while(true) {
				if (remain == 0) {
					System.out.println(answer);
					break;
				} 
				else {
					remain = (remain * 10 + 1) % N;
					answer++;
				}
			}
		}
		sc.close();

	}

}
