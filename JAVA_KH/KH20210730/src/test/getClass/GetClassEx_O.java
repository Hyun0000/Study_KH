package test.getClass;


import java.lang.reflect.Field;

import test.clone.Point;

public class GetClassEx_O {
	public void testGetClass() {
		Point point = new Point(10, 20);
		
		Class cls = point.getClass();
		
		System.out.println("getName() : " + cls.getName());
		// 객체의 클래스 이름
		
		System.out.println("getSuperclass() : " + cls.getSuperclass());
		// 상위 클래스 이름
		
		Field[] fields = cls.getDeclaredFields();
		for(Field f : fields) {
			System.out.println("Field : " + f);
		}
	}
}
