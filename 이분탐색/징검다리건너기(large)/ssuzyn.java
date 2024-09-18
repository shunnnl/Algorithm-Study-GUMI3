import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] bridge, dp;
	static int N;
	
	public static long jump(int index) {
		if(index == N-1) return 0;
		
		if(dp[index] != -1) return dp[index];
		
		dp[index] = Long.MAX_VALUE;
		for(int i = index+1; i < N; i++) {
			long power = (i - index) * (1 + Math.abs(bridge[index] - bridge[i]));
			dp[index] = Math.min(dp[index], Math.max(jump(i), power)); // index에서 i로 점프한 후 그 이후 경로에서 발생할 수 있는 최대 힘
			/**
			 * 가장 왼쪽 돌에서 출발하여 가장 오른쪽에 있는 돌로 건너갈 수 있는 모든 경우 중 가능한 K의 최솟값을 출력한다.
			 * dp[index] : 0번 다리에서 index번 다리까지 오는데 필요한 최소 힘
			 * 
			 * 최소 힘은 index -> i로 갈때 드는 힘과 0 -> index 까지 오는데 드는 힘(dp[j]) 중 더 큰 힘이 될 것입니다.
			 */
		}
		return dp[index];
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		bridge = new long[N];
		dp = new long[N];
		for(int i = 0; i < N; i++) {
			bridge[i] = Integer.parseInt(st.nextToken());
			dp[i] = -1;
		}
		
		System.out.println(jump(0));
	
	}
}
