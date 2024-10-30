package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 3190. 뱀
 * 메모리 : 15260 KB
 * 시간 : 116 ms
 */
public class 뱀_3190 {

    static int N, K, time;
    static int[][] map;
    static char[] rotate = new char[10000];
    static int[][] d = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 상좌하우
    static Deque<int[]> snake = new ArrayDeque<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 보드의 크기
        K = Integer.parseInt(br.readLine()); // 사과의 개수
        map = new int[N][N];
        for(int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            map[row][col] = 1; // 사과의 위치
        }

        int L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            rotate[time] = dir;
        }

        time = 1;
        map[0][0] = 2;
        snake.add(new int[]{0, 0});

        play(0, 1, 3); // 뱀은 처음에 오른쪽을 향한다.
        System.out.println(time);
    }


    private static void play(int x, int y, int dir){

        // 벽에 부딪히거나 자기 자신의 몸과 부딪히면 게임이 끝난다.
        if(x < 0 || x >= N || y < 0 || y >= N || map[x][y] == 2) return;

        snake.addFirst(new int[]{x, y}); // 머리 늘려주기
        int apple = map[x][y];
        map[x][y] = 2;

        if(apple == 0 && !snake.isEmpty()){
            int[] tail = snake.pollLast(); // 꼬리 비우기
            map[tail[0]][tail[1]] = 0;
        }

        switch (rotate[time]) {
            case 'L': // 왼쪽 일때 시계방향으로 전환
                dir = (dir + 1) % 4;
                break;
            case 'D':
                dir = (dir + 3) % 4;
                break;
        }

        time++;
        play(x + d[dir][0], y + d[dir][1], dir);
    }
}
