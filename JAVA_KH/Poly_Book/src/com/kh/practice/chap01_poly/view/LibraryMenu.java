package com.kh.practice.chap01_poly.view;

import java.util.Scanner;

import com.kh.practice.chap01_poly.controller.LibraryController;
import com.kh.practice.chap01_poly.model.vo.Book;
import com.kh.practice.chap01_poly.model.vo.Member;

public class LibraryMenu {
	private LibraryController lc = new LibraryController();
	private Scanner sc = new Scanner(System.in);
	private void scannerClose() {
		try {
			if (sc != null) {
				sc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//============================================================================	
	public void mainMenu() {
		// 이름, 나이, 성별을 키보드로 입력 받은 후 Member 객체 생성
		// LibraryController의 insertMember() 메소드에 전달
		System.out.print("이름 : ");
		String name = sc.next();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		
		System.out.print("성별 : ");
		String gneder = sc.next();
		char genderChar = gneder.charAt(0);
		
		Member member = new Member(name, age, genderChar);
		lc.insertMember(member);
		System.out.println();
		
		boolean loop = true;
		
		while(loop) {
		System.out.println("==== 메뉴 ====");
		System.out.println("1. 마이페이지");
		System.out.println("2. 도서 전체 조회");
		System.out.println("3. 도서 검색");
		System.out.println("4. 도서 대여하기");
		System.out.println("9. 프로그램 종료하기");
		System.out.print("메뉴 번호 : ");
		
		int num = sc.nextInt();
		
		switch (num) {
		case 1:
			Member mem = lc.myInfo();
			System.out.println(mem.toString());
			System.out.println();
			break;
			
		case 2:
			selectAll();
			break;
			
		case 3:
			searchBook();
			break;
			
		case 4:
			rentBook();
			break;

		case 9:
			scannerClose();
			loop = false;
			System.out.println("프로그램을 종료합니다.");
			break;

		default:
			System.out.println("잘못 된 번호입니다. 다시 입력해주세요.");
			System.out.println();
			break;
		}
	}
}
//============================================================================	
	public void selectAll() {
		Book[] bList = lc.selectAll();
		
		System.out.println();
		for (int i = 0; i < bList.length; i++) {
			System.out.println(i + "번 도서 : " + bList[i].toString());
		}
		System.out.println();
	}
//============================================================================
	public void searchBook() {
		// “검색할 제목 키워드 : “ >> 입력 받음 (keyword)
		// LibraryController의 searchBook() 에 전달
		// 그 결과 값을 Book[] 자료형 searchList에 담아 검색 결과인 도서 목록 출력
		System.out.println();
		System.out.print("검색할 제목 키워드 : ");
		String keyword = sc.next();
		
		Book[] searchList = lc.searchBook(keyword);
		
		int count = 0;
		
		for (int i = 0; i < searchList.length; i++) {
			if (searchList[i] == null) {
				count++;
			} else {
				System.out.println(searchList[i].toString());
			}
		}
		
		if (count == searchList.length) {
			System.out.println("검색 결과가 없습니다.");
		}
		System.out.println();
	}
//============================================================================
	public void rentBook() {
		selectAll();
		
		System.out.print("대여할 도서 번호 선택 : ");
		int num = sc.nextInt();
		
		int rentNum = lc.rentBook(num);
		
		if (rentNum == 0) {
			System.out.println("성공적으로 대여되었습니다.");
		} else if (rentNum == 1) {
			System.out.println("나이 제한으로 대여 불가능입니다.");
		} else {
			System.out.println("성공적으로 대여되었습니다. 요리학원 쿠폰이 발급되었으니 마이페이지에서 확인하세요.");
		}
		System.out.println();
	}
}
