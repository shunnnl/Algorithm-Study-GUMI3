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
			dp[index] = Math.min(dp[index], Math.max(jump(i), power));
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
