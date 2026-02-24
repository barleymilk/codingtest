// https://www.acmicpc.net/workbook/view/9901
package 알고리즘수업;

import java.io.*;
import java.util.*;

public class Q24446_너비우선탐색3 {
    static int N, M, R;
    static ArrayList<Integer>[] adj;
    static int[] visitedDepth;
    static int depth = 0;

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
            // 무방향 그래프
            adj[u].add(v);
            adj[v].add(u);
        }

        visitedDepth = new int[N + 1];
        Arrays.fill(visitedDepth, -1);

        bfs(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visitedDepth[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visitedDepth[start] = 0; // 깊이 0 입력됨

        while(!q.isEmpty()) {
            int curr = q.poll();

            for (int neighbor: adj[curr]) {
                // neighbor 다 돌 때까지 똑같은 깊이 입력해야 함.
                if (visitedDepth[neighbor] == -1) {
                    visitedDepth[neighbor] = visitedDepth[curr] + 1;
                    q.offer(neighbor);
                }
            }
        }
    }
}
