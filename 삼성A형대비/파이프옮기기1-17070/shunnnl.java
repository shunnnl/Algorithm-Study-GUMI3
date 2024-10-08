import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 18488kb / 시간 : 364ms
// 가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로 => 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수 구하기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 완탐(dfs)로 풀기

public class BOJ_17070 {
    static int[][] drow= {{0,0,1},{0,1,1},{0,1,1}}; // 가로(0), 세로(1), 대각선(2)에서 갈 수 있는 방향
    static int[][] dcol= {{1,0,1},{0,0,1},{1,0,1}};
    static int n; // 집의 크기
    static int[][] arr; // 집의 상태
    static int cnt=0; // 이동시키는 방법의 수

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        arr=new int[n][n];

        for(int i=0;i<n;i++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,1,0);
        System.out.println(cnt);
    }

    static void dfs(int row, int col, int dir) { // dir : 전에 이동한 방향
        if(row==n-1 && col==n-1) cnt++;

        for(int d=0;d<drow[dir].length;d++) { // 전 이동 방향에 따라 다른 이동 방향 선택
            if(drow[dir][d]==0 && dcol[dir][d]==0) continue; // 제자리면 뛰어넘기
            int nrow=row+drow[dir][d];
            int ncol=col+dcol[dir][d];

            if(nrow>=0&&nrow<n&&ncol>=0&&ncol<n&&arr[nrow][ncol]==0) {
                if(d==2) { // 대각선 방향일 경우 추가 방향 확인 (세 군데 0이어야 가능)
                    if(arr[nrow-1][ncol]==0&&arr[nrow][ncol-1]==0) {
                        dfs(nrow,ncol,d);
                    }
                } else {
                    dfs(nrow,ncol,d);
                }
            }
        }
    }
}
