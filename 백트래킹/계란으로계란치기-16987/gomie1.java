import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 : 계란으로 계란치기
 * 풀이 : 백트래킹
 * 메모리 : 16020KB
 * 시간 : 188ms
 */

public class Main {

    static int N, res;
    private static Egg[] arr;
    static class Egg {
        private int state, weight;

        public Egg(int s, int w) {
            this.state = s;
            this.weight = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 계란의 수

        StringTokenizer st;
        arr = new Egg[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[i] = new Egg(s, w);
        }

        res = 0;
        dfs(0, 0);
        System.out.println(res);
    }

    // idx : 계란 번호, cnt : 깨진 계란 개수
    private static void dfs(int idx, int cnt) {
        if(res < cnt) res = cnt; // 최대값 갱신

        if(idx == N) { // 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란인 경우
            return; // 종료
        }

        if(arr[idx].state <= 0) { // 현재 계란이 이미 깨져있다면
            dfs(idx+1, cnt); // 다음(오른쪽) 계란 선택
        }
        else {
            for(int i = 0; i < N; i++) {
                if(i == idx || arr[i].state <= 0) {
                    continue; // 같은 계란이거나 이미 깨진 계란이면 패스
                }

                // 계란 깨기
                arr[idx].state -= arr[i].weight;
                arr[i].state -= arr[idx].weight;

                // 깨진 계란이 있다면 개수 증가
                int count = cnt;
                if(arr[idx].state <= 0) count++;
                if(arr[i].state <= 0) count++;

                dfs(idx+1, count); // 다음 계란 들기

                // 다음 계란 치기를 위해 계란 복구
                arr[idx].state += arr[i].weight;
                arr[i].state += arr[idx].weight;
            }
        }

    }
}