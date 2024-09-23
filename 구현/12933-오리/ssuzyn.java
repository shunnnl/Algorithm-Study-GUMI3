import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 12933. 오리
 * 메모리: 14612 KB
 * 시간 : 116 ms
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        String quack = "quack"; // "quack"의 순서에 해당하는 문자열
        List<Integer> ducks = new ArrayList<>(); // 오리의 상태를 저장하는 리스트
        int count = 0;

        if(input[0] != 'q' || input.length % 5 != 0){
            System.out.println(-1);
            return;
        }

        for (char c : input) {
            boolean found = false;

            // 현재 울음소리를 처리할 수 있는 오리가 있는지 확인
            for (int i = 0; i < ducks.size(); i++) {
                if (quack.charAt(ducks.get(i)) == c) {
                    ducks.set(i, ducks.get(i) + 1); // 다음 단계로 진행
                    found = true;

                    // 오리가 "quack"을 완료하면 상태 초기화
                    if (ducks.get(i) == 5) {
                        ducks.set(i, 0);
                    }
                    break;
                }
            }

            // 기존 오리들이 처리하지 못한 소리라면 새로운 오리 추가
            if (!found) {
                if (c == 'q') {
                    ducks.add(1); // 새로운 오리는 'q'로 시작
                    count = Math.max(count, ducks.size()); // 최대 오리 수 기록
                } else {
                    System.out.println(-1);
                    return;
                }
            }
        }

        // 모든 오리가 제대로 "quack"을 완료했는지 확인
        for (int state : ducks) {
            if (state != 0) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(count);
    }
}