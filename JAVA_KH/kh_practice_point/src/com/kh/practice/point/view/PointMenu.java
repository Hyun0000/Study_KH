package com.kh.practice.point.view;

import java.util.Scanner;

import com.kh.practice.point.controller.CircleController;
import com.kh.practice.point.controller.RectangleController;

public class PointMenu {
	private Scanner sc = new Scanner(System.in);
	
	private CircleController cc = new CircleController();
	
	private RectangleController rc = new RectangleController();
	
	
	// void : 리턴을 안 한다 --> 화면에 보여주겠다는 의미

	public void mainMenu() {
		
		System.out.println("===== 메뉴 =====");
		System.out.println("1. 원");
		System.out.println("2. 사각형");
		System.out.println("9. 끝내기");
		System.out.print("메뉴 번호 : ");
		int choNum = sc.nextInt();
		if(choNum == 1) {
			circleMenu();
		} else if (choNum == 2) {
			rectangleMenu();
		} else if (choNum == 9) {
			sc.close();
		}
	}
	
	
	public void circleMenu() {
		System.out.println("===== 원 메뉴 =====");
		System.out.println("1. 원 둘레");
		System.out.println("2. 원 넓이");
		System.out.println("9. 메인으로");
		System.out.print("메뉴 번호 : ");
		int choNum = sc.nextInt();
		if(choNum == 1) {
			calcCircum();
		} else if (choNum == 2) {
			calcCircleArea();
		} else if (choNum == 9) {
			mainMenu();
		}
//		sc.close();
	}
	
	public void rectangleMenu() {
		System.out.println("===== 원 메뉴 =====");
		System.out.println("1. 사각형 둘레");
		System.out.println("2. 사각형 넓이");
		System.out.println("9. 메인으로");
		System.out.print("메뉴 번호 : ");
		int choNum = sc.nextInt();
		if(choNum == 1) {
			calcPerimeter();
		} else if (choNum == 2) {
			calcRectArea();
		} else if (choNum == 9) {
			mainMenu();
		}
//		sc.close();
	}
	
	public void calcCircum() {
		System.out.print("x 좌표 : ");
		int numX = sc.nextInt();
		
		System.out.print("y 좌표 : ");
		int numY = sc.nextInt();
		
		System.out.print("반지름 : ");
		int numR = sc.nextInt();
		
		System.out.printf("둘레 : %d, %d, %d / ", numX, numY, numR);
		System.out.println(cc.calcCircum(numX, numY, numR));
		
//		sc.close();
		mainMenu();
	}
	
	public void calcCircleArea() {
		System.out.print("x 좌표 : ");
		int numX = sc.nextInt();
		
		System.out.print("y 좌표 : ");
		int numY = sc.nextInt();
		
		System.out.print("반지름 : ");
		int numR = sc.nextInt();
		
		System.out.printf("면적 : %d, %d, %d / ", numX, numY, numR);
		System.out.println(cc.calcArea(numX, numY, numR));
		
//		sc.close();
		mainMenu();
	}
	
	public void calcPerimeter() {
		System.out.print("x 좌표 : ");
		int numX = sc.nextInt();
		
		System.out.print("y 좌표 : ");
		int numY = sc.nextInt();
		
		System.out.print("높이 : ");
		int numR = sc.nextInt();
		
		System.out.print("너비 : ");
		int numW = sc.nextInt();
		
		System.out.printf("둘레 : %d, %d %d %d / ", numX, numY, numR, numW);
		System.out.println(rc.calcPerimeter(numX, numY, numR, numW));
		
//		sc.close();
		mainMenu();
	}
	
	public void calcRectArea() {
		System.out.print("x 좌표 : ");
		int numX = sc.nextInt();
		
		System.out.print("y 좌표 : ");
		int numY = sc.nextInt();
		
		System.out.print("높이 : ");
		int numR = sc.nextInt();
		
		System.out.print("너비 : ");
		int numW = sc.nextInt();
		
		System.out.printf("둘레 : %d, %d %d %d / ", numX, numY, numR, numW);
		System.out.println(rc.calcArea(numX, numY, numR, numW));
		
//		sc.close();
		mainMenu();
		}
	}