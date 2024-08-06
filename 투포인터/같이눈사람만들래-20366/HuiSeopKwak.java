package algo.study.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj20366 {
	static int N;
	static int[] snow;
	static int sum1 = 0;
	static int sum2 = 0;
	static int start;
	static int end;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		snow = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			snow[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(snow);	// 배열 정렬
		
		// 3중 반복문 O(N^3)
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				sum1 = snow[i] + snow[j];	// 2중 for 문 돌면서 모든 i, j 값 조건에 대해 탐색
				start = 0;
				end = N - 1;
				while (start < end) {		// i, j 를 선택하는 경우를 제외하고 모든 경우를 돌면서 최소값 찾음
					if (start == i || start == j) {
						start += 1;
						continue;
					}
					if (end == i || end == j) {
						end -= 1;
						continue;
					}
					sum2 = snow[start] + snow[end];
					min = Math.min(min, Math.abs(sum1 - sum2));
					if (min == 0) {			// 최소값이 0이되면 더 작을 수는 없기 때문에 프린트 하고 종료
						System.out.println(0);
						return;
					}
					else if (sum1 > sum2) {	// sum2의 수를 키움
						start++;
					}
					else if (sum1 < sum2) {	// sum2의 수를 줄임
						end--;
					}
				}
			}
		}
		System.out.println(min);

	}

}
