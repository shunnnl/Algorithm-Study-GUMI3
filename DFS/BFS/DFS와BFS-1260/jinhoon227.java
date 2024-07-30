package ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제: DFS 와 BFS (B1260)
 * 풀이: DFS, BFS, 그래프
 * 메모리: 20160kb
 * 시간: 240ms
 */
public class B1260 {

    StringBuilder result = new StringBuilder();

    List<List<Integer>> graph;
    boolean[] visited;

    public void solution() throws IOException {
        int N = 0; // 정점의 개수
        int M = 0; // 간선의 개수
        int V = 0; // 시작점
        graph = new ArrayList<>(); // 그래프

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken()); // 시작점
            int second = Integer.parseInt(st.nextToken()); // 끝점

            // 양방향 그래프이므로 양쪽으로 넣어줍니다.
            graph.get(first).add(second);
            graph.get(second).add(first);
        }

        // 각 점에 대하여 오름차순으로 정렬합니다.
        graph.forEach(Collections::sort);

        visited = new boolean[N + 1]; // 각 점에 대한 방문여부, 점은 1부터 시작하므로 N+1 로 초기화
        visited[V] = true; // 시작점 세팅
        result.append(V); // 시작점 세팅
        dfsRoute(V);

        result.append("\n");

        visited = new boolean[N + 1]; // 각 점에 대한 방문여부, 점은 1부터 시작하므로 N+1 로 초기화
        visited[V] = true; // 시작점 세팅
        result.append(V); // 시작점 세팅
        bfsRoute(V);

        System.out.println(result);
    }

    private void dfsRoute(int start) {

        // 각 점에 대해 탐색을 진행
        for (int i = 0; i < graph.get(start).size(); i++) {
            // 각 점에 대해 정렬이 되어있으므로 순차적으로 방문하면 작은숫자부터 방문하는게 보장됌
            int next = graph.get(start).get(i);
            if (!visited[next]) {
                visited[next] = true;
                result.append(" ").append(next);
                dfsRoute(next);
            }
        }
    }

    private void bfsRoute(int start) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            start = queue.poll();
            // 각 점에 대해 탐색을 진행
            for (int i = 0; i < graph.get(start).size(); i++) {
                // 각 점에 대해 정렬이 되어있으므로 순차적으로 방문하면 작은숫자부터 방문하는게 보장됌
                int next = graph.get(start).get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    result.append(" ").append(next);
                    queue.offer(next);
                }
            }
        }
    }
}


//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B1260().solution();
//    }
//}