/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import calculatorprogram.calculators.CalculationMethods;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class CalculationMethodTest {
    CalculationMethods cm;
     @Before
    public void setUp() {
        cm = new CalculationMethods();

    }

     @Test
    public void basicOperations() {
        assertEquals(cm.sum(5, 5), 10.0, 0.1);
        assertEquals(cm.sum(5, -5), 0.0, 0.1);
        assertEquals(cm.product(5, 5), 25.0, 0.1);
        assertEquals(cm.product(5, -5), -25.0, 0.1);
        assertEquals(cm.quotient(5, 5), 1.0, 0.1);
        assertEquals(cm.quotient(5, -5), -1.0, 0.1);
        assertEquals(cm.sum(1.55, -1), 0.55, 0.01);
        assertEquals(cm.power(5, 2), 25.0, 0.1);
        assertEquals(cm.factorial(5), 120.0, 0.1);
        assertEquals(cm.factorial(-4), -24.0, 0.1);
        assertEquals(cm.factorial(0), 1, 0.1);

    }
 @Test
    public void binomial() throws Exception {
       assertEquals(cm.binomial("-5","2"), 15.0,0.1);
       assertEquals(cm.binomial("5","-2"), 0.0,0.1);
       assertEquals(cm.binomial("-5","-2"), 0.0,0.1);
       assertEquals(cm.binomial("12","4"), 495,0.1);
       assertEquals(cm.binomial("4","12"), 0.0,0.1);
       assertEquals(cm.binomial("0","0"), 1.0,0.1);
    }
    @Test
    public void permutations() throws Exception {
       assertEquals(cm.permutational("5","2"), 20);
       assertEquals(cm.permutational("12","4"), 11880);
       assertEquals(cm.permutational("2","5"), 0);
       assertEquals(cm.permutational("0","0"), 1);
       assertEquals(cm.permutational("1000","1"), 1000);
    }
}
