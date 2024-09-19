package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제: 특정거리의 도시 찾기 (B18352)
 * 풀이: 다익스트라
 * 메모리: 277508kb
 * 시간: 1064ms
 */
public class B18352 {

    int N, M, K, X;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
        }

        // 다익스트라 알고리즘을 위한 초기화
        final int INF = 100_000_000;
        int[] minDist = new int[N + 1];
        Arrays.fill(minDist, INF);
        boolean[] visit = new boolean[N + 1];

        minDist[X] = 0; // 시작점 설정
        Queue<Integer> q = new LinkedList<>(); // 큐를 통하여 다음 지점 관리
        q.add(X);

        // 다익스트라
        while (!q.isEmpty()){

            int cur = q.poll();
            if (visit[cur]) { // 방문했으면 다음 지점
                continue;
            }

            visit[cur] = true;
            for (int num : graph[cur]) { // 다음 방문점에 대해 최소경로 업데이트
                if (!visit[num] && minDist[num] > minDist[cur] + 1) {
                    minDist[num] = minDist[cur] + 1;
                    q.add(num);
                }
            }
        }

        // 거리 정보가 K 인 도시 번호 출력
        for (int i = 1; i <= N; i++) {
            if (minDist[i] == K) {
                result.append(i).append("\n");
            }
        }

        if (result.toString().isEmpty()) {
            // 최단 거리가 K 인 도시가없으면 -1
            System.out.println("-1");
        } else {
            System.out.println(result);
        }
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B18352().solution();
//    }
//}
