package algo.study.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 1260 백준 DFS와 BFS
 * 
 */

public class boj1260 {
//public class Main {
	static int n;
	static int[][] graph; // 그래프 배열
	static boolean[] visited; // 방문 노드 체크용 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 세 정수 입력 받기
		n = Integer.parseInt(st.nextToken()); // 정점의 개수
		int k = Integer.parseInt(st.nextToken()); // 간선의 개수
		int v = Integer.parseInt(st.nextToken()); // 정점의 번호

		// 그래프 크기와 방문체크용 배열 크기 할당
		graph = new int[n + 1][n + 1];
		visited = new boolean[n + 1];

		// 간선 정보들을 받아서 그래프에 저장
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph[x][y] = 1;
			graph[y][x] = 1;
		}
		// dfs
		dfs(v);
		System.out.println();
		visited = new boolean[n + 1]; // bfs를 위해 visited 배열 초기화
		// bfs
		bfs(v);

	}

//	재귀함수를 이용한 DFS 구현
	private static void dfs(int node) {
		visited[node] = true; // 방문 처리
		System.out.print(node + " "); // 방문 노드 프린트
		for (int i = 1; i < n + 1; i++) { // 다음으로 방문할 노드 탐색
			if (graph[node][i] == 1 && visited[i] == false) {
				dfs(i);
			}
		}
	}

//	Queue 자료구조를 이용한 BFS 구현
	private static void bfs(int node) {
		Queue<Integer> que = new LinkedList<>();
		que.add(node);
		visited[node] = true;

		while(!que.isEmpty()) {		
			int current = que.poll();
			System.out.print(current + " ");	// 방문 노드 프린트
			for (int i = 0; i < n + 1; i++) {
				if (graph[current][i] == 1 && visited[i] == false) {	// 반복문 돌면서 visited 되지 않았고 연결 되어 있는노드 추가
					que.add(i);
					visited[i] = true;
				}
			}
		}
	}

}
