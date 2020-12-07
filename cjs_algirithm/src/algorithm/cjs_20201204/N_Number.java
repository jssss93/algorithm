package algorithm.cjs_20201204;

import java.util.ArrayList;
import java.util.List;

public class N_Number {
	public static void main(String args[]){
//https://tech.kakao.com/2017/11/14/kakao-blind-recruitment-round-3/
//		문제1. N진수 게임
//		진법 n, 미리 구할 숫자의 갯수 t, 게임에 참가하는 인원 m, 튜브의 순서 p 가 주어진다.
//
//		2 ≦ n ≦ 16
//		0 ＜ t ≦ 1000
//		2 ≦ m ≦ 100
//		1 ≦ p ≦ m
		int n=16;
		int t=16;
		int m=2;
		int p=2;
		
		List numList = new ArrayList<>();
		numList.add(0);
		String answer="";//0111
		
		for(int i=0;i<t*m;i++){
			String parseNum = parseNum(i, n);
			for(int j=0;j<parseNum.length();j++){
				numList.add(parseNum.charAt(j));
			}
		}
		System.out.println("numList==>"+numList);
		for(int k=0;k<t*m;k++){
			if(k%m==p-1){
				answer+=numList.get(k);
			}
		}
		
		System.out.println(answer);
		
		
		
	}
	
	public static String parseNum(int num, int n){
		String resStr="";
		while(num>0){
			int res = num%n;
			String resS="";
			if(res==10){
				resS="A";
			}else if(res==11){
				resS="B";
			}else if(res==12){
				resS="C";
			}else if(res==13){
				resS="D";
			}else if(res==14){
				resS="E";
			}else if(res==15){
				resS="F";
			}else{
				resS=res+"";
			}
			resStr = resS+resStr;
			num=num/n;
		}
		
		return resStr;
	}
}
