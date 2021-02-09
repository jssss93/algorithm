package algorithm.cjs_20210209;
//2차원 행렬 arr1과 arr2를 입력받아, arr1에 arr2를 곱한 결과를 반환하는 함수, solution을 완성해주세요.
//
//제한 조건
//행렬 arr1, arr2의 행과 열의 길이는 2 이상 100 이하입니다.
//행렬 arr1, arr2의 원소는 -10 이상 20 이하인 자연수입니다.
//곱할 수 있는 배열만 주어집니다.
//입출력 예
//arr1	arr2	return
//[[1, 4], [3, 2], [4, 1]]	[[3, 3], [3, 3]]	[[15, 15], [15, 15], [15, 15]]
//[[2, 3, 2], [4, 2, 4], [3, 1, 4]]	[[5, 4, 3], [2, 4, 1], [3, 1, 1]]	[[22, 22, 11], [36, 28, 18], [29, 20, 14]]
public class arrayGob {
	
	public static void main(String args[]){
		int arr1[][] = {{1,1}, {1,1}};
		int arr2[][] = {{1,1}, {1,1},{1,1},{1,1}};
		arrayGob sol = new arrayGob();
		sol.solution(arr1, arr2);
	}
	
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        
        for(int i=0;i<arr1.length;i++){
        	for(int j=0;j<arr2[0].length;j++){
        		//0,0 * 0,0 + 0,1*1,0
//        		System.out.println(i+"||"+j+"==>");
        		answer[i][j] = 0;
        		for(int k=0;k<arr1[0].length;k++){
//        			System.out.println(i+"||"+k+"**"+k+"||"+j);
        			answer[i][j] +=arr1[i][k]*arr2[k][j];
        		}
//        		System.out.println(answer[i][j]);
        	}
        }
        
        return answer;
    }
}
