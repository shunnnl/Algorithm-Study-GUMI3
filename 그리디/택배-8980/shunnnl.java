import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// 메모리 : 21264kb / 시간 : 312ms
public class Q_8980 {

    public static class Box implements Comparable<Box> {
        int start, end, cnt;

        public Box(int start, int end, int cnt) {
            this.start = start;
            this.end = end;
            this.cnt = cnt;
        }

        // 도착하는 지점에 대해서 오름차순, 동일하면 시작하는 지점에 대해서 오름차순 정렬
        public int compareTo(Box o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int C = Integer.parseInt(st.nextToken()); // 트럭의 용량
        int M = Integer.parseInt(br.readLine());  // 보내는 박스 정보 개수

        ArrayList<Box> box = new ArrayList<>(); // 박스 보내고 받는 정보 배열

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            box.add(new Box(start, end, cnt));
        }

        Collections.sort(box); // 정렬

        int[] town = new int[N]; // 마지막 마을은 배송을 받기만 하기 때문에 N으로 설정
        Arrays.fill(town, C); // 트럭 용량으로 초기화

        int ans = 0; // 트럭 한 대로 배송할 수 있는 최대 박스 수

        for (int i=0; i<M; i++) {
            Box b = box.get(i);

            // 출발지에서 도착지까지 옮길 때 최솟값으로만 옮길 수 있음
            int min = Integer.MAX_VALUE;
            for (int j=b.start; j<b.end; j++) {
                min = Math.min(min, town[j]);
            }

            // 모든 짐을 옮길 수 있음
            if (min >= b.cnt) {
                for (int j=b.start; j<b.end; j++) {
                    town[j] -= b.cnt;
                }
                ans += b.cnt;

            } else { // 모든 짐을 옮길 수 없는 경우
                for (int j=b.start; j<b.end; j++) {
                    town[j] -= min;
                }
                ans += min;
            }
        }

        System.out.println(ans);
    }
}
