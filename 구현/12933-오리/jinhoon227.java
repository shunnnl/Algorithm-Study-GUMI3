package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제: 오리 (B12933)
 * 풀이: 완탐
 * 메모리: 14232kb
 * 시간: 108ms
 */
public class B12933 {

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] sound = br.readLine().toCharArray(); // 녹음 소리 저장

        boolean[] check = new boolean[sound.length]; // 체크한 소리인지 확인하는 배열
        int answer = 0;

        char[] quack = {'q', 'u', 'a', 'c', 'k'}; // 꽉
        int qIdx = 0; // 꽉배열 현재 인덱스 위치
        int preQIdx = 0; // 이전 꽉배열 인덱스 위치

        // 최대 꽉 소리가 나올 수 있을 만큼 반복한다
        int maxLen = sound.length / 5 + 1;
        // 녹음 소리를 한바퀴 돌면서 꽉소리를 체크 한다
        for (int q = 0; q < maxLen; q++) {
            for (int i = 0; i < sound.length; i++) {

                // 이미 체크했다면 패스
                if (check[i]) {
                    continue;
                }

                // quack 소리를 순서대로 잘 나오고 있다면
                // 해당 소리를 체크해준다
                if (sound[i] == quack[qIdx % 5]) {
                    check[i] = true;
                    qIdx++;
                }
            }

            // 녹음 소리를 한 사이클 돌았을때
            // 1. qIdx % 5 가 q 를 가리키지 않는다는것은
            // quack 소리를 완벽히 못냈다는 뜻이므로 잘못된 녹음소리
            // 2. 이전 qIdx 와 preQIdex 값이 같다면
            // 아직 체크할 녹음 소리를 남았는데, 새로운 quack 소리가 없었다는 뜻이므로 잘못된 녹음소리
            if (qIdx % 5 != 0 || qIdx == preQIdx) {
                System.out.println("-1");
                return;
            }

            preQIdx = qIdx;
            answer++;

            // 만약 qIdx 의 값이 sound.length 면 녹음된 모든 소리가 체크되었으므로 반복문 탈출
            if (qIdx == sound.length) {
                break;
            }
        }

        System.out.println(answer);
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B12933().solution();
//    }
//}
