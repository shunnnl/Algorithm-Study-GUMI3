import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 16188kb, 시간 : 124ms
public class Q_1507 {
    static int n; // 도시의 개수
    static int[][] arr; // 도시 간의 이동 시간 배열
    static boolean[][] origin; // true : 다른 정점을 통해 이어짐 , false : x

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        n=Integer.parseInt(br.readLine());
        arr=new int[n+1][n+1];
        origin=new boolean[n+1][n+1];

        for(int i=1;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++) arr[i][j]=Integer.parseInt(st.nextToken());
        }

        for(int k=1; k<=n; k++) // 플로이드 워셜 알고리즘 활용 (중간 정점 거치면서 최소 갱신) k : 중간에 거치는 정점
            for(int i=1; i<=n; i++)
                for (int j=1; j<=n; j++) {

                    if (i == j || i == k || j == k) continue; // 시작점과 끝점 동일하면 갱신 필요 없음

                    if (arr[i][j] == (arr[i][k] + arr[k][j])) // 거쳐감
                        origin[i][j] = true;

                    else if (arr[i][j] > (arr[i][k] + arr[k][j])) { // 성립안되는 조건
                        System.out.println(-1);
                        System.exit(0);
                    }
                }

        int sum = 0;

        for(int i=1; i<=n; i++)
            for (int j = 1; j <= n; j++)
                if(!origin[i][j]) sum += arr[i][j]; // 거쳐가는 부분 없는 도로의 합

        System.out.println(sum / 2);
    }
}
