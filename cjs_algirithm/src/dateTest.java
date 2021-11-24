import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class dateTest {
	public static void main(String args[]) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		System.out.println(getSunday("2021","08","01"));
	}
	
	
	public static String getSunday(String yyyy,String mm, String wk){

 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy.MM.dd");

 		Calendar c = Calendar.getInstance();

 		

 		int y=Integer.parseInt(yyyy);

 		int m=Integer.parseInt(mm)-1;

 		int w=Integer.parseInt(wk);

 		

 		c.set(Calendar.YEAR,y);

 		c.set(Calendar.MONTH,m);

 		c.set(Calendar.WEEK_OF_MONTH,w);

 		c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);

 		c.add(c.DATE,7);

 		return formatter.format(c.getTime());

 	}





	
}
