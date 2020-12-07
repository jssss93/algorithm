package algorithm.cjs_20201204;

public class FirstCharUpperCase {
	public static void main(String args[]){
		String s="3people unFollowed me";
		String answer="";
		String answer2="3people UnFollowed Me";
		for(int i=0;i<s.length();i++){
			System.out.println(s.charAt(i));
			if(i>0 ){
				if((s.charAt(i-1)+"").equals(" ")){
					answer+=(s.charAt(i)+"").toUpperCase();
				}else{
					answer+=(s.charAt(i)+"").toLowerCase();
				}
			}else{
				answer+=(s.charAt(i)+"").toUpperCase();
			}
		}
		System.out.println(answer);
		
		System.out.println(answer.equals(answer2));
	}
}
