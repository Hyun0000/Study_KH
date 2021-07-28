package com.kh.practice.point.model.vo;

public class Point {
	private int x;
	private int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		// 아직 작성하지 않는다.
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// atl + shift + s --> Generate toString()
	// 필드만 체크해서 만들었다.(밑의 methods와 inherited methods는 거의 체크 안 한다.)
	// 필드 값을 디스플레이 하기 위한 메소드이다.
	@Override
	public String toString() {
		return "Point [x" + x + ", y=" + y + "]";
	}	
	// 초록색 삼각형
	// 니가 지금 부모 class(Object class)에 있는 toString() method를 오버라이드 하려고 한다.
	// cf) 모든 Class는 Object class를  상속하며 toString()은 Object class의 method이다.
	// 따라서 @Override 이걸 붙여준다.(안 붙여줘도 문제는 없다.)
	// Annotation으로 인해 함수 작성의 틀이 마련됐다.
	
//	@Override
//	public String toString() {
//		// 뭘 return 해야하지??
//		// 아직 명확하지 않을 때 기본적으로 아래와 같이 작성한다.
//		// cf) 프로그램의 성격에 따라 "" / null을 결정
//		String result = "";
//		return result;
		

//	}
}
