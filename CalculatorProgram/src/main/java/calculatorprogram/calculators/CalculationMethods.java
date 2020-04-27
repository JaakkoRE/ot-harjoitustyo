/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorprogram.calculators;

import calculatorprogram.database.Database;

/**
 *
 * @author Jaakko
 */
public class CalculationMethods {
    private boolean inf;
    private Database database;
    public CalculationMethods() {
        database = new Database();
    }
    public double sum(double x, double y) {
        return (double) x + (double) y;
    }

    public double differential(double x, double y) {
        return (double) x + (double) y;
    }

    public double product(double x, double y) {
        return (double) x * (double) y;
    }

    public double quotient(double x, double y) {
        inf = false;
        if (Double.isInfinite(x / y)) {
            inf = true;
        }
         
        return (double) x / (double) y;
    }
    public boolean quotientInf() {
        
        return inf;
    }

    public double power(double x, double y) {
        double a = Math.pow((double) x, (double) y);
        return  a;
    }
    
    public int factorial(double x) {
//        try {
//            if (!(x == Math.floor(x)) && !Double.isInfinite(x)) {
//                throw new Exceptions("kertoman arvo ei ole kokonaisluku");
//            }
//        } catch (Exception e) {}
        int factorial = (int) x;
        if (x == 0) {
            return 1;
        }
        if (x < 0) {
            x = product(x, -1);
            for (int i = (int) x - 1; i > 0; i--) {
                factorial *= i;
            }
            return factorial;
        } else {
            for (int i = (int) x - 1; i > 0; i--) {
                factorial *= i;
            }
            return factorial;
        }
    }
    
    /**
    * Method solves combination problems
    * 
    * @param xS string with given value for example "5"
    * @param yS string with given value for example "5"
    * 
    * @return solution
    */
    public int binomial(String xS, String yS) throws Exception {
        int y = valCorrector(yS);
        int x = valCorrector(xS);
        if (y < 0) {
            return 0;
        }
        if (x < 0) {
            x = -x + 1; 
        }
        int[][] binom = new int[x + 1][y + 1];
        binom[0][0] = 1;
        for (int i = 1; i <= x; i++) {
            binom[i][0] = 1;
            for (int j = 1; j <= y; j++) {
                binom[i][j] = binom[i - 1][j - 1] + binom[i - 1][j];
            }
             
        }
        return binom[x][y];
    }
    
    /**
    * Method solves permutational problems
    * 
    * @param xS string with given value for example "5"
    * @param yS string with given value for example "5"
    * 
    * @return solution
    */
    public int permutational(String xS, String yS) throws Exception {
        int x = valCorrector(xS);  
        int y = valCorrector(yS);
        if (x < y) {
            return 0;
        }
        if (y == 1) {
            return x;
        }
        return (int) (factorial(x) / factorial(x - y));
    }
    
    /**
    * Method corrects values given to permutational and binomial
    * 
    * @param s checks if given value is a proper number or belongs to database (database only answers if the password has been given)
    * 
    * @return boolean true if is number else false
    */
    public int valCorrector(String s) throws Exception { 
        if (s.matches("[a-zA-Z]+")) {
            s = database.getValue(s);
            if (s.equals("Nimellä ei ole arvoa") || s.equals("Tietokantaa ei ole vielä luotu")) {
                s = "0";
            }
            String[] split = s.split("\\.");
            if (split[1].equals("0")) {
                return Integer.valueOf(split[0]);
            } else {
                throw new Exception();
            }    
        }
        try { 
            Integer.parseInt(s); 
            return Integer.valueOf(s);
        } catch (Exception e) { 
            throw new Exception();
        }   
    }
}
