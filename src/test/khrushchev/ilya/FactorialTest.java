package khrushchev.ilya;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    Factorial factorial = new Factorial();

    @Test
    void testRecursiveFactorial() {
        assertEquals(1,factorial.getRecursiveFactorial(0));
        assertEquals(2,factorial.getRecursiveFactorial(2));
        assertEquals(5040,factorial.getRecursiveFactorial(7));
    }

    @Test
    void testFactorial() {
        assertEquals(1,factorial.getFactorial(0));
        assertEquals(1,factorial.getFactorial(1));
        assertEquals(5040,factorial.getFactorial(7));
    }
}