import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 메모리 : 14352 KB
 * 시간 : 104 ms
 */
public class Main {

    static int N;
    static int max = Integer.MIN_VALUE;
    static List<Character> op = new LinkedList<>();
    static List<Integer> num = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        for (int i = 0; i < N; i++) {
            char tmp = input.charAt(i);
            if (Character.isDigit(tmp))
                num.add(tmp - '0');
            else
                op.add(tmp);
        }

        dfs(0, num.get(0));
        System.out.println(max);

    }

    private static void dfs(int idx, int result) {
        if (idx == op.size()) {
            max = Math.max(max, result);
            return;
        }

        // 괄호 치기
        if (idx + 1 < op.size()) {
            int priority = calc(num.get(idx + 1), num.get(idx + 2), op.get(idx + 1));
            dfs(idx + 2, calc(result, priority, op.get(idx)));
        }

        // 괄호를 추가하지 않은 경우
        dfs(idx + 1, calc(result, num.get(idx + 1), op.get(idx)));

    }

    private static int calc(int num1, int num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }
        return 0;
    }
}
