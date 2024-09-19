import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 메모리 : 281608kb, 시간 : 976ms
public class Q_18352 {
    static int n; // 도시의 개수(노드)
    static int m; // 도로의 개수(간선)
    static int k; // 거리 정보
    static int x; // 출발 도시의 번호
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();;
    static boolean[] visited; // 방문 checked
    static int[] distance; // 이동 거리
    static ArrayList<Integer> result = new ArrayList<>(); // 결과를 담을 리스트


    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        visited = new boolean[n + 1];
        distance = new int[n + 1];

        for (int i = 0; i < m; i++) { // 연결 정보
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        bfs();

        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(result);
            for (int i : result) {
                System.out.println(i);
            }
        }
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        visited[x] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i : graph.get(now)) {
                if (!visited[i]) {
                    visited[i] = true;
                    distance[i] = distance[now] + 1;
                    q.add(i);
                    if (distance[i] == k) {
                        result.add(i);
                    }
                }
            }
        }
    }
}
