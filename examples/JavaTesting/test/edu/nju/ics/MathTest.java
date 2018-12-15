package edu.nju.ics;

import org.junit.Test;
import static org.junit.Assert.*;

public class MathTest {
    @Test
    public void fibonacci() throws Exception {
        assertEquals(21, new Math().fibonacci(18));
    }

    @Test
    public void testFactorial() throws Exception {
        assertEquals(120, new Math().factorial(10));
    }
}