package algorithm.cjs_20210126;

public class findSosu {
	public static void main(String args[]){
		findSosu sol = new findSosu();
		String numbers= "017";
		sol.solution(numbers);
	}
	public int solution(String numbers) {
        int answer = 0;
        String[] input = new String[numbers.length()];
        for(int i=0;i<numbers.length();i++){
        	System.out.println(i+"||||"+numbers.charAt(i));
        	input[i] = numbers.charAt(i)+"";
        }
        
        // 순열
        int n = numbers.length();                                  // 3가지 숫자 중
        int r = 1;                                  // 2개를 뽑을 경우
                      // 주어진 3가지 숫자  
        String[] output = new String[r];                  // 정답을 담을 배열        
        boolean[] check = new boolean[n];           // 해당 숫자를 방문했는지 체크
        for(int i=1;i<numbers.length();i++){
        	permutation(n, i, input, check, output, 0);
        	System.out.println();
        }
        return answer;
    }
	
    public static void permutation(int n, int r, String[] input, boolean[] check, String[] answer, int depth) {
        if(depth == r) {
            print(answer);
            return;
        }

        for (int i = 0; i < n; i++){
            if (!check[i]) {
                check[i] = true;                    // 중복 체크
                answer[depth] = input[i];
                permutation(n, r, input, check, answer, depth+1);
                check[i] = false;
            }
        }
    }
    public static void print(String[] answer) {
        for(String ans : answer) {
            System.out.print(ans + " ");
        }
        System.out.println();
    }
    
}
