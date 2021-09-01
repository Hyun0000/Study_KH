package Wood;

public class Chair extends Furniture{

	public Chair() {
		super(100); // 부모 클래스의 생성자 호출
		// 부모 클래스의 생성자가 반드시 int 인자를 받아야하기에 생략 할 수 없다.
		// 이렇게 함으로써 Chair, Desk 각각의 클래스에 price 필드를 따로 두지 않아도
		// 부모 class에서 관리 할 수 있으니 더 깔끔한 코드가 된다.
	}
	
	@Override
	public String toString() {
		return "Chair";
	}
}
