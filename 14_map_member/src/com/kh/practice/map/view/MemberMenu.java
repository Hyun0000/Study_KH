package com.kh.practice.map.view;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.kh.practice.map.controller.MemberController;
import com.kh.practice.map.model.vo.Member;

public class MemberMenu {
	private Scanner scanner = new Scanner(System.in);
	
	private MemberController mc = new MemberController();
	
	private void scannerClose() {
		try {
			if (scanner != null) {
				scanner.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mainMenu() {
		System.out.println("******* 메인 메뉴 *******");
		
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 같은 이름 회원 찾기");
		System.out.println("9. 종료");
		
		System.out.println("");
		System.out.print("메뉴 번호 입력 : ");
		int num = scanner.nextInt();
		
		switch (num) {
		case 1:
			joinMembership();
			break;
			
		case 2:
			logIn();
			break;
			
		case 3:
			sameName();
			break;
			
		case 9:
			System.out.println("프로그램 종료");
			scannerClose();
			break;

		default:
			System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			mainMenu();
			break;
		}
	}
	
	public void memberMenu() {
		// logIn() 실행 후 memberMenu() 실행
		// 종료 전까지 반복 실행
		System.out.println("******* 회원 메뉴 *******");
		System.out.println("1. 비밀번호 바꾸기");
		System.out.println("2. 이름 바꾸기");
		System.out.println("3. 로그아웃");
		
		System.out.println("");
		System.out.print("메뉴 번호 입력 : ");
		int num = scanner.nextInt();
		
		switch (num) {
		case 1:
			changePassword();
			break;
			
		case 2:
			changeName();
			break;
			
		case 3:
			System.out.println("로그아웃 되었습니다.");
			mainMenu();
			break;

		default:
			System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			memberMenu();
			break;
		}
	}
	
	public void joinMembership() {
		System.out.print("아이디 : ");
		String id = scanner.next();
		
		System.out.print("비밀번호 : ");
		String password = scanner.next();
		
		System.out.print("이름 : ");
		String name = scanner.next();
		
		Member member = new Member(password, name);
		
		boolean join = mc.joinMembership(id, member);
		
		if (join) {
			System.out.println("성공적으로 회원가입 완료하였습니다.");
		} else {
			System.out.println("중복된 아이디입니다. 다시 입력해주세요");
			joinMembership();
		}
		mainMenu();
	}
	
	public void logIn() {
		// logIn() 실행 후 memberMenu() 실행
		System.out.print("아이디 : ");
		String id = scanner.next();
		
		System.out.print("비밀번호 : ");
		String password = scanner.next();
		
		String login = mc.logIn(id, password);
		
		if (login != null) {
			System.out.println(login + "님, 환영합니다!");
			memberMenu();
		} else {
			System.out.println("틀린 아이디 또는 비밀번호입니다. 다시 입력해주세요.");
			logIn();
		}
	}
	
	public void changePassword() {
		System.out.print("아이디 : ");
		String id = scanner.next();
		
		System.out.print("현재 비밀번호 : ");
		String oldPw = scanner.next();
		
		System.out.print("새로운 비밀번호 : ");
		String newPw = scanner.next();
		
		boolean changePw = mc.changePassword(id, oldPw, newPw);
		
		if (changePw) {
			System.out.println("비밀번호 변경에 성공했습니다.");
			memberMenu();
		} else {
			System.out.println("비밀번호 변경에 실패했습니다. 다시 입력해주세요");
			changePassword();
		}
	}
	
	public void changeName() {
		System.out.print("아이디 : ");
		String id = scanner.next();
		
		System.out.print("비밀번호 : ");
		String password = scanner.next();
		
		String oldName = mc.logIn(id, password);
		
		if (oldName == null) {
			System.out.println("이름 변경에 실패하였습니다. 다시 입력해주세요");
			changeName();
		} else {
			System.out.println("현재 설정된 이름 : " + oldName);
			
			System.out.print("변경할 이름 : ");
			String newName = scanner.next();
			
			mc.changeName(id, newName);
			System.out.println("이름 변경에 성공하였습니다.");
			memberMenu();
		}
	}
	
	public void sameName() {
		System.out.print("검색할 이름: ");
		String name = scanner.next();
		
		TreeMap<String, String> sameName = mc.sameName(name);
		
		Iterator<String> iterKey = sameName.keySet().iterator();
		
		while (iterKey.hasNext()) {
			String userName = iterKey.next();
			System.out.println(userName + "-" + sameName.get(userName));
		}
	}
}
