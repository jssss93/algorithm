package algorithm.cjs_20201207;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class matchString2 {
//	url : https://programmers.co.kr/learn/courses/30/lessons/12973
	public static void main(String args[]){
		String s="abbcca";
		int answer=0;
		
		Stack<Character> stack = new Stack<>();
		
		for(char c : s.toCharArray()){
			if(!stack.isEmpty() &&stack.peek()==c){
				stack.pop();
			}else{
				stack.push(c);
			}
		}
		if(stack.isEmpty()){
			answer=1;
		}
		
	}
}
