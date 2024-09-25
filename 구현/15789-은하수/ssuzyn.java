import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 15787. 기차가 어둠을 헤치고 은하수를
 * 메모리 : 38704 KB
 * 시간 : 324 ms
 */
public class Main {

    static int N, M;
    static int[] train;
    static Set<Integer> result = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 기차의 수
        M = Integer.parseInt(st.nextToken()); // 명령의 수
        train = new int[N + 1];

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            if(cmd == 1){
                int j = Integer.parseInt(st.nextToken());
                train[i] |= (1 << (j - 1)); // x번째 비트 1로 만들기 (OR)
            }
            else if(cmd == 2){
                int j = Integer.parseInt(st.nextToken());
                train[i] &= ~(1 << (j - 1)); // x번째 비트 0으로 만들기 (AND, NOT)
            }
            else if(cmd == 3){
                train[i] &= ~(1 << 19); // 가장 왼쪽 비트 0으로 만들기 (하차, AND, NOT, SHIFT)
                train[i] <<= 1; // 각 비트 왼쪽으로 SHIFT
            }
            else {
                train[i] &= ~1; // 가장 오른쪽 비트 0으로 만들기 (하차, AND, NOT)
                train[i] >>= 1; // 각 비트 오른쪽으로 SHIFT
            }
        }

        for(int i = 1; i <= N; i++) {
            result.add(train[i]); // Set에 중복 제거하여 기차 저장
        }
        System.out.println(result.size());
    }
}
