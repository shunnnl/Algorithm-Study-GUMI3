package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제: 괄호 추가하기 (B16637)
 * 풀이: dfs 완탐
 * 메모리: 14352kb
 * 시간: 104ms
 */
public class B16637 {

    int N;
    char[] oper;
    int[] num;
    boolean[] oVisit;
    int maxNum = Integer.MIN_VALUE;
    int targetNum;

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        targetNum = N / 2;
        oper = new char[targetNum];
        oVisit = new boolean[targetNum];
        num = new int[targetNum + 1];

        // 문자 전처리
        // oper 에는 연산자만 담기고
        // num 에는 숫자만 담김
        String temp = br.readLine();
        for (int i = 0; i < temp.length(); i++) {
            if (i % 2 == 0) {
                num[i / 2] = temp.charAt(i) - '0';
            } else {
                oper[i / 2] = temp.charAt(i);
            }
        }

        dfs(0, num[0]);
        System.out.println(maxNum);
    }

    private void dfs(int cnt, int sum) {

        // 연산자를 다 사용했다면 최댓값 업데이트
        if (cnt == targetNum) {
            maxNum = Math.max(maxNum, sum);
            return;
        }

        // 괄호를 치지 않고, 기존값에다가 단순히 연산을 수행한것
        dfs(cnt + 1, cal(sum, num[cnt + 1], oper[cnt]));

        // a x (b + c) 와 같이 오른쪽에 괄호를 쳤을경우
        if (cnt + 1 < targetNum) {
            int right = cal(num[cnt + 1], num[cnt + 2], oper[cnt + 1]); // (b + c) 를 계산
            int left = cal(sum, right, oper[cnt]); // a + () 를 계산
            dfs(cnt + 2, left);
        }
    }


    // 연산 수행
    private int cal(int n1, int n2, int op) {
        if (op == '+') {
            return n1 + n2;
        } else if (op == '-') {
            return n1 - n2;
        }
        return n1 * n2;
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B16637().solution();
//    }
//}
