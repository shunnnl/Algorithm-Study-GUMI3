package algo.study.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 3079 입국심사
 * 메모리 : 23396 KB
 * 시간 : 272 ms
 */
public class Boj3079 {
    static long N, M;
    static int T[];
    static long start, end, mid;
    static long maxTime = -1;
    static long minTime = Long.MAX_VALUE;
    static long sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = new int[(int) N];
        
        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(br.readLine());
            maxTime = Math.max(maxTime, T[i]);
        }
        start = 0;
        end = M * maxTime;
        
        while (start <= end) {	// 이분탐색 start가 end보다 크거나 같아질때 까지 반복
        	mid = (start + end) / 2;
        	sum = 0;
        	for (int i = 0; i < N; i++) {
        		sum += mid / T[i];
        		if (sum >= M) {	// 처리된 사람의 수가 상근이 친구보다 많거나 같아지면 멈추기
        			minTime = Math.min(minTime, mid);
        			end = mid - 1;
        			break;
        		}
        	}
        	if (sum < M)
        		start = mid + 1;
        }
        System.out.println(minTime);
    }
}
