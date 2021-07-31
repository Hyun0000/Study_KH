package test.clone;

public class Point implements Cloneable {
	private int posX;
	private int posY;
	
	public Point(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	@Override // clone() method 재정의
	// 아래의 오버라이드된 코드는 바꿀 수 없다.
	public Object clone() throws CloneNotSupportedException {
	// throws --> 예외가 발생하면 try-catch로 묶어서 처리해줘
	// 특히 예외의 종류는 CloneNotSupportedException 이걸로 해줘
		return super.clone();
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}