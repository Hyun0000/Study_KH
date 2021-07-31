package test.main;

import test.wrapper.WrapperEx_O;

public class Main_Wrapper_O {

	public static void main(String[] args) {
		WrapperEx_O wp = new WrapperEx_O();
		
		int i = 123;
		double d = 3.14;
		
		wp.printWrapper(i);
		wp.printWrapper(d);
	}

}
