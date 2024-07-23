import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/***
 * 백준 20438번 출석체크
 * 학생 번호는 3 ~ n+2 까지
 *
 */

public class boj20438 {
//public class Main {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1번째줄 입력
        int n = Integer.parseInt(st.nextToken());	// 저체 학생 수
        int k = Integer.parseInt(st.nextToken());	// 조는 학생 수
        int q = Integer.parseInt(st.nextToken());	// 출석 코드 보낼 학생 수
        int m = Integer.parseInt(st.nextToken());	// 주어질 구간의 수

        int[] stuArr = new int[n+3];	// 출석 체크용 학생 배열
        int[] k_stu = new int[n+3];		// 조는 학생들의 입장 번호 배열
        int[] Sum = new int[n+3];		// 누적합 배열
        int temp = 0;					// 조는 학생번호 임시 저장용
        int code = 0;					// 출석 코드 받는 학생 임시 저장용

        // 2번째줄 조는 학생 입장 번호
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            temp = Integer.parseInt(st.nextToken());
            k_stu[temp] = 1;
        }

        // 3번째줄 출석 코드 보낼 학생 번호 받아서 출석 하는 인원 파악
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            code = Integer.parseInt(st.nextToken());
            int value = code;			// 배수 호출용
            while (code < n+3) {
                if (k_stu[code] == 0) {	// 불렸고, 깨있으면 출석 인정해줌
                    stuArr[code] = 1;	// stuArr배열에서 자기번호가 1이면 출석인정, 0은 출석 인정 X
                    code += value;
                }
                else
                    code += value;
            }
        }

        // 번호 순 출석안된 누적 합 배열 구하기
        for (int i = 3; i < n+3; i++) {
            if (stuArr[i] == 0) {
                Sum[i] = Sum[i-1] + 1;
            }
            else
                Sum[i] = Sum[i-1];
        }

//		for (int i = 3; i < n+3; i++) {
//			System.out.println(i + " " + stuArr[i] + " " + Sum[i]);
//		}

        // 4번째줄 구간 간격 받으면서 누적합 배열 이용해서 프린트
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(Sum[e] - Sum[s-1]).append("\n");
        }
        System.out.println(sb);
    }

}
