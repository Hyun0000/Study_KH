package practice3.controller;

import java.util.Arrays;

import practice3.model.vo.Animal;
import practice3.model.vo.Cat;
import practice3.model.vo.Dog;

public class AnimalManager {

	public static void main(String[] args) {
		Animal[] animals = new Animal[5];
//		String name, String kinds, String location, String color
//		String name, String kinds, int weight
		// 각각에 맞는 배열 생성
		
		for (int i = 0; i < animals.length; i++) {
			if(i >= 2*Math.random()) {
				animals[i] = new Cat();
			} else {
				animals[i] = new Dog();
			}
		}
		
		for (int i = 0; i < animals.length; i++) {
			animals[i].speak();
		}
	}

}
