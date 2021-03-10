package djtest;

public class cntTest {
	public static void main(String args[]){
		int bef=37599;
		int newInput=37700;
		
		int tar = 0;
		for(int i=0;i<1000;i++){
			if((bef+i)%1000==0){
				tar=i;
				break;
			}
		}
		if(bef%1000==0){
			tar=tar+1000;
		}
		if(newInput-bef>=tar){
			System.out.println("알람");
		}
		System.out.println(tar);
		
	}
}
