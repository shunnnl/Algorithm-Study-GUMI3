
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제: (B20366_같이눈사람만들래)
 * 풀이: 눈사람 2개 만들기 -> 눈사람 정렬 -> 최소가되는 눈사람 높이 찾기
 * 메모리: 29388kb
 * 시간: 476ms
 */
class B20366_같이눈사람만들래 {

    int N;
    int[] arr;
    List<SnowMan> snowMans;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // == 입력 끝 ==//

        // 눈사람 만들기
        snowMans = new ArrayList<>();
        makeSnowMans();

        // 눈사람 내림차순 정렬
        snowMans.sort((o1, o2) -> o2.sum - o1.sum);

        // 눈사람을 비교하면서 최솟값 찾기
        // 같은 눈덩이를 사용했다면 패스
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < snowMans.size() - 1; i++) {

            // i 번째 눈사람과 바로 옆에 있는 i+1 눈사람이랑 비교
            // 눈사람은 정렬되어있으므로 바로옆에 있는 눈사람과 비교만 해도 최소의 높이차가 보장됨
            SnowMan A = snowMans.get(i);
            SnowMan B = snowMans.get(i + 1);

            // 같은 눈덩이로 만들어진 눈사람 이면 패스
            if (A.isSameBall(B)) {
                continue;
            }

            // 다른 눈덩이면 최솟값 업데이트
            result = Math.min(result, A.diff(B));

        }

        System.out.println(result);
    }

    // 눈사람 미리 만들기
    private void makeSnowMans() {

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                snowMans.add(new SnowMan(arr[i] + arr[j], i, j));
            }
        }
    }


    // 눈사람
    static class SnowMan {
        int sum;
        int a;
        int b;

        public SnowMan(int sum, int a, int b) {
            this.sum = sum;
            this.a = a;
            this.b = b;
        }

        public boolean isSameBall(SnowMan snowMan) {
            return this.a == snowMan.a || this.a == snowMan.b || this.b == snowMan.a || this.b == snowMan.b;
        }

        public int diff(SnowMan snowMan) {
            return Math.abs(this.sum - snowMan.sum);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        new B20366_같이눈사람만들래().solution();
    }
}