package algorithm.cjs_20201207;

import java.util.ArrayList;
import java.util.List;

public class matchString {
	public static void main(String args[]){
		String s="cdcd";
		List list =new ArrayList<>();
		for(int i=0;i<s.length();i++){
			list.add(s.charAt(i));
		}
		
		list=parseMath(list);
		
		
		int answer = 0;
		if(list.size()==0){
			answer=0;
		}else{
			answer=1;
			
		}
        System.out.println("answer :: "+answer);
	}
	
	public static List parseMath(List list){
		for(int i=0;i<list.size()-1;i++){
			if((list.get(i)).equals(list.get(i+1))){
				list.remove(i);
				list.remove(i);
				parseMath(list);
				break;
			}
		}
		return list;
	}
}
