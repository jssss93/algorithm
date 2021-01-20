package algorithm.cjs_20210118;

public class EmptySnail {
	public static void main(String args[]){
		
		int print=5;
		int[][] snail = new int[print][print];
		
		int first = 0;
		int second = -1;
		int cnt = 1;
		int opt=1;
		
		for(int i=0;i<print;i++){
			for(int j=0;j<print;j++){
				second = second+opt;
				System.out.println(first+"//"+second);
				snail[first][second]=cnt++;
				
			}
			print--;
			
			System.out.println();
			for(int k=0;k<print;k++){
				first = first+opt;
				System.out.println(first+"//"+second);
				snail[first][second] = cnt++;
			}
			System.out.println();
			
			opt = opt*-1;
		}
		
		
        // 출력
        for(int i=0; i<snail.length; i++) {
            for(int j=0; j<snail[i].length; j++) {
        	    System.out.printf("%2d ", snail[i][j]);
            }
            System.out.println();
        }
		
		
	}
}
