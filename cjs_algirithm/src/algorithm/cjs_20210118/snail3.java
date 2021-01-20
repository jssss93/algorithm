package algorithm.cjs_20210118;

public class snail3 {
	public static void main(String args[]){
		
		int n=6;
		
		snail3 sol = new snail3();
		sol.solution(n);
//		System.out.println(sol.solution(6));
		
		
	}
	public int[] solution(int n) {
		int[][] snail = new int[n][n];
		
		int first = -1;
		int second = 0;
		int opt = 1;
		int cnt = 1 ;
		
		for(int i=n;i>0;i--){
			for(int k=0;k<n;k++){
				first = first+opt;
//				System.out.println(first+",,"+second);
				snail[first][second] = cnt++;
			}
			n--;
//			System.out.println();
			for(int j=0;j<n;j++){
				
				second = second+opt;
//				System.out.println(first+",,"+second);
				snail[first][second] = cnt++;
			}
			n--;
			for(int h=0;h<n;h++){
				first = first-1;
				second=second-1;
				snail[first][second]=cnt++;
			}
			n--;
			
			
//			opt=opt*-1;
			
			
			
		}
        int[] answer = new int[cnt];
//        System.out.println();
        // 출력
        int arrCnt = 0;
        for(int i=0; i<snail.length; i++) {
            for(int j=0; j<snail[i].length; j++) {
            	
//        	    System.out.printf("%2d ", snail[i][j]);
            }
//            System.out.println();
        }
        for(int i=0;i<answer.length;i++){
        	System.out.println(answer[i]);
        }
        
        return answer;
    }
	
}
