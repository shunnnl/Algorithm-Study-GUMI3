package algo.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 4256 트리 
 * 메모리: 40700 KB
 * 시간: 472 ms
 */

public class boj4256 {
//public class Main {
	static int testCase;
	static int n;
	static int[] preorderTree;
	static int[] inorderTree;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		testCase = Integer.parseInt(st.nextToken());

		for (int t = 0; t < testCase; t++) { // 테케 만큼 반복

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			preorderTree = new int[n];
			inorderTree = new int[n];
			sb = new StringBuilder();

			st = new StringTokenizer(br.readLine()); // 전위 순회 입력 받기
			for (int i = 0; i < n; i++) {
				preorderTree[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine()); // 중위 순회 입력 받기
			for (int i = 0; i < n; i++) {
				inorderTree[i] = Integer.parseInt(st.nextToken());
			}

			findPostorder(0, 0, n - 1); // postorder tree 찾는 함수
			System.out.println(sb);

		}
		System.out.println();
	}

	static void findPostorder(int rootIdx, int start, int end) {
		if (rootIdx >= n)
			return;
		// pre, in 을 이용해 pre 순회의 경우 루트 노드가 가장 앞에 있다는 점을 이용해 트리를 잘라나가면서 post 순회를 찾아낸다.
		int rootValue = preorderTree[rootIdx];
		for (int idx = start; idx <= end; idx++) { //
			if (rootValue == inorderTree[idx]) {
				findPostorder(rootIdx + 1, start, idx);
				findPostorder(rootIdx + idx - start + 1, idx + 1, end);
				sb.append(rootValue + " ");
				return;
			}
		}

	}

}
