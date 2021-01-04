package algorithm.cjs_20201222;

public class commonFactor {
	public static void main(String args[]){
		commonFactor sol = new commonFactor();
//		for(int i=1;i<10;i++){
//			for(int j=1;j<10;j++){
//				System.out.println(i+","+j+"==>");
//				sol.reatestCommon(i, j);
//			}
//		}
		
		System.out.println("최대공약수 ==> "+sol.reatestCommon(74,1478));
		System.out.println("최소공배수 ==> "+sol.LeastCommon(36,1458,2));
		System.out.println();
		System.out.println(sol.solution(36,1458)[0]);
		System.out.println(sol.solution(36,1458)[1]);
	}
	//최대공약수
	public int reatestCommon(int a,int b){
		int answer = 0;
		int temp=0;
		if(a<b){
			temp=b; 
			b=a;
			a=temp;
		}
		a=a%b;
		if(a==0){
			answer=b;
			return answer;
		}
		answer=reatestCommon(a, b);
		
		return answer;
	}
	//최소공배수
	public int LeastCommon(int a,int b,int cnt){
		int answer = 0;
		
		for(int index=1;index<cnt;index++){
			for(int index2=1;index2<cnt;index2++){
				if(a*index==b*index2){
					answer=a*index;
					return a*index;
				}
			}
		}
		
		if(answer==0){
			answer = LeastCommon(a,b,cnt+1);
		}
		
		return answer;
	}
	
	public int[] solution(int n, int m) {
		int[] answer = new int[2];
		// 두수의 크기 정렬
		int big, small;
		if (n > m) {
			big = n;
			small = m;
		} else {
			big = m;
			small = n;
		}
		answer[0] = gcd(big, small);
		answer[1] = big * small / answer[0];

		return answer;
	}

	public int gcd(int a, int b) {
		if (a % b == 0)
			return b;
		return gcd(b, a % b);
	}
	  
}
