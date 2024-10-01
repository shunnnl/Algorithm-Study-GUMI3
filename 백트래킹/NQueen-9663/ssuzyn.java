import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueen_9663 {

    static int N, answer;
    static int[] queen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queen = new int[N+1];

        setQueens(1);
        System.out.println(answer);
    }

    private static void setQueens(int idx){

        if(idx > N){
            answer++;
            return;
        }

        for(int i = 1; i <= N; i++){
            queen[idx] = i;
            if(isAvailable(idx)) setQueens(idx + 1);
        }
    }

    private static boolean isAvailable(int x){
        for(int i = 1; i < x; i++){
            // 같은 열에 있는 경우
            if(queen[x] == queen[i]) return false;
            // 같은 대각선에 있는 경우
            if(x - i == Math.abs(queen[x] - queen[i])) return false;
        }
        return true;
    }
}
