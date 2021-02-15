package djtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;

public class callAPITest {
	
	final static String user_key = "5OYQDNNSyAib11bI3zbspDhbyPjU7wSm";
	
	public static void main(String args[]){
		callAPITest sol =new callAPITest();
		String paramUrl="https://api.dowjones.com/alpha/accounts";
		
		JSONObject data=new JSONObject();
//		data.put("user-key", user_key);
		
		JSONObject headers=new JSONObject();
		headers.put("Content-Type", "application/json");
		headers.put("user-key", user_key);
		
		sol.RestCall(paramUrl, data,headers);
	}
	
	private void RestCall(String paramUrl,JSONObject data,JSONObject headers){
    	try {
    		
    		
            URL url = new URL(paramUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("user-key", "5OYQDNNSyAib11bI3zbspDhbyPjU7wSm");
            conn.setDoOutput(true);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            osw.write(data.toString());
            osw.flush();
            osw.close();
            System.out.println(conn.getHeaderField("user-key"));
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            if (conn.getResponseCode() != 200) {
                System.out.println("Failed: HTTP error code : " + conn.getResponseCode());
            	throw new RuntimeException("Failed: HTTP error code : " + conn.getResponseCode());
            } else {
                System.out.println("발송 성공");
            }
            
            String line = null;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            br.close();           
            conn.disconnect();
        } catch (IOException e) {
            System.out.println("RestCall Fail : " + e.getMessage());
        }
    }
}
