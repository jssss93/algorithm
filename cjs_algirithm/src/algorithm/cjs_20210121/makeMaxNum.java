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
		String number = "1924";
		int k = 2;
		System.out.println("answer :: "+sol.solution(number, k));
		
//		System.out.println("answer :: "+sol.solution2(number, k));
	}
	public String solution(String number, int k) {
//		if(number.length()==k){
//        	return "0";
//        }
		String answer="";
		StringBuilder sb = new StringBuilder(number);
		
        int del_cnt = 0;
        int idx = 1;
    	while(del_cnt<k ){
    		
//    		System.out.println("idx :: "+idx+"   del_cnt :: "+del_cnt);
//    		System.out.println(sb.charAt(idx)+",,"+sb.charAt(idx-1));
    		
    		if(idx>=1 && sb.charAt(idx)>sb.charAt(idx-1)){
//    			System.out.println("del");
    			sb.deleteCharAt(idx-1);
    			del_cnt++;
    			idx--;
    		}else{
    			if(idx==sb.length()-1 && sb.charAt(idx)<=sb.charAt(idx-1)){
    				sb.deleteCharAt(idx);
    				del_cnt++;
    				idx--;
    			}else{
    			idx++;
    			}
    		}
//    		System.out.println("중간결과"+sb.toString());
//    		System.out.println();
    	}
//    	System.out.println("k :: "+k);
//    	System.out.println("del_cnt :: "+del_cnt);
    	
		answer = sb.toString();
        
        return answer;
    }
//    public String solution2(String number, int k) {
//        //빠른 연산을 위해 StringBuilder을 사용했다.
//        StringBuilder sb = new StringBuilder(number);
//        int delete_count = 0;
//        int index = 1;
//        
//        while(delete_count != k) {
//        	System.out.println("index"+index);
//        	
//            //전의 숫자와 비교해야 하므로 index는 1부터 시작한다.
//            //전의 숫자보다 더 크면 전의 숫자를 삭제하고 크기가 줄어들었으므로 index를 줄여준다.
//            if(index>=1 && sb.charAt(index) > sb.charAt(index-1)) {
//                sb.deleteCharAt(index-1);
//                index--;
//                delete_count++;
//            } else {
//                //index가 맨 끝으로 가고, 그 전의 숫자와 작거나 같으면 지금의 숫자를 삭제해준다.
//                if(index==sb.length()-1 && sb.charAt(index) <= sb.charAt(index-1)) {
//                    sb.deleteCharAt(index);
//                    delete_count++;
//                    index--;
//                } else {
//                //그 외의 경우에는 index를 추가해준다.
//                index++;
//                }
//            }
//        }      
//        return sb.toString();
//    }
//	public String deleteIndex(String number,int idx){
//		return number.substring(0,idx)+number.substring(idx+1,number.length());
//	}
    
}
