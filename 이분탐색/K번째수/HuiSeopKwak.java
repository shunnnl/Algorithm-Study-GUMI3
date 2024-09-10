package algo.study.week7;

import java.util.Scanner;

/**
 * 백준 1300 K번째 수
 * 메모리 : 17704 KB
 * 시간 : 180 ms
 */
public class Boj1300 {
	static int N, K;
	static long count;
	static int start, end, mid;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		start = 1;
		end = K;
		
		while(start < end) {
			count = 0;
			mid = (start + end) / 2;
			
			for (int i = 1; i <= N; i++) {
				count += Math.min(mid / i, N);
			}
			if (K <= count) {
				end = mid;
			}
			else {
				start = mid + 1;
			}
		}
		System.out.println(start);
	}
}
