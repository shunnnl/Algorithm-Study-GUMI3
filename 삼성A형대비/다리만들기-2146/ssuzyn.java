import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리: 137780 KB
 * 시간: 264 ms
 */
public class 다리만들기_2146 {

	static int N, answer;
	static boolean[][] visited;
	static int[][] map;
	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];


		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 구분해주기
		int num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					setIsland(i, j, num++);
				}
			}
		}
		
		// 섬과 섬 사이의 최단거리 구하기
		answer = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 0) {
					visited = new boolean[N][N];
					findBridge(i, j, map[i][j]);
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void setIsland(int x, int y, int num) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visited[x][y] = true;
		map[x][y] = num;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				if(map[nx][ny] == 1) {
					visited[nx][ny] = true;
					map[nx][ny] = num;
					q.add(new int[] {nx, ny});
				}
			}
		}
	}
	
	private static void findBridge(int x, int y, int group) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y, 0});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int bridge = cur[2];
			
			if(bridge >= answer) continue; 
			
			for(int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
				if(map[nx][ny] == group) continue;
				
				if(map[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny, bridge + 1});
				}
				else {
					answer = Math.min(answer, bridge);
				}
				
			}
		}
	}
		

}
