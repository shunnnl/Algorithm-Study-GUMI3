import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 37764kb / 시간 : 616ms
public class Main {
    static int n;
    static int m;
    static int max = Integer.MIN_VALUE;
    static int arr[][];
    static boolean visited[][];
    static int[] drow={-1,0,1,0}; // 4가지 방향
    static int[] dcol={0,1,0,-1};

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken()); // 세로 크기 n
        m=Integer.parseInt(st.nextToken()); // 가로 크기 m
        arr=new int[n][m]; // 종이 2차원 배열
        visited=new boolean[n][m]; // 방문 여부 2차원 배열

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        // 2중 for문으로 탐색
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                visited[i][j]=true;
                dfs(i,j,1,arr[i][j]);
                visited[i][j]=false; // 또 다시 탐색이 필요하기에 false 처리 필요

                another_dfs(i,j); // ㅗ, ㅜ, ㅓ, ㅏ 탐색

            }
        }
        System.out.println(max);
    }

    // ㅗ를 제외한 나머지 모양 탐색
    public static void dfs(int row, int col, int cnt, int sum){
        if(cnt==4){ // 깊이 4가 되면 모든 모양 만들 수 있음(ㅗ를 제외하고)
            max=Math.max(max,sum);
            return;
        }
        for(int d=0;d<4;d++){
            int nrow=row+drow[d];
            int ncol=col+dcol[d];

            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m){
                if(!visited[nrow][ncol]){
                    visited[nrow][ncol]=true;
                    dfs(nrow,ncol,cnt+1,sum+arr[nrow][ncol]);
                    visited[nrow][ncol]=false;
                }
            }
        }
    }

    // ㅜ, ㅏ, ㅓ, ㅗ 모양 (십자가 모양했을 때 중앙을 기점으로)
    public static void another_dfs(int row, int col){
        if(row+1<n && col-1>=0 && col+1<m){
            max=Math.max(max, arr[row][col]+arr[row+1][col]+arr[row][col-1]+arr[row][col+1]);
        }
        if(row+1<n && row-1>=0 && col+1<m){
            max=Math.max(max, arr[row][col]+arr[row+1][col]+arr[row-1][col]+arr[row][col+1]);
        }
        if(row+1<n && row-1>=0 && col-1>=0){
            max=Math.max(max, arr[row][col]+arr[row+1][col]+arr[row-1][col]+arr[row][col-1]);
        }
        if(row-1>=0 && col-1>=0 && col+1<m){
            max=Math.max(max, arr[row][col]+arr[row-1][col]+arr[row][col-1]+arr[row][col+1]);
        }
    }
}
