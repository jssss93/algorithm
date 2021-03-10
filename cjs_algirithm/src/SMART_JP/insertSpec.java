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

public class insertSpec {
	
	File path = new File("D:\\JPO\\2005\\");
	final String fatternName = ".xml" ;
	static int xmlCnt=0;
	static List<String> xmlList = new ArrayList();
	
	
	public static void main(String[] args) {
		searchDirList("D:/JPO/2005/");
//		searchDirList("D:\\JPO\\2020\\JPO_2020-005\\2020-005\\DOCUMENT\\B9\\0006642001\\0006642601");
		System.out.println(xmlCnt);
		for(int i=0;i<xmlList.size();i++){
			System.out.println(xmlList.get(i));
			try {
				File input = new File(xmlList.get(i));
				Document doc = Jsoup.parse(input, "EUC-JP", "http://example.com/");
				
				String doc_id = "";//RGSTNO,DOCID
				String kind = "";
				String rgst_date = "";
				Elements eles = doc.select("publication-reference");
				for (Element ele : eles) {
					Elements subnode = ele.select("doc-number");
					doc_id=subnode.text();
					
					Elements subnode_2 = ele.select("kind");
					kind=subnode_2.text();
					kind=kind.split("[(]")[1].split("[)]")[0];
					
					Elements subnode_3 = ele.select("date");
					rgst_date=subnode_3.text();
					
				}
				
				
				String applno= "";
				String appl_date="";
				Elements eles1 = doc.select("application-reference");
				for (Element ele1 : eles1) {
					Elements subnode = ele1.select("doc-number");
					applno=subnode.text();
					Elements subnode_2 = ele1.select("date");
					appl_date=subnode_2.text();
				}
				
				//////////////////////////////////
				
				String invent_ttl="";
				int cnt=0;
				Elements eles2 = doc.select("invention-title");
				invent_ttl=eles2.text();
				String opno="";
				String op_date="";
//				jp:previously-published-document
				Elements eles3 = doc.select("jp|previously-published-document");
				for (Element ele1 : eles3) {
					Elements subnode = ele1.select("doc-number");
					opno=subnode.text();
					Elements subnode_2 = ele1.select("date");
					op_date=subnode_2.text();
				}
				
				
				insertData(doc_id,applno,kind,rgst_date,appl_date,invent_ttl,opno,op_date);
				
				
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	 public static void insertData(String doc_id, String applno, String kind,String rgst_date,String appl_date,String invent_ttl,String opno,String op_date) throws SQLException {
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
				String strQuery = "INSERT INTO JP_SPEC(APPLNO,APPL_DATE,DOCID,DOCKIND,RGSTNO,RGST_DATE,INVENT_TTL,OPNNO,OPN_DATE)"+""
						+ 			" VALUES(  ?,?, ?, ?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(strQuery);
				
				pstmt.setString(1, applno);
				pstmt.setString(2, appl_date);
		        pstmt.setString(3, doc_id );
		        pstmt.setString(4, kind);
		        pstmt.setString(5, doc_id);
		        pstmt.setString(6, rgst_date);
		        
		        pstmt.setString(7, invent_ttl);
		        pstmt.setString(8, opno);
		        pstmt.setString(9, op_date);
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
