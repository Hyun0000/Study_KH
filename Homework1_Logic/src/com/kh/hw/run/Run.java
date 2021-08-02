/*Run에서 Menu사용, Menu에서 Function사용
Run클래스에 main()를 구현하여 main() 메소드에는 Menu클래스의 displayMenu()메소드 실행*/

package com.kh.hw.run;

import com.kh.hw.view.Menu;

public class Run {

	public static void main(String[] args) {
		Menu menu = new Menu();
		
		menu.displayMenu();
		
		
	}

}
