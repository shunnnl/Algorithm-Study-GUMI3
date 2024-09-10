import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // �迭 ũ��
        int K = Integer.parseInt(br.readLine()); // ���ؾ� �ϴ� k��° ��

        // �̺� Ž���� ���� ���� ����
        long low = 1;
        long high = (long) N * N;
        long answer = 0;

        while (low <= high) {
            long mid = (low + high) / 2;
            long count = 0;

            // mid���� �۰ų� ���� ���� ���� ����
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N); // �� �࿡�� mid���� ���� ���� ����
            }

            if (count >= K) {
                answer = mid; // K��° ���� mid ���϶�� ���̹Ƿ�, mid�� ������ ����
                high = mid - 1; // �� ���� ���� ã�� ���� high�� ����
            } else {
                low = mid + 1; // �� ū ���� ã�� ���� low�� �ø�
            }
        }

        System.out.println(answer);
    }
}
