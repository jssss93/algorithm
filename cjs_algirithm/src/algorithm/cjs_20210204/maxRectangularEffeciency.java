package algorithm.cjs_20210204;

public class maxRectangularEffeciency {
	public static void main(String args[]){
		int[][] board = {
//							{1,1,1,1},
//							{1,0,1,1},
//							{1,1,1,1},
//							{0,0,1,0}
//				{1,1,1},
//				{0,1,0}
				{0,1,0}
						};
		
		maxRectangularEffeciency sol = new maxRectangularEffeciency();
		System.out.println("answer ==> "+sol.solution(board));
	}
	public int solution(int [][]board)
    {
		int answer = 0;
//		int[][] arr = new int[board.length][board[0].length];
		int first = 0;
		int second = 0;
		int third = 0;
		int min = 0;
		
		if(board.length==1 || board[0].length==1){
			for(int i=0;i<board.length;i++){
				for(int j=0;j<board[0].length;j++){
					if(board[i][j]==1){
						return 1;
					}
				}
			}
			
		}else{
		
			for(int i=0;i<board.length-1;i++){
				for(int j=0;j<board[0].length-1;j++){
					first = board[i][j];
					second = board[i][j+1];
					third = board[i+1][j];
					if(board[i+1][j+1]==1){
						min = first < second ? first : second;
						min = min < third ? min : third;
						
						board[i+1][j+1]=min+1;
						
					}
					
//					System.out.println(first+"  "+second);
//					System.out.println(third+"  "+board[i+1][j+1]);
					
					
					
					answer = answer < board[i+1][j+1] ? board[i+1][j+1] : answer;
//					System.out.println();
					
				}
//				System.out.println();
			}
		}
		
		return answer*answer;
		
    }
}
