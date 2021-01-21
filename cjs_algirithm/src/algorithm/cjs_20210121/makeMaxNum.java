package algorithm.cjs_20210121;

//어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
//
//예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.
//
//문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다. number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.
//
//제한 조건
//number는 1자리 이상, 1,000,000자리 이하인 숫자입니다.
//k는 1 이상 number의 자릿수 미만인 자연수입니다.
//입출력 예
//number	k	return
//1924	2	94
//1231234	3	3234
//4177252841	4	775841
public class makeMaxNum {
	public static void main(String args[]){
		makeMaxNum sol = new makeMaxNum();
		String number = "123123";
		int k = 2;
		System.out.println("answer :: "+sol.solution(number, k));
	}
	public String solution(String number, int k) {
        String answer = number;
//        System.out.println(deleteIndex(number, 1));
        int cnt=0;
        int max = 0;
        
        
        	
        for(int i=0;i<answer.length()-1;i++){
        	while(cnt<=k ){
			
    			System.out.println(cnt+"@@");
        		for(int j=i+1;j<answer.length();j++){
        			int dupCnt=0;
        			System.out.println(answer.charAt(i)+""+"|||||"+answer.charAt(j)+"");
	        		if((answer.charAt(i)+"").compareTo(answer.charAt(j)+"")<0){
	        			System.out.println("여기"+i+",,"+j);
	        			answer = deleteIndex(answer, i);
	        			i--;j--;
	        			System.out.println(answer);
	        			System.out.println();
	        			cnt++;
	        			break;
	        		}else if((answer.charAt(i)+"").compareTo(answer.charAt(j)+"")==0){
	        			dupCnt++;
	        		}
        		}
        	}
        }
        System.out.println(number);
        for(int i=0;i<number.length();i++){
//        	number.
//        	System.out.println(number.charAt(i));
        }
        
        return answer;
    }
	
	public String deleteIndex(String number,int idx){
		return number.substring(0,idx)+number.substring(idx+1,number.length());
	}
    
}
