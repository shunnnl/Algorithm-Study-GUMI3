import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] sum = new int[N+1][M+1];
		int[][] ground = new int[N+1][M+1];
		int maxScore = Integer.MIN_VALUE;

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				ground[i][j] = Integer.parseInt(st.nextToken());
				sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] + ground[i][j];
				maxScore = Math.max(maxScore, sum[i][j]);
			}
		}
		
		
		for(int x1 = 1; x1 <= N; x1++) {
			for(int y1 = 1; y1 < M; y1++) {
				for(int x2 = x1; x2 <= N; x2++) {
					for(int y2 = y1; y2 <= M; y2++) {
						int tmp = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
						maxScore = Math.max(maxScore, tmp);
					}
				}
			}
		}
		
		System.out.println(maxScore);

	}

}