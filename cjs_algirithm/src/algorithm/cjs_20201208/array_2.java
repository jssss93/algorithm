package algorithm.cjs_20201208;

import java.util.HashMap;
import java.util.Set;

public class array_2 {
	//https://sas-study.tistory.com/215
	//https://blog.naver.com/PostView.nhn?blogId=yongyos&logNo=221478536324
	public static void main(String args[]){
		array_2 func = new array_2();
		String[][] colthes = {{"yellow_hat", "headgear"},{"yellow_hat2", "headgear"},{"yellow_hat22", "jean"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		
		System.out.println(func.solution(colthes));
		
	}
	
    public int solution(String[][] clothes) {
    	int answer = 1; //곱셈을 위해 1로 선언
        HashMap<String, Integer> clothesMap = new HashMap<String, Integer>();
        for(int i =0; i<clothes.length; i++){
            clothesMap.put(clothes[i][1], clothesMap.getOrDefault(clothes[i][1], 0)+1);
        }
        System.out.println(clothesMap);
        Set<String> keySet = clothesMap.keySet(); //의상종류.
        System.out.println(keySet);
        for(String key : keySet) {
        	System.out.println(clothesMap.get(key)+1);
        	answer *= clothesMap.get(key)+1; 
        }
        return answer-1; //아무것도 안입는 경우의 수 제거
    }
}
