package algo.study.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 2212 센서
 * 메모리 : 16900 KB
 * 시간 : 160 ms
 *
 */
public class Boj2212 {
    static int N, K;    // N개의 센서, K개의 집중국
    static int [] sensors;    // 센서 배열
    static int [] diff;    // 센서 거리 차이 배열
    static int sum;    // 결과값
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력 받기
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        
        sensors = new int[N];
        diff = new int[N-1];
        
        for (int i = 0; i < N; i++)    // 센서 좌표 위치 입력 받기 
            sensors[i] = Integer.parseInt(st.nextToken());
        // 입력 끝
        
        Arrays.sort(sensors);    // 오름차순 순서대로 정렬
        
        for (int i = 0; i < N-1; i++) {
            diff[i] = sensors[i+1] - sensors[i]; 
        }
        
        Arrays.sort(diff);    // 오름차순 순서대로 정렬 (뒤로 갈수록 큰 차이가 나는 값)
        
        for (int i = 0; i < N-K; i++) {    // 최대 차이 나는 것들에 대해서 빼주면 된다는데
            sum += diff[i];    // 이해가 안돼
        }
        
        System.out.println(sum);
    }
}