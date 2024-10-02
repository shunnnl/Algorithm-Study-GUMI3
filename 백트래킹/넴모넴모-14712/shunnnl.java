import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 17888kb / 시간 : 1260ms
public class Q_14712 {
    static int N; // 행의 개수
    static int M; // 열의 개수
    static boolean[][] visited;
    static int cnt=0; // 2x2 사각형을 이루지 않는 가짓수

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        visited=new boolean[N][M];
        dfs(0);
        System.out.println(cnt);
    }

    public static void dfs(int depth) {
        if (depth == N * M) {
            if(isPossible()) cnt++;
            return;
        }

        int row=depth/M; // 행의 위치
        int col=depth%M; // 열의 위치

        dfs(depth+1);
        visited[row][col]=true;
        dfs(depth+1);
        visited[row][col]=false;
    }

    // 2x2 사각형 존재 여부 판단 -> true일 경우 존재하지 않음
    public static boolean isPossible(){
        for(int i=0;i<N-1;i++){
            for(int j=0;j<M-1;j++){
                if(visited[i][j] && visited[i+1][j]&&visited[i][j+1]&&visited[i+1][j+1]) // 네모가 생겼음을 판단
                    return false;
            }
        }
        return true;
    }
}
