// https://www.acmicpc.net/workbook/view/9901
package 알고리즘수업;

import java.io.*;
import java.util.*;

public class B24447_너비우선탐색4 {
    static int N, M, R;
    static int[] visitedOrder, visitedDepth;
    static ArrayList<Integer>[] adj;
    static int count = 1; // 방문 순서

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        for (int i = 1; i <= N; i++) Collections.sort(adj[i]); // 오름차순 정렬

        visitedOrder = new int[N + 1];
        visitedDepth = new int[N + 1];
        Arrays.fill(visitedDepth, -1);
        
        bfs(R);

        long result = 0; // long 타입!
        for (int i = 1; i <= N; i++) {
            result += (long)visitedOrder[i] * visitedDepth[i];
        }
        System.out.println(result);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visitedOrder[start] = count++;
        visitedDepth[start] = 0;

        while(!q.isEmpty()) {
            int curr = q.poll();
            for (int neighbor: adj[curr]) {
                // 방문한 적 없는 정점일 때 
                if (visitedDepth[neighbor] == -1) {
                    q.offer(neighbor);
                    visitedOrder[neighbor] = count++;
                    visitedDepth[neighbor] = visitedDepth[curr] + 1;
                }
            }
        }
    }
}
