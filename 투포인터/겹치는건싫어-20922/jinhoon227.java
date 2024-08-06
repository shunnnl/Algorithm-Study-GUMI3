
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제: (B20822_겹치는건싫어)
 * 풀이: 투포인터
 * 메모리: 34260kb
 * 시간: 336ms
 */
class B20822_겹치는건싫어 {

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int start = 1; // 순열의 시작 인덱스
        int[] check = new int[100001]; // 중복 개수 확인용 배열

        int result = -1;
        int count = 0;
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());

            arr[i] = num;
            check[num]++;

            // 임의의 수가 중복의 개수가 K를 초과한다면
            // 해당 수를 만날때까지 시작점을 옮기면서, 중복 개수와 현재 길이를 줄여준다.
            while (check[num] > K) {
                check[arr[start]]--;
                start++;
                count--;
            }

            // 임의의 수가 K 이하라면, 길이 증가
            count++;
            result = Math.max(result, count);
        }

        // 임으의 수가 K를 초과하지 않을 경우를 대비해 result 업데이트
        result = Math.max(result, count);
        System.out.println(result);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new B20822_겹치는건싫어().solution();
    }
}