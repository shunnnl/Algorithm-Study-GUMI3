import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 메모리 : 14,304kb
 * 시간 : 108ms
 */

public class 뱀 {

    static int N, L, board[][], res;
    static Game[] info;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Game { // implements Comparable<Game>
        int x; // 게임 시작 후 x초 이후에
        char c; // c 방향으로 회전

        Game(int x, char c) {
            this.x = x;
            this.c = c;
        }

        // 방향 전환 정보 X가 증가하는 순으로 주어지기 때문에 정렬은 필요 X
        // @Override
        // public int compareTo(Game o) {
        // 	return this.x != o.x ? this.x - o.x : o.x - this.x;
        // }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 보드의 크기

        // 보드에 사과 놓기
        StringTokenizer st;
        board = new int[N][N];
        int K = Integer.parseInt(br.readLine()); // 사과의 개수
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            board[x][y] = 1; // 사과는 1로 표시
        }

        L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
        info = new Game[L];
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);

            info[i] = new Game(X, C);
        }

        res = 0;
        Dummy();
        System.out.println(res);
    }

    private static void Dummy() {
        boolean[][] visited = new boolean[N][N];
        ArrayList<int[]> pos = new ArrayList<>();

        // 뱀의 초기 위치는 (0, 0), 초기 방향은 오른쪽
        int x = 0;
        int y = 0;
        int dir = 1;

        visited[x][y] = true; // 초기위치 방문 처리
        pos.add(new int[] {x, y}); // 뱀이 방문한 위치를 pos에 넣어주기

        int idx = 0;
        int time = info[idx].x;
        char d = info[idx].c;

        // Game Start !!!
        while(true) {
            // 게임이 시작한지 time초가 되면 방향 회전
            if(res == time) {
                if(d == 'L') { // 왼쪽으로 90도 회전
                    dir = (dir + 3) % 4;
                }
                else { // 오른쪽으로 90도 회전
                    dir = (dir + 1) % 4;
                }

                idx++;

                if(idx < L) {
                    time = info[idx].x;
                    d = info[idx].c;
                }
            }

            // 1. 뱀의 몸 길이를 늘려 머리를 다음 칸에 위치시키기
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 2. 벽이나 자기 자신의 몸과 부딪히면 게임 종료
            if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
                res++;
                break;
            }

            // 3. 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않음
            if(board[nx][ny] == 1) {
                board[nx][ny] = 0; // 사과 먹기
            }
            else { // 4. 이동한 칸에 사과가 없다면, 꼬리가 위치한 칸을 비워줌
                int rx = pos.get(0)[0];
                int ry = pos.get(0)[1];

                pos.remove(0);
                visited[rx][ry] = false;
            }

            // 뱀 이동
            visited[nx][ny] = true;
            pos.add(new int[] {nx, ny});

            // 현재 위치 갱신
            x = nx;
            y = ny;

            res++; // 게임 시간 1증가
        }
    }
}