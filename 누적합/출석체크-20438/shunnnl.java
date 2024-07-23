import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생 수 n
        int k = Integer.parseInt(st.nextToken()); // 졸고 있는 학생 수 k
        int q = Integer.parseInt(st.nextToken()); // 출석 코드를 보낼 학생 수 q
        int m = Integer.parseInt(st.nextToken()); // 주어질 구간의 수 m
        int arr[]=new int[n+3]; // 학생 출석 여부 판단 배열

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++){
            int tmp = Integer.parseInt(st.nextToken());
            arr[tmp]=-1; // 조는 학생 -1로 checking
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<q;i++){
            int tmp = Integer.parseInt(st.nextToken());
            if(arr[tmp]==-1)continue;
            for(int j=tmp;j<n+3;j+=tmp){
                if(arr[j]!=-1) arr[j]=1;
            }
        }

        int[] sum = new int[n+3]; // 누적 합 구하기 위한 배열
        for(int i=3;i<n+3;i++){
            sum[i]=sum[i-1]+(arr[i]!=1?1:0);
        }

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            System.out.println(sum[e]-sum[s-1]);
        }
    }
}
