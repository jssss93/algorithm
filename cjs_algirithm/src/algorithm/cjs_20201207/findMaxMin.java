package algorithm.cjs_20201207;

public class findMaxMin {
	
	public static void main(String args[]){
		findMaxMin func = new findMaxMin();
		func.solution("1 2 3 4");
	}
	public String solution(String s){
		String answer = "";
		
		String[] arr = s.split(" ");
		int max = Integer.parseInt(arr[0]);
		int min = Integer.parseInt(arr[0]);
		
		for(int i=0;i<arr.length;i++){
			if(max<Integer.parseInt(arr[i])){
				max=Integer.parseInt(arr[i]);
			}
			if(min>Integer.parseInt(arr[i])){
				min=Integer.parseInt(arr[i]);
			}
		}
		answer = min+" "+max;
        return answer;
	}
}
