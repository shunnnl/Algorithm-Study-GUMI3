import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리 : 34156kb / 시간 : 172ms
public class BOJ_17135 {
    static int N,M,D,enemies; // 격자판 행의 수, 열의 수, 궁수의 공격 거리 제한, 적의 수
    static int[][] map; // 격자판의 상태
    static int[] picked=new int[3]; // comb를 활용해 뽑은 궁수
    static int result=0;
    static int dx[] = {0, -1, 0}; // 공격 가능한 3방향 (왼쪽부터 없애야하기 때문에 0,-1 우선 배치)
    static int dy[] = {-1, 0, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        D=Integer.parseInt(st.nextToken());
        enemies=0;
        map=new int[N][M];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==1) enemies++;
            }
        }

        comb(0,0); // 1. 궁수 3명 배치 조합 찾기

        System.out.println(result);
    }

    // 3명의 궁수 뽑는 조합
    private static void comb(int cnt, int start) {
        if(cnt==3){
            result=Math.max(result,play());
            return;
        }
        for(int i=start;i<M;i++){
            picked[cnt]=i;
            comb(cnt+1,i+1);
        }
    }

    // 궁수의 공격으로 제거할 수 있는 적의 수 구하는 함수
    private static int play() {
        int enemySu = enemies; // 적의 수

        int[][] copyMap = new int[N][M]; // map 복사
        for(int i=0;i<N;i++)
            copyMap[i] = Arrays.copyOf(map[i], M);

        int castle = N;
        while(enemySu > 0 && castle > 0) { //적이 다 없을 때까지
            for(int i=0;i<3;i++) { //궁수 3명과 가까운 적 찾아 없애기
                //bfs
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[] {castle, picked[i]}); // i번째 궁수의 위치 삽입
                boolean[][] visited = new boolean[N][M]; // 방문 표시(bfs)

                int d = 0;//거리

                outloop: while(!q.isEmpty()) {
                    if(++d > D) // 거리 오버되면 빼기
                        break;

                    for(int k=0, size=q.size(); k<size; k++) {
                        int[] node = q.poll();
                        for(int j=0; j<3; j++) { // 3방향 탐색
                            int nx = node[0] + dx[j];
                            int ny = node[1] + dy[j];
                            if(0<=nx && nx<castle && 0<=ny && ny<M && !visited[nx][ny]) {//범위에 속하면
                                if(copyMap[nx][ny]==1) { // 적이 있으면
                                    enemySu--;
                                    copyMap[nx][ny] = -castle; // 적 제거
                                    break outloop;
                                }
                                else if(copyMap[nx][ny]== -castle) { // 적 제거 완료
                                    break outloop;
                                }
                                visited[nx][ny] = true;
                                q.add(new int[] {nx, ny});
                            }
                        }
                    }
                }
            }
            castle--;
        }

        return enemies-enemySu;
    }

}
