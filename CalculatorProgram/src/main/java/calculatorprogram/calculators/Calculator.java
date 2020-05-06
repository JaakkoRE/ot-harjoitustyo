
package calculatorprogram.calculators;

import calculatorprogram.database.Database;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jaakko
 */

/**
 * Class is meant for calculating input based on given String
 */
public class Calculator {
    /**
     * Stores each piece of the calculation. For example 5+2-3 in ArrayList form is {5,+,2,-,3}
    */
    private ArrayList<String> calculationArrayList;
    private boolean brackets;
    private boolean absoluteValue;
    private boolean inf;
    private Database database;
    private boolean nameDoesntExistInDatabase;
    private String errorMessegeNameDoesntExistInDatabase;
    private CalculationMethods cm;
    private boolean isInputValid;


    public Calculator() {
        cm = new CalculationMethods();
        this.database = new Database(false);
        this.calculationArrayList = new ArrayList<>();
    }
    
    /**
    * Method splits calculation to parts with splitter´
    * and with bracketSolver and absoluteValueSolver splits the calculation to multiple parts
    * and uses calculate method to calculate the rest
    * 
    * @param text calculation in string format for example ("5+3*2)
    * 
    * @return String for example "11"
    */
    public String calcArrayList(String text) {
        calcArrayListSetup(text);
        if (nameDoesntExistInDatabase) {
            return errorMessegeNameDoesntExistInDatabase;
        }
        if (!brackets && !absoluteValue) {
            calculate(this.calculationArrayList);
            return calculationArrayList.get(0);
        } else {
            if (brackets) {
                bracketSolver(calculationArrayList);   
            }
            if (absoluteValue) {
                absoluteValueSolver(calculationArrayList);
            }
            calculate(calculationArrayList);
            return calculationArrayList.get(0);
        }
    }
    /**
    * Method sets up modifiers to default values
    * 
    */
    public void calcArrayListSetup(String text) {
        brackets = false;
        absoluteValue = false;
        nameDoesntExistInDatabase = false;
        this.calculationArrayList = new ArrayList<>();
        splitter(text);
    }
    /**
    * Method splits Text to ArrayList for example "5+3*2" turns into
    * {5,+,3,*,2} 
    * 
    * @param text calculation in string format for example ("5+3*2)
    * 
    * @see calculationArrayList
    */
    public void splitter(String text) {
        String[] parts = text.split("(?<=[\\+,\\-,\\:,\\^,\\*,\\/,\\(,\\),\\|,\\!,\\%,\\π,e])|(?=[\\+,\\-,\\:,\\^,\\*,\\/,\\(,\\),\\|,\\!,\\%,\\π,e])");
        for (int i = 0; i < parts.length; i++) {
            partChecker(i, parts);
        }
        isInputValid = isInputValid();
        if (!isInputValid) {
            calculationArrayList.clear();
            calculationArrayList.add("Virheellinen syöte");
        }
    }    
    /**
    * Method adds * to for example "5(2)" so it calculates as 5*(2) 
    * ,adds pi and e values and checks using partsContainsBracketsOrAbsoluteVal if absolutevalues and brackets exist
    * and uses partsDataBaseChecker to check for database values
    * 
    * @param i works as iterator for parts
    * @param parts is the list of split parts from splitter
    * 
    * @see calculationArrayList
    */
    public void partChecker(int i, String[] parts) {
        if (i > 0) {
            if ((parts[i].equals("(") || parts[i].equals("e") || parts[i].equals("π")) && (isNumber(parts[i - 1]) || parts[i - 1].equals("e") || parts[i - 1].equals("π"))) {
                calculationArrayList.add("*");
            }
        }
        partsContainsBracketsOrAbsoluteVal(parts[i]);
        if (!parts[i].equals(parts[i].equals(" "))) {     
            if (parts[i].equals("π")) {
                calculationArrayList.add(3.1415926 + "");
            } else if (parts[i].equals("e")) {
                calculationArrayList.add(2.7182818 + "");
            } else if (parts[i].matches("[a-zA-Z]+")) {
                parts[i] = partsDataBaseChecker(parts[i]);
            } else {
                calculationArrayList.add(parts[i]);
            }
        }
    }
    
