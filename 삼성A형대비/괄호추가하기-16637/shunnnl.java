import java.io.BufferedReader;
import java.io.InputStreamReader;

// 메모리 : 14212kb / 시간 : 100ms
public class BOJ_16637 {
    static int N; // 수식의 길이
    static int M;
    static int[] numArr; // 숫자 배열
    static char[] opArr; // 연산자 배열
    static int res=Integer.MIN_VALUE; // 연산 최댓값

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        String str=br.readLine();
        M=N/2;

        numArr=new int[M+1];
        opArr=new char[M];

        for(int i=0;i<N;i++){
            if(i%2==0) { // 인덱스 짝수일 경우 숫자
                numArr[i/2]=Integer.parseInt(String.valueOf(str.charAt(i)));
            } else{ // 인덱스 홀수일 경우 연산자
                opArr[i/2]=str.charAt(i);
            }
        }
        dfs(numArr[0],0);
        System.out.println(res);

    }


    // 산술 함수
    private static int cal(char op, int num1, int num2){
        if(op=='+') return num1+num2;
        else if(op=='-') return num1-num2;
        else return num1*num2;
    }

    private static void dfs(int tmpRes, int index){
        if(index>=M){
            res=Math.max(tmpRes,res);
            return;
        }

        // 1. 괄호 쳐서 넘어가는 경우 -> ex. (8*3)+5
        dfs(cal(opArr[index], tmpRes, numArr[index+1]),index+1);

        // 2. 괄호 안치고 넘어가는 경우 -> ex. 8*(3+5)
        if(index+1<M){
            int tmpRes1=cal(opArr[index+1],numArr[index+1],numArr[index+2]);
            dfs(cal(opArr[index],tmpRes,tmpRes1),index+2);
        }
    }
}
