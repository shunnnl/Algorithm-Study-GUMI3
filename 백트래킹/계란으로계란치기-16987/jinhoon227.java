package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 계란으로 계란치기 (B16987)
 * 풀이: 백트래킹
 * 메모리: 15652kb
 * 시간: 200ms
 */
public class B16987 {

    int N;
    Egg[] eggs;

    int result;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dfs(0, 0);

        System.out.println(result);
    }

    private void dfs(int cur, int cnt) {

        if (result == N) {
            return;
        }

        if (cnt > result) {
            result = cnt;
        }

        // 가장 오른쪽 계란을 골랐다면 패스
        if (cur == N) {
            return;
        }

        // 손에 든 계란이 깨져있다면 내려놓고 오른쪽 계란 선택
        if (eggs[cur].hp <= 0) {
            dfs(cur + 1, cnt);
        } else {
            // 손에 든 계란이 깨지지 않았다면, 하나씩 선택하면서 깨본다
            for (int i = 0; i < N; i++) {
                // 같은계란이 아니고, 안꺠진 계란이라면
                if (cur != i && eggs[i].hp > 0) {

                    // 계란치기
                    eggs[cur].hp -= eggs[i].weight;
                    eggs[i].hp -= eggs[cur].weight;

                    // 계란이 깨진만큼 카운팅
                    int nCnt = cnt;
                    if(eggs[cur].hp <= 0) {
                        nCnt++;
                    }
                    if (eggs[i].hp <= 0) {
                        nCnt++;
                    }

                    // 오른쪽 계란 들기
                    dfs(cur + 1, nCnt);

                    // 계란복구
                    eggs[cur].hp += eggs[i].weight;
                    eggs[i].hp += eggs[cur].weight;
                }
            }
        }
    }

    static class Egg {
        int hp;
        int weight;

        public Egg(int hp, int weight) {
            this.hp = hp;
            this.weight = weight;
        }
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B16987().solution();
//    }
//}
