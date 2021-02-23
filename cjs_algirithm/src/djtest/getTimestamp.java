package djtest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class getTimestamp {
	public static void main(String args[]) throws ParseException{
		String current_date="2021-02-15";
		System.out.println("date"+current_date);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(current_date);
		long timestamp=date.getTime();
		System.out.println("timestamp"+(timestamp/1000));


		



	}
}
