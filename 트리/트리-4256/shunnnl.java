import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 63668 / 1712
public class Main {
    static int n; // 노드의 개수 n
    static int[] preorder; // 전위순회
    static int[] inorder; // 중위순회

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int t=Integer.parseInt(st.nextToken()); // 테스트 케이스 수 t
        for(int i=0;i<t;i++){
            st=new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            preorder=new int[n];
            inorder=new int[n];

            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                preorder[j]=Integer.parseInt(st.nextToken());
            }

            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                inorder[j]=Integer.parseInt(st.nextToken());
            }

            find(0,0,n-1);
            System.out.println();
        }
    }

    public static void find(int rootIdx, int begin, int end){
        if(rootIdx>=n)return;
        int rootValue=preorder[rootIdx]; // 기준이 되는 루트 값

        for(int idx=begin ; idx<=end ; idx++){
            // 중위순회에서 기준이 되는 루트 값 기준으로 왼쪽은 좌측 트리, 오른쪽은 우측 트리
            if(rootValue==inorder[idx]){
                find(rootIdx+1,begin,idx); // 좌측
                find(rootIdx+idx+1-begin,idx+1,end); // 우측
                System.out.print(rootValue + " ");
                return;
            }
        }
    }
}
