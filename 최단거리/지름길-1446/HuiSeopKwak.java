package algo.study.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 1446 지름길
 * 메모리 : 14328 KB
 * 시간 : 108 ms
 */
public class Boj1446{
	static int N, D;	// N: 지름길 수, D: 고속도로 길이
	static int distance[];
	
	static class shortcut implements Comparable<shortcut>{
		int s;
		int e;
		int w;
		
		shortcut(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(shortcut o) {
			return this.s == o.s ? this.e - o.e : this.s - o.s;
		}
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		shortcut[] shortcuts = new shortcut[N];
		distance = new int[D+1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			shortcuts[i] = new shortcut(s, e, w);
		}
		// 입력 완료
		
		// sort하기 (출발위치, 도착위치 순으로)
		Arrays.sort(shortcuts);
		
		// 1씩 올려가며 고속도로의 끝까지 도착할때 배열 값 초기화
		for (int i = 1; i <= D; i++) {
			distance[i] = distance[i - 1] + 1;
		}
		
		// 1씩 올려가며 고속도로의 끝까지 도착할때 배열 값 초기화하며 매 순간 shortcut부분 확인하여 최소값 갱신
		for (int i = 1; i <= D; i++) {
			for (shortcut sc : shortcuts) {
				int s = sc.s;
				int e = sc.e;
				int w = sc.w;
				
				if (e <= D) {
					distance[e] = Math.min(distance[e], distance[s] + w);
				}
			}
			distance[i] = Math.min(distance[i], distance[i-1] + 1);
		}
		System.out.println(distance[D]);
	}

}
