package algorithm;

import java.util.ArrayList;
import java.util.List;


public class complex {
	
	public static void main(String[] args) {

		List<String> arr = new ArrayList<>();
		arr.add("1");
		arr.add("1");
		arr.add("1");

		List<String> result = new ArrayList<>();
		
		
		
		System.out.println("순열 ==>");
		permutation(arr, result, arr.size(), 2);//순열
		
		
		
		System.out.println();
		System.out.println();
		
		
		
		combination(arr, result, 0, arr.size(), 2);//조합
		System.out.println("조합==>");

	}
	
	/**
	 * 순열 구하기
	 * 
	 * @param arr    : 기준 리스트
	 * @param result : 결과를 담아줄 리스트
	 * @param n      : 전체 갯수
	 * @param r      : 뽑을 갯수
	 */
	private static void permutation(List<String> arr, List<String> result, int n, int r) {

		if (r == 0) {

			System.out.println(result.toString());
			return;
		}

		for (int i = 0; i < n; i++) {

			result.add(arr.remove(i));
			permutation(arr, result, n - 1, r - 1);
			arr.add(i, result.remove(result.size() - 1));
		}
	}
	
	/**
	 * 조합 구하기
	 * 
	 * @param arr    : 기준 리스트
	 * @param result : 결과를 담아줄 리스트
	 * @param index  : 반복문 시작 인덱스
	 * @param n      : 전체 갯수
	 * @param r      : 뽑을 갯수
	 */
	private static void combination(List<String> arr, List<String> result, int index, int n, int r) {

		if (r == 0) {

			System.out.println(result.toString());
			return;
		}

		for (int i = index; i < n; i++) {

			result.add(arr.get(i));
			combination(arr, result, i + 1, n, r - 1);
			result.remove(result.size() - 1);
		}
	}
	
}
