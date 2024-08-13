import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, V;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static boolean[] visited2;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        M = Integer.parseInt(st.nextToken()); // 노드의 개수
        V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호

        graph = new ArrayList<>(); // 배열 하나 생성
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>()); // 생성한 배열 안에 원소로 배열 하나씩 추가
        }

        // M개의 줄에 주어지는 두 정점의 번호 입력 받기
        for(int i = 0; i < M; i++) {
            String[] nv = br.readLine().split(" "); // 한 줄로 입력받고 띄어쓰기를 기준으로 분리해서 배열에 담기
            int n1 = Integer.parseInt(nv[0]);
            int n2 = Integer.parseInt(nv[1]);

            // 양방향 연결
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        for(int i = 0; i < graph.size(); i++) {
            Collections.sort(graph.get(i));
        }

        visited = new boolean[N+1]; // 방문표시 배열
        dfs(V);
        sb.append("\n");

        visited2 = new boolean[N+1];
        bfs(V);
        System.out.println(sb);
    }

    /* DFS */
    public static void dfs(int start) {
        visited[start] = true; // 시작 노드 방문 처리
        sb.append(start + " ");

        for(int node : graph.get(start)) { // 시작 노드에 연결된 노드들에 대해 하나씩 순회
            if(!visited[node]) { // 현재 노드에 방문한 적이 없다면
                dfs(node);
            }
        }
    }

    /* BFS */
    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<Integer>(); // 큐 생성
        queue.add(start); // 시작 노드를 큐에 넣어줌
        visited2[start] = true; // 시작 노드 방문 처리

        // 큐가 비지 않을 때까지 동작
        while(!queue.isEmpty()) {
            int node = queue.poll(); // 현재 노드를 큐에서 꺼내서 node에 담아줌
            sb.append(node + " ");

            // 현재 노드에 연결된 노드 수만큼 반복
            for(int i = 0; i < graph.get(node).size(); i++) {
                int nn = graph.get(node).get(i); // 연결된 노드를 nn에 담아줌

                if(!visited2[nn]) { // 연결된 노드에 방문한 적이 없다면
                    visited2[nn] = true; // 방문 처리한 후
                    queue.add(nn); // 큐에 담아줌
                }
            }
        }
    }
}