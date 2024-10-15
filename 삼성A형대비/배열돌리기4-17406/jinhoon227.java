package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 배열 돌리기 (B17406)
 * 풀이: 구현 + 순열
 * 메모리: 60764kb
 * 시간: 480ms
 */
public class B17406 {

    // 수행할 연산 저장용 객체
    static class Operator {
        int r;
        int c;
        int s;

        public Operator(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    int N, M, K;
    int[][] map;
    int result;
    boolean[] visit;
    Operator[] operators;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        operators = new Operator[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            operators[i] = new Operator(
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken())
            );
        }
        // == 입력 끝 == //

        result = Integer.MAX_VALUE;
        visit = new boolean[K];
        dfs(0);
        System.out.println(result);
    }

    // 순열
    private void dfs(int count) {

        if (count == K) {

            // 각 행들의 합 중에서 가장 최소가 되는 값을 찾고 업데이트
            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < M; j++) {
                    sum += map[i][j];
                }

                if (result > sum) {
                    result = sum;
                }
            }
        }

        for (int n = 0; n < K; n++) {
            if (!visit[n]) {
                visit[n] = true;
                int[][] temp = new int[N][M]; // 임시 배열에 맵 정보를 저장
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        temp[i][j] = map[i][j];
                    }
                }

                rotate(operators[n].r, operators[n].c, operators[n].s); // 시계 방향 으로 배열 돌리기
                dfs(count + 1);

                for (int i = 0; i < N; i++) { // 맵 배열을 원상복구
                    for (int j = 0; j < M; j++) {
                        map[i][j] = temp[i][j];
                    }
                }
                visit[n] = false;
            }
        }
    }

    // 시계 방향으로 배열 돌리기
    private void rotate(int r, int c, int s) {
        int startX = c - s;
        int startY = r - s;
        int endX = c + s;
        int endY = r + s;
        int rCount = Math.min(endX - startX, endY - startY) / 2;
        for (int i = 0; i < rCount; i++) {

            int temp = map[i + startY][i + startX]; // 사각형 왼쪽 위 값 저장

            // 왼쪽
            for (int j = startY + i; j < endY - i; j++) {
                map[j][startX + i] = map[j + 1][startX + i];
            }

            // 아래쪽
            for (int j = startX + i; j < endX - i; j++) {
                map[endY - i][j] = map[endY - i][j + 1];
            }

            // 오른쪽
            for (int j = endY - i; j > i + startY; j--) {
                map[j][endX - i] = map[j - 1][endX - i];
            }

            // 위쪽
            for (int j = endX - i; j > i + startX; j--) {
                map[startY + i][j] = map[startY + i][j - 1];
            }

            map[i + startY][i + 1 + startX] = temp;
        }
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B17406().solution();
//    }
//}
