package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * 문제 : 오리
 * 메모리 : 16084KB
 * 시간 : 144ms
 */

public class _12933_오리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] duck = {'q', 'u', 'a', 'c', 'k'};
        char[] chars = br.readLine().toCharArray();
        boolean[] visited = new boolean[chars.length];

        if(chars[0] != 'q' || chars.length % 5 != 0) { // 녹음한 소리가 올바르지 않은 경우
            System.out.println(-1);
            return;
        }

        int k = 0;
        int cnt = 0;
        ArrayList<Character> arr = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if(!visited[j] && chars[j] == duck[k]) {
                    k++;
                    arr.add(chars[j]);
                    visited[j] = true;
                    if(k == 5) k = 0;
                }
            }

            if(arr.size() != 0) {
                if(arr.get(arr.size()-1) != 'k') {
                    System.out.println(-1);
                    return;
                }
                cnt++;
            }
            arr.clear();
        }
        System.out.println(cnt);
    }
}
