package 다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 18352. 특정 거리의 도시 찾기
 * 메모리 : 335388 KB
 * 시간 : 1280 ms
 */
public class 특정거리의도시찾기_18352 {

    static int N, M, K, X;
    static ArrayList<Node>[] graph;
    static int[] distance;

    static class Node{
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Node(X, 0));
        distance[X] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int cur = node.v;

            // 이미 처리된 노드라면 넘어간다.
            if(distance[cur] < node.cost) continue;

            for(Node next : graph[cur]){
                int cost = distance[cur] + next.cost;
                if(cost < distance[next.v]){
                    distance[next.v] = cost;
                    pq.offer(new Node(next.v, cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 개수
        M = Integer.parseInt(st.nextToken()); // 도로의 개수
        K = Integer.parseInt(st.nextToken()); // 거리 정보
        X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호

        distance = new int[N + 1];
        graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, 1));
        }

        Arrays.fill(distance, Integer.MAX_VALUE);
        dijkstra();

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            if(distance[i] == K) {
                sb.append(i + "\n");
            }
        }

        if(sb.length() == 0) System.out.println(-1);
        else System.out.println(sb);

    }



}
