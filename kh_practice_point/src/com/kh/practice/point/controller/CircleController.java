package com.kh.practice.point.controller;

import com.kh.practice.point.model.vo.Circle;
import com.kh.practice.point.model.vo.Point;

public class CircleController {
	private Circle c = new Circle();
	Point p = new Point();
	
	// 원 넓이
	public String calcArea(int x, int y, int radius) {
		p.setX(x);
		p.setY(y);
		c.setRadius(radius);
		p.toString(); // 없어도 되는데 그냥 넣었다.
		c.toString(); // 없어도 되는데 그냥 넣었다.
		// calcArea : 면적
		double calcArea = Math.PI * radius * radius;
		String result = Double.toString(calcArea);
		return result;
	}
	
	// 원 둘레
	public String calcCircum(int x, int y, int radius) {
		p.setX(x);
		p.setY(y);
		c.setRadius(radius);
		p.toString(); // 없어도 되는데 그냥 넣었다.
		c.toString(); // 없어도 되는데 그냥 넣었다.
		// calcCircum : 원둘레
		double calcCircum = Math.PI * radius * 2;
		String result = Double.toString(calcCircum);
		return result;
	}
}
