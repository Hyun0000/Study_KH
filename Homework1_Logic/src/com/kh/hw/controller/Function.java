/*Run에서 Menu사용, Menu에서 Function사용
Run클래스에 main()를 구현하여 main() 메소드에는 Menu클래스의 displayMenu()메소드 실행*/

package com.kh.hw.controller;

import java.util.Scanner;

public class Function {
	Scanner scanner = new Scanner(System.in);
	
	public void calculator() {
		System.out.print("첫 번째 정수 : ");
		int num1 = scanner.nextInt();
		
		System.out.print("두 번째 정수 : ");
		int num2 = scanner.nextInt();
		
		System.out.print("연산자(+, -, x, /) : ");
		String Operatopr = scanner.next();
		
		
	}
}
