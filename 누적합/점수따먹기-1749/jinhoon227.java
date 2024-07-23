package ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 점수따먹기 (B1749)
 * 풀이: 누적합
 * 메모리: 20276kb
 * 시간: 868ms
 */
public class B1749 {

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 누적합으로 만들어진 2차원 배열을 만든다.
        int[][] square = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                int cur = Integer.parseInt(st.nextToken());
                // x축 이전 좌표값 + y축 이전 좌표값 - x,y 축이전 좌표값(x축 이전 좌표값과 y축 이전 좌표값이 겹치는 부분) + 현재좌표값 으로 누적합을 구한다.
                square[i][j] = square[i - 1][j] + square[i][j - 1] + cur - square[i - 1][j - 1];
            }
        }

        // 모든 두 점 사이의 누적합에서 최대값을 찾는다
        int result = square[1][1];
        for (int y2 = 1; y2 <= n; y2++) {
            for (int x2 = 1; x2 <= m; x2++) {

                for (int y1 = 1; y1 <= y2; y1++) {
                    for (int x1 = 1; x1 <= x2; x1++) {
                        // 누적합 좌표에서 (x2, y2) 에서 왼쪽부분(y2,x1 - 1) 과 위쪽부분(y1 - 1, x2) 을 빼주고 왼쪽부분과 위쪽부분이 서로 겹치는 좌표(x1 - 1)(y1 - 1) 을 더해준다.
                        int sum = square[y2][x2] - square[y2][x1 - 1] - square[y1 - 1][x2] + square[y1 - 1][x1 - 1];
                        result = Math.max(result, sum);
                    }
                }
            }
        }

        System.out.println(result);
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B1749().solution();
//    }
//}
