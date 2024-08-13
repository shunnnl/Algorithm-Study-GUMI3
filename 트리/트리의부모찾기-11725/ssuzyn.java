import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/***
 * 11725. 트리의 부모 찾기
 * 1차 시도 메모리: 72508 KB, 시간: 1472 ms
 * 2차 시도 메모리: 67180 KB, 시간: 680 ms
 */
class 트리의부모찾기_11725 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int node = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < node; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        boolean[] visited = new boolean[node+1];  // 방문 여부 체크 배열
        int[] parent = new int[node+1];  // 각 노드의 부모 노드를 저장할 배열

        Queue<Integer> q = new LinkedList<>();
        q.add(1); // 루트 노드 1부터 탐색 시작
        visited[1] = true;

        // BFS 탐색 시작
        while(!q.isEmpty()) {
            int tmp = q.poll();

            // 인접한 노드를 탐색
            for(int i = 0; i < graph.get(tmp).size(); i++) {
                int next = graph.get(tmp).get(i);

                if(!visited[next]) {
                    visited[tmp] = true;
                    q.add(next);
                    parent[next] = tmp; // 부모 노드 설정 (현재 노드가 부모)
                }
            }
        }

        for(int i = 2; i <= node; i++) {
            bw.write(String.valueOf(parent[i]));
            bw.write("\n");
        }

        bw.flush();
    }
}
