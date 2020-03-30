/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculatorprogram;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jaakko
 */
public class Calculator {

    private ArrayDeque<String> calculationStack;
    private ArrayList<String> calculationArrayList;
    private HashMap<Integer, Integer> parenthesis;
    private boolean brackets;
    private boolean absoluteValue;

    public Calculator() {
        this.calculationStack = new ArrayDeque<>();
        this.calculationArrayList = new ArrayList<>();
    }

    public double StackCalc(String text) throws Exceptions {
        boolean error = false;
        boolean calculated = false;
        Splitter(text);

        boolean firstNumberFilled = false;
        boolean secondNumberFilled = false;
        double secondNumber = 0;
        double calculationResult = 0;
        while (!calculated && !error) {

            //if the first part is number.
            if (firstNumberFilled == false) {
                if (isNumber(calculationStack.getFirst())) {
                    calculationResult = Double.valueOf(calculationStack.getFirst());
                    calculationStack.removeFirst();
                    firstNumberFilled = true;
                } else {
                    error = true;
                }
            } else {
                //if the first part is operation
                if (calculationStack.getFirst().equals("+")) {
                    calculationStack.removeFirst();
                    if (isNumber(calculationStack.getFirst()) && !calculationStack.isEmpty()) {
                        secondNumber = Double.valueOf(calculationStack.getFirst());
                    } else {
                        error = true;
                    }
                    calculationResult = sum(calculationResult, secondNumber);
                    calculationStack.removeFirst();
                } else if (calculationStack.getFirst().equals("-")) {
                    calculationStack.removeFirst();
                    if (isNumber(calculationStack.getFirst()) && !calculationStack.isEmpty()) {
                        secondNumber = Double.valueOf(calculationStack.getFirst());
                    } else {
                        error = true;
                    }
                    calculationResult = sum(calculationResult, -secondNumber);
                    calculationStack.removeFirst();
                } else if (calculationStack.getFirst().equals("*")) {
                    calculationStack.removeFirst();
                    if (isNumber(calculationStack.getFirst()) && !calculationStack.isEmpty()) {
                        secondNumber = Double.valueOf(calculationStack.getFirst());
                    } else {
                        error = true;
                    }
                    calculationResult = product(calculationResult, secondNumber);
                    calculationStack.removeFirst();
                } else if (calculationStack.getFirst().equals(":")) {
                    calculationStack.removeFirst();
                    if (isNumber(calculationStack.getFirst()) && !calculationStack.isEmpty()) {
                        secondNumber = Double.valueOf(calculationStack.getFirst());
                    } else {
                        error = true;
                    }
                    calculationResult = quotient(calculationResult, secondNumber);
                    calculationStack.removeFirst();
                } else if (calculationStack.getFirst().equals("^")) {
                    calculationStack.removeFirst();
                    if (isNumber(calculationStack.getFirst()) && !calculationStack.isEmpty()) {
                        secondNumber = Double.valueOf(calculationStack.getFirst());
                    } else {
                        error = true;
                    }
                    calculationResult = power(calculationResult, secondNumber);
                    calculationStack.removeFirst();
                } else {
                    error = true;
                }
            }

            if (calculationStack.isEmpty()) {
                calculated = true;
            }
        }
        if (!error) {
            return calculationResult;
        }
        return -1;
    }

