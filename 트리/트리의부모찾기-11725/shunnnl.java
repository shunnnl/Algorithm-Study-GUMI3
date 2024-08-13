import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 80948 / 1416
public class Main {
    static int n; // 노드의 개수 n
    static List<List<Integer>> adjList; // 인접 노드 리스트
    static int[] parent; // 부모 노드 저장 배열
    static boolean[] visited; // 방문 여부 배열

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        adjList= new ArrayList<>();
        for(int i=0;i<=n;i++) adjList.add(new ArrayList<>());

        for(int i=0;i<n-1;i++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            adjList.get(start).add(end);
            adjList.get(end).add(start);
        }

        parent=new int[n+1];
        visited=new boolean[n+1];

        find(1); // 부모 찾는 함수 호출

        for(int i=2;i<=n;i++){
            System.out.println(parent[i]);
        }
    }

    public static void find(int node){
        visited[node]=true;

        // 방문하지 않았을 경우, 인접 노드에 대한 부모노드 배열에 넣기
        for(int adjNode:adjList.get(node)){
            if(!visited[adjNode]){
                parent[adjNode]=node;
                find(adjNode);
            }
        }
    }
}
