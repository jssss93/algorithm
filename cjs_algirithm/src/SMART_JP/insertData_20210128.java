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
import oracle.sql.CLOB;

public class insertData_20210128 {
	static int xmlCnt=0;
	static List<String> xmlList = new ArrayList();
	
	
	public static void main(String[] args) {
		searchDirList("D:/JPO/2013/");
//		searchDirList("D:\\JPO\\2020\\JPO_2020-005\\2020-005\\DOCUMENT\\B9\\0006642001\\0006642601");
		System.out.println(xmlCnt);
		
		
		
		System.out.println("===============insertSpec Start===============");
		insertSpec();
		System.out.println("===============insertSpec End===============");
		
		
		System.out.println("===============insertClaim start===============");
		insertClaim();
		System.out.println("===============insertClaim End===============");
	}
	public static void insertClaim(){
		for(int i=0;i<xmlList.size();i++){
			System.out.println(i+"��°���� Claim"+xmlList.get(i));
			try {
//				String dir = "D:\\JPO\\2020\\JPO_2020-001\\2020-001\\DOCUMENT\\B9\\0006586001\\0006586201\\0006586221\\";
//				String fileName = "0006586226.xml";
//				System.out.println(xmlList.get(i));
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
//				System.out.println("doc_id :: "+doc_id +"     applno :: "+applno);
				
				
				
				Elements eles2 = doc.select("claim");
//				List<String> list = new ArrayList();
				int claim_text_cnt=0;
				for (Element ele2 : eles2) {
					Elements subnode = ele2.select("claim-text");
//					System.out.println(subnode.text());
					insertClaimData(doc_id,applno,++claim_text_cnt,subnode.text());
//					list.add(subnode.text());
				}
				
				
//				System.out.println(list.get(0));
//				System.out.println(list.size()+"�� claimList");
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	 public static void insertClaimData(String doc_id, String applno, int claim_num,String claim__text) throws SQLException {
//		 System.out.println("===============insert start===============");
//			String resource = "D:/workspace/textParsing/bin/textParsing/prop/info.properties";
//			Properties properties = new Properties();

			String url = "jdbc:oracle:thin:@localhost:1521:dbpasp1";
			Connection con = null;
//			Statement stmt = null;

			Properties props = new Properties();
			props.put("user", "SMART_JP");
			props.put("password", "SMART_JP");
			
//			String[] data = string.split("��");
			
			try {
				con = DriverManager.getConnection(url, props);
				con.setAutoCommit(false);
//				stmt = con.createStatement();
				

//				String sqlStr1 = "INSERT INTO JP_CLAIM (DOCID,CLAIM_NUM,CLAIM2) VALUES ('"+doc_id+"','"+claim_num +"','"+claim__text +"')";
//				System.out.println(sqlStr1);
//				for(int i=0 ; i<data.length ; i++){
//					sqlStr1 += data[i] + ", ";
//					if(i == data.length){
//						sqlStr1 += data[i];
//					}
//				}
//						  sqlStr1+= "')";
//				ResultSet rs1 = stmt.executeQuery(sqlStr1);
				
//				stmt.close();
				
				
				////////////////////����
				
				String strQuery = "INSERT INTO JP_CLAIM (DOCID,APPLNO,CLAIM_NUM,CLAIM) VALUES( ?, ?, ?,  EMPTY_CLOB() )";
				PreparedStatement pstmt = con.prepareStatement(strQuery);
				
				pstmt.setString(1, doc_id);
				pstmt.setString(2, applno);
		        pstmt.setString(3, claim_num+"");
		        int nRowCnt = pstmt.executeUpdate();
		        pstmt.close();


		        if( nRowCnt == 1 ) {
		            // Make Select Query & Row Lock
		            strQuery = "SELECT CLAIM FROM JP_CLAIM WHERE DOCID = ? AND CLAIM_NUM = ? FOR UPDATE";
		            pstmt = con.prepareStatement(strQuery);
		            pstmt.setString(1, doc_id);
			        pstmt.setString(2, claim_num+"");
		            ResultSet rs = pstmt.executeQuery();
		 
		            // Write CLOB Data
		            String strCLOB = claim__text;
		            Boolean flag1=false;
		            Boolean flag2=false;
		            if(strCLOB.toString().contains("۰��")){
	                	flag1=true;
	                	//��� CLAIM_TYPE=1
	                	
	                }else if(strCLOB.toString().contains("ڪ��")){
	                	flag2=true;
	                	//��ǰ CLAIM_TYPE=2
	                	
	                }
		            if( rs.next() ) {
		                CLOB clob = ((OracleResultSet)rs).getCLOB("CLAIM");
		                
		                Writer writer = clob.getCharacterOutputStream();
		                Reader reader = new CharArrayReader(strCLOB.toCharArray());
		                char[] buffer = new char[1024];
		                int read = 0;
		                 
		                while ((read = reader.read(buffer, 0, 1024)) != -1) {
		                    writer.write(buffer, 0, read);
		                }
		                reader.close();
		                writer.close();
		            }
		            String uptQuery = "UPDATE JP_CLAIM SET CLAIM_TYPE = ? WHERE DOCID= ? AND CLAIM_NUM = ?";
					PreparedStatement pstmt2 = con.prepareStatement(uptQuery);
					String claim_type="3";
					if(flag1){
						claim_type="1";
					}else if(flag2){
						claim_type="2";
					}else{
						claim_type="3";
					}
//					System.out.println(claim_type+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					pstmt2 = con.prepareStatement(uptQuery);
					pstmt2.setString(1, claim_type);
					pstmt2.setString(2, doc_id);
		            pstmt2.setString(3, claim_num+"");
		            int res = pstmt2.executeUpdate();
		            pstmt2.close();
		            
		            // Commit
		            con.commit();
		            con.setAutoCommit(true);
		            rs.close();
		        }


			
//		        pstmt.setString(3, "XXXXX");


			
				
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					con.close();
				}
				catch (Exception ignored) {
					
				}
			}
//			 System.out.println("===============insert end===============");
		}
	 
	public static void insertSpec(){
		for(int i=0;i<xmlList.size();i++){
			System.out.println(i+"��°���� Spec"+xmlList.get(i));
//			System.out.println(xmlList.get(i));
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
				
				
				insertSpecData(doc_id,applno,kind,rgst_date,appl_date,invent_ttl,opno,op_date);
				
				
				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	 public static void insertSpecData(String doc_id, String applno, String kind,String rgst_date,String appl_date,String invent_ttl,String opno,String op_date) throws SQLException {
//		 System.out.println("===============insert start===============");

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
//			 System.out.println("===============insert end===============");
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
//		System.out.println(xmlList.size()+"�� ��ȸ.");
	}
}
