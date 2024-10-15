import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 : 배열 돌리기 4
 * 풀이 : 순열, 구현
 * 메모리 : 31,824kb
 * 시간 : 344ms
 */

public class Main {
    private static int N, M, K, A[][], info[][], isSelected[], res;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 배열의 행 크기
        M = Integer.parseInt(st.nextToken()); // 배열의 열 크기
        K = Integer.parseInt(st.nextToken()); // 회전 연산의 개수

        A = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        info = new int[K][3];
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken()) - 1;
            info[i][1] = Integer.parseInt(st.nextToken()) - 1;
            info[i][2] = Integer.parseInt(st.nextToken());
        }

        isSelected = new int[K];
        visited = new boolean[K];
        res = Integer.MAX_VALUE;
        permutation(0);

        System.out.println(res);
    }

    // (r-s, c-s) ~ (r+s, c+s)인 정사각형을 시계 방향으로 한 칸씩 회전
    private static void rotate(int[] select) {
        // 1. 배열 복사
        int[][] copyA = new int[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                copyA[i][j] = A[i][j];
            }
        }

        // 2. 회전
        for(int i = 0; i < K; i++) {
            int r = info[isSelected[i]][0];
            int c = info[isSelected[i]][1];
            int info_s = info[isSelected[i]][2];

            // 안쪽 사각형부터 회전
            for(int s = 1; s <= info_s; s++) {
                // 위
                int upTmp = copyA[r-s][c+s]; // [2][5]
                for(int y = c+s; y > c-s; y--) { // y: 5 ~ 4
                    copyA[r-s][y] = copyA[r-s][y-1]; // [2][5] <- [2][4], [2][4] <- [2][3]
                }

                // 오른쪽
                int rightTmp = copyA[r+s][c+s]; // [4][5]
                for(int x = r+s; x > r-s+1; x--) { // x: 4
                    copyA[x][c+s] = copyA[x-1][c+s]; // [4][5] <- [3][5]
                }
                copyA[r-s+1][c+s] = upTmp; // [3][5] <- [2][5]

                // 아래
                int downTmp = copyA[r+s][c-s]; // [4][3]
                for(int y = c-s; y < c+s-1; y++) { // y: 3
                    copyA[r+s][y] = copyA[r+s][y+1]; // [4][3] <- [4][4]
                }
                copyA[r+s][c+s-1] = rightTmp; // [4][4] <- [4][5]

                // 왼쪽
                for(int x = r-s; x < r+s-1; x++) { // x: 2
                    copyA[x][c-s] = copyA[x+1][c-s]; // [2][3] <- [3][3]
                }
                copyA[r+s-1][c-s] = downTmp; // [3][3] <- [4][3]
            }
        }

        // 3.최소값 산출
        for(int x = 0; x < N; x++) {
            int sum = 0;
            for(int y = 0; y < M; y++) {
                sum += copyA[x][y];
            }
            if(res > sum) res = sum;
        }
    }

    // 연산을 수행한 순서에 따라 최종 배열이 달라짐 => 순서가 유의미하므로 순열 사용!!
    private static void permutation(int cnt) {
        if(cnt == K) {
            rotate(isSelected);
            return;
        }

        for(int i = 0; i < K; i++) {
            if(!visited[i]) {
                visited[i] = true;
                isSelected[cnt] = i;
                permutation(cnt+1);
                visited[i] = false;
            }
        }
    }
}