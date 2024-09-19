import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 : 특정 거리의 도시 찾기
 * 메모리 : 335080KB
 * 시간 : 1408ms
 */

public class _18352_특정거리도시찾기 {
    static int N, M, K, X, dist[];
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    final static int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시의 개수
        M = Integer.parseInt(st.nextToken()); // 도로의 개수
        K = Integer.parseInt(st.nextToken()); // 거리 정보
        X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호

        graph = new ArrayList[N+1]; // 도시 번호는 1번부터 N번까지
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(new Node(B, 1)); // 단방향 그래프
        }

        visited = new boolean[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, INF); // 거리 배열 초기화

        dijkstra(X);

        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            if(dist[i] == K) { // 최단거리가 K인 모든 도시 번호 출력
                flag = true;
                System.out.println(i);
            }
        }

        if(!flag) System.out.println(-1); // 최단거리가 K인 도시가 존재하지 않으면 -1 출력
    }

    private static void dijkstra(int start) {
        dist[start] = 0; // start to start의 거리는 0
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위 큐 생성
        pq.offer(new Node(start, 0)); // 큐에 시작 노드 삽입

        while(!pq.isEmpty()) {
            int cur = pq.poll().idx;

            if(!visited[cur]) { // 현재 정점에 방문한 적이 없다면
                visited[cur] = true; // 현재 정점 방문처리

                // 현재 정점과 연결된 정점들 비교
                for (Node next : graph[cur]) {
                    // 출발지에서 다음 정점까지의 현재거리보다 현재 정점을 거쳐가는 거리가 더 짧다면 갱신
                    if(dist[next.idx] > dist[cur] + next.cost) {
                        dist[next.idx] = dist[cur] + next.cost;
                        // 현재 정점까지의 거리 + 다음 정점까지의 거리 연산이기 때문에
                        // 큐에 넣어줄 때, 가중치 값을 dist[next.idx]로 해주어야 함
                        pq.offer(new Node(next.idx, dist[next.idx]));
                    }
                }
            }
            else continue; // 현재 정점을 이미 방문햤다면 패스
        }
    }
}
