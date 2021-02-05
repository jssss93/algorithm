package algorithm.cjs_20210204;

import java.util.Stack;

//괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다. 예를 들어
//
//()() 또는 (())() 는 올바른 괄호입니다.
//)()( 또는 (()( 는 올바르지 않은 괄호입니다.
//'(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때, 문자열 s가 올바른 괄호이면 true를 return 하고, 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.
//
//제한사항
//문자열 s의 길이 : 100,000 이하의 자연수
//문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.
//입출력 예
//s	answer
//()()	true
//(())()	true
//)()(	false
//(()(	false
//입출력 예 설명
//입출력 예 #1,2,3,4
//문제의 예시와 같습니다.
public class rightBracket {
	public static void main(String args[]){
		String s = "((()))";
		rightBracket sol = new rightBracket();
		System.out.println("answer==>"+sol.solution(s));
	}
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack =new Stack<>();
        for(int i=0;i<s.length();i++){
        	if(stack.size()>0){
	        	if(s.charAt(i)==')'){
	        		stack.pop();
	        	}else{
	        		stack.push('(');
	        	}
        	}else{
        		stack.push(s.charAt(i));
        	}
        }
        
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        if(stack.size()!=0){
        	answer = false;
        }
        return answer;
    }
}
