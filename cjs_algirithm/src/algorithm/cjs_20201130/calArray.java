package algorithm.cjs_20201130;

import java.util.Arrays;

public class calArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 1, 5, 2, 6, 3, 7, 4 };
		int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };

//		System.out.println(commands[0][1]);
		int[] answer = new int[commands.length];

		for (int i = 0; i < commands.length; i++) {
			System.out.println("============================");
			answer[i] = solution(array, commands[i]);
		}

//		System.out.println(answer);
	}

	public static int solution(int[] array, int[] commands) {
		//2, 5, 3
//		int answer = 0;
		int[] answerArr=new int[commands[1]-commands[0]+1];
		System.out.println(answerArr.length+" : answerArr[] 길이");
		System.out.println();
//		System.out.println(answerArr[0]);
		int cnt=0;
		System.out.println("포문시작");
		for(int i=commands[0]-1;i<commands[1];i++){
			answerArr[cnt] = array[i];
			System.out.println("answerArr["+cnt+"] :: "+array[i]);
			cnt++;
			
		}
		Arrays.sort(answerArr);
		
		System.out.println("--------");
		System.out.println(commands[2]-1);
		
		return answerArr[commands[2]-1];
	}
	
	public static int[]  arrSort(int arr[]){
		int temp=-1;
		int retArr[]=new int[arr.length];
		
		for(int i=0;i<arr.length;i++){
			
		}
		return arr;
		
	}
}
