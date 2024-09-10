

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 입국심사_3079 {

    static int N;
    static long M, ans;
    static int[] desk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 입국 심사대 개수
        M = Integer.parseInt(st.nextToken()); // 상근이와 친구들

        desk = new int[N];
        int max = 0;
        for(int i = 0; i < N; i++) {
            desk[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, desk[i]);
        }

        Arrays.sort(desk); // 이분탐색을 위한 정렬
        ans = max * M; // 최대로 걸리는 시간 = 사람수 * 가장 오래 걸리는 심사 시간

        search();
        System.out.println(ans);

    }

    static void search(){
        long low = 1;
        long high = ans;

        while(low <= high){
            long mid = (low + high) / 2;
            long people = 0;
            
            for(int time : desk){ // mid 시간 내에 각 심사대에서 처리할 수 있는 사람의 수 계산
                people += mid / time;
                
                if(people >= M) break;
            }

            if(people >= M){ // 주어진 시간 내에 모든 사람을 처리할 수 있는 경우
                high = mid - 1; // 더 작은 시간을 탐색
                ans = mid;
            }
            else{
                low = mid + 1; // 더 큰 시간을 탐색
            }
        }

    }
}
