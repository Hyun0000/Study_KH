package kh.test.jdbc01.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {
	private String dr = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	// localhost 대신 (127.0.0.1)을 써도 된다.
	private String uid = "scott";
	private String pwd = "TIGER"; // 여기서도 대소문자 구분한다.
	
	public EmpDao() {
		
	}
//====================================================================================================================
	// select를 이용해 emp tabel의 리스트를 전부 읽어나오자.
	public ArrayList<EmpVo> selectLiset() {
		ArrayList<EmpVo> voList = null;
		// try-catch 실행 중 Exception을 만날 수도 있기에 '선언'은 여기서 한다.
		
		// Class.forName(); / getConnection(); / createStatement();
		// 위 3개의 method가 모두 Exception을 throws 하고 있으므로 각각 따로 try-catch문으로
		// 묶는건 지저분하니 하나로 묶어서 처리한다.
		try {
			// new로 생성하는게 아니다.
			// 1. Oracle과 연결 : Driver load
			// DriverManager에 해당 DBMS Driver 등록
			// Driver를 load 하는것이기에 해당 드라이버의 (패키지명.클래스명)이 들어가야한다.
			// Class.forName("연결할 DBMS의 Driver 패키지명.클래스명");
			Class.forName(dr);
			// 1-1. Oracle과 연결 : Connection (Driver load --> Connection)순서
			// 해당 Driver로 부터 Connection instance 획득
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			// getConnection(url, uid, pwd) --> 3개의 인자를 전달해서 DB에 연결(connect)하겠다는 의미
			// url : 위에서 정한 DBMS에 연결할 url (만약 외부pc라면 외부 pc의 해당 IP를 적으면 된다.)
			// 내부 pc라면 localhost 이용
			System.out.println("연결 성공");
			// 위의 두 과정으로 DB와의 연결 여부를 알 수 있다.
			
			// 2. Query을 날릴 수 있는 리소스(Statement) 만들기
			Statement stmt = conn.createStatement();
			// SQL문 작성
			
			// String sql = "select emp.*, dept.dname from emp join dept on emp.deptno=dept.deptno";
			// String sql = "select * from emp join dept USING(deptno)";
//			 String sql = "select * from emp join dept on emp.deptno = dept.deptno";
		 	 String sql = "select * from emp left outer join dept on emp.deptno = dept.deptno";
			// where 절을 사용하면 left outer join과 같은 방법이 따로 없다.
			// cf) Having은 Group By가 있어야 쓸 수 있다.
			
			// 3. 쿼리문을 실행하고 그 결과를 받아온다.
			// Statement method를 이용하며 SQL문 실행
			ResultSet rs = stmt.executeQuery(sql);
			// stmt.executeQuery(sql)  -->  위에서 연결한 Oracle로 Query를 날려준다.
			// 이떄 executeQuery()의 리턴 타입은 ResultSet 이다.
			// 즉, ResultSet 모양으로 결과를 토해준다는 건데 ResultSet의 모양은 테이블처럼 격자 모양이다.
			
			// ResultSet에 담긴 table을 하나씩 꺼내서 VO 모양에 담아보자.
			// (한 행 읽어서 VO에 넣고, 한 행 읽어서 VO에 넣고, 한 행 읽어서 VO에 넣고...)
			// 위의 과정을 반복해 계속 집어 넣는다.
			// 이때 VO에 집어넣는 행의 개수가 정확히 몇개인지 알 수 없다. 따라서 배열은 사용할 수 없다.
			// why? 배열은 초기에 사이즈를 정해줘야하며 선언한 이후에는 크기 변경이 불가능하기 때문이다.
			// 그레서 ArrayList를 쓴다. 정확히는 ArrayList<EmpVo>
			
			voList = new ArrayList<EmpVo>();
			// 이것을 반복문안에 넣어버리면 반복을 돌 때마다 초기화되므로 넣으면 안 된다.
			
			// 이때 ResultSet에서 데이터는 어떻게 가져오며 ResultSet을 이용한  반복문의 조건은 어떻게 할 것인가?
			while(rs.next()) {
			// rs.next() --> 한 행(한 줄)을 읽는다.
			// 한 행을 읽어 나올것이 없으면 false / 있으면 true & 읽어 나온 값을 가지고 있다.
			// 한 행 읽고, 다음거 읽고, 다음거 읽고...... 더 이상 읽을게 없으면 false를 반환
			EmpVo vo = new EmpVo();
			// VO의 필드를 채워 준 후 마지막에는 VO 자체를 ArrayList에 넣는다.
			// 컬럼을 지정하는 두 가지 방법
			// 1.컬럼 이름 / 2.컬럼의 위치 번호 (1부터 시작)
			
			// 규칙 : VO에 대한건 set / ResultSet에 대한건 get
			// 1. 컬럼 이름 이용
			vo.setEmpno(rs.getInt("empno")); // empno가 NUMBER 타입이므로 숫자 형태로 꺼내야한다. --> getInt()
			vo.setEname(rs.getString("ename")); // ename은 VARCHAR2 타입이므로 String 형태로 꺼내야한다. --> getString()
			vo.setJob(rs.getString("job"));
			vo.setComm(rs.getInt("comm")); // null은 '0'으로 처리된다.
			vo.setDeptno(rs.getInt("deptno"));
			vo.setMgr(rs.getInt("mgr"));
			vo.setHiredate(rs.getDate("hiredate"));
			vo.setSal(rs.getInt("sal"));
			vo.setDname(rs.getString("dname"));
			
			voList.add(vo);
//================================================================================================
			// 2. 컬럼 번호 이용
			// 컬럼 번호를 이용하면 SQL문을 어떻게 작성하느냐에 따라 컬럼의 숫자가 매번 달라지므로 이점을 유의해야 한다.
			
			// ex)
			// select * from emp join dept on emp.deptno = dept.deptno;
			// 이 SQL문은 deptno 컬럼이 2개가 나오고 8번, 9번에 위치한다.
			// 하지만
			// select * from emp join dept USING(deptno);
			// 이 SQL문은 deptno 컬럼이 1개만 나오며 1번에 위치한다.
			// 그로 인해 나머지 컬럼의 번호도 바뀐다. 
			
//			vo.setEmpno(rs.getInt(1));
//			vo.setEname(rs.getString(2));
//			vo.setJob(rs.getString(3)); //
//			vo.setComm(rs.getInt(7)); 
//			vo.setDeptno(rs.getInt(8));
//			vo.setMgr(rs.getInt("mgr"));
//			vo.setHiredate(rs.getDate(5));
//			vo.setSal(rs.getInt(6));
//			vo.setDname(rs.getString(10));
			
//			voList.add(vo);	
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
		// Catch문에서 Exception 종류를 나눌 필요 없이 (Exception e)로 다 묶어버려도 상관없다.
		return voList;
	}
//====================================================================================================================
	public void inputEmp(EmpVo vo) {
		System.out.println("DAO의 inputEmp메소드");
		System.out.println(vo.toString());
		
		try {
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("연결 성공");
			
			Statement stmt = conn.createStatement();

			// DriverManager --> Connection --> Statement까지는 동일하다.
			// 이 이후부터 달라진다.
			// 1명의 사원을 추가하는 SQL문 작성
			 String sql = "insert into emp (empno, ename) values (" + vo.getEmpno() + ", '" + vo.getEname() + "')";
			// String sql2 = "delete into emp (empno, ename) values (" + vo.getEmpno() + ", '" + vo.getEname() + "')";
			
			// insert를 비롯한 DML문을 실행할 때는 executeQuery()가 아니라 executeUpdate() method를 이용해야 한다.
			int result = stmt.executeUpdate(sql);
			// executeQuery -- > select문 ﻿Query를 날려줄 때 사용
			// executeUpdate --> insert, delete, update ﻿Query를 날려줄 떄 사용
			// 다른 이유 : insert, delete, update문을 실행하고 난 후에는 결과가 select처럼 표가 아니라
			// 숫자 형태가 나온다. --> '1'행이 삽입되었습니다. --> 여기서 숫자 1이 중요하다.
			// 즉, Return이 int형으로 된다는 것이다.
			
			// 이렇게 뿌리는 건 뷰에서 뿌려야 한다.(내일 옮긴다.)
			if (result > 0) {
				System.out.println("추가 성공");
			} else {
				System.out.println("추가 실패");
			}
			
		} catch (Exception e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
	}
//====================================================================================================================
	public int inputEmpPreparedStatement(EmpVo vo) {
		System.out.println("DAO의 inputEmp메소드");
		System.out.println(vo.toString());
		int result = 0;
		
		try {
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			System.out.println("연결 성공");
			
			// PreparedStatement에서는 SQL 문장을 아래와 같이 간단하게 작성한다.(따옴표를 걱정할 필요도 없다.)
			String sql = "insert into emp (empno, ename, deptno) values (?, ?, ?)";
			// PreparedStatement에서는 Statement 객체 생성 전에 먼저 SQL문을 작성한다.
			// 그래서 prepared이다.
			
			// PreparedStatement 특징
			// 1. 객체 생성을 할 때 SQL문을 넣어준채 생성한다.
			// 2. 넣어주고 싶은 값이 있는 위치에 ? 를 적는다. 그러면 해당 위치에 값이 채워진다.
			// ? : 위치 홀더
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 아래 2개는 순서를 바꿔도 상관 없다. by (1, 2)로 위치홀더의 위치를 정해줬기 때문이다.
			pstmt.setInt(1, vo.getEmpno());  // empno
			pstmt.setString(2, vo.getEname()); // ename
			pstmt.setInt(3, vo.getDeptno());  // deptno
			// pstmt.setInt(parameterIndex, x);
			// parameterIndex : 위치 홀더의 index(위치)를 적어준다.
			// 이때 'index는 1부터 시작'한다.
			// pstmt.setString(2, vo.getEname()) --> setString()을 사용하면 '작은 따옴표를 알아서 붙여'준다.
			
			// 이미 PreparedStatement 객체인 pstmt가 SQL문을 가진채 생성됐기 때문에
			// executeUpdate() method에 SQL문을 따로 인자로  안 넣어도 된다.
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
		return result;
	}
//====================================================================================================================
	public int deleteEmp(EmpVo vo) {
		int result = 0;
		try {
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			
			String sql = "delete from emp where empno = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getEmpno());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
//====================================================================================================================
	public int updateEmp(EmpVo vo) {
		int result = 0;
		// 생성은 try-catch 밖에서 해야한다.
		try {
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			
			String sql = "update emp set sal = (nvl(sal,0) + 100) where empno = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getEmpno());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("뭔가 잘 못 됐어요");
			e.printStackTrace();
			
		}
		return result;
	}
//====================================================================================================================
	// 사원명으로 검색하여 검색 결과 보여줌
	public EmpVo displayEmployee(EmpVo vo) {
		try {
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			
			Statement stmt = conn.createStatement();
			
			String sql = "select * from emp where ename = " + "'" + vo.getEname() + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
			vo.setEmpno(rs.getInt("empno"));
			vo.setEname(rs.getString("ename"));
			vo.setDeptno(rs.getInt("deptno"));
			vo.setJob(rs.getString("job"));
			vo.setSal(rs.getInt("sal"));
			}
			return vo;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
//====================================================================================================================	
	// 사원번호로 검색하여 검색 결과 보여줌
	public EmpVo displayEmployeeEmpno(EmpVo vo) {
		try {
			Class.forName(dr);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
			
			String sql = "Select * from emp where empno = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getEmpno());
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vo.setEmpno(rs.getInt("empno"));
				vo.setEname(rs.getString("ename"));
				vo.setDeptno(rs.getInt("deptno"));
				vo.setJob(rs.getString("job"));
				vo.setSal(rs.getInt("sal"));
			}
			return vo;
		} catch (Exception e) {
			System.out.println("뭔가 문제가 있군요");
			e.printStackTrace();
			return null;
		}
	}
}