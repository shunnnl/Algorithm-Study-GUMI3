import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] board;
    static boolean[][] visited;

    // 상하좌우 & 대각선 & 제자리
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1, 0};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1, 0};

    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void moveWall() {
        for(int i = 7; i >= 0; i--) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j] == '#') {
                    board[i][j] = '.';
                    if(i != 7) {
                        board[i+1][j] = '#';
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[8][8];
        int cntWall = 0;
        for(int i = 0; i < 8; i++) {
            board[i]=br.readLine().toCharArray();
        }

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board[i][j] == '#') {
                    cntWall++;
                }
            }
        }

        if(cntWall == 0) { // 체스판에 벽이 없으면 도착지에 갈 수 있으므로
            System.out.println(1); // 1을 출력하고
            return; // 종료
        }

        bfs(7, 0);
    }

    private static void bfs(int start_x, int start_y) {
        Queue<Pos> queue = new LinkedList<Pos>(); // 큐 생성
        queue.add(new Pos(start_x, start_y)); // 시작 위치 큐에 넣어주기

        while(!queue.isEmpty()) {
            visited = new boolean[8][8]; // 벽이 움직여서 새로운 경로가 생길 수 있으므로 한 턴마다 초기화

            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Pos now = queue.poll();

                if(now.x == 0 && now.y==7) { // 도착지인 오른쪽 끝에 도착하면
                    System.out.println(1); // 1을 출력하고
                    return; // 종료
                }

                if(board[now.x][now.y] == '#') { // 현재 위치가 벽인 경우
                    continue;
                }

                for(int j = 0; j < 9; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];

                    if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8) { // 가지치기
                        continue;
                    }

                    if(board[nx][ny] == '#') {
                        continue; // 벽을 만나면 pass
                    }

                    if(visited[nx][ny]) {
                        continue; // 방문한 곳이면 pass
                    }

                    queue.add(new Pos(nx, ny));
                    visited[nx][ny] = true;
                }
            }

            moveWall(); // 벽을 아래로 이동
        }
        System.out.println(0);
    }
}