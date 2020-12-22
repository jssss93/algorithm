package algorithm.cjs_20201217;

import java.util.Stack;

public class duple_check {
	public static void main(String args[]){
		duple_check sol = new duple_check();
		sol.solution("(())");
	}
	
	boolean solution(String s) {
        boolean answer = true;
        
        Stack stack = new Stack();
        
        for(int i=0;i<s.length();i++){
        	if((s.charAt(i)+"").equals(")")){
        		stack.pop();
        	}else if((s.charAt(i)+"").equals("(")){
        		stack.push("(");
        	}
        }
        if(stack.size()>0){
        	answer=false;
        }else{
        	answer=true;
        }
        System.out.println(answer);
        return answer;
    }
}
