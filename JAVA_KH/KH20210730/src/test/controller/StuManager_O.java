package test.controller;

import java.util.Objects;
import test.vo.Student_O;

public class StuManager_O {
	public void scoreComp(Student_O s1, Student_O s2) {
		new StuComparator_O().compare(s1, s2);
		int result = Objects.compare(s1, s2, new StuComparator_O());
		
		if (result > 0) {
			System.out.println(s1.getName() + " 학생이 우수합니다");
		} else if(result < 0) {
			System.out.println(s2.getName() + " 학생이 우수합니다");
		} else {
			System.out.println("두 학생은 점수가 같습니다");
		}
		
	}
}
