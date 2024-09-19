package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 궁금한 민호(B1507)
 * 풀이: 플로이드워샬
 * 메모리: 14344kb
 * 시간: 104ms
 */
public class B1507 {

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] dist = new int[N + 1][N + 1];
        int[][] map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = dist[i][j];
            }
        }

        final int INF = 100_000_000;
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {

                    if (i == j || i == k || k == j) { // 같은 점이면 패스
                        continue;
                    }

                    if (dist[i][j] > dist[i][k] + dist[k][j]) { // 만들어질 수 없는 플로이드 워샬이
                        System.out.println("-1");
                        return;
                    }

                    // 거쳐갔으면 i->j 간선을 없앰
                    if (dist[i][j] == dist[i][k] + dist[k][j]) {
                        map[i][j] = INF;
                        map[j][i] = INF;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] != INF && i != j) {
                    ans += map[i][j];
                }
            }
        }

        System.out.println(ans / 2);
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B1507().solution();
//    }
//}
