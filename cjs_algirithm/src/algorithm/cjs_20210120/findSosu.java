package algorithm.cjs_20210120;

public class findSosu {

	//https://wooaoe.tistory.com/50
	//에라토스테네스의 체를 활용한 소수찾기 코드
	
	
	public int findSosu(int n) { 
		
		int answer = 0;
		boolean[] sosu =new boolean [n+1]; 
		
		for(int i=2; i<=n ; i++) {
			sosu[i]=true; //1. 2~n번째수를 true로 초기화 
		}
		
		 
		//2. 제곱근 구하기 
		int root=(int)Math.sqrt(n); 
		
		//3. 2~루트n까지 검사
		for(int i=2; i<=root; i++){
			if(sosu[i]==true){//i번째의 수가 소수일 때
				for(int j=i; i*j<=n; j++){//그 배수들을 다 false로 초기화(배수는 소수가 아니기 때문) 
					sosu[i*j]=false; 
				}
			} 
		} 
		for(int i =2; i<=n; i++) {
			if(sosu[i]==true){ 
				//소수의 개수 세기 
				answer++; 
			}
		} 
		System.out.println(answer);
		return answer; 
			
	}
	public static void main(String args[]){
		findSosu sol = new findSosu();
		sol.findSosu(10);
	}
}
