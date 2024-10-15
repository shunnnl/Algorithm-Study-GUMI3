import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리: 27012 KB
 * 시간: 272 ms
 */
public class 배열돌리기4_17406 {

    static int N, M, K, answer;
    static int[][] map;
    static int[][] op;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 회전 연산의 개수

        map = new int[N+1][M+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        op = new int[K][3];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                op[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        int[] turn = new int[K];
        Arrays.fill(turn, -1);
        pickTurn(0, turn);
        System.out.println(answer);

    }

    private static void pickTurn(int cnt, int[] turn){
        if(cnt == K){
            int[][] arr = initArray();
            for(int i = 0; i < K; i++){
                arr = rotation(op[turn[i]][0],op[turn[i]][1],op[turn[i]][2], arr);
            }
            getMinValue(arr);
            return;
        }

        for(int i = 0; i < K; i++){
            if(turn[i] == -1){
                turn[i] = cnt;
                pickTurn(cnt+1, turn);
                turn[i] = -1;
            }
        }
    }

    private static int[][] initArray(){
        int[][] copy = new int[N+1][M+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    private static int[][] rotation(int r, int c, int s, int[][] arr) {
        // 회전 범위 설정
        for (int layer = s; layer >= 1; layer--) {
            int x1 = r - layer, y1 = c - layer; // 좌상단
            int x2 = r + layer, y2 = c + layer; // 우하단

            if(x1 < 1 || y1 < 1 || x2 > N || y2 > M) continue;

            int temp = arr[x1][y1];

            // left
            for (int i = x1; i < x2; i++) arr[i][y1] = arr[i + 1][y1];
            // bottom
            for (int i = y1; i < y2; i++) arr[x2][i] = arr[x2][i + 1];
            // right
            for (int i = x2; i > x1; i--) arr[i][y2] = arr[i - 1][y2];
            // top
            for (int i = y2; i > y1 + 1; i--) arr[x1][i] = arr[x1][i - 1];

            arr[x1][y1 + 1] = temp;
        }

        return arr;
    }

    private static void getMinValue(int[][] arr){
        for(int i = 1; i <= N; i++){
            int sum = 0;
            for(int j = 1; j <= M; j++){
                sum += arr[i][j];
            }
            answer = Math.min(answer, sum);
        }
    }
}