    public String calcArrayList(String text) throws Exceptions {
        brackets = false;
        this.calculationArrayList = new ArrayList<>();
        this.parenthesis = new HashMap<>();
        Splitter(text);
        if (!brackets && !absoluteValue) {
            return calculate(this.calculationArrayList);
        } else {
            //solves absolute value before calculation  
            if (absoluteValue) {
                while (absoluteValue) {
                    int absoluteValueStartIndex = -1;
                    int absoluteValueEndIndex = -1;
                    for (int i = 0; i < calculationArrayList.size(); i++) {
                        if (calculationArrayList.get(i).equals("|")) {
                            absoluteValueStartIndex = i;
                            for (int j = i + 1; j < calculationArrayList.size(); j++) {
                                if (calculationArrayList.get(j).equals("|")) {
                                    absoluteValueEndIndex = j;
                                    break;
                                }
                            }
                            break;

                        }
                    }
                    if (absoluteValueStartIndex == -1 || absoluteValueEndIndex == -1) {
                        absoluteValue = false;
                        break;
                    }

                    List<String> calculationArrayListParts = calculationArrayList.subList(absoluteValueStartIndex + 1, absoluteValueEndIndex);
                    calculate(calculationArrayListParts);
                    if (Double.valueOf(calculationArrayList.get(absoluteValueStartIndex + 1)) < 0) {
                        calculationArrayList.add(absoluteValueStartIndex + 1, "" + product(-1, Double.valueOf(calculationArrayList.get(absoluteValueStartIndex + 1))));
                        calculationArrayList.remove(absoluteValueStartIndex + 2);
                    }
                    calculationArrayList.remove(absoluteValueStartIndex + 2);
                    calculationArrayList.remove(absoluteValueStartIndex);
                }
            }
            if (brackets) //solves brackets before calculation  
            {
                while (brackets) {
                    int bracketStartIndex = -1;
                    int bracketEndIndex = -1;
                    for (int i = 0; i < calculationArrayList.size(); i++) {
                        if (calculationArrayList.get(i).equals(")")) {
                            bracketEndIndex = i;
                            for (int j = i; j >= 0; j--) {
                                if (calculationArrayList.get(j).equals("(")) {
                                    bracketStartIndex = j;
                                    break;
                                }
                            }
                            break;

                        }
                    }
                    if (bracketStartIndex == -1 || bracketEndIndex == -1) {
                        brackets = false;
                        break;
                    }

                    List<String> calculationArrayListPart = calculationArrayList.subList(bracketStartIndex + 1, bracketEndIndex);
                    calculate(calculationArrayListPart);
                    int removeIndex = bracketStartIndex + 2;
                    calculationArrayList.remove(bracketStartIndex + 2);
                    calculationArrayList.remove(bracketStartIndex);
                }
            }
            calculate(calculationArrayList);

            return calculationArrayList.get(0);
        }
    }

