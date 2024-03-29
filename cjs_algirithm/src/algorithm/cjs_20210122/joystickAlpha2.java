package algorithm.cjs_20210122;

public class joystickAlpha2 {
	public static void main(String args[]){
		joystickAlpha2 sol = new joystickAlpha2();
		sol.solution("ABAAABB");
		//JEROEN 56
		//ABAAAAAAAAABB 7
	}
	
	//abcdefghijklm
	//
	//nopqrstuvwxyz
	//65~90
	
    public int solution(String name) {
        int answer = 0;
        int exp = name.length() - 1;
        for(int i = 0; i < name.length(); i++){
            char c = name.charAt(i);
            answer += ('Z'- c + 1) > c - 'A' ? c - 'A' : ('Z' - c + 1);
            if(c == 'A'){
                int nextIdx = i+1;
                int countA = 0;
                while (nextIdx < name.length() && name.charAt(nextIdx) == 'A'){
                    countA ++;
                    nextIdx++;
                }
                int tmp = (i-1)*2 + (name.length() -1 -i - countA) ;
                if(exp > tmp) exp = tmp;
            }
        }
        System.out.println(answer);
        answer += exp;
        System.out.println(answer);
        return answer;
    }
	
	
}
