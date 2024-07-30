import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] visited;
	static List<Integer>[] computer;
	static int[] hacking;
	static int N, M, maxCnt;
    

	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 컴퓨터 갯수 = 노드
		M = Integer.parseInt(st.nextToken()); // 신뢰하는 관계 갯수 = 간선
		
		computer = new ArrayList[N + 1]; // 컴퓨터 신뢰 관계 
		visited = new boolean[N + 1]; // 방문 여부 
		hacking = new int[N + 1]; // 해킹 가능한 컴퓨터 갯수 카운트 
		maxCnt = Integer.MIN_VALUE; // 최대값 저장하는 변수 

		
		for(int i = 0; i <= N; i++) {
			computer[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			// A가 B를 신뢰한다 = B를 해킹하면 A도 해킹할 수 있다 
			computer[B].add(A);
		}
		
		
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N + 1]; // 매번 방문 초기화 
			
			Queue<Integer> q = new LinkedList<>();
			q.add(i);
			visited[i] = true;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				for(int next: computer[cur]) {
					if(!visited[next]) {
						hacking[i]++;
						q.add(next);
						visited[next] = true;
					}
				}
			}
			
			maxCnt = Math.max(maxCnt, hacking[i]); // 해킹 가능한 컴퓨터 최대값 구하기
		}
		
		for(int i = 1; i <= N; i++) { // 최댓값인 컴퓨터 출력하기 
			if(hacking[i] == maxCnt)
				sb.append(i).append(" ");
		}
		
		System.out.println(sb.toString());
		
	}

}
