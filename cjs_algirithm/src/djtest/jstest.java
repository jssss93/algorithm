package djtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class jstest {
	static Connection con = null;
	static int searchCnt=0;
	static List<String> searchList = new ArrayList();
	
	public static void main(String[] args) throws ParseException, java.text.ParseException {
//		String rslt=
		String root="C:/Users/user/Desktop/다우존스 데이터";
		searchDirList(root,"json");
		System.out.println(searchList);
		for(int i=0;i<searchList.size();i++){
			String rslt = insertDJ(searchList.get(i));
			if(rslt.equals("1")){
				System.out.println(i+"번째 파일 ::"+searchList.get(i)+"성공");
			}
		}
		
		
		
		
		
	}
	public static void searchDirList(String source,String extPrm) {

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
					if(ext.equals(extPrm)){
						searchCnt++;
						searchList.add(file.getCanonicalPath());
//						list.add(file.getCanonicalPath().replaceAll("\\", "\\\\"));
//						System.out.println(file.getCanonicalPath() );
					}
				} else if (file.isDirectory()) {
					searchDirList(file.getCanonicalPath().toString(),extPrm);
				}
			}

		} catch (IOException e) {

		}
		System.out.println(searchList.size()+"건 조회.");
	}
	public static String insertDJ(String filePath) throws ParseException, java.text.ParseException{
		long start = System.currentTimeMillis();
//		System.out.println("insertDJ START!!!-------------------------------------------------");
//		System.out.println(new java.util.Date());
		String rslt="0";
		JSONParser parser = new JSONParser();
		int cnt=0;
    	
    	
		try {
			File text = new File(filePath);
			FileReader textRead = new FileReader(text);
			BufferedReader bfReader = new BufferedReader(textRead);
			String line = "";
			List<String> lineArray = new ArrayList<String>();
			while ((line = bfReader.readLine()) != null) {
				lineArray.add(line);
			}
			for (int i = 0; i < lineArray.size(); i++) {
				Object obj = parser.parse(lineArray.get(i));
				JSONObject jo = (JSONObject) obj;
				cnt++;
				
//				Iterator iter = jo.keySet().iterator();
//				while (iter.hasNext()) {
//					Object value =null;
//					String key = (String) iter.next();
//					if (jo.get(key)!=null && !jo.get(key).equals("")) {
//						value = jo.get(key);
//					}else{
//						value = "";
//					}
//					System.out.println(key +"  ::  "+value);
//				}
//				System.out.println();
				System.out.println(cnt+"번째 :: "+insert_KNA(jo));
//146.617초
			}
			
			
		} catch (FileNotFoundException e) {
			// 파일 없거나 이름 안맞으면 여기걸림.
			e.printStackTrace();
			System.out.println("파일이 존재하지않습니다. 경로를 확인해주세요");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
		System.out.println( cnt+"개 완료 시간 : " + ( end - start )/1000.0 +"초"); //실행 시간 계산 및 출력
//		System.out.println("insertDJ END!!!-------------------------------------------------");
		rslt="1";
		return rslt;
			
	}
	
	public static Boolean insert_KNA(JSONObject jo) throws java.text.ParseException{
		
		
		Boolean rslt=false;
		
        PreparedStatement pstmt=null;
    
        String url = "jdbc:postgresql://58.149.174.155:5433/kna";
        String user = "kna";
        String password = "kna123";
 
        try {
            con = DriverManager.getConnection(url, user, password);
            
            	
            	String query="";
            	query = "INSERT INTO dawjones("+
					            "copyright, subject_codes, art, modification_datetime, body, company_codes_occur_ticker_exchange, "+
					            "company_codes_occur, company_codes_about, company_codes_lineage, "+
					            "company_codes_ticker_exchange, snippet, company_codes_relevance_ticker_exchange, "+
					            "publication_date, market_index_codes, credit, section, company_codes_association_ticker_exchange, "+
					            "region_of_origin, company_codes_lineage_ticker_exchange, ingestion_datetime, "+
					            "modification_date, source_name, language_code, region_codes, "+
					            "company_codes_association, person_codes, byline, dateline, company_codes_relevance, "+
					            "source_code, an, word_count, company_codes, industy_codes, title, "+
					            "publication_datetime, publisher_name, action, document_type, "+
					            "reg_date, upt_date, currency_codes, company_codes_about_ticker_exchange)"+
					    "VALUES (?, ?, ?,  to_timestamp(?/1000)  ,?, ?, "+
					            "?, ?, ?, "+
					            "?, ?, ?, "+
					            "to_timestamp(?/1000), ?, ?, ?, ?, "+
					            "?, ?, to_timestamp(?/1000), "+
					            "to_timestamp(?/1000), ?, ?, ?, "+
					            "?, ?, ?, ?, ?, "+
					            "?, ?, ?, ?, ?, ?, "+
					            "to_timestamp(?/1000), ?, ?, ?, "+
					            "?, ?, ?, ?)";	
            	
            	pstmt = con.prepareStatement(query);

            	
	            pstmt.setString(1, (String) jo.get("copyright"));
	            pstmt.setString(2, (String) jo.get("subject_codes"));
	            pstmt.setString(3, (String) jo.get("art"));
	            pstmt.setLong(4, Long.parseLong(""+ jo.get("modification_datetime")));
	            pstmt.setString(5, (String) jo.get("body"));
	            pstmt.setString(6, (String) jo.get("company_codes_occur_ticker_exchange"));
	            pstmt.setString(7, (String) jo.get("company_codes_occur"));
	            pstmt.setString(8, (String) jo.get("company_codes_about"));
	            pstmt.setString(9, (String) jo.get("company_codes_lineage"));
	            pstmt.setString(10, (String) jo.get("company_codes_ticker_exchange"));
	            pstmt.setString(11, (String) jo.get("snippet"));
	            pstmt.setString(12, (String) jo.get("company_codes_relevance_ticker_exchange"));
	            pstmt.setLong(13, Long.parseLong(""+ jo.get("publication_date")));
	            pstmt.setString(14, (String) jo.get("market_index_codes"));
	            pstmt.setString(15, (String) jo.get("credit"));
	            pstmt.setString(16, (String) jo.get("section"));
	            pstmt.setString(17, (String) jo.get("company_codes_association_ticker_exchange"));
	            pstmt.setString(18, (String) jo.get("region_of_origin"));
	            pstmt.setString(19, (String) jo.get("company_codes_lineage_ticker_exchange"));
	            pstmt.setLong(20, Long.parseLong(""+ jo.get("ingestion_datetime")));
	            pstmt.setLong(21, Long.parseLong(""+ jo.get("modification_date")));
	            pstmt.setString(22, (String) jo.get("source_name"));
	            pstmt.setString(23, (String) jo.get("language_code"));
	            pstmt.setString(24, (String) jo.get("region_codes"));
	            pstmt.setString(25, (String) jo.get("company_codes_association"));
	            pstmt.setString(26, (String) jo.get("person_codes"));
	            pstmt.setString(27, (String) jo.get("byline"));
	            pstmt.setString(28, (String) jo.get("dateline"));
	            pstmt.setString(29, (String) jo.get("company_codes_relevance"));
	            pstmt.setString(30, (String) jo.get("source_code"));
	            pstmt.setString(31, (String) jo.get("an"));
	            pstmt.setString(32, (String) jo.get("word_count"));
	            pstmt.setString(33, (String) jo.get("company_codes"));
	            pstmt.setString(34, (String) jo.get("industy_codes"));
	            pstmt.setString(35, (String) jo.get("title"));
	            pstmt.setLong(36, Long.parseLong(""+ jo.get("publication_datetime")));
	            pstmt.setString(37, (String) jo.get("publisher_name"));
	            pstmt.setString(38, (String) jo.get("action"));
	            pstmt.setString(39, (String) jo.get("document_type"));
	            pstmt.setTimestamp(40, new java.sql.Timestamp(0) );
	            pstmt.setTimestamp(41,  new java.sql.Timestamp(0));
	            pstmt.setString(42, (String) jo.get("currency_codes"));
	            pstmt.setString(43, (String) jo.get("company_codes_about_ticker_exchange"));
	            
	            boolean result = pstmt.execute(); //false 면 정상 실행.
	            rslt=result;
            
           
 
        } catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
        	System.out.println("SQLState: " + ex.getSQLState());
 
        } finally {
            try {
                if (pstmt != null) {
                	pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
                
                
            } catch (SQLException ex) {
            	System.out.println("SQLException: " + ex.getMessage());
            	System.out.println("SQLState: " + ex.getSQLState());
            }
        }
	
        
		
		return rslt;
	}

}
