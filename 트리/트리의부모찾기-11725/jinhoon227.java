package ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제: 트리의 부모 찾기 (B11725)
 * 풀이: BFS
 * 메모리: 58896kb
 * 시간: 512ms
 */
public class B11725_트리의부모찾기 {

    List<List<Integer>> tree;
    int[] parents;
    boolean[] isVisited;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            tree.get(first).add(second);
            tree.get(second).add(first);
        }

        //== 입력 끝 ==//
        parents = new int[N + 1]; // 인덱스 번호 에 대해 부모 노드 번호를 저장
        isVisited = new boolean[N + 1];

        // bfs 로 루트노드(1) 부터 탐색하여 자식들의 부모를 저장
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        isVisited[1] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int num : tree.get(cur)) {
                if (!isVisited[num]) {
                    parents[num] = cur; // 부모 번호 저장
                    q.offer(num);
                    isVisited[num] = true;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            result.append(parents[i]).append("\n");
        }

        System.out.println(result);
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B11725().solution();
//    }
//}
