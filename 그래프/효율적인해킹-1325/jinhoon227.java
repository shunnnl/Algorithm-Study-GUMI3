package ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제: (B1325_효율적인해킹)
 * 1차 시도 : dfs (시간초과)
 * 2차 시도 : dfs + 배열 리스트(시간초과)
 * 3차 시도 : dfs + 배열 리스트 + 향상된 포문 (시간초과)
 * 4차 시도 : static + dfs + 배열 리스트 + 향상된 포문 (시간초과)
 * 5차 시도 : bfs + static + 배열 리스트 + 향상된 포문 (시간초과)
 * 6차 시도 : bfs + static + 배열 리스트 + 향상된 포문 + 별도의 함수 분리없이 하나의 메서드로 통일
 * 메모리: 313472kb
 * 시간: 10728ms
 */
class Main {
    static boolean[] isVisited;
    static int N, M;
    static List<Integer>[] computers;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 리스트 배열로 초기화
        computers = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            computers[i] = (new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            computers[A].add(B);
        }

        count = new int[N + 1]; // count[i] 는 i 번째 컴퓨터를 해킹하면 한 번에 해킹할 수 있는 컴퓨터 수
        for (int first = 1; first <= N; first++) {
            isVisited = new boolean[N + 1];
            isVisited[first] = true;
            count[first]++;

            // bfs 로 탐색
            Queue<Integer> q = new LinkedList<>();
            q.add(first);
            isVisited[first] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int next : computers[cur]) {
                    if (!isVisited[next]) {
                        isVisited[next] = true;
                        // 컴퓨터 해킹 성공했다면 증가
                        count[next]++;
                        q.add(next);
                    }
                }
            }
        }

        // 최대 해킹 가능한 컴퓨터가 몇개인지 찾기
        int maxHackedComputer = 0;
        for (int i = 1; i <= N; i++){
            maxHackedComputer = Math.max(maxHackedComputer, count[i]);
        }

        // 최대 해킹 가능한 컴퓨터 번호 찾기
        for (int i = 1; i <= N; i++) {
            if (count[i] == maxHackedComputer) {
                result.append(i).append(" ");
            }
        }

        System.out.println(result);
    }
}
