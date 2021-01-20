package algorithm.cjs_20210104;

public class maxSumArr {
	public static void main(String args[]){
		int [][] land ={
					{1,2,3,5},
					{5,6,7,100},
					{4,3,2,1}
					};
        int answer = 0;
        
        
        for(int i = 1 ; i < land.length ; i++) {
        	land[i][0] += Math.max(land[i-1][1], Math.max(land[i-1][2], land[i-1][3]));
        	land[i][1] += Math.max(land[i-1][0], Math.max(land[i-1][2], land[i-1][3]));
        	land[i][2] += Math.max(land[i-1][0], Math.max(land[i-1][1], land[i-1][3]));
        	land[i][3] += Math.max(land[i-1][0], Math.max(land[i-1][1], land[i-1][2]));
        }
        System.out.println(Math.max(land[land.length-1][0], Math.max(land[land.length-1][1], 
        		Math.max(land[land.length-1][2], land[land.length-1][3]))));
	}
}
