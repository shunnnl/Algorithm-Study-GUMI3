package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제: 색종이 붙이기 (B17136)
 * 풀이: DFS + 백트래킹
 * 메모리: 26256kb
 * 시간: 360ms
 */
public class B17136 {

    int[][] map = new int[10][10];
    int[] paperCount = new int[5];
    int result = Integer.MAX_VALUE;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.fill(paperCount, 5);

        dfs(0, 0, 0);

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }

        System.out.println(result);
    }

    private void dfs(int y, int x, int count) {

        if (x == 10 && y == 9) {
            result = Math.min(result, count);
            return;
        }

        // 가로를 끝까지 갔다면 아래 줄로 이동
        if (x > 9) {
            dfs(y + 1, 0, count);
            return;
        }

        // 색종이를 붙여야 된다면
        if (map[y][x] == 1) {
            // 큰 사이즈의 색종이부터 붙인다
            for (int i = 4; i >= 0; i--) {
                // 붙이려는 색종이가 남아있고
                if (paperCount[i] > 0) {
                    // 해당 색종이를 붙일 수 있다면
                    if (isAttach(y, x, i + 1)) {
                        attachPaper(y, x, i + 1); // 색종이 붙이고
                        paperCount[i]--; // 해당 색종이 수 감소
                        dfs(y, x + 1, count + 1);
                        paperCount[i]++; // 색종이 수 복구
                        detachPaper(y, x, i + 1); // 색종이 다시 떼기
                    }
                }
            }
        }
        // 색종이를 붙일 수 없다면
        else  {
            dfs(y, x + 1, count);
        }
    }

    private boolean isAttach(int y, int x, int size) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (i > 9 || j > 9 || map[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void attachPaper(int y, int x, int size) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                map[i][j] = 0;
            }
        }
    }

    private void detachPaper(int y, int x, int size) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                map[i][j] = 1;
            }
        }
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B17136().solution();
//    }
//}
