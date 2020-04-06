/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorprogram.calculators;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaakko
 */
public class Calculator {

    private ArrayDeque<String> calculationStack;
    private ArrayList<String> calculationArrayList;
    private boolean brackets;
    private boolean absoluteValue;
    private boolean inf;

    public Calculator() {
        this.calculationStack = new ArrayDeque<>();
        this.calculationArrayList = new ArrayList<>();
    }
    
    public String calcArrayList(String text) {
        this.calculationArrayList = new ArrayList<>();
        splitter(text);
        if (!brackets && !absoluteValue) {
            calculate(this.calculationArrayList);
            return calculationArrayList.get(0);
        } else {
            if (brackets) {
                bracketSolver(calculationArrayList, brackets);   
            }
            if (absoluteValue) {
                absoluteValueSolver(calculationArrayList, absoluteValue);
            }
            calculate(calculationArrayList);
            return calculationArrayList.get(0);
        }
    }
    
    public void splitter(String text) {
        String[] parts = text.split("(?<=[\\+,\\-,\\:,\\^,\\*,\\/,\\(,\\),\\|,\\!,\\%,\\π,e])|(?=[\\+,\\-,\\:,\\^,\\*,\\/,\\(,\\),\\|,\\!,\\%,\\π,e])");
        for (int i = 0; i < parts.length; i++) {
            partChecker(i, parts);
        }
    }    
    
    public void partChecker(int i, String[] parts) {
        if (i > 0) {
            if ((parts[i].equals("(") || parts[i].equals("e") || parts[i].equals("π")) && (isNumber(parts[i - 1]) || parts[i - 1].equals("e") || parts[i - 1].equals("π"))) {
                calculationArrayList.add("*");
            }
        }
        if (parts[i].equals(")")) {
            brackets = true;
        }
        if (parts[i].equals("|")) {
            absoluteValue = true;
        }
        if (parts[i].equals("π")) {
            calculationArrayList.add(3.1415926 + "");
        } else if (parts[i].equals("e")) {
            calculationArrayList.add(2.7182818 + "");
        } else {
            calculationArrayList.add(parts[i]);
        }
    }
    public void checker(int i, String[] parts) {
        if (i > 0) {
            if ((parts[i].equals("(") || parts[i].equals("e") || parts[i].equals("π")) && (isNumber(parts[i - 1]) || parts[i - 1].equals("e") || parts[i - 1].equals("π"))) {
                calculationArrayList.add("*");
            }
        } 
        if (parts[i].equals(")")) {
            brackets = true;
        } 
        if (parts[i].equals("|")) {
            absoluteValue = true;
        } 
        if (parts[i].equals("π")) {
            calculationArrayList.add(3.1415926 + "");
        } else if (parts[i].equals("e")) {
            calculationArrayList.add(2.7182818 + "");
        } else {
            calculationArrayList.add(parts[i]);
        }
    }    
    
    
    
    public ArrayList<String> absoluteValueSolver(ArrayList<String> calcArrayList, boolean absValue) {
        while (true) {
            int[] index = absoluteValueIndexSolver(calcArrayList);
            int absoluteValueStartIndex = index[0];
            int absoluteValueEndIndex = index[1]; 
            if (absoluteValueStartIndex == -1 || absoluteValueEndIndex == -1) {
                break;
            }
            List<String> calculationArrayListParts = calcArrayList.subList(absoluteValueStartIndex + 1, absoluteValueEndIndex);
            calculate(calculationArrayListParts);
            if (Double.valueOf(calcArrayList.get(absoluteValueStartIndex + 1)) < 0) {
                calcArrayList.add(absoluteValueStartIndex + 1, "" + product(-1, Double.valueOf(calcArrayList.get(absoluteValueStartIndex + 1))));
                calcArrayList.remove(absoluteValueStartIndex + 2);
            }
            calcArrayList.remove(absoluteValueStartIndex + 2);
            calcArrayList.remove(absoluteValueStartIndex);
        }
        return calcArrayList; 
    }
    
    public int[] absoluteValueIndexSolver(ArrayList<String> calcArrayList) {
        int absoluteValueStartIndex = -1;
        int absoluteValueEndIndex = -1;
        for (int i = 0; i < calcArrayList.size(); i++) {
            if (calcArrayList.get(i).equals("|")) {
                absoluteValueStartIndex = i;
                for (int j = i + 1; j < calcArrayList.size(); j++) {
                    if (calcArrayList.get(j).equals("|")) {
                        absoluteValueEndIndex = j;
                        break;
                    }
                }
                break;
            }
        }
        int[] startEnd = new int[]{absoluteValueStartIndex, absoluteValueEndIndex};
        return startEnd;
    }
    
    public ArrayList<String> bracketSolver(ArrayList<String> calcArrayList, boolean brackets) {
        while (true) {
            int[] index = bracketIndexSolver(calcArrayList);
            int bracketStartIndex = index[0];
            int bracketEndIndex = index[1]; 
            if (bracketStartIndex == -1 || bracketEndIndex == -1) {
                brackets = false;
                break;
            }

            List<String> calculationArrayListPart = calcArrayList.subList(bracketStartIndex + 1, bracketEndIndex);
            calculate(calculationArrayListPart);
            calcArrayList.remove(bracketStartIndex + 2);
            calcArrayList.remove(bracketStartIndex);
        }
        return calcArrayList;       
    }
    public int[] bracketIndexSolver(ArrayList<String> calcArrayList) {
        int bracketStartIndex = -1;
        int bracketEndIndex = -1;
        for (int i = 0; i < calcArrayList.size(); i++) {
            if (calcArrayList.get(i).equals(")")) {
                bracketEndIndex = i;
                for (int j = i; j >= 0; j--) {
                    if (calcArrayList.get(j).equals("(")) {
                        bracketStartIndex = j;
                        break;
                    }
                }
                break;
            }
        }
        int[] startEnd = new int[]{bracketStartIndex, bracketEndIndex};
        return startEnd;        
    }
    
