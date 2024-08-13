import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 4256. 트리
 * 메모리: 36504 KB
 * 시간: 388 ms
 */
public class 트리_4256 {

    static StringBuilder sb = new StringBuilder();
    static int[] inorder, preorder;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int t = 0; t < test; t++) {
            n = Integer.parseInt(br.readLine());

            preorder = new int[n+1];
            inorder = new int[n+1];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) { // 전위 순회 저장
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) { // 중위 순회 저장
                inorder[i] = Integer.parseInt(st.nextToken());
            }

            postorder(0, 0, n);
            sb.append("\n");

        }

        System.out.println(sb.toString());
    }

    public static void postorder(int rootIdx, int start, int end) {
        int root = preorder[rootIdx];

        for(int i = start; i < end; i++) {
            if(inorder[i] == root) {
                postorder(rootIdx + 1, start,  i); // left
                postorder(rootIdx + i - start + 1, i + 1, end); // right
                sb.append(root).append(" "); // root
                return;

            }
        }

    }
}