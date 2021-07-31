package kh.test;

public class TestEquals_O {
	public void stringEquals() {
		String s1 = new String("aaa");
		String s2 = new String("aaa");
		
		System.out.println("============================");
		if (s1 == s2) {
			System.out.println("s1 == s2 같다");
		}	// false
		
		System.out.println("============================");
		if (s1.equals(s2)) {
			System.out.println("s1.equals(s2) 같다");
		}	// true
		
		System.out.println("============================");
		// 이렇게 비교도 가능하다.
		if("aaa".equals(s1)) {
			System.out.println("문자.equals(s1) 같다.");
		}	// true
		
		System.out.println("============================");
		// 이렇게 비교도 가능하다.
		if (s1.equals("aaa")) {
			System.out.println("s1.equals(문자) 같다.");
		}	// true
		
		System.out.println("============================");
		// 이렇게 비교도 가능하다.
		Student_O s = new Student_O();
		s.setName("hykim");
		
		if (s.getName().equals("hykim")) {
			System.out.println("s.getName().equals(문자) 같다.");
		}	// true
		
		System.out.println("============================");
		if ("hykim".equals(s.getName())) {
			System.out.println("문자.equals(s.getName() 같다.");
		}	// true
		
		System.out.println("=====끝=====");
	}
}