package algorithm.cjs_20201204;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FindMusicOfMelody {
	//접근 자체가 틀렸다. 토큰화 하여 다시 풀자.
	//https://tech.kakao.com/2017/11/14/kakao-blind-recruitment-round-3/
	public static void main(String args[]){
		String m="CC#BCC#BCC#BCC#B";
		String[] musicinfos={"03:00,03:30,FOO,CC#B","04:00,04:08,BAR,CC#BCC#BCC#B"};
		String answer = "";
		String playMelody="";
		
		for(int k=0;k<musicinfos.length;k++){
			System.out.println("===============");	
		
		
			SimpleDateFormat transFormat = new SimpleDateFormat("HH:mm");
			Date st_time =null;
			Date end_time =null;
			try {
				st_time = transFormat.parse(musicinfos[k].split(",")[0]);
				end_time = transFormat.parse(musicinfos[k].split(",")[1]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long diff = end_time.getTime() - st_time.getTime();
			int diff_min=(int) (diff/1000/60);
			System.out.println("diff_min :: "+diff_min);
			
			String Melody = musicinfos[k].split(",")[3];
			
			System.out.println(0%m.length());
			for(int i=0;i<diff_min;i++){
				System.out.println(i+"%"+m.length());
				playMelody+=Melody.charAt(i%m.length())+"";
			}
			System.out.println("find answer");
			System.out.println("playMelody :: "+playMelody);
			System.out.println(musicinfos[k].split(",")[3]);
			if(playMelody.contains(musicinfos[k].split(",")[3])){
				answer=musicinfos[k].split(",")[2];
				break;
			}
		}
		//각각의 배열에서 찾을것.
		System.out.println();
		
		
		
		
		System.out.println(answer);
	}
}
