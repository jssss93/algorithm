package algorithm.cjs_20210125.copy;

import java.util.Arrays;
import java.util.Comparator;

//전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
//전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
//
//구조대 : 119
//박준영 : 97 674 223
//지영석 : 11 9552 4421
//전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때,
//어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
//
//제한 사항
//phone_book의 길이는 1 이상 1,000,000 이하입니다.
//각 전화번호의 길이는 1 이상 20 이하입니다.
//입출력 예제
//phone_book	return
//[119, 97674223, 1195524421]	false
//[123,456,789]	true
//[12,123,1235,567,88]	false
//입출력 예 설명
//입출력 예 #1
//앞에서 설명한 예와 같습니다.
//
//입출력 예 #2
//한 번호가 다른 번호의 접두사인 경우가 없으므로, 답은 true입니다.
//
//입출력 예 #3
//첫 번째 전화번호, “12”가 두 번째 전화번호 “123”의 접두사입니다. 따라서 답은 false입니다.

public class phoneBook {
	public static void main(String args[]){
		phoneBook sol = new phoneBook();
		String[] phone_book = {"01","101"};
		sol.solution(phone_book);
		
	}
	
    public boolean solution(String[] phone_book) {
    	//1. phone_book 을 길이순으로 정렬.
    	//2. 접두어가 있는지 비교.
    	Arrays.sort(phone_book, new Comparator<String>() {
    	    @Override
    	    public int compare(String s1, String s2) {
    	        return s1.length() - s2.length();
    	    }
    	});
    	boolean answer = true;
    	
    	for(int i=0;i<phone_book.length;i++){
    		for(int j=i+1;j<phone_book.length;j++){
//    			System.out.println(phone_book[i]+",,"+(phone_book[j]+"").substring(0,phone_book[i].length()));
    			if (phone_book[i].equals((phone_book[j]+"").substring(0,phone_book[i].length()))) {
    				answer = false;
    				break;
				}
    		}
    		if(!answer){
    			break;
    		}
    	}
//    	System.out.println(answer);
        return answer;
    }
}




