package algorithm.cjs_20201110;

import java.util.Scanner;

public class ex1 {
	public static void main(String args[]){
//		화폐는 고액권을 우선으로 한다.
//		화폐 단위는 5만원권 부터 1원권 까지 10종류로 한다.
//		성명과 출장비지급액을 입력받는다. 성명에 quit가 입력될 경우 종료한다.
//		출력은 성명, 출장비지급액, 개인별 각 화폐 매수로 하며, 마지막에 화폐 단위별 전체 매수를 출력한다.
//		변수 설명은 주석으로 대체
//		출처: https://cbts.tistory.com/137?category=649617 [IT일기장]
		
		//50000,10000,5000,1000,500,100,50,10,5,1
		
//		int[] unit = new int[10];
		int[] unit = {50000,10000,5000,1000,500,100,50,10,5,1};
		int[] cnt = new int[10];
		int totCnt = 0;
		String name="";
		int amount = 0;
		loop :while(true){
			Scanner sc_name =new Scanner(System.in);
			System.out.println("이름을 입력하세요");
			name = sc_name.nextLine();
			
			if(name.equals("quit")){
				System.out.println("시스템을 종료합니다.");
				return ;
			}
			
			Scanner sc_amount =new Scanner(System.in);
			System.out.println("출장비 지급액을 입력하세요");
			amount = sc_amount.nextInt();
			System.out.println("성명 : "+name);
			System.out.println("출장비지급액 : "+amount);
			
			for(int i=0;i<unit.length;i++){
				cnt[i] = amount/unit[i];
				amount = amount - unit[i]*cnt[i];
			}
			
			for(int i=0;i<unit.length;i++){
				totCnt+=cnt[i];
				System.out.println(unit[i]+"원 :: "+cnt[i]);
			}
			System.out.println("전체 매수 : "+totCnt);
		}
		
	}
}
