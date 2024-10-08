package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * 문제 : 괄호 추가하기
 * 풀이 : 완전탐색
 * 메모리 : 14348KB
 * 시간 : 104ms
 */

public class _16637_괄호추가하기 {

    private static ArrayList<Character> operator;
    private static ArrayList<Integer> num;
    private static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 수식의 길이
        String str = br.readLine(); // 수식

        operator = new ArrayList<>();
        num = new ArrayList<>();

        // 연산자는 연산자 리스트에, 숫자는 숫자 리스트에 입력
        for (int i = 0; i < str.length(); i++) {
            if(i % 2 == 0) {
                int val = str.charAt(i) - '0';
                num.add(val);
            }
            else {
                operator.add(str.charAt(i));
            }
        }

        res = Integer.MIN_VALUE;
        dfs(0, num.get(0));
        System.out.println(res);
    }

    private static void dfs(int idx, int total) {
        if(idx == operator.size()) { // 모든 연산자를 다 사용했다면 종료
            if(res < total) res = total; // 최대값 찾기
            return;
        }

        // 괄호를 치고 연산 후 넘어가기
        // ex. (8 * 3) + 5
        int val = calculate(total, num.get(idx+1), operator.get(idx));
        dfs(idx+1, val);

        // 그냥 넘어가기
        // ex. 8 * (3 + 5)
        if(idx + 2 < num.size()) {
            val = calculate(total, calculate(num.get(idx+1), num.get(idx+2), operator.get(idx+1)), operator.get(idx));
            dfs(idx+2, val);
        }
    }

    private static int calculate(int a, int b, int op) {
        if(op == '+') return a + b;
        else if(op == '-') return a - b;
        else return a * b;
    }
}