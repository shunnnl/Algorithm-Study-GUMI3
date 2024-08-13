package algo.study.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj11728 {
//public class Main {
	// 받을 배열들과 정리할 배열 선언
	static int[] arr1;
	static int[] arr2;
	static int[] result;
	static final int MAX_VALUE = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 입력 받는 부분
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int len = n+m;
		arr1 = new int[n+1];
		arr2 = new int[m+1];
//		result = new int[len];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		int p1 = 0;
		int p2 = 0;
//		int idx = 0;
		
		arr1[n] = MAX_VALUE;	// 마지막 값에 maxvalue를 넣음으로서 배열 초과부분에 대
		arr2[m] = MAX_VALUE;	// 조건문 검사를 따로 하지 않아도 된다.
		
		while (!(p1 == n && p2 == m)) {	// 양 배열의 끝에 왔을때 종료
			if (arr1[p1] < arr2[p2]) {	// arr1의 포인터가 가리키고 값이 작은 경우에 배열에 추가
				sb.append(arr1[p1++] + " ");
//				result[idx++] = arr1[p1++];
			}
			else { 						// 반대로 arr2 포인터가 가리키고 있는 값이 더 작을때
				sb.append(arr2[p2++] + " ");
//				result[idx++] = arr2[p2++];
			}
			
		}
//		for (int i = 0; i < len; i++) {
//			System.out.print(result[i] + " ");
//		}
		System.out.println(sb);
	}

}
