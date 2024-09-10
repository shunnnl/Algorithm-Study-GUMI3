import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 배열 크기
        int K = Integer.parseInt(br.readLine()); // 구해야 하는 k번째 값

        // 이분 탐색을 위한 범위 설정
        long low = 1;
        long high = (long) N * N;
        long answer = 0;

        while (low <= high) {
            long mid = (low + high) / 2;
            long count = 0;

            // mid보다 작거나 같은 값의 개수 세기
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N); // 각 행에서 mid보다 작은 값의 개수
            }

            if (count >= K) {
                answer = mid; // K번째 값이 mid 이하라는 뜻이므로, mid를 답으로 설정
                high = mid - 1; // 더 작은 값을 찾기 위해 high를 줄임
            } else {
                low = mid + 1; // 더 큰 값을 찾기 위해 low를 늘림
            }
        }

        System.out.println(answer);
    }
}
