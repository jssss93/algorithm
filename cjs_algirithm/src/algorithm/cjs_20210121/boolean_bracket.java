package algorithm.cjs_20210121;

import java.util.Stack;

public class boolean_bracket {
	public static void main(String args[]){
	
		String p = "(()))";
		boolean_bracket sol = new boolean_bracket();
		sol.solution(p);
	}
	public String solution(String p) {
        String answer = "";
        Stack<String> stack = new Stack<String>();
        stack.push(p.charAt(0)+"");
        for(int i=1;i<p.length();i++){
        	
        	if(stack.size()>0&&(p.charAt(i)+"").equals(")")){
        		stack.pop();
        	}else{
        		stack.push("(");
        	}
        }
        if(stack.size()==0){
        	answer="true";
        }else{
        	answer="false";
        }
        System.out.println(answer);
        return answer;
    }
	
}
