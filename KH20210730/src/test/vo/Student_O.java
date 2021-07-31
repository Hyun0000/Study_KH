package test.vo;

public class Student_O {
	private String name;
	private int kor;
	private int eng;
	
	public Student_O(String name, int kor, int eng) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
	}
	
	public int getSum() {
		return kor+eng;
	}
	
	public String getName() {
		return name;
	}
}
