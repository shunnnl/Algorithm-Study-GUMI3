package algo.study.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 16953 A -> B
 * 메모리 : 14184 KB
 * 시간 : 100 ms
 *
 */
public class Boj16953 {
    static long a, b;    // a는 시작 값, b는 목표 값
    // int로 선언하니 계속 틀려서 long으로 변경 후 성공
    static int minCnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        
        find(a, 0);
        if (minCnt == Integer.MAX_VALUE) 
            System.out.println("-1");
        else 
            System.out.println(minCnt + 1);
    }
    
    static void find(long num, int cnt) {
        if (num > b)    // num보다 커지면 return
            return;
        if (num == b) {    // num과 b가 같을때 최소 카운트로 변경
            if (minCnt > cnt) {
                minCnt = cnt;
            }
            return;
        }
        find(num*10 + 1, cnt + 1);    // * 10 + 1 을 실행하는 호출
        find(num*2, cnt + 1);        // * 2 를 실행하는 호출
    }
}