package procTest;

import java.io.IOException;

public class proc {
	public static void main(String args[]) throws InterruptedException{
		Runtime rt = Runtime.getRuntime();
	    Process pc = null;
	    try {
	        //외부 프로세스 실행
	    	String pyPath="C:/Users/user/AppData/Local/Programs/Python/Python38/python.exe";
	    	String path=" C:/Users/user/Desktop/dawjones_20201221/dj-dna-streams-python-master/dj-dna-streams-python-master/dnaStreaming/test.py";

	    	pc = rt.exec(pyPath + path);
	    	
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        //명령어 종료시 까지 대기
	        pc.waitFor(); 
	        //명령어 종료시 하위 프로세스 제거
	        pc.destroy();
	        
	    }
	}
}
