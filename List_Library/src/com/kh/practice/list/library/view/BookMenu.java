package com.kh.practice.list.library.view;

import java.util.Scanner;

import com.kh.practice.list.library.controller.BookController;
import com.kh.practice.list.library.model.vo.Book;

public class BookMenu {
	private Scanner scanner = new Scanner(System.in);
	private BookController bc = new BookController();
	
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
		System.out.println("== Welcome KH Library ==");
		
		System.out.println("******* 메인 메뉴 *******");
		System.out.println("1. 새 도서 추가");
		System.out.println("2. 도서 전체 조회");
		System.out.println("3. 도서 검색 조회");
		System.out.println("4. 도서 삭제");
		System.out.println("5. 도서 명 오름차순 정렬");
		System.out.println("9. 종료");
		System.out.print("메뉴 번호 선택 : :");
		int num = scanner.nextInt();
		
		switch (num) {
		case 1:
			insertBook();
			mainMenu();
			break;
			
		case 2:
			selectList();
			mainMenu();
			break;
			
		case 3:
			searchBook();
			mainMenu();
			break;
			
		case 4:
			deleteBook();
			mainMenu();
			break;
			
		case 5:
			ascBook();
			mainMenu();
			break;
			
		case 9:
			System.out.println("프로그램을 종료합니다");
			scannerClose();
			break;
			
		default:
			System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			mainMenu();
			break;
		}
		
		
	}
	
	public void insertBook() {
		System.out.println("===== 새 도서 추가 =====");
		System.out.println("책 정보를 입력해주세요.");
		
		System.out.print("도서 명 : ");
		String title = scanner.nextLine();
		
		System.out.print("저자 명 : ");
		String author = scanner.nextLine();
		
		System.out.print("장르(1. 인문 / 2. 과학 / 3. 외국어 / 4. 기타)");
		int categorys = scanner.nextInt();
		String category = null;
		switch (categorys) {
		case 1:
			category = "인문";
			break;
			
		case 2:
			category = "과학";
			break;
			
		case 3:
			category = "외국어";
			break;
			
		case 4:
			category = "기타";
			break;
			
		default:
			System.out.println("잘 못 입력했습니다.");
			break;
		}
		
		System.out.print("가격 : ");
		int price = scanner.nextInt();
		
		Book bk = new Book(title, author, category, price);
		
		bc.insertBook(bk);
		scannerClose();
	}
	
	public void selectList() {
		System.out.println("===== 도서 전체 조회 =====");
		bc.selectList();
	}
	
	public void searchBook() {
		
	}
	
	public void deleteBook() {
		
	}
	
	public void ascBook() {
		
	}
}
