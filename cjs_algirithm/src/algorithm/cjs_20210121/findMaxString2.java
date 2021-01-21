package algorithm.cjs_20210121;

//0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
//
//예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
//
//0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
//
//제한 사항
//numbers의 길이는 1 이상 100,000 이하입니다.
//numbers의 원소는 0 이상 1,000 이하입니다.
//정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
public class findMaxString2 {
	public static void main(String args[]){
		findMaxString2 sol = new findMaxString2();
//		int[] numbers = new int[5];
//		for(int i=4;i>=0;i--){
//			numbers[i]=i;
//		}
		int[] numbers={1, 11, 111, 1111};
		sol.solution(numbers);
		//앞에꺼가 크면 1, 뒤에꺼가 크면 -1, 같으면 0
		
	}
	public String solution(int[] numbers) {
        String answer = "";
        
        for(int i=0;i<numbers.length;i++){
        	
        	for(int j=i+1;j<numbers.length;j++){
        		String compVal1 = (numbers[i]+"").charAt(0)+"";
        		String compVal2 = (numbers[j]+"").charAt(0)+"";
//        		System.out.println(i+"||"+j);
//        		System.out.println("compare"+compVal1+",,"+compVal2);
        		
        		
        		if(compVal1.compareTo(compVal2)<0){
        			swapArr(numbers,i,j);
        			i--;
        			break;
        			
//            		max = numbers[i]+"";
            	}else if(compVal1.compareTo(compVal2)==0){
            		compare(numbers,i,j);
//            		if(numbers[i]+"")
            	}
        	}
        	
        	
        }
        
        boolean zeroFlag = true;
        for(int i=0;i<numbers.length;i++){
        	answer+=numbers[i];
//        	System.out.println(numbers[i]);
        	if(numbers[i]!=0){
        		zeroFlag=false;
        	}
        }
        if(zeroFlag){
        	answer="0";
        }
        System.out.println("answr=>");
        System.out.println(answer);
        return answer;
    }
	public void swapArr(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	public void compare(int[] numbers,int a,int b){
		int returnVal=0;
		String strA = numbers[a]+"";
		String strB = numbers[b]+"";
		int minLength=strA.length();
		if(strA.length()>strB.length()){
			minLength=strB.length();
		}
		
		for(int i=0;i<minLength;i++){
			
			if((strA.charAt(i)+"").compareTo(strB.charAt(i)+"")<0){
				swapArr(numbers, a, b);
				returnVal=1;
				break;
			}
		}
		if(returnVal==0){
			if(strA.length()<strB.length()){
//				System.out.println("1");
//				System.out.println(strA+"||"+strB);
//				System.out.println(strA.charAt(0)+"||"+strB.charAt(minLength));
				if(strA.charAt(0)<strB.charAt(minLength)){
					swapArr(numbers, a, b);
				}else{
					if(strA.compareTo(strB)<0){
						swapArr(numbers, a, b);
					}
				}
			}else if(strA.length()>strB.length()){
//				System.out.println("2");
//				System.out.println(strA+"||"+strB);
//				System.out.println(strA.charAt(minLength)+"||"+strB.charAt(0));
				if(strA.charAt(minLength)<strB.charAt(0)){
					swapArr(numbers, a, b);
				}else{
					if(strA.compareTo(strB)<0){
						swapArr(numbers, a, b);
					}
				}
			} 
				else{
				if(strA.compareTo(strB)<0){
					swapArr(numbers, a, b);
				}
			}
			
		}
	}
	
}
