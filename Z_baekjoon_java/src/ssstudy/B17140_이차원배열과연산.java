import java.io.*;
import java.util.*;

public class B17140_이차원배열과연산 {
  static int R, C, K;
  static int[][] A = new int[101][101]; // 1-based index 사용, 최대 100x100

  static class Cell implements Comparable<Cell> {
    int num, cnt;
    public Cell(int num, int cnt) {
      this.num = num;
      this.cnt = cnt;
    }

    @Override
    public int compareTo(Cell o) {
      // 1. 빈도수(cnt) 오름차순
      if (this.cnt != o.cnt) return this.cnt - o.cnt;
      // 2. 숫자(num) 오름차순
      return this.num - o.num;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    for (int i = 1; i <= 3; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= 3; j++) {
        A[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int rLen = 3;
    int cLen = 3;

    // 0초부터 100초까지 확인 (100초가 지난 후에도 확인해야 하므로 <= 100)
    for (int time = 0; time <= 100; time++) {
      // 목표 값 도달 확인
      if (R <= 100 && C <= 100 && A[R][C] == K) {
        System.out.println(time);
        return;
      }

      if (rLen >= cLen) {
        cLen = R_Operation(rLen, cLen);
      } else {
        rLen = C_Operation(rLen, cLen);
      }
    }

    // 100초를 넘기면 -1
    System.out.println(-1);
  }

  // 모든 행에 대해 정렬 수행
  static int R_Operation(int rLen, int cLen) {
    int maxColumn = 0;
    for (int i = 1; i <= rLen; i++) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int j = 1; j <= cLen; j++) {
        if (A[i][j] == 0) continue;
        map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
        A[i][j] = 0; // 연산 전 해당 행 초기화
      }

      PriorityQueue<Cell> pq = new PriorityQueue<>();
      for (int key : map.keySet()) {
        pq.add(new Cell(key, map.get(key)));
      }

      int idx = 1;
      while (!pq.isEmpty() && idx <= 100) {
        Cell curr = pq.poll();
        A[i][idx++] = curr.num;
        A[i][idx++] = curr.cnt;
      }
      // 이번 행의 길이를 기록하여 전체 최대 열 길이 갱신
      maxColumn = Math.max(maxColumn, idx - 1);
    }
    return maxColumn;
  }

  // 모든 열에 대해 정렬 수행
  static int C_Operation(int rLen, int cLen) {
    int maxRow = 0;
    for (int j = 1; j <= cLen; j++) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 1; i <= rLen; i++) {
        if (A[i][j] == 0) continue;
        map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
        A[i][j] = 0; // 연산 전 해당 열 초기화
      }

      PriorityQueue<Cell> pq = new PriorityQueue<>();
      for (int key : map.keySet()) {
        pq.add(new Cell(key, map.get(key)));
      }

      int idx = 1;
      while (!pq.isEmpty() && idx <= 100) {
        Cell curr = pq.poll();
        A[idx++][j] = curr.num;
        A[idx++][j] = curr.cnt;
      }
      // 이번 열의 길이를 기록하여 전체 최대 행 길이 갱신
      maxRow = Math.max(maxRow, idx - 1);
    }
    return maxRow;
  }
}