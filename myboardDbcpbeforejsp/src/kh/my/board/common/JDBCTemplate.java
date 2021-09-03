package kh.my.board.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {
	private JDBCTemplate() {}
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/mylocaloracle");
			
			conn = ds.getConnection();
			
			if (conn != null) { System.out.println("2021 08 30 DBCP JNDI 연결성공"); }
			else { System.out.println("2021 08 30 DBCP JNDI 연결실패"); } 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
//			// 연결 여부가 궁금해서 넣는 코드
//			if (conn != null) System.out.println("연결성공");
//			else System.out.println("연결실패");
//		} catch (Exception e) {
//			System.out.println("연결실패");
//			e.printStackTrace();
//		}
		// 애초에 AutoCommit 설정을 Connection을 만들때 사용할 수도 있다.
		// setAutoCommit(conn, false);
		// 근데 이게 더 오리혀 더 번거롭다.
		return conn;
	}
//============================================================================================================
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
	// PreparedStatement도 여기에 넣을 수 있다. (by 다형성)
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
			// 위의 과정을 거치지 않으면 널포인트Exception을 발생시킨다.
				rest.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//============================================================================================================
	//commit과 rollBack 또한 Connection을 이용한다.
	public static void commit(Connection conn) {
		try { // DB 에서 일어나는 트랜잭션 커밋 처리
			if (conn != null && !conn.isClosed())
				conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//============================================================================================================
	public static void rollBack(Connection conn) {
		try { // DB 에서 일어나는 트랜잭션 커밋 처리
			if (conn != null && !conn.isClosed())
				conn.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//============================================================================================================
	public static void setAutoCommit(Connection conn, boolean onOff) {
		try {
			conn.setAutoCommit(onOff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
