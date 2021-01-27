package algorithm.cjs_20210127;

import java.util.ArrayList;
import java.util.List;

public class stackDev {
	public static void main(String args[]){
		stackDev sol =  new stackDev();
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1,1,1,1,1,1};
		
		sol.solution(progresses, speeds);
		
		
	}
	
	public int[] solution(int[] progresses, int[] speeds) {
		
		int[] success = new int[progresses.length];
		boolean successFlag = false;
		int time = 1;
		while(!successFlag){
			successFlag =true;
			for(int i=0;i<progresses.length;i++){
				
				if(progresses[i]<100){
					progresses[i]=progresses[i]+speeds[i];
					if(progresses[i]>=100){
						success[i]=time;
					}
					
				}
				
				for(int j=0;j<success.length;j++){
					if(progresses[j]<100){
						successFlag = false;
						break;
					}
				}
			}
			time++;
		}
		
		List answerList = new ArrayList();
		
		
		
		int cnt = 1;
		int max = success[0];
		for(int i=1;i<success.length;i++){
			if(max<success[i]){
				answerList.add(cnt);
				max = success[i];
				cnt=0;
				i--;
			}else{
				cnt++;
				if(i==success.length-1){
					answerList.add(cnt);
				}
			}
			
		}
		int[] answer =new int[answerList.size()];
		
		for(int i=0;i<answerList.size();i++){
			answer[i] = (int) answerList.get(i);
		}
		
		
		
		
		return answer;
	}
}
