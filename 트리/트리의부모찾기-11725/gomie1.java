import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제: 11725. 트리의 부모 찾기
 * 풀이: 트리(2차원 리스트) 생성 -> 노드 양방향 연결 -> bfs를 돌리며 각 노드의 부모 찾기
 * 메모리: 83120kb
 * 시간: 1168ms
 */

public class gomie1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드의 개수

        // tree로 사용할 2차원 ArrayList 생성
        ArrayList<Integer>[] tree = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) { // 인덱스 번호와 노드 번호를 맞춰주기 위해 범위는 1~N까지로 설정
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++) {
            // 연결된 노드들을 입력 받음
            String[] nodes = br.readLine().split(" ");
            int n1 = Integer.parseInt(nodes[0]);
            int n2 = Integer.parseInt(nodes[1]);

            // 양방향 연결
            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        boolean[] visited = new boolean[N+1]; // 방문 확인 배열
        int[] parent = new int[N+1]; // 부모노드를 담아줄 배열

        Queue<Integer> queue = new LinkedList<>(); // bfs를 위한 큐 생성
        visited[1] = true; // 시작 노드 방문 처리
        queue.add(1); // 시작 노드를 큐에 넣어줌

        while(!queue.isEmpty()) { // 큐가 빌때까지 반복 (연결된 모든 노드 탐색)
            int n = queue.poll(); // 현재 노드를 큐에서 꺼냄

            for(int node : tree[n]) { // 현재 노드와 연결된 모든 노드에 대해 탐색
                if(!visited[node]) { // 연결된 노드에 방문한 적이 없다면
                    visited[node] = true; // 방문처리 후
                    queue.add(node); // 큐에 추가
                    parent[node] = n; // 연결된 노드의 부모 노드는 현재 노드라고 명시
                }
            }
        }

        // 2번 노드부터 각 노드의 부모 노드 출력
        for(int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }

    }

}
