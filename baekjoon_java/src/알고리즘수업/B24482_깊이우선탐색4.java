// https://www.acmicpc.net/workbook/view/9901
package 알고리즘수업;

import java.io.*;
import java.util.*;

public class B24482_깊이우선탐색4 {
    static int N, M, R;
    static ArrayList<Integer>[] adj;
    static int[] visitedDepth;
    
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
        for (int i = 1; i <= N; i++) Collections.sort(adj[i], Collections.reverseOrder()); // 내림차순 정렬

        visitedDepth = new int[N + 1];
        Arrays.fill(visitedDepth, -1);

        dfs(R, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visitedDepth[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int curr, int depth) {
        visitedDepth[curr] = depth;
        for (int neighbor: adj[curr]) {
            if (visitedDepth[neighbor] == -1) {
                dfs(neighbor, depth + 1);
            }
        }
    }
}
