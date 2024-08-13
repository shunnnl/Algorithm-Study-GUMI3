import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 눈덩이의 개수

        // 눈덩이 지름 정보를 담을 배열 생성 및 초기화
        int[] H = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(H); // 정렬

        int ans = Integer.MAX_VALUE;
        int snowman1, snowman2;

        // 모든 눈 조합에 대해 차이 계산
        for(int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                snowman1 = H[i] + H[j];
                int start = 0, end = N-1;

                // 투 포인터를 사용하여 두 개의 snowman 높이를 비교하고 최소 높이를 찾는 루프
                while(start < end) {
                    // 현재 포인터가 이미 사용된 눈을 가리키는 경우 다음 눈으로 이동
                    if(start == i || start == j) {
                        start++;
                        continue;
                    }
                    if(end == i || end == j) {
                        end--;
                        continue;
                    }

                    snowman2 = H[start] + H[end];
                    ans = Math.min(ans, Math.abs(snowman1 - snowman2));

                    // 높이 차이에 따라 포인터 이동
                    if(snowman1 > snowman2) {
                        start++;
                    }
                    else if (snowman1 < snowman2) {
                        end--; // snowman2가 더 크다면 격차를 줄이기 위해서 end를 앞으로 이동 (정렬되어 있으니까 앞으로 이동하면 현재보다 작거나 같은 수)
                    }
                    else {
                        // snowman1과 2의 값이 같다는 것은 높이 차이가 0이라는 것이므로
                        // 이 경우보다 작은 차이값은 없다!!
                        System.out.println(0); // 그래서 0을 출력하고
                        return; // 프로그램 종료
                    }
                }
            }
        }

        System.out.println(ans); // 최소 높이 차이 출력

    }

}
