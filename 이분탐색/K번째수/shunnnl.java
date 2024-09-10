import java.io.BufferedReader;
import java.io.InputStreamReader;

// 메모리 : 14236kb, 시간 : 140ms
public class Q_1300 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        int K=Integer.parseInt(br.readLine());

        long low = 1;
        long high = K;

        // lower-bound 왜일까요,,,
        while(low < high) {

            long mid = (low + high) / 2;
            long count = 0;

            for(int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            // count가 더 많을 경우 개수가 더 많다는 거니까 줄일 수 있음
            if(K <= count) {
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }

        System.out.println(low);
    }
}
