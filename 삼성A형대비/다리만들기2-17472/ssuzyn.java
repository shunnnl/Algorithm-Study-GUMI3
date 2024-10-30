package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 17472. 다리 만들기 2
 * 메모리 : 14348 KB
 * 시간 : 108 ms
 */
public class 다리만들기2_17472 {
    static int N, M, islandCnt;
    static int[][] map;
    static boolean[][] visited;
    static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static PriorityQueue<Bridge> pq = new PriorityQueue<>();;
    static int[] parent;

    static class Bridge implements Comparable<Bridge> {
        int start, end, length;

        Bridge(int start, int end, int length){
            this.start = start;
            this.end = end;
            this.length = length;
        }

        @Override
        public int compareTo(Bridge o) {
            return Integer.compare(this.length, o.length);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 가로
        M = Integer.parseInt(st.nextToken()); // 세로
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 섬 구분하기
        islandCnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    setIsland(i, j, islandCnt++);
                }
            }
        }

        // 2. 건설할 수 있는 다리 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) makeBridge(i, j, map[i][j]);
            }
        }

        // 3. MST - 크루스칼 : 모든 섬 잇기
        setParent();
        int answer = shortestPath();
        System.out.println(answer == 0? -1 : answer);

    }

    // 1. 섬 구분하기
    private static void setIsland(int x, int y, int group) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { x, y });
        map[x][y] = group;
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            x = cur[0];
            y = cur[1];

            for (int[] dir : d) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;

                if (map[nx][ny] == 1) {
                    map[nx][ny] = group;
                    visited[nx][ny] = true;
                    q.add(new int[] { nx, ny });
                }
            }
        }
    }

    // 2. 건설할 수 있는 다리 구하기
    private static void makeBridge(int startX, int startY, int group) {
        visited = new boolean[N][M];

        for (int dir = 0; dir < 4; dir++) {
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] {startX, startY, 0});
            visited[startX][startY] = true;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                int depth = cur[2];

                int nx = x + d[dir][0];
                int ny = y + d[dir][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) break;

                if(map[nx][ny] != group){
                    if(map[nx][ny] != 0){ // 다른 섬인 경우
                        if(depth >= 2){
                            pq.add(new Bridge(group, map[nx][ny], depth));
                            break;
                        }
                    }
                    else{ // 바다인 경우 계속 탐색
                        visited[nx][ny] = true;
                        q.add(new int[] { nx, ny, depth + 1 });
                    }
                }
            }
        }
    }

    // 3. MST - 크루스칼 : 모든 섬 잇기
    private static void setParent(){
        parent = new int[islandCnt];
        for(int i = 1; i < islandCnt; i++) {
            parent[i] = i;
        }
    }

    private static int findParent(int x) {
        if(parent[x] == x) return x;
        return parent[x] = findParent(parent[x]);
    }

    private static void union(int a, int b) {
        int aRoot = findParent(a);
        int bRoot = findParent(b);

        if(aRoot == bRoot) return;
        parent[bRoot] = aRoot;
    }

    private static int shortestPath() {
        int sum = 0;

        while(!pq.isEmpty()) {
            Bridge tmp = pq.poll();
            if(findParent(tmp.start) != findParent(tmp.end)) {
                sum += tmp.length;
                union(tmp.start, tmp.end);
            }
        }

        // 4. 모든 섬이 연결되어 있는지 확인
        int root = findParent(1);
        for(int i = 2; i < islandCnt; i++){
            if(root != findParent(i)) return 0;
        }
        return sum;
    }

}
