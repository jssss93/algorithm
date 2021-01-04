package algorithm.cjs_20201222;

import java.util.Stack;

public class pickTest {

	static char array[] = { 'A', 'B', 'C', 'D' ,'E'};

	public static void main(String[] args) {
		Stack<Integer> st = new Stack<Integer>();
//		for (int i = 1; i <= array.length; i++) {
//			System.out.println(i + "개 뽑을경우 조합");
			pick(array.length, st, 3);
//		}
	}

	static void pick(int n, Stack<Integer> st, int r) {
		if (r == 0){
			printPick(st);
			
		}
		int smallest = st.isEmpty() ? 0 : st.lastElement() + 1;
		for (int next = smallest; next < n; next++) {
			st.push(next);
			pick(n, st, r - 1);//재귀
			st.pop();
		}
	}

	static void printPick(Stack<Integer> st) {
		for (int i : st)
			System.out.print(array[i] + " ");
		System.out.println();
		System.out.println();
	}
}
