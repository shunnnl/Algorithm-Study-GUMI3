import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리: 63712 KB
 * 시간: 524 ms
 */
public class 야구_17281 {

    static int N, maxScore = Integer.MIN_VALUE;
    static int[] turn = new int[9 + 1];
    static int[][] playerInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        playerInfo = new int[N][9+1];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++) {
                playerInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        turn[4] = 1; // 4번 타자로 1번 선수
        setTurn(2);
        System.out.println(maxScore);
    }

    private static void setTurn(int idx) {
        if (idx == 10) {
            int score = playBall();
            maxScore = Math.max(maxScore, score);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (turn[i] != 0 || i == 4) continue;  // 이미 해당 자리에 숫자가 있으면 건너뜀

            turn[i] = idx;
            setTurn(idx + 1);
            turn[i] = 0;
        }
    }

    private static int playBall(){
        int score = 0;
        int start = 1; // 타순 시작

        for(int inning = 0; inning < N; inning++){
            boolean[] base = new boolean[4]; // 매 이닝마다 1루, 2루, 3루 초기화
            int[] player = playerInfo[inning];
            int out = 0; // 아웃 횟수

            while(out < 3){
                int hit = player[turn[start]];

                if(hit == 0) {
                    out++;
                }
                else if(hit == 4){
                    score += calcScore(base, hit);
                    score++; // 홈런 친 선수 득점
                }
                else{
                    score += calcScore(base, hit);
                    base[hit] = true;
                }

                start = (start % 9) + 1;
            }
        }

        return score;
    }

    private static int calcScore(boolean[] base, int hit){
        int score = 0;

        for(int b = 3; b > 0; b--){
            if(base[b]){
                if(b + hit > 3) score++;
                else base[b + hit] = true;

                base[b] = false; // 원래 베이스 비우기
            }
        }
        return score;
    }
}
