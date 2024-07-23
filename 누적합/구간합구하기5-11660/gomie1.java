import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); // 표의 크기
        int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수

        // 누적합 배열 생성 및 계산
        int[][] mtrx = new int[N+1][N+1];
        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++) {
                int value = Integer.parseInt(st.nextToken());
                mtrx[i][j] = mtrx[i-1][j] + mtrx[i][j-1] - mtrx[i-1][j-1] + value;
            }
        }

        int x1, x2, y1, y2, res;
        for(int i = 0; i < M; i++) {
            res = 0;

            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            res = mtrx[x2][y2] - mtrx[x2][y1-1] - mtrx[x1-1][y2] + mtrx[x1-1][y1-1];
            sb.append(res + "\n");
        }
        System.out.println(sb);
    }
}