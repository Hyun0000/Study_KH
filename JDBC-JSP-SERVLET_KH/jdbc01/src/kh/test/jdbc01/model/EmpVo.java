package kh.test.jdbc01.model;

import java.sql.Date;

public class EmpVo {
	// 필드 생성 tip
	// SQL Developer에서 (desc emp;) 쿼리문으로 해당 테이블의 디스크립션을 복사한 후
	// 아래와 같이 그대로 붙여넣는다.
	
	//	이름       널?       유형           
	//-------- -------- ------------ 
	//	EMPNO    NOT NULL NUMBER       
	//	ENAME             VARCHAR2(10) 
	//	JOB               VARCHAR2(9)  
	//	MGR               NUMBER       
	//	HIREDATE          DATE         
	//	SAL               NUMBER       
	//	COMM              NUMBER       
	//	DEPTNO            NUMBER
	
	// DB에서 table 형태로 넘어온 결과물을 java가 쓸 수 있는 형태로 잡아줘야하는데
	// 그 파일이 바로 vo 파일이다.(아래의 변수들)
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate; // java.sql을 import한다.
	private int sal;
	private int comm;
	private int deptno;
	private String dname;
	
	public EmpVo() {
		// TODO Auto-generated constructor stub
	}

	// toString을 안 쓰면 해쉬코드가 보인다.
	@Override
	public String toString() {
		return "EmpVo [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hiredate=" + hiredate
				+ ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + ", dname=" + dname + "]";
	}
	
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}
	
}
