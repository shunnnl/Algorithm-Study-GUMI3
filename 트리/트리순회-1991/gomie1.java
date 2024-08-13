import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class gomie1 {
    static class Node {
        char value; // 노드의 알파벳 값
        Node left;
        Node right;

        Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static int n; // 이진 트리의 노드의 개수
    static Node root=new Node('A',null,null); // 루트 노드

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            char temp_value = st.nextToken().charAt(0); // 값
            char left = st.nextToken().charAt(0); // 좌측 노드
            char right = st.nextToken().charAt(0); // 우측 노드

            insertNode(root, temp_value ,left, right);
        }

        // 전위 순회
        preorder(root);
        System.out.println();

        // 중위 순회
        inorder(root);
        System.out.println();

        // 후위 순회
        postorder(root);
        System.out.println();
    }

    public static void insertNode(Node temp, char value, char left, char right) {
        if (temp.value == value) {
            temp.left = (left == '.' ? null : new Node(left,null,null)); // '.'일 경우 아무것도 삽입x
            temp.right = (right == '.' ? null : new Node(right,null,null));
        }
        else {
            if(temp.left != null) insertNode(temp.left, value, left, right); // 탐색
            if(temp.right != null) insertNode(temp.right, value, left, right);
        }
    }

    // 전위 순회
    public static void preorder(Node node){
        if(node==null)return;
        System.out.print(node.value);
        preorder(node.left);
        preorder(node.right);
    }

    // 중위 순회
    public static void inorder(Node node){
        if(node==null)return;
        inorder(node.left);
        System.out.print(node.value);
        inorder(node.right);
    }

    // 후위 순회
    public static void postorder(Node node){
        if(node==null)return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value);
    }

}