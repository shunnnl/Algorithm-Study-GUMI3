import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1차 : List 사용 시 시간 초과
// 317320kb / 1484ms

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken()); // 배열 A의 크기
        int m=Integer.parseInt(st.nextToken()); // 배열 B의 크기
        int[] arr1=new int[n]; // 배열 A
        int[] arr2=new int[m]; // 배열 B
        StringBuilder sb=new StringBuilder();

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr1[i]=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            arr2[i]=Integer.parseInt(st.nextToken());
        }

        int p1=0;
        int p2=0;
        while(p1<n&&p2<m){
            if(arr1[p1]<=arr2[p2]){
                sb.append(arr1[p1++] + " ");
            }
            else{
                sb.append(arr2[p2++] + " ");
            }
        }
        while(p1<n){
            sb.append(arr1[p1++] + " ");
        }
        while(p2<m){
            sb.append(arr2[p2++] + " ");
        }
        System.out.println(sb);

    }
}