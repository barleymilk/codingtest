package sswork.imclassproblem;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q10157_자리배정 {
  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);
    
    int C = sc.nextInt();
    int R = sc.nextInt();
    int K = sc.nextInt(); // 어떤 관객의 대기번호
    int[][] theater = new int[R][C];

    if (K > C * R) System.out.println(0);
    else if (K == 1) {
      System.out.println("1 1");
    }
    else {
      // 하, 우, 상, 좌
      int[] dx = {1, 0, -1, 0};
      int[] dy = {0, 1, 0, -1};

      int direction = 0;
      int[] now = {0, 0};
      theater[0][0] = 1;
      for (int i = 2; i <= K; i++) {
        int tempX = now[0] + dx[direction];
        int tempY = now[1] + dy[direction];

        if (tempX < 0 || tempX >= R || tempY < 0 || tempY >= C || theater[tempX][tempY] != 0 || theater[tempX][tempY] != 0) {
          // 벽을 만나거나 값을 만나면 direction 조정
          direction = (direction + 1) % 4;
          tempX = now[0] + dx[direction];
          tempY = now[1] + dy[direction];
        }

        theater[tempX][tempY] = i;
        now[0] = tempX;
        now[1] = tempY;
        if (i == K) {
          System.out.println((tempY + 1) + " " + (tempX + 1));
          break;
        }
      }
    }

    sc.close();
  }
}
