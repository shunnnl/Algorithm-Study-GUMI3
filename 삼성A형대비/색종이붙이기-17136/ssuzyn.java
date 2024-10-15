import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리 : 22616 KB
 * 시간 : 200 ms
 */
public class 색종이붙이기_17136 {

    static int answer = Integer.MAX_VALUE;
    static int[] type = {0, 5, 5, 5, 5, 5}; // 각 크기별 색종이 사용 가능 개수
    static int[][] paper = new int[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void dfs(int x, int y, int usedPapers) {
        if (usedPapers >= answer) return;

        // 종이를 전부 다 덮은 경우
        if (x >= 10) {
            answer = Math.min(answer, usedPapers);
            return;
        }

        // 다음 행으로 이동
        if (y >= 10) {
            dfs(x + 1, 0, usedPapers);
            return;
        }

        // 이미 덮인 곳이면 다음 칸으로 이동
        if (paper[x][y] == 0) {
            dfs(x, y + 1, usedPapers);
            return;
        }

        // 1을 덮을 수 있는 색종이를 큰 것부터 시도
        for (int size = 5; size >= 1; size--) {
            if (canPlacePaper(x, y, size)) {
                placePaper(x, y, size, 0); // 색종이 붙이기
                type[size]--;

                dfs(x, y + 1, usedPapers + 1); // 다음 칸으로

                placePaper(x, y, size, 1); // 색종이 제거
                type[size]++;
            }
        }
    }

    // 해당 위치에 size 크기의 색종이를 붙일 수 있는지 확인
    private static boolean canPlacePaper(int x, int y, int size) {
        if (type[size] == 0) return false; // 해당 크기의 색종이를 더 이상 사용할 수 없는 경우

        if (x + size > 10 || y + size > 10) return false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (paper[x + i][y + j] == 0) return false;
            }
        }

        return true;
    }

    // 색종이를 붙이거나 떼는 함수 (flag가 0이면 붙이고, 1이면 떼는 역할)
    private static void placePaper(int x, int y, int size, int flag) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                paper[x + i][y + j] = flag;
            }
        }
    }
}