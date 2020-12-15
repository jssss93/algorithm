package algorithm.cjs_20201211;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class zacardTest {
//	https://medium.com/@dltkddud4403/2018-%EC%B9%B4%EC%B9%B4%EC%98%A4-%EB%B8%94%EB%9D%BC%EC%9D%B8%EB%93%9C-%EC%B1%84%EC%9A%A9-%EB%89%B4%EC%8A%A4-%ED%81%B4%EB%9F%AC%EC%8A%A4%ED%84%B0%EB%A7%81-e324ee45bad8
	public static void main(String args[]){
	
		zacardTest sol = new zacardTest();
		sol.solution("FRANCE", "french");
		sol.solution("handshake", "shake hands");
		sol.solution("aa1+aa2", "AAAA12");
		sol.solution("E=M*C^2", "e=m*c^2");
	}
	
	
//	FRANCE	french	16384
//	handshake	shake hands	65536
//	aa1+aa2	AAAA12	43690
//	E=M*C^2	e=m*c^2	65536
	
	
	//정규식 패턴
	
//	숫자만 : ^[0-9]*$
//	영문만 : ^[a-zA-Z]*$
//	영문만, 띄어쓰기가능 : /^[a-zA-Z\s]+$/
//	한글만 : ^[가-힣]*$
//	한글만,띄어쓰기가능 :  /^[가-힣\s]+$/
//	한글 & 영문만 : /^[가-힣a-zA-Z]+$/;
//	영문 & 숫자만 : ^[a-zA-Z0-9]*$
//	E-Mail : ^[a-zA-Z0-9]+@[a-zA-Z0-9]+$
//	휴대폰 : ^01(?:0|1|[6-9]) - (?:\d{3}|\d{4}) - \d{4}$
//	일반전화 : ^\d{2,3} - \d{3,4} - \d{4}$
//	URL : /^(file|gopher|news|nntp|telnet|https?|ftps?|sftp):\/\/([a-z0-9-]+\.)+[a-z0-9]{2,4}.*$/
//	주민등록번호 : \d{6} \- [1-4]\d{6}
//	IP 주소 : ([0-9]{1,3}) \. ([0-9]{1,3}) \. ([0-9]{1,3}) \. ([0-9]{1,3})
	
	
    public int solution(String str1, String str2) {
    	System.out.println(str1);
    	System.out.println(str2);
    	
        int answer = 0;
        String match = "^[a-zA-Z]*$";
        
        
        str1= str1.toUpperCase();
        str2= str2.toUpperCase();
        //공백 또는 숫자 특수문자 가 들어있는지 판별.
        
        
        Set hs_1 = new HashSet();
        List hs_2 = new ArrayList();
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        
        //0.두글자씩 자른다.
        //1.배열에넣는다.
        for(int i=0;i<str1.length()-1;i++){
        	if(str1.substring(i,i+2).matches(match)){
	        	list1.add(str1.substring(i,i+2));
	        	hs_1.add(str1.substring(i,i+2));
        	}
        }
        for(int i=0;i<str2.length()-1;i++){
        	if(str2.substring(i,i+2).matches(match)){
        		list2.add(str2.substring(i,i+2));
        	}
        }
        System.out.println("list1 :: "+list1);
        System.out.println("list2 :: "+list2);
        
        
        list1.retainAll(list2);
//        hs_1.retainAll(list2);
        System.out.println("교집합 : "+list1);
        
        hs_1.addAll(list2);
//        hs_2.addAll(list2);
        System.out.println("합집합 : "+hs_1);
        //2. -1)교집합, -2)합집합을 구한다.
        //etc. array와 arraylist 의 차이를.
        System.out.println(list1.size());
        System.out.println(hs_1.size());
        
        answer = (int)(list1.size()/(double)hs_1.size() *65536);
        System.out.println("print answer : "+answer);
        System.out.println("--------------------------------------");
        return answer;
    	
    	
    	
    	
    	
    	
    }
    
}
