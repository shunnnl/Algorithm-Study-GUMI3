import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리: 15636 KB
 * 시간: 128 ms
 */
public class 게리맨더링_17471 {

    static int N, answer = Integer.MAX_VALUE;
    static int[] population;
    static List[] section;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 구역의 개수
        population = new int[N + 1];
        section = new List[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            section[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                section[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        divide(1, new boolean[N + 1]);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void divide(int idx, boolean[] selected) {
        if (idx == N) {
            int diff = 0;
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (selected[i]) {
                    groupA.add(i);
                    diff += population[i];
                } else {
                    groupB.add(i);
                    diff -= population[i];
                }
            }

            if (groupA.isEmpty() || groupB.isEmpty()) {
                return;
            }

            if (check(groupA) & check(groupB)) {
                answer = Math.min(answer, Math.abs(diff));
            }
            return;

        }

        selected[idx] = true;
        divide(idx + 1, selected);

        selected[idx] = false;
        divide(idx + 1, selected);
    }

    private static boolean check(List<Integer> group) {
        boolean[] visited = new boolean[N + 1];
        isConnect(group.get(0), group, visited);

        for(int node : group){
            if(!visited[node]) return false;
        }
        return true;
    }

    private static void isConnect(int idx, List<Integer> group, boolean[] visited) {
        visited[idx] = true;

        for (int j = 0; j < section[idx].size(); j++) {
            int neighbor = (int) section[idx].get(j);
            if (group.contains(neighbor) && !visited[neighbor]) {
                isConnect(neighbor, group, visited);
            }
        }
    }
}