    /**
    * Method checks if the input given by the user is valid
    * 
    * 
    */
    public boolean isInputValid() {

        if (!(isNumber(calculationArrayList.get(0)) || calculationArrayList.get(0).equals("-") || calculationArrayList.get(0).equals("(") || calculationArrayList.get(0).equals("|"))) {
            return false;
        }
        return isInputValidMiddle();
    }
    public boolean isInputValidMiddle() {
        for (int i = 1; i < calculationArrayList.size() - 1; i++) {
            if (calculationArrayList.get(i).equals("+") || calculationArrayList.get(i).equals("*") || calculationArrayList.get(i).equals("/") || calculationArrayList.get(i).equals(":") || calculationArrayList.get(i).equals("^")) {
                if (!((isNumber(calculationArrayList.get(i - 1)) || calculationArrayList.get(i - 1).equals(")") || calculationArrayList.get(i - 1).equals("|") || calculationArrayList.get(i - 1).equals("%") || calculationArrayList.get(i - 1).equals("!")) && isNumber(calculationArrayList.get(i + 1)) || calculationArrayList.get(i + 1).equals("(") || calculationArrayList.get(i + 1).equals("|"))) {        
                    return false;
                }
            }
            if (calculationArrayList.get(i).equals("%") || calculationArrayList.get(i).equals("!")) {
                if (!(isNumber(calculationArrayList.get(i - 1)) || calculationArrayList.get(i - 1).equals(")") || calculationArrayList.get(i - 1).equals("|")) || isNumber(calculationArrayList.get(i + 1)) || calculationArrayList.get(i + 1).equals("%")) {
                    return false;
                }
            }
            if (calculationArrayList.get(i).equals("-")) {
                if (!(calculationArrayList.get(i + 1).equals("(") || calculationArrayList.get(i + 1).equals("|") || isNumber(calculationArrayList.get(i + 1)))) {
                    return false;
                }
            }
        }
        return true;
    }  
    /**
    * Method checks if s is a bracket or absolutevalue symbol
    * 
    * @param s part from partcheckers parts[]
    * 
    */
    public void partsContainsBracketsOrAbsoluteVal(String s) {
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
    public String partsDataBaseChecker(String s) {
        s = database.getValue(s);
        if (!database.isPasswordGiven() || s.equals("Nimellä ei ole arvoa") || s.equals("Tietokantaa ei ole vielä luotu")) {
            s = "0";
            nameDoesntExistInDatabase = true;
            if (!database.isPasswordGiven()) {
                errorMessegeNameDoesntExistInDatabase = "Salasanaa ei ole annettu";
            } else {
                errorMessegeNameDoesntExistInDatabase = "Arvoa ei löytynyt muistista";
            }
            return "0";
        }
        calculationArrayList.add(s);
        return s;
    }
   
    /**
    * Method makes changes negative value to positive
    * 
    * @param calcArrayList ArrayList with calculation parts
    * 
    * @return ArrayList with solved value
    */
    public ArrayList<String> absoluteValueSolver(ArrayList<String> calcArrayList) {
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
                calcArrayList.add(absoluteValueStartIndex + 1, "" + cm.product(-1, Double.valueOf(calcArrayList.get(absoluteValueStartIndex + 1))));
                calcArrayList.remove(absoluteValueStartIndex + 2);
            }
            calcArrayList.remove(absoluteValueStartIndex + 2);
            calcArrayList.remove(absoluteValueStartIndex);
        }
        return calcArrayList; 
    }
    /**
    * Method tells absoluteValueSolver where absolutevalue begins and ends
    * 
    * @param calcArrayList ArrayList with calculation parts
    * 
    * @return int with indexes where abs starts and where abs ends
    */
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
     /**
    * Method calculates bracketet calculation
    * 
    * @param calcArrayList ArrayList with calculation parts
    * 
    * @return ArrayList with solved value
    */
    public ArrayList<String> bracketSolver(ArrayList<String> calcArrayList) {
        while (true) {
            int[] index = bracketIndexSolver(calcArrayList);
            int bracketStartIndex = index[0];
            int bracketEndIndex = index[1]; 
            if (bracketStartIndex == -1 || bracketEndIndex == -1) {
                break;
            }

            List<String> calculationArrayListPart = calcArrayList.subList(bracketStartIndex + 1, bracketEndIndex);
            calculate(calculationArrayListPart);
            calcArrayList.remove(bracketStartIndex + 2);
            calcArrayList.remove(bracketStartIndex);
        }
        return calcArrayList;       
    }
    
    /**
    * Method tells bracketSolver where brackets begins and ends
    * 
    * @param calcArrayList ArrayList with calculation parts
    * 
    * @return int with indexes where abs starts and where abs ends
    */
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
    
    /**
    * Method changes the first value to negative if it starts with - for example "-1+12"
    * Method solves calculations in proper calculation order using calculation methods
    * 
    * @param calculatableArrayList ArrayList with calculation parts
    * 
    * @return String with calculation results for example "11"
    */
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
        if (calculatableArrayList.get(0).equals("Infinity") || inf == true) {
            calculatableArrayList.set(0, "ääretön");
        }
        return calculatableArrayList.get(0);
    }
    
    /**
    * Method solves percentage calculations
    * 
    * @param calculatableArrayList ArrayList with calculation parts
    */
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
    
    /**
    * Method solves factorial calculations
    * 
    * @param calculatableArrayList ArrayList with calculation parts
    */
    public void factorialSolver(List<String> calculatableArrayList) {
        for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("!")) {
                if (isNumber(calculatableArrayList.get(i - 1))) {
                    double firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                    calculatableArrayList.add(i + 1, cm.factorial(firstValue) + "");
                    calculatableArrayList.remove(i);
                    calculatableArrayList.remove(i - 1);
                    i--;
                    i--;
                }
            }
        }
    }
    /**
    * Method solves power calculations
    * 
    * @param calculatableArrayList ArrayList with calculation parts
    */
    public void powerSolver(List<String> calculatableArrayList) {
        for (int i = 0; i < calculatableArrayList.size(); i++) {
            if (calculatableArrayList.get(i).equals("^")) {
                if (isNumber(calculatableArrayList.get(i - 1)) && isNumber(calculatableArrayList.get(i + 1))) {
                    double  firstValue = Double.valueOf(calculatableArrayList.get(i - 1));
                    double  secondValue = Double.valueOf(calculatableArrayList.get(i + 1));
                    calculatableArrayList.remove(i + 1);
                    calculatableArrayList.add(i + 1, cm.power(firstValue, secondValue) + "");
                    calculatableArrayList.remove(i);
                    calculatableArrayList.remove(i - 1);
                    i--;
                    i--;
                }
            }
        }
    }
    /**
    * Method uses product and equation solvers to calculate product and equation
    * 
    * @param calculatableArrayList ArrayList with calculation parts
    */
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
            calculatableArrayList.add(i + 1, cm.product(firstValue, secondValue) + "");
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
            calculatableArrayList.add(i + 1, cm.quotient(firstValue, secondValue) + "");
            calculatableArrayList.remove(i);
            calculatableArrayList.remove(i - 1);
            i -= 2;
        }
        if (cm.quotientInf()) {
            inf = true;
        }
        return i;
    }
    /**
    * Method uses sum and differential solvers to calculate product and equation
    * 
    * @param calculatableArrayList ArrayList with calculation parts
    */
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
            calculatableArrayList.add(i + 1, cm.sum(firstValue, secondValue) + "");
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
            calculatableArrayList.add(i + 1, cm.sum(firstValue, -secondValue) + "");
            calculatableArrayList.remove(i);
            calculatableArrayList.remove(i - 1);
            i -= 2;
        }
        return i;
    }
    /**
    * Method checks if given value is a number
    * 
    * @param number string with given value for example "5"
    * 
    * @return boolean true if is number else false
    */
    public boolean isNumber(String number) {
        try {
            double num = Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public class Exceptions extends Exception {

        public Exceptions(String errorMessage) {
            super(errorMessage);
        }

    }
}
