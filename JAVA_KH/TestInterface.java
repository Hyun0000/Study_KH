public interface TestInterface {
	
	// interface의 추상 method에는 abstract을 안 적는다.
	// 어차피 interface 안의 method이기에 abstract인 것을 아는 것이다.(작성해도 상관은 없다.)
	
	// public도 안 넣는다.
	
	void aaa();
	
	void bbb();
	
	void ccc();
	
	// 필드는 final이 붙어있는 '상수'만 가능하다.
	// 상수 필드는 'public static final'을 붙여야한다.
	// 보통 상수는 모두가 공유하는 숫자이기 때문이다.
	public static final int MAX_MAMBER_COUNT = 30;
}
