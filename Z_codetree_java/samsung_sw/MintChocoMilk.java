// https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/mint-choco-milk/description
package codetree_java.samsung_sw;

import java.io.*;
import java.util.*;

public class MintChocoMilk {
    static int N, T;
    static int[][] food;
    static int[][] faith;
    static boolean[][] blocked;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static final int MINT = 1, CHOCO = 2, MILK = 4;

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Group implements Comparable<Group> {
        int headR, headC, headFaith;

        @Override
        public int compareTo(Group o) {
            int p1 = getFoodPriority(food[this.headR][this.headC]);
            int p2 = getFoodPriority(food[o.headR][o.headC]);

            if (p1 != p2) return p1 - p2; // 음식 우선
            if (this.headFaith != o.headFaith) return o.headFaith - this.headFaith;
            if (this.headR != o.headR) return this.headR - o.headR;
            return this.headC - o.headC;
        }
    }

    static int getFoodPriority(int f) {
        int cnt = Integer.bitCount(f);
        if (cnt == 1) return 0;
        if (cnt == 2) return 1;
        return 2;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        food = new int[N][N];
        faith = new int[N][N];

        // 음식 입력
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = line.charAt(j);
                if (c == 'T') food[i][j] = MINT;
                else if (c == 'C') food[i][j] = CHOCO;
                else food[i][j] = MILK;
            }
        }

        // 신앙심 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                faith[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int day = 0; day < T; day++) {
            // 1. 아침
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    faith[i][j]++;
                }
            }

            // 2. 점심 (그룹 형성)
            List<Group> groups = new ArrayList<>();
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        makeGroup(i, j, visited, groups);
                    }
                }
            }

            // 3. 저녁
            Collections.sort(groups);
            blocked = new boolean[N][N];

            for (Group g : groups) {
                if (blocked[g.headR][g.headC]) continue;
                spread(g);
            }

            // 결과 출력
            int tcm = 0, tc = 0, tm = 0, cm = 0, m = 0, c = 0, t = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int f = food[i][j];
                    int val = faith[i][j];

                    if (f == 7) tcm += val;
                    else if (f == 3) tc += val;
                    else if (f == 5) tm += val;
                    else if (f == 6) cm += val;
                    else if (f == 4) m += val;
                    else if (f == 2) c += val;
                    else if (f == 1) t += val;
                }
            }

            System.out.println(tcm + " " + tc + " " + tm + " " + cm + " " + m + " " + c + " " + t);
        }
    }

    static void makeGroup(int sr, int sc, boolean[][] visited, List<Group> groups) {
        Queue<Point> q = new LinkedList<>();
        List<Point> list = new ArrayList<>();

        q.offer(new Point(sr, sc));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            list.add(cur);

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                if (!visited[nr][nc] && food[cur.r][cur.c] == food[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }
            }
        }

        // 대표자 선정
        list.sort((a, b) -> {
            if (faith[a.r][a.c] != faith[b.r][b.c])
                return faith[b.r][b.c] - faith[a.r][a.c];
            if (a.r != b.r) return a.r - b.r;
            return a.c - b.c;
        });

        Point head = list.get(0);

        // 신앙심 이동
        for (int i = 1; i < list.size(); i++) {
            Point p = list.get(i);
            faith[p.r][p.c]--;
            faith[head.r][head.c]++;
        }

        Group g = new Group();
        g.headR = head.r;
        g.headC = head.c;
        g.headFaith = faith[head.r][head.c];

        groups.add(g);
    }

    static void spread(Group g) {
        int r = g.headR;
        int c = g.headC;

        int curFaith = faith[r][c];
        int curFood = food[r][c];

        int d = curFaith % 4;
        int x = curFaith - 1;

        faith[r][c] = 1;

        while (x > 0) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;

            if (food[nr][nc] == curFood) {
                r = nr;
                c = nc;
                continue;
            }

            int y = faith[nr][nc];

            // 방어 상태 처리
            blocked[nr][nc] = true;

            if (x > y) { // 강한 전파
                x -= (y + 1);
                food[nr][nc] = curFood;
                faith[nr][nc]++;

                r = nr;
                c = nc;
            } else { // 약한 전파
                food[nr][nc] |= curFood;
                faith[nr][nc] += x;
                x = 0;
            }
        }
    }
}