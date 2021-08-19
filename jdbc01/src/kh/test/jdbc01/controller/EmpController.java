package kh.test.jdbc01.controller;

import java.util.ArrayList;

import kh.test.jdbc01.model.EmpDao;
import kh.test.jdbc01.model.EmpVo;

public class EmpController {
	
	public EmpController() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<EmpVo> selectEmpList() {
		return new EmpDao().selectLiset();
	}
	
	public int inputEmp(EmpVo vo) {
//		new EmpDao().inputEmp(vo);
		return new EmpDao().inputEmpPreparedStatement(vo);
	}
	
	public int deleteEmp(EmpVo vo) {
		return new EmpDao().deleteEmp(vo);
	}
	
	public int updateEmp(EmpVo vo) {
		return new EmpDao().updateEmp(vo);
	}
	
	// 사원명으로 검색하여 검색 결과 보여줌
	public EmpVo displayEmployee(EmpVo vo) {
		return new EmpDao().displayEmployee(vo);
	}
	
	// 사원번호로 검색하여 검색 결과 보여줌
	public EmpVo displayEmployeeEmpno(EmpVo vo) {
		return new EmpDao().displayEmployeeEmpno(vo); 
	}
	
}
