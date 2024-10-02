package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제: N-Queen (B9663)
 * 풀이: 백트래킹
 * 메모리: 14472kb
 * 시간: 5700øms
 */
public class B9663 {

    int N;
    int[] col;
    long result;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        col = new int[N + 1];

        dfs(1);

        System.out.println(result);
    }

    private void dfs(int cnt) {

        // 이전에 배치된 퀸이 서로 공격안하는지 체크
        int preCnt = cnt - 1;
        for (int i = 1; i < preCnt; i++) {
            // 서로 같은 열에 배치되어있지 않아야 한다.
            if (col[preCnt] == col[i]) {
                return;
            }

            // 서로 같은 대각선에 배치되어있지 않아야 한다.
            if (preCnt - i == Math.abs(col[preCnt] - col[i])) {
                return;
            }
        }

        if (cnt == N + 1) { // 배치 끝
            result++;
            return;
        }

        // 각 열에 대해 모든 행에 퀸을 배치
        for (int i = 1; i <= N; i++) {
            col[cnt] = i;
            dfs(cnt + 1);
        }
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B9663().solution();
//    }
//}
