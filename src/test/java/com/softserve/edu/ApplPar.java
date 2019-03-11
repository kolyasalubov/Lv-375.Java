package com.softserve.edu;

class DataPar {
	private int result1;
	private int result2;

	public DataPar(int result1, int result2) {
		this.result1 = result1;
		this.result2 = result2;
	}

	public int getResult1() {
		return result1;
	}

	public void setResult1(int result1) {
		this.result1 = result1;
	}

	public int getResult2() {
		return result2;
	}

	public void setResult2(int result2) {
		this.result2 = result2;
	}

}

public class ApplPar {

	public int sqr(int i) {
		return i * i;
	}

	public DataPar sqr2(int i) {
		DataPar dataPar = new DataPar(2 * i, i * i);
		return dataPar;
	}

	public void work(int i) { // Overload
		System.out.println("start work(...), i = " + i);
		i = i + 1;
		System.out.println("done work(...), i = " + i);
	}

	public void work(StringBuilder sb) { // Overload
		System.out.println("start work(...), sb = " + sb);
		sb.append(" work added");
		System.out.println("done work(...), sb = " + sb);
	}

	public void work(String s) { // Overload
		System.out.println("start work(...), s = " + s);
		s = s + " work added";
		System.out.println("done work(...), s = " + s);
	}

	public void work(Integer i) { // Overload
		System.out.println("start work(...), i = " + i);
		i = i + 2;
		System.out.println("done work(...), i = " + i);
	}

	public static void main(String[] args) {
		ApplPar app = new ApplPar();
		//
		//int i2 = app.sqr(5);
		//System.out.println("i2 = " + i2);
		//
		//DataPar dataPar = app.sqr2(5);
		//System.out.println("dataPar = " + dataPar.getResult1() + "  " + dataPar.getResult2());
		//
		// 1. By Value, Value Type (Primitive)
//		int i = 1;
//		app.work(i);
//		System.out.println("done main(...), i = " + i);
		//
		// 2. By Value, Reference Type
//		StringBuilder sb = new StringBuilder("123");
//		app.work(sb);
//		System.out.println("done main(...), sb = " + sb);
		//
		// By Value, Reference Immutable Type
//		String s = "1234"; // s = new String("1234"); 
//		app.work(s);
//		System.out.println("done main(...), s = " + s);
		//
		// By Value, Reference Immutable Type
//		Integer i = 12;
//		app.work(i);
//		System.out.println("done main(...), i = " + i);
	}

}
