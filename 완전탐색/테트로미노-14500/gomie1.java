package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 : 테트로미노
 * 메모리 : 33964KB
 * 시간 : 552ms
 */

public class _14500_테트로미노 {

    static int N, M, res=Integer.MIN_VALUE;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 크기
        M = Integer.parseInt(st.nextToken()); // 가로 크기

        // 종이에 값 입력 받기
        paper = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, paper[i][j]);
                visited[i][j] = false;
                others(i, j);
            }
        }
        System.out.println(res);
    }

    private static void dfs(int x, int y, int cnt, int sum) {
        if(cnt > 4) {
            return;
        }

        // 상, 하, 좌, 우로 움직여서 4칸을 만들었기 때문에 무조건 블록!
        if(cnt == 4) {
            //System.out.println("여기 드렁옴" + sum);
            res = Math.max(res, sum); // 최대값 찾기
            return;
        }

        for(int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx >= N || ny >= M || nx < 0 || ny < 0) continue;

            if(!visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, cnt+1, sum+paper[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    // 범위 똑바로 하기
    private static void others(int x, int y) {
        if(x+1 < N && y-1 >= 0 && y+1 < M){
            res = Math.max(res, paper[x][y] + paper[x+1][y] + paper[x][y-1] + paper[x][y+1]);
        }

        if(x+1 < N && x-1 >= 0 && y+1 < M){
            res = Math.max(res, paper[x][y] + paper[x+1][y] + paper[x-1][y] + paper[x][y+1]);
        }

        if(x+1 < N && x-1 >= 0 && y-1 >= 0){
            res = Math.max(res, paper[x][y] + paper[x+1][y] + paper[x-1][y] + paper[x][y-1]);
        }

        if(x-1 >= 0 && y-1 >= 0 && y+1 < M){
            res = Math.max(res, paper[x][y] + paper[x-1][y] + paper[x][y-1] + paper[x][y+1]);
        }
    }
}