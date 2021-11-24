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

class CheckThread2 implements Runnable {

	List<String> xmlList;
	String type;
	CheckThread2(List<String> xmlList,String type) {
		this.xmlList = xmlList;
		this.type = type;
	}

	@Override
	public void run() {
		try {
			
			if(type.equals("SPEC")){
				insertSpec(xmlList);
			}else if(type.equals("CLAIM")){
				insertClaim(xmlList);
			}else if(type.equals("FIGURE")){
				insertFigure(xmlList);
			}else if(type.equals("DESC")){
				insertDescription(xmlList);// inventor
			}else if(type.equals("IPC")){
				insertIPC(xmlList);
			}else if(type.equals("PRIOR")){
				insertPriority(xmlList);
			}else if(type.equals("REF")){
				insertReferences(xmlList);
			}else {
				System.out.println("ERROR!!!!!!!!!!!!!!!!!!!!");
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public static void insertReferences(List<String> xmlList) {
		for (int i = 0; i < xmlList.size(); i++) {
			try {
				File input = new File(xmlList.get(i));
				Document doc = Jsoup.parse(input, "EUC-JP", "http://example.com/");

				String doc_id = "";
				Elements eles = doc.select("publication-reference");
				for (Element ele : eles) {
					Elements subnode = ele.select("doc-number");
					doc_id = subnode.text();
				}

				String applno = "";
				Elements eles1 = doc.select("application-reference");
				for (Element ele1 : eles1) {
					Elements subnode = ele1.select("doc-number");
					applno = subnode.text();
				}

				String ref_doc = "";
				Elements eles2 = doc.select("references-cited");
				for (Element ele2 : eles2) {
					int ref_num = 0;
					Elements subnode = ele2.select("patcit");
					for (Element subEle : subnode) {
						Elements subsubnode = subEle.select("text");
						ref_doc = subsubnode.text();
						ref_num++;
						insertReferencesData(doc_id, applno, ref_num + "", ref_doc);
					}

				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertReferencesData(String doc_id, String applno, String ref_num, String ref_doc)
			throws SQLException {

		String url = "jdbc:oracle:thin:@localhost:1521:dbpasp1";
		Connection con = null;
		// Statement stmt = null;

		Properties props = new Properties();
		props.put("user", "SMART_JP");
		props.put("password", "SMART_JP");

		try {
			con = DriverManager.getConnection(url, props);
			con.setAutoCommit(false);

			String strQuery = "INSERT INTO JP_REFERENCES (DOCID,APPLNO,REF_NUM,REF_DOCID) VALUES( ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(strQuery);

			pstmt.setString(1, doc_id);
			pstmt.setString(2, applno);
			pstmt.setString(3, ref_num);
			pstmt.setString(4, ref_doc);
			int nRowCnt = pstmt.executeUpdate();
			pstmt.close();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception ignored) {

			}
		}
	}

	public static void insertPriority(List<String> xmlList) {
		for (int i = 0; i < xmlList.size(); i++) {
			try {
				File input = new File(xmlList.get(i));
				Document doc = Jsoup.parse(input, "EUC-JP", "http://example.com/");

				String doc_id = "";
				Elements eles = doc.select("publication-reference");
				for (Element ele : eles) {
					Elements subnode = ele.select("doc-number");
					doc_id = subnode.text();
				}

				String applno = "";
				Elements eles1 = doc.select("application-reference");
				for (Element ele1 : eles1) {
					Elements subnode = ele1.select("doc-number");
					applno = subnode.text();
				}

				//////////////////////////////////

				String county = "";
				String pri_doc_id = "";
				String pri_doc_date = "";
				int cnt = 0;
				Elements eles2 = doc.select("priority-claim");
				for (Element ele2 : eles2) {
					cnt++;
					Elements subnode = ele2.select("country");
					county = subnode.text();

					Elements subnode2 = ele2.select("doc-number");
					pri_doc_id = subnode2.text();

					Elements subnode3 = ele2.select("date");
					pri_doc_date = subnode3.text();

					// System.out.println("doc_id :: "+doc_id +" applno ::
					// "+applno+" type :: "+type+" text :: "+text);
					insertPriorityData(doc_id, applno, cnt + "", county, pri_doc_id, pri_doc_date);
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertPriorityData(String doc_id, String applno, String num, String country, String doc,
			String date) throws SQLException {

		String url = "jdbc:oracle:thin:@localhost:1521:dbpasp1";
		Connection con = null;
		// Statement stmt = null;

		Properties props = new Properties();
		props.put("user", "SMART_JP");
		props.put("password", "SMART_JP");

		try {
			con = DriverManager.getConnection(url, props);
			con.setAutoCommit(false);
			String strQuery = "INSERT INTO JP_PRIORITY(DOCID,APPLNO,PRIORITY_NUM,PRIORITY_COUNTRY,PRIORITY_DOCID,PRIORITY_DATE)"
					+ "" + " VALUES(  ?,?, ?, ?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(strQuery);

			pstmt.setString(1, doc_id);
			pstmt.setString(2, applno);
			pstmt.setString(3, num);
			pstmt.setString(4, country);
			pstmt.setString(5, doc);
			pstmt.setString(6, date);
			int nRowCnt = pstmt.executeUpdate();
			pstmt.close();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception ignored) {

			}
		}
	}

	public static void insertIPC(List<String> xmlList) {
		for (int i = 0; i < xmlList.size(); i++) {
			System.out.println(i + "inser ipc" + xmlList.get(i));
			try {
				File input = new File(xmlList.get(i));
				Document doc = Jsoup.parse(input, "EUC-JP", "http://example.com/");

				String doc_id = "";
				Elements eles = doc.select("publication-reference");
				for (Element ele : eles) {
					Elements subnode = ele.select("doc-number");
					doc_id = subnode.text();
				}

				String applno = "";
				Elements eles1 = doc.select("application-reference");
				for (Element ele1 : eles1) {
					Elements subnode = ele1.select("doc-number");
					applno = subnode.text();
				}

				String main_yn = "";
				Elements eles2 = doc.select("classification-national");
				for (Element ele2 : eles2) {
					Elements subnode = ele2.select("main-clsf");
					main_yn = subnode.text();

				}
				String ipc_cd = "";
				ipc_cd = main_yn;
				insertIPCData(doc_id, applno, "Y", ipc_cd);

				Elements eles3 = doc.select("classification-national");
				for (Element ele3 : eles3) {
					Elements subnode = ele3.select("further-clsf");
					// ipc_cd=subnode.text();
					List list = subnode.eachText();
					for (int j = 0; j < list.size(); j++) {
						ipc_cd = list.get(j) + "";
						// String main_yn_prm="";
						// if(main_yn.equals(ipc_cd)){
						// main_yn_prm="Y";
						// }else{
						// main_yn_prm="N";
						// }
						System.out.println("doc_id :: " + doc_id + "     applno :: " + applno
								+ "     main_yn :: N    \n ipc_cd :: " + ipc_cd);
						insertIPCData(doc_id, applno, "N", ipc_cd);

					}

				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertIPCData(String doc_id, String applno, String main_yn, String ipc_cd) throws SQLException {

		String url = "jdbc:oracle:thin:@localhost:1521:dbpasp1";
		Connection con = null;
		// Statement stmt = null;

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
		} finally {
			try {
				con.close();
			} catch (Exception ignored) {

			}
		}
	}

	public static void insertDescription(List<String> xmlList) {
		for (int i = 0; i < xmlList.size(); i++) {
			System.out.println(i + "insert description" + xmlList.get(i));
			try {

				File input = new File(xmlList.get(i));
				Document doc = Jsoup.parse(input, "EUC-JP", "http://example.com/");

				String doc_id = "";
				Elements eles = doc.select("publication-reference");
				for (Element ele : eles) {
					Elements subnode = ele.select("doc-number");
					doc_id = subnode.text();
				}

				String applno = "";
				Elements eles1 = doc.select("application-reference");
				for (Element ele1 : eles1) {
					Elements subnode = ele1.select("doc-number");
					applno = subnode.text();
				}

				//////////////////////////////////

				String text = "";
				String type = "";
				// (1:기술분야,2:배경기술,3:발명의개요,과제를 해결하기위한 수단,도면의 간단한 설명,발명을 실시하기 위한
				// 형)
				// (1:����о�,2:�����,3:�߸��ǰ���,������ �ذ��ϱ����� ����,������
				// ������ ����,�߸��� �ǽ��ϱ� ���� ��)
				Elements eles2 = doc.select("technical-field");
				type = "1";
				for (Element ele2 : eles2) {
					Elements subnode = ele2.select("p");
					text = subnode.text();
					System.out.println("doc_id :: " + doc_id + "     applno :: " + applno + "      type :: " + type
							+ "     text :: " + text);
					insertDescriptionData(doc_id, applno, type, text);
				}

				Elements eles3 = doc.select("background-art");
				type = "2";
				for (Element ele3 : eles3) {
					Elements subnode = ele3.select("p");
					text = subnode.text();
					System.out.println("doc_id :: " + doc_id + "     applno :: " + applno + "      type :: " + type
							+ "     text :: " + text);
					insertDescriptionData(doc_id, applno, type, text);
				}

				Elements eles4 = doc.select("summary-of-invention");
				type = "3";
				for (Element ele4 : eles4) {
					Elements subnode = ele4.select("p");
					text = subnode.text();
					System.out.println("doc_id :: " + doc_id + "     applno :: " + applno + "      type :: " + type
							+ "     text :: " + text);
					insertDescriptionData(doc_id, applno, type, text);
				}

				Elements eles5 = doc.select("description-of-drawings");
				type = "4";
				for (Element ele5 : eles5) {
					Elements subnode = ele5.select("p");
					text = subnode.text();
					System.out.println("doc_id :: " + doc_id + "     applno :: " + applno + "      type :: " + type
							+ "     text :: " + text);
					insertDescriptionData(doc_id, applno, type, text);
				}

			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertDescriptionData(String doc_id, String applno, String type, String text)
			throws SQLException {
		System.out.println("===============insert start===============");

		String url = "jdbc:oracle:thin:@localhost:1521:dbpasp1";
		Connection con = null;
		// Statement stmt = null;

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
			pstmt.setString(3, type);
			pstmt.setString(4, text);
			int nRowCnt = pstmt.executeUpdate();
			pstmt.close();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception ignored) {

			}
		}
		System.out.println("===============insert end===============");
	}

	public static void insertFigure(List<String> xmlList) {
		for (int i = 0; i < xmlList.size(); i++) {
			System.out.println(i + "inser figure" + xmlList.get(i));
			try {
				File input = new File(xmlList.get(i));
				Document doc = Jsoup.parse(input, "EUC-JP", "http://example.com/");

				String doc_id = "";
				Elements eles = doc.select("publication-reference");
				for (Element ele : eles) {
					Elements subnode = ele.select("doc-number");
					doc_id = subnode.text();
				}

				String applno = "";
				Elements eles1 = doc.select("application-reference");
				for (Element ele1 : eles1) {
					Elements subnode = ele1.select("doc-number");
					applno = subnode.text();
				}

				//////////////////////////////////

				int figure_cnt = 0;
				Elements eles2 = doc.select("figure");

				for (Element ele2 : eles2) {
					Elements subnode = ele2.select("img");
					// System.out.println("--------");
					// System.out.println(subnode.attr("id"));
					// System.out.println(subnode.attr("file"));
					// System.out.println(subnode.attr("he"));
					// System.out.println(subnode.attr("img-format"));
					// System.out.println(subnode.attr("wi"));
					// System.out.println(subnode.attr("img-content"));

					// figure=subnode.text();
					figure_cnt++;

				}
				insertFigureData(doc_id, applno, figure_cnt);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertFigureData(String doc_id, String applno, int figure_cnt) throws SQLException {
		// System.out.println("===============insert start===============");

		String url = "jdbc:oracle:thin:@localhost:1521:dbpasp1";
		Connection con = null;
		// Statement stmt = null;

		Properties props = new Properties();
		props.put("user", "SMART_JP");
		props.put("password", "SMART_JP");

		try {
			con = DriverManager.getConnection(url, props);
			con.setAutoCommit(false);

			String strQuery = "INSERT INTO JP_FIGURE (DOCID,APPLNO,COUNT) VALUES(  ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(strQuery);

			pstmt.setString(1, doc_id);
			pstmt.setString(2, applno);
			pstmt.setInt(3, figure_cnt);
			// pstmt.setString(4, ipc_cd);
			int nRowCnt = pstmt.executeUpdate();
			pstmt.close();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception ignored) {

			}
		}
		// System.out.println("===============insert end===============");
	}

	public static void insertClaim(List<String> xmlList) {
		for (int i = 0; i < xmlList.size(); i++) {
			System.out.println(i + "insert Claim" + xmlList.get(i));
			try {
				// String dir =
				// "D:\\JPO\\2020\\JPO_2020-001\\2020-001\\DOCUMENT\\B9\\0006586001\\0006586201\\0006586221\\";
				// String fileName = "0006586226.xml";
				// System.out.println(xmlList.get(i));
				File input = new File(xmlList.get(i));
				Document doc = Jsoup.parse(input, "EUC-JP", "http://example.com/");

				String doc_id = "";
				Elements eles = doc.select("publication-reference");
				for (Element ele : eles) {
					Elements subnode = ele.select("doc-number");
					doc_id = subnode.text();
				}

				String applno = "";
				Elements eles1 = doc.select("application-reference");
				for (Element ele1 : eles1) {
					Elements subnode = ele1.select("doc-number");
					applno = subnode.text();
				}
				// System.out.println("doc_id :: "+doc_id +" applno ::
				// "+applno);

				Elements eles2 = doc.select("claim");
				// List<String> list = new ArrayList();
				int claim_text_cnt = 0;
				for (Element ele2 : eles2) {
					Elements subnode = ele2.select("claim-text");
					// System.out.println(subnode.text());
					insertClaimData(doc_id, applno, ++claim_text_cnt, subnode.text());
					// list.add(subnode.text());
				}

				// System.out.println(list.get(0));
				// System.out.println(list.size()+"�� claimList");
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertClaimData(String doc_id, String applno, int claim_num, String claim__text)
			throws SQLException {
		// System.out.println("===============insert start===============");
		// String resource =
		// "D:/workspace/textParsing/bin/textParsing/prop/info.properties";
		// Properties properties = new Properties();

		String url = "jdbc:oracle:thin:@localhost:1521:dbpasp1";
		Connection con = null;
		// Statement stmt = null;

		Properties props = new Properties();
		props.put("user", "SMART_JP");
		props.put("password", "SMART_JP");

		// String[] data = string.split("��");

		try {
			con = DriverManager.getConnection(url, props);
			con.setAutoCommit(false);
			// stmt = con.createStatement();

			// String sqlStr1 = "INSERT INTO JP_CLAIM (DOCID,CLAIM_NUM,CLAIM2)
			// VALUES ('"+doc_id+"','"+claim_num +"','"+claim__text +"')";
			// System.out.println(sqlStr1);
			// for(int i=0 ; i<data.length ; i++){
			// sqlStr1 += data[i] + ", ";
			// if(i == data.length){
			// sqlStr1 += data[i];
			// }
			// }
			// sqlStr1+= "')";
			// ResultSet rs1 = stmt.executeQuery(sqlStr1);

			// stmt.close();

			//////////////////// ����

			String strQuery = "INSERT INTO JP_CLAIM (DOCID,APPLNO,CLAIM_NUM,CLAIM) VALUES( ?, ?, ?,  EMPTY_CLOB() )";
			PreparedStatement pstmt = con.prepareStatement(strQuery);

			pstmt.setString(1, doc_id);
			pstmt.setString(2, applno);
			pstmt.setString(3, claim_num + "");
			int nRowCnt = pstmt.executeUpdate();
			pstmt.close();

			if (nRowCnt == 1) {
				// Make Select Query & Row Lock
				strQuery = "SELECT CLAIM FROM JP_CLAIM WHERE DOCID = ? AND CLAIM_NUM = ? FOR UPDATE";
				pstmt = con.prepareStatement(strQuery);
				pstmt.setString(1, doc_id);
				pstmt.setString(2, claim_num + "");
				ResultSet rs = pstmt.executeQuery();

				// Write CLOB Data
				String strCLOB = claim__text;
				Boolean flag1 = false;
				Boolean flag2 = false;
				if (strCLOB.toString().contains("۰��")) {
					flag1 = true;
					// ��� CLAIM_TYPE=1

				} else if (strCLOB.toString().contains("ڪ��")) {
					flag2 = true;
					// ��ǰ CLAIM_TYPE=2

				}
				if (rs.next()) {
					CLOB clob = ((OracleResultSet) rs).getCLOB("CLAIM");

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
				String claim_type = "3";
				if (flag1) {
					claim_type = "1";
				} else if (flag2) {
					claim_type = "2";
				} else {
					claim_type = "3";
				}
				// System.out.println(claim_type+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				pstmt2 = con.prepareStatement(uptQuery);
				pstmt2.setString(1, claim_type);
				pstmt2.setString(2, doc_id);
				pstmt2.setString(3, claim_num + "");
				int res = pstmt2.executeUpdate();
				pstmt2.close();

				// Commit
				con.commit();
				con.setAutoCommit(true);
				rs.close();
			}

			// pstmt.setString(3, "XXXXX");

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception ignored) {

			}
		}
		// System.out.println("===============insert end===============");
	}

	public static void insertSpec(List<String> xmlList) {
		for (int i = 0; i < xmlList.size(); i++) {
			System.out.println(i + "��°���� Spec" + xmlList.get(i));
			// System.out.println(xmlList.get(i));
			try {
				File input = new File(xmlList.get(i));
				Document doc = Jsoup.parse(input, "EUC-JP", "http://example.com/");

				String doc_id = "";// RGSTNO,DOCID
				String kind = "";
				String rgst_date = "";
				Elements eles = doc.select("publication-reference");
				for (Element ele : eles) {
					Elements subnode = ele.select("doc-number");
					doc_id = subnode.text();

					Elements subnode_2 = ele.select("kind");
					kind = subnode_2.text();
					kind = kind.split("[(]")[1].split("[)]")[0];

					Elements subnode_3 = ele.select("date");
					rgst_date = subnode_3.text();

				}

				String applno = "";
				String appl_date = "";
				Elements eles1 = doc.select("application-reference");
				for (Element ele1 : eles1) {
					Elements subnode = ele1.select("doc-number");
					applno = subnode.text();
					Elements subnode_2 = ele1.select("date");
					appl_date = subnode_2.text();
				}

				//////////////////////////////////

				String invent_ttl = "";
				int cnt = 0;
				Elements eles2 = doc.select("invention-title");
				invent_ttl = eles2.text();
				String opno = "";
				String op_date = "";
				// jp:previously-published-document
				Elements eles3 = doc.select("jp|previously-published-document");
				for (Element ele1 : eles3) {
					Elements subnode = ele1.select("doc-number");
					opno = subnode.text();
					Elements subnode_2 = ele1.select("date");
					op_date = subnode_2.text();
				}

				insertSpecData(doc_id, applno, kind, rgst_date, appl_date, invent_ttl, opno, op_date);

			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertSpecData(String doc_id, String applno, String kind, String rgst_date, String appl_date,
			String invent_ttl, String opno, String op_date) throws SQLException {
		// System.out.println("===============insert start===============");

		String url = "jdbc:oracle:thin:@localhost:1521:dbpasp1";
		Connection con = null;
		// Statement stmt = null;

		Properties props = new Properties();
		props.put("user", "SMART_JP");
		props.put("password", "SMART_JP");

		try {
			con = DriverManager.getConnection(url, props);
			con.setAutoCommit(false);
			String strQuery = "INSERT INTO JP_SPEC(APPLNO,APPL_DATE,DOCID,DOCKIND,RGSTNO,RGST_DATE,INVENT_TTL,OPNNO,OPN_DATE)"
					+ "" + " VALUES(  ?,?, ?, ?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(strQuery);

			pstmt.setString(1, applno);
			pstmt.setString(2, appl_date);
			pstmt.setString(3, doc_id);
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
		} finally {
			try {
				con.close();
			} catch (Exception ignored) {

			}
		}
		// System.out.println("===============insert end===============");
	}
}

public class insertDataThread {
	static int xmlCnt = 0;

	public static void main(String[] args) {
		// List<String> xmlList1 = new ArrayList();
		// xmlList1 = searchDirList("D:/JPO/2013/",xmlList1);
		// List<String> xmlList2 = new ArrayList();
		// xmlList2 = searchDirList("D:/JPO/2006_2/",xmlList2);
		List<String> xmlList3 = new ArrayList();
		xmlList3 = searchDirList("D:/JPO/2009_3", xmlList3);

		
		Runnable d1 = new CheckThread2(xmlList3,"SPEC");
		Runnable d2 = new CheckThread2(xmlList3,"CLAIM");
		Runnable d3 = new CheckThread2(xmlList3,"FIGURE");
		Runnable d4 = new CheckThread2(xmlList3,"DESC");
		Runnable d5 = new CheckThread2(xmlList3,"IPC");
		Runnable d6 = new CheckThread2(xmlList3,"PRIOR");
		Runnable d7 = new CheckThread2(xmlList3,"REF");
		

		Thread thread1 = new Thread(d1);
		Thread thread2 = new Thread(d2);
		Thread thread3 = new Thread(d3);
		Thread thread4 = new Thread(d4);
		Thread thread5 = new Thread(d5);
		Thread thread6 = new Thread(d6);
		Thread thread7 = new Thread(d7);
		
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		
	}

	public static List<String> searchDirList(String source, List<String> xmlList) {
		File dir = new File(source);
		File[] fileList = dir.listFiles();

		try {
			for (int i = 0; i < fileList.length; i++) {

				File file = fileList[i];
				if (file.isFile()) {
					String fileName = file.getName();
					int pos = fileName.lastIndexOf(".");
					String ext = fileName.substring(pos + 1, fileName.length());
					if (ext.equals("xml")) {
						xmlCnt++;
						System.out.println(xmlCnt);
						xmlList.add(file.getCanonicalPath());
					}
				} else if (file.isDirectory()) {
					xmlList = searchDirList(file.getCanonicalPath().toString(), xmlList);
				}
			}

		} catch (IOException e) {

		}
		return xmlList;
		// System.out.println(xmlList.size()+"�� ��ȸ.");
	}
}
