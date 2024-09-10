import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 문제 : 입국심사
* 메모리 : 31948kB
* 시간 : 456ms
*/

public class Main {

    static int N, time[];
    static long M, MAX_VALUE, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 입국심사대의 개수
        M = Long.parseLong(st.nextToken()); // 인원 수

        // 각 입국심사대 별 심사 시간 입력받기
        time = new int[N];
        int max = 0;
        for(int i = 0; i < N; i++) {
            time[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, time[i]); // 가장 오래 걸리는 심사대 찾기
        }

        MAX_VALUE = M * max; // 최대로 걸리는 시간
        res = MAX_VALUE;
        Arrays.sort(time); // 이분탐색을 위한 정렬

        binarySearch();
        System.out.println(res);
    }

    private static void binarySearch() {
        long min = 0, max = MAX_VALUE;

        while(min <= max) {
            long mid = (min + max) / 2;

            long total = 0;
            for(int t : time) {
                long cnt = mid / t; // 해당 시간에 각 심사대에서 맡을 수 있는 사람의 수

                // 현재까지 심사한 사람 수가 총 인원수보다 크거나
                // 현재 심사대의 심사 시간이 검사중인 시간보다 크면 반복문 탈출
                if(total >= M || t > mid) {
                    break;
                }

                total += cnt;
            }

            // total이 총 사람 수보다 크거나 같다면 더 짧은 시간에 완료될 수 있음을 의미
            // 반대로 총 사람 수보다 작다면 해당 시간에 심사가 불가능함을 의미
            if(total >= M) {
                max = mid - 1;
                res = Math.min(res, mid);
            }
            else {
                min = mid + 1;
            }
        }
    }

}