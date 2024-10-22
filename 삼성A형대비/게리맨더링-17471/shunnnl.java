import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리 : 16332kb / 시간 : 120ms
public class BOJ_17471 {
    static int N, min = Integer.MAX_VALUE; // 구역의 개수, 인구 차이 최솟값
    static int[] people; // 구역 별 인구 수
    static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(); // 연결 표시
    static int[] arr; // 인덱스 : 구역 , 요소값 : 선거구 (0 or 1)
    static boolean[] checked; // 탐색을 위한 여부

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N];
        arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            people[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++)
            list.add(new ArrayList<Integer>());

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++)
                list.get(i).add(Integer.parseInt(st.nextToken()));
        }

        subset(1); //선거구 조합
        if(min==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    public static void subset(int depth) {
        if(depth==N+1) { // 모든 구역의 선거구 결정 완료
            checked = new boolean[N+1];
            int p1=0, p0=0;// a : 1번 선거구 인구수, b : 0번 선거구 인구수
            for(int i=0; i<N; i++) {
                if(arr[i+1]==1) p1+=people[i];
                else p0+=people[i];
            }

            int cnt=0;
            for(int i=1; i<=N; i++) {
                if(checked[i]) continue;
                bfs(i,arr[i]); // 지역 연결 시키기(bfs)
                cnt++;
            }

            if(cnt==2) min = Math.min(min, Math.abs(p1-p0));  // 선거구가 2개 나올 경우 인구수 차 계산 가능
            return;
        }

        arr[depth] = 1; // 1번 선거구로
        subset(depth+1);

        arr[depth] = 0; // 0번 선거구로
        subset(depth+1);
    }

    public static void bfs(int num, int local) { // num : 지역, local : 선거구 (1 or 0)
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(num);
        checked[num] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next:list.get(cur)) {
                if(arr[next]==local && !checked[next]) { // 선거구가 동일할 때만 방문함
                    checked[next]=true;
                    q.add(next);
                }
            }
        }
    }
}