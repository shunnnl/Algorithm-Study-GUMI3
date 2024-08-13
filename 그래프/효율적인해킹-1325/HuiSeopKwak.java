package algo.study.week2;
/***
 * 	1325 효율적인 해킹
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1325 {
//public class Main {
	static int N, M;
	static List<Integer>[] list;
	static boolean[] visited;
	static int[] result;
	static int node;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 입력 값 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1]; // 컴퓨터 수 만큼 list 생성
		result = new int[N + 1]; // 각 노드 별 최대 해킹 컴퓨터 수 기록용 배열
		int a = 0;
		int b = 0;

		for (int i = 1; i < N + 1; i++) { // list배열에 ArrayList 할당
			list[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) { // 간선 정보들을 받아와 저장
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			list[b].add(a);
		}
//		for (int i = 1; i < N + 1; i++) {	// 저장 확인용
//			for (int j = 0; j < list[i].size(); j++) {
//				System.out.println(list[i].get(j));
//			}
//		}

		for (int startNode = 1; startNode < N + 1; startNode++) {	// bfs main으로 옮김 ㅠ
			visited = new boolean[N + 1]; // 방문 노드 초기화
			Queue<Integer> que = new LinkedList<Integer>();
			que.add(startNode);
			visited[startNode] = true;
			result[startNode] += 1;

			while (!que.isEmpty()) {
				int current = que.poll();
				if (list[current] != null) {
					for (int i : list[current]) {
						if (!visited[i]) {
							result[startNode] += 1;
							que.add(i);
							visited[i] = true;
						}
					}
				}
			}
//			bfs(i);
		}
		
		int max = 0;
		for (int i = 1; i < N + 1; i++) {	// max값 알아내기
			if (result[i] > max)
				max = result[i];
		}
		
		for (int i = 1; i < N + 1; i++) {	// 오름차순으로 맥스값 출력
			if (result[i] == max)
				System.out.print(i + " ");
		}
	}

//	public static void bfs(int startNode) {
//		visited = new boolean[N + 1]; // 방문 노드 초기화
//		Queue<Integer> que = new LinkedList<Integer>();
//		que.add(startNode);
//		visited[startNode] = true;
//		result[startNode] += 1;
//
//		while (!que.isEmpty()) {
//			int current = que.poll();
//			if (list[current] != null) {
////				for (int i = 0; i < list[current].size(); i++) {
////					node = list[current].get(i);
////					if (!visited[node]) {
////						result[startNode] += 1;
////						que.offer(node);
////						visited[node] = true;
////					}
////				}
//				for (int i : list[current]) {
//					if (!visited[i]) {
//						result[startNode] += 1;
//						que.add(i);
//						visited[i] = true;
//					}
//				}
//			}
//		}
//	}

}
