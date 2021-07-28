package com.kh.practice.point.controller;

import com.kh.practice.point.model.vo.Point;
import com.kh.practice.point.model.vo.Rectangle;

// Rectangle'Controller' --> Rectangle class를 제어(controll)하겠다는 의미
// 제어(controll)가 메인이기 때문에 method 중심이다.
// cf) Rectangle --> 직사각형
public class RectangleController {
	private Rectangle r = new Rectangle();
	Point p = new Point();
	
	
	// 직사각형 넓이
	public String calcArea(int x, int y, int height, int width) {
		p.setX(x);
		p.setY(y);
		r.setHeight(height);
		r.setWidth(width);
		int calcArea = height * width;
		String result = Integer.toString(calcArea);
		return result;
	}
	
	// 직사각형 둘레
	public String calcPerimeter(int x, int y, int height, int width) {
		p.setX(x);
		p.setY(y);
		r.setHeight(height);
		r.setWidth(width);
		int calcPerimeter = 2*(height + width);
		String result = Integer.toString(calcPerimeter);
		return result;
	}
}
