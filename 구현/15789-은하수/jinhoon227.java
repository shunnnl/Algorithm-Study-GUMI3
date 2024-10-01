package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 문제: 은하수 (B15787)
 * 풀이: 비트
 * 메모리: 39136kb
 * 시간: 332ms
 */
public class B15787 {

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trains = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            int x = 0;
            if(command == 1 || command == 2) {
                x = Integer.parseInt(st.nextToken()) - 1; // 0번 부터 시작하기위해 -1
            }

            if(command == 1) {
                // 특정 비트를 1로 만듬
                trains[num] = trains[num] | (1 << x);
            }

            if(command == 2) {
                // 특정비트를 0 으로 만듬
                trains[num] = trains[num] & (~(1 << x));
            }

            if(command == 3) {
                trains[num] = trains[num] << 1;
                // 왼쪽으로 한칸 움직였을때, 최대 20칸이라 21칸째는 0 으로 만듬
                trains[num] = trains[num] & (~(1 << 20));
            }

            if(command == 4) {
                trains[num] = trains[num] >> 1;
            }
        }

        // set 통해 기차의 종류를 세어준다.
        Set<Integer> trainTypes = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            trainTypes.add(trains[i]);
        }

        System.out.println(trainTypes.size());
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B15787().solution();
//    }
//}
