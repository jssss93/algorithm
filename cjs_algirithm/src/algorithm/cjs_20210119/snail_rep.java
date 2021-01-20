package algorithm.cjs_20210119;

public class snail_rep {
	public static void main(String args[]){
		snail_rep sol = new snail_rep();
		int n=5;
		sol.solution(n);
	}
	
	
	
	public void solution(int n) {
		int[][] snail = new int[n][n];
		int first = -1;
		int second = 0;
		int opt = 1;
		int cnt = 1;
		int num = n;
		
		for(int i=0;i<5;i++){
			for(int k=0;k<num;k++){
				first = first +opt;
				System.out.println(first+",,"+second);
				snail[first][second] = cnt++;	
			}
			
			num--;
			for(int j=0;j<num;j++){
				second = second + opt;
				snail[first][second] = cnt++;
			}
			num--;
			for(int x=0;x<num;x++){
				first= first-1;
				second= second-1;
				System.out.println(first+",,"+second);
				snail[first][second] = cnt++;
			}
			num--;
			
		}
		
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.printf("%2d ",snail[i][j]);
			}
			System.out.println();
		}
		
	}
}
