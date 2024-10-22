import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 : 다리 만들기
 * 풀이 : bfs
 * 메모리 : 138,144kb
 * 시간 : 268ms
 */

public class _2146_다리만들기 {
    static int N, map[][], res;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 지도의 크기

        // 지도 입력 받기
        StringTokenizer st;
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 섬마다 번호 부여하기
        visited = new boolean[N][N];
        int val = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j] == 1) {
                    checkIsland(i, j, val);
                    val++;
                }
            }
        }

        // 다리 만들기
        res = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] > 0) {
                    visited = new boolean[N][N];
                    bfs(i, j, map[i][j]);
                }
            }
        }

        System.out.println(res);
    }

    static void checkIsland(int start_x, int start_y, int num) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {start_x, start_y});
        visited[start_x][start_y] = true;
        map[start_x][start_y] = num;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 0 || visited[nx][ny]) continue;

                queue.offer(new int[] {nx, ny});
                visited[nx][ny] = true;
                map[nx][ny] = num;
            }
        }
    }

    static void bfs(int start_x, int start_y, int num) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {start_x, start_y, 0});
        visited[start_x][start_y] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cnt = cur[2];

            if(map[cur[0]][cur[1]] > 0 && map[cur[0]][cur[1]] != num) {
                if(res > cnt-1) res = cnt-1;
            }

            if(cnt > res) return;

            for(int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == num || visited[nx][ny]) continue;

                queue.offer(new int[] {nx, ny, cur[2]+1});
                visited[nx][ny] = true;
            }
        }
    }
}