    public String calculate(List<String> calculatableArrayList) {
        inf = false;
        //checks if the virst value is negative
        if (calculatableArrayList.get(0).equals("-")) {
            calculatableArrayList.add(0, -Double.valueOf(calculatableArrayList.get(1)) + "");
            calculatableArrayList.remove(1);
            calculatableArrayList.remove(1);
        }
        percentSolver(calculatableArrayList);
        factorialSolver(calculatableArrayList);
        powerSolver(calculatableArrayList);
        prodAndEquSolver(calculatableArrayList);
        sumAndDiffSolver(calculatableArrayList);
        if (inf == true) {
            calculatableArrayList.set(0, "ääretön");
            return "ääretön";
        }
        return calculatableArrayList.get(0);
    }
    
    public void percentSolver(List<String> calculatableArrayList) {
        for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("%")) {
                if (isNumber(calculatableArrayList.get(i - 1))) {
                    double firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                    calculatableArrayList.add(i + 1, firstValue / 100 + "");
                    calculatableArrayList.remove(i);
                    calculatableArrayList.remove(i - 1);
                    i--;
                    i--;
                }
            }
        }
    }
    
    public void factorialSolver(List<String> calculatableArrayList) {
        for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("!")) {
                if (isNumber(calculatableArrayList.get(i - 1))) {
                    double firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                    calculatableArrayList.add(i + 1, factorial(firstValue) + "");
                    calculatableArrayList.remove(i);
                    calculatableArrayList.remove(i - 1);
                    i--;
                    i--;
                }
            }
        }
    }
    
    public void powerSolver(List<String> calculatableArrayList) {
        for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("^")) {
                if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
                    double  firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                    double  secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
                    calculatableArrayList.remove(i + 1);
                    calculatableArrayList.add(i + 1, power(firstValue, secondValue) + "");
                    calculatableArrayList.remove(i);
                    calculatableArrayList.remove(i - 1);
                    i--;
                    i--;
                }
            }
        }
    }
    
    public void prodAndEquSolver(List<String> calculatableArrayList) {
        for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("*")) {
                i = productSolver(calculatableArrayList, i);
            } else if (!calculatableArrayList.isEmpty() && (calculatableArrayList.get(i).equals(":") || calculatableArrayList.get(i).equals("/"))) {
                i = quotientSolver(calculatableArrayList, i);
            }
        }
    }
    public int productSolver(List<String> calculatableArrayList, int i) {
        if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
            double firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
            double secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
            calculatableArrayList.remove(i + 1);
            calculatableArrayList.add(i + 1, product(firstValue, secondValue) + "");
            calculatableArrayList.remove(i);
            calculatableArrayList.remove(i - 1);
            i -= 2;
        }
        return i;
    }
    public int quotientSolver(List<String> calculatableArrayList, int i) {
        if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
            double  firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
            double secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
            calculatableArrayList.remove(i + 1);
            calculatableArrayList.add(i + 1, quotient(firstValue, secondValue) + "");
            calculatableArrayList.remove(i);
            calculatableArrayList.remove(i - 1);
            i -= 2;
        }
        return i;
    }
    public void sumAndDiffSolver(List<String> calculatableArrayList) {
        for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("+")) {
                i = sumSolver(calculatableArrayList, i);
            } else if (!calculatableArrayList.isEmpty() && calculatableArrayList.get(i).equals("-")) {
                i = differentialSolver(calculatableArrayList, i);
            }
        }
    }
    public int sumSolver(List<String> calculatableArrayList, int i) {
        if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
            double firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
            double secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
            calculatableArrayList.remove(i + 1);
            calculatableArrayList.add(i + 1, sum(firstValue, secondValue) + "");
            calculatableArrayList.remove(i);
            calculatableArrayList.remove(i - 1);
            i -= 2;
        }
        return i;
    }
    public int differentialSolver(List<String> calculatableArrayList, int i) {
        if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
            double firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
            double secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
            calculatableArrayList.remove(i + 1);
            calculatableArrayList.add(i + 1, sum(firstValue, -secondValue) + "");
            calculatableArrayList.remove(i);
            calculatableArrayList.remove(i - 1);
            i -= 2;
        }
        return i;
    }
    
    public boolean isNumber(String number) {
        try {
            double num = Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public double sum(double x, double y) {
        return x + y;
    }

    public double differential(double x, double y) {
        return x + y;
    }

    public double product(double x, double y) {
        return x * y;
    }

    public double quotient(double x, double y) {
        
        if (Double.isInfinite(x / y)) {
            inf = true;
        }
         
        return x / y;
        
    }

    public double power(double x, double y) {
        double a = Math.pow(x, y);
        return  a;
    }
    
    public int binomial(int x, int y) {
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

    public double factorial(double x) {
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

    public class Exceptions extends Exception {

        public Exceptions(String errorMessage) {
            super(errorMessage);
        }

    }
}
