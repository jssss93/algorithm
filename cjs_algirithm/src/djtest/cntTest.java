package djtest;

public class cntTest {
	public static void main(String args[]){
		int bef=420;
		int newInput=423;
		
		int tar = 0;
		for(int i=0;i<10;i++){
			if((bef+i)%10==0){
				tar=i;
				break;
			}
		}
		if(bef%10==0){
			tar=tar+10;
		}
		if(newInput-bef>=tar){
			System.out.println("알람");
		}
		System.out.println(tar);
		
	}
}
