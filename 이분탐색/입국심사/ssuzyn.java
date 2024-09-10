

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class �Ա��ɻ�_3079 {

    static int N;
    static long M, ans;
    static int[] desk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // �Ա� �ɻ�� ����
        M = Integer.parseInt(st.nextToken()); // ����̿� ģ����

        desk = new int[N];
        int max = 0;
        for(int i = 0; i < N; i++) {
            desk[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, desk[i]);
        }

        Arrays.sort(desk); // �̺�Ž���� ���� ����
        ans = max * M; // �ִ�� �ɸ��� �ð� = ����� * ���� ���� �ɸ��� �ɻ� �ð�

        search();
        System.out.println(ans);

    }

    static void search(){
        long low = 1;
        long high = ans;

        while(low <= high){
            long mid = (low + high) / 2;
            long people = 0;
            
            for(int time : desk){ // mid �ð� ���� �� �ɻ�뿡�� ó���� �� �ִ� ����� �� ���
                people += mid / time;
                
                if(people >= M) break;
            }

            if(people >= M){ // �־��� �ð� ���� ��� ����� ó���� �� �ִ� ���
                high = mid - 1; // �� ���� �ð��� Ž��
                ans = mid;
            }
            else{
                low = mid + 1; // �� ū �ð��� Ž��
            }
        }

    }
}
