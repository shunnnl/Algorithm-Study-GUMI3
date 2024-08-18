package ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: (B2422_한윤정)
 * 풀이: dfs
 * 메모리: 19176kb
 * 시간: 204ms
 */
public class B2422_한윤정 {

    int N, M;
    int[] tastes;
    int[][] worstMap;
    int resultCount;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        worstMap = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            worstMap[first][second] = 1;
            worstMap[second][first] = 1;
        }

        tastes = new int[3];
        find(0, 1);

        System.out.println(resultCount);
    }

    // 조합으로 찾기
    private void find(int count, int start) {

        if (count == 3) {
            // 선택된 맛들이 최악의 맛이 아니라면 수를 증가
            if (worstMap[tastes[0]][tastes[1]] == 0
                    && worstMap[tastes[1]][tastes[2]] == 0
                    && worstMap[tastes[0]][tastes[2]] == 0) {
                resultCount++;
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            tastes[count] = i; // 맛의 정보를 저장
            find(count + 1, i + 1);
        }
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B2422_한윤정().solution();
//    }
//}
