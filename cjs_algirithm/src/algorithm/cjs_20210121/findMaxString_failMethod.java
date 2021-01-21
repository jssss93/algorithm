package algorithm.cjs_20210121;

import java.util.ArrayList;
import java.util.List;

//0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
//
//예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
//
//0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
//
//제한 사항
//numbers의 길이는 1 이상 100,000 이하입니다.
//numbers의 원소는 0 이상 1,000 이하입니다.
//정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
public class findMaxString_failMethod {
	public static void main(String args[]){
		findMaxString_failMethod sol = new findMaxString_failMethod();
		int[] numbers = new int[5];
		for(int i=0;i<5;i++){
			System.out.println(i);
			numbers[i]=i;
		}
		sol.solution(numbers);
		
	}
	public String solution(int[] numbers) {
        String answer = "";
        int[] answerArr = new int[numbers.length];
        boolean[] check = new boolean[numbers.length]; 
        List<Long> list = new ArrayList<Long>();
        permutation(numbers.length, numbers.length, numbers, check, answerArr, 0,list);
        System.out.println(list);
        if(list.size()==0){
        	return "0";
        }
        long max = list.get(0);
        for(int i=1;i<list.size();i++){
        	if(max<list.get(i)){
        		max = list.get(i);
        	}
        }
        answer = Long.toString(max);
        System.out.println(answer);
        return answer;
    }
    public void permutation(int n, int r, int[] input, boolean[] check, int[] answer, int depth,List list) {
        if(depth == r) {
        	if(!((answer[0]+"").charAt(0)+"").equals("0")){
        		
	        	String str = "";
	        	for(int i=0;i<answer.length;i++){
	        		str += answer[i];
	        	}
	        	System.out.println(str);
	        	list.add(Long.parseLong(str));
        	}
            return;
        }

        for (int i = 0; i < n; i++){
            if (!check[i]) {
                check[i] = true;                    // 중복 체크
                answer[depth] = input[i];
                permutation(n, r, input, check, answer, depth+1,list);
                check[i] = false;
            }
        }
    }
	
}
