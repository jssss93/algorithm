package algorithm2.cjs_20211119;

public class BSExample {
	public static void main(String args[]) {
//		������������ ���ĵ� �迭�� �ִ�.

//		int[] arr = new int[7];

			
		int arr[] = { 17, 28, 43, 67, 88, 92, 100, 200 };

//		�� �迭���� ���� Ž���� �̿��Ͽ� 43�� ���� ã�ƺ���.
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
