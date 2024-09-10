package algo.study.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 22871 징검다리 건너기 메모리 : KB 시간 : ms
 */
public class Boj22871 {
	static int N;
	static long A[];
	static long dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = new long[N];
		dp = new long[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Long.parseLong(st.nextToken());
			dp[i] = Long.MAX_VALUE;
		}
		// 입력 끝
		// 범위 돌면서 최소값을 dp 배열에 저장해준다.
		dp[0] = 0;
		for (long i = 1; i < N; i++) {
			for (long j = 0; j < i; j++) {
				dp[(int) i] = Math.min((i - j) * (1 + Math.abs(A[(int) i] - A[(int) j])), dp[(int) i]);
			}
		}
		System.out.println(dp[N - 1]);
	}
}