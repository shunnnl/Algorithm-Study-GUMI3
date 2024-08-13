import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 1991. 트리 순회
 * 메모리: 14156 KB
 * 시간: 124 ms
 */
public class 트리순회_1991 {

    static Node[] tree;

    static class Node{
        char value;
        Node left;
        Node right;

        public Node(char value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void preorder(Node node) {
        if(node == null) return;
        System.out.print(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    public static void inorder(Node node) {
        if(node == null) return;
        inorder(node.left);
        System.out.print(node.value);
        inorder(node.right);
    }

    public static void postorder(Node node) {
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value);
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new Node[N + 1];

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char value = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);


            if(tree[value - 'A'] == null) { // 입력된 노드의 정보가 없다면
                tree[value - 'A'] = new Node(value);
            }

            if(left != '.') {
                tree[left - 'A'] = new Node(left);
                tree[value - 'A'].left = tree[left - 'A'];
            }

            if(right != '.') {
                tree[right - 'A'] = new Node(right);
                tree[value - 'A'].right = tree[right - 'A'];
            }
        }

        preorder(tree[0]);
        System.out.println();

        inorder(tree[0]);
        System.out.println();

        postorder(tree[0]);
        System.out.println();
    }
}