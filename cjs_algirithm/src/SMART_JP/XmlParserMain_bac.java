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

public class XmlParserMain_bac {
	
	File path = new File("D:\\JPO\\2020");
	final String fatternName = ".xml" ;
	static int xmlCnt=0;
	static List<String> xmlList = new ArrayList();
	
	
	public static void main(String[] args) {
		searchDirList("D:/JPO/2020");
//		List<String> dirList = searchDirList("D:/JPO/2020");
//		List<String> dirList = searchDirList("D:\\JPO\\2020\\JPO_2020-005\\2020-005\\DOCUMENT\\B9\\0006642001\\0006642601\\0006642641");
		System.out.println(xmlCnt);
		for(int i=0;i<xmlList.size();i++){
			System.out.println(xmlList.get(i));
			try {
//				String dir = "D:\\JPO\\2020\\JPO_2020-001\\2020-001\\DOCUMENT\\B9\\0006586001\\0006586201\\0006586221\\";
//				String fileName = "0006586226.xml";
				System.out.println(xmlList.get(i));
				File input = new File(xmlList.get(i));
				Document doc = Jsoup.parse(input, "EUC-JP", "http://example.com/");
				
				String doc_id = "";
				Elements eles = doc.select("publication-reference");
				for (Element ele : eles) {
					Elements subnode = ele.select("doc-number");
					doc_id=subnode.text();
				}
				System.out.println("doc_id :: "+doc_id);
				Elements eles2 = doc.select("claim");
//				List<String> list = new ArrayList();
				int claim_text_cnt=0;
				for (Element ele2 : eles2) {
					Elements subnode = ele2.select("claim-text");
					System.out.println(subnode.text());
					insertData(doc_id,++claim_text_cnt,subnode.text());
//					list.add(subnode.text());
				}
				
				
//				System.out.println(list.get(0));
//				System.out.println(list.size()+"개 claimList");
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	 public static void insertData(String doc_id,int claim_num,String claim__text) throws SQLException {
		 System.out.println("===============insert start===============");
//			String resource = "D:/workspace/textParsing/bin/textParsing/prop/info.properties";
//			Properties properties = new Properties();

			String url = "jdbc:oracle:thin:@localhost:1521:dbpasp1";
			Connection con = null;
//			Statement stmt = null;

			Properties props = new Properties();
			props.put("user", "SMART_JP");
			props.put("password", "SMART_JP");
			
//			String[] data = string.split("¶");
			
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
				
				
				////////////////////변경
				
				String strQuery = "INSERT INTO JP_CLAIM (DOCID,CLAIM_NUM,CLAIM2) VALUES( ?, ?,  EMPTY_CLOB() )";
				PreparedStatement pstmt = con.prepareStatement(strQuery);
				
				pstmt.setString(1, doc_id);
		        pstmt.setString(2, claim_num+"");
		        int nRowCnt = pstmt.executeUpdate();
		        pstmt.close();


		        if( nRowCnt == 1 ) {
		            // Make Select Query & Row Lock
		            strQuery = "SELECT CLAIM2 FROM JP_CLAIM WHERE DOCID = ? AND CLAIM_NUM = ? FOR UPDATE";
		            pstmt = con.prepareStatement(strQuery);
		            pstmt.setString(1, doc_id);
			        pstmt.setString(2, claim_num+"");
		            ResultSet rs = pstmt.executeQuery();
		 
		            // Write CLOB Data
		            String strCLOB = claim__text;
		            if( rs.next() ) {
		                CLOB clob = ((OracleResultSet)rs).getCLOB("CLAIM2");
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
			 System.out.println("===============insert end===============");
		}
	 
	public static void searchDirList(String source) {

		File dir = new File(source);
		File[] fileList = dir.listFiles();
		
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile()) {
//					System.out.println("\t 파일 이름 = " + file.getName());
					String fileName = file.getName();
					int pos = fileName .lastIndexOf(".");
					String ext = fileName.substring(pos+1, fileName.length());
					if(ext.equals("xml")){
						xmlCnt++;
						xmlList.add(file.getCanonicalPath());
//						list.add(file.getCanonicalPath().replaceAll("\\", "\\\\"));
//						System.out.println(file.getCanonicalPath() );
					}
				} else if (file.isDirectory()) {
					searchDirList(file.getCanonicalPath().toString());
				}
			}

		} catch (IOException e) {

		}
		System.out.println(xmlList.size()+"건 조회.");
//		return list;
	}
	
}
