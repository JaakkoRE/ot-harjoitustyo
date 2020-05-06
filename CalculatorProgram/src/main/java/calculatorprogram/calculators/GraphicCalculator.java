
package calculatorprogram.calculators;



import calculatorprogram.database.Database;
import java.util.ArrayList;


/**
 *
 * @author Jaakko
 */

/**
 * Class is meant for calculating set of y.values based on x cordinates.
 */
public class GraphicCalculator {
    private Calculator calc;
    private Database database;
    private boolean isInputValid;
    /**
     * Stores calculation y values
    */
    private ArrayList<Double> resultList;
    /**
     * Stores each piece of the calculation. For example 5+x-3 in ArrayList form is {5,+,x,-,3}
    */
    private ArrayList<String> calculationArrayList;
    private ArrayList<String> calculationArrayListClone;
    private boolean brackets;
    private boolean absoluteValue;
    
    
    public GraphicCalculator() {
        calc = new Calculator();
        database = new Database(false); 
    }
    /**
    * Method initialises the calculation and returns calculation values
    * 
    * @param calculatable calculatable String for example "5x+2"
    * @param upperBound highest x value that is calculated
    * @param lowerBound lowest x value that is calculated
    * @param precision what is the gap between different x values
    * 
    * 
    * @return ArrayList with coordinate values
    */
    public ArrayList<Double> results(String calculatable, int upperBound, int lowerBound, double precision) throws Calculator.Exceptions {
        this.resultList = new ArrayList<>();
        this.calculationArrayList = new ArrayList<>();
        this.calculationArrayListClone = new ArrayList<>();
        calcArrayListX(calculatable,  upperBound,  lowerBound,  precision);
        return resultList;
    }
    /**
    * Method initialises the calculation and returns calculation values
    * 
    * @param text calculatable String for example "5x+2"
    * @param upperBound highest x value that is calculated
    * @param lowerBound lowest x value that is calculated
    * @param precision what is the gap between different x values
    * 
    * @see brackets
    * @see absoluteValue
    * 
    * 
    */
    public void calcArrayListX(String text, int upperBound, int lowerBound, double precision) throws Calculator.Exceptions {
        //setting up arrayLists
        ArrayList<Integer> whereXIndex = new ArrayList<>();
        splitterX(text);
        isInputValid = true;
        isInputValidX();
        if (!isInputValid) {
            calculationArrayList.clear();
        }
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
                    calculationArrayList = calc.absoluteValueSolver(calculationArrayList);
                }
                if (brackets) {
                    calculationArrayList = calc.bracketSolver(calculationArrayList);
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
    
    /**
    * Method splits Text to ArrayList for example "x+3*2" turns into
    * {x,+,3,*,2} 
    * takes into account x, unlike method splitter in Calculator
    * 
    * @param text calculation in string format for example ("x+3*2)
    * 
    * @see calculationArrayList
    */
    public void splitterX(String text) {
        String[] parts = text.split("(?<=[\\+,\\-,\\:,\\^,\\*,\\/,\\(,\\),\\|,\\!,\\%,\\π,e,x])|(?=[\\+,\\-,\\:,\\^,\\*,\\/,\\(,\\),\\|,\\!,\\%,\\π,e,x])");
        for (int i = 0; i < parts.length; i++) {
            partCheckerX(i, parts);
        }
    }
    
    /**
    * Method adds * to for example "5(2)" so it calculates as 5*(2) 
    * ,adds pi and e values and checks using partsContainsBracketsOrAbsoluteVal if absolutevalues and brackets exist
    * and uses partsDataBaseChecker to check for database values
    * takes into account x, unlike method splitter in Calculator
    * 
    * @param i works as iterator for parts
    * @param parts is the list of split parts from splitter
    * 
    * @see calculationArrayList
    */
    public void partCheckerX(int i, String[] parts) {
        if (i > 0) {
            if ((parts[i].equals("(") || parts[i].equals("e") || parts[i].equals("π") || parts[i].equals("x")) && (calc.isNumber(parts[i - 1]) || parts[i - 1].equals("e") || parts[i - 1].equals("π") || parts[i - 1].equals("x"))) {
                calculationArrayList.add("*");
            }
        }
        partsContainsBracketsOrAbsoluteValX(parts[i]);
        if (!parts[i].equals(parts[i].equals(" "))) {   
            if (parts[i].equals("π")) {
                calculationArrayList.add(3.1415926 + "");
            } else if (parts[i].equals("e")) {
                calculationArrayList.add(2.7182818 + "");
            } else if (parts[i].matches("[a-zA-Z]+") && !parts[i].equals("x")) {
                parts[i] = calc.partsDataBaseChecker(parts[i]);
                calculationArrayList.add(parts[i]);
            } else {
                calculationArrayList.add(parts[i]);
            }   
        }
    }
    /**
    * Method checks if s is a bracket or absolutevalue symbol
    * 
    * @param s part from partcheckers parts[]
    * 
    * @see brackets
    * @see absoluteValue
    */
    public void partsContainsBracketsOrAbsoluteValX(String s) {
        if (s.equals(")")) {
            brackets = true;
        }
        if (s.equals("|")) {
            absoluteValue = true;
        }   
    }
    /**
    * Method checks if s exists in the database (database only answers if the password has been given)
    * 
    * @param s part from partcheckers parts[]
    * 
    * @see database
    */
    public String partsDataBaseCheckerX(String s) {
        s = database.getValue(s);
        if (s.equals("Nimellä ei ole arvoa") || s.equals("Tietokantaa ei ole vielä luotu")) {
            s = "0";
            isInputValid = false;
        }
        calculationArrayList.add(s);
        return s;
    }
    
    /**
    * Method checks if the input given by the user is valid
    * 
    * 
    */
    public void isInputValidX() {

        if (!(calculationArrayList.get(0).equals("x") || calc.isNumber(calculationArrayList.get(0)) || calculationArrayList.get(0).equals("-") || calculationArrayList.get(0).equals("(") || calculationArrayList.get(0).equals("|"))) {
            isInputValid = false; 
        }
        isInputValidMiddleX();
    }
    public void isInputValidMiddleX() {
        for (int i = 1; i < calculationArrayList.size() - 1; i++) {
            if (calculationArrayList.get(i).equals("+") || calculationArrayList.get(i).equals("*") || calculationArrayList.get(i).equals("/") || calculationArrayList.get(i).equals(":") || calculationArrayList.get(i).equals("^")) {
                if (!((calculationArrayList.get(i - 1).equals("x") || calc.isNumber(calculationArrayList.get(i - 1)) || calculationArrayList.get(i - 1).equals(")") || calculationArrayList.get(i - 1).equals("|") || calculationArrayList.get(i - 1).equals("%") || calculationArrayList.get(i - 1).equals("!")) && calculationArrayList.get(i + 1).equals("x") || calc.isNumber(calculationArrayList.get(i + 1)) || calculationArrayList.get(i + 1).equals("(") || calculationArrayList.get(i + 1).equals("|"))) {   
                    isInputValid = false;
                }
            }
            if (calculationArrayList.get(i).equals("%") || calculationArrayList.get(i).equals("!")) {
                if (!(calculationArrayList.get(i - 1).equals("x") || calc.isNumber(calculationArrayList.get(i - 1)) || calculationArrayList.get(i - 1).equals(")") || calculationArrayList.get(i - 1).equals("|")) || calculationArrayList.get(i + 1).equals("x") || calc.isNumber(calculationArrayList.get(i + 1)) || calculationArrayList.get(i + 1).equals("%")) {
                    isInputValid = false;
                }
            }
            if (calculationArrayList.get(i).equals("-")) {
                if (!(calculationArrayList.get(i + 1).equals("(") || calculationArrayList.get(i + 1).equals("|") || calculationArrayList.get(i + 1).equals("x") || calc.isNumber(calculationArrayList.get(i + 1)))) {
                    isInputValid = false;
                }
            }
        }
    } 
    
    /**
    * Method clears calculatable ArrayList and clones the original
    * 
    * @see database
    */
    public void cloneArrayList() {
        calculationArrayList.clear();
        for (String s: calculationArrayListClone) {
            calculationArrayList.add(s);
        }
       
    }
    /**
    * Method clones ArrayList that splitter doesnt have to be used more than once
    *  
    * @see calculationArrayListClone
    */
    public void cloneArrayListOtherWay() {
        for (String s: calculationArrayList) {
            calculationArrayListClone.add(s);
        }
    }   
    
    
}
