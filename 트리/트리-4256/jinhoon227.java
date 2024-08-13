package ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: (B4256_트리)
 * 풀이: 트리
 * 메모리: 39368kb
 * 시간: 632ms
 */
public class B4256_트리 {

    int N;
    int[] pre;
    int[] in;
    StringBuilder result = new StringBuilder();

    public void solution() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            pre = new int[N + 1];
            in = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pre[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                in[i] = Integer.parseInt(st.nextToken());
            }

            // pre : 전위순회
            // 루트 -> 왼쪽 -> 오른쪽으로 첫반째 방문 노드는 항상 루트 노드이다
            // 그리고 왼쪽을 먼저 탐색함으로 root index + 1 한 값은 왼쪽의 root 노드 이다.
            // 왼쪽을 전부 탐색후 오른쪽을 탐색하기 때문에, 왼쪽 노드의 수 만큼 이동한뒤에 오른쪽의 root 가 나온다.

            // in : 중위순회
            // 왼쪽 -> 루트 -> 오른쪽
            // 왼쪽을 전부 탐색 후, 루트 탐색 후 오른쪽을 탐색하므로
            // 루트 번호를 찾으면, 해당 루트를 기준으로 왼쪽 노드의 개수와 오른쪽 노드의 개수를 알 수 있다.
            // 전위 순회는 루트노드(한개) 탐색 후 왼쪽을 우선으로 탐색하고, 중위 순회에서도 왼쪽을 우선으로 탐색하므로
            // 중위 순회에서 루트번호를 기준으로 전위 순회에서 왼쪽 노드의 수를 알 수 있다. 왼쪽 노드의 수를 알았다면 오른쪽 노드의 수도 알 수 있다.
            // ex)
            // 전위 : 3-6-5-4-8-7-1-2  // 3이 루트노드면
            // 중위 : [5-6-8-4]-3-[1-2-7] // 중위에서 3을 기준으로 왼쪽노드는 4개다. 따라서 전위에서도 3을 제외하고 나오는 4개가 왼쪽 노드 개수다.
            // 전위 순회 에서 [6-5-4-8] 은 왼쪽에 있고, [7-1-2] 는 오른쪽에 있고
            // 여기서 [6-5-4-8] 의 6은 루트 노드, [7-1-2] 에서 7은 루트노드가 되고
            // 중위에서 [5]-6-[8-4], [1-2]-7-x 또 각각의 왼쪽노드와 오른쪽노드의 개수를 알 수 있다.
            // 이는 계속 반복되는 과정으로 재귀로 코드를 작성할 수 있다.

            postOrder(0, 0, N);
            result.append("\n");
        }

        System.out.println(result);
    }

    // 후위 순회
    // 왼쪽 -> 오른쪽 -> 루트
    private void postOrder(int root, int start, int end) {
        int r = pre[root]; // 전위 순회 에서 루트노드를 찾는다.

        for(int i=start; i<end; i++) {
            if(r==in[i]) { // 중위 순회에서 루트 노드가 어딨는지 찾았다면
                // 루트 노드 + 1 을해서 전위 순회의 루트 노드를 재설정, 다음 중위 순회 탐색 범위는 start~i 로 설정
                postOrder(root + 1, start, i); // 왼쪽 탐색

                // 루트 노드에서 (i-start) + 1 더한다. (i-start) 는 중위 순회에서 루트 기준 왼쪽 노드의 개수이다.
                // 루트 노드에서 왼쪽노드 개수 만큼 더해줌으로써 전위 순회에서 바로 오른쪽 노드를 탐색할 수 있다.
                // 다음 중위 순회 탐색 범위는 i+1 ~ end 로 설정한다.
                postOrder(root + (i - start) + 1, i + 1, end); // 오른쪽 탐색

                // 루트 노드 출력
                result.append(r).append(" ");
            }
        }
    }
}

//public class Main {
//    public static void main(String[] args) throws IOException {
//        new B4256_트리().solution();
//    }
//}
