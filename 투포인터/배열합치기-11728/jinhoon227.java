
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: (B11728_배열합치기)
 *
 * 메모리: 296684kb
 * 시간: 1572ms
 */
class B11728_배열합치기 {

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N + 1];
        int[] B = new int[M + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        // == 입력 끝 == //

        // 마지막 값은 최대값으로 설정
        A[N] = Integer.MAX_VALUE;
        B[M] = Integer.MAX_VALUE;

        int aPoint = 0;
        int bPoint = 0;

        // A, B 는 이미 정렬되어있으므로
        // A, B 배열을 서로 비교하면서 작은 순으로 출력해주면 된다.
        // 배열의 마지막 값은 최대값이므로 한쪽이 먼저 최대에 도달하면 다른 나머지도 최대값에 도달할 때 까지 출력한다.
        while (aPoint != N || bPoint != M) {

            if (A[aPoint] < B[bPoint]) {
                result.append(A[aPoint]).append(" ");
                aPoint++;
            } else {
                result.append(B[bPoint]).append(" ");
                bPoint++;
            }
        }

        System.out.println(result);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new B11728_배열합치기().solution();
    }
}