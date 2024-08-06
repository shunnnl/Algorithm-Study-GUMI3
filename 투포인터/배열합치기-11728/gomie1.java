import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 배열 A의 크기
        int M = Integer.parseInt(st.nextToken()); // 배열 B의 크기

        // A 배열 생성 및 초기화
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // B 배열 생성 및 초기화
        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int pA = 0; // A 배열 포인터
        int pB = 0; // B 배열 포인터

        StringBuilder sb = new StringBuilder();

        // A, B 중 포인터가 한 배열의 끝을 가리킬 때까지 반복
        while(pA < N && pB < M) {
            // A, B 배열에서 현재 가리키는 원소가 작은 것부터 ans에 담아줌
            if(A[pA] < B[pB]) {
                sb.append(A[pA++] + " ");
            }
            else {
                sb.append(B[pB++] + " ");
            }
        }

        if(pA == N) { // B의 원소가 남은 경우
            // 주어진 배열은 정렬되어 있기 때문에 순서대로 담아줌
            for(int i = pB; i < M; i++) {
                sb.append(B[i] + " ");
            }
        }
        else if(pB == M) { // A의 원소가 남은 경우도 동일하게 작성
            for(int i = pA; i < N; i++) {
                sb.append(A[i] + " ");
            }
        }

        System.out.println(sb); // 결과 출력
    }

}
