package SMART_JP;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class insertPriority {
	
	File path = new File("D:\\JPO\\2020");
	final String fatternName = ".xml" ;
	static int xmlCnt=0;
	static List<String> xmlList = new ArrayList();
	
	
	public static void main(String[] args) {
		searchDirList("D:/JPO/2020");
//		searchDirList("D:\\JPO\\2020\\JPO_2020-005\\2020-005\\DOCUMENT\\B9\\0006642001\\0006642601");
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
				
				//////////////////////////////////
				
				String county="";
				String pri_doc_id="";
				String pri_doc_date="";
				int cnt=0;
				Elements eles2 = doc.select("priority-claim");
				for (Element ele2 : eles2) {
					cnt++;
					Elements subnode = ele2.select("country");
					county=subnode.text();

					Elements subnode2 = ele2.select("doc-number");
					pri_doc_id=subnode2.text();

					Elements subnode3 = ele2.select("date");
					pri_doc_date=subnode3.text();

					
//					System.out.println("doc_id :: "+doc_id +"     applno :: "+applno+"      type :: "+type+"     text :: "+text);
					insertData(doc_id,applno,cnt+"",county,pri_doc_id,pri_doc_date);
				}
				
				
				
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	 public static void insertData(String doc_id, String applno, String num,String country,String doc,String date) throws SQLException {
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
				String strQuery = "INSERT INTO JP_PRIORITY(DOCID,APPLNO,PRIORITY_NUM,PRIORITY_COUNTRY,PRIORITY_DOCID,PRIORITY_DATE)"+""
						+ 			" VALUES(  ?,?, ?, ?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(strQuery);
				
				pstmt.setString(1, doc_id);
				pstmt.setString(2, applno);
		        pstmt.setString(3, num );
		        pstmt.setString(4, country);
		        pstmt.setString(5, doc);
		        pstmt.setString(6, date);
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
