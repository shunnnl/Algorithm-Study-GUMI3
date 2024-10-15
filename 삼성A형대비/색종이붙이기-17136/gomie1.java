import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 : 색종이 붙이기
 * 풀이 : dfs, 구현
 * 메모리 : 24,088kb
 * 시간 : 332ms
 */

public class Main {
    private static int arr[][], paper[], res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[10][10];
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 종류의 색종이는 5개씩 가지고 있음
        paper = new int[6];
        Arrays.fill(paper, 5);

        res = Integer.MAX_VALUE;
        dfs(0, 0, 0);

        if(res == Integer.MAX_VALUE) res = -1;
        System.out.println(res);
    }
    private static void dfs(int x, int y, int cnt) {
        if(x >= 9 && y > 9) { // 맨 끝 위치까지 왔다면 종료
            if(res > cnt) res = cnt;
            return;
        }

        if(res <= cnt) return; // 최소값 가지치기

        if(y > 9) {
            dfs(x+1, 0, cnt); // 아래줄로 이동
            return;
        }

        // 5x5 ~ 1x1까지의 색종이를 현재 위치에 붙일 수 있는지 확인
        // -> 큰 색종이부터 확인하는 것이 효율적!
        if(arr[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (paper[i] > 0 && isPossible(x, y, i)) {
                    attach(x, y, i, 0); // 색종이 붙이기
                    paper[i]--; // ixi 크기의 색종이 개수 1 감소
                    dfs(x, y + 1, cnt + 1);
                    attach(x, y, i, 1); // 색종이 떼기
                    paper[i]++;
                }
            }
        }
        else { // 오른쪽으로 이동
            dfs(x, y+1, cnt);
        }
    }

    // 현재 위치(x, y)에 크기가 size x size인 색종이를 붙일 수 있는지 확인하는 함수
    private static boolean isPossible(int x, int y, int size) {
        int cnt = 0;
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if(i < 0 || i >= 10 || j < 0 || j >= 10) return false; // 좌표가 범위를 넘어가면 false
                if(arr[i][j] == 1) cnt++; // 현재 색종이 범위안의 1 개수 카운팅
            }
        }

        if(cnt == size*size) return true; // 색종이 범위안의 숫자가 모두 1이면 true
        else return false; // 하나라도 0이 있다면 false
    }

    private static void attach(int x, int y, int size, int status) {
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size ; j++) {
                arr[i][j] = status;
            }
        }
    }
}