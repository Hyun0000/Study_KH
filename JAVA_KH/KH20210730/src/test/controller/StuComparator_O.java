package test.controller;

import java.util.Comparator;

import test.vo.Student_O;

public class StuComparator_O implements Comparator<Student_O> {

	@Override // Comparator 인터페이스의 compare() 메소드를 재정의
	public int compare(Student_O o1, Student_O o2) {
		if(o1.getSum() > o2.getSum()) {
			return 1;
		} else if(o1.getSum() < o2.getSum()) {
			return -1;
		} else {
			return 0;
		}
	}
}
