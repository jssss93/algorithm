package algorithm.cjs_20201110;

import java.util.Scanner;

public class twoToTen {
//	부호를 포함하는 8비트 2진수 값을 10진수로 변환하는 알고리즘을 작성하라
//	<조건>
//	8비트 2진수 값의 최상위 비트는 부호비트이다.
//	부호비트가 0일 경우 양수, 1일 경우 음수이다.(단, 음수의 경우 2의 보수로 표현)
//	출처: https://cbts.tistory.com/135?category=649617 [IT일기장]
	
	public static void main(String args[]){
		Boolean flag = true;
		String inputVal = "";
		int parseRet=0; 
		loop:while(flag){
			parseRet=0; 
			System.out.println("8비트 2진수를 입력해주세요");
			
			Scanner sc = new Scanner(System.in);
			inputVal = sc.nextLine();
			
			if(inputVal.length()!=8  ){
				System.out.println("8비트 2진수를 올바르게 입력해주세요");
				continue;
			}
			
			for(int i=1 ; i<inputVal.length() ; i++){
				if((inputVal.charAt(i)+"").equals("0") || (inputVal.charAt(i)+"").equals("1")){
					if((inputVal.charAt(i)+"").equals("1")){
						parseRet+=Math.pow(2, inputVal.length()-1-i);
					}
				}else{
					System.out.println("올바르게입력해주세요.");
					continue;
				}
				
			}
			if((inputVal.charAt(0)+"").equals("1")){
				parseRet=parseRet*-1;
			}
			
			System.out.println(inputVal + "(2) :: "+parseRet);
//			flag=false;
		}
		
	}
}
