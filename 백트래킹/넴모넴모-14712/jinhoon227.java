package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 넴모넴모 (B14712)
 * 풀이: 백트래킹
 * 메모리: 18112kb
 * 시간: 1260ms
 */
public class B14712 {

    int N, M;
    boolean[][] visit;
    long result;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[N][M];

        dfs(0, 0);

        System.out.println(result);
    }

    private void dfs(int row, int col) {

        if (row == N) { // 행이 마지막 다음줄 이라면
            // 2x2 를 만드는 넴모들이 있는지 확인
            for (int i = 0; i < N - 1; i++) {
                for (int j = 0; j < M - 1; j++) {
                    if (visit[i][j] && visit[i][j + 1] && visit[i + 1][j] && visit[i + 1][j + 1]) {
                        return;
                    }
                }
            }

            result++;
            return;
        }

        int nextCol = (col + 1) % M;
        int nextRow = row;
        if(nextCol == 0) { // nextCol 이 0 이면 열이 끝까지 도달하고 왔다는 뜻이므로 행값을 +1
            nextRow++;
        }

        visit[row][col] = true; // 넴모 배치
        dfs(nextRow, nextCol);

        visit[row][col] = false; // 넴모 미배치
        dfs(nextRow, nextCol);
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B14712().solution();
//    }
//}
