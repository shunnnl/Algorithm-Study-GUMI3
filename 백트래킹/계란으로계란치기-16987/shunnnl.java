import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 16448kb / 시간 : 196ms
public class Q_16987 {
    static int N; // 계란의 수
    static int[][] arr; // [0] : 내구도, [1] : 무게
    static int res=Integer.MIN_VALUE; // 깰 수 있는 최대 계란의 수

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        N=Integer.parseInt(br.readLine());
        arr=new int[N][2];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            arr[i][0]=Integer.parseInt(st.nextToken());
            arr[i][1]=Integer.parseInt(st.nextToken());
        }

        simulation(0,0);
        System.out.println(res);
    }

    public static void simulation(int now, int cnt){ // now : 현재 들고 있는 계란의 위치, cnt : 현재 깨진 계란 수
        if(cnt==N-1 || now==N){ // 계란이 다 깨지거나 끝까지 갔을 경우는 max값 찾기
            res=Math.max(res,cnt);
            return;
        }

        if(arr[now][0]<=0){ // 현재 들고 있는 계란의 내구도가 작으면 깰 수 없음
            simulation(now+1,cnt);
        } else{
            for(int i=0;i<N;i++){
                if(i==now) continue; // 현재 들고 있는 걸로 다른 걸 쳐야하니까 pass
                if(arr[i][0]>0){
                    arr[i][0]-=arr[now][1];
                    arr[now][0]-=arr[i][1];
                    simulation(now+1,cnt+(arr[i][0]<=0?1:0)+(arr[now][0]<=0?1:0)); // 계란이 깨졌으니까 +1
                    arr[i][0]+=arr[now][1];
                    arr[now][0]+=arr[i][1];
                }

            }
        }

    }
}
