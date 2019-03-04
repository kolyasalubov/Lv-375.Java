package com.softserev.edu;

public class ClassB extends ClassA {
    public double i = 1.1;

    public void m1() { // Overload
        System.out.println("ClassB, metod m1, i=" + i);
        //super.m1();
    }

    public void m4() { // Overload
        System.out.println("ClassB, metod m4");
    }
    
    public void m5() {
        System.out.println("ClassB, metod m5");
        //m7();
    }
    
    public static void m6() { // Hide parent method
        System.out.println("ClassB, metod m6");
        //ClassA.m6();
    }

    //@Override
    //public void m7() {
    //private void m7() { // Architecture Error
    //    System.out.println("ClassB, metod m7");
    //}

	public void m8() {
		System.out.println("ClassB, metod m8");
	}

}
