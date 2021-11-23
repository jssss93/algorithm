package algorithm2.cjs_20211119;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.util.SystemOutLogger;

public class chuseokTraffic {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		String[] lines = {
			"2016-09-15 20:59:57.421 0.351s",
			"2016-09-15 20:59:58.233 1.181s",
			"2016-09-15 20:59:58.299 0.8s",
			"2016-09-15 20:59:58.688 1.041s",
			"2016-09-15 20:59:59.591 1.412s",
			"2016-09-15 21:00:00.464 1.466s",
			"2016-09-15 21:00:00.741 1.581s",
			"2016-09-15 21:00:00.748 2.31s",
			"2016-09-15 21:00:00.966 0.381s",
			"2016-09-15 21:00:02.066 2.62s"
		};
		solution(lines);
	}
    public static int solution(String[] lines) throws ParseException {
        int answer = 0;
        
        
		int[] startTimes = new int[lines.length];
		int[] endTimes = new int[lines.length];
		
		getTimes(lines, startTimes, endTimes);
		
		
		for(int i = 0 ; i < lines.length ; ++i) {
			int cnt = 0;
			int startOfSection = startTimes[i];
			int endOfSection = startOfSection + 1000;
			
			for(int j = 0 ; j < lines.length ; ++j) {
				if(startTimes[j] >= startOfSection && startTimes[j] < endOfSection) {
					cnt++;
				} else if(endTimes[j] >= startOfSection && endTimes[j] < endOfSection) {
					cnt++;
				} else if(startTimes[j] <= startOfSection && endTimes[j] >= endOfSection) {
					cnt++;
				}
			}

			answer = cnt > answer ? cnt : answer;
		}
		
		
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//        
//        for(int i=0;i<lines.length;i++) {
//        	
//        	
//        	String subTime = lines[i].split(" ")[2].replace("s", "").replace(".","");
//        	subTime = setRPad(subTime,4,"0");
//        	
//        	long subTime_long = Long.parseLong(subTime);
//            Date d = sdf.parse(lines[i]);
//             
//            Calendar cal = Calendar.getInstance();
//	        cal.setTime(d);
//	        long end_time = cal.getTimeInMillis();
//	        long start_time = end_time-subTime_long;
//	        
//	        cal.setTimeInMillis(start_time);
//	        Date start_date = cal.getTime();
//	        
//	        cal.add(Calendar.SECOND, 1);
//	        Date start_date_1 = cal.getTime();
//	        
//	        cal.setTimeInMillis(end_time);
//	        Date end_date = cal.getTime();
//	        
//	        
//	        String start_str = sdf.format(start_date);
//	        String end_str = sdf.format(end_date);
//	        System.out.println(start_date+",,"+end_date);
//	        
//	        int max = 0;
//	        if(i!=lines.length-1) {
//	        	//첫번째 시작시간부터 1초간 for문돌린다.
//	        	//=>첫번쨰 시작시간+1초보다 포문의 시작시간이 작으면 ++;
//	        	//두번째 시작시간부터 1초간 for문돌린다.
//	        	System.out.println(i);
//		        for(int j=i+1;j<lines.length;j++) {
//		        	String subTime2 = lines[j].split(" ")[2].replace("s", "").replace(".","");
//		        	subTime2 = setRPad(subTime,4,"0");
//		        	
//		        	long subTime_long2 = Long.parseLong(subTime2);
//		            Date d2 = sdf.parse(lines[j]);
//		             
//		            Calendar cal2 = Calendar.getInstance();
//			        cal.setTime(d2);
//			        long end_time2 = cal.getTimeInMillis();
//			        long start_time2 = end_time2-subTime_long2;
//			        
//			        cal.setTimeInMillis(start_time2);
//			        Date start_date2 = cal.getTime();
//			        
//			        System.out.println(start_date_1+",,"+start_date2);
//		        	if(start_date_1.compareTo(start_date2)>0) {
//		        		System.out.println("작다");
//		        		max++;
//		        	}
//		        }
//		        if(answer<max) {
//		        	answer=max;
//		        }
//	        }
//            System.out.println("##");
//        }
        System.out.println(answer);
        return answer;
    }
    
	private static String setRPad(String strContext, int iLen, String strChar) {
		String strResult = "";
		StringBuilder sbAddChar = new StringBuilder();
		for (int i = strContext.length(); i < iLen; i++) {
			// iLen길이 만큼 strChar문자로 채운다.
			sbAddChar.append(strChar);
		}
		strResult = strContext + sbAddChar; // LPAD이므로, 채울문자열 + 원래문자열로 Concate한다.
		return strResult;
	}
	
	private  static void getTimes(String[] lines, int[] startTimes, int[] endTimes) {
		for(int i = 0 ; i < lines.length ; ++i) {
			String[] log = lines[i].split(" ");
			String[] responseTime = log[1].split(":");
			int processingTime = (int)(Double.parseDouble(log[2].substring(0, log[2].length() - 1)) * 1000);
			int startTime = 0;
			int endTime = 0;
			
			endTime += Integer.parseInt(responseTime[0]) * 60 * 60 * 1000;
			endTime += Integer.parseInt(responseTime[1]) * 60 * 1000;
			endTime += (int)(Double.parseDouble(responseTime[2]) * 1000);
			startTime = endTime - processingTime + 1;
			
			startTimes[i] = startTime;
			endTimes[i] = endTime;
		}
	}
	
    		
}
