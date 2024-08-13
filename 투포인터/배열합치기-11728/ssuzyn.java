import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 배열 A의 크기
        int M = Integer.parseInt(st.nextToken()); // 배열 B의 크기
        int[] A = new int[N];
        int[] B = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }


        int idxA = 0; // 배열 A의 인덱스
        int idxB = 0; // 배열 B의 인덱스
        int idxTmp = 0; // 병합 배열의 인덱스
        int [] merged = new int[N + M];

        while(idxA < N && idxB < M) {
            if(A[idxA] > B[idxB]) {
                merged[idxTmp++] = B[idxB++];
            }
            else{
                merged[idxTmp++] = A[idxA++];
            }
        }

        while(idxA < N) { // 배열 A의 나머지 요소 병합 배열에 할당
            merged[idxTmp++] = A[idxA++];
        }

        while(idxB < M) { // 배열 B의 나머지 요소 병합 배열에 할당
            merged[idxTmp++] = B[idxB++];
        }

        StringBuilder sb = new StringBuilder();
        for(int num : merged) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);

    }

}
