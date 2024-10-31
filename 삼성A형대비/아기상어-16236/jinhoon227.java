import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int N;
    static int startX, startY;
    static int sharkSize, eatenFish;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    startY = i;
                    startX = j;
                    map[i][j] = 0;
                }
            }
        }

        sharkSize = 2;
        eatenFish = 0;

        int result = 0;
        while (true) {
            int num = bfs();
            if (num == -1) {
                break;
            }
            result += num;
        }

        System.out.println(result);
    }

    static int[] dy = { -1, 0, 0, 1 };
    static int[] dx = { 0, -1, 1, 0 };

    private static int bfs() {

        boolean[][] visit = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        visit[startY][startX] = true;
        q.add(new int[] { startY, startX });

        int targetX = Integer.MAX_VALUE;
        int targetY = Integer.MAX_VALUE;
        int time = 0;
        while (!q.isEmpty()) {

            time++;
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {

                int[] cur = q.poll();

                for (int d = 0; d < 4; d++) {

                    int nx = cur[1] + dx[d];
                    int ny = cur[0] + dy[d];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[ny][nx]) {
                        continue;
                    }

                    // 상어의 크기보다 큰 물고기라면 지나갈 수 없다.
                    if (map[ny][nx] > sharkSize) {
                        continue;
                    }

                    // 작은 물고기를 찾았다면 좌표를 업데이트
                    if (map[ny][nx] != 0 && map[ny][nx] < sharkSize) {

                        if(targetY > ny) {
                            targetY = ny;
                            targetX = nx;
                        }else if(targetY == ny && targetX > nx) {
                            targetY = ny;
                            targetX = nx;
                        }
                    }

                    q.add(new int[] { ny, nx });
                    visit[ny][nx] = true;
                }
            }

            // 탐색 완료 후 물고기 먹는지 확인
            if(targetY != Integer.MAX_VALUE) {

                map[targetY][targetX] = 0;
                // 먹은 물고기를 증가시키고, 진화를 충족했는지 확인
                eatenFish++;
                if (eatenFish == sharkSize) {
                    sharkSize++;
                    eatenFish = 0;
                }

                startX = targetX;
                startY = targetY;

                return time;
            }

        }

        return -1;
    }

}
