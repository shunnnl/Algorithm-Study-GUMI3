package algo.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 1991 트리 순회
 * 메모리: 14120 KB
 * 시간: 128 ms
 */

public class boj1991 {
//public class Main {
	static int N;
	static Node root = new Node('A', null, null);
	
	static class Node {	// 노드 객체
		char self;
		Node leftnode;
		Node rightnode;
		
		Node(char self, Node leftnode, Node rightnode) {	// 노드 생성자
			this.self = self;
			this.leftnode = leftnode;
			this.rightnode = rightnode;
		}
	}
	
	static void insertNode(Node temp, char self, char l, char r) {	// temp노드와  본인 노드, left, right 노드 값 받음
		if (temp.self == self) {	// temp 노드에는 root로 시작하고 만약 같다면 left, right에 들어가는 노드 넣어줌
			if (l != '.') {
				temp.leftnode = new Node(l, null, null);
			}
			if (r != '.') {
				temp.rightnode = new Node(r, null, null);
			}
		}
		
		else {	// root 노드가 같지 않다면 left, right 중 비어있지 않는 노드로 들어가서 비교한다. 반복.
			if (temp.leftnode != null)
				insertNode(temp.leftnode, self, l, r);
			if (temp.rightnode != null) {
				insertNode(temp.rightnode, self, l, r);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {	// 입력 받아서 트리 생성
			st = new StringTokenizer(br.readLine());
			char s = st.nextToken().charAt(0);
			char l = st.nextToken().charAt(0);
			char r = st.nextToken().charAt(0);
			
			insertNode(root, s, l, r);
		} 
		
		// 트리 순회 및 출력 부분
		preorder(root);
		System.out.println();
		inorder(root);
		System.out.println();
		postorder(root);
	}
	
	static void preorder(Node root) {	// 전위 순회
		if (root == null) return;
		System.out.print(root.self);
		preorder(root.leftnode);
		preorder(root.rightnode);
	}
	
	static void inorder(Node root) {	// 중위 순회
		if (root == null) return;
		inorder(root.leftnode);
		System.out.print(root.self);
		inorder(root.rightnode);
	}
	
	static void postorder(Node root) {	// 후위 순회
		if (root == null) return;
		postorder(root.leftnode);
		postorder(root.rightnode);
		System.out.print(root.self);
	}

}
