package com.kh.practice.set.view;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import com.kh.practice.set.controller.LotteryController;
import com.kh.practice.set.model.vo.Lottery;

public class LotteryMenu {
	private Scanner sc = new Scanner(System.in);
	private LotteryController lc = new LotteryController();
	
	public void mainMenu() {
		System.out.println("========== KH 추첨 프로그램 ==========");
		
		boolean loop = true;
		while(loop) {
		System.out.println("******* 메인 메뉴 *******");
		System.out.println("1. 추첨 대상 추가");
		System.out.println("2. 추첨 대상 삭제");
		System.out.println("3. 당첨 대상 확인");
		System.out.println("4. 정렬된 당첨 대상 확인");
		System.out.println("5. 당첨 대상 검색");
		System.out.println("9. 종료");
		System.out.println();
		
		System.out.print("메뉴 번호 선택 : ");
		int num = sc.nextInt();
		
		switch (num) {
		case 1:
			insertObject();
			break;
			
		case 2:
			deleteObject();
			break;
			
		case 3:
			winObject();
			break;
			
		case 4:
			sortedWinObject();
			break;
			
		case 5:
			searchWinner();
			break;
			
		case 9:
			loop = false;
			System.out.println("프로그램 종료");
			sc.close();
			break;

		default:
			System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			break;
			}
		}
	}
//============================================================
	public void insertObject() {
		System.out.print("추가할 추첨 대상 수 : ");
		int count = sc.nextInt();
		
		for (int i = 0; i < count; i++) {
			System.out.println();
			System.out.print("이름 : ");
			String name = sc.next();
			
			System.out.print("핸드폰 번호('-'빼고) : ");
			String phone = sc.next();
			
			Lottery lottery = new Lottery(name, phone);
			
			boolean insert = lc.insertObject(lottery);
			if (insert) {
				continue;
			} else {
				System.out.println("중복된 대상입니다. 다시 입력하세요.");
				i--;
			}
		}
		System.out.println();
		System.out.println(count + "명 추가 완료되었습니다.");
		System.out.println();
	}
//============================================================
	public void	deleteObject() {
//		System.out.println("삭제할 대상의 이름과 핸드폰 번호를 입력하세요.");
//		System.out.print("이름 : ");
//		String name = sc.next();
//		
//		System.out.print("핸드폰 번호('-'빼고) : ");
//		String phone = sc.next();
//		
//		Lottery lottery = new Lottery(name, phone);
//		
//		boolean deleteObject = lc.deleteObject(lottery);
//		
//		if (deleteObject = true) {
//			System.out.println("삭제 완료 되었습니다.");
//		} else {
//			System.out.println("존재하지 않는 대상입니다.");
//		}
	}
//============================================================
	public void winObject() {
		// 3. 당첨 대상 확인용 view 메소드
		// lc에서 받아온 Set 객체를 println()메소드를 통해 출력
		
		// 쉼표 해결하기
		HashSet<Lottery> win =  lc.winObject();
		
		Iterator<Lottery> iterKey = win.iterator();
		
		System.out.print("[ ");
		while (iterKey.hasNext()) {
			Lottery key = iterKey.next();
			System.out.print(key + ", ");
		}
		System.out.println(" ]");
	
	}
//============================================================
	public void sortedWinObject() {
		
	}
//============================================================
	public void searchWinner() {
		
	}
}
