package algo.study.week12;
/**
 * 백준 2146 다리 만들기
 * 메모리 : 137716 KB
 * 시간 : 272 ms 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2146 {
    
    static int N;    // 지도의 크기
    static int map[][];
    static boolean visited[][];
    static int landCnt = 1;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int minBridge = Integer.MAX_VALUE;
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력 끝
        
        // BFS로 섬 찾기 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                landFind(i, j);
            }
        }
        
        // 각 섬에서 다른 섬까지 최단거리 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 섬일때
                if (map[i][j] != 0) {
                    bridgeFind(i, j, map[i][j]);
                }
            }
        }
        System.out.println(minBridge);
        
    }
    
    // BFS
    public static void landFind(int y, int x) {
        //  땅이 없거나 이미 탐색된 곳이면 return
        if (visited[y][x] == true || map[y][x] == 0)
            return;

        landCnt++;
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {y, x});
        visited[y][x] = true;
        map[y][x] = landCnt;
        
        while(!que.isEmpty()) {
            int[] now = que.poll();
            for (int d = 0; d < 4; d++) {
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];
                
                if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx] && map[ny][nx] == 1) {
                    map[ny][nx] = landCnt;
                    visited[ny][nx] = true;
                    que.add(new int[] {ny, nx});
                }
            }
        }
    }
    
    // BFS 섬일때만 들어옴
    public static void bridgeFind(int y, int x, int startLand) {
        boolean checked[][] = new boolean[N][N];
        
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {y, x, 0});
        checked[y][x] = true;
        
        while(!que.isEmpty()) {
            int[] now = que.poll();
            for (int d = 0; d < 4; d++) {
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];
                
                if (ny >= 0 && ny < N && nx >= 0 && nx < N && checked[ny][nx] == false) {
                    if (map[ny][nx] == startLand) {    // 같은 땅에서 이동인 경우
                        continue;
                    }
                    else if (map[ny][nx] == 0) {    // 바다인 경우
                        checked[ny][nx] = true;
                        que.add(new int[] {ny, nx, now[2] + 1});
                    }
                    else {    // 찾은 경우
                        minBridge = Math.min(minBridge, now[2]);
                        return;
                    }
                }
            }
        }
    }
}