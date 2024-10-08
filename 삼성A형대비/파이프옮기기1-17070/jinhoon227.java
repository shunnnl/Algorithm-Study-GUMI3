package sol.jinhoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제: 파이프 옮기기(B17070)
 * 풀이 : dp
 * 메모리: 14320kb
 * 시간: 120ms
 */
public class B17070 {

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // dp[][][] 첫번째요소 y좌표, 두번째요소 x좌표, 3번째요소 파이프상태(가로, 세로, 대각). dp[1][2][0] 이면 (1,2) 에 파이프가 가로로 놓였을때 최대 가지 수
        // 0 : 가로
        // 1 : 세로
        // 2 : 대각
        int[][][] dp = new int[N + 1][N + 1][3];
        dp[1][2][0] = 1; // 첫번째 파이프 세팅
        for (int i = 1; i <= N; i++) {
            for (int j = 3; j <= N; j++) { // 앞에 2칸은 사용 못하므로 3부터 시작
                if (map[i][j] == 0) {
                    dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2]; // 가로 : 이전에 가로로 배치된 파이프 + 이전에 대각선으로 배치된 파이프
                    dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2]; // 세로 : 이전에 세로로 배치된 파이프 + 이전에 대각선으로 배치된 파이프

                    // 대각선으로 배치될 경우 주변도 벽이 없어야 한다
                    if (map[i - 1][j] == 0 && map[i][j - 1] == 0) {
                        // 대각선 : 이전에 가로로 배치된 파이프 + 이전에 세로로 배치된 파이프 + 이전에 대각선으로 배치된 파이프
                        dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                    }
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write((dp[N][N][0] + dp[N][N][1] + dp[N][N][2]) + "\n");
        bw.flush();
        bw.close();
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B17070().solution();
//    }
//}
