package khrushchev.ilya.NumberSequence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberSequenceTest {
    Fibonacci fibonacciGenerator = new Fibonacci();
    Leonardo leonardoGenerator = new Leonardo();
    @Test
    void testFibonacciGenerator() {
        assertArrayEquals(new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610},
                            fibonacciGenerator.generateSequence(15));
    }

    @Test
    void testLeonardoGenerator(){
        assertArrayEquals(new int[]{1, 1, 3, 5, 9, 15, 25, 41, 67, 109, 177, 287, 465, 753, 1219},
                            leonardoGenerator.generateSequence(15));
    }
}