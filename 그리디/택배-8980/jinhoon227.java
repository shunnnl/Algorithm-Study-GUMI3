package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제: 택배 (B8980)
 * 풀이: 그리디
 * 메모리: 21036kb
 * 시간: 308ms
 */
public class B8980 {

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());

        Box[] boxes = new Box[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int box = Integer.parseInt(st.nextToken());

            boxes[i] = new Box(start, end, box);
        }

        // 도착마을을 기준으로 오름차순 정렬
        // 도착마을이 같다면, 시작마을로 오름차순 정렬
        Arrays.sort(boxes, (o1, o2) -> {
            if (o1.end == o2.end) {
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        });

        int[] maxAmount = new int[N + 1]; // 마을에서 실을 수 있는 최대 용량 배열
        Arrays.fill(maxAmount, C); // 초기값은 택배의 최대 용량으로 설정

        int result = 0;
        // 각 택배에 대해 배송 시작
        // 각 마을에서 택배를 실을 수 있는 최대용량을 정해져있고,
        // 택배를 실을때마다, 해당 택배의 출발점과 종착점까지에서 해당 택배 용량만큼 못싣게 됨으로 각 마을의 최대용량에서 빼준다.
        // 최대한 종착점이 짧은곳에 있는 택배를 싣고 나름으로써 최적의 해를 찾을 수 있다.
        for (Box box : boxes) {
            int maxBoxNum = Integer.MAX_VALUE;

            // 현재 택배차가 실을 수 있는 최대 용량을 계산
            for (int i = box.start; i < box.end; i++) {
                maxBoxNum = Math.min(maxBoxNum, maxAmount[i]);
            }

            if (maxBoxNum >= box.num) { // 택배차가 실을 수 있는 최대용량이, 현재 택배의 용량보다 클 경우 전부 실을 수 있다.
                // 해당 택배의 시작과 끝미만에 대해 현재 택배의 용량을 빼준다.
                for (int i = box.start; i < box.end; i++) {
                    maxAmount[i] -= box.num;
                }
                result += box.num;
            } else { // 택배차가 실을 수 있는 최대용량이, 현재 택배의 용량보다 작으면, 부분적(maxAmount)으로 실을 수 있다
                for (int i = box.start; i < box.end; i++) {
                    maxAmount[i] -= maxBoxNum;
                }
                result += maxBoxNum;
            }
        }

        System.out.println(result);
    }

    static class Box {
        int start;
        int end;
        int num;

        public Box(int start, int end, int num) {
            this.start = start;
            this.end = end;
            this.num = num;
        }
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B8980().solution();
//    }
//}
