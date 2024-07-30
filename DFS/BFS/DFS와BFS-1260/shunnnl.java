import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n; // 정점의 개수
    static int m; // 간선의 개수
    static int v; // 탐색을 시작할 정점의 번호
    static int[][] arr; // 연결 여부 판단하는 2차원 배열
    static boolean[] isVisit; // 방문 여부 판단하는 1차원 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        m= Integer.parseInt(st.nextToken());
        v= Integer.parseInt(st.nextToken());


        arr=new int[n+1][n+1];
        isVisit=new boolean[n+1];
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            arr[start][end]=1; // 연결 checking
            arr[end][start]=1;
        }

        dfs(v); // dfs 탐색
        System.out.println();

        isVisit=new boolean[n+1];
        bfs(v); // bfs 탐색
    }

    public static void dfs(int start){
        isVisit[start]=true;
        System.out.print(start + " ");
        for(int i=1;i<=n;i++){
            if(arr[start][i]==1 && isVisit[i]==false){
                dfs(i);
            }
        }
    }

    public static void bfs(int start){
        Queue<Integer> queue=new LinkedList<Integer>();
        queue.offer(v);
        isVisit[start]=true;
        System.out.print(v+" ");

        while(!queue.isEmpty()){
            int tmp=queue.poll();
            for(int i=1;i<=n;i++){
                if(arr[tmp][i]==1 && isVisit[i]==false){
                    isVisit[i]=true;
                    queue.offer(i);
                    System.out.print(i+" ");
                }
            }
        }
    }
}