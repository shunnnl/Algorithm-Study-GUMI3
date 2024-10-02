import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 메모리 : 14424kb,	시간 : 116ms
public class Q_12933 {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine(); // 녹음한 소리
        char[] duck={'q','u','a','c','k'};
        ArrayList<Character> arr=new ArrayList<>(); // 제거 용이하여 관리
        int idx=0; // 오리 울음소리 요소 count
        int result=0;

        if(str.charAt(0)!='q' || str.length()%5!=0){ // 맨 앞자리 q가 아니거나 길이가 5의 배수가 아닌 경우 -1
            System.out.println(-1);
            return;
        }

        for(int i=0;i<str.length();i++) arr.add(str.charAt(i));

        while(true){
            for(int i=0;i<arr.size();i++){
                char ch=arr.get(i);
                char find=duck[idx];
                if(ch==find){
                    idx=(idx+1)%5;
                    arr.remove(i);
                    i--;
                }
            }
            // 위의 과정으로 한 마리의 quack 사라짐

            if(idx==0) result++; // 정확하게 0번을 가리켜야 중간에 끊기질 않음
            else{
                System.out.println(-1);
                break;
            }
            if(arr.isEmpty()){ // 비면 종료
                System.out.println(result);
                break;
            }
        }

    }
}
