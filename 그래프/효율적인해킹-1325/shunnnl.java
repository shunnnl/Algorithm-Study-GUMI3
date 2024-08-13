import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static int[] result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken()); // 정점의 개수
        m=Integer.parseInt(st.nextToken()); // 간선의 개수
        result=new int[n+1];
        arr=new ArrayList[n+1];

        for(int i=0;i<=n;i++) {
            arr[i]=new ArrayList<>();
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            arr[start].add(end);
        }

        for(int i=1;i<=n;i++) {
            visited=new boolean[n+1];
            visited[i]=true;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);

            while(!queue.isEmpty()) {
                int tmp=queue.poll();
                for(int val : arr[tmp]) {
                    if(visited[val]==false) {
                        queue.offer(val);
                        visited[val]=true;
                        result[val]++;

                    }
                }
            }
        }

        int max=0;
        for(int i=1;i<=n;i++) {
            max=Math.max(max, result[i]);
        }

        for(int i=1;i<=n;i++) {
            if(result[i]==max) {
                System.out.print(i + " ");
            }
        }
    }
}