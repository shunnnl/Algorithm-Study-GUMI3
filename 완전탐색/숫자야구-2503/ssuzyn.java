package 완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 2503. 숫자야구
 * 메모리: 14376 KB
 * 시간: 116 ms
 */
public class 숫자야구_2503 {

    static int N;
    static int[][] quest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 질문 횟수
        quest = new int[N][3];
        int answer = 0;

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            quest[i][0] = Integer.parseInt(st.nextToken()); // 숫자
            quest[i][1] = Integer.parseInt(st.nextToken()); // 스트라이크 갯수
            quest[i][2] = Integer.parseInt(st.nextToken()); // 볼 갯수
        }

        // 123 ~ 987 사이의 숫자 중에서 모든 질문에 적합한 숫자 찾기
        for(int i = 1; i <= 9; i++) {
            for(int j = 1; j <= 9; j++) {
                if(i == j) continue;
                for(int k = 1; k <= 9; k++) {
                    if(i == k || j == k) continue;
                    if(solve(i, j, k)) answer++;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean solve(int n1, int n2, int n3) {

        for(int q = 0; q < N; q++) {
            int strike = 0;
            int ball = 0;

            int first = quest[q][0] / 100; // 첫번째 자리
            int second = (quest[q][0] / 10) % 10; // 두번째 자리
            int third = quest[q][0] % 10; // 세번째 자리

            if(n1 == first) strike++;
            if(n2 == second) strike++;
            if(n3 == third) strike++;

            if(n1 == second || n1 == third) ball++;
            if(n2 == first || n2 == third) ball++;
            if(n3 == first || n3 == second) ball++;


            if(strike != quest[q][1] || ball != quest[q][2])
                return false;
        }
        return true;
    }
}
