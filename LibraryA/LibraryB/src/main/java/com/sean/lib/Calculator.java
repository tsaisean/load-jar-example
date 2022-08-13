package com.sean.lib;

public class Calculator {

    private static Calculator singleton;
    public int counter = 0;

    public static Calculator getInstance() {
        if (singleton == null) {
            synchronized (Calculator.class) {
                if (singleton == null) {
                    singleton = new Calculator();
                    singleton.counter++;
                    System.out.println("This is Library B! Counter: " +  singleton.counter);
                    return singleton;
                }
            }
        }
        singleton.counter++;
        System.out.println("This is Library B! Counter: " +  singleton.counter);
        return singleton;
    }

    public int add(int a, int b) {
        System.out.println("This is Library B! Version 1");
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        return a / b;
    }
}