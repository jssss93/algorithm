package algorithm.cjs_20210128;

import java.util.ArrayList;
import java.util.List;

public class targetNumber {
//	n개의 음이 아닌 정수가 있습니다. 이 수를 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다.
//	예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
//
//	-1+1+1+1+1 = 3
//	+1-1+1+1+1 = 3
//	+1+1-1+1+1 = 3
//	+1+1+1-1+1 = 3
//	+1+1+1+1-1 = 3
//	사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
//
//	제한사항
//	주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
//	각 숫자는 1 이상 50 이하인 자연수입니다.
//	타겟 넘버는 1 이상 1000 이하인 자연수입니다.
//	입출력 예
//	numbers	target	return
//	[1, 1, 1, 1, 1]	3	5
//	입출력 예 설명
//	문제에 나온 예와 같습니다.
	public static void main(String args[]){
		
		int[] numbers = {1,1,1,1,1};
		int target = 1;
		
		targetNumber sol = new targetNumber();
		sol.solution(numbers, target);
		
	}
	public int solution(int[] numbers, int target) {
        int answer = 0;
        List<Integer> arr = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        //다 더한 후에 특정 숫자만 빼면 타겟이 되도록?
        int sum = 0;
        for(int i=0;i<numbers.length;i++){
        	sum+=numbers[i];
        	arr.add(numbers[i]);
//        	System.out.println(numbers[i]);
        }
        
		
		
		
		
//		System.out.println("순열 ==>");
		for(int i=0;i<numbers.length;i++){
			answer = combination(arr, result, 0, arr.size(), i,sum-target,answer);//조합
		}
//		System.out.println(answer);
        return answer;
    }
	private static int combination(List<Integer> arr, List<Integer> result, int index, int n, int r,int value,int answer) {
		
		if (r == 0) {
//			System.out.println(result.toString());
			int sum =0;
			for(int i=0;i<result.size();i++){
				sum=sum+result.get(i)*2;
			}
//			
			if(sum==value){
//				System.out.println(result.toString());
//				System.out.println(value+"||"+sum);
				return 1+answer;
			}
			
			return answer;
		}

		for (int i = index; i < n; i++) {

			result.add(arr.get(i));
			answer = combination(arr, result, i + 1, n, r - 1,value,answer);
			result.remove(result.size() - 1);
		}
		return answer;
	}
}
