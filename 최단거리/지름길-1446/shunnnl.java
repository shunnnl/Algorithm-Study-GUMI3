import java.util.*;
import java.io.*;

// 메모리 : 14120kb, 시간 : 100ms
public class Q_1446 {
    static int result = 0;
    static int N, D; // 지름길의 개수, 고속도로의 길이
    static Road[] graph; // 지름길 정보 배열
    static int[] dist; // 최단 거리 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        dist = new int[D + 1];
        for(int i = 0 ; i < dist.length ; i++) {
            dist[i] = i;
        }

        graph = new Road[N];

        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph[i] = new Road(s, e, l);
        }

        dijkstra(0);
        System.out.println(dist[D]);
    }

    private static void dijkstra(int start) { // 다익스트라 활용하여 최단거리 계산
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(start, 0, 0));

        dist[start] = 0; // 시작 point 처리

        while(!pq.isEmpty()) {
            Road cur = pq.poll();
            int nowPos = cur.end; // 현재 위치

            for(Road r : graph) {
                if(r.start >= nowPos) { // 시작 위치가 현재 위치보다 뒤쪽에 있을 때
                    if(r.end > D) continue; // 고속도로 벗어나면 pass

                    if(dist[r.end] > dist[nowPos] + r.length + (r.start - nowPos)) { // 최단 거리 갱신
                        dist[r.end] = dist[nowPos] + r.length + (r.start - nowPos);
                        pq.offer(new Road(nowPos, r.end, dist[r.end]));
                    }
                }
            }

            dist[D] = Math.min(dist[nowPos] + D - nowPos, dist[D]); // 🔔 현재 위치에서 D까지의 거리 고려해 최단 거리 갱신
        }
    }
}

class Road implements Comparable<Road> {
    int start, end, length; // 시작 위치, 도착 위치, 지름길 길이

    public Road(int start, int end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }

    @Override
    public int compareTo(Road r) {
        return this.length - r.length;
    } // 지름길 길이를 기준으로 오름차순 정렬
}
