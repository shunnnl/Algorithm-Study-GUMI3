import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리: 14372 KB
 * 시간: 108 ms
 */
public class 로봇청소기_14503 {

	static int N, M, cleaning;
	static int[][] map;
	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 0: 북, 1: 동, 2: 남, 3: 서

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		map = new int[N][M];

		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cleaning = 0;
		dfs(startX, startY, dir);

		System.out.println(cleaning);
	}

	private static void dfs(int x, int y, int dir) {
		if (map[x][y] == 0) {
            map[x][y] = 2; // 청소 완료 표시
            cleaning++;
        }
		
		for (int i = 0; i < 4; i++) {
			dir = (dir + 3) % 4;
			int nx = x + d[dir][0];
			int ny = y + d[dir][1];

			if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
				if (map[nx][ny] == 0) {
					dfs(nx, ny, dir);
					return;
				}
			}
		}

		int nextDir = (dir + 2) % 4;
		int nx = x + d[nextDir][0];
		int ny = y + d[nextDir][1];
		if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 1) {
			dfs(nx, ny, dir); // 반대 방향으로 이동만 하기, 현재 로봇 방향은 유지
		}
	}

}
