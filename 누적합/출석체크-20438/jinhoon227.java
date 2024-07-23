package ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제: (B20438)
 * 풀이: 누적합
 * 메모리: 30444kb
 * 시간: 364ms
 */
public class B20438 {

    public void solution() throws IOException {

        StringBuilder result = new StringBuilder();

        // 입력값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<Integer> dreamStudents = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            dreamStudents.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> attendStudents = new ArrayList<>(); // 여기에 잠자는 학생 X
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int student = Integer.parseInt(st.nextToken());
            if (!dreamStudents.contains(student)) { // 잠자는 학생이 아닐때만 출석학생에 넣음
                attendStudents.add(student);
            }

        }

        int[][] sections = new int[M][2]; // 0 일때 S, 1 일때 E
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sections[i][0] = Integer.parseInt(st.nextToken());
            sections[i][1] = Integer.parseInt(st.nextToken());
        }

        // 출석 학생을 관리하는 배열
        // 계산하기 편하게 1부터 시작하기 위해 1+(N+2) 로 초기화
        int[] attendances = new int[N + 3];

        // 출석한 학생이 0, 출석한 안한 학생을 1 로 설정 하여 관리
        for (int i = 3; i <= N + 2; i++) {
            attendances[i] = 1;
        }

        // 출석한 사람을 0로 설정
        for (int attendStudent : attendStudents) {
            for (int i = attendStudent; i <= N + 2; i += attendStudent) {
                if (!dreamStudents.contains(i)) { // 잠자는 학생이 아니고
                    attendances[i] = 0; // 출석을 했다면 0
                }

            }
        }

        // 구간별 출석 되지 않은 학생 수 구하기
        // 누적합 알고리즘을 사용해 구간마다 반복문 돌릴 필요없이 한번만 사용
        for (int i = 4; i <= N + 2; i++) {
            attendances[i] += attendances[i - 1];
        }

        for (int i = 0; i < M; i++) {
            int start = sections[i][0];
            int end = sections[i][1];
            result.append(attendances[end] - attendances[start - 1]).append("\n");
        }

        System.out.println(result);
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B20438().solution();
//    }
//}
