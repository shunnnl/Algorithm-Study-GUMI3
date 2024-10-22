import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 14440kb / 시간 : 108ms
public class BOJ_14503 {
    static int N,M; // 방의 크기
    static int r,c,d; // 처음 좌표, 로봇 청소기 바라보는 방향(0:북쪽, 1:동쪽, 2:남쪽, 3:서쪽)
    static int[][] arr; // 장소의 상태 (0 : 청소x 빈 칸, 1 : 벽)
    static int[] drow = {-1,0,1,0};
    static int[] dcol = {0,1,0,-1};
    static int cnt=1;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr=new int[N][M];

        st=new StringTokenizer(br.readLine());
        r=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());
        d=Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dfs(r,c,d);
        System.out.println(cnt);
    }

    private static void dfs(int r, int c, int dir){
        arr[r][c]=2; // 현재 위치 청소

        for(int i=0;i<4;i++){ // 청소 가능한 곳 탐색
            dir=(dir+3)%4; // 반시계 방향으로 변환
            int nr=r+drow[dir];
            int nc=c+dcol[dir];

            // 청소 안 된 곳이 있으면
            if(nr>=0&&nr<N&&nc>=0&&nc<M&&arr[nr][nc]==0){
                cnt++;
                dfs(nr,nc,dir);

                return; // 다른 방향 탐색 필요x
            }
        }

        // 청소되지 않은 빈 칸이 없는 경우
        int br=r+drow[(dir+2)%4]; // 바라보는 방향 유지한 채로 후진
        int bc=c+dcol[(dir+2)%4];

        if(br>=0&&br<N&&bc>=0&&bc<M&&arr[br][bc]!=1) { // 벽이 아닌 경우
            dfs(br,bc,dir);
        }
    }
}
