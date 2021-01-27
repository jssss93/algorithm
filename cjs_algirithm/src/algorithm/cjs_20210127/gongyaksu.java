package algorithm.cjs_20210127;

import java.util.ArrayList;
import java.util.List;

public class gongyaksu {
	public static void main(String args[]){
		gongyaksu sol = new gongyaksu();
		sol.solution(50, 22);
//		sol.solution(8, 1);
	}
	public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int sum = brown+yellow;
		List<Integer> nList = new ArrayList();
		int row =0;
		int col = 0; 
		
		for(int i=1;i<=sum;i++){
			if(sum%i==0){
				nList.add(i);
			}
		}

		for(int i=0;i<nList.size()/2+1;i++){
			if(nList.get(i)>=3){
				
				col = nList.get(i);
				row = sum/col;
				if(brown == row*2+col*2-4){
					answer[0] = row;
					answer[1] = col;
					break;
				}
				
			}
			
		}
		return answer;
    }
}
