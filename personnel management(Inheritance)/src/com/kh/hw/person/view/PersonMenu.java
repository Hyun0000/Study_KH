package com.kh.hw.person.view;

import java.util.Scanner;

import com.kh.hw.person.controller.PersonController;
import com.kh.hw.person.model.vo.Employee;
import com.kh.hw.person.model.vo.Student;

public class PersonMenu {
	private PersonController pc = new PersonController();
	private Scanner sc = new Scanner(System.in);

	public void mainMenu() {
		
		int[] personCount =  pc.personCount();
		System.out.println("학생은 최대 3명까지 저장할 수 있습니다.");
		System.out.println("현재 저장된 학생은 " + personCount[0] + "명입니다.");
		
		System.out.println("사원은 최대 10명까지 저장할 수 있습니다.");
		System.out.println("현재 저장된 사원은 " + personCount[2] + "명입니다.");
		
		System.out.println();

		boolean loop = true;
		
		while (loop) {
			System.out.println("1. 학생 메뉴");
			System.out.println("2. 직원 메뉴");
			System.out.println("9. 끝내기");
			System.out.print("메뉴 번호 : ");
			int num = sc.nextInt();
			
			switch (num) {
			case 1:
				studentMenu();
				break;

			case 2:
				employeeMenu();
				break;

			case 9:
				System.out.println("종료합니다.");
				System.exit(0);
				break;

			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
//========================================================================
	public void studentMenu() {
		int[] personCount =  pc.personCount();
		
		System.out.println();
		System.out.println("1. 학생 추가");
		System.out.println("2. 학생 보기");
		System.out.println("9. 메인으로");
		if (personCount[0] == personCount[1]) {
			System.out.println("학생을 담을 수 있는 공간이 꽉 찼기 때문에 학생 추가 메뉴는 더 이상 활성화 되지 않습니다.");
			System.out.println();
		}
		System.out.print("메뉴 번호 : ");
		int num = sc.nextInt();
		
		switch (num) {
		case 1:
			if (personCount[0] == personCount[1]) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
				System.out.println();
				studentMenu();
				break;
			} else {
				insertStudent();
				break;
			}
		case 2:
			printStudent();
			studentMenu();
			break;
			
		case 9:
			System.out.println();
			System.out.println("메인으로 돌아갑니다.");
			System.out.println();
			mainMenu();
			break;

		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			studentMenu();
			break;
		}
	}
//========================================================================
	public void employeeMenu() {
		int[] count = pc.personCount();
		
		System.out.println();
		System.out.println("1. 사원 추가");
		System.out.println("2. 사원 보기");
		System.out.println("9. 메인으로");
		if(count[2] == count[3]) {
			System.out.println("사원을 담을 수 있는 공간이 꽉 찼기 때문에 사원 추가 메뉴는 더 이상 활성화 되지 않습니다.");
			System.out.println();
		}
		
		System.out.print("메뉴 번호 : ");
		int num = sc.nextInt();
		
		switch (num) {
		case 1:
			if(count[2] == count[3]) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				System.out.println();
				employeeMenu();
			}
			insertEmployee();
			break;
			
		case 2:
			printEmployee();
			employeeMenu();
			break;
			
		case 9:
			System.out.println();
			System.out.println("메인으로 돌아갑니다.");
			System.out.println();
			mainMenu();
			break;

		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			employeeMenu();
			break;
		}
	}
//========================================================================
	public void insertStudent() {
		
		System.out.println();
		
		int[] count = pc.personCount();
			
		if(count[0] < count[1]) {
		System.out.print("학생 이름 : ");
		String name = sc.next();
		
		System.out.print("학생 나이 : ");
		int age = sc.nextInt();
		
		System.out.print("학생 키 : ");
		double height = sc.nextDouble();
		
		System.out.print("학생 몸무게 : ");
		double weight = sc.nextDouble();
		
		System.out.print("학생 학년 : ");
		int grade = sc.nextInt();
		
		System.out.print("학생 전공 : ");
		String major = sc.next();
		
		pc.insertStudent(name, age, height, weight, grade, major);
		
		count = pc.personCount();
		
		if (count[0] == count[1]) {
			System.out.println("학생을 담을 수 있는 공간이 꽉 찼기 때문에 학생 추가를 종료하고 학생 메뉴로 돌아갑니다.");
			System.out.println();
			studentMenu();
			}
		}
		
		if(count[0] < count[1]) {
			System.out.print("그만하시려면 N(또는 n), 이어하시려면 아무 키나 누르세요 : ");
			String goStop = sc.next();
			if (goStop.equals("n") || goStop.equals("N")) {
				studentMenu();
			}
			insertStudent();
		} 
	}
//========================================================================
	public void printStudent() {
		Student[] printS = pc.printStudent();
		
		for (int i = 0; i < printS.length; i++) {
			if (printS[i] == null) {
				continue;
			} else {
				String studentInfo = printS[i].toString();
				System.out.println(studentInfo);
			}
		}
		System.out.println();
	}
//========================================================================
	public void insertEmployee() {
		
		System.out.println();
		
		int[] personCount = pc.personCount();
		
		System.out.print("사원 이름 : ");
		String name = sc.next();
		
		System.out.print("사원 나이 : ");
		int age = sc.nextInt();
		
		System.out.print("사원 키 : ");
		double height = sc.nextDouble();
		
		System.out.print("사원 몸무게 : ");
		double weight = sc.nextDouble();
		
		System.out.print("사원 급여 : ");
		int salary = sc.nextInt();
		
		System.out.print("사원 부서 : ");
		String dept = sc.next();
		
		pc.insertEmployee(name, age, height, weight, salary, dept);
		
		personCount = pc.personCount();
		
		if (personCount[2] == personCount[3]) {
			System.out.println("사원을 담을 수 있는 공간이 꽉 찼기 때문에 사원 추가를 종료하고 사원 메뉴로 돌아갑니다.");
			employeeMenu();
		}
		
		if (personCount[2] < personCount[3]) {
			System.out.print("그만하시려면 N(또는 n), 이어하시려면 아무 키나 누르세요 : ");
			String goStop = sc.next();
			if (goStop.equals("n") || goStop.equals("N")) {
				employeeMenu();
			}
		}
		insertEmployee();
	}
//========================================================================
	public void printEmployee() {
		Employee[] printE = pc.printEmployee();
		
		for (int i = 0; i < printE.length; i++) {
			if (printE[i] == null) {
				continue;
			} else {
				System.out.println(printE[i].toString());
			}
		}
		System.out.println();
	}
}