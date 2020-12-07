package algorithm.cjs_20201207;

public class tupleTest {

	public static void main(String args[]){
		tupleTest func =new tupleTest();
		func.Soultion("{{2},{2,1},{2,1,3},{2,1,3,4}}");
	}
	
	
	public int[] Soultion(String s){
		int[] answer = {};
		
		s = s.substring(2,s.length()-2);
		System.out.println(s);
		s= s.replace("},{","-");
		//2-  2,1  -2,1,3-  2,1,3,4
		String[] arr = s.split("-");
		
		
		System.out.println(arr[1]);
		int[] seq ={};
		
		for(int i=0;i<arr.length;i++){
			
		}
		
		
		
        return answer;
	}
}
