import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


class Main {

    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 맵 입력받기
        map = new char[8][8];
        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(move());
    }

    // 맵 아래로 한칸씩 움직이기
    private static void moveWall() {
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                map[i + 1][j] = map[i][j];
            }
        }
    }

    // 8방향 + 안움직이기 좌표 설정
    static int[] dx = { 1, 1, -1, -1, 0, 0, 1, -1, 0 };
    static int[] dy = { 0, 1, 0, -1, 1, -1, -1, 1, 0 };

    private static int move() {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 7));
        boolean[][] isVisited;
        int time = 0; // 시간, 8초가 지나면 모든 벽이 아래로 내려가 길이 사라지므로 무조건 도착하게 되므로 체크
        while (!q.isEmpty()) {

            time++;
            // 탐색시 마다 방문 초기화
            isVisited = new boolean[8][8];
            int size = q.size();

            for (int i = 0; i < size; i++) {
                // 큐 안에 있는 좌표를 기준으로 각각 8방향 탐색

                Point cur = q.poll();

                // 벽이 내려와 캐릭터가 벽을 만나면 패스
                if (map[cur.y][cur.x] == '#') {
                    continue;
                }

                // 벽이 모두 없어져서 도착한 경우
                if (time > 8) {
                    return 1;
                }

                // 캐릭터가 도착지에 도착한 경우
                if (cur.x == 7 && cur.y == 0) {
                    return 1;
                }

                // 9방향 탐색
                for (int k = 0; k < 9; k++) {
                    int nx = cur.x + dx[k];
                    int ny = cur.y + dy[k];

                    // 미로밖이고 벽이면 패스
                    if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8 || map[ny][nx] == '#') {
                        continue;
                    }

                    isVisited[ny][nx] = true;
                    q.add(new Point(nx, ny));
                }
            }
            moveWall();
        }

        // 여기까지 왔다는건 도착을 어떻게든 못했다는 뜻
        return 0;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
