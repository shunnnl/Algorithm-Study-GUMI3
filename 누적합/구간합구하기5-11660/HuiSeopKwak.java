import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/***
 * 백준 11660 구간 함 구하기
 *
 */

public class boj11660 {
//public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int [][] board = new int[n+1][n+1];
        int [][] Sboard = new int[n+1][n+1];
//		int [][] Cboard = new int[m+1][n+1];	// 최대 10만 개의 m이 만들어져서 선언하는 것이 아니라 받아올때 계산하는 방식으로 변경

        // 배열 입력 받기
        for (int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n+1; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
//				System.out.print(board[i][j] + " ");
            }
//			System.out.println();
        }
        // 배열 누적합 계산
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                Sboard[i][j] = Sboard[i][j-1] + Sboard[i-1][j] - Sboard[i-1][j-1] + board[i][j];
//				System.out.print(Sboard[i][j] + " ");
            }
//			System.out.println();
        }

        // 검사할 좌표 입력받기
        for (int i = 1; i < m+1; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int sum = Sboard[x2][y2] - Sboard[x1-1][y2] - Sboard[x2][y1-1] + Sboard[x1-1][y1-1];
            System.out.println(sum);
        }

    }

}