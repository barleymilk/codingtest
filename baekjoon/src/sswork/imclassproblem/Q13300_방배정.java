package sswork.imclassproblem;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q13300_방배정 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream(("res/input.txt")));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt(); // 학생 수
    int K = sc.nextInt(); // 최대 인원 수

    int[][] rooms = new int[2][6];
    for (int i = 0; i < N; i++) {
      int gender = sc.nextInt();
      int grade = sc.nextInt();
      rooms[gender][grade-1]++;
    }

    int count = 0;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 6; j++) {
        count += rooms[i][j] / K;
        if (rooms[i][j] % K != 0) count++;
      }
    }

    System.out.println(count);
    sc.close();
  }
}
