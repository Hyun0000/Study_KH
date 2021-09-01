package Wood;

public class Furniture {
	protected int price; //제품가격
//	private int price; --> 교재에 있는 내용

	public Furniture(int price) {
		this.price = price;
	}
	// 만약 위의 생성자가 없었으면 아래의 기본 생성자가
	// 정적바인딩때(컴파일 때) 만들어진다.
	// public Furniture() {}
	// 그러나 이미 생성자가 있으니 넣어주지 않았다.
}