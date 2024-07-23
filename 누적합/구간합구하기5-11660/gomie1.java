import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int K = Integer.parseInt(st.nextToken()); // 졸고 있는 학생 수
        int Q = Integer.parseInt(st.nextToken()); // 출석 코드를 보낼 학생 수
        int M = Integer.parseInt(st.nextToken()); // 주어질 구간의 수

        // 졸고 있는 학생 = 2, 출첵한 학생 = 1, 아무것도 안한 학생 = 0
        int[] arr = new int[N + 3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int idx = Integer.parseInt(st.nextToken()); // 졸고 있는 학생 번호
            arr[idx] = 2; // 해당 학생 졸았다고 표시
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int idx = Integer.parseInt(st.nextToken()); // 출석 코드 번호
            if (arr[idx] != 2) { // 출석 코드를 보낸 학생이 졸지 않고 있다면
                // 입장 번호의 배수인 학생들에게 번호 전달
                for (int num = idx; num < N + 3; num += idx) {
                    if (arr[num] != 2) // 배수인 학생이 졸지 않는다면
                        arr[num] = 1; // 출석 처리
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()); // Start
            int E = Integer.parseInt(st.nextToken()); // End

            int count = 0;
            for (int j = S; j <= E; j++) {
                // 출석 코드를 받지 못했거나 자고있는 학생이라면 (출석 X)
                if (arr[j] == 0 || arr[j] == 2) {
                    count++; // 출석이 되지 않은 학생의 수 1 증가
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}