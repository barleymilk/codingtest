package sswork.imclassproblem;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q14696_딱지놀이 {
  public static void main(String[] args) throws Exception {
  // 별(4) > 동그라미(3) > 네모(2) > 세모(1)
  // 별, 동그라미, 네모, 세모의 개수가 각각 모두 같다면 무승부 -> D
  System.setIn(new FileInputStream("res/input.txt"));
  Scanner sc = new Scanner(System.in);

  int N = sc.nextInt();
  int[][] A = new int[N][5];
  int[][] B = new int[N][5];
  for (int n = 0; n < N; n++) {
    int cardNumA = sc.nextInt();
    for (int a = 0; a < cardNumA; a++) {
      int shape = sc.nextInt();
      A[n][shape]++;
    }
    int cardNumB = sc.nextInt();
    for (int b = 0; b < cardNumB; b++) {
      int shape = sc.nextInt();
      B[n][shape]++;
    }

    // 스테이지마다 겨루기
    boolean flag = false;
    for (int i = 4; i >= 1; i--) {
      flag = false;
      if (A[n][i] > B[n][i]) {
        System.out.println("A");
        flag = true;
        break;
      }
      else if (A[n][i] < B[n][i]) {
        System.out.println("B");
        flag = true;
        break;
      }
    }
    if (!flag) System.out.println("D");
  }

  sc.close();
  }
}
