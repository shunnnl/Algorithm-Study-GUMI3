import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 메모리 : 31692kb, 시간 : 432ms
public class Q_3079 {
    static int n; // 입국 심사대 개수
    static long m, max; // 사람 수, 입국 심사대 중 최대 시간
    static int [] arr; // 입국 심사대 별 걸리는 시간
    static long result = Long.MAX_VALUE; // 심사 최소 시간

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max,arr[i]);
        }
        Arrays.sort(arr); // 이분 탐색을 위한 정렬

        biSearch(); // 이분 탐색

        System.out.println(result);
    }

    private static void biSearch(){
        long low = 0;
        long high = m * max; // 가장 오래 걸리는 시간

        while(low<=high){
            long mid = (low+high)/2;
            long sum = 0; // 심사대에서 처리할 수 있는 사람 수
            for(long index: arr){
                long count = mid/index;
                sum+=count;
                if(sum>=m){ // 줄여야하기 때문에 가지치기
                    break;
                }
            }
            if(sum>=m){
                high = mid-1;
                result = Math.min(mid,result);
            }
            else{
                low = mid+1;
            }
        }
    }
}