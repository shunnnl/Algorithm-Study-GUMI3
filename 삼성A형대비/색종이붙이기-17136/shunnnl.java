import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 26832kb / 시간 : 336ms
public class BOJ_17136 {
    static int[][] arr=new int[10][10]; // 종이
    static int[] papers={0,5,5,5,5,5}; // 색종이 개수
    static int res=Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        for(int i=0;i<10;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<10;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0,0);
        if(res==Integer.MAX_VALUE) res=-1;
        System.out.println(res);
    }

    private static void dfs(int x, int y, int cnt){
        if(res<=cnt) return; // 더 크면 탐색할 필요x (가지치기)

        if(x>=9 && y>9){ // 맨 끝점에 도달
            res=Math.min(res,cnt);
            return;
        }

        // 아래 줄로 이동
        if(y>9){
            dfs(x+1,0,cnt);
            return;
        }

        if(arr[x][y]==1){ // 색종이 붙이기 시작
            for(int i=5;i>=1;i--){
                if(papers[i]>0 && isAttach(x,y,i)){ // 가능하다면
                    attach(x,y,i,0); // 색종이 붙이기
                    papers[i]--;
                    dfs(x,y+1,cnt+1);
                    attach(x,y,i,1); // 색종이 뗴기
                    papers[i]++;

                }
            }
        }else{ // 오른쪽으로 이동
            dfs(x,y+1,cnt);
        }
    }

    // 색종이 붙이는 함수
    private static void attach(int x, int y, int size, int state) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                arr[i][j] = state;
            }
        }
    }

    // 색종이 붙일 수 있는지 확인 (0이 있으면 안됨)
    private static boolean isAttach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                    return false;
                }

                if (arr[i][j] != 1) { // 1인 부분에서는 붙일 수 없음
                    return false;
                }
            }
        }
        return true;
    }
}

