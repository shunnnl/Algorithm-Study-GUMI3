import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 컴퓨터의 개수
        M = Integer.parseInt(st.nextToken()); // 관계의 개수
        res = new int[N+1]; // 컴퓨터 번호는 1~N번까지

        graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) { // 0 ~ N+1
            graph.add(new ArrayList<>());
        }

        // M개의 관계를 입력 받아 그래프에 넣어줌
        for(int i = 0; i < M; i++) {
            String[] computer = br.readLine().split(" ");
            int c1 = Integer.parseInt(computer[0]);
            int c2 = Integer.parseInt(computer[1]);

            // 단방향 연결
            graph.get(c1).add(c2);
        }

        // 모든 컴퓨터에 대해서 해킹할 수 있는 컴퓨터의 개수를 구함
        for(int i = 1; i <= N; i++) {
            visited = new boolean[N+1];
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(i);
            visited[i] = true;

            while(!queue.isEmpty()) {
                int n = queue.poll(); // now

                // now 에서 next로 갈 수 잇다는 것은 now가 next를 신뢰한다는 뜻!
                // 가장 많은 컴퓨터를 해킹하기 위해서는 가장 많은 컴퓨터에게 신뢰받는 컴퓨터를 찾아야 함
                for(int nn : graph.get(n)) { // nextnode
                    if(!visited[nn]) {
                        visited[nn] = true;
                        res[nn]++;
                        queue.add(nn);
                    }
                }
            }
        }

        // 최대값 찾기
        int maxValue = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i++) {
            maxValue = Math.max(maxValue, res[i]);
        }

        for(int i = 1; i <= N; i++) {
            if(res[i] == maxValue) {
                System.out.print(i + " ");
            }
        }
    }
}