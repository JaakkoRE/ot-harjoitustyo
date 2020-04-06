package CalculatorTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import calculatorprogram.calculators.Calculator;
import calculatorprogram.calculators.GraphicCalculator;
import java.util.ArrayList;
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
public class GraphicCalculatorTest {
    GraphicCalculator graphCalc = new GraphicCalculator();
    ArrayList<Double> expectdResults;
    public GraphicCalculatorTest() {

    }
    
    @Before
    public void setUp() {
        graphCalc = new GraphicCalculator();
        expectdResults = new ArrayList<>();
        
    }
    @Test
    public void operations() throws Calculator.Exceptions{
        ArrayList<Double> results = graphCalc.results("x(x^2)+5-x", 1, 0, 0.1);
        expectdResults.add(5.0);
        expectdResults.add(4.901);
        expectdResults.add(4.808);
        expectdResults.add(4.727);
        expectdResults.add(4.664);
        expectdResults.add(4.625);
        expectdResults.add(4.616);
        expectdResults.add(4.643);
        expectdResults.add(4.712);
        expectdResults.add(4.829);
        expectdResults.add(5.0);
        assertEquals(expectdResults, results);
    }
    @Test
    public void operations2() throws Calculator.Exceptions{
        ArrayList<Double> results = graphCalc.results("|2x|*(5)^x+100%", 1, -1, 1);
        expectdResults.add(1.4);
        expectdResults.add(1.);
        expectdResults.add(11.);
        assertEquals(expectdResults, results);
    }
    @Test
    public void operations3() throws Calculator.Exceptions{
        ArrayList<Double> results = graphCalc.results("5x", -5, 5, 1);
        assertEquals(expectdResults, results);
    }
    @Test
    public void operations4() throws Calculator.Exceptions{
        ArrayList<Double> results = graphCalc.results("5x", 0, 0, 1);
        expectdResults.add(0.);
        assertEquals(expectdResults, results);
    }
    @Test
    public void operations5() throws Calculator.Exceptions{
        ArrayList<Double> results = graphCalc.results("|x|*(5(5x))", 1, -4, 1);
        expectdResults.add(-400.);
        expectdResults.add(-225.);
        expectdResults.add(-100.);
        expectdResults.add(-25.);
        expectdResults.add(0.);
        expectdResults.add(25.);
        assertEquals(expectdResults, results);
    }
    @Test
     public void basicCalcOperationsWork() throws Calculator.Exceptions{
        ArrayList<Double> results = graphCalc.results("100%*e+π-2x!+x/(2^|-1|)", 1, 0, 1);
        expectdResults.add(3.859874);
        expectdResults.add(4.359874);
        assertEquals(expectdResults, results);
    }
    
}
