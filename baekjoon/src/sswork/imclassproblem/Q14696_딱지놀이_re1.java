package sswork.imclassproblem;

import java.util.Scanner;
import java.io.FileInputStream;

public class Q14696_딱지놀이_re1 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    
    for (int n = 0; n < N; n++) {
      int[] countA = new int[5];
      int[] countB = new int[5];

      // A의 카드 입력 및 카운트
      int cardNumA = sc.nextInt();
      for (int a = 0; a < cardNumA; a++) {
        countA[sc.nextInt()]++;
      }

      // B의 카드 입력 및 카운트
      int cardNumB = sc.nextInt();
      for (int b = 0; b < cardNumB; b++) {
        countB[sc.nextInt()]++;
      }

      // 승부 판정
      String result = "D"; // 기본값은 무승부
      for (int i = 4; i >= 1; i--) {
        if (countA[i] > countB[i]) {
          result = "A";
          break;
        } else if (countA[i] < countB[i]) {
          result = "B";
          break;
        }
      }
      System.out.println(result);
    }
    sc.close();
  }
}
