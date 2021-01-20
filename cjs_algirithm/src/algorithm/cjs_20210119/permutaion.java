package algorithm.cjs_20210119;

public class permutaion {
	public static void main(String args[]){
		
		int[] arr={1,2,3};
		int[] output = new int[arr.length];
		for(int i=0;i<arr.length;i++){
			output[i] = arr[i];
		}
		
		
		for(int i=0;i<output.length;i++){
			System.out.println(output[i]);
		}
		
	}
}
