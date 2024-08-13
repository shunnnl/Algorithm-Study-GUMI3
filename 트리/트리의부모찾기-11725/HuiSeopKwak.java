package algo.study.week4;
/**
 * 문제: 11725 트리의 부모찾기
 * 풀이: BFS
 * 1차 시도 메모리: 70420 KB 시간: 1096 ms
 * 2차 시도 메모리: 65432 KB 시간: 568 ms (buffredwriter 사용)
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj11725 {
//public class Main {

	static int N; // 총 노드개수
	static int a, b; // 저장용 임시 변
	static List<Integer>[] Tree; // 트리 구현 리스트배열
	static int fromArr[]; // 부모 노드 저장
	static Queue<Integer> que; // BFS용 que

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 입력 받는 부분
		N = Integer.parseInt(st.nextToken());
		Tree = new ArrayList[N + 1];
		fromArr = new int[N + 1];

		for (int i = 1; i < N + 1; i++) { // Tree배열에 ArrayList 할당
			Tree[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			Tree[a].add(b);
			Tree[b].add(a);
		}

		// 루트 노드 찾기
		que = new LinkedList<Integer>();
		bfs(1);

//		for (int i = 2; i < N + 1; i++) {
//			System.out.println(fromArr[i]);
//		}
		for (int i = 2; i < N + 1; i++) {
			bw.write(String.valueOf(fromArr[i]));
			bw.write("\n");
		}
		bw.flush();

	}

	static void bfs(int node) {
		que.add(node);
		while (!que.isEmpty()) {
			int current = que.poll();
			for (int i = 0; i < Tree[current].size(); i++) {
				if (fromArr[Tree[current].get(i)] != 0) // 이미 부모노드의 정보를 알고 있으면 스킵
					continue;
				fromArr[Tree[current].get(i)] = current; // 모른다면 부모노드라고 작성해주고
				que.add(Tree[current].get(i)); // que에 추가해줌
			}
		}
	}
}
