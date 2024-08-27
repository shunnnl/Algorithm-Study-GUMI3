import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 문제 : 센서
 * 풀이 : 그리디
 * 메모리 : 16436KB
 * 시간 : 180ms
 */

public class _2212_센서 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 센서의 개수
        int K = Integer.parseInt(br.readLine()); // 집중국의 개수

        // 집중국의 갯수가 센서의 갯수보다 크거나 같은 경우
        // 각각의 집중국이 각각의 센서를 담당하면 되므로, 각 집중국의 거리와 함은 0
        int res = 0;
        if (K >= N) {
            System.out.println(res);
            return;
        }

        // 센서의 좌표 입력 받기
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr); // 오름차순 정렬

        // 각 센서의 거리 차이를 담은 배열 생성
        Integer[] diff = new Integer[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diff[i] = arr[i+1] - arr[i];
        }

        Arrays.sort(diff, Collections.reverseOrder()); // 내림차순 정렬
        for (int i = K - 1; i < N - 1; i++) {
            res += diff[i];
        }
        System.out.println(res);
    }

}