package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mysqlConnection {
	
	static ResultSet rs = null;
	static Connection con = null;
	static Statement st = null;
	
	public static void main(String args[]) throws SQLException {
        //1. E_KNA SELECT
		String selectRslt=selectE_KNA();
		if(selectRslt.equals("1")){
			//2. KNA UPDATE / INSERT
			Boolean insertRslt = insert_KNA();
			if(!insertRslt){
				if (rs != null) {
					rs.close();
				}
			}
		}
	}
	
	public static Boolean insert_KNA(){
		System.out.println("insertKNA START!!!-------------------------------------------------");
		
		Boolean rslt=false;
		
        PreparedStatement pstmt=null;
    
        String url = "jdbc:postgresql://58.149.174.155:5433/kna";
        String user = "kna";
        String password = "kna123";
 
        try {
            con = DriverManager.getConnection(url, user, password);
            int cnt=0;
            while (rs.next()) {
            	cnt++;
            	System.out.println(cnt + "번째 ::반복분 rs.next()");
            	
            	String query="";
            	query = "INSERT INTO pdsm ("+
            			 "num,"+ 
            			 "pds_num,"+
            			 "user_id,"+
            			 "title,"+
            			 "writer,"+
            			 "email,"+
            			 "memo,"+
            			 "count,"+
            			 "ip,"+
            			 "in_date,"+
            			 "up_date"+
            	") VALUES ("+
            		"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"+
            	") "+
            	"ON CONFLICT (num) DO "+
            	"UPDATE SET "+
	            	"num=?,"+ 
	            	"pds_num=?,"+
		   			"user_id=?,"+
		   			"title=?,"+
		   			"writer=?,"+
		   			"email=?,"+
		   			"memo=?,"+
		   			"count=?,"+
		   			"ip=?,"+
		   			"in_date=?,"+
		   			"up_date=?";       	
            	
            	pstmt = con.prepareStatement(query);

	            pstmt.setInt(1, rs.getInt(1));
	            pstmt.setInt(2, rs.getInt(2));
	            pstmt.setString(3, rs.getString(3));
	            pstmt.setString(4, rs.getString(4));
	            pstmt.setString(5, rs.getString(5));
	            pstmt.setString(6, rs.getString(6));
	            pstmt.setString(7, rs.getString(7));
	            pstmt.setInt(8, rs.getInt(8));
	            pstmt.setString(9, rs.getString(9));
	            pstmt.setString(10, rs.getString(10));
	            pstmt.setString(11, rs.getString(11));
	            
	            pstmt.setInt(12, rs.getInt(1));
	            pstmt.setInt(13, rs.getInt(2));
	            pstmt.setString(14, rs.getString(3));
	            pstmt.setString(15, rs.getString(4));
	            pstmt.setString(16, rs.getString(5));
	            pstmt.setString(17, rs.getString(6));
	            pstmt.setString(18, rs.getString(7));
	            pstmt.setInt(19, rs.getInt(8));
	            pstmt.setString(20, rs.getString(9));
	            pstmt.setString(21, rs.getString(10));
	            pstmt.setString(22, rs.getString(11));
	            
	            boolean result = pstmt.execute(); //false 면 정상 실행.
	            rslt=result;
            }
            System.out.println(cnt +" 개 UPSERT 완료 !");
            
           
 
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
                if (rs != null) {
                	rs.close();
                }
                if (st != null) {
                    st.close();
                }
                
                System.out.println("insertKNA END!!!-------------------------------------------------");
            } catch (SQLException ex) {
            	System.out.println("SQLException: " + ex.getMessage());
            	System.out.println("SQLState: " + ex.getSQLState());
            }
        }
	
        
		
		return rslt;
	}
	

	
	
	public static String selectE_KNA() throws SQLException{
		System.out.println("selectE_KNA START!!!-------------------------------------------------");
		String rslt="0";
		
		try {
			
			String url = "jdbc:mysql://119.205.211.74";
	        String user = "ekna";
	        String password = "ekna1357";
	        
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			
			st.executeQuery("use ekna;");
			
			rs = st.executeQuery(
					"select num,pds_num,user_id,title,writer,email,memo,count,ip,in_date,up_date "
					+ " from pdsm "
					+ " where pds_num='68' order by b_top desc, in_date desc, num desc, replyno DESC, replyst ASC"
					+ " limit 5000");
			
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			if (con != null) {
                con.close();
            }
            if (rs != null) {
            	rs.close();
            }
            if (st != null) {
                st.close();
            }
			return rslt;
			
		} finally {
			System.out.println("selectE_KNA END!!!-------------------------------------------------");
			
        }
		
		rslt="1";
		return rslt;
		
	}
	
	
	
	public static void psqlTest(){
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;
 
    
        String url = "jdbc:postgresql://58.149.174.155:5433/kna";
        String user = "kna";
        String password = "kna123";
 
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");
 
            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
 
        } catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
        	System.out.println("SQLState: " + ex.getSQLState());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
 
            } catch (SQLException ex) {
            	System.out.println("SQLException: " + ex.getMessage());
            	System.out.println("SQLState: " + ex.getSQLState());
            }
        }
	}
	
	
}
