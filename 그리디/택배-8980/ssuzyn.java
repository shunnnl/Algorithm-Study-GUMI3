import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/***
 * 8980. 택배
 * 메모리: 21120 KB
 * 시간: 296 ms
 */
public class 택배_8980 {

    static class Delivery{
        int from; // 보내는 마을
        int to; // 받는 마을
        int box; // 박스 개수

        Delivery(int from, int to, int box){
            this.from = from;
            this.to = to;
            this.box = box;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int weight = Integer.parseInt(st.nextToken()); // 트럭의 용량
        int M = Integer.parseInt(br.readLine()); // 박스 정보의 개수
        List<Delivery> info = new ArrayList<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            info.add(new Delivery(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 도착지 기준으로 오름차순 정렬
        Collections.sort(info, (o1, o2) ->{
            if(o1.to == o2.to){ // 도착지가 같다면 출발지 기준으로 정렬
                return o1.from - o2.from;
            }
            return o1.to - o2.to;
        });

        // 각 구간별 남은 용량 관리
        int[] capacity = new int[N + 1];
        Arrays.fill(capacity, weight);

        int totalBox = 0;

        for(Delivery delivery : info){
            // 현재 택배의 경로에서 최소 남은 용량
            int minCapacity = Integer.MAX_VALUE;
            for(int i = delivery.from; i < delivery.to; i++){
                minCapacity = Math.min(minCapacity, capacity[i]);
            }

            // 실을 수 있는 택배
            int box = Math.min(minCapacity, delivery.box);

            // 각 구간의 남은 용량 업데이트
            for(int i = delivery.from; i < delivery.to; i++){
                capacity[i] -= box;
            }

            // 총 실은 양 누적
            totalBox += box;
        }

        System.out.println(totalBox);
    }
}
