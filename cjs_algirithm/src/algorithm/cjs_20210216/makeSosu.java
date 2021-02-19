package algorithm.cjs_20210216;

import java.util.ArrayList;
import java.util.List;

//주어진 숫자 중 3개의 수를 더했을 때 소수가 되는 경우의 개수를 구하려고 합니다. 
//숫자들이 들어있는 배열 nums가 매개변수로 주어질 때, 
//nums에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때 소수가 되는 경우의 개수를 return 하도록 solution 함수를 완성해주세요.
//
//제한사항
//nums에 들어있는 숫자의 개수는 3개 이상 50개 이하입니다.
//nums의 각 원소는 1 이상 1,000 이하의 자연수이며, 중복된 숫자가 들어있지 않습니다.
//입출력 예
//nums	result
//[1,2,3,4]	1
//[1,2,7,6,4]	4
//입출력 예 설명
//입출력 예 #1
//[1,2,4]를 이용해서 7을 만들 수 있습니다.
//
//입출력 예 #2
//[1,2,4]를 이용해서 7을 만들 수 있습니다.
//[1,4,6]을 이용해서 11을 만들 수 있습니다.
//[2,4,7]을 이용해서 13을 만들 수 있습니다.
//[4,6,7]을 이용해서 17을 만들 수 있습니다.
public class makeSosu {
	public static void main(String args[]){
		
		makeSosu sol = new makeSosu();
		int[] nums={1,2,7,6,4};
		System.out.println("정답 :: "+sol.solution(nums));
	}
	public int solution(int[] nums) {
        int answer = 0;

		List<String> arr = new ArrayList<>();
		List<String> result = new ArrayList<>();
		for(int i=0;i<nums.length;i++){
			arr.add(nums[i]+"");
		}
		
		answer = combination(arr, result, 0, arr.size(), 3,answer);//조합

        return answer;
    }
	private int combination(List<String> arr, List<String> result, int index, int n, int r,int answer) {
		
		if (r == 0) {
			int sum = 0;
			for(int i=0;i<result.size();i++){
				sum+=Integer.parseInt(result.get(i));
			}
			if(is_prime(sum)){
				answer++;
			}
			
			
			return answer;
		}

		for (int i = index; i < n; i++) {

			result.add(arr.get(i));
			answer = combination(arr, result, i + 1, n, r - 1,answer);
			result.remove(result.size() - 1);
		}
		return answer;
	}
	
	public boolean is_prime(int number) {
		 
		// 0 과 1 은 소수가 아니다
		if(number < 2) {
//			System.out.print("소수가 아닙니다");
			return false;
		}
		
		// 2 는 소수다
		if(number == 2) {
//			System.out.print("소수입니다");
			return true;
		}
		
        
		for(int i = 2; i < number; i++) {
        
			// 소수가 아닐경우
			if(number % i == 0) {
//				System.out.print("소수가 아닙니다");
				return false;
			}
		}
		// 위 반복문에서 약수를 갖고 있지 않는경우 소수다.
//		System.out.print("소수입니다");
		return true;
	}
	
}
