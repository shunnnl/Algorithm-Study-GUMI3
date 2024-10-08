package algo.study.week11;

import java.util.Scanner;
/**
 * 백준 17135 캐슬 디펜스
 * 메모리 : 34988 KB
 * 시간 : 576 ms 
 */
public class Boj17135 {
    
    static int N, M, D; // N*M 맵 크기, D: 궁수 공격 사거리
    static int maxKill = Integer.MIN_VALUE; // 궁수 최다 킬
    static int[][] map;    // 맵 현황
    static int[] archer; // 궁수 위치
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt();
        map = new int[N][M];
        archer = new int[3];    // 궁수는 무조건 3명
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        // 입력 끝
        
        picked(0, 0);
        System.out.println(maxKill);
    }

    // 궁수 세명의 위치를 조합으로 뽑는 method
    public static void picked(int start, int cnt) {
        if (cnt == 3) {
            // 세 명을 뽑을때마다 게임 시작 후 maxKill 업데이트
            maxKill = Math.max(maxKill, game());
            return;
        }
        // 모든 조합을 뽑아봄
        for (int i = start; i < M; i++) {
            archer[cnt] = i;
            picked(start + 1, cnt + 1);
        }
    }
    
    // 게임 실행하는 method
    public static int game() {
        int[][] newMap = new int[N][M];    
        // 새로운 맵에 복사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        int count = 0;
        int kill = 0;
        
        // 리스트 내려주기 + 최단거리(킬 계산)을 행 길이 만큼 반복할거임
        while(count++ < N) {    
            // 공격해주고 죽인 숫자 return 받기
            kill += attack(archer, newMap);
            // 한칸씩 내려줌
            for (int i = N-1; i > 0; i--) {
                for (int j = 0; j < M; j++) {
                    newMap[i][j] = newMap[i-1][j];
                }
            }
            // 맨 윗줄은 삭제 (원래 처음에만 하면되는데 계산량 얼마 안되니 그냥 냅둠)
            for (int j = 0; j < M; j++)
                newMap[0][j] = 0;
        }
        return kill;
    }
    
    public static int attack(int[] archerLoc, int[][] map) {
        int bfenemy, afenemy;    // 쏘기 전, 후의 적의 수
        int shoot[][] = {{-1,-1, Integer.MAX_VALUE}, {-1,-1, Integer.MAX_VALUE},{-1,-1, Integer.MAX_VALUE}};    // 세명의 궁수가 공격할 적 x좌표, y좌표 , 거리
        int distance; // 적과 궁수와의 거리
        
        bfenemy = enemyCount(map);
        // logic
        for (int i = 0; i < 3; i++) {
            int[] nowArcher = {N, archerLoc[i]};
            for (int x = 0; x < M; x++) {
                for (int y = 0; y < N; y++) {
                    if (map[y][x] == 1) {    // 적이 있다면
                        distance = Math.abs(nowArcher[0] - y) + Math.abs(nowArcher[1] - x);
                        // 적의 거리가 사정거리 안에 들어오고, 기존의 거리보다 짧을때 표적 업데이트 (항상 왼쪽부터 탐색하기 때문에 더 짧을때만 새로고침함)
                        if (distance <= D &&  distance < shoot[i][2]) {    
                            shoot[i][0] = y;
                            shoot[i][1] = x;
                            shoot[i][2] = distance;
                        }
                    }
                }
            }
        }
        
        // 각 궁수의 목표 제거
        for (int i = 0; i < 3; i++) {
            if (shoot[i][0] != -1) {    // 쏠 적이 있다면
                map[shoot[i][0]][shoot[i][1]] = 0;    // 죽임
            }
        }
        
        afenemy = enemyCount(map);    // 남은 적 확인
        // 기존 적 - 남은 적 = 죽인 적
        return bfenemy - afenemy;
        
    }
    
    // 맵에 있는 적의 수 알아내는 method
    public static int enemyCount(int[][] map) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sum += map[i][j];
            }
        }
        return sum;
    }
}