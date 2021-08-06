package com.kh.practice.generics.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.kh.practice.generics.controller.FarmController;
import com.kh.practice.generics.model.vo.Farm;
import com.kh.practice.generics.model.vo.Fruit;
import com.kh.practice.generics.model.vo.Nut;
import com.kh.practice.generics.model.vo.Vegetable;

public class FarmMenu {
	private Scanner scanner = new Scanner(System.in);
	private FarmController fc = new FarmController();
	
	private void scannerClose() {
		try {
			if (scanner != null) {
				scanner.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//=====================================================================	
	public void mainMenu() {
		System.out.println("******* 메인 메뉴 *******");
		System.out.println("1. 직원메뉴");
		System.out.println("2. 손님 메뉴");
		System.out.println("9. 종료");
		
		System.out.println();
		System.out.print("메뉴 번호 선택 : ");
		int num = scanner.nextInt();
		
		switch (num) {
		case 1:
			adminMenu();
			break;
			
		case 2:
			System.out.println("현재 KH마트 농산물 수량");
			printFarm();
			customerMenu();
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
//=====================================================================
	public void adminMenu() {
		System.out.println("******* 직원 메뉴 *******");
		System.out.println("1. 새 농산물 추가");
		System.out.println("2. 종류 삭제");
		System.out.println("3. 수량 수정");
		System.out.println("4. 농산물 목록");
		System.out.println("9. 메인으로 돌아가기");
		
		System.out.println();
		System.out.print("메뉴 번호 선택 : ");
		int num = scanner.nextInt();
		
		switch (num) {
		case 1:
			addNewKind();
			adminMenu();
			break;
			
		case 2:
			removeKind();
			adminMenu();
			break;
			
		case 3:
			changeAmount();
			adminMenu();
			break;
			
		case 4:
			printFarm();
			adminMenu();
			break;
			
		case 9:
			mainMenu();
			break;

		default:
			System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			adminMenu();
			break;
		}
	}
//=====================================================================	
	public void customerMenu() {
		
		System.out.println("******* 고객 메뉴 *******");
		System.out.println("1. 농산물 사기");
		System.out.println("2. 농산물 빼기");
		System.out.println("3. 구입한 농산물 보기");
		System.out.println("9. 메인으로 돌아가기");
		
		System.out.println();
		System.out.print("메뉴 번호 선택 : ");
		int num = scanner.nextInt();
		
		switch (num) {
		case 1:
			buyFarm();
			customerMenu();
			break;
			
		case 2:
			removeFarm();
			customerMenu();
			break;
			
		case 3:
			printBuyFarm();
			customerMenu();
			break;
			
		case 9:
			mainMenu();
			break;

		default:
			System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
			customerMenu();
			break;
		}
	}
//=====================================================================
	public void addNewKind() {
//		새로운 농산물 추가 성공을 알리는 메소드
		System.out.println("1. 과일 / 2. 채소 / 3. 견과");
		System.out.print("추가할 종류 번호 : ");
		int num = scanner.nextInt();
		
		System.out.print("이름 : ");
		String name = scanner.next();
		
		System.out.print("수량 : ");
		int amount = scanner.nextInt();
		
		switch (num) {
		case 1:
			Fruit fruit = new Fruit("과일", name);
			boolean add1 = fc.addNewKind(fruit, amount);
			if (add1) {
				System.out.println("새 농산물이 추가되었습니다.");
			} else {
				System.out.println("새 농산물 추가에 실패하였습니다.");
				addNewKind();
			}
			adminMenu();
			break;
			
		case 2:
			Vegetable vegetable = new Vegetable("채소", name);
			boolean add2 = fc.addNewKind(vegetable, amount);
			if (add2) {
				System.out.println("새 농산물이 추가되었습니다.");
			} else {
				System.out.println("새 농산물 추가에 실패하였습니다.");
				addNewKind();
			}
			adminMenu();
			break;
			
		case 3:
			Nut nut = new Nut("견과", name);
			boolean add3 = fc.addNewKind(nut, amount);
			if (add3) {
				System.out.println("새 농산물이 추가되었습니다.");
			} else {
				System.out.println("새 농산물 추가에 실패하였습니다.");
				addNewKind();
			}
			adminMenu();
			break;

		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			addNewKind();
			break;
		}
		
	}
//=====================================================================
	public void removeKind() {
		System.out.println("1. 과일 / 2. 채소 / 3. 견과");
		System.out.print("삭제할 종류 번호 : ");
		int num = scanner.nextInt();
		
		System.out.print("이름 : ");
		String name = scanner.next();
		
		switch (num) {
		case 1:
			Fruit fruit = new Fruit("과일", name);
			boolean add1 = fc.removeKind(fruit);
			if (add1) {
				System.out.println("농산물 삭제에 성공했습니다.");
			} else {
				System.out.println("해당 농산물이 없습니다.");
				removeKind();
			}
			adminMenu();
			break;
			
		case 2:
			Vegetable vegetable = new Vegetable("채소", name);
			boolean add2 = fc.removeKind(vegetable);
			if (add2) {
				System.out.println("농산물 삭제에 성공했습니다.");
			} else {
				System.out.println("해당 농산물이 없습니다.");
				removeKind();
			}
			adminMenu();
			break;
			
		case 3:
			Nut nut = new Nut("견과", name);
			boolean add3 = fc.removeKind(nut);
			if (add3) {
				System.out.println("농산물 삭제에 성공했습니다.");
			} else {
				System.out.println("해당 농산물이 없습니다.");
				removeKind();
			}
			adminMenu();
			break;

		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			removeKind();
			break;
		}
	}
//=====================================================================
	public void changeAmount() {
		System.out.println("1. 과일 / 2. 채소 / 3. 견과");
		System.out.print("수정할 종류 번호 : ");
		int num = scanner.nextInt();
		
		System.out.print("이름 : ");
		String name = scanner.next();
		
		System.out.print("수정할 수량 : ");
		int changeAmout = scanner.nextInt();
		
		switch (num) {
		case 1:
			Fruit fruit = new Fruit("과일", name);
			boolean add1 = fc.changeAmount(fruit, changeAmout);
			if (add1) {
				System.out.println("농산물 수량이 수정되었습니다.");
			} else {
				System.out.println("해당 농산물이 없습니다.");
				changeAmount();
			}
			adminMenu();
			break;
			
		case 2:
			Vegetable vegetable = new Vegetable("채소", name);
			boolean add2 = fc.changeAmount(vegetable, changeAmout);
			if (add2) {
				System.out.println("농산물 수량이 수정되었습니다.");
			} else {
				System.out.println("해당 농산물이 없습니다.");
				changeAmount();
			}
			adminMenu();
			break;
			
		case 3:
			Nut nut = new Nut("견과", name);
			boolean add3 = fc.changeAmount(nut, changeAmout);
			if (add3) {
				System.out.println("농산물 수량이 수정되었습니다.");
			} else {
				System.out.println("해당 농산물이 없습니다.");
				changeAmount();
			}
			adminMenu();
			break;

		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			changeAmount();
			break;
		}
	}
//=====================================================================
	public void printFarm() {
		HashMap<Farm, Integer> printFarmMap = fc.printFarm();
		
		Iterator<Farm> printFarm = printFarmMap.keySet().iterator();
		
		while (printFarm.hasNext()) {
			Farm key = printFarm.next();
			System.out.println(key.toString() + "(" +printFarmMap.get(key) + "개)");
		}
	}
//=====================================================================
	public void buyFarm() {
		// 농산물 구매용 메소드
		System.out.println("1. 과일 / 2. 채소 / 3. 견과");
		System.out.print("구매 종류 번호 : ");
		int num = scanner.nextInt();
		
		System.out.print("구매할 것 : ");
		String name = scanner.next();
		
		switch (num) {
		case 1:
			Fruit fruit = new Fruit("과일", name);
			boolean add1 = fc.buyFarm(fruit);
			if (add1) {
				System.out.println("구매에 성공했습니다.");
			} else {
				System.out.println("마트에 없는 물건이거나 수량이 없습니다. 다시 입력해주세요");
				buyFarm();
			}
			System.out.println("현재 KH마트 농산물 수량");
			printFarm();
			customerMenu();
			break;
			
		case 2:
			Vegetable vegetable = new Vegetable("채소", name);
			boolean add2 = fc.buyFarm(vegetable);
			if (add2) {
				System.out.println("구매에 성공했습니다.");
			} else {
				System.out.println("마트에 없는 물건이거나 수량이 없습니다. 다시 입력해주세요");
				buyFarm();
			}
			System.out.println("현재 KH마트 농산물 수량");
			printFarm();
			customerMenu();
			break;
			
		case 3:
			Nut nut = new Nut("견과", name);
			boolean add3 = fc.buyFarm(nut);
			if (add3) {
				System.out.println("구매에 성공했습니다.");
			} else {
				System.out.println("마트에 없는 물건이거나 수량이 없습니다. 다시 입력해주세요");
				buyFarm();
			}
			System.out.println("현재 KH마트 농산물 수량");
			printFarm();
			customerMenu();
			break;

		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			buyFarm();
			break;
		}
	}
//=====================================================================
	public void removeFarm() {
		
	}
//=====================================================================
	public void printBuyFarm() {
		
	}
	
}
