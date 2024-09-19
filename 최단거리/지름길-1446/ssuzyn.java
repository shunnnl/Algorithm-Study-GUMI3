import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1446. 지름길
 * 메모리 : 14436 KB
 * 시간 : 108 ms
 */
public class Main {

    static int N, D;
    static int[] distance;
    static ArrayList<Node>[] graph;

    static class Node{
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.dist - b.dist);
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int curNode = cur.v;
            int curDist = cur.dist;

            if(distance[curNode] < curDist) continue;

            for(Node next : graph[curNode]){
                int newDist = distance[curNode] + next.dist;
                if(newDist < distance[next.v]){
                    distance[next.v] = newDist;
                    pq.add(new Node(next.v, newDist));
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지름질의 개수
        D = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        graph = new ArrayList[D + 1];
        for(int i = 0; i <= D; i++) {
            graph[i] = new ArrayList<>();
        }

        distance = new int[D + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;

        // 고속도로의 기본 경로 (1km씩 이동하는 경로)
        for(int i = 0; i < D; i++) {
            graph[i].add(new Node(i + 1, 1));
        }

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(b <= D){
                graph[a].add(new Node(b, cost)); // 지름길 추가
            }
        }

        dijkstra(0);
        System.out.println(distance[D]);
    }
}
