import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 : 15140kb, 메모리 : 172ms
public class Q_22871{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 돌의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1]; // 돌의 힘을 나타내는 배열
        long[] dp = new long[N + 1]; // 돌까지 도달하는데 최소 힘 값을 나타내는 배열
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[1] = 0; // 처음 돌은 무조건 0

        for (int i = 2; i <= N; i++) { // i란 목표까지 도달하는데 발생하는 힘
            dp[i] = Long.MAX_VALUE; // 초기화
            for (int j = 1; j < i; j++) {
                long k = power(j, i, arr[i], arr[j]); // j에서 i로 이동할 시 힘의 값
                dp[i] = Math.min(dp[i], Math.max(k, dp[j]));
            }
        }

        System.out.println(dp[N]);
    }

    public static long power(int i, int j, int s, int e) {
        return (long) (j - i) * (1 + Math.abs(s - e));
    }
}