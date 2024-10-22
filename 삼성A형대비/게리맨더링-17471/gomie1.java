import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 : 게리맨더링
 * 풀이 : 완전탐색(dfs) + bfs + 그래프
 * 메모리 : 16,892kb
 * 시간 : 132ms
 */

public class _17471_게리맨더링 {
    static int N, population[], res;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer> A, B;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 구역의 개수

        // 구역 별 인구 수 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        population = new int[N+1];
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 백준시 구역 정보 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < num; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        A = new ArrayList<>();
        B = new ArrayList<>();
        res = Integer.MAX_VALUE;
        dfs(1);

        if(res == Integer.MAX_VALUE) res = -1;
        System.out.println(res);
    }

    static void dfs(int idx) { // idx: 구역 번호
        if(idx == N+1) {
            // 한 선거구에 모든 구역이 포함된 경우는 패스
            if(A.size() == 0 || B.size() == 0) return;

            // 각 선거구의 구역들이 모두 인접해 있다면 인구수 계산
            if(bfs(A) && bfs(B)) {
                int p1 = 0, p2 = 0;
                for (int i = 1; i <= N; i++) {
                    if(A.contains(i)) p1 += population[i];
                    else p2 += population[i];
                }

                if (res > Math.abs(p1 - p2)) res = Math.abs(p1 - p2);
            }
            return;
        }

        // 현재 구역을 A 선거구에 넣는 경우
        A.add(idx);
        dfs(idx+1);
        A.remove(A.indexOf(idx));

        // 현재 구역을 B 선거구에 넣는 경우
        B.add(idx);
        dfs(idx+1);
        B.remove(B.indexOf(idx));
    }

    static boolean bfs(ArrayList<Integer> arr) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(arr.get(0));
        visited = new boolean[N+1];
        visited[arr.get(0)] = true;

        int cnt = 1;
        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < graph[cur].size(); i++) {
                int nxt = graph[cur].get(i);
                if(arr.contains(nxt) && !visited[nxt]) {
                    queue.offer(nxt);
                    visited[nxt] = true;
                    cnt++;
                }
            }
        }

        if(cnt == arr.size()) return true;
        else return false;
    }
}