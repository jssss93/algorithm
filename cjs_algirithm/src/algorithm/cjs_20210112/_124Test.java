package algorithm.cjs_20210112;

public class _124Test {
	public static void main(String args[]){
		
		_124Test sol = new _124Test();
		for(int i=1;i<11;i++){
			sol.solution(i);
		}
	}
	public String solution(int n) {
		System.out.print(n+"==>");
		String answer ="";
		int rem=0;
		
		while(n>0){
			rem = n%3;
			n=n/3;
			if(rem==0){
				rem=4;
				n=n-1;
			}
			answer = rem+answer;
		}
		System.out.println(answer);
		return answer;
	}
}
