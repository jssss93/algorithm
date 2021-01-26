package algorithm.cjs_20210126;

public class snail {
	public static void main(String args[]){
		snail sol = new snail();
		int n=6;
		sol.solutionReal(n);
	}
	
	//half Sanil
	public int[] solutionReal(int n) {
		int[][] arr = new int[n][n];
		
		int first = -1;
		int second = 0;
		int opt = 1;
		int print =1;
		
		while(n>0){
		for(int i=0;i<n;i++){
			first = first +opt;
			arr[first][second] = print++;
		}
		n--;
		for(int i=0;i<n;i++){
			second = second+opt;
			arr[first][second] = print++;
		}
		n--;
		for(int i=0;i<n;i++){
			first = first-1;
			second = second-1;
			arr[first][second] = print++;
		}
		n--;
		}
		int[] answer =new int[print-1];
		int cnt=0;
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				if(arr[i][j]!=0){
					answer[cnt++] = arr[i][j];
				}
			}
		}

		for(int i=0;i<answer.length;i++){
		}
		
		return answer;
	}
		
	//full Sanil
	public int[] solution(int n) {
		int[][] arr = new int[n][n];
		
		
		int first = 0;
		int second = -1;
		int opt = 1;
		int print = 1;

		while(n>0){
		for(int i=0;i<n;i++){
			second = second+opt;
			arr[first][second] =print++; 
		}
		n--;
		
		for(int i=0;i<n;i++){
			first = first+opt;
			arr[first][second] =print++; 
		}
		opt=opt*-1;
		}
		
		
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[0].length;j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
		
		int[] answer =new int[5];
		return answer;
	}
}
