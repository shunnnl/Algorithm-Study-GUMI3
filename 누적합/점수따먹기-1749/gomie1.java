import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행렬의 크기 입력 받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 누적합 배열 생성 및 초기화
        int[][] mtrx = new int[N+1][M+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                int value = Integer.parseInt(st.nextToken());
                mtrx[i][j] = mtrx[i-1][j] + mtrx[i][j-1] - mtrx[i-1][j-1] + value;
            }
        }

        // 누적합 배열을 활용한 부분행렬의 합 구하기
        int res = Integer.MIN_VALUE;
        for(int x1 = 1; x1 <= N; x1++) {
            for(int y1 = 1; y1 <= M; y1++) {
                for(int x2 = x1; x2 <= N; x2++) {
                    for(int y2 = y1; y2 <= M; y2++) {
                        int value = mtrx[x2][y2] - mtrx[x1-1][y2] - mtrx[x2][y1-1] + mtrx[x1-1][y1-1];
                        res = Math.max(res, value); // 최대값 찾기
                    }
                }
            }
        }
        System.out.println(res);
    }
}