package algorithm.cjs_20210126;

import java.util.ArrayList;
import java.util.List;

public class findSosu {
	public static void main(String args[]){
		findSosu sol = new findSosu();
		String numbers= "2";
		sol.solution(numbers);
	}
	public int solution(String numbers) {
        int answer = 0;
        List<String> arr =new ArrayList();
        List<String> sosuList =new ArrayList();
        for(int i=0;i<numbers.length();i++){
        	arr.add(numbers.charAt(i)+"");
        }
        for(int i=0;i<numbers.length();i++){
	        List<String> result =new ArrayList();
	        answer=permutation(arr, result, numbers.length(), i+1,answer,sosuList);
	        System.out.println();
        }
        System.out.println("answer :: "+answer);
        return answer;
    }
	
	/**
	 * 순열 구하기
	 * 
	 * @param arr    : 기준 리스트
	 * @param result : 결과를 담아줄 리스트
	 * @param n      : 전체 갯수
	 * @param r      : 뽑을 갯수
	 */
	private static int permutation(List<String> arr, List<String> result, int n, int r,int answer,List sosuList) {

		if (r == 0) {
			String str = "";
			int strInt = 0;
			for(int i=0;i<result.size();i++){
				str+=result.get(i);
			}
			if(str.charAt(0)=='0'){
				return answer;
			}
			strInt = Integer.parseInt(str);
			
			if(strInt < 2) {
//				System.out.print("소수가 아닙니다");
				return answer;
			}
			
			// 2 는 소수다
			if(strInt == 2) {
				for(int i=0;i<sosuList.size();i++){
					if(sosuList.get(i).equals(str)){
						return answer;
					}
				}
				sosuList.add(str);
				return ++answer;
			}
			
	        
			for(int i = 2; i < strInt; i++) {
	        
				// 소수가 아닐경우
				if(strInt % i == 0) {
//					System.out.print("소수가 아닙니다");
					return answer;
				}
			}
					
			
			for(int i=0;i<sosuList.size();i++){
				if(sosuList.get(i).equals(str)){
					return answer;
				}
			}
			sosuList.add(str);
//			System.out.println(result.toString());
			return ++answer;
			
		}

		for (int i = 0; i < n; i++) {

			result.add(arr.remove(i));
			answer = permutation(arr, result, n - 1, r - 1,answer,sosuList);
			arr.add(i, result.remove(result.size() - 1));
		}
		return answer;
	}
	
	
    
}
