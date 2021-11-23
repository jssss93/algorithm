package algorithm.cjs_20201201;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class bracketCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "()()";
        boolean answer = true;

        Stack<String> stack = new Stack<String>();
		for(int i=0;i<s.length();i++){
			
			if((s.charAt(i)+"").equals("(")){
				stack.push("(");
			}else if((s.charAt(i)+"").equals(")")){
				stack.pop();
			}
			
		}
        if (stack.size()!=0) {
       
            answer=false;
        }
        System.out.println(answer);
	}

}
