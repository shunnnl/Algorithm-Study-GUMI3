import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 16953. AtoB
 * 메모리: 14216 KB
 * 시간: 104 ms
 */
public class AtoB_16953 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int count = 1;
        while(A != B){
            if(A > B) {
                System.out.println(-1);
                return;
            }

            if(B % 2 == 0){ // 2로 나누어 떨어지는 경우
                B /= 2;
            }
            else if(B % 10 == 1){ // 마지막 자리가 1인 경우
                B /= 10;
            }
            else{ // 둘다 해당하지 않는 경우
                System.out.println(-1);
                return;
            }
            count++;
        }

        System.out.println(count);
    }
}
