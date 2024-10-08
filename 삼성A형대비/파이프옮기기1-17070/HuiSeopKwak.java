package algo.study.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 백준 17070 파이프 옮기기 1
 * 메모리 : 15300 KB
 * 시간 : 204 ms 
 */
public class Boj17070 {
	
	static int N;
	static int[][] map;	// 맵 
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝
		
		dfs(0,1,0);
		System.out.println(result);
	}

	// direction 0 : 가로, 1 : 대각선, 2 : 세로
	public static void dfs(int x, int y, int direction) {
//		System.out.println("x : " + x + " y : " + y + " direction : " + direction);
		if (map[x][y] == 1)	// 벽지면 출발 X
			return;
		
		if (x == N-1 && y == N-1) {
			result++;
			return;
		}
		
		switch(direction) {
		case 0:	// 가로모양일때
			if (x < N && y + 1 < N)
				dfs(x, y + 1, 0);
			if (x + 1 < N && y + 1 < N){
				// 대각선인 경우 가는 방향 위 옆도 벽지가 있는지 확인해야 한다.
				if (map[x + 1][y] != 1 && map[x][y + 1] != 1 && map[x + 1][y + 1] != 1)	 
					dfs(x + 1, y + 1, 1);
			}
			break;
			
		case 1:	// 대각선 모양일때
			if (x < N && y + 1 < N)
				dfs(x, y + 1, 0);
			if (x + 1 < N && y + 1 < N) {
				// 대각선인 경우 가는 방향 위 옆도 벽지가 있는지 확인해야 한다.
				if (map[x + 1][y] != 1 && map[x][y + 1] != 1 && map[x + 1][y + 1] != 1)	 
					dfs(x + 1, y + 1, 1);
			}
				
			if (x + 1 < N && y < N)
				dfs(x + 1, y, 2);
			break;
			
		case 2: // 세로 모양일때
			if (x + 1 < N && y + 1 < N){
				// 대각선인 경우 가는 방향 위 옆도 벽지가 있는지 확인해야 한다.
				if (map[x + 1][y] != 1 && map[x][y + 1] != 1 && map[x + 1][y + 1] != 1)	 
					dfs(x + 1, y + 1, 1);
			}
			if (x + 1 < N && y < N)
				dfs(x + 1, y, 2);
		}
	}
}