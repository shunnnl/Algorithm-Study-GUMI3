package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제: 캐슬 디펜스 (B17135)
 * 풀이: 구현
 * 메모리: 24296kb
 * 시간: 304ms
 */
public class B17135 {

    int N;
    int M;
    int D;
    int[][] map;
    int[][] tempMap;
    int result;
    List<Integer> archers;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tempMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                tempMap[i][j] = num;
            }
        }

        archers = new ArrayList<>();
        result = 0;
        dfs(0, 0);

        System.out.println(result);
    }

    // 궁수 배치
    private void dfs(int start, int cnt) {

        // 궁수가 3마리면 로직 실행
        if (cnt == 3) {
            init();
            attack();
            return;
        }

        for (int i = start; i < M; i++) {
            archers.add(i);
            dfs(i + 1, cnt + 1);
            archers.remove(archers.size() - 1);
        }
    }

    // 맵 초기화
    private void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = tempMap[i][j];
            }
        }
    }

    // 공격
    private void attack() {
        int kill = 0;

        // 벽이 내려오는 수만큼
        for (int n = 0; n < N; n++) {
            boolean[][] visited = new boolean[N][M];

            // 궁수 공격
            for (int k = 0; k < archers.size(); k++) {
                int temp = archers.get(k);
                int minD = Integer.MAX_VALUE;
                int minY = Integer.MAX_VALUE;
                int minX = Integer.MAX_VALUE;

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (map[i][j] == 1) { // 적이 있을 경우
                            int distance = Math.abs(i - N) + Math.abs(j - temp); // 적과 궁수와의 거리 계산

                            // 현재 구한 최단 거리보다 더 짧은 거리일 경우 최단 거리, 좌표 갱신
                            if (distance < minD) {
                                minD = distance;
                                minY = i;
                                minX = j;
                            }

                            // 최단 거리와 같고, 더 왼쪽에 적이 있을 경우 왼쪽의 적을 우선 처치
                            if (distance == minD && minX > j) {
                                minY = i;
                                minX = j;
                            }
                        }
                    }
                }

                // 공격 가능한 거리일 시 해당 좌표 공격 예정(둘 이상의 궁수가 같은 적을 노릴 수 있으므로, 바로 공격하면 안됨)
                if (minD <= D) {
                    visited[minY][minX] = true;
                }
            }

            // 궁수 공격 처리
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) {
                        map[i][j] = 0;
                        kill++;
                    }
                }
            }

            // i번째 줄을 i-1번째 줄로 변경(전부 아래로 1칸씩 이동)
            for (int i = N - 1; i > 0; i--) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = map[i - 1][j];
                }
            }

            // 맨 위 줄을 0으로 초기화
            for (int j = 0; j < M; j++) {
                map[0][j] = 0;
            }
        }

        result = Math.max(result, kill);
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B17135().solution();
//    }
//}
