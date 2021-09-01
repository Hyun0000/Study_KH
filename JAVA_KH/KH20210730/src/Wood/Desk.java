package Wood;

public class Desk extends Furniture {

	public Desk() {
		super(200); // 부모 클래스의 생성자 호출
		// 부모 클래스의 생성자가 반드시 int 인자를 받아야하기에 생략 할 수 없다.
		// 이렇게 함으로써 Chair, Desk 각각의 클래스에 price 필드를 따로 두지 않아도
		// 부모 class에서 관리 할 수 있으니 더 깔끔한 코드가 된다.
	}
	
	@Override
	public String toString() {
		return "Desk";
	}
}
/* toString() method 
 * java.lang.Object의 Object class에 toString() method가 있다.
 * 이 method는 원래 232@eferaweh --> 이런식으로 뿌려주기로 돼있다.
 * 근데 이것이 맘에 들지 않아 바꿔주기 위해
 * Object class의 자손인 Desk class에서 toString() method가
 * "Desk"를 뱉어내도록 override한 것이다.
 * (Chair class도 동일하다.)
 * */