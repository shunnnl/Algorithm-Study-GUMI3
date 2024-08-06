import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 첫번째 눈사람의 눈덩이를 이중 반복문으로 지정, 두번째 눈사람의 눈덩이 투포인터 사용
// 하나의 눈사람 고정하고, 다른 최적 눈사람 구하기
// 14888kb / 728ms
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken()); // 개수 n
        int[] snow = new int[n]; // 눈덩이 지름 배열
        int min=Integer.MAX_VALUE; // 두 눈사람의 키 차이 최솟값

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            snow[i]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snow);
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int p1=0; // 처음부터 가리키는 포인터
                int p2=n-1; // 끝부터 가리키는 포인터
                while(p1<p2){
                    // 첫번째 눈사람일 경우
                    if(p1==i||p1==j){
                        p1++;
                        continue;
                    }
                    if(p2==i||p2==j){
                        p2--;
                        continue;
                    }
                    min=Math.min(min,Math.abs((snow[i]+snow[j])-(snow[p1]+snow[p2])));
                    if(snow[i]+snow[j]<snow[p1]+snow[p2])p2--;
                    else p1++;
                }
            }
        }

        System.out.println(min);

    }
}