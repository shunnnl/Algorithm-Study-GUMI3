import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1차 : 현재 방향 dir 변수로 두고, 시계 방향으로 돌렸지만 값을 미는 처리 불가능
// 2차 : 방향 별 값을 미는 기능 처리
// 메모리 : 31340kb / 시간 : 380ms
public class BOJ_17406 {
    static int N, M, K;
    static int[][] map;
    static int[][] cycle;

    static int[] arr; // 순열 결과 배열
    static boolean[] visited; // 순열 방문 배열

    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cycle = new int[K][3];
        arr=new int[K];
        visited=new boolean[K];

        for(int k = 0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            cycle[k][0] = Integer.parseInt(st.nextToken()) - 1; // 행
            cycle[k][1] = Integer.parseInt(st.nextToken()) - 1; // 열
            cycle[k][2] = Integer.parseInt(st.nextToken()); // 차, 합의 범위
        }

        perm(0);

        System.out.println(min);
    }

    public static void perm(int cnt) {
        if(cnt == K) {
            doCycle();
            return;
        }
        for(int i=0; i<K; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            arr[cnt] = i;
            perm(cnt+1);
            visited[i] = false;
        }
    }

    public static void doCycle() {
        int[][] tmp = copyMap(); // 배열 초기화

        for(int k=0; k<K; k++) {
            int r = cycle[arr[k]][0];
            int c = cycle[arr[k]][1];
            int S = cycle[arr[k]][2];

            for(int s=1; s<=S; s++) {
                // 위쪽 (우측으로 미는 방향, 제일 우측부터 밀어줘야함)
                int upTmp = tmp[r-s][c+s];
                for(int y = c+s; y > c-s; y--) {
                    tmp[r-s][y] = tmp[r-s][y-1];
                }

                //오른쪽 (아래쪽으로 미는 방향, 제일 아래쪽부터 밀어줘야함)
                int rightTmp = tmp[r+s][c+s];
                for(int x = r+s; x > r-s; x--) {
                    tmp[x][c+s] = tmp[x-1][c+s];
                }
                tmp[r-s+1][c+s] = upTmp;

                //아래 (왼쪽으로 미는 방향, 제일 왼쪽부터 밀어줘야함)
                int leftTmp = tmp[r+s][c-s];
                for(int y = c-s; y < c+s; y++) {
                    tmp[r+s][y] = tmp[r+s][y+1];
                }
                tmp[r+s][c+s-1] = rightTmp;

                //왼쪽 (위쪽으로 미는 방향, 제일 위쪽부터 밀어줘야함)
                for(int x = r-s; x < r+s; x++) {
                    tmp[x][c-s] = tmp[x+1][c-s];
                }
                tmp[r+s-1][c-s] = leftTmp;
            }
        }

        getMin(tmp); // 행 별 최소값
    }

    public static int[][] copyMap() {
        int[][] tmp = new int[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        return tmp;
    }

    public static void getMin(int[][] tmp) {
        for(int i=0; i<N; i++) {
            int sum = 0;
            for(int j=0; j<M; j++) {
                sum += tmp[i][j];
            }
            min=Math.min(min,sum);
        }
    }
}