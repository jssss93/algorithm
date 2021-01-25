package algorithm.cjs_20210122;

public class joystickAlpha {
	public static void main(String args[]){
		joystickAlpha sol = new joystickAlpha();
		
		sol.solution("ABAAAAAAAAABB");
		//JEROEN 56
		//ABAAAAAAAAABB 7
	}
	
	//abcdefghijklm
	//
	//nopqrstuvwxyz
	//65~90
	
	//1. 제일 길게 연속되는 A를 제거하고 계산.
	//2. 
	public int solution(String name) {
        int answer = 0;
        int right = 0; 
        int back = 0; 
        int direction = 0;
        int while_cnt=0;
        
        	System.out.println("while"+while_cnt);
	        for(int i=1;i<name.length();i++){
	        	if(name.charAt(i)=='A'){
	        		right = i;
	        		direction--;
	        	}else{
	        		break;
	        	}
	        }
	        for(int i=name.length()-1;i>0;i--){
	        	if(name.charAt(i)=='A'){
	        		back = i;
	        		direction++;
	        	}else{
	        		break;
	        	}
	        }
//	        while_cnt++;
//        }
        if(right==0 && back==0){
//        	answer =
//        	answer = ;
        }else{
	        if(direction>0){
	        	answer  = -name.length()+back;
	        	//정방향이면
	        	//8인데 6만 이동하면됨.
	        	
	        }else{
	        	answer  = -right;
	        }
        }
        System.out.println("right:: "+right);
        System.out.println("back:: "+back);
        System.out.println("direction:: "+direction);
        System.out.println("방향계산후  :: "+answer);
        System.out.println();
        answer += minDistance(name.charAt(0));
        System.out.println("첫번째 문자 시간 :: "+answer);
        
        for(int i=1;i<name.length();i++){
        	System.out.println(name.charAt(i)+"까지 시간:: "+(minDistance(name.charAt(i))+1));
        	answer +=(minDistance(name.charAt(i))+1);
        }
        
        
        
        
        
        System.out.println("answer :: "+answer);
        return answer;
    }
	
	public int minDistance(Character c){
		int inputLoc = (int)c;
		
		if((int)c<=77){
			return inputLoc-65;
		}else{
			return 91-inputLoc;
		}
		
	}
	
	
}
