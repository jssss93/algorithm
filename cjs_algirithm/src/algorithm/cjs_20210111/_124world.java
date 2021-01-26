package algorithm.cjs_20210111;

//124 나라가 있습니다. 124 나라에서는 10진법이 아닌 다음과 같은 자신들만의 규칙으로 수를 표현합니다.
//
//124 나라에는 자연수만 존재합니다.
//124 나라에는 모든 수를 표현할 때 1, 2, 4만 사용합니다.
//예를 들어서 124 나라에서 사용하는 숫자는 다음과 같이 변환됩니다.
//
//10진법	124 나라	10진법	124 나라
//1	1	6	14
//2	2	7	21
//3	4	8	22
//4	11	9	24
//5	12	10	41
//자연수 n이 매개변수로 주어질 때, n을 124 나라에서 사용하는 숫자로 바꾼 값을 return 하도록 solution 함수를 완성해 주세요.
//
//제한사항
//n은 500,000,000이하의 자연수 입니다.
public class _124world {
	public static void main(String args[]){
		_124world sol = new _124world();
		sol.solution(1);
		sol.solution(2);
		sol.solution(3);
		sol.solution(4);
		sol.solution(5);
		sol.solution(6);
		sol.solution(7);
		sol.solution(8);
		sol.solution(9);
		sol.solution(10);
		sol.solution(11);
		sol.solution(12);
		sol.solution(13);
		sol.solution(14);
		sol.solution(15);
	}
    //3진법으로 바꾸는거.
    public String solution(int n) {
        
        String answer = "";
        int reminder;

        while(n>0){
            reminder = n%3;
            n = n/3;

            if(reminder == 0){
                n -= 1;
                reminder = 4;
            }

            answer = reminder + answer;
        }
        	System.out.println(answer);
        return answer;
    }
    
	
}
