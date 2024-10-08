import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리 : 42836 KB
 * 시간 : 348 ms
 */
public class Main {

    static int N, M, D, answer;
    static List<Node> enemies = new ArrayList<>(); // 적의 위치를 저장하는 리스트

    private static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 적이 있는 행의 수
        M = Integer.parseInt(st.nextToken()); // 열의 수
        D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한

        // 적의 위치를 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    enemies.add(new Node(i, j));
                }
            }
        }

        pickArchers(0, 0, new int[3]);
        System.out.println(answer);
    }

    private static void pickArchers(int start, int count, int[] archers) {
        if (count == 3) {
            simulateGame(archers);
            return;
        }

        for (int i = start; i < M; i++) {
            archers[count] = i;
            pickArchers(i + 1, count + 1, archers);
        }
    }

    private static void simulateGame(int[] archers) {
        int killCount = 0; // 제거된 적의 수
        List<Node> currentEnemies = new ArrayList<>(enemies); // 현재 적 리스트 복사

        // 매 턴마다 적을 공격하고 이동
        for (int round = 0; round < N; round++) {
            boolean[][] attacked = new boolean[N][M]; // 공격한 적을 기록
            List<Node> targets = new ArrayList<>();

            for (int archer : archers) {
                Node target = null;
                int minDistance = Integer.MAX_VALUE;

                for (Node enemy : currentEnemies) {
                    int distance = (N - enemy.x) + Math.abs(archer - enemy.y); // 거리 계산
                    if (distance <= D) {
                        // 가장 가까운 적 및 왼쪽의 적 선택
                        if (distance < minDistance || (distance == minDistance && enemy.y < target.y)) {
                            minDistance = distance;
                            target = enemy;
                        }
                    }
                }

                if (target != null && !attacked[target.x][target.y]) {
                    targets.add(target);
                    attacked[target.x][target.y] = true; // 공격한 적 기록
                }
            }

            // 공격한 적 제거
            for (Node target : targets) {
                currentEnemies.remove(target); // 현재 적 리스트에서 제거
                killCount++;
            }

            // 적 이동
            List<Node> moveEnemies = new ArrayList<>();
            for (Node enemy : currentEnemies) {
                if (enemy.x + 1 < N) { // 아래로 이동 가능한 경우
                    moveEnemies.add(new Node(enemy.x + 1, enemy.y)); // 적 위치 업데이트
                }
            }
            currentEnemies = moveEnemies; // 현재 적 리스트 업데이트
        }

        answer = Math.max(answer, killCount);
    }

}