package test.clone;

public class CloneEx_O {
	public void testClone() {
//		Point p1 = null;
		Point p1 = new Point(10, 20);
		Point p2 = p1; // 얕은 복사, p1의 주소를 복사한다.
		Point p3 = null;
		
		try {
			p3 = (Point)p1.clone();
			// 깊은 복사, p1의 주소가 가리키는 값을 복사한다.
			// 만약 [ Point p1 = null; ]이면 --> 에러 발생
			// java.lang.NullPointerException
			// 여기서 실행이 멈추고 밑의 부분은 수행을 하지 않는다.
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		p1.setPosX(111); // p2는 주소를 공유 --> 영향 O  /  p3는 영향 X
		p1.setPosY(222); // p2는 주소를 공유 --> 영향 O  /  p3는 영향 X
		
		System.out.println("p1 = " + p1 + " : (" + p1.getPosX() + ", " +
				p1.getPosY() + ")");
		
		System.out.println("p2 = " + p2 + " : (" + p2.getPosX() + ", " +
				p2.getPosY() + ")");
		
		System.out.println("p3 = " + p3 + " : (" + p3.getPosX() + ", " +
				p3.getPosY() + ")");
	}
}
