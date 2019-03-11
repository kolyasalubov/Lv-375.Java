package com.softserve.training;

public class Appl {
	public static void main(String[] args) {
//		Box box = new Box(new Integer(11));
//		BoxWrapper box = new BoxWrapper();
		GenBox<Integer> box = new GenBox<>();
		box.setData(new Integer(11));
		// code
		//box.setData("11"); // Compile Error 
		// code
		//int i = (int) box.getData(); // Code Smell
		int i = box.getData();
		System.out.println(i);
	}
}
