package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 : 기차가 어둠을 헤치고 은하수를
 * 메모리 : 57432KB
 * 시간 : 552ms
 */

public class _15787_기차가어둠을헤치고은하수를 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 기차의 수
        int M = Integer.parseInt(st.nextToken()); // 명령의 수

        // N개의 모든 기차에 20번 좌석까지 생성
        boolean[][] train = new boolean[N+1][21];

        // M개의 명령 실행
        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 명령 번호
            int i = Integer.parseInt(st.nextToken()); // 기차 번호
            int x = 0;
            if(n < 3) {
                x = Integer.parseInt(st.nextToken()); // 좌석 번호
            }

            switch (n) {
                case 1: // x번째 좌석에 사람 태우기
                    if(!train[i][x]) train[i][x] = true;
                    break;
                case 2: // x번째 좌석에 앉은 사람 하차
                    if(train[i][x]) train[i][x] = false;
                    break;
                case 3: // 승객 모두 한 칸씩 뒤로 이동
                    for (int k = 19; k >= 0 ; k--) { // 0까지 반복하며 false인 0번째 값을 1번째에 넣어줌으로써 맨 앞자리 비워주기
                        train[i][k+1] = train[i][k];
                    }
                    break;
                default: // 승객 모두 한 칸씩 앞으로 이동
                    for (int k = 1; k < 20; k++) {
                        train[i][k] = train[i][k+1];
                    }
                    train[i][20] = false; // 맨 뒷자리 비워주기
                    break;
            }
        }

        ArrayList<boolean[]> arr = new ArrayList<>();
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            int flag = 0;
            for(boolean[] v : arr) {
                if(Arrays.equals(train[i], v)) { // 기차 좌석 중복 체크
                    break;
                }
                flag++;
            }

            // arr에 i번째 기차의 좌석 정보와 같은 배열이 없다면
            // flag와 arr.size()는 동일함
            if(flag == arr.size()) {
                arr.add(train[i]);
                cnt++;
            }
        }

        int res = -1;
        if(cnt != 0) res = cnt;
        System.out.println(res);
    }
}