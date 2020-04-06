package CalculatorTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import calculatorprogram.calculators.Calculator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class CalculatorTest {

    Calculator calc;

    public CalculatorTest() {

    }

    @Before
    public void setUp() {
        calc = new Calculator();

    }

    @Test
    public void basicOperations() {
        assertEquals(calc.sum(5, 5), 10.0, 0.1);
        assertEquals(calc.sum(5, -5), 0.0, 0.1);
        assertEquals(calc.product(5, 5), 25.0, 0.1);
        assertEquals(calc.product(5, -5), -25.0, 0.1);
        assertEquals(calc.quotient(5, 5), 1.0, 0.1);
        assertEquals(calc.quotient(5, -5), -1.0, 0.1);
        assertEquals(calc.sum(1.55, -1), 0.55, 0.01);
        assertEquals(calc.power(5, 2), 25.0, 0.1);
        assertEquals(calc.factorial(5), 120.0, 0.1);
        assertEquals(calc.factorial(-4), -24.0, 0.1);
        assertEquals(calc.factorial(0), 1, 0.1);

    }
 @Test
    public void binomial() {
       assertEquals(calc.binomial(-5,2), 15.0,0.1);
       assertEquals(calc.binomial(5,-2), 0.0,0.1);
       assertEquals(calc.binomial(-5,-2), 0.0,0.1);
       assertEquals(calc.binomial(12,4), 495,0.1);
       assertEquals(calc.binomial(4,12), 0.0,0.1);
       assertEquals(calc.binomial(0,0), 1.0,0.1);
    }

    @Test
    public void calculateSums() {
        assertEquals(calc.calcArrayList("5+5"), "10.0");
        assertEquals(calc.calcArrayList("5-5"), "0.0");
        assertEquals(calc.calcArrayList("5-5+5-2-5"), "-2.0");
        assertEquals(calc.calcArrayList("-5+5"), "0.0");
        assertEquals(calc.calcArrayList("5.5+2.55"), "8.05");

    }

    @Test
    public void calculateProduct() {
        assertEquals(calc.calcArrayList("5*5"), "25.0");
        assertEquals(calc.calcArrayList("-5*5"), "-25.0");
        assertEquals(calc.calcArrayList("|-5|*5"), "25.0");
        assertEquals(calc.calcArrayList("5.5*2"), "11.0");
        assertEquals(calc.calcArrayList("5.2*2"), "10.4");

    }

    @Test
    public void calculateQuotient() {
        assertEquals(calc.calcArrayList("5/5"), "1.0");
        assertEquals(calc.calcArrayList("-5:5"), "-1.0");
        assertEquals(calc.calcArrayList("5.5/2"), "2.75");
        assertEquals(calc.calcArrayList("5.2:2"), "2.6");
        assertEquals(calc.calcArrayList("5/0"), "ääretön");
    }

    @Test
    public void calculatePower() {
        assertEquals(calc.calcArrayList("5^5"), "3125.0");
        assertEquals(calc.calcArrayList("-5^2"), "25.0");
        assertEquals(calc.calcArrayList("5.5^2"), "30.25");
        assertEquals(calc.calcArrayList("(-5)^0"), "1.0");
        assertEquals(calc.calcArrayList("5^(-2)"), "0.04");
        assertEquals(calc.calcArrayList("4^(1/2)"), "2.0");
    }

    @Test
    public void calculateFactorial() {
        assertEquals(calc.calcArrayList("5!"), "120.0");
        assertEquals(calc.calcArrayList("-5!"), "-120.0");
        assertEquals(calc.calcArrayList("0!"), "1.0");
    }

    @Test
    public void combinedOperations() {
        assertEquals(calc.calcArrayList("-5+5^2*2"), "45.0");
        assertEquals(calc.calcArrayList("-5:5+5-5*2"), "-6.0");
        assertEquals(calc.calcArrayList("1.1^2:1.21+3^2-9/9*9"), "1.0");
    }
    @Test
    public void piAnde() {
        assertEquals(calc.calcArrayList("π+e"), "5.859874400000001");
        assertEquals(calc.calcArrayList("2π(e+2(e))"), "51.238403925568086");
        assertEquals(calc.calcArrayList("π*2^2"), "12.5663704");
    }

    @Test
    public void combinedOperationsWithBrackets() {
        assertEquals(calc.calcArrayList("-5+5^(2+1)"), "120.0");
        assertEquals(calc.calcArrayList("2+(3-1)^(2+1)"), "10.0");
        assertEquals(calc.calcArrayList("2-(-5*(2^0+5))"), "32.0");
        assertEquals(calc.calcArrayList("2(5!+2)^2"), "29768.0");
    }
}
