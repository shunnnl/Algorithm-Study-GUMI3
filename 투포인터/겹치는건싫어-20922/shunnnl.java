import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 같은 정수 k개 이하 포함한 최장 연속 부분 수열 길이 구하는 프로그램
// 32860kb / 368ms
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken()); // 길이가 n인 수열
        int k=Integer.parseInt(st.nextToken()); // 같은 정수 k개
        int[] arr=new int[n]; // 배열
        int[] cnt=new int[100001]; // 등장 cnt 나타내는 배열

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int p1=0; // 수열 처음 가리키는 포인터
        int p2=0; // 이동 포인터
        int result=0; // 최장 부분 수열 길이

        while(p1<n && p2<n){
            if(cnt[arr[p2]]<k){
                ++cnt[arr[p2]];
                p2++;
            }else{
                --cnt[arr[p1]];// 기존에 가리키고 있었던 부분 빼기
                p1++;
            }
            result=Math.max(result,p2-p1);
        }
        System.out.println(result);

    }
}