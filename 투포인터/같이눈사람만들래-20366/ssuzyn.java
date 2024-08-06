import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] snow;
    static int diff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 배열 A의 크기
        StringTokenizer st = new StringTokenizer(br.readLine());

        snow = new int[N];
        for(int i = 0; i < N; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snow);

        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {

                int snowMan1 = snow[i] + snow[j]; // 엘사가 먼저 눈사람 고르기
                int start = 0, end = N-1;

                while(start < end) {
                    if(start == i || start == j) {
                        start++;
                        continue;
                    }

                    if(end == i || end == j) {
                        end--;
                        continue;
                    }

                    int snowMan2 = snow[start] + snow[end];
                    diff = Math.min(Math.abs(snowMan1 - snowMan2), diff);

                    if(snowMan1 > snowMan2) {
                        start++;
                    }
                    else if(snowMan1 < snowMan2){
                        end--;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(diff);
    }

}