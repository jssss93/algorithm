package algorithm.cjs_20210119;
//한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
//
//각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.
//
//제한사항
//numbers는 길이 1 이상 7 이하인 문자열입니다.
//numbers는 0~9까지 숫자만으로 이루어져 있습니다.
//013은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
//입출력 예
//numbers	return
//17	3
//011	2
//입출력 예 설명
//예제 #1
//[1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.
//
//예제 #2
//[0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.
//
//11과 011은 같은 숫자로 취급합니다.

import java.util.ArrayList;
import java.util.List;

public class findDecimal_fail {
	public static void main(String args[]){
		findDecimal_fail sol = new findDecimal_fail();
		String numbers="017";
		sol.solution(numbers);
	}
	
	public int solution(String numbers) {
        int answer = 0;
        
        String[] input = new String[numbers.length()];
        String[] output = new String[numbers.length()];
        boolean[] check = new boolean[numbers.length()];
        List outputList = new ArrayList<>();
        
        for(int i=0;i<input.length;i++){
        	input[i]= numbers.charAt(i)+"";
        }
        permutation(numbers.length(), numbers.length(), input, check, output, 0,outputList);
        System.out.println(outputList);
        answer = sosuCounting(outputList,answer);
        System.out.println(answer);
        return answer;
    }
	
	public int findSosu(int n) { 
		int answer = 0;
		boolean[] sosu =new boolean [n+1]; 
		for(int i=2; i<=n ; i++) 
			sosu[i]=true; 
		//2~n번째수를 true로 초기화 
		//제곱근 구하기 
		int root=(int)Math.sqrt(n); 
		
		for(int i=2; i<=root; i++){ 
			//2~루트n까지 검사 
			if(sosu[i]==true){ 
				//i번째의 수가 소수일 때 
				for(int j=i; i*j<=n; j++){ 
					//그 배수들을 다 false로 초기화(배수는 소수가 아니기 때문) 
					sosu[i*j]=false; 
				}
			} 
		} 
		for(int i =2; i<=n; i++) {
			if(sosu[i]==true){ 
				//소수의 개수 세기 
				answer++; 
			}
		} 
		return answer; 
			
	}

		
	public int sosuCounting(List outputList,int answer){
		for(int i=0;i<outputList.size();i++){
			if(!((outputList.get(i)+"").charAt(0)+"").equals("0")){
				
				answer++;
			}
		}
		return answer;
	}
	
    public void permutation(int n, int r, String[] input, boolean[] check, String[] answer, int depth,List outputList) {
        if(depth == r) {
        	String str = "";
        	for(int i=0;i<n;i++){
        		str+=answer[i];
        	}
        	outputList.add(str);
        }

        for (int i = 0; i < n; i++){
            if (!check[i]) {
                check[i] = true;                    // 중복 체크
                answer[depth] = input[i];
                permutation(n, r, input, check, answer, depth+1,outputList);
                check[i] = false;
            }
        }
        
    }
	
	
}
