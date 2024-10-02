import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제 : N-Queen
 * 풀이 : 백트래킹
 * 메모리 : 14816KB
 * 시간 : 2488ms
 */

public class _9663 {

    static int N, board[][], res;
    static boolean[] v1, v2, v3;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 체스판의 크기

        // 체스판의 크기가 1이라면 퀸을 놓을 수 있는 방법이 1개 뿐이므로 1출력 후 종료
        if(N == 1) {
            System.out.println(1);
            return;
        }

        v1 = new boolean[N]; // 가로 체크
        v2 = new boolean[N*2]; // 왼쪽 대각선 체크
        v3 = new boolean[N*2]; // 오른쪽 대각선 체크

        board = new int[N][N]; // 체스판
        res = 0;
        dfs(0);

        System.out.println(res);
    }

    private static void dfs(int cnt) {
        if(cnt == N) { // 모든 행에 퀸을 놓는 것이 가능했다면
            res++; // 경우의 수 1 증가 후 종료
            return;
        }

        for(int i = 0; i < N; i++) {
            if(v1[i] == false && v2[cnt+i] == false && v3[cnt-i+(N-1)] == false) {
                v1[i] = true;
                v2[cnt+i] = true;
                v3[cnt-i+(N-1)] = true;

                dfs(cnt+1);

                v1[i] = false;
                v2[cnt+i] = false;
                v3[cnt-i+(N-1)] = false;
            }
        }
    }
}