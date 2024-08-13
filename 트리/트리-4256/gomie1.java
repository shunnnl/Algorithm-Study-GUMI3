import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class gomie1 {
    static StringBuilder sb = new StringBuilder();
    static int[] inorder, preorder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); // 테스트 케이스의 개수
        for(int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine()); // 노드의 개수
            preorder = new int[n+1];
            inorder = new int[n+1];

            // 전위 순회 결과 입력 받기
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            // 중위 순회 결과 입력 받기
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++) {
                inorder[j] = Integer.parseInt(st.nextToken());
            }

            postorder(0, 0, n);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    // 후위 순회
    static void postorder(int root, int start, int end) {
        int r = preorder[root]; // 루트 노드

        for(int i = start; i < end; i++) {
            if(r == inorder[i]) {
                postorder(root+1, start, i);
                postorder(root+(i - start + 1), i+1, end);
                sb.append(r).append(" ");
                return;
            }
        }
    }

}
