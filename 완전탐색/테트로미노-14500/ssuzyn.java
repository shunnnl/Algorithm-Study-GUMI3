package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14500. 테트로미노
 * 메모리: 37860 KB
 * 시간: 664 ms
 */
public class 테트로미노_14500 {

    static int N, M, max;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        max = Integer.MIN_VALUE;
        N = Integer.parseInt(st.nextToken()); // 세로 크기
        M = Integer.parseInt(st.nextToken()); // 가로 크기
        paper = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, paper[i][j]);
                visited[i][j] = false;

                combi(i, j, 0, 0, paper[i][j]);
            }
        }

        System.out.println(max);

    }

    public static void combi(int x, int y, int depth, int start, int sum) { // ㅓ, ㅜ, ㅏ, ㅗ 테트로미노
        if(depth == 3) {
            max = Math.max(max, sum);
            return;
        }


        for(int i = start; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                combi(x, y, depth + 1, i + 1, sum + paper[nx][ny]);
            }
        }
    }

    public static void dfs(int x, int y, int depth, int sum) {
        if(depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + paper[nx][ny]);
                visited[nx][ny] = false;

            }
        }
    }

}

