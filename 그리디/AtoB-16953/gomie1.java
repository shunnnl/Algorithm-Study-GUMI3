import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 : A to B
 * 풀이 : 그리디
 * 메모리 : 14236KB
 * 시간 : 100ms
 */

public class _16953_AtoB {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int res = 1;
        while(A != B) {
            if(B < A) {
                res = -1;
                break;
            }

            if(B % 2 == 0) {
                B /= 2;
            }
            else if(B % 10 == 1) {
                B /= 10;
            }
            else {
                res = -1;
                break;
            }

            res++;
        }

        System.out.println(res);
    }

}