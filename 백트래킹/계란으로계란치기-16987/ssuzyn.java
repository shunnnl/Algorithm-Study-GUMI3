import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리 : 18664 KB
 * 시간 : 244 ms
 */
public class 계란으로계란치기_16987 {

    static int N, answer = 0;
    static int[][] egg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 계란 개수
        egg = new int[N][2];

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            egg[i][0] = Integer.parseInt(st.nextToken()); // 내구도
            egg[i][1] = Integer.parseInt(st.nextToken()); // 무게
        }
        solve(0);
        System.out.println(answer);
    }

    private static void solve(int turn) {
        if(turn == N){
            int cnt = 0;
            for(int i = 0; i < N; i++){
                if(egg[i][0] <= 0) cnt++;
            }
            answer = Math.max(cnt, answer);
            return;
        }

        if(egg[turn][0] <= 0) { // 현재 들고 있는 계란이 깨진 경우
            solve(turn + 1);
            return;
        }

        boolean brokenEgg = false;
        for(int i = 0; i < N; i++){
            if (i == turn || egg[i][0] <= 0) continue; // 자신이나 깨진 계란 제외

            brokenEgg = true;
            egg[turn][0] -= egg[i][1];
            egg[i][0] -= egg[turn][1];

            solve(turn + 1);

            egg[turn][0] += egg[i][1];
            egg[i][0] += egg[turn][1];
        }
        if(!brokenEgg) // 깨지지 않은 다른 계란이 없을 경우
            solve(turn + 1);
    }
}
