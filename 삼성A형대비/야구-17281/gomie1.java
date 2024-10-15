import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 : 야구
 * 풀이 : 순열, 구현
 * 메모리 : 64,252kb
 * 시간 : 544ms
 */

public class Main {
    private static int N, arr[][], res, isSelected[];
    private static boolean[] visited;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 이닝 수

        StringTokenizer st;
        arr = new int[N+1][10];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isSelected = new int[10];
        visited = new boolean[10];

        isSelected[4] = 1; // 1번 선수는 4번 타자로 지정
        visited[4] = true; // 4번 자리에 이미 선수가 배정되었으므로 체크

        res = 0;
        permutation(2); // 1번 선수는 이미 배정되었기 때문에 2번 선수부터 배정 시작
        System.out.println(res);
    }

    private static void permutation(int cnt) { // cnt: 현재 선수 번호
        if(cnt == 10) { // 1~9번 선수를 다 배정했다면 종료
            int ans = play();
            if(res < ans) {
                res = ans;
            }
            return;
        }

        // 현재 선수를 1번부터 9번 자리까지 넣어보며 재귀 호출
        for(int i = 1; i <= 9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                isSelected[i] = cnt;
                permutation(cnt+1);
                visited[i] = false;
            }
        }
    }

    private static int play() {
        int player = 1; // 현재 선수 번호
        int sum = 0;

        for(int i = 1; i <= N; i++) { // 이닝 수만큼 반복
            int out = 0; // 아웃 수
            int curScore = 0;
            boolean[] status = new boolean[4]; // 0: home, 1: 1루, 2: 2루, 3: 3루

            while(out < 3) { // 한 이닝에 3아웃이 발생하면 이닝 종료
                int value = arr[i][isSelected[player]];
                //System.out.println("value = " + value);

                switch(value) {
                    case 0: // 아웃
                        out++;
                        break;
                    case 1: // 안타
                        if(status[3]) { // 3루에 선수가 있다면
                            curScore++; // 3루에 있던 선수가 홈으로 가므로 1점 획득
                            status[3] = false;
                        }

                        if(status[2]) { // 2루에 선수가 있다면
                            status[3] = true; // 2루에 있던 선수가 3루로 이동
                            status[2] = false;
                        }

                        if(status[1]) { // 1루에 선수가 있다면
                            status[2] = true; // 1루에 있던 선수가 2루로 이동
                        }
                        status[1] = true; // 홈에 있던 선수가 1루로 이동
                        break;
                    case 2: // 2루타
                        if(status[3]) { // 3루에 선수가 있다면
                            curScore++; // 3루에 있던 선수가 홈으로 가므로 1점 획득
                            status[3] = false;
                        }

                        if(status[2]) { // 2루에 선수가 있다면
                            curScore++; // 2루에 있던 선수가 홈으로 가므로 1점 획득
                        }

                        if(status[1]) { // 1루에 선수가 있다면
                            status[3] = true; // 1루에 있던 선수가 3루로 이동
                            status[1] = false;
                        }
                        status[2] = true; // 홈에 있던 선수가 2루로 이동
                        break;
                    case 3: // 3루타
                        if(status[3]) { // 3루에 선수가 있다면
                            curScore++; // 3루에 있던 선수가 홈으로 가므로 1점 획득
                        }

                        if(status[2]) { // 2루에 선수가 있다면
                            curScore++; // 2루에 있던 선수가 홈으로 가므로 1점 획득
                            status[2] = false;
                        }

                        if(status[1]) { // 1루에 선수가 있다면
                            curScore++; // 1루에 있던 선수가 홈으로 가므로 1점 획득
                            status[1] = false;
                        }
                        status[3] = true; // 홈에 있던 선수가 3루로 이동
                        break;
                    case 4: // 홈런
                        if(status[3]) { // 3루에 선수가 있다면
                            curScore++; // 3루에 있던 선수가 홈으로 가므로 1점 획득
                            status[3] = false;
                        }

                        if(status[2]) { // 2루에 선수가 있다면
                            curScore++; // 2루에 있던 선수가 홈으로 가므로 1점 획득
                            status[2] = false;
                        }

                        if(status[1]) { // 1루에 선수가 있다면
                            curScore++; // 1루에 있던 선수가 홈으로 가므로 1점 획득
                            status[1] = false;
                        }

                        curScore++; // 홈에 있던 선수가 다시 홈으로 가므로 1점 획득
                        break;
                }

                player++; // 다음 선수로 넘어감
                if(player >= 10) player = 1; // 9번 선수까지 돌았다면 다시 1번 선수부터!
            }
            sum += curScore; // 해당 이닝에서 얻은 점수를 총 점수에 더해줌
        }
        return sum;
    }
}