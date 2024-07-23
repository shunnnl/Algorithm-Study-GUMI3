import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/***
 * 백준 11660 구간 함 구하기
 *
 */

public class boj1749 {
    //public class Main {
    static long maxSum = Integer.MIN_VALUE;	// 0으로 하면 안됌...

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int [][] board = new int[n+1][m+1];		// 입력 받을 배열
        int [][] Sboard = new int[n+1][m+1];	// 누적합 배열

        // 배열 입력 받기
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < m + 1; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 누적합 배열 만들기
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                Sboard[i][j] = Sboard[i][j-1] + Sboard[i-1][j] - Sboard[i-1][j-1] + board[i][j];
//				System.out.print(Sboard[i][j] + " ");
            }
//			System.out.println();
        }
        // 부분 수열 브루투 포스로 최대값 계산
        for (int x = 1; x < n + 1; x++) {
            for (int y = 1; y < m + 1; y++) {
                for (int i = x; i < n + 1; i++) {
                    for (int j = y; j < m + 1; j++) {
                        maxCount(x, y, i, j, Sboard);
                    }
                }
            }
        }
        System.out.println(maxSum);
    }

    // 부분 배열 최대값 찾는 함수
    public static void maxCount(int x, int y, int i, int j, int [][] Sboard) {
        long temp = Sboard[i][j] - Sboard[x-1][j] - Sboard[i][y-1] + Sboard[x-1][y-1];
        if (temp > maxSum) {
//			System.out.printf("x = %d, y = %d, i = %d, j = %d\nmaxsum = %d\nSboard[i][j] = %d - Sboard[x-1][j] = %d - Sboard[i][y-1] = %d + Sboard[x-1][y-1] = %d\n", x, y, i, j, temp, Sboard[i][j], Sboard[x-1][j], Sboard[i][y-1], Sboard[x-1][y-1]);
            maxSum = temp;
        }
    }
}
