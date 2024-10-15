package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 야구 (B17281)
 * 풀이: 순열 + 구현
 * 메모리: 62520kb
 * 시간: 492ms
 */
public class B17281 {

    int N;
    int[][] player;
    int[] select;
    boolean[] visit;
    int result;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        player = new int[N][10];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        select = new int[10];
        visit = new boolean[10];
        select[4] = 1;
        visit[4] = true;
        dfs(2);

        System.out.println(result);
    }

    private void dfs(int count) {

        if (count == 10) { // 선수 배치 완료
            result = Math.max(result, playGame());
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (!visit[i]) {
                visit[i] = true;
                select[i] = count;
                dfs(count + 1);
                visit[i] = false;
            }
        }
    }

    // 0 : home
    // 1 : 1루
    // 2 : 2루
    // 3 : 3루
    boolean[] ground;

    private int playGame() {
        int score = 0;
        int cur = 1;
        // 이닝 수 만큼 게임 반복
        for (int inning = 0; inning < N; inning++) {

            int outCount = 0;
            ground = new boolean[4];
            // 아웃될때까지 무한반복
            while (outCount < 3) {

                // 선수가 친 공
                int swing = player[inning][select[cur++]];

                // 9번 타자까지 공을 쳤다면 1번타자로 초기화
                if (cur == 10) {
                    cur = cur % 9;
                }

                // 아웃
                if (swing == 0) {
                    outCount++;
                }

                // 1루 진출
                if (swing == 1) {

                    // 3루
                    if (ground[3]) {
                        score++;
                        ground[3] = false;
                    }

                    // 2루
                    if (ground[2]) {
                        ground[3] = true;
                        ground[2] = false;
                    }

                    // 1루
                    if (ground[1]) {
                        ground[2] = true;
                        ground[1] = false;
                    }

                    ground[1] = true;
                }

                // 2루 진출
                if (swing == 2) {

                    // 3루
                    if (ground[3]) {
                        score++;
                        ground[3] = false;
                    }

                    // 2루
                    if (ground[2]) {
                        score++;
                        ground[2] = false;
                    }

                    // 1루
                    if (ground[1]) {
                        ground[3] = true;
                        ground[1] = false;
                    }

                    ground[2] = true;
                }

                // 3루 진출
                if (swing == 3) {

                    // 3루
                    if (ground[3]) {
                        score++;
                        ground[3] = false;
                    }

                    // 2루
                    if (ground[2]) {
                        score++;
                        ground[2] = false;
                    }

                    // 1루
                    if (ground[1]) {
                        score++;
                        ground[1] = false;
                    }

                    ground[3] = true;
                }

                // 홈런
                if (swing == 4) {

                    // 3루
                    if (ground[3]) {
                        score++;
                        ground[3] = false;
                    }

                    // 2루
                    if (ground[2]) {
                        score++;
                        ground[2] = false;
                    }

                    // 1루
                    if (ground[1]) {
                        score++;
                        ground[1] = false;
                    }

                    score++;
                }
            }
        }

        return score;
    }


}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B17281().solution();
//    }
//}
