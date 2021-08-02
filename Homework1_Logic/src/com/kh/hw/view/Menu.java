/*Run에서 Menu사용, Menu에서 Function사용
Run클래스에 main()를 구현하여 main() 메소드에는 Menu클래스의 displayMenu()메소드 실행
*
*메뉴 번호: 1
*(메뉴 1 실행) 
*이 부분이 한 줄로 나와야 한다.
*
*/




package com.kh.hw.view;

import java.util.Scanner;

public class Menu {
	public void displayMenu() {
		// 반복문 작성
		while(true) {

		System.out.println("1. 간단 계산기");
		System.out.println("2. 작은 수에서 큰 수까지 합계");
		System.out.println("3. 신상 정보 확인");
		System.out.println("4. 학생 정보 확인");
		System.out.println("5. 별과 숫자 출력");
		System.out.println("6. 난수까지의 합계");
		System.out.println("7. 구구단");
		System.out.println("8. 주사위 숫자 알아맞추기 게임");
		System.out.println("9. 프로그램 종료");
		System.out.print("메뉴 번호: ");
		
		Scanner scanner = new Scanner(System.in);
		int inputNum = scanner.nextInt();
		
		if (inputNum == 9) {
			System.out.println("종료합니다.");
			break;
		}
		
		switch (inputNum) {
		case 1:
			System.out.printf("(메뉴 %d 실행) %n", inputNum);
			break;
			
		case 2:
			System.out.printf("(메뉴 %d 실행) %n", inputNum);
			break;
			
		case 3:
			System.out.printf("(메뉴 %d 실행) %n", inputNum);
			break;
			
		case 4:
			System.out.printf("(메뉴 %d 실행) %n", inputNum);
			break;
			
		case 5:
			System.out.printf("(메뉴 %d 실행) %n", inputNum);
			break;
			
		case 6:
			System.out.printf("(메뉴 %d 실행) %n", inputNum);
			break;
			
		case 7:
			System.out.printf("(메뉴 %d 실행) %n", inputNum);
			break;
			
		case 8:
			System.out.printf("(메뉴 %d 실행) %n", inputNum);
			break;
			
		default:
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			break;
			}
		}
	}
}
