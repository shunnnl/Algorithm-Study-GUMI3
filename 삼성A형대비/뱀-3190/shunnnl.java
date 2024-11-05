import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

// 메모리 : 14584kb / 시간 : 124ms
public class BOJ_3190 {
    static int N; // 보드의 크기
    static int K; // 사과의 개수
    static int L; // 뱀의 방향 변환 횟수
    static int[][] map;
    static HashMap<Integer,String> hash=new HashMap<>(); // 뱀의 방향 변환 정보
    static List<int[]> snake=new ArrayList<>(); // 탐색을 위한 배열 리스트
    static int[] drow={0,1,0,-1}; // 동, 남, 서, 북
    static int[] dcol={1,0,-1,0};

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        N=Integer.parseInt(br.readLine());
        K=Integer.parseInt(br.readLine());
        map=new int[N][N];

        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            map[a-1][b-1]=1; // 사과의 위치
        }
        L=Integer.parseInt(br.readLine());
        for(int i=0;i<L;i++){
            st=new StringTokenizer(br.readLine());
            int X=Integer.parseInt(st.nextToken());
            String C=st.nextToken();
            hash.put(X,C);
        }

        sol();
    }

    private static void sol(){
        // 게임이 시작할때 뱀은 맨위 맨좌측에 위치, 뱀의 길이는 1, 오른쪽을 향함
        int crow=0, ccol=0;
        int time=0;
        int dir=0;
        snake.add(new int[]{0,0});

        loop:while(true){
            time++; // 시간 플러스

            int nrow=crow+drow[dir];
            int ncol=ccol+dcol[dir];

            if(nrow<0 || nrow>=N || ncol<0 || ncol>=N) break; // 범위 벗어날 경우 종료
            for (int i = 0; i < snake.size(); i++) { // 몸 만날 경우 종료
                int[] temp = snake.get(i);
                if (nrow == temp[0] && ncol == temp[1])
                    break loop;
            }

            if(map[nrow][ncol]==1){ // 사과 있을 때
                map[nrow][ncol]=0;
                snake.add(new int[]{nrow,ncol});
            } else{ // 사과 없을 때
                snake.add(new int[]{nrow,ncol});
                snake.remove(0); // 꼬리 제거
            }

            if(hash.containsKey(time)){ // 방향 회전하는 시간 만날 경우
                if (hash.get(time).equals("D")) {
                    dir=(dir+1)%4;
                } else {
                    dir-=1;
                    if(dir==-1) dir=3;
                }
            }

            crow=nrow;
            ccol=ncol;
        }

        System.out.println(time);
    }



}
