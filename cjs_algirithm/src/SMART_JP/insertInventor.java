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

public class insertInventor {
	
	File path = new File("D:\\JPO\\2020");
	final String fatternName = ".xml" ;
	static int xmlCnt=0;
	static List<String> xmlList = new ArrayList();
	
	
	public static void main(String[] args) {
//		searchDirList("D:/JPO/2020");
		searchDirList("D:\\JPO\\2020\\JPO_2020-005\\2020-005\\DOCUMENT\\B9\\0006642001\\0006642601\\0006642641");
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
				
				String text="";
				String type="";
//				(1:기술분야,2:배경기술,3:발명의개요,과제를 해결하기위한 수단,도면의 간단한 설명,발명을 실시하기 위한 형)
//				(1:����о�,2:�����,3:�߸��ǰ���,������ �ذ��ϱ����� ����,������ ������ ����,�߸��� �ǽ��ϱ� ���� ��)
				Elements eles2 = doc.select("technical-field");
				type="1";
				for (Element ele2 : eles2) {
					Elements subnode = ele2.select("p");
					text=subnode.text();
					System.out.println("doc_id :: "+doc_id +"     applno :: "+applno+"      type :: "+type+"     text :: "+text);
					insertData(doc_id,applno,type,text);
				}
				
				Elements eles3 = doc.select("background-art");
				type="2";
				for (Element ele3 : eles3) {
					Elements subnode = ele3.select("p");
					text=subnode.text();
					System.out.println("doc_id :: "+doc_id +"     applno :: "+applno+"      type :: "+type+"     text :: "+text);
					insertData(doc_id,applno,type,text);
				}
				
				Elements eles4 = doc.select("summary-of-invention");
				type="3";
				for (Element ele4 : eles4) {
					Elements subnode = ele4.select("p");
					text=subnode.text();
					System.out.println("doc_id :: "+doc_id +"     applno :: "+applno+"      type :: "+type+"     text :: "+text);
					insertData(doc_id,applno,type,text);
				}
				
				Elements eles5 = doc.select("description-of-drawings");
				type="4";
				for (Element ele5 : eles5) {
					Elements subnode = ele5.select("p");
					text=subnode.text();
					System.out.println("doc_id :: "+doc_id +"     applno :: "+applno+"      type :: "+type+"     text :: "+text);
					insertData(doc_id,applno,type,text);
				}
				
				
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	 public static void insertData(String doc_id, String applno, String type,String text) throws SQLException {
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
				
				String strQuery = "INSERT INTO JP_DESCRIPTION(DOCID,APPLNO,TYPE,DESCRIPTION) VALUES(  ?,?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(strQuery);
				
				pstmt.setString(1, doc_id);
				pstmt.setString(2, applno);
		        pstmt.setString(3, type );
		        pstmt.setString(4, text);
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
