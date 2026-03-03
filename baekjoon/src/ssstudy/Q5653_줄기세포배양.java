package ssstudy;

import java.io.*;
import java.util.*;

class Cell implements Comparable<Cell> {
  int r, c;   // 좌표
  int x;      // 생명력 (불변)
  int life;   // 남은 시간
  int status; // 0: 비활성, 1: 활성, 2: 죽음

  public Cell(int r, int c, int x) {
    this.r = r;
    this.c = c;
    this.x = x;
    this.life = x; // 생성 시 X시간 대기 필요
    this.status = 0; // 초기 상태: 비활성
  }
  
  @Override
  public int compareTo(Cell o) {
    // 생명력(x)이 큰 순서대로 우선순위를 갖게 함 (내림차순 정렬)
    return o.x - this.x; 
  }
}

public class Q5653_줄기세포배양 {
  static int N, M, K;
  static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
  static boolean[][] visited;
  static ArrayList<Cell> allCells;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    
    for (int tc = 1; tc <= T; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      visited = new boolean[N + 2 * K][M + 2 * K];
      allCells = new ArrayList<>();

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++) {
          int x = Integer.parseInt(st.nextToken());
          if (x > 0) {
            int r = i + K;
            int c = j + K;
            allCells.add(new Cell(r, c, x));
            visited[r][c] = true;
          }
        }
      }

      // K시간 배양 시작
      for (int i = 0; i < K; i++) {
        PriorityQueue<Cell> breedQueue = new PriorityQueue<>();
        
        // 1. 세포 상태 변화 및 번식 후보 선별
        for (Cell cell : allCells) {
          if (cell.status == 2) continue; // 죽은 세포는 통과
          
          if (cell.status == 0) {
            if (--cell.life == 0) cell.status = 1; // 활성으로 변경
          }
          else if (cell.status == 1) {
            if (cell.life == 0) {
              for (int d = 0; d < 4; d++) {
                int nr = cell.r + dr[d];
                int nc = cell.c + dc[d];
                if (!visited[nr][nc]) {
                  breedQueue.add(new Cell(nr, nc, cell.x));
                }
              }
            }
            cell.life++;
            if (cell.life == cell.x) cell.status = 2; // 세포 죽음
          }
        }

        // 2. 번식 진행 (생명력 높은 세포가 찜하기)
        while (!breedQueue.isEmpty()) {
          Cell child = breedQueue.poll();
          if (!visited[child.r][child.c]) {
            visited[child.r][child.c] = true;
            allCells.add(child);
          }
        }
      }

      // 3. 살아 있는 세포(status: 0, 1) 개수 세기
      int count = 0;
      for (Cell cell : allCells) {
        if (cell.status != 2) count++;
      }

      System.out.println("#" + tc + " " + count);
    }
  }
}