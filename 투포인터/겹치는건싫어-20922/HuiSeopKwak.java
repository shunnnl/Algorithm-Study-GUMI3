package algo.study.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj20922 {
//public class Main {
	static int N;
	static int K;
	static int[] arr;
	static int[] cntArr = new int[100001];	// 숫자별 개수 저장할 배열
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int p1 = 0;		// 포인터 1번
		int p2 = 0;		// 포인터 2번
		int max = 0;	// max 길이 저장용
		int len = 0;	// 길이 계산용
		
		while (p2 != N) {	// 두번재 포인터가 끝을 만날때까지 반복
			cntArr[arr[p2++]] += 1;		// 두번재 포인터 값에 해당하는 개수 증가 및 포인터 오른쪽으로 보냄
			if (cntArr[arr[p2-1]] <= K) {	// 두번째 포인터에 있던 값의 개수가 K개 이하이면 요건 만족하기 때문에 len 계산해서 최대값 검사
				len = p2-p1;
				max = Math.max(max, len);
			}
			else {							// 두번재 포인터에 있던 값의 개수가 K개 초과일 경우 첫번째 포인터를 오른쪽을 옮기면서 K개 이하가 될때까지 반복
				while(!(cntArr[arr[p2-1]] <= K)) {
					cntArr[arr[p1++]] -= 1;
				}
			}
			
		}
		System.out.println(max);
	}

}
