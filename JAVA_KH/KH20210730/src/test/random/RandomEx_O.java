package test.random;

import java.util.Random;

public class RandomEx_O {
	public void testRandom() {
		
	Random r = new Random();
	
	System.out.println("nextInt() : " + r.nextInt());
	// -2^31 ~ 2^31-1 사이의 난수
	
	System.out.println("nextInt(100) : " + r.nextInt(100));
	// 0~100 사이의 난수
	
	System.out.println("nextBoolean() : " + r.nextBoolean());
	// true 또는 false
	
	System.out.println("nextDouble() : " + r.nextDouble());
	// double형 표현범위의 난수
	
	System.out.println("nextFloat() : " + r.nextFloat());
	// float형 표현범위의 난수
	}
}
