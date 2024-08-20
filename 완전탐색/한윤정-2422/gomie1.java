package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 : 한윤정이 이탈리아에 가서 아이스크림을 사먹는데
 * 메모리 : 18252 KB
 * 시간 : 200ms
 */

public class _2422_한윤정아이스크림 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 아이스크림 종류의 수
        int M = Integer.parseInt(st.nextToken()); // 섞어먹으면 안되는 조합의 수

        int[][] check = new int[N+1][N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            check[a][b] = 1;
            check[b][a] = 1;
        }

        // 선택하는 아이스크림의 수가 정해져있고 작기 떄문에 for문 사용
        int res = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = i+1; j <= N; j++) {
                for(int k = j+1; k <= N; k++) {
                    if(check[i][j] == 1 || check[j][k] == 1 || check[k][i] == 1) {
                        continue;
                    }
                    res++; // 섞어먹으면 안되는 쌍이 없다면 res 1 증가
                }
            }
        }

        System.out.println(res);
    }
}
