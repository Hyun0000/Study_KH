package com.kh.hw.shape.view;

import java.util.Scanner;

import com.kh.hw.shape.controller.SquareController;
import com.kh.hw.shape.controller.TriangleController;

public class ShapeMenu {
	private Scanner sc = new Scanner(System.in);
	
	private SquareController scr = new SquareController();
	
	private TriangleController tc = new TriangleController();
	
	public void closeScanner() {
		try {
			if (sc != null) {
				sc.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//====================================================	
	public void inputMenu() {
		System.out.println("=====도형 프로그램=====");
		System.out.println("3. 삼각형");
		System.out.println("4. 사각형");
		System.out.println("9. 프로그램 종료");
		System.out.print("메뉴 번호 : ");
		int num = sc.nextInt();
		switch (num) {
		case 3:
			triangleMenu();
			closeScanner();
			break;
			
		case 4:
			squareMenu();
			closeScanner();
			break;
			
		case 9:
			System.out.println("프로그램을 종료합니다.");
			closeScanner();
			break;

		default:
			System.out.println("잘못된 번호입니다. 다시 입력하세요");
			inputMenu();
			break;
		}
	}
//====================================================	
	public void triangleMenu() {
		System.out.println("=====삼각형=====");
		System.out.println("1. 삼각형 면적");
		System.out.println("2. 삼각형 색칠");
		System.out.println("3. 삼각형 정보");
		System.out.println("9. 메인으로");
		System.out.print("메뉴 번호 : ");
		int menuNum = sc.nextInt();
		
		switch (menuNum) {
		case 1:
			inputSize(3, menuNum);
			closeScanner();
			break;
			
		case 2:
			inputSize(3, menuNum);
			closeScanner();
			break;
			
		case 3:
			printInformation(3);
			closeScanner();
			break;
			
		case 9:
			System.out.println("메인으로 돌아갑니다.");
			inputMenu();
			closeScanner();
			break;

		default:
			System.out.println("잘못된 번호입니다. 다시 입력하세요");
			triangleMenu();
			closeScanner();
			break;
		}
	}
//====================================================	
	public void squareMenu() {
		System.out.println("=====사각형=====");
		System.out.println("1. 사각형 둘레");
		System.out.println("2. 사각형 면적");
		System.out.println("3. 사각형 색칠");
		System.out.println("4. 사각형 정보");
		System.out.println("9. 메인으로");
		System.out.print("메뉴 번호 : ");
		int menuNum = sc.nextInt();
		
		switch (menuNum) {
		case 1:
			inputSize(4, menuNum);
			closeScanner();
			break;
			
		case 2:
			inputSize(4, menuNum);
			closeScanner();
			break;
			
		case 3:
			inputSize(4, menuNum);
			closeScanner();
			break;
			
		case 4:
			printInformation(4);
			closeScanner();
			break;
			
		case 9:
			System.out.println("메인으로 돌아갑니다.");
			inputMenu();
			closeScanner();
			break;

		default:
			System.out.println("잘못된 번호입니다. 다시 입력하세요");
			triangleMenu();
			closeScanner();
			break;
		}
	}
//====================================================	
	public void inputSize(int type, int menuNum) {
		if (type == 3 && menuNum == 1) {
			System.out.print("높이 : ");
			int scHeight = sc.nextInt();
			
			System.out.print("너비 : ");
			int scwidth = sc.nextInt();
			
			double calcArea = tc.calcArea(scHeight, scwidth);
			
			System.out.print("삼각형 면적 : ");
			System.out.println(calcArea);
			
			triangleMenu();
			closeScanner();
		} else if (type == 3 && menuNum == 2) {
			System.out.print("색깔을 입력하세요 : ");
			String color = sc.next();
			
			tc.paintColor(color);
			System.out.println("색이 수정되었습니다.");
			triangleMenu();
			closeScanner();
		}
//=======사각형===================================================
		if (type == 4 && menuNum == 1) {
			System.out.print("높이 : ");
			int scHeight = sc.nextInt();
			
			System.out.print("너비 : ");
			int scwidth = sc.nextInt();
			
			double calcArea = scr.calcPerimeter(scHeight, scwidth);
			
			System.out.print("사각형 둘레 : ");
			System.out.println(calcArea);
			
			squareMenu();
			closeScanner();
		} else if (type == 4 && menuNum == 2) {
			System.out.print("높이 : ");
			int scHeight = sc.nextInt();
			
			System.out.print("너비 : ");
			int scwidth = sc.nextInt();
			
			double calcArea = scr.calcArea(scHeight, scwidth);
			
			System.out.print("사각형 면적 : ");
			System.out.println(calcArea);
			
			squareMenu();
			closeScanner();
		} else if (type == 4 && menuNum == 3) {
			System.out.print("색깔을 입력하세요 : ");
			String color = sc.next();
			
			scr.paintColor(color);
			System.out.println("색이 수정되었습니다.");
			squareMenu();
			closeScanner();
		}
	}
//====================================================	
	public void printInformation(int type) {
		if (type == 3) {
			String information = tc.print();
			System.out.println(information);
			triangleMenu();
			closeScanner();
		} else if (type == 4) {
			String information = scr.print();
			System.out.println(information);
			squareMenu();
			closeScanner();
		}
	}
}