package algo.study.week8;
/**
 * 백준 18352 특정 거리의 도시 찾기
 * 메모리 : 257256 KB
 * 시간 : 900 ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj18352 {
	static int N, M, K, X;	// N개의 도시, M개의 도로(단방향), K거리의 있는걸 찾을 것, X 시작 도시
	static ArrayList<Integer>[] road;	// 0번째가 시작, 1번째가 도착
	static int[] dist;		// 도시들의 최단거리 저장 부분
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		road = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			road[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			road[a].add(b);
		}
		// 입력 끝
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[X] = 0;
		bfs(X);
	}
	static void bfs(int start) {
		Queue<Integer> que = new LinkedList<>();
		ArrayList<Integer> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		
		que.offer(start);
		while(!que.isEmpty()) {
			int cur = que.poll();
			// 거리같거나 더 클땐 계산 X
			if (dist[cur] == K) {
				result.add(cur);
				continue;
			}
			if (dist[cur] > K) 
				continue;
				
			for (int i : road[cur]) {
				if (dist[i] != Integer.MAX_VALUE)	// 만약 이미 거리계산을 했다면 (가까운 값은 이미 들어가있음)
					continue;
				dist[i] = dist[cur] + 1;	// 이전까지의 거리 + 1
				que.add(i);					// 값에 넣어줌
			}
		}
		// 오름차순 출력위한 정렬
		Collections.sort(result);
		// result 값이 찼거나 비었거나에 따라서 출력을 다르게
		if (result.isEmpty())
			System.out.println("-1");
		else {
			for (int i : result) {
				sb.append(i).append("\n");
			}
			System.out.println(sb);
		}
	}
 }
