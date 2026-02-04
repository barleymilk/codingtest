package sswork.imclassproblem;

import java.io.FileInputStream;
import java.util.*;

public class Q2309_일곱난쟁이 {
  static int totalHeights;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    int[] fakeDwarfs = new int[9];
    for (int i = 0; i < 9; i++) {
      fakeDwarfs[i] = sc.nextInt();
    }
    Arrays.sort(fakeDwarfs);

    totalHeights = Arrays.stream(fakeDwarfs).sum();

    // 9명 키 총합에서 2명의 키를 뺀 것이 100인지 확인
    outer:
    for (int i = 0; i < 8; i++) {
      for (int j = i+1; j < 9; j++) {
        if (totalHeights - fakeDwarfs[i] - fakeDwarfs[j] == 100) {
          fakeDwarfs[i] = 0;
          fakeDwarfs[j] = 0;
          break outer;
        }
      }
    }

    for (int i = 0; i < 9; i++) {
      if (fakeDwarfs[i] != 0) System.out.println(fakeDwarfs[i]);
    }

    sc.close();
  }
}
