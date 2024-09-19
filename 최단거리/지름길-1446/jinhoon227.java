package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제: 지름길 (B1446)
 * 풀이: 다익스트라..?
 * 메모리: 14364kb
 * 시간: 104ms
 */
public class B1446 {

    int N, D;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        List<Edge>[] edges = new ArrayList[D + 1];
        for (int i = 0; i <= D; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 목적지에 벗어나지 않은길만 저장
            if(from <= D && to <= D) {
                edges[from].add(new Edge(to, weight));
            }
        }

        final int INF = 100_000_000;
        int[] minDist = new int[D + 1];
        Arrays.fill(minDist, INF);

        for(int i=0; i<=D; i++) { // 각 지점까지 가는 경로비용 세팅
            minDist[i] = i;
        }

        for (int i = 0; i < D; i++) {

            // 지름길이 있다면 지름길 값 업데이트
            for(Edge e : edges[i]) {
                if (minDist[e.to] > minDist[i] + e.weight) {
                    minDist[e.to] = minDist[i] + e.weight;
                }
            }

            // 다음길에 대해 최소루트 업데이트
            minDist[i + 1] = Math.min(minDist[i + 1], minDist[i] + 1);
        }

        System.out.println(minDist[D]);
    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B1446().solution();
//    }
//}
