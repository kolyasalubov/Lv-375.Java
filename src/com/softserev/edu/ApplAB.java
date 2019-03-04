package com.softserev.edu;

public class ApplAB {
    public static void main(String[] args) {
        System.out.println("The Start.");
        //
        System.out.println("Test ClassA.");
        ClassA a;
        a = new ClassA();
        a.m1();
        a.m2();
        a.m3();
        a.m4();
        a.m6(); // Bad Practice
        ClassA.m6();
        a.m7();
        //
//        System.out.println("Test ClassB.");
//        ClassA b;
//        b = new ClassB();
//        b.m1();
//        b.m2();
//        b.m3();
//        b.m4();
        //b.m5(); // Compile Error
        //((ClassB) b).m5();
        //b.m6(); // Bad Practice. Executing m6 from ClassA, static not Overload !!!
//        ClassB.m6();
//        b.m7();
//        ((ClassB) b).m8();
        //
//        System.out.println("Test_0 ClassB.");
//        ClassB b0;
//        b0 = new ClassB();
//        b0.m1();
//        b0.m2();
//        b0.m3();
//        b0.m4();
//        b0.m5();
//        ClassA.m6();
        //b0.m6(); // Bad Practice
//        ClassB.m6();
        //
        System.out.println("The End.");
    }
}