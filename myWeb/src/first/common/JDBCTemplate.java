package first.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// JDBCTemplate의 모양은 한 가지로 정의할 수 없다.
// 즉, 아래의 코드가 FM이 아니라는 얘기이다. (대부분의 얘기를 하는 것이다.)
public class JDBCTemplate {
	
	public JDBCTemplate() {}
	
	public static Connection getConnection() {
		// static 메소드에 있는 모든 변수는 static으로 생성 or 동적할당
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			if (conn == null) {
				System.out.println("연결 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
//=======================================================================================================================
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//=======================================================================================================================	
	// PreparedStatement도 여기에 넣을 수 있다.
	public static void close(Statement stmt) {
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//=======================================================================================================================
	public static void close(ResultSet rest) {
		try {
			if (rest != null && !rest.isClosed()) {
				rest.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//=======================================================================================================================
//	public static void commit(Connection conn) {
//		
//	}
//=======================================================================================================================
//	public static void commit(Connection conn) {
//		
//	}
}