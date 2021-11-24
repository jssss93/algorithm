package algorithm2.cjs_20211119;

public class BSExample {
	public static void main(String args[]) {
//		오름차순으로 정렬된 배열이 있다.

//		int[] arr = new int[7];

			
		int arr[] = { 17, 28, 43, 67, 88, 92, 100, 200 };

//		이 배열에서 이진 탐색을 이용하여 43의 값을 찾아보자.
		int start =0;
		int mid = 0 ;
		int end = arr.length-1;
		int target = 17;
		
		while(start<=end) {
			
			mid = start+end/2;
			System.out.println(start+",,"+mid+",,"+end);
			if(arr[mid]==target) {
				break;
			}else if(arr[mid]<target) {
				start = mid+1;
			}else if(arr[mid]>target) {
				end = mid-1;
			}
		}

		
		System.out.println(mid);
		System.out.println(arr[mid]);
	}
}
