import java.io.*;
import java.util.*;

// 메모리 : 13672kb / 시간 : 260ms
public class BOJ_2146 {
    static int N; // 지도의 크기
    static int[][] arr; // 섬 2차원 배열
    static boolean[][] checked; // 방문 체크
    static int groupNum; // 섬 별 분류
    static int min=Integer.MAX_VALUE; // 가장 짧은 다리의 길이
    static Queue<int[]> q; // queue
    static int[] drow={-1,0,1,0};
    static int[] dcol={0,1,0,-1};

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        N=Integer.parseInt(br.readLine());
        arr=new int[N][N];
        checked=new boolean[N][N];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!checked[i][j] && arr[i][j]!=0){
                    checked[i][j]=true;
                    arr[i][j]+=groupNum;
                    makeGroup(i,j);
                    groupNum++;
                }
            }
        }

        checked=new boolean[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!checked[i][j]&&arr[i][j]!=0){
                    checked[i][j]=true;
                    cntBridge(i,j,arr[i][j]);
                    checked=new boolean[N][N];
                }
            }
        }

        System.out.println(min==Integer.MAX_VALUE?"0":min);
    }

    // 섬 group으로 뷴류
    private static void makeGroup(int i, int j) {
        q=new LinkedList<>();
        q.add(new int[]{i,j});

        while(!q.isEmpty()){
            int[] cur=q.poll();

            for(int d=0;d<4;d++){
                int nr=cur[0]+drow[d];
                int nc=cur[1]+dcol[d];

                if(nr>=0&&nr<N&&nc>=0&&nc<N&&!checked[nr][nc]){
                    if(arr[nr][nc]!=0){
                        checked[nr][nc]=true;
                        arr[nr][nc]+=groupNum;
                        q.add(new int[]{nr,nc});
                    }
                }
            }
        }
    }

    // 가장 적은
    private static void cntBridge(int i, int j, int groupNum) {
        q.clear();
        q.add(new int[]{i,j,0});

        while(!q.isEmpty()){
            int[] cur=q.poll();

            if(cur[2]>=min) continue;

            for(int d=0;d<4;d++){
                int nr=cur[0]+drow[d];
                int nc=cur[1]+dcol[d];

                if(nr<0||nr>=N||nc<0||nc>=N)continue; // 범위 넘어서면
                if(checked[nr][nc]) continue; // 체킹 됬으면
                if(arr[nr][nc]==groupNum) continue; // 섬일 경우

                if(arr[nr][nc]==0){ // 바다일 경우
                    checked[nr][nc]=true;
                    q.add(new int[]{nr,nc,cur[2]+1});
                }else{ // 다른 섬일 경우
                    min=Math.min(min,cur[2]);
                }
            }
        }
    }

}
