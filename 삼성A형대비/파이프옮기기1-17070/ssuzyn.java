import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리 : 30808 KB
 * 시간 : 468 ms
 */
public class Main {

    static int N, count;
    static int[][] map;
    static boolean[][] visited;

    private static int getDirection(int diffX, int diffY) {
        if (diffX == 0 && diffY == 1)
            return 0; // 가로 방향
        else if (diffX == 1 && diffY == 0)
            return 1; // 세로 방향
        else if (diffX == 1 && diffY == 1)
            return 2; // 대각선 방향

        return -1;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private static void moveHorizontal(int x1, int y1, int x2, int y2) {
        x1 = x2;
        y1 = y2;
        x2 = x2 + 0;
        y2 = y2 + 1;

        if (isValid(x2, y2) && !visited[x2][y2] && map[x2][y2] == 0) {
            visited[x2][y2] = true;
            dfs(x1, y1, x2, y2);
            visited[x2][y2] = false;
        }

    }

    private static void moveVertical(int x1, int y1, int x2, int y2) {
        x1 = x2;
        y1 = y2;
        x2 = x2 + 1;
        y2 = y2 + 0;

        if (isValid(x2, y2) && !visited[x2][y2] && map[x2][y2] == 0) {
            visited[x2][y2] = true;
            dfs(x1, y1, x2, y2);
            visited[x2][y2] = false;
        }

    }

    private static void moveDiagonal(int x1, int y1, int x2, int y2) {
        x1 = x2;
        y1 = y2;
        x2 = x2 + 1;
        y2 = y2 + 1;

        if (isValid(x2, y2) && !visited[x2][y2] && map[x2][y2] == 0 && map[x2 - 1][y2] == 0 && map[x2][y2 - 1] == 0) {
            visited[x2][y2] = true;
            dfs(x1, y1, x2, y2);
            visited[x2][y2] = false;
        }

    }

    private static void dfs(int x1, int y1, int x2, int y2) {
        if (x2 == N - 1 && y2 == N - 1)
            count++;

        int flag = getDirection(x2 - x1, y2 - y1);

        switch (flag) {
            case 0:
                moveHorizontal(x1, y1, x2, y2);
                moveDiagonal(x1, y1, x2, y2);
                break;
            case 1:
                moveVertical(x1, y1, x2, y2);
                moveDiagonal(x1, y1, x2, y2);
                break;
            case 2:
                moveHorizontal(x1, y1, x2, y2);
                moveVertical(x1, y1, x2, y2);
                moveDiagonal(x1, y1, x2, y2);
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0, 1);

        System.out.println(count);
    }

}
