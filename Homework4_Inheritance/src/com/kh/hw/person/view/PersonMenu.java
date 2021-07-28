package com.kh.hw.person.view;

import java.util.Scanner;

import com.kh.hw.person.controller.PersonController;

public class PersonMenu {
	private Scanner sc = new Scanner(System.in);
	
	private PersonController pc = new PersonController();
	
	public void mainMenu() {
		System.out.println("학생은 최대 3명까지 저장할 수 있습니다.");
		System.out.println();
		System.out.println("사원은 최대 10명까지 저장할 수 있습니다");
		System.out.println();
		
		System.out.println("1. 학생 메뉴");
		System.out.println("2. 사원 메뉴");
		System.out.println("9. 끝내기");
		
		System.out.print("메뉴 번호 : ");
		int num = sc.nextInt();
//		sc.close();   클로즈 메소드 제대로
		
	}
	
	public void studentMenu() {
		
	}
	
	public void employeeMenu() {
		
	}
	
	public void insertStudent() {
		
	}
	
	public void printStudent() {
		
	}
	
	public void insertEmployee() {
		
	}
	
	public void printEmployee() {
		
	}
}
