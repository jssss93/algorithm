package algorithm2.cjs_20211124;

public class 거리두기 {

	public static void main(String args[]) {
//		P는 응시자가 앉아있는 자리를 의미합니다.
//		O는 빈 테이블을 의미합니다.
//		X는 파티션을 의미합니다.
		
//		거리두기를 위하여 응시자들 끼리는 맨해튼 거리1가 2 이하로 앉지 말아 주세요.
		
//		맨해튼거리란 
//		두 테이블 T1, T2가 행렬 (r1, c1), (r2, c2)에 각각 위치하고 있다면,
//		T1, T2 사이의 맨해튼 거리는 |r1 - r2| + |c1 - c2| 입니다. ↩
		
		
		String[][] places= 
		
			{
					{
						"OOOOO",
						"OOOOO", 
						"OOOOO", 
						"OOOOO", 
						"OOPOP"},
//					{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, 
//					{"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
//					{
//						"PXXPX", 
//						"XXPXP", 
//						"OXXPX", 
//						"OXXXX", 
//						"PXOPX"
//					}
//					{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, 
//					{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
			};
						
//		{
//				{	
//					"POOOP", 
//					"PXPOX", 
//					"OPXPX", 
//					"OOXOX", 
//					"POXXP"
//				},
//				{	
//					"OPXPO", 
//					"OOOOO", 
//					"OOOOO", 
//					"OOXOX", 
//					"POXXP"
//				}
//		};
		거리두기.solution(places);
	}
    public static int[] solution(String[][] places) {
    	int[] answer = new int[places.length];
    	for(int i=0;i<places.length;i++) {
    		answer[i]=1;
    		boolean flag = true;
    		for(int j=0;j<places[0].length;j++) {
    			if(flag) {
    			String str = places[i][j];
    			for(int k=0;k<str.length();k++) {
    				//j,k 행렬이다,
    				System.out.println(str.charAt(k));
    				if(str.charAt(k)=='P') {
    					System.out.println("선택");
    					if(check(j,k,places[i])) {
    						System.out.println("패스");
    					}else {
    						System.out.println("노패스");
    						flag = false;
    						break;
    						//flag 처리
    					}
    					System.out.println("\n---------------");
    				}
    			}
    			System.out.println();
    			}else {
    				answer[i]=0;
    				break;
    			}
//    			System.out.println(places[i][j]);
    			
    		}
    		if(!flag) {
    			answer[i]=0;
    		}
    	}
       
    	System.out.println("answer ==> ");
        for(int a=0;a<answer.length;a++) {
        	System.out.println(answer[a]);
        }
        return answer;
    }
    
    public static boolean check(int j, int k,String[] place) {
    	System.out.println(j+",,"+k+"의비교");
    	//j,k 기준으로 좌우대각선 2칸 거리계산
    	
    	boolean answer 		= true;
    	boolean flag_right	= true;
    	boolean flag_line	= true;
    	boolean flag_bottom	= true;
    	
    	for(int a=1;a<3;a++) {
			
    		if(flag_right && (k+a) < 5) {
    			if(place[j].charAt(k+a)=='P') {
    				System.out.println("false_right "+j+",,"+(k+a));
    				answer = false;
    				break;
    			}
	    		if(place[j].charAt(k+a)=='X') {
	    			flag_right = false;
	    		}
    		}
    		
			if(flag_bottom && (j+a) < place[j].length()) {
				if(place[j+a].charAt(k)=='P') {
					System.out.println("false_bottom "+(j+a)+",,"+(k));
					answer = false;
    				break;
    			}
				
	    		if(place[j+a].charAt(k)=='X') {
	    			flag_bottom = false;
	    		}
			}
			
			
			
    	}
    	if((j+1) < place[j].length() && (k+1) <5) {
    		if(place[j+1].charAt(k+1)=='P') {
    			if(!(place[j+1].charAt(k)=='X' && place[j].charAt(k+1)=='X')) {
    				answer = false;
    			}
    		}
    	}
    	if((j+1) < place[j].length() && (k-1) >0) {
    		if(place[j+1].charAt(k-1)=='P') {
    			if(!(place[j+1].charAt(k)=='X' && place[j].charAt(k-1)=='X')) {
    				answer = false;
    			}
    		}
    	}
    	System.out.println(j+",,"+k+"의비교끝");
    	
    	return answer;
    }
	
	
			
}
