package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 : 파이프 옮기기 1
 * 풀이 : dfs (완전탐색)
 * 메모리 : 20112KB
 * 시간 : 204ms
 */

public class _17070_파이프옮기기1 {
    private static int N, map[][], res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 집의 크기

        // 집의 상태 입력받기
        StringTokenizer st;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = 0;
        dfs(0, 1, 0);
        System.out.println(res);
    }

    private static void dfs(int x, int y, int dir) {
        if(x == N-1 && y == N-1) { // 파이프의 한쪽 끝이 (N, N)에 도달했다면
            res++; // 방법의 수를 1 증가 후 종료
            return;
        }

        switch(dir) {
            case 0: // 현재 파이프 방향이 가로인 경우
                if(y+1 < N && map[x][y+1] == 0) { // 1. 가로로 이동
                    dfs(x, y+1, 0);
                }
                // 2. 대각선으로 이동
                break;
            case 1: // 현재 파이프 방향이 세로인 경우
                if(x+1 < N && map[x+1][y] == 0) { // 1. 세로로 이동
                    dfs(x+1, y, 1);
                }
                // 2. 대각선으로 이동
                break;
            case 2: // 현재 파이프 방향이 대각선인 경우
                if(y+1 < N && map[x][y+1] == 0) { // 1. 가로로 이동
                    dfs(x, y+1, 0);
                }

                if(x+1 < N && map[x+1][y] == 0) { // 2. 세로로 이동
                    dfs(x+1, y, 1);
                }
                // 3. 대각선으로 이동
                break;
        }

        // 대각선으로 이동하는 것은 모든 case에서 공통으로 하므로 따로 빼줌
        if(x+1 < N && y+1 < N && map[x][y+1] == 0 && map[x+1][y] == 0 && map[x+1][y+1] == 0) {
            dfs(x+1, y+1, 2);
        }
    }
}
