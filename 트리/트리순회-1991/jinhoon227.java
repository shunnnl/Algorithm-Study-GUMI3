package sol.jinhoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 트리 순회 (B1991_트리순회)
 * 풀이: 트리
 * 메모리: 14180kb
 * 시간: 100ms
 */
public class B1991_트리순회 {

    int N;
    Node[] tree;
    StringBuilder result = new StringBuilder();

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            int parentNum = parent - 'A';
            // 이전에 생성된 적 없다면 부모 모드 생성
            if(tree[parentNum] == null) {
                tree[parentNum] = new Node(parent);
            }

            // 왼쪽 노드 생성
            if (left != '.') {
                int leftNum = left - 'A';
                tree[leftNum] = new Node(left);
                tree[parentNum].left = tree[leftNum]; // 부모의 왼쪽 자식에 연결
            }

            // 오른쪽 노드 생성
            if (right != '.') {
                int rightNum = right - 'A';
                tree[rightNum] = new Node(right);
                tree[parentNum].right = tree[rightNum]; // 부모의 오른쪽 자식에 연결
            }
        }

        //== 입력 끝 ==//

        // 전위 순회
        preOrder(tree[0]);
        result.append("\n");

        // 중위 순회
        centerOrder(tree[0]);
        result.append("\n");

        // 후위 순회
        lastOrder(tree[0]);
        result.append("\n");

        System.out.println(result);
    }

    static class Node {
        char center;
        Node left;
        Node right;

        public Node(char center) {
            this.center = center;
            left = null;
            right = null;
        }
    }

    // 전위 순회 => 현재 -> 왼쪽 -> 오른쪽
    private void preOrder(Node start) {
        if (start != null) { // 비어 있는 노드가 아니라면
            result.append(start.center); // 현재 노드 출력후
            preOrder(start.left); // 왼쪽 자식 노드 탐색 후
            preOrder(start.right); // 오른쪽 자식 노드 탐색
        }
    }

    // 중위 순회 => 왼쪽 -> 현재 -> 오른쪽
    private void centerOrder(Node start) {
        if (start != null) {
            centerOrder(start.left);
            result.append(start.center);
            centerOrder(start.right);
        }
    }

    // 후위 순회 => 왼쪽 -> 오른쪽 -> 현재
    private void lastOrder(Node start) {
        if (start != null) {
            lastOrder(start.left);
            lastOrder(start.right);
            result.append(start.center);
        }
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B1991_트리순회().solution();
//    }
//}
