package test.main;

import test.controller.StuManager_O;
import test.vo.Student_O;

public class Main_100p_O {
	public static void main(String[] args) {
		Student_O s1 = new Student_O("홍길동", 100, 90);
		Student_O s2 = new Student_O("고길동", 90, 80);
		new StuManager_O().scoreComp(s1, s2);
	}
}
