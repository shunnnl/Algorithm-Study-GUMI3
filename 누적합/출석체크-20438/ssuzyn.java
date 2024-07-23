import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 학생의 수
		int K = Integer.parseInt(st.nextToken()); // 졸고있는 학생의 수
		int Q = Integer.parseInt(st.nextToken()); // 지환이가 출석 코드를 보낼 학생의 수
		int M = Integer.parseInt(st.nextToken()); // 주어질 구간의 수

		boolean[] check = new boolean[N+3];
		ArrayList<Integer> sleepStudent = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) { // 졸고 있는 학생 입장번호
			sleepStudent.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) { 
			int idx = Integer.parseInt(st.nextToken()); // 출석 코드를 받을 학생의 입장번호
			if(sleepStudent.contains(idx)) continue;

			if(!check[idx]) {
				for(int j = idx; j < N + 3; j++) {
					if(j % idx == 0 && !sleepStudent.contains(j)) {// 현재 번호의 배수인 경우 = 나머지 0
						check[j] = true;
					}
				}
			}
		}

		int sum[] = new int[N+3];
		for(int i = 3; i < check.length; i++) {
			sum[i] += sum[i-1] + (!check[i]? 1 : 0); // check[i]=false : 출석체크 하지 않은 학생
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			System.out.println(sum[E] - sum[S-1]);
		}
	}

}