package sswork.pastquestions;

import java.io.*;
import java.util.*;

public class Q17471_게리맨더링 {
  static int N, minDiff;
  static int[] populations;
  static ArrayList<Integer>[] adj;
  static boolean[] selected;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    N = Integer.parseInt(br.readLine());
    populations = new int[N + 1];
    
    // 인구수 입력
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      populations[i] = Integer.parseInt(st.nextToken());
    }

    // 인접 리스트 입력
    adj = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
      st = new StringTokenizer(br.readLine());
      int count = Integer.parseInt(st.nextToken()); // 첫 번째 숫자는 인접 구역의 개수
      for (int j = 0; j < count; j++) {
        adj[i].add(Integer.parseInt(st.nextToken()));
      }
    }

    minDiff = Integer.MAX_VALUE;
    selected = new boolean[N + 1];
    dfs(1);
    
    System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
  }

  static void dfs(int idx) {
    if (idx == N + 1) {
      List<Integer> groupA = new ArrayList<>();
      List<Integer> groupB = new ArrayList<>();
      for (int i = 1; i <= N; i++) {
        if (selected[i]) groupA.add(i);
        else groupB.add(i);
      }

      if (groupA.isEmpty() || groupB.isEmpty()) return;

      if (bfs(groupA) && bfs(groupB)) calc(groupA, groupB);

      return;
    }

    // 분기 1) A 선거구 선택
    selected[idx] = true;
    dfs(idx+1);

    // 분기 2) B 선거구 선택
    selected[idx] = false;
    dfs(idx+1);
  }

  static boolean bfs(List<Integer> list) {
    Queue<Integer> q = new LinkedList<>();
    boolean[] visited = new boolean[N + 1];

    int start = list.get(0);
    q.add(start);
    visited[start] = true;

    int count = 1; // 방문한 구역 수
    
    while(!q.isEmpty()) {
      int curr = q.poll();

      for (int neighbor: adj[curr]) {
        if (list.contains(neighbor) && !visited[neighbor]) {
          visited[neighbor] = true;
          q.add(neighbor);
          count++;
        }
      }
    }

    return count == list.size();
  }

  static void calc(List<Integer> groupA, List<Integer> groupB) {
    int sumA = 0;
    for (int i : groupA) sumA += populations[i];

    int sumB = 0;
    for (int i : groupB) sumB += populations[i];

    minDiff = Math.min(minDiff, Math.abs(sumA - sumB));
  }
}
