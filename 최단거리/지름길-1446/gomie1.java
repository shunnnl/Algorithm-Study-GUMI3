import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제 : 지름길
 * 메모리 : 14532KB
 * 시간 : 108ms
 */

public class _1446_지름길 {
    static int N, D, dist[];
    static ArrayList<int[]>[] graph;
    // static boolean[] visited; -> 1km씩 다음 위치를 모두 방문하므로 visit배열 필요 X
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 지름길의 개수
        D = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        graph = new ArrayList[D+1];
        for (int i = 0; i <= D; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 모든 지름길은 일방통행이므로 단방향 그래프
            // D까지만 가면 되므로 D를 벗어나는 정점에 대한 것은 추가하지 않음
            if(e <= D) {
                graph[s].add(new int[] {e, c});
            }
        }

        dist = new int[D+1];
        Arrays.fill(dist, INF); // 최단 거리 배열 초기화

        dijkstra(0);
        System.out.println(dist[D]);
    }

    // 다음 지점으로 한 칸(1Km)씩 이동하고,
    // 해당 위치에서 지름길이 있는경우 탐색하면서 최단거리 계산
    private static void dijkstra(int start) {
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] {start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            // 다음 지점으로 이동
            if(cur[0] + 1 <= D && cur[1] + 1 < dist[cur[0] + 1]) {
                dist[cur[0] + 1] = cur[1] + 1;
                pq.add(new int[] {cur[0]+1, dist[cur[0]+1]});
            }

            // 현재 위치에서 연결된 지름길이 있다면
            // for문을 통해 연결된 모든 지름길 탐색
            for (int[] next : graph[cur[0]]) {
                if(dist[next[0]] > dist[cur[0]] + next[1]) {
                    dist[next[0]] = dist[cur[0]] + next[1];
                    pq.offer(new int[] {next[0], dist[next[0]]});
                }
            }
        }
    }
}
