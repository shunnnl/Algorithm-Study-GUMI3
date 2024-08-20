import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 18624kb, 시간	: 172ms
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken()); // 아이스크림 종류의 수
        int m=Integer.parseInt(st.nextToken()); // 섞어먹으면 안 되는 조합의 개수
        boolean noComb[][]=new boolean[n+1][n+1]; // 섞어먹으면 안되는 아이스크림 나타내는 배열 (true : 조합x)
        int cnt=0; // 조합 count

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int ice1=Integer.parseInt(st.nextToken());
            int ice2=Integer.parseInt(st.nextToken());
            noComb[ice1][ice2]=noComb[ice2][ice1]=true;
        }

        for(int i=1;i<=n;i++){
            for(int j=i+1;j<=n;j++){
                if(noComb[i][j]==false){
                    for(int k=j+1;k<=n;k++){
                        if(noComb[i][k]==false && noComb[j][k]==false) cnt++;
                    }
                }
            }
        }

        System.out.println(cnt);

    }
}
