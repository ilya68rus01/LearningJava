package khrushchev.ilya.Calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calc = new Calculator();
    @Test
    void addTest() {
        assertEquals("2.0",calc.calculate("1+1"));
        assertEquals("13.85",calc.calculate("1.55+12.3"));
        assertEquals("4.0",calc.calculate("1+1+2"));
    }
    @Test
    void substractTest() {
        assertEquals("0.0",calc.calculate("1-1"));
        assertEquals("-10.75",calc.calculate("1.55-12.3"));
        assertEquals("-2.0",calc.calculate("1-1-2"));
    }
    @Test
    void multTest() {
        assertEquals("1.0",calc.calculate("1*1"));
        assertEquals("19.065",calc.calculate("1.55*12.3"));
        assertEquals("2.0",calc.calculate("1*1*2"));
    }
    @Test
    void divisoonTest() {
        assertEquals("1.0",calc.calculate("1/1"));
        assertEquals("0.125",calc.calculate("1.5/12"));
        assertEquals("0.5",calc.calculate("1/1/2"));
    }

    @Test
    void fullTest() {
        assertEquals("-100.0",calc.calculate("3.125*12-(18/(3*3))+4/(0.05+1.95)-137.5"));
        assertEquals("4.0",calc.calculate("1+(1*1)-(1/1)*(1+1)/(1-2)"));
    }

}