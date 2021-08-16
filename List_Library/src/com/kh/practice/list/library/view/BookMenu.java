package com.kh.practice.list.library.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kh.practice.list.library.controller.BookController;
import com.kh.practice.list.library.model.vo.Book;
import com.kh.practice.list.library.run.Run;

public class BookMenu {
	private BookController bc = new BookController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		System.out.println("======= Welcome KH Library =======");
		
		while(true) {
			System.out.println("******* 메인 메뉴 *******");
			System.out.println("1. 새 도서 추가");
			System.out.println("2. 도서 전체 조회");
			System.out.println("3. 도서 검색 조회");
			System.out.println("4. 도서 삭제");
			System.out.println("5. 도서 명 오름차순 정렬");
			System.out.println("9. 종료");
			
			System.out.print("메뉴 번호 선택 : ");
			int num = sc.nextInt();
			sc.nextLine();
			
			switch (num) {
			case 1:
				insertBook();
				break;
				
			case 2:
				selectList();
				break;
				
			case 3:
				searchBook();
				break;
				
			case 4:
				deleteBook();
				break;
				
			case 5:
				ascBook();
				break;
				
			case 9:
				System.out.println("프로그램을 종료합니다.");
				sc.close();
				break;
	
			default:
				System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
	
	public void insertBook() {
		System.out.println();
		
		System.out.println("===== 새 도서 추가 =====");
		System.out.println("책 정보를 입력해주세요.");
		
		System.out.print("도서 명 : ");
		String title = sc.nextLine();
		
		System.out.print("저자 명 : ");
		String author = sc.nextLine();
		
		System.out.print("장르 (1. 인문 / 2. 과학 / 3. 외국어 / 4. 기타) : ");
		int categoryNum = sc.nextInt();
		String category = null;
		
		switch (categoryNum) {
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
			System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
			break;
		}
		
		System.out.print("가격 : ");
		int price = sc.nextInt();
		
		Book book = new Book(title, author, category, price);
		
		bc.insertBook(book);
		
		System.out.println();
	}
	
	public void selectList() {
		List<Book> bookList = bc.selectList();
		
		if (bookList.isEmpty()) {
			System.out.println("존재하는 도서가 없습니다.");
		} else {
			for (int i = 0; i < bookList.size(); i++) {
				System.out.println(bookList.get(i).toString());
			}
		}
	}
	
	public void searchBook() {
		System.out.print("검색 키워드 : ");
		String keyword = sc.nextLine();
		
		List<Book> bookList = bc.searchBook(keyword);
		
		if (bookList.isEmpty()) {
			System.out.println("존재하는 도서가 없습니다.");
		} else {
			for (int i = 0; i < bookList.size(); i++) {
				System.out.println("\t" + bookList.get(i).toString());
			}
		}
		System.out.println();
	}
	
	public void deleteBook() {
		// nextLine()
		// '\n'을 포함하는 한 라인을 읽고 '\n'을 버린 나머지만 리턴
		// nextLine() 메소드는 Enter값을 기준으로 메소드를 종료

		System.out.print("삭제할 도서 명 : ");
		String title = sc.nextLine();
		
		System.out.print("삭제할 저자 명 : ");
		String author = sc.nextLine();
		
		Book book = bc.deleteBook(title, author);
		
		if (book == null) {
			System.out.println("삭제할 도서를 찾지 못했습니다.");
		} else {
			System.out.println("성공적으로 삭제 되었습니다.");
		}
		System.out.println();
	}
	
	public void ascBook() {
//		bc(BookController)의 ascBook() 메소드 값에 따라
//		성공 시 “정렬에 성공하였습니다.”, 실패 시 “정렬에 실패하였습니다.” 출력
	}
	
}
