package test.math;

import java.util.Scanner;

public class MathTest_O {
	public void printMath() {
		System.out.println("abs(-10) : " + Math.abs(-10));
		System.out.println("ceil(3.7) : " + Math.ceil(3.7));
		System.out.println("floor(3.7) : " + Math.floor(3.7));
		System.out.println("exp(3.3) : " + Math.exp(3.3));
		System.out.println("pow(2.1, 2) : " + Math.pow(2.1, 2));
		System.out.println("random() : " + Math.random());
		// Math.random() --> 0~1까지의 실수가 나온다.
		// (int)Math.random() * 10 --> 한 세트라고 생각하면 된다.

		// cf) 1~100까지의 난수 ??
		int a = (int)(Math.random()*1000);
		
		// cf) 1~6까지의 난수 ??
		// 이런것에 대한 추가적인 API가 있다.
	}
}