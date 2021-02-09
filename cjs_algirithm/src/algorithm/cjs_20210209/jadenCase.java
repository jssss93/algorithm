package algorithm.cjs_20210209;
//JadenCase란 모든 단어의 첫 문자가 대문자이고,그 외의 알파벳은 소문자인 문자열입니다. 
//문자열 s가 주어졌을 때, s를 JadenCase로 바꾼 문자열을 리턴하는 함수, solution을 완성해주세요.
//
//제한 조건
//s는 길이 1 이상인 문자열입니다.
//s는 알파벳과 공백문자(" ")로 이루어져 있습니다.
//첫 문자가 영문이 아닐때에는 이어지는 영문은 소문자로 씁니다. ( 첫번째 입출력 예 참고 )
//입출력 예
//s	return
//3people unFollowed me          	3people Unfollowed Me
//for the last week	                For The Last Week
public class jadenCase {
	public static void main(String args[]){
		String s = "   for the last week  ";
		jadenCase sol =new jadenCase();
		System.out.println("answer==>"+sol.solution(s)+"");
				
	}
	
    public String solution(String s) {
        String answer = "";
        String[] arr = s.split(" ");
        String arrSub = "";
        
        for(int i=0;i<arr.length;i++){
        	System.out.println(arr[i]);
        	if(!arr[i].equals("")){
	        	arrSub="";
	        	for(int j=0;j<arr[i].length();j++){
		        	if(j==0){
		        		arrSub = (arr[i].charAt(0)+"").toUpperCase();
		        	}else{
		        		arrSub += (arr[i].charAt(j)+"").toLowerCase();
		        	}
	        	}
	        	answer+= arrSub+" ";
        	}else{
        		System.out.println("ok");
        	}
        }
        System.out.println(answer);
        return answer.substring(0,answer.length()-1);
    }
}
