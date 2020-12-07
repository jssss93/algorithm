package djtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
	public static void main(String args[]) throws Exception {
		CheckJSON cjson = new CheckJSON();
		cjson.check();
		System.out.println(cjson.getDriver());
	}
}

class CheckJSON {
	String driver = null;

	public void check() throws Exception {
		JSONParser parser = new JSONParser();
		File file = new File("C:\\1_100.json");
		String sLine = null;
		int cnt=0;
		if (file.exists()) {
			BufferedReader inFile = new BufferedReader(new FileReader(file));
			sLine = null;
			sLine = inFile.readLine();
			while (sLine != null)
				System.out.println(++cnt +sLine);
			
		}
		
		
//		setDriver((String) jo.get("JDBCDriver"));
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriver() {
		return driver;
	}
}