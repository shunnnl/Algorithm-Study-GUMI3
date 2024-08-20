import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 16088kb, 시간	: 116ms
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine()); // 질문 횟수
        String num; // 외친 숫자
        int strike; // 스트라이크 횟수
        int ball; // 볼 횟수
        boolean[] checked=new boolean[1000]; // true : 고려하지 않는 숫자

        // 시작하기 전 0을 포함하거나 동일한 숫자가 있으면 true로 변경
        for(int i=123;i<1000;i++){
            String str = Integer.toString(i);

            // 0을 포함할 경우 제외
            if(str.charAt(0)=='0' || str.charAt(1)=='0' || str.charAt(2)=='0') checked[i]=true;

            // 동일한 숫자 있을 경우 제외
            if(str.charAt(0)==str.charAt(1) || str.charAt(0)==str.charAt(2) || str.charAt(1)==str.charAt(2)) checked[i]=true;
        }

        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            num=st.nextToken();
            strike=Integer.parseInt(st.nextToken());
            ball=Integer.parseInt(st.nextToken());

            // 123~999비교하며 가능한 숫자 구분
            for(int j=123;j<1000;j++){
                if(!checked[j]){ // 정답 가능성 일 경우(false)
                    int strike_cnt=0; // 스트라이크 횟수
                    int ball_cnt=0; // 볼 횟수

                    // 내가 입력받은 숫자를 기준으로 ball, strike 비교하기
                    for(int first=0;first<3;first++){
                        char num_split = num.charAt(first);

                        for(int second=0;second<3;second++){
                            char j_split = Integer.toString(j).charAt(second);

                            if(num_split == j_split && first == second) strike_cnt++; // strike
                            else if(num_split == j_split && first != second) ball_cnt++; // ball
                        }
                    }
                    // strike, ball 카운트가 동일하지 않으면 가능성 없는 수
                    if(!(strike==strike_cnt && ball==ball_cnt)) checked[j]=true;
                }
            }
        }

        // 카운팅
        int cnt = 0;
        for(int i=123;i<1000;i++){
            if(!checked[i]) cnt++;
        }
        System.out.println(cnt);
    }
}