    public String calculate(List<String> calculatableArrayList) throws Exceptions {
        double firstValue = 0;
        double secondValue = 0;
        //checks if the virst value is negative
        if (calculatableArrayList.get(0).equals("-")) {
            calculatableArrayList.add(0, -Double.valueOf(calculatableArrayList.get(1)) + "");
            calculatableArrayList.remove(1);
            calculatableArrayList.remove(1);
        }
        //percent conversion, π and e conversions
 for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("%")) {
                if (isNumber(calculatableArrayList.get(i - 1))) {
                    firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                    calculatableArrayList.add(i + 1, firstValue/100 + "");
                    calculatableArrayList.remove(i);
                    calculatableArrayList.remove(i - 1);
                    i--;
                    i--;
                }
            }
        }

        //factorial
        for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("!")) {
                if (isNumber(calculatableArrayList.get(i - 1))) {
                    firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                    calculatableArrayList.add(i + 1, factorial(firstValue) + "");
                    calculatableArrayList.remove(i);
                    calculatableArrayList.remove(i - 1);
                    i--;
                    i--;
                }
            }
        }
        //power
        for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("^")) {
                if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
                    firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                    secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
                    calculatableArrayList.remove(i + 1);
                    calculatableArrayList.add(i + 1, power(firstValue, secondValue) + "");
                    calculatableArrayList.remove(i);
                    calculatableArrayList.remove(i - 1);
                    i--;
                    i--;
                }
            }
        }
        for (int i = 0; i < calculatableArrayList.size(); i++) {

            //Product and quotient
            if (calculatableArrayList.get(i).equals("*")) {
                if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
                    firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                    secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
                    calculatableArrayList.remove(i + 1);
                    calculatableArrayList.add(i + 1, product(firstValue, secondValue) + "");
                    calculatableArrayList.remove(i);
                    calculatableArrayList.remove(i - 1);
                    i--;
                    i--;
                }
            } else if (!calculatableArrayList.isEmpty() && (calculatableArrayList.get(i).equals(":") || calculatableArrayList.get(i).equals("/"))) {
                if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
                    firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                }
                secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
                calculatableArrayList.remove(i + 1);
                calculatableArrayList.add(i + 1, quotient(firstValue, secondValue) + "");
                calculatableArrayList.remove(i);
                calculatableArrayList.remove(i - 1);
                i--;
                i--;
            }
        }
        //sum
        for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("+")) {
                if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
                    firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                    secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
                    calculatableArrayList.remove(i + 1);
                    calculatableArrayList.add(i + 1, sum(firstValue, secondValue) + "");
                    calculatableArrayList.remove(i);
                    calculatableArrayList.remove(i - 1);
                    i--;
                    i--;
                }
            } else if (!calculatableArrayList.isEmpty() && calculatableArrayList.get(i).equals("-")) {
                if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
                    firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                }
                secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
                calculatableArrayList.remove(i + 1);
                calculatableArrayList.add(i + 1, sum(firstValue, -secondValue) + "");
                calculatableArrayList.remove(i);
                calculatableArrayList.remove(i - 1);
                i--;
                i--;
            }
        }
        return calculatableArrayList.get(0);
    }

    public static boolean isNumber(String number) {

        try {
            double num = Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void Splitter(String text) {
        String[] parts = text.split("(?<=[\\+,\\-,\\:,\\^,\\*,\\/,\\(,\\),\\|,\\!,\\%,\\π,e])|(?=[\\+,\\-,\\:,\\^,\\*,\\/,\\(,\\),\\|,\\!,\\%,\\π,e])");

        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                if ((parts[i].equals("(")||parts[i].equals("e")||parts[i].equals("π"))&& (isNumber(parts[i - 1])||parts[i - 1].equals("e") ||parts[i - 1].equals("π") )) {
                    calculationArrayList.add("*");
                }

            }
            if (parts[i].equals(")")) {
                brackets = true;
            }
            if (parts[i].equals("|")) {
                absoluteValue = true;
            }
            
            if(parts[i].equals("π")){
                 calculationArrayList.add(3.1415926+"");
            }
            else if(parts[i].equals("e")){
                 calculationArrayList.add(2.7182818+"");
            }else
            calculationArrayList.add(parts[i]);

        }
    }

    public double sum(double x, double y) {
        return x + y;
    }

    
    public double product(double x, double y) {
        return x * y;
    }

    public double quotient(double x, double y) {
        return x / y;
    }

    public double power(double x, double y) {
        double a = Math.pow(x, y);
        return  a;
    }
     public double binomial(double x, double y) {
          if(x<0){
            x=product(x,-1);
        }
        if(y<0){
            y=product(y,-1);
        }
        
        try {
            if (!(x == Math.floor(x)) && !Double.isInfinite(x)&&!(y == Math.floor(y)) &&!Double.isInfinite(y)) {
                throw new Exceptions("permutaation arvo ei ole kokonaisluku");
            }
        } catch (Exception e) {

        }
    
       
         int binomialX = (int) x;
         int binomialY = (int) y;
         double[][] binom=new double[binomialX+1][binomialY+1];
         binom[0][0] = 1;
         for(int i =1;i<=binomialX;i++){
             binom[i][0] = 1;
             for(int j=1; j<=binomialY; j++){
                 binom[i][j] = binom[i-1][j-1]+binom[i-1][j];
             }
             
         }
        return binom[binomialX][binomialY];
     }

    public double factorial(double x) {
        try {
            if (!(x == Math.floor(x)) && !Double.isInfinite(x)) {
                throw new Exceptions("permutaation arvo ei ole kokonaisluku");
            }
        } catch (Exception e) {

        }
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

    public class Exceptions extends Exception {

        public Exceptions(String errorMessage) {
            super(errorMessage);
        }

    }
}
