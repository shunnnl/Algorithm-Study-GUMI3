import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 : 택배
 * 풀이 : 그리디
 * 메모리 : 20608KB
 * 시간 : 296ms
 */

public class _8980_택배 {

    static class Delivery implements Comparable<Delivery> {
        int start;
        int end;
        int num;

        Delivery(int s, int e, int n) {
            this.start = s;
            this.end = e;
            this.num = n;
        }

        @Override
        public int compareTo(Delivery arg) {
            if(end == arg.end) {
                return start - arg.start;
            }
            return end - arg.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int C = Integer.parseInt(st.nextToken()); // 트럭의 용량
        int M = Integer.parseInt(br.readLine()); // 박스 정보의 개수

        // 택배 정보 입력 받기
        Delivery[] deliveries = new Delivery[M+1];
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            deliveries[i] = new Delivery(start, end, num);
        }

        // 각 마을당 보낼 수 있는 최대 용량을 설정한다.
        int[] weight = new int[N + 1];
        for (int i = 1; i < N; i++) {
            weight[i] = C;
        }

        // 받는 마을을 기준으로 오름차순 정렬을 하되, 받는마을이 같을 경우 보내는 마을을 기준으로 오름차순 정렬
        Arrays.sort(deliveries, 1, M+1);

        int res = 0;
        for(int i = 1; i <= M; i++) {
            Delivery d = deliveries[i];
            int maxBoxNum = Integer.MAX_VALUE;

            // 보내는 마을과 받는 마을 사이의 경로 마을 중에서 보낼 수 있는 최대 한도를 구한다.
            for (int j = d.start; j < d.end; j++) {
                maxBoxNum = Math.min(maxBoxNum, weight[j]);
            }

            // 위에서 구한 보낼 수 있는 최대 한도가 현재 보내려는 택배의 개수보다 크다면,
            // 보내는 마을과 받는 마을 사이의 경로 마을 모두 용량에서 현재 보내려는 택배의 개수를 뺀다.
            if (maxBoxNum >= d.num) {
                for (int j = d.start; j < d.end; j++) {
                    weight[j] -= d.num;
                }
                res += d.num;
            } else {
                // 보낼 수 있는 최대 한도보다 현재 보내려는 택배의 개수가 클 경우,
                // 현재 보내려는 택배의 개수가 아닌 위에서 구한 최대 한도를 기준으로 한다.
                for (int j = d.start; j < d.end; j++) {
                    weight[j] -= maxBoxNum;
                }
                res += maxBoxNum;
            }
        }
        System.out.println(res);
    }

}