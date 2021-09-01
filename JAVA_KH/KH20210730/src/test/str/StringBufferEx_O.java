package test.str;

public class StringBufferEx_O {
	public void testStringBuffer() {
	StringBuffer str1 = new StringBuffer();
	StringBuffer str2 = new StringBuffer(30);
	StringBuffer str3 = new StringBuffer("Java");
	
	str1.append("HI");
	str2.append("Hello");
	str3.insert(0, str2 + " ");
	
	System.out.println(str1);
	System.out.println(str2);
	System.out.println(str3);
	}
}