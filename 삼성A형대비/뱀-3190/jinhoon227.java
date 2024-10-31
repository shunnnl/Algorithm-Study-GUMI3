import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, K, L;
    static int[][] map;
    static char[] command;
    static List<Part> snake;

    static class Part {
        int y;
        int x;

        public Part(int y, int x) {
            super();
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
        }

        command = new char[10001];
        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            command[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
        }

        snake = new ArrayList<>();
        snake.add(new Part(1, 1));
        int curD = 0;

        int[] dx = { 1, 0, -1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        int time = 0;
        loop: while (time <= 30000) {

            // 방향전환이 필요하다면 전환
            if (command[time] != 0) {
                if (command[time] == 'D') {
                    curD = (curD + 1) % 4;
                }

                if (command[time] == 'L') {
                    curD--;
                    if (curD < 0) {
                        curD = 3;
                    }
                }
            }

            // 머리 다음 좌표
            int nx = snake.get(snake.size() - 1).x + dx[curD];
            int ny = snake.get(snake.size() - 1).y + dy[curD];

            // 갈 수 없는길이거나
            if (nx <= 0 || nx > N || ny <= 0 || ny > N) {
                break;
            }

            // 뱀의 몸통에 부딪히거나
            for (int i = 0; i < snake.size() - 1; i++) {
                if (snake.get(i).x == nx && snake.get(i).y == ny) {
                    break loop;
                }
            }

            // 사과가 있다면
            if (map[ny][nx] == 2) {
                snake.add(new Part(ny, nx));
                map[ny][nx] = 0;
            }
            // 사과가 없다면
            else {
                // 꼬리 좌표 제거 후 머리 좌표 넣기
                snake.remove(0);
                snake.add(new Part(ny, nx));
            }

            time++;
        }

        System.out.println(time + 1);
    }

}
