package khrushchev.ilya;

import khrushchev.ilya.Calculator.Calculator;
import khrushchev.ilya.NumberSequence.*;

public class Main {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        String result = calc.calculate("3.125*12-(18/9)+4/(0.05+1.95)-137.5");
        System.out.println(result);
    }
}

