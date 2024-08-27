import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 메모리 : 15828kb / 시간 : 112ms
public class Q_16953 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int a=Integer.parseInt(st.nextToken()); // 숫자 a
        int b=Integer.parseInt(st.nextToken()); // 숫자 b
        int cnt=0; // 연산 횟수


        while (a!=b) {
            if (b % 2 != 0 && b % 10 != 1) {
                cnt = -1;
                break;
            }
            if (b < a) {
                cnt = -1;
                break;
            }

            if (b % 2 == 0) {
                b /= 2;

            }
            else if (b % 10 == 1) { // 끝자리가 1이라면
                b /= 10;
            }
            cnt++;

            /*
            String str=Integer.toString(b);
            if(str.charAt(str.length()-1)=='1') {
                str=str.substring(0,str.length()-1);
                b=Integer.parseInt(str);
                cnt++;
            }
             */
        }
        if(cnt==-1)bw.write(-1+"\n");
        else bw.write((cnt+1)+"\n");

        bw.flush();
        bw.close();
    }
}
