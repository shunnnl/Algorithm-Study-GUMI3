import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] visited;
	static List<Integer>[] graph;
	
	public static void dfs(int start) {
		visited[start] = true;
		System.out.print(start + " ");
		
		for(int i = 0; i < graph[start].size(); i++) {
			int tmp = graph[start].get(i);
			if(!visited[tmp]) dfs(tmp);
		}
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			System.out.print(cur + " ");
			
			for(int i = 0; i < graph[cur].size(); i++) {
				int tmp = graph[cur].get(i);
				if(!visited[tmp]) {
					q.add(tmp);
					visited[tmp] = true;
				}
					
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수 
		int V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점 
		
		visited = new boolean[N + 1];
		graph = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) { // 그래프 정보를 저장하기 전, List 할당
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			graph[v1].add(v2);
			graph[v2].add(v1);
		}
		
		for(int i = 1; i <= N; i++) {
			Collections.sort(graph[i]);
		}
		
		dfs(V);
		System.out.println();
		
		visited = new boolean[N + 1];
		bfs(V);
	}

}
