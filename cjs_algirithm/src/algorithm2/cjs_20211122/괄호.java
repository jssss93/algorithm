package algorithm2.cjs_20211122;

import java.util.Stack;

public class 괄호 {
	public static void main(String args[]) {
		String p = "()))((()";
//		String p = ")(";
//		String p = "(()())()";
		//)(
		//(()())()
		
		괄호.solution(p);
	}
	public static String solution(String p) {
        String answer = "";
        
        if(right(p)) {
        	answer = p;
        }else {
    		answer = retry(p,answer);
        }
        System.out.println("answer ==>"+answer);
        //1. 올바른 괄호 판단 및 분리
        
        return answer;
    }
	public static String retry(String p,String answer) {
		System.out.println("중간p"+p);
		System.out.println("중간a"+answer);
    	for(int i=2;i<p.length();i=i+2) {
    		
    		String u = p.substring(0,i);
    		String v = p.substring(i,p.length());
    		System.out.println(u);
    		System.out.println(v);
    		System.out.println("###");
    		if(plat(u)) {
    			System.out.println("균형잡힌 문자열");
    			if(right(u)) {
    				System.out.println("올바른 문자열");
    				answer += u+ retry(v, answer);
    			}else {
    				System.out.println("올바르지않은 문자열");
    				answer += "("+ retry(v, answer) +")"+reserve(u);
    			}
    			return answer;
    		}
    		
    		
    	}
		
		return answer;
	}
	
	public static String reserve(String str) {
		String substr = str.substring(1,str.length()-1);
		substr = substr.replaceAll("\\(", "0");
		substr = substr.replaceAll("\\)", "(");
		substr = substr.replaceAll("0", ")");
		return substr;
	}
	public static boolean plat(String str) {
		int cnt=0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)==')') {
				cnt++;
			}else {
				cnt--;
			}
		}
		if(cnt==0) {
			return true;
		}else {
			return false;	
		}
		
		
	}
	public static boolean right(String str) {
		if(str.charAt(0)==')') {
			return false;
		}
		
		Stack<Character> stack = new Stack<>();
		stack.push(str.charAt(0));
		for(int i=1;i<str.length();i++) {
			if(str.charAt(i)==')') {
				if(stack.size()==0) {
					return false;
				}
				stack.pop();
			}else {
				stack.push(str.charAt(i));
			}
		}
		if(stack.size()==0) {
			return true;
		}else {
			return false;
		}
	}
}
