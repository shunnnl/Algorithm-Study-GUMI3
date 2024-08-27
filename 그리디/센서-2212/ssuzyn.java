import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/***
 * 2212. 센서
 * 메모리: 16544 KB
 * 시간: 176 ms
 */
public class 센서_2212 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 센서의 개수
        int K = Integer.parseInt(br.readLine()); // 집중국의 개수
        int[] sensor = new int[N]; // 센서의 좌표
        List<Integer> diff = new ArrayList<>(); // 센서간의 거리 차이

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensor);

        for(int i = 0; i < N - 1; i++) {
            int tmp = sensor[i + 1] - sensor[i];
            if(tmp != 0) diff.add(tmp);
        }
        Collections.sort(diff, Collections.reverseOrder());

        int sum = 0;
        for(int i = K - 1; i < diff.size(); i++) { // K-1개 간격 나누기
            sum += diff.get(i);
        }
        System.out.println(sum);

    }

}
