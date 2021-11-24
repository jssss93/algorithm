package SMART_JP;

import java.io.CharArrayReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import oracle.jdbc.OracleResultSet;
//import oracle.jdbc.driver.OracleResultSet;
import oracle.sql.CLOB;

public class insertIPC {
	
	File path = new File("D:\\JPO\\2020");
	final String fatternName = ".xml" ;
	static int xmlCnt=0;
	static List<String> xmlList = new ArrayList();
	
	
	public static void main(String[] args) {
		searchDirList("D:/JPO/2020");
//		searchDirList("D:\\JPO\\2020\\JPO_2020-005\\2020-005\\DOCUMENT\\B9\\0006642001\\0006642601\\0006642641");
		System.out.println(xmlCnt);
		for(int i=0;i<xmlList.size();i++){
			System.out.println(xmlList.get(i));
			try {
				File input = new File(xmlList.get(i));
				Document doc = Jsoup.parse(input, "EUC-JP", "http://example.com/");
				
				String doc_id = "";
				Elements eles = doc.select("publication-reference");
				for (Element ele : eles) {
					Elements subnode = ele.select("doc-number");
					doc_id=subnode.text();
				}
				
				
				String applno= "";
				Elements eles1 = doc.select("application-reference");
				for (Element ele1 : eles1) {
					Elements subnode = ele1.select("doc-number");
					applno=subnode.text();
				}
				
				
				
				String main_yn="";
				Elements eles2 = doc.select("classification-national");
				for (Element ele2 : eles2) {
					Elements subnode = ele2.select("main-clsf");
					main_yn=subnode.text();
					
				}
				String ipc_cd="";
				ipc_cd=main_yn;
				insertData(doc_id,applno,"Y",ipc_cd);
				
				Elements eles3 = doc.select("classification-national");
				for (Element ele3 : eles3) {
					Elements subnode = ele3.select("further-clsf");
//					ipc_cd=subnode.text();
					List list = subnode.eachText();
					for(int j=0;j<list.size();j++){
						ipc_cd=list.get(j)+"";
//						String main_yn_prm="";
//						if(main_yn.equals(ipc_cd)){
//							main_yn_prm="Y";
//						}else{
//							main_yn_prm="N";
//						}
						System.out.println("doc_id :: "+doc_id +"     applno :: "+applno+"     main_yn :: N    \n ipc_cd :: "+ipc_cd);
						insertData(doc_id,applno,"N",ipc_cd);
						
					}
					
					
				}
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	 public static void insertData(String doc_id, String applno, String main_yn,String ipc_cd) throws SQLException {
		 System.out.println("===============insert start===============");

			String url = "jdbc:oracle:thin:@localhost:1521:dbpasp1";
			Connection con = null;
//			Statement stmt = null;

			Properties props = new Properties();
			props.put("user", "SMART_JP");
			props.put("password", "SMART_JP");
			
			
			try {
				con = DriverManager.getConnection(url, props);
				con.setAutoCommit(false);
				
				String strQuery = "INSERT INTO JP_IPC2 (DOCID,APPLNO,MAIN_YN,IPC_CD) VALUES( ?, ?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(strQuery);
				
				pstmt.setString(1, doc_id);
				pstmt.setString(2, applno);
		        pstmt.setString(3, main_yn);
		        pstmt.setString(4, ipc_cd);
		        int nRowCnt = pstmt.executeUpdate();
		        pstmt.close();
				
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					con.close();
				}
				catch (Exception ignored) {
					
				}
			}
			 System.out.println("===============insert end===============");
		}
	 
	public static void searchDirList(String source) {

		File dir = new File(source);
		File[] fileList = dir.listFiles();
		
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile()) {
					String fileName = file.getName();
					int pos = fileName .lastIndexOf(".");
					String ext = fileName.substring(pos+1, fileName.length());
					if(ext.equals("xml")){
						xmlCnt++;
						xmlList.add(file.getCanonicalPath());
					}
				} else if (file.isDirectory()) {
					searchDirList(file.getCanonicalPath().toString());
				}
			}

		} catch (IOException e) {

		}
		System.out.println(xmlList.size()+"�� ��ȸ.");
	}
	
}
