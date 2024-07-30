import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

// queue에 넣기 위한 노드
class Node{
    int x;
    int y;
    Node(int x, int y){
        this.x=x;
        this.y=y;
    }
}

public class Main {
    static char arr[][];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr=new char[8][8]; // 미로를 나타내는 2차원 배열 (. : 가능, # : 불가능)
        boolean visited[][] = new boolean[8][8]; // 방문 여부 판단하는 2차원 배열
        int[] dx={1,1,-1,-1,0,0,1,-1,0};
        int[] dy={-1,1,-1,1,1,-1,0,0,0};

        for(int i=0;i<8;i++){
            arr[i]=br.readLine().toCharArray();
        }

        Queue<Node> queue=new LinkedList<>();
        queue.add(new Node(7,0));

        while(!queue.isEmpty()){
            visited = new boolean[8][8];

            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node tmp = queue.poll();

                if(tmp.x==0 && tmp.y==7){
                    System.out.println(1);
                    return;
                }

                for(int d=0;d<9;d++){
                    int nx=tmp.x + dx[d];
                    int ny=tmp.y + dy[d];
                    if(nx>=0 && nx<8 && ny>=0 && ny<8){
                        if(visited[nx][ny]==false && arr[nx][ny]=='.'){
                            if(nx==0){
                                visited[nx][ny] = true;
                                queue.offer(new Node(nx, ny));
                                continue;
                            }
                            if(arr[nx-1][ny]=='.') {
                                visited[nx][ny] = true;
                                queue.offer(new Node(nx, ny));
                            }
                        }
                    }
                }
            }
            wall_move();
        }
        System.out.println(0);
    }

    public static void wall_move(){
        // 제일 아래 부분은 . 로 초기화하고 시작
        for(int i=0;i<8;i++){
            arr[7][i]='.';
        }
        for(int i=6;i>=0;i--){
            for(int j=0;j<8;j++){
                if(arr[i][j]=='#'){
                    arr[i][j]='.';
                    arr[i+1][j]='#';
                }
            }
        }
    }
}