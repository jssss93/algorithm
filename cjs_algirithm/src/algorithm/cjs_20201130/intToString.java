package algorithm.cjs_20201130;

public class intToString {
	public static void main(String args[]) {

		int answer = 0;
		String str = "987";
		for (int i = 0; i < str.length(); i++) {
			answer += Integer.parseInt(str.charAt(i) + "");
			// System.out.print(str.charAt(i) + "-");
		}

		// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
		// System.out.println("Hello Java");
		System.out.println(answer);
	}
}
