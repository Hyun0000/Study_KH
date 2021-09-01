package test.str;

public class StringEx_O {
	public void testString() {
		String str = "Hello Java";
		// 공백도 하나의 문자로 취급
		
		String concat, upperCase, replace, subString;
		
		System.out.println("기본 String : " + str);
		System.out.println("기본 String : " + str.length());
		
		concat = str.concat(", Hi Java");
		// 문자열 연결
		
		upperCase = concat.toUpperCase();
		// 대문자로 변화
		
		replace = upperCase.replace("J", "L");
		// 문자 J를 L로 변환
		
		subString = replace.substring(3, 10);
		// 3~9번째 위치의 문자열 잘라내기
		
		System.out.println("Concat String : " + concat);
		System.out.println("upperCase String : " + upperCase);
		System.out.println("replace String : " + replace);
		System.out.println("subString String : " + subString);
	}
}
