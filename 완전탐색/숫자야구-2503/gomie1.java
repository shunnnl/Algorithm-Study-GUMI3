import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _2503_숫자야구 {

    static ArrayList<question> arr = new ArrayList<>();
    static int res;

    static class question {
        int num;
        int strike;
        int ball;

        question(int n, int s, int b) {
            this.num = n;
            this.strike = s;
            this.ball = b;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 질문 횟수

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            arr.add(new question(num, strike, ball));
        }

        res = 0;
        baseball(N);
        System.out.println(res);
    }

    private static void baseball(int size) {
        // 서로 다른 숫자 세개로 구성된 숫자를 맞추는 것이므로
        // 정답이 될 수 있는 수는 123~987
        for(int value = 123; value <= 987; value++) {
            if(!isValidNum(value)) {
                continue;
            }

            int cnt = 0;
            for(int i = 0; i < size; i++) { // 질문의 수만큼 반복하며
                if(isAnswer(arr.get(i).num, value, i)) { // value에 대한 s, b와 영수가 대답한 s, b가 같은지 확인
                    cnt++; // 같다면 cnt 1 증가
                }
                else { // 정답 가능성이 없다면 pass
                    break;
                }
            }

            // 모든 질문에 대한 영수의 답과 value의 답이 같다면
            if(cnt == size) {
                // System.out.println("value = " + value);
                res++; // 정답이 될 수 있는 수이므로 res 1 증가
            }
        }
    }

    private static boolean isValidNum(int num) { // 정답으로 예측하는 숫자에 0이 있거나 중복되는 숫자가 있는지 확인
        int first = num / 100;
        int second = (num % 100) / 10;
        int third = num % 10;

        if(first == second || first == third || second == third || first == 0 || second == 0 || third == 0) {
            return false;
        }
        return true;
    }

    // n1 : 질문한 숫자, n2 : 정답으로 예측하는 숫자
    private static boolean isAnswer(int n1, int n2, int idx) {
        ArrayList<Integer> numList1 = new ArrayList<>();
        numList1.add(n1 / 100);
        numList1.add((n1 % 100) / 10);
        numList1.add(n1 % 10);

        ArrayList<Integer> numList2 = new ArrayList<>();
        numList2.add(n2 / 100);
        numList2.add((n2 % 100) / 10);
        numList2.add(n2 % 10);

        int s = 0, b = 0;
        for(int i = 0; i < 3; i++) {
            if(numList1.contains(numList2.get(i))) { // n1을 구성하는 숫자 중 n2를 구성하는 숫자가 있는 경우
                if(numList1.get(i).equals(numList2.get(i))) { // 위치까지 같다면
                    s++; // strike 1 증가
                }
                else {
                    b++; // 위치는 다르다면 ball 1 증가
                }
            }
        }

        // 영수가 대답한 strike, ball과 정답으로 예측한 수의 s, b가 같다면
        if(arr.get(idx).strike == s && arr.get(idx).ball == b) {
            return true; // 정답이 될 수 있으므로 true 반환
        }

        return false; // 아닌 경우 false 반환
    }
}

