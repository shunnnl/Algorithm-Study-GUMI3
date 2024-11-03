import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제 : 아기 상어
 * 풀이 : bfs + 구현
 * 메모리 : 26,472kb
 * 시간 : 196ms
 */

public class _16236_아기상어 {

    static int N, arr[][], shark, res;
    static ArrayList<fish> fishInfo;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class fish implements Comparable<fish> {
        int x;
        int y;
        int dist;

        fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        // 거리가 가까운 물고기가 많다면 가장 위에 있는 물고기
        // 그러한 물고기가 여러 마리라면 가장 왼쪽에 있는 물고기를 먹음
        @Override
        public int compareTo(fish o) {
            if(this.dist != o.dist) return this.dist - o.dist;
            if(this.x != o.x) return this.x - o.x;
            return this.y - o.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 공간의 크기

        StringTokenizer st;
        arr = new int[N][N];
        fishInfo = new ArrayList<>();
        int start_x = 0;
        int start_y = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                // 물고기들의 정보 저장
                if(arr[i][j] != 0 && arr[i][j] != 9) {
                    fishInfo.add(new fish(i, j, Integer.MAX_VALUE));
                }

                // 아기 상어의 초기 위치 저장
                if(arr[i][j] == 9) {
                    start_x = i;
                    start_y = j;
                }
            }
        }

        shark = 2; // 아기상어의 처음 크기 = 2
        res = 0;
        BabyShark(start_x, start_y);
        System.out.println(res);
    }

    private static void BabyShark(int x, int y) {
        int cnt = 0; // 아기 상어가 먹은 물고기의 수

        while(true) {
            // 1. 현재 아기상어의 위치에서 먹을 수 있는 물고기들을 추출
            PriorityQueue<fish> isPossible = bfs(x, y);

            // 2. 먹을 수 있는 물고기가 없으면 종료
            if(isPossible.isEmpty()) break;

            // 3. 가장 가까운 물고기 선택
            fish eatFish = isPossible.poll();

            // 4. 아기 상어의 상태 갱신 (가장 가까운 물고기 먹기, 크기 업데이트)
            fishInfo.remove(eatFish);
            cnt++;
            if(cnt == shark) {
                shark++;
                cnt = 0;
            }

            // 5. 아기 상어의 이동 및 위치 업데이트
            arr[x][y] = 0;
            x = eatFish.x;
            y = eatFish.y;
            arr[x][y] = 9;

            res += eatFish.dist;
        }
    }

    private static PriorityQueue<fish> bfs(int start_x, int start_y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new int[] {start_x, start_y, 0});
        visited[start_x][start_y] = true;

        int ans = Integer.MAX_VALUE;
        PriorityQueue<fish> fishes = new PriorityQueue<>();
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(arr[cur[0]][cur[1]] != 0 && arr[cur[0]][cur[1]] != 9 && arr[cur[0]][cur[1]] < shark) {
                fishes.offer(new fish(cur[0], cur[1], cur[2]));
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || arr[nx][ny] > shark) continue;

                visited[nx][ny] = true;
                q.add(new int[] {nx, ny, cur[2]+1});
            }
        }
        return fishes;
    }
}