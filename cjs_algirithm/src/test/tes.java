package test;

public class tes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="特許公報(B2)";
		System.out.println(str.split("[(]")[1].split("[)]")[0]);
//		java.util.regex.PatternSyntaxException: Unclosed group near index 1
	}

}
