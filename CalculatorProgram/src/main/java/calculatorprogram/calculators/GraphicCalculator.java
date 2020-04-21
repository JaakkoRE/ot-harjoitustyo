/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorprogram.calculators;



import calculatorprogram.database.Database;
import java.util.ArrayList;


/**
 *
 * @author Jaakko
 */
public class GraphicCalculator {
    Calculator calc;
    Database database;
    ArrayList<Double> resultList;
    private ArrayList<String> calculationArrayList;
    private ArrayList<String> calculationArrayListClone;
    private boolean brackets;
    private boolean absoluteValue;
    
    public GraphicCalculator() {
        calc = new Calculator();
        database = new Database(); 
    }
    
    public ArrayList<Double> results(String calculatable, int upperBound, int lowerBound, double precision) throws Calculator.Exceptions {
        resultList = new ArrayList<>();
        calcArrayListX(calculatable,  upperBound,  lowerBound,  precision);
        return resultList;
    }
    public void calcArrayListX(String text, int upperBound, int lowerBound, double precision) throws Calculator.Exceptions {
        this.calculationArrayList = new ArrayList<>();
        this.calculationArrayListClone = new ArrayList<>();
        ArrayList<Integer> whereXIndex = new ArrayList<>();
        //setting up arrayLists
        splitterX(text);
        Boolean bracketsCopy = brackets;
        Boolean absoluteValueCopy = absoluteValue;
        cloneArrayListOtherWay();
        for (int i = 0; i < calculationArrayList.size(); i++) {
            if (calculationArrayList.get(i).equals("x")) {
                whereXIndex.add(i);
            }
        }
            //iterate trough all x values
        for (double k = lowerBound; k <= upperBound; k += precision) {
            k = Math.round(k * 100000.0) / 100000.0;
                //clone values to original form
            brackets = bracketsCopy;
            absoluteValue = absoluteValueCopy;
            cloneArrayList();
            for (Integer index: whereXIndex) {    
                calculationArrayList.set(index, k + "");
            }    
            if (!brackets && !absoluteValue) {
                String result = calc.calculate(calculationArrayList);
                if (result.equals("ääretön")) {
                    resultList.add(200000.);
                } else {
                    double resultVal = Double.valueOf(result);
                    double resultRound = Math.round(resultVal * 1000000.0) / 1000000.0;
                    resultList.add(resultRound);
                }
            } else {
                //solves absolute values and brackets before calculation
                if (absoluteValue) {
                    calculationArrayList = calc.absoluteValueSolver(calculationArrayList, absoluteValue);
                }
                if (brackets) {
                    calculationArrayList = calc.bracketSolver(calculationArrayList, brackets);
                }
                String result = calc.calculate(calculationArrayList);
                if (result.equals("ääretön")) {
                    resultList.add(200000.);
                } else {
                    double resultVal = Double.valueOf(result);
                    double resultRound = Math.round(resultVal * 1000000.0) / 1000000.0;
                    resultList.add(resultRound);
                }
            }   
        }
    }
    public void splitterX(String text) {
        String[] parts = text.split("(?<=[\\+,\\-,\\:,\\^,\\*,\\/,\\(,\\),\\|,\\!,\\%,\\π,e,x])|(?=[\\+,\\-,\\:,\\^,\\*,\\/,\\(,\\),\\|,\\!,\\%,\\π,e,x])");
        for (int i = 0; i < parts.length; i++) {
            partCheckerX(i, parts);
        }
    }
    public void partCheckerX(int i, String[] parts) {
        if (i > 0) {
            if ((parts[i].equals("(") || parts[i].equals("e") || parts[i].equals("π") || parts[i].equals("x")) && (calc.isNumber(parts[i - 1]) || parts[i - 1].equals("e") || parts[i - 1].equals("π") || parts[i - 1].equals("x"))) {
                calculationArrayList.add("*");
            }
        }
        partsContainsBracketsOrAbsoluteValX(parts[i]);
        if (parts[i].equals("π")) {
            calculationArrayList.add(3.1415926 + "");
        } else if (parts[i].equals("e")) {
            calculationArrayList.add(2.7182818 + "");
        } else if (parts[i].matches("[a-zA-Z]+") && !parts[i].equals("x")) {
            parts[i] = partsDataBaseCheckerX(parts[i]);
        } else {
            calculationArrayList.add(parts[i]);
        }
    }
    
    public void partsContainsBracketsOrAbsoluteValX(String s) {
        if (s.equals(")")) {
            brackets = true;
        }
        if (s.equals("|")) {
            absoluteValue = true;
        }   
    }
    
    public String partsDataBaseCheckerX(String s) {
        s = database.getValue(s);
        if (s.equals("Nimellä ei ole arvoa") || s.equals("Tietokantaa ei ole vielä luotu")) {
            s = "0";
        }
        calculationArrayList.add(s);
        return s;
    }
    
    public void cloneArrayList() {
        calculationArrayList.clear();
        for (String s: calculationArrayListClone) {
            calculationArrayList.add(s);
        }
       
    }
    public void cloneArrayListOtherWay() {
        for (String s: calculationArrayList) {
            calculationArrayListClone.add(s);
        }
    }   

    
}
