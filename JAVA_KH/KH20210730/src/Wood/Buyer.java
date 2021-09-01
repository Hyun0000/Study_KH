package Wood;

public class Buyer {
	private int money = 500;
	
	public void buy(Furniture f) {
		if (money < f.price) {
			System.out.println("잔액부족!");
			return; // main() method를 끝내버린다.
		}
		money -= f.price;
		System.out.println(f + " 구매성공! 잔액 : " + money + " 만원");
	}
}
/* b.buy(new Chair()); ----풀어쓰면----> Furniture f = new Chair()
 * Furniture class의 필드 price의 접근제한자가 private라면
 * (private int price;)
 * 이때 f.price()는 쓸 수 없다.
 * 
 * 그러면 어떻게 해야 Buyer class에서 price를  쓸 수 있을까?
 * 접근제한자가 private가 아니라면 다 가능하다.
 * 
 * 근데 protected라고 써준이유는
 * Furniture를 '상속받은 클래스'들에서 price를 이용해야 할때
 * protected로 인해서 접근할 수 있기 때문이다.(부모-자식 관계니까)
 * 
 * 그럴거면 그냥 같은 패키지안인데 default라고 하면 되지 않나?
 * 지금은 수업 때문에 같은 패키지 안에 있는것이지 
 * 실전에서는 패키지를 나누어서 쓸 가능성이 높기에 그걸 염려하고 protected라고 쓴 것이다.
 * 
 * getter,setter를 쓰면 price와 상관없는
 * buyer class 같은데서도 price를 건드릴 수 있기 때문에 안된다.
 * (getter,setter의 접근제한자는 public)
 * 정 getter,setter를 쓰고 싶으면
 * getter,setter의 접근제한자도 protected로 해야한다.
 * */

/* toString() method
 * instance명인 'f'만 썼는데 어떻게 결과값에 'Desk'가 나오고 'Chair'가 나오는가?
 * f 뒤에 [ .toStirng() ]이 생략돼있는 것이다. --> f.toString()
 * JDK가 컴파일하는 과정에서  'f'뒤에 .toStirng()을 넣어준 것인데 생략된 것이다.
 * (그냥 자동으로 f가 'Desk'나'Chair'를 뱉어낸게 아니라는 것이다.)
 * cf) f.toStirng() 이라고 작성해도 결과값은 동일하다.
 * 
 * toString() method의 역할 : 해당 객체를 대표하는 문구를 작성하는 메소드
 * (toString() method는 자신의 아이덴티티를 나타낼때 쓴다.)
 * Buyer class에서 toString() method는 'f'를 대표하는 메소드로 작동한 것이다.

 * Furniture class에서  Chair class에서 오버라이드 한 toString() method를 가질 수 있다.
 * why? Furniture class도 Object class를 상속받았기에
 * Furniture class도 toString() method가 있는것이고
 * 그래서 Chair class에서 오버라이드 한 toString() method가
 * Furniture class의 toString() method에 적용되는 것이다.
 * */