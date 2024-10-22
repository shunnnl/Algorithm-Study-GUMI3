package algo.study.week13;
/**
 * 백준 14503 로봇 청소기
 * 메모리 : 14364 KB
 * 시간 : 112 ms 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14503 {
    
    static int N, M;    // 방사이즈
    static int r, c, d;    // 위치 위치, 방향
    static int[][] map;
    static int dr[] = {-1, 0, 1, 0};    // 북 동 남 서
    static int dc[] = {0, 1, 0, -1};
    static boolean cleaned[][];
    static int result = 0;
    static boolean flag = false;	// 조건 2, 3 구분용
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cleaned = new boolean[N][M];
        
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력 끝
        
        while(true) {
            flag = false;
            // 조건1, 자기 자리 청소하기
            if (cleaned[r][c] == false) {
                cleaned[r][c] = true;
                continue;
            }
            // 조건2, 3
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                // 조건 3 청소되지 않은 빈 칸이 있는 경우
                if (cleaned[nr][nc] == false && map[nr][nc] == 0) {
                    d -= 1;    // 반시계 90도 회전
                    if (d < 0)
                        d += 4;
                    nr = r + dr[d];
                    nc = c + dc[d];
                    
                    // 회전 후 바라본 방향이 청소되지 않았다면 한칸 이동
                    if (map[nr][nc] == 0 && cleaned[nr][nc] == false) {
                        r = nr;
                        c = nc;
                    }
                    // 아니라면 이동 안하고 1번으로
                    flag = true;
                    break;
                }
            }
            
            // 빈칸이 없는 경우
            if (flag == false) {
                int tempd = (d + 2) % 4;    // 후진위해 방향 체크
                int tempdr = r + dr[tempd];
                int tempdc = c + dc[tempd];
                // 후진 가능하다면 뒤로 이동
                if (map[tempdr][tempdc] == 0) {
                    r = tempdr;
                    c = tempdc;
                }
                // 후진 안되면
                else
                    break;
            }
        }
        
        // 청소한 곳의 개수 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 청소되어있다면
                if (cleaned[i][j] == true)
                    result++;
            }
        }
        
        System.out.println(result);
    }
}