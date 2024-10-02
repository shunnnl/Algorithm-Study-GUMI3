import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 : 넴모넴모 (Easy)
 * 풀이 : 백트래킹
 * 메모리 : 18132KB
 * 시간 : 1188ms
 */

public class _14712 {

    static int N, M, res;
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행의 개수
        M = Integer.parseInt(st.nextToken()); // 열의 개수

        board = new boolean[N][M];
        res = 0;
        dfs(0, 0);
        System.out.println(res);
    }

    private static void dfs(int row, int col) {
        if(row == N) { // 종료 조건
            // 2중 for문으로 2x2 정사각형을 발견하면 즉시 종료
            for(int i = 0; i < N-1; i++) {
                for(int j = 0; j < M-1; j++) {
                    if(board[i][j] && board[i][j+1] && board[i+1][j] && board[i+1][j+1]) {
                        return;
                    }
                }
            }

            res++; // 2x2 정사각형을 발견하지 못했다면 경우의 수 1 증가
            return;
        }

        int nc = col, nr = row;
        if(col+1 < M) {
            nc++;
        }
        else {
            nc = 0;
            nr++;
        }

        board[row][col] = true;
        dfs(nr, nc);

        board[row][col] = false;
        dfs(nr, nc);
    }
}