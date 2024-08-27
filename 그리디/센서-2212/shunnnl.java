import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리 : 16524kb / 시간 : 180ms
public class Q_2212 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine()); // 센서의 개수 n
        int k=Integer.parseInt(br.readLine()); // 집중국의 개수 k

        StringTokenizer st=new StringTokenizer(br.readLine());
        int arr[]=new int[n]; // 센서의 좌표 1차원 배열
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        Queue<Integer> queue=new PriorityQueue<>(); // 우선순위큐를 활용하여 낮은 숫자부터
        for(int i=0;i<n-1;i++) queue.offer(arr[i+1]-arr[i]); // 센서의 차를 넣기

        int result=0;
        for(int i=0;i<n-k;i++) result+=queue.poll(); // n-k개의 간격 포함
        System.out.println(result);
    }
}
