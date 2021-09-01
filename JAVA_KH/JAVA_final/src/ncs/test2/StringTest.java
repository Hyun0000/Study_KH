package ncs.test2;

public class StringTest {
	public static void main(String[] args) {
		String str = "A, b, c, D, a, B, C, d, f, F";
		
		String[] st = str.split(", ");
		
		char[] data = new char[st.length];
		
		
		for (int i = 0; i < data.length; i++) {
			data[i] = st[i].charAt(0);
		}
		
		int i = 0;
		while (i < data.length) {
			// 아스키코드 이용
			if ((int) data[i] >= 65 && (int)data[i] <= 90 ) {
				System.out.println("data[" + i + "]" + " : " + data[i]);
				i++;
			}
			else {
				i++;
				continue;
			}
		}
	}
}
