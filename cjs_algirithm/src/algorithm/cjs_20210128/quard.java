package algorithm.cjs_20210128;

public class quard {
	
	public static void main(String args[]){
//		int[][] arr = {
//				{1,1,0,0},
//				{1,0,0,0},
//				{1,0,1,1},
//				{1,1,1,1}
//				};
		int[][] arr = {
			{1,1,1,1,1,1,1,1},
			{0,1,1,1,1,1,1,1},
			{0,0,0,0,1,1,1,1},
			{0,1,0,0,1,1,1,1},
			{0,0,0,0,0,0,1,1},
			{0,0,0,0,0,0,0,1},
			{0,0,0,0,1,0,0,1},
			{0,0,0,0,1,1,1,1}
		};
		quard sol =new quard();
		sol.solution(arr);
	}
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        
        int zeroCnt = 0;
        int oneCnt = 0;
        
        for(int i=0;i<arr.length;i++){
        	for(int j=0;j<arr.length;j++){
        		if(arr[i][j] == 0){
        			zeroCnt++;
        		}
        	}
        }
        oneCnt = arr.length*arr.length-zeroCnt;
        System.out.println("zeroCnt :: "+zeroCnt);
        System.out.println("oneCnt :: "+oneCnt);
        answer[0] = zeroCnt;
        answer[1] = oneCnt;
        
        
        
        
        System.out.println();
        System.out.println("answer=>");
        System.out.println(answer[0]);
        System.out.println(answer[1]);
        return answer;
    }
    
    public void search(int row,int start1,int start2){
    	
    }
    
    public void deteleCnt(int idx,int[] answer,int row){
    	answer[idx] = answer[idx]-row*row+1;
    }
}
