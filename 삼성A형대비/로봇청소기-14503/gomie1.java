import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 : 로봇 청소기
 * 풀이 : 구현
 * 메모리 : 14,400kb
 * 시간 : 104ms
 */

public class _14503_로봇청소기 {
    static int N, M, arr[][], x, y, d, res;

    // 0: 북, 1: 동, 2: 남, 3: 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arr = new int[N][M]; // 0: 청소되지 않은 빈 칸, 1: 벽
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = 0;
        sol();
        System.out.println(res);
    }

    static void sol() {
        while(true){
            // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소 (0 -> -1)
            if(arr[x][y] == 0) {
                res++; // 청소한 칸 개수 증가
                arr[x][y] = -1; // 청소한 칸임을 표시하기 위해 -1로 변경
            }

            // 2. 현재 칸의 주변 4칸 중 청소되지 않은 칸이 존재하는지 확인
            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                int ndir = (d+3) % 4; // 90도 반시계 회전
                int nx = x + dx[ndir];
                int ny = y + dy[ndir];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if(arr[nx][ny] == 0) {
                    flag = true; // 청소되지 않은 칸이 존재한다면 flag 체크
                    d = ndir;
                    x = nx;
                    y = ny;
                    break;
                }
                d = (d+3) % 4;
            }

            // 3. 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            if(!flag) {
                // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 후진
                int ndir = (d+2) % 4;

                int nx = x + dx[ndir];
                int ny = y + dy[ndir];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && arr[nx][ny] != 1) { // 후진할 수 있는 경우 (벽이 아닌 경우)
                    // 현재 위치를 후진한 위치로 갱신
                    x = nx;
                    y = ny;
                }
                else { // 후진할 수 없다면 작동 중지 (종료)
                    return;
                }
            }
        }
    }
}