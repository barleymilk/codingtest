package ssstudy;

import java.io.*;
import java.util.*;

public class B4485_녹색옷입은애가젤다지 {
  static int N, minCost;
  static int[][] board, dist;
  static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input_b4485.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tc = 0;

    while(true) {
      String line = br.readLine();
      if (line == null) break; // 파일 끝 체크
 
      N = Integer.parseInt(line);
      if (N == 0) break;
      tc++;

      board = new int[N][N];
      StringTokenizer st;
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine().trim());
        for (int j = 0; j < N; j++) {
          board[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      minCost = Integer.MAX_VALUE;
      dijkstra();

      System.out.println("Problem " + tc + ": " + minCost);
    }
  }

  public static class Node implements Comparable<Node>{
    int x, y, cost;

    Node (int x, int y, int cost) {
      this.x = x;
      this.y = y;
      this.cost = cost;
    }

    @Override 
    public int compareTo(Node o) {
      return Integer.compare(this.cost, o.cost); // 오름차순
    }
  }

  // 비용이 다른 최단 거리 -> 다익스트라 ( BFS + PriorityQueue )
  static void dijkstra() {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(0, 0, board[0][0]));
    
    dist = new int[N][N];
    for (int i = 0; i < N; i++) {
      Arrays.fill(dist[i], Integer.MAX_VALUE);
    }
    dist[0][0] = board[0][0];

    while(!pq.isEmpty()) {
      Node curr = pq.poll();
      int x = curr.x, y = curr.y, cost = curr.cost;

      // 목적지 도착한 경우 -> 리턴
      if (x == N-1 && y == N - 1) {
        minCost = cost;
        return;
      }

      // 경로가 같은데 비용이 더 큰 경우 -> 통과
      if (cost > dist[x][y]) continue;

      // 사방 탐색
      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        // 범위를 벗어난 곳이라면 통과
        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

        if (cost + board[nx][ny] < dist[nx][ny]) {
          dist[nx][ny] = cost + board[nx][ny];
          pq.offer(new Node(nx, ny, dist[nx][ny]));
        }
      }
    }
  }
}
