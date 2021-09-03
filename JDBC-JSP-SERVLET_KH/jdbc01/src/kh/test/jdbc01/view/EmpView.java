package kh.test.jdbc01.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.test.jdbc01.controller.EmpController;
import kh.test.jdbc01.model.EmpVo;

public class EmpView {
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		boolean loop = true;
		
		while(loop) {
		System.out.println("1. 사원 정보 전체 출력");
		System.out.println("2. 사원 1명 추가");
		System.out.println("3. 사원 1명 삭제 (사원번호를 입력하고 해당 사원 삭제)");
		System.out.println("4. 사원 정보 수정 (사원 번호를 입력하고 해당하는 사원의 sal을 100증가)");
		System.out.println("5. 이름으로 사원 정보 검색");
		System.out.println("6. 사번으로 사원 정보 검색");
		System.out.println("0. 끝내기");
		
		int inputMenu = sc.nextInt();
		
		switch (inputMenu) {
		case 0:
			loop = false;
			break;
		
		case 1:
			// 1. 사원 정보 전체 출력 select
			displayList();
			break;
			
		case 2:
			// 2. 사원 1명 추가 insert
			inputEmp();
			break;
			
		case 3:
			// 3. 사원 1명 삭제 (사원번호를 입력하고 해당 사원 삭제) delete
			deleteEmp();
			break;
			
		case 4:
			// 4. 사원 정보 수정 (사원 번호를 입력하고 해당하는 사원의 sal을 100증가) update
			updateEmp();
			break;
			
		case 5:
			// 5. 이름으로 사원 정보 검색
			displayEmployee();
			break;
			
		case 6:
			// 5. 이름으로 사원 정보 검색
			displayEmployeeEmpno();
			break;

		default:
			System.out.println("메뉴 번호만 눌러주세요.");
			break;
			}
		}
	}
//========================================================================================================================
	public void displayList() {
		ArrayList<EmpVo> voList = new EmpController().selectEmpList();
		// 예쁘게 출력해보자(html이 없으니까 이렇게 하는거)
		System.out.println("=======================");
		System.out.println("사원 총 : " + voList.size() + "명");
		System.out.println("=======================");
		
		int totalSal = 0;
		for (EmpVo vo : voList) {
			System.out.println(vo.getEname() + " : " + vo.getJob() + " 업무중" + ", " + vo.getDname() + ", "	+ "사번 : " + vo.getEmpno() + ", "+ "급여 : " + vo.getSal());
			totalSal += vo.getSal();
		}
		System.out.println("=======================");
		System.out.println("사원 총 급여 : " + totalSal);
		System.out.println("=======================");
	}
	
	
//========================================================================================================================	
	public void inputEmp() {
		EmpVo vo = new EmpVo();
		System.out.print("사원명을 입력해주세요 : ");
		String name = sc.next();
		
		// 문자열로 받은 후 변환
		System.out.print("사번을 입력해주세요 : ");
		String empnoStr = sc.next();
		int empno = 0;
		
		System.out.print("부서번호를 입력해주세요 : ");
		String deptnoStr = sc.next();
		int deptno = 0;
		
		try {
			empno = Integer.parseInt(empnoStr);
			deptno = Integer.parseInt(deptnoStr); 
		} catch (Exception e) {
			System.out.println("사번과 부서번호는 숫자만 가능합니다.");
			e.printStackTrace();
			System.out.println("프로그램을 다시 수행해 주세요");
			return;
		}
		vo.setEname(name);
		vo.setEmpno(empno);
		vo.setDeptno(deptno);
		int result = new EmpController().inputEmp(vo);
		
		if (result > 0) {
			System.out.println("추가 성공");
		} else {
			System.out.println("추가 실패");
		}
	}
//========================================================================================================================
	public void deleteEmp() {
		System.out.print("삭제할 사원의 사번 입력해주세요:");
		String empnoStr = sc.next();
		sc.nextLine();   // enter key 까지 읽어들여 버퍼를 깨끗하게 만들어 줌.
		int empno = 0;
		try {
			empno = Integer.parseInt(empnoStr);
		} catch (Exception e) {
			System.out.println("사번과 숫자만 가능합니다.");
			e.printStackTrace();
			System.out.println("프로그램을 다시 수행해 주세요.");
			return;
		}
		EmpVo vo = new EmpVo();
		vo.setEmpno(empno);
		
		int result = new EmpController().deleteEmp(vo);
		
		if (result == 0) {
			System.out.println("사원을 찾지 못하여 삭제하지 못했습니다.");
		} else if(result == 1) {
			System.out.println(empno + " 사원을 삭제하였습니다.");
		} else {   // 2,3,4,5,6,7
			System.out.println(empno + " 사원이 여러명이 있었습니다. " + result +"명 삭제되었습니다.");
		}
	}
//========================================================================================================================
	public void updateEmp() {
		EmpVo vo = new EmpVo();
		System.out.print("sal를 100증가할 사원의 사번 입력해주세요:");
		String empnoStr = sc.next();
		sc.nextLine(); // enter key 까지 읽어들여 버퍼를 깨끗하게 만들어 준다.
		int empno = 0;
		try {
			empno = Integer.parseInt(empnoStr);
		} catch (Exception e) {
			System.out.println("사번은 숫자만 가능합니다.");
			e.printStackTrace();
			System.out.println("프로그램을 다시 수행해 주세요.");
			return;
		}
		vo.setEmpno(empno);
		
		int result = new EmpController().updateEmp(vo);
		
		if (result == 0) {
			System.out.println("조회하신 사원이 없습니다.");
		} else if (result == 1) {
			System.out.println(empno + " 사원을 수정하였습니다.");
		} else {
			System.out.println(empno + " 사원이 여러명입니다.");
			System.out.println(result + "명의 사원 정보를 수정했습니다.");
		}
	}
//========================================================================================================================
	// 사원명으로 검색하여 검색 결과 보여줌
	public void displayEmployee() {
		System.out.print("사원명을 입력하세요 : ");
		String ename = sc.next();
		
		EmpVo vo = new EmpVo();
		
		vo.setEname(ename);
		
		EmpVo inforEmp = new EmpController().displayEmployee(vo);
		
		System.out.println("사번 : " + inforEmp.getEmpno());
		System.out.println("이름 : " + inforEmp.getEname());
		System.out.println("부서 번호 : " + inforEmp.getDeptno());
		System.out.println("직무 : " + inforEmp.getJob());
		System.out.println("급여 : " + inforEmp.getSal());
	}
//========================================================================================================================
	// 사원번호로 검색하여 검색 결과 보여줌
	public void displayEmployeeEmpno() {
		System.out.print("사번을 입력하세요 : ");
		String empnoStr = sc.next();
		int empno = 0;
		
		try {
			empno = Integer.parseInt(empnoStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EmpVo vo = new EmpVo();
		vo.setEmpno(empno);
		
		EmpVo inforEmp = new EmpController().displayEmployeeEmpno(vo);
		
		if (inforEmp != null) {
			System.out.println("사번 : " + inforEmp.getEmpno());
			System.out.println("이름 : " + inforEmp.getEname());
			System.out.println("부서 번호 : " + inforEmp.getDeptno());
			System.out.println("직무 : " + inforEmp.getJob());
			System.out.println("급여 : " + inforEmp.getSal());
		} else {
			System.out.println("해당 사원이 없습니다.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
