package algo.study.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 8980 택배
 * 메모리 : 20980 KB
 * 시간 : 320 ms
 *
 */
class Box implements Comparable<Box> { // 보내는 마을, 받는 마을, 최대 박스 수 클래스
    int from;
    int to;
    int pcs;

    public Box(int from, int to, int pcs) {
        this.from = from;
        this.to = to;
        this.pcs = pcs;
    }

    @Override
    public int compareTo(Box o) {
        return this.to == o.to ? this.from - o.from : this.to - o.to;
    }
}

public class Boj8980 {
    static int N, C, M; // N개는 마을의 수, C는 트럭의 용량, M은 보내는 박스의 개수
    static int[] sensors; // 센서 배열
    static int fr, t, p; // 보내는 마을, 받는 마을, 최대 박스 수
    static int[] weight; // 각 마을 별 보낼 수 있는 최대용량
    static int result; // 결과값 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        Box[] Boxes = new Box[M];
        weight = new int[N + 1];
        for (int i = 1; i < N; i++) {
            weight[i] = C;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            fr = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            Boxes[i] = new Box(fr, t, p);
        }

        Arrays.sort(Boxes);

        for (int i = 0; i < M; i++) {
            int maxBox = Integer.MAX_VALUE;

            // i번째의 택배 경로 상에서 보낼 수 있는 최대 한도를 구한다.
            for (int j = Boxes[i].from; j < Boxes[i].to; j++) {
                maxBox = Math.min(maxBox, weight[j]);
            }

            // 최대 한도보다 작다면 모두 용량에 넣어 보내줌
            if (maxBox >= Boxes[i].pcs) {
                for (int j = Boxes[i].from; j < Boxes[i].to; j++) {
                    weight[j] -= Boxes[i].pcs;
                }
                result += Boxes[i].pcs;
            }
            // 최대 한도보다 크다면 최대 한도만큼만 추가해준다.
            else {
                for (int j = Boxes[i].from; j < Boxes[i].to; j++) {
                    weight[j] -= maxBox;
                }
                result += maxBox;
            }
        }

        System.out.println(result);

    }
}