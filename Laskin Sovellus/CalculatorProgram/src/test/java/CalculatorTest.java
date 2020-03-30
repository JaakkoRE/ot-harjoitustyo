/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.calculatorprogram.Calculator;
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
public class CalculatorTest {

    Calculator calc;

    public CalculatorTest() {

    }

    @Before
    public void setUp() {
        calc = new Calculator();

    }

    @Test
    public void basicOperations() throws Calculator.Exceptions {
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
        //assertEquals(calc.product(5, -5),"-25");

    }
@Test
    public void binomial() throws Calculator.Exceptions {
       assertEquals(calc.binomial(-5,2), 10.0,0.1);
       assertEquals(calc.binomial(5,-2), 10.0,0.1);
       assertEquals(calc.binomial(-5,-2), 10.0,0.1);
       assertEquals(calc.binomial(12,4), 495,0.1);
       assertEquals(calc.binomial(4,12), 0.0,0.1);
       assertEquals(calc.binomial(0,0), 1.0,0.1);
    }
  

    @Test
    public void calculateSums() throws Calculator.Exceptions {
        assertEquals(calc.calcArrayList("5+5"), "10.0");
        assertEquals(calc.calcArrayList("5-5"), "0.0");
        assertEquals(calc.calcArrayList("5-5+5-2-5"), "-2.0");
        assertEquals(calc.calcArrayList("-5+5"), "0.0");
        assertEquals(calc.calcArrayList("5.5+2.55"), "8.05");

    }

    @Test
    public void calculateProduct() throws Calculator.Exceptions {
        assertEquals(calc.calcArrayList("5*5"), "25.0");
        assertEquals(calc.calcArrayList("-5*5"), "-25.0");
        assertEquals(calc.calcArrayList("5.5*2"), "11.0");
        assertEquals(calc.calcArrayList("5.2*2"), "10.4");

    }
     @Test
    public void calculatePercent() throws Calculator.Exceptions {
        assertEquals(calc.calcArrayList("5%"), "0.05");
        assertEquals(calc.calcArrayList("-5%"), "-0.05");
        assertEquals(calc.calcArrayList("5%+0%"), "0.05");

    }

    @Test
    public void calculateQuotient() throws Calculator.Exceptions {
        assertEquals(calc.calcArrayList("5/5"), "1.0");
        assertEquals(calc.calcArrayList("-5:5"), "-1.0");
        assertEquals(calc.calcArrayList("5.5/2"), "2.75");
        assertEquals(calc.calcArrayList("5.2:2"), "2.6");
    }

    @Test
    public void calculatePower() throws Calculator.Exceptions {
        assertEquals(calc.calcArrayList("5^5"), "3125.0");
        assertEquals(calc.calcArrayList("-5^2"), "25.0");
        assertEquals(calc.calcArrayList("5.5^2"), "30.25");
        assertEquals(calc.calcArrayList("(-5)^0"), "1.0");
        assertEquals(calc.calcArrayList("5^(-2)"), "0.04");
        assertEquals(calc.calcArrayList("4^(1/2)"), "2.0");
    }

    @Test
    public void calculateFactorial() throws Calculator.Exceptions {
        assertEquals(calc.calcArrayList("5!"), "120.0");
        assertEquals(calc.calcArrayList("-5!"), "-120.0");
        assertEquals(calc.calcArrayList("0!"), "1.0");
    }

    @Test
    public void combinedOperations() throws Calculator.Exceptions {
        assertEquals(calc.calcArrayList("-5+5^2*2"), "45.0");
        assertEquals(calc.calcArrayList("-5:5+5-5*2"), "-6.0");
        assertEquals(calc.calcArrayList("1.1^2:1.21+3^2-9/9*9"), "1.0");
    }
    @Test
    public void piAnde() throws Calculator.Exceptions {
        assertEquals(calc.calcArrayList("π+e"), "5.859874400000001");
        assertEquals(calc.calcArrayList("2π(e+2(e))"), "51.238403925568086");
        assertEquals(calc.calcArrayList("π*2^2"), "12.5663704");
    }

    @Test
    public void combinedOperationsWithBrackets() throws Calculator.Exceptions {
        assertEquals(calc.calcArrayList("-5+5^(2+1)"), "120.0");
        assertEquals(calc.calcArrayList("2+(3-1)^(2+1)"), "10.0");
        assertEquals(calc.calcArrayList("2-(-5*(2^0+5))"), "32.0");
        assertEquals(calc.calcArrayList("2(5!+2)^2"), "29768.0");
        assertEquals(calc.calcArrayList("2^(|-2|)"), "4.0");
        assertEquals(calc.calcArrayList("2*|-5|"), "10.0");
    }
}
