package test.hashcode;

public class GetHashCodeEx_O {
	public void testHashCode() {
		Point point = new Point(10, 20);
		
		System.out.println(point.hashCode());
		System.out.println("point HashCode : " + point.hashCode());
	}
}
//======================================================
class Point { // Point 객체를 선언하여 hashCode 재정의
	int posX;
	int posY;

	public Point(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public String getHashcode() {
		return "point HashCode : " + super.hashCode();
	}
	
	@Override /*--> 이건 수동으로 입력해줘야한다.*/
	public int hashCode() {// hashCode 메소드 재정의
		return super.hashCode();
	}
}





