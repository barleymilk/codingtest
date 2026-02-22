package 알고리즘수업;

import java.io.*;
import java.util.*;

public class Q24444_너비우선탐색1 {
  static int N, M, R;
  static ArrayList<Integer>[] adj;
  static int[] visitedOrder; // 방문 순서를 저장할 배열
  static int count = 1; // 순서 카운트

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // 정점의 수
    M = Integer.parseInt(st.nextToken()); // 간선의 수
    R = Integer.parseInt(st.nextToken()); // 시작 정점

    adj = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      // 무방향 그래프이므로 양쪽에 추가
      adj[u].add(v);
      adj[v].add(u);
    }

    // 1. 각 인접 리스트 오름차순 정렬
    for (int i = 1; i <= N; i++) {
      Collections.sort(adj[i]);
    }

    visitedOrder = new int[N + 1];
    bfs(R);

    // 결과 출력
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= N; i++) {
      sb.append(visitedOrder[i]).append("\n");
    }
    System.out.println(sb);
  }

  static void bfs(int start) {
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    visitedOrder[start] = count++; // 시작 정점 방문 순서 기록

    while(!q.isEmpty()) {
      int curr = q.poll();

      for (int neighbor : adj[curr]) {
        if (visitedOrder[neighbor] == 0) { // 아직 방문하지 않았다면
          visitedOrder[neighbor] = count++; // 순서 기록
          q.offer(neighbor);
        }
      }
    }
  }
}
