import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리 : 17392 KB
 * 시간 : 188 ms
 */
public class ssuzyn {

    static int N, count;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 1); // 1 : 가로, 2 : 세로, 3 : 대각선

        System.out.println(count);
    }

    private static void dfs(int x, int y, int status) {
        if (x == N - 1 && y == N - 1) {
            count++;
            return;
        }

        // 수평으로 이동 가능
        if (status == 1 || status == 3) {
            if (y + 1 < N && map[x][y + 1] != 1)
                dfs(x, y + 1, 1);
        }

        // 수직으로 이동 가능
        if (status == 2 || status == 3) {
            if (x + 1 < N && map[x + 1][y] != 1)
                dfs(x + 1, y, 2);
        }

        // 대각선으로 이동 가능
        if (x + 1 < N && y + 1 < N && map[x + 1][y + 1] != 1 && map[x][y + 1] != 1 && map[x + 1][y] != 1) {
            dfs(x + 1, y + 1, 3);
        }

    }

}
