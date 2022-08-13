package com.sean.liba;

import com.sean.lib.Calculator;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.print();
    }

    public void print() {
        System.out.println("This is Library A!");

        Calculator c = Calculator.getInstance();
        System.out.println(c.add(1, 2));
    }
}
