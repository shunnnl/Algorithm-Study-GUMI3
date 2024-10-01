import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 넴모넴모_14712 {

    static int N, M, answer = 0;
    static boolean[][] picked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        picked = new boolean[N+1][M+1];

        batch(0);

        System.out.println(answer);

    }

    private static void batch(int cnt){

        if(cnt == N * M) {
            answer++;
            return;
        }

        int x = cnt / M + 1;
        int y = cnt % M + 1;

        // 2*2 직사각형 만들 수 있는 경우 (x, y) 좌표는 선택하지 않는다.
        if(picked[x-1][y] && picked[x][y-1] && picked[x-1][y-1]){
            batch(cnt + 1);
        }
        else{
            picked[x][y] = true;
            batch(cnt + 1);
            picked[x][y] = false;
            batch(cnt + 1);
        }
    }

}
