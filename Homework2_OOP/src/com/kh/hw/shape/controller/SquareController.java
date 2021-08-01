package com.kh.hw.shape.controller;

import com.kh.hw.shape.model.vo.Shape;

public class SquareController{
	private Shape s = new Shape();
	
	// 사각형 둘레
	public double calcPerimeter (double height, double width){
		s.setType(4);
		s.setHeight(height);
		s.setWidth(width);
		return (width * 2) + (height * 2);
	}
	
	// 사각형 넓이
	public double calcArea (double height, double width) {
		s.setType(4);
		s.setHeight(height);
		s.setWidth(width);
		return (height * width);
	}
	
	public void paintColor(String color) {
		s.setColor(color);
	}
	
	public String print() {
		return "사각형 " + s.information();
	}
}