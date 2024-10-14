import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 94788kb / 시간 : 580ms
public class BOJ_17281 {
    static int N, ans = 0; // 이닝 수, 최대 점수
    static boolean[] selected = new boolean[10]; // 순열 선택
    static int[] players = new int[10]; // 순열 선수 순서
    static int[][] arr; // 각 선수가 각 이닝에서 얻는 결과
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new int[N][10];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=9;j++) {
                arr[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        players[4] = 1;  // 4번 타자 -> 1번 선수
        selected[4]=true;

        perm(2);
        System.out.println(ans);

    }

    // 1번 선수 외에 8명의 타순 결정
    private static void perm(int cnt) {
        if(cnt==10) {
            ans = Math.max(ans, game()); // 순서 정해지면 게임 진행
            return;
        }

        for(int i=1;i<=9;i++) {
            if(!selected[i]) {
                selected[i] = true;
                players[i] = cnt;
                perm(cnt+1);
                selected[i] = false;
            }
        }
    }

    private static int game() {
        int start = 1; // 시작 인덱스(1번 타자) -> 선수 위주로 돌아야함
        int score = 0; // 얻을 수 있는 점수

        for(int i=0;i<N;i++) {
            int[] point = {0,0,0,0,0};  // 각 주자들의 위치를 저장하기 위한 배열 (아웃은 0번 인덱스에 저장 , 점수는 4번 인덱스에 저장 )

            while(point[0]<3) { // 아웃이 3번이 되기 전까지 진행
                run(point,arr[i][players[start]]); // (위치 저장 배열,1번 타자의 선수의 결과)
                if(start==9) { // 9번까지 하면 다시 1번
                    start=1;
                }else { // 아닐 경우 타자 ++
                    start++;
                }
            }

            score += point[4]; // 한 이닝이 끝나면 얻은 점수를 score에 저장
        }
        return score;
    }

    // 선수 이동
    private static void run(int[] point, int n) { // 1:안타, 2:2루타, 3:3루타, 4:홈런
        for(int i=0;i<n;i++) {
            point[4]+=point[3];
            point[3]=point[2];
            point[2]=point[1];
            point[1]=0;
        }

        point[n]++;  // 이전에 나가있던 주자들을 이동시킨 후에 새로 공을 친 주자의 위치를 저장
    }



}
