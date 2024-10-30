package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 16236. 아기 상어
 * 메모리 : 25192 KB
 * 시간 : 188 ms
 */
public class 아기상어_16236 {

    static int N;
    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    static int[][] map;
    static PriorityQueue<Fish> pq;

    static class Fish implements Comparable<Fish> {
        int x, y, dist;

        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            if(this.dist != o.dist) return this.dist - o.dist;
            else{
                if(this.x == o.x) return this.y - o.y;
                else return this.x - o.x;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 공간의 크기
        map = new int[N][N];

        Queue<Fish> q = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    map[i][j] = 0;
                    q.add(new Fish(i, j, 0));
                }
            }
        }

        int sharkSize = 2;
        int full = 0;
        int time = 0;
        bfs(q, sharkSize);

        while(!pq.isEmpty()) {
            Fish fish = pq.poll();
            map[fish.x][fish.y] = 0;

            if(++full == sharkSize){
                full = 0;
                sharkSize++;
            }

            time += fish.dist;
            q = new LinkedList<>();
            q.offer(new Fish(fish.x, fish.y, 0));
            bfs(q, sharkSize);
        }

        System.out.println(time);
    }

    private static void bfs(Queue<Fish> q, int sharkSize) {
        pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        while(!q.isEmpty()) {
            Fish fish = q.poll();

            for(int[] dir : d){
                int nx = fish.x + dir[0];
                int ny = fish.y + dir[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N
                                || map[nx][ny] > sharkSize || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.offer(new Fish(nx, ny, fish.dist + 1));

                if(map[nx][ny] != 0 && map[nx][ny] < sharkSize){
                    pq.offer(new Fish(nx, ny, fish.dist + 1));
                }
            }
        }
    }
}
