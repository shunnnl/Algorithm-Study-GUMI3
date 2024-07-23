package ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구간 합 더하기 5
 * 1차 시도 Brute Fore : 시간초과
 * 2차 시도 누적합 알로리즘
 * 주의점 : 2차원 누접합 알고리즘 사용할때 중복되는 값 더해주기
 * 삽질 : x 가 행, y 가 열로 일반적으로 반대여서 삽질함
 */
public class B11660 {

    public void solution() throws IOException {

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 누적합으로 만들어진 2차원 배열을 만든다.
        int[][] square = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int cur = Integer.parseInt(st.nextToken());
                // x축 이전 좌표값 + y축 이전 좌표값 - x,y 축이전 좌표값(x축 이전 좌표값과 y축 이전 좌표값이 겹치는 부분) + 현재좌표값 으로 누적합을 구한다.
                square[i][j] = square[i - 1][j] + square[i][j - 1] + cur - square[i - 1][j - 1];
            }
        }

        StringBuilder result = new StringBuilder();

        // 두 점 사이의 누적합의 차를 구한다.
        for (int test = 0; test < m; test++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 누적합 좌표에서 (x2, y2) 에서 왼쪽부분(x1 - 1,y2) 과 위쪽부분(x2, y2-1) 을 빼주고 왼쪽부분과 위쪽부분이 서로 겹치는 좌표(x1 - 1)(y1 - 1) 을 더해준다.
            int sum = square[x2][y2] - square[x1 - 1][y2] - square[x2][y1 - 1] + square[x1 - 1][y1 - 1];

            result.append(sum).append("\n");
        }

        System.out.println(result);
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B11660().solution();
//    }
//}
