package sswork.imclassproblem;

import java.io.*;
import java.util.*;

public class Q2669_직사각형네개의합집합의면적구하기 {

	public static void main(String[] args) throws Exception {
		// https://www.acmicpc.net/problem/2669
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    // 전체 영역: 최대 100 * 100
    int[][] map = new int[100][100];

    // 직사각형 4개
    for (int n = 0; n < 4; n++) {
      int lx = sc.nextInt();
      int ly = sc.nextInt();
      int rx = sc.nextInt();
      int ry = sc.nextInt();

      // map에 해당 영역 칠하기
      for (int x = ly; x < ry; x++) {
        for (int y = lx; y < rx; y++) {
          map[x][y] = 1;
        }
        
      }

      // 영역 확인
      // StringBuilder sb = new StringBuilder();
      // for (int i = 0; i < 100; i++) {
      //   for (int j = 0; j < 100; j++) {
      //     sb.append(map[i][j]).append(" ");
      //   }
      //   sb.append("\n");
      // }
      // System.out.println(sb);
    }

    int answer = 0;
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        answer += map[i][j];
      }
    }
    System.out.println(answer);
    
    sc.close();
		
	}

}
