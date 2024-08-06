import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열의 길이
        int K = Integer.parseInt(st.nextToken()); // 같은 정수의 개수

        // 수열 생성 및 초기화
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[100001];
        int start = 0;
        int end = 0;
        int ans = Integer.MIN_VALUE;

        while(start <= end) { // 수열 전체 탐색
            if(start <= N-1 && cnt[arr[end]] < K) { // 길이를 늘릴 수 있는 경우
                cnt[arr[end]]++;
                end++;
            }
            else if(cnt[arr[end]] == K) { // 길이를 늘릴 수 없는 경우
                cnt[arr[start]]--;
                start++;
            }

            ans = Math.max(ans, end - start); // 최장 길이 찾기

            if(end == N) {
                break; // end 포인터가 수열의 끝에 도착하면 종료
            }
        }

        System.out.println(ans); // 출력

    }

}
