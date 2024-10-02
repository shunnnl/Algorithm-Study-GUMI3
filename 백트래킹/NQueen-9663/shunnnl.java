import java.io.BufferedReader;
import java.io.InputStreamReader;

// 메모리 : 14596kb / 시간 : 5788ms
// NXN인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제
public class Q_9663 {
    static int[] arr; // 각 행의 체스판 말 위치 ex) 첫번째 행의 두번째 열에 위치 -> arr[0]=1
    static int N;
    static int cnt=0; // 서로 공격 불가능한 횟수

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        arr=new int[N];

        nQueen(0);
        System.out.println(cnt);
    }


    public static void nQueen(int depth){
        // 기저 조건 : 모든 원소를 다 채운 경우
        if(depth==N){
            cnt++;
            return;
        }

        for(int i=0;i<N;i++){
            arr[depth]=i;
            if(isPossible(depth)){ // 놓을 수 있는 경우 확인
                nQueen(depth+1);
            }
        }
    }

    // 위치 확인
    public static boolean isPossible(int row){
        for(int i=0;i<row;i++){
            if(arr[row]==arr[i]) // 같은 열에 위치할 경우
                return false;
            else if(Math.abs(arr[row]-arr[i])==Math.abs(row-i)) // 대각선에 위치할 경우
                return false;
        }

        return true;
    }
}
