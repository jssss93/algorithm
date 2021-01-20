package algorithm.cjs_20201222;

import java.math.BigInteger;

public class gcdTest{
	
	//	유클리드 호제법 참조.
	//	최대공약수 최소공배수 구하는 방법 기억.
	//	https://greenapple16.tistory.com/89
	
	private static int getGcd(int a, int b){
		BigInteger num1 = BigInteger.valueOf(a);
		BigInteger num2 = BigInteger.valueOf(b);
		BigInteger gcd = num1.gcd(num2);
		return gcd.intValue();
	}
	
	public static int solution(int w, int h){
		int answer;
		int gcd = getGcd(w,h);
		
		if(gcd == 1){
			answer = w + h - 1;
		}else {
			answer = w + h - gcd;
		}
		
		return w*h - answer;
	}
	public static void main(String[] args){
		System.out.println(solution(2,4));
	}
}
