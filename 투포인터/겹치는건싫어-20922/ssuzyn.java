import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열의 크기
        int K = Integer.parseInt(st.nextToken()); // 같은 정수 K개 이하
        int[] check = new int[100001]; // 100,000 이하의 양의 정수로 이루어진 수열
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int answer = Integer.MIN_VALUE;

        while(start < N){
            while(end < N && check[arr[end]] < K){
                check[arr[end]]++;
                end++;
            }
            answer = Math.max(answer, end - start);
            check[arr[start]]--;
            start++;
        }

        System.out.println(answer);

    }
}