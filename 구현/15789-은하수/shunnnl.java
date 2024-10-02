import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 메모리 : 38212kb, 시간 : 340ms
public class Q_15787 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n; // 기차의 수
        int m; // 명령의 수
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        int[] trains=new int[n+1];
        Set<Integer> trainSet=new HashSet<>(); // 중복 제거를 위한 set

        // 비트마스킹이기 때문에 뒤에서부터 좌석
        for(int ㅓ=0;ㅓ<m;ㅓ++){
            st=new StringTokenizer(br.readLine());
            int num=Integer.parseInt(st.nextToken());
            int i=Integer.parseInt(st.nextToken()); // i번째 기차
            if(num==1){
                int x=Integer.parseInt(st.nextToken());
                trains[i]=trains[i]|(1<<x);

            }else if(num==2){
                int x=Integer.parseInt(st.nextToken());
                trains[i]=trains[i]&~(1<<x);

            }else if(num==3){ // 앞으로 가고, 맨 앞 사람 하차
                trains[i]=trains[i]<<1;
                trains[i]=trains[i]&((1<<21)-1);// 가장 좌측 비트는 0

            }else if(num==4){ // 뒤로 가고, 맨 뒷 사람 하차
                trains[i]=trains[i]>>1;
                trains[i]=trains[i]&~1;
            }
        }

        for(int i=1;i<=n;i++){
            trainSet.add(trains[i]);
        }

        System.out.println(trainSet.size());
    }
}
