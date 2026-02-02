package sswork.imclassproblem;

import java.io.FileInputStream;
import java.util.Scanner;

public class Q1244_스위치켜고끄기 {
  public static void main(String[] args) throws Exception {
    // https://www.acmicpc.net/problem/1244
    System.setIn(new FileInputStream("res/input.txt"));
    Scanner sc = new Scanner(System.in);

    // 스위치 8개 (1: 켜진 상태, 0: 꺼진 상태)
    // 남학생은 스위치 번호가 자기가 받은 수의 배수이면, 그 스위치의 상태를 바꾼다.
    // 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치의 상태를 모두 바꾼다.

    int N = sc.nextInt();
    int[] onOff = new int[N];
    for (int i = 0; i < N; i++) onOff[i] = sc.nextInt();

    int studentNum = sc.nextInt();
    int[][] toDo = new int[studentNum][2]; 
    for (int i = 0; i < studentNum; i++) {
      for (int j = 0; j < 2; j++) {
        toDo[i][j] = sc.nextInt();
      }
    }

    for (int i = 0; i < studentNum; i++) {
      if (toDo[i][0] == 1) {
        // 남학생 (배수)
        for (int j = 1; j <= N; j++) {
          // 스위치 순회(1번부터 N번까지)
          if (j % toDo[i][1] == 0) {
            onOff[j - 1] = 1 - onOff[j - 1];
          }
      }}
      else {
        // 여학생 (데칼코마니)
        int point = toDo[i][1];
        onOff[point - 1] = 1 - onOff[point - 1];

        int leftPoint = point - 1;
        int rightPoint = point + 1;

        // 좌우 대칭 검사 루프
        while (leftPoint >= 1 && rightPoint <= N) {
          if (onOff[leftPoint - 1] == onOff[rightPoint - 1]) {
            onOff[leftPoint - 1] = 1 - onOff[leftPoint - 1];
            onOff[rightPoint - 1] = 1 - onOff[rightPoint - 1];
            leftPoint--;
            rightPoint++;
          }
          else break;
        }
      }
    }

    // 스위치는 한 줄에 20개까지만 출력 
    StringBuilder sb = new StringBuilder();
    for (int k = 0; k < N; k++) {
      sb.append(onOff[k]).append(" ");

      // 20번째마다 줄바꿈
      if ((k + 1) % 20 == 0) sb.append("\n");
    }
    System.out.println(sb);
    sc.close();
  }
}
