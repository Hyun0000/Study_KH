package com.kh.hw.person.controller;

import com.kh.hw.person.model.vo.Employee;
import com.kh.hw.person.model.vo.Student;

public class PersonController {
	Student[] s = new Student[3];
	Employee[] e = new Employee[10];
//========================================================================
	public int[] personCount() {
		int sCount = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i] == null) {
				sCount++;
			}
		}
		
		int eCount = 0;
		for (int i = 0; i < e.length; i++) {
			if (e[i] == null) {
				eCount++;
			}
		}
		
		int[] personCount = new int[4];
		
		personCount[0] = s.length - sCount;
		personCount[1] = s.length;
		
		personCount[2] = e.length - eCount;
		personCount[3] = e.length;
		
		
		return personCount;		
	}
//========================================================================
	public void insertStudent
	(String name, int age, double height,
			double weight, int grade, String major) {
		
	for (int i = 0; i < e.length; i++) {
		if (s[i] == null) {
			s[i] = new Student(name, age, height, weight, grade, major);
			break;
		}
	}
}
//========================================================================
	public Student[] printStudent() {
		return s;
	}
////========================================================================
	public void insertEmployee(String name, int age, double height, 
			double weight, int salary, String dept) {
		
		for (int i = 0; i < e.length; i++) {
			if (e[i] == null) {
				e[i] = new Employee(name, age, height, weight, salary, dept);
				break;
			}
		}
	}
////========================================================================
	public Employee[] printEmployee() {
		return e;
	}
}