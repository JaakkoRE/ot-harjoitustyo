package calculatorProgram.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jaakko
 */

import calculatorprogram.calculators.Calculator;
import calculatorprogram.calculators.GraphicCalculator;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInterface extends Application {
    public int bintextField;
    public int graphInd;
    public void interFaceStart() {
        launch(UserInterface.class);
    }

    @Override
    public void start(Stage window) throws Calculator.Exceptions {

        BorderPane Layout = new BorderPane();
        //Starting screen gui

        BorderPane startingLayout = new BorderPane();
        VBox menuButtons = new VBox();
        menuButtons.setSpacing(20);

        Button databaseButton = new Button("Tietokannan hallinta");
        Button calcButton = new Button("Laskin");
        Button graphCalcButton = new Button("Graafinen laskin");
        //Back to menu
        Button backToStartScreen = new Button("Takaisin");

        menuButtons.getChildren().add(databaseButton);
        menuButtons.getChildren().add(calcButton);
        menuButtons.getChildren().add(graphCalcButton);

        startingLayout.setTop(new Label("Tervetuloa"));
        startingLayout.setCenter(menuButtons);
        Layout.setCenter(startingLayout);
        //Database layout

        BorderPane databaseLayout = new BorderPane();

        //calc layout
        //previous results
        HBox previousResults = new HBox();
        previousResults.setSpacing(135);
        VBox previousResultsVBox = new VBox();
        Label empty = new Label("");
        Label previousResult1 = new Label();
        Label previousResult2 = new Label();
        Label previousResult3 = new Label();
        Label previousResult4 = new Label();
        Label previousResult5 = new Label();
        previousResultsVBox.getChildren().addAll(previousResult5, previousResult4, previousResult3, previousResult2, previousResult1);
        previousResults.getChildren().add(empty);
        previousResults.getChildren().add(previousResultsVBox);
        //calc default layout
        BorderPane calcLayout = new BorderPane();
        VBox calcFieldAndButton = new VBox();
        HBox calculationField = new HBox();
        TextField calculatable = new TextField();
        Label resultText = new Label();
        calculationField.getChildren().add(calculatable);
        calculationField.getChildren().add(resultText);
        Button calculate = new Button("Laske");
        calculationField.getChildren().add(calculate);
        
        //Calculator quick buttons
        calcFieldAndButton.getChildren().add(calculationField);
        VBox buttonRows = new VBox();
        HBox buttonRow1 = new HBox();
        Button quickButton1 = new Button("1");
        Button quickButton2 = new Button("2");
        Button quickButton3 = new Button("3");
        Button quickButtonSum = new Button("+");
        Button quickButtonNeg = new Button("-");
        Button quickButtonProd = new Button("*");
        Button quickButtonQue = new Button("/");
        Button quickButtonPow = new Button("^");
        Button quickButtonDot = new Button(".");
        Button quickButtonπ = new Button("π");
        Button quickButtone = new Button("e");
        buttonRow1.getChildren().addAll(quickButton1, quickButton2, quickButton3, quickButtonDot, quickButtonSum, quickButtonNeg, quickButtonProd, quickButtonQue, quickButtonPow, quickButtonπ, quickButtone);

        HBox buttonRow2 = new HBox();
        Button quickButton4 = new Button("4");
        Button quickButton5 = new Button("5");
        Button quickButton6 = new Button("6");
        Button quickButtonFac = new Button("!");
        Button quickButtonAbs = new Button("||");
        Button quickButtonPerc = new Button("%");
        Button quickButtonAns = new Button(" Edellinen vastaus");
        buttonRow2.getChildren().addAll(quickButton4, quickButton5, quickButton6, quickButtonFac,quickButtonPerc, quickButtonAbs, quickButtonAns);

        HBox buttonRow3 = new HBox();
        Button quickButton7 = new Button("7");
        Button quickButton8 = new Button("8");
        Button quickButton9 = new Button("9");
        Button quickButton0 = new Button("0");
        Button quickButtonPar1 = new Button("(");
        Button quickButtonPar2 = new Button(")");
        Button quickButtonRemoveLastChar = new Button("Poista");
        Button emptyFields = new Button(" Tyhjennä");
        buttonRow3.getChildren().addAll(quickButton7, quickButton8, quickButton9, quickButton0, quickButtonPar1, quickButtonPar2, quickButtonRemoveLastChar, emptyFields);

        buttonRows.getChildren().addAll(buttonRow1, buttonRow2, buttonRow3);
        calcFieldAndButton.getChildren().add(buttonRows);

        //button effects
        calculate.setOnAction((event) -> {
            Calculator calc = new Calculator();
            String calculationResult = "";
            boolean noError = true;
            try {
                calculationResult = calc.calcArrayList(calculatable.getText());
            } catch (Exception e) {
                String result1=previousResult1.getText();
                String result2=previousResult2.getText();
                String result3=previousResult3.getText();
                String result4=previousResult4.getText();

                previousResult5.setText(result4);
                previousResult4.setText(result3);
                previousResult3.setText(result2);
                previousResult2.setText(result1);
                previousResult1.setText("Virheellinen syöte");
                noError = false;
            }
            if (noError) {
          //      resultText.setText(calculationResult + "");
                String result1 = previousResult1.getText();
                String result2 = previousResult2.getText();
                String result3 = previousResult3.getText();
                String result4 = previousResult4.getText();

                previousResult5.setText(result4);
                previousResult4.setText(result3);
                previousResult3.setText(result2);
                previousResult2.setText(result1);
                String[] parts = calculationResult.split("\\.");
                if (parts.length > 1) {
                    if (parts[1].equals("0"))
                        previousResult1.setText(parts[0] + ""); 
                    else
                        previousResult1.setText(calculationResult + "");
                } else
                    previousResult1.setText(calculationResult + "");
            }
        });
        quickButton1.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "1");
        });
        quickButton2.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "2");
        });
        quickButton3.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "3");
        });
        quickButton4.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "4");
        });
        quickButton5.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "5");
        });
        quickButton6.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "6");
        });
        quickButton7.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "7");

        });
        quickButton8.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "8");
        });
        quickButton9.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "9");

        });
        quickButtonDot.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + ".");

        });
        quickButtonSum.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "+");

        });
        quickButtonNeg.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "-");

        });
        quickButtonProd.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "*");
        });
        quickButtonQue.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "/");
        });
        quickButtonPow.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "^");
        });
        quickButtonFac.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "!");
        });
        quickButtonAbs.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "|");
        });
        quickButtonPar1.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "(");
        });
        quickButtonPar2.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + ")");
        });
        quickButton0.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "0");
        });
        quickButtonPerc.setOnAction((event) -> {
            calculatable.setText(calculatable.getText() + "%");
        });
        quickButtonπ.setOnAction((event) -> {
           calculatable.setText(calculatable.getText() + "π");
        });
        quickButtone.setOnAction((event) -> {
           calculatable.setText(calculatable.getText() + "e");
        });
        quickButtonAns.setOnAction((event) -> {
            if (!previousResult1.getText().equals("Virheellinen syöte"))
                calculatable.setText(calculatable.getText() + previousResult1.getText());
            else if (!previousResult2.getText().equals("Virheellinen syöte"))
                calculatable.setText(calculatable.getText() + previousResult2.getText());
            else if (!previousResult3.getText().equals("Virheellinen syöte"))
                calculatable.setText(calculatable.getText() + previousResult3.getText());
            else if (!previousResult4.getText().equals("Virheellinen syöte"))
                calculatable.setText(calculatable.getText() + previousResult4.getText());
            else if (!previousResult5.getText().equals("Virheellinen syöte"))
                calculatable.setText(calculatable.getText() + previousResult5.getText());
        });
        quickButtonRemoveLastChar.setOnAction((event) -> {
            if (calculatable.getText().length() > 0) {
                String calcText = calculatable.getText().substring(0, calculatable.getText().length() - 1);
                calculatable.setText(calcText);
            }
        });
        emptyFields.setOnAction((event) -> {
            calculatable.setText("");
            resultText.setText("");
        });
        //bin calc layout
        
        VBox binomialLayout = new VBox();
        HBox binomialCalculationField = new HBox();
        Label ncr = new Label("nCr(");
        TextField binomialn = new TextField();
        Label divider = new Label(",");
        TextField binomialk = new TextField();
        Label binParEnd = new Label(")");
        Button binCalculate = new Button("Laske");
        Label binResultField = new Label("");
        binomialCalculationField.getChildren().addAll(ncr, binomialn, divider, binomialk, binParEnd, binCalculate, binResultField);
        
        HBox binButtonFieldRow1 = new HBox();
        Button binQuickButton1 = new Button("1");
        Button binQuickButton2 = new Button("2");
        Button binQuickButton3 = new Button("3");
        Button binQuickButton4 = new Button("4");
        Button binQuickButton5 = new Button("5");
        binButtonFieldRow1.getChildren().addAll(binQuickButton1, binQuickButton2, binQuickButton3, binQuickButton4, binQuickButton5);

        HBox binButtonFieldRow2 = new HBox();
        Button binQuickButton6 = new Button("6");
        Button binQuickButton7 = new Button("7");
        Button binQuickButton8 = new Button("8");
        Button binQuickButton9 = new Button("9");
        Button binQuickButton0 = new Button("0");
        binButtonFieldRow2.getChildren().addAll(binQuickButton6, binQuickButton7, binQuickButton8, binQuickButton9, binQuickButton0);
         
        HBox binButtonFieldRow3 = new HBox();
        Button binScreenSwitch1 = new Button("->");
        Button binScreenSwitch2 = new Button("<-");
        Button binEmpty = new Button("Tyhjennä");
        Button binQuickButtonAns = new Button("Edellinen vastaus");
        Button binDeleteLastChar = new Button("Poista");
        binButtonFieldRow3.getChildren().addAll(binScreenSwitch1,binScreenSwitch2,binEmpty,binDeleteLastChar,binQuickButtonAns);
   
        binomialLayout.getChildren().addAll(binomialCalculationField,binButtonFieldRow1);
        binomialLayout.getChildren().addAll(binButtonFieldRow2,binButtonFieldRow3);
        binCalculate.setOnAction((event) -> {
            Calculator calc = new Calculator();
            if(calc.isNumber(binomialn.getText())&&calc.isNumber(binomialk.getText())){
                int calculationResult = 0;
                boolean error = false;
                try{
                    calculationResult = calc.binomial(Integer.valueOf(binomialn.getText()), Integer.valueOf(binomialk.getText()));
                } catch (Exception e) {
                    error = true;
                }
         //   binResultField.setText(calculationResult+"");
            String Result1=previousResult1.getText();
            String Result2=previousResult2.getText();
            String Result3=previousResult3.getText();
            String Result4=previousResult4.getText();

            previousResult5.setText(Result4);
            previousResult4.setText(Result3);
            previousResult3.setText(Result2);
            previousResult2.setText(Result1);
            String calculationResultS="";
            if(!error)
                calculationResultS=calculationResult+"";
            else{
                calculationResultS="Virheellinen syöte";
            }
            String[] parts = calculationResultS.split("\\.");
            if(parts.length>1){
                if(parts[1].equals("0"))
                    previousResult1.setText(parts[0]+""); 
                else
                    previousResult1.setText(calculationResult+"");
            }else
                previousResult1.setText(calculationResult+"");
     
            }else{
                String result1=previousResult1.getText();
                String result2=previousResult2.getText();
                String result3=previousResult3.getText();
                String result4=previousResult4.getText();

                previousResult5.setText(result4);
                previousResult4.setText(result3);
                previousResult3.setText(result2);
                previousResult2.setText(result1);
                previousResult1.setText("Virheellinen syöte");
            }
         });
        binScreenSwitch1.setOnAction((event) -> {
            BinTextField(2);
        });
        binScreenSwitch2.setOnAction((event) -> {
            BinTextField(1);
        });
        binEmpty.setOnAction((event) -> {
            binomialn.setText("");
            binomialk.setText("");
            binResultField.setText("");
        });
        bintextField=1;
        binQuickButton1.setOnAction((event) -> {
            if(bintextField==1)
                binomialn.setText(binomialn.getText() + "1");
            else
                 binomialk.setText(binomialk.getText() + "1");
                
                
        });
        binQuickButton2.setOnAction((event) -> {
             if(bintextField==1)
                binomialn.setText(binomialn.getText() + "2");
            else
                 binomialk.setText(binomialk.getText() + "2");
        });
        binQuickButton3.setOnAction((event) -> {
             if(bintextField == 1)
                binomialn.setText(binomialn.getText() + "3");
            else
                 binomialk.setText(binomialk.getText() + "3");
        });
        binQuickButton4.setOnAction((event) -> {
             if(bintextField == 1)
                binomialn.setText(binomialn.getText() + "4");
            else
                 binomialk.setText(binomialk.getText() + "4");
        });
        binQuickButton5.setOnAction((event) -> {
             if(bintextField == 1)
                binomialn.setText(binomialn.getText() + "5");
            else
                 binomialk.setText(binomialk.getText() + "5");
        });
        binQuickButton6.setOnAction((event) -> {
             if(bintextField == 1)
                binomialn.setText(binomialn.getText() + "6");
            else
                 binomialk.setText(binomialk.getText() + "6");
        });
        binQuickButton7.setOnAction((event) -> {
             if(bintextField == 1)
                binomialn.setText(binomialn.getText() + "7");
            else
                binomialk.setText(binomialk.getText() + "7");

        });
        binQuickButton8.setOnAction((event) -> {
             if(bintextField == 1)
                binomialn.setText(binomialn.getText() + "8");
            else
                binomialk.setText(binomialk.getText() + "8");
        });
        binQuickButton9.setOnAction((event) -> {
             if(bintextField == 1)
                binomialn.setText(binomialn.getText() + "9");
            else
                binomialk.setText(binomialk.getText() + "9");
         });
         binQuickButton0.setOnAction((event) -> {
             if(bintextField == 1)
                binomialn.setText(binomialn.getText() + "0");
            else
                binomialk.setText(binomialk.getText() + "0");
         });
         binQuickButtonAns.setOnAction((event) -> {
            if(bintextField == 1){
                if(!previousResult1.getText().equals("Virheellinen syöte"))
                    binomialn.setText(binomialn.getText() + previousResult1.getText());
                else if(!previousResult2.getText().equals("Virheellinen syöte"))
                    binomialn.setText(binomialn.getText() + previousResult2.getText());
                else if(!previousResult3.getText().equals("Virheellinen syöte"))
                    binomialn.setText(binomialn.getText() + previousResult3.getText());
                else if(!previousResult4.getText().equals("Virheellinen syöte"))
                    binomialn.setText(binomialn.getText() + previousResult4.getText());
                else if(!previousResult5.getText().equals("Virheellinen syöte"))
                    binomialn.setText(binomialn.getText() + previousResult5.getText());    
             }
            if(bintextField == 2){
                if(!previousResult1.getText().equals("Virheellinen syöte"))     
                    binomialk.setText(binomialk.getText() + previousResult1.getText());
                else if(!previousResult2.getText().equals("Virheellinen syöte"))
                    binomialk.setText(binomialk.getText() + previousResult2.getText());
                else if(!previousResult3.getText().equals("Virheellinen syöte"))
                    binomialk.setText(binomialk.getText() + previousResult3.getText());
                else if(!previousResult4.getText().equals("Virheellinen syöte"))
                    binomialk.setText(binomialk.getText() + previousResult4.getText());
                else if(!previousResult5.getText().equals("Virheellinen syöte"))
                    binomialk.setText(binomialk.getText() + previousResult5.getText());   
             }
         });
         binDeleteLastChar.setOnAction((event) -> {
            if(bintextField == 1){
                if (binomialn.getText().length() > 0) {
                    String calcText = binomialn.getText().substring(0, binomialn.getText().length() - 1);
                    binomialn.setText(calcText);
                }
            }    
            if(bintextField == 2){
                if (binomialk.getText().length() > 0) {
                    String calcText = binomialk.getText().substring(0, binomialk.getText().length() - 1);
                    binomialk.setText(calcText);
                }
            }
        });
         
         

        //more options
        VBox optionButtons = new VBox();
        Button openOptions = new Button("Lisää");
        optionButtons.getChildren().add(openOptions);
        //once openOptions opened.
        VBox optionsOpened = new VBox();
        Button backFromSelection = new Button("Pois valikosta");
        Button basicCalcOperations = new Button("Perus laskin toimitukset");
        Button binomialCalcOpen = new Button("Binomi laskin");
        optionsOpened.getChildren().addAll(backFromSelection,basicCalcOperations,binomialCalcOpen);
        
        //add
        calcLayout.setCenter(calcFieldAndButton);
        calcLayout.setLeft(optionButtons);
        calcLayout.setTop(previousResults);
        
        openOptions.setOnAction((event) -> {
            calcLayout.setLeft(optionsOpened);
        });
        backFromSelection.setOnAction((event) -> {
            calcLayout.setLeft(optionButtons);
        });
        basicCalcOperations.setOnAction((event) -> {
            calcLayout.setCenter(calcFieldAndButton);
        });
        binomialCalcOpen.setOnAction((event) -> {
            calcLayout.setCenter(binomialLayout);
        });
        
        //graphCalc layout
        BorderPane graphCalcLayout = new BorderPane();
        GraphicCalculator GraphCalc = new GraphicCalculator();
        //graphCalc calculationField
        graphInd = 1;
        VBox graphCalcFields = new VBox();
        Label graphErrorField = new Label("");
        HBox graphCalculationField = new HBox();
        Label fx = new Label("F(x)=");
        TextField graphTextField = new TextField();
        Button graphCalcCalculate = new Button("Laske");
        Button graphCalcEmpty = new Button("Tyhjennä");
        Slider graphPrecision = new Slider();
        Label graphPrecisionExplanationLabel = new Label("     X tarkkuus");
        graphPrecision.setMin(1);
        graphPrecision.setMax(4);
        graphPrecision.setMinorTickCount(0);
        graphPrecision.setSnapToTicks(true);
        graphPrecision.setShowTickMarks(true);
        graphPrecision.setMajorTickUnit(1);
        Label graphPrecisionLabel = new Label("0.01");
        Label graphPrecisionLabelWarning = new Label("");
        graphPrecision.setOnMouseReleased(e -> {
            double value = graphPrecision.getValue();
            if (value == 1)
                graphPrecisionLabelWarning.setText("        varoitus hidas");
            
            else
                graphPrecisionLabelWarning.setText("");
            if (value == 1)
                graphPrecisionLabel.setText("0.001");
            if (value == 2)
                graphPrecisionLabel.setText("0.01");
            if (value == 3)
                graphPrecisionLabel.setText("0.1");
            if (value == 4)
                graphPrecisionLabel.setText("1");
         });
        graphPrecision.setValue(2);
        graphCalculationField.getChildren().addAll(fx, graphTextField, graphCalcCalculate, graphCalcEmpty,
        graphPrecisionExplanationLabel, graphPrecision, graphPrecisionLabel, graphPrecisionLabelWarning);
        
        HBox graphCalculationBoundsX = new HBox();
        Label xboundLabel = new Label("X koordinaattien Rajat          ");
        Label xLowerBoundLabel = new Label(" Alaraja ");
        TextField xLowerBoundTextField = new TextField();
        Label xUpperBoundLabel = new Label(" Yläraja" );
        TextField xUpperBoundTextField = new TextField();
        graphCalculationBoundsX.getChildren().addAll(xboundLabel, xLowerBoundTextField, xLowerBoundLabel, xUpperBoundTextField, xUpperBoundLabel);
        
        HBox graphCalculationBoundsY = new HBox();
        Label yboundLabel = new Label("Y koordinaattien Rajat          ");
        Label yLowerBoundLabel = new Label(" Alaraja ");
        TextField yLowerBoundTextField = new TextField();
        Label yUpperBoundLabel = new Label(" Yläraja ");
        TextField yUpperBoundTextField = new TextField();
        Button graphCalcZoom = new Button("Tarkenna väliin");
        graphCalculationBoundsY.getChildren().addAll(yboundLabel, yLowerBoundTextField, yLowerBoundLabel, yUpperBoundTextField, yUpperBoundLabel, graphCalcZoom);

        graphCalcFields.getChildren().addAll(graphErrorField, graphCalculationField, graphCalculationBoundsX, graphCalculationBoundsY);
        graphCalcLayout.setTop(graphCalcFields);
        //cord setup
        NumberAxis xCords = new NumberAxis(-20,20,1);
        NumberAxis yCords = new NumberAxis(-20,20,1);

        xCords.setLabel("X");
        yCords.setLabel("Y");

        LineChart<Number, Number> cordLineChart = new LineChart<>(xCords, yCords);
        cordLineChart.setCreateSymbols(false);
        cordLineChart.setLegendVisible(false);
        cordLineChart.setAnimated(false);
        
       
        graphCalcEmpty.setOnAction((event) -> {
            xCords.setLowerBound(-20);
            xCords.setUpperBound(20);
            yCords.setLowerBound(-20);
            yCords.setUpperBound(20);
            cordLineChart.getData().clear();
            graphInd = 1;
        });
           
        graphCalcZoom.setOnAction((event) -> {
            Boolean error = false;
            int yLowerBound = 0;
            try{
                if (yLowerBoundTextField.getText().isBlank()) {
                    yLowerBound = -20;
                }else
            yLowerBound = Integer.valueOf(yLowerBoundTextField.getText());
                
            }catch(NumberFormatException e){
            error = true;
            graphErrorField.setText("Virheellinen Y alaraja");
                        
            }
            
            int yUpperBound = 0;
            try{
                if (yUpperBoundTextField.getText().isBlank()) {
                    yUpperBound = 20;
                }else
            yUpperBound = Integer.valueOf(yUpperBoundTextField.getText());
                
            }catch(NumberFormatException e){
                error = true;
                graphErrorField.setText("Virheellinen Y yläraja");
                        
            }
            
            int xLowerBound = 0;
            try{
                if (xLowerBoundTextField.getText().isBlank()) {
                    xLowerBound = -20;
                }else
            xLowerBound = Integer.valueOf(xLowerBoundTextField.getText());
                
            }catch(NumberFormatException e){
               error = true;
               graphErrorField.setText("Virheellinen X alaraja");
                        
            }
            
            int xUpperBound=0;
            try{
                if (xUpperBoundTextField.getText().isBlank()) {
                    xUpperBound=20;
                }else
                    xUpperBound = Integer.valueOf(xUpperBoundTextField.getText());
                
            }catch(NumberFormatException e){
                error = true;
                graphErrorField.setText("Virheellinen X yläraja");
                        
            }
            if(xUpperBound <= xLowerBound || yUpperBound <= yLowerBound) {
                graphErrorField.setText("Yläraja ei voi olla pienempi kuin alaraja");
                error = true;
            }
            if(!error){
                xCords.setLowerBound(xLowerBound);
                xCords.setUpperBound(xUpperBound);
                yCords.setLowerBound(yLowerBound);
                yCords.setUpperBound(yUpperBound);
            }
        });
        
         graphCalcCalculate.setOnAction((event) -> {
             
            XYChart.Series resultsSeries1 = new XYChart.Series();
            XYChart.Series resultsSeries2 = new XYChart.Series();
            XYChart.Series resultsSeries3 = new XYChart.Series();
            XYChart.Series resultsSeries4 = new XYChart.Series();
            XYChart.Series resultsSeries5 = new XYChart.Series();
            XYChart.Series resultsSeries6 = new XYChart.Series();
            XYChart.Series resultsSeries7 = new XYChart.Series();
            XYChart.Series resultsSeries8 = new XYChart.Series();
            XYChart.Series resultsSeries9 = new XYChart.Series();
            XYChart.Series resultsSeries10 = new XYChart.Series();
            
            graphErrorField.setText("");
            Boolean error = false;
          
            
            //bounds
            int yLowerBound = 0;
            try{
                if(yLowerBoundTextField.getText().isBlank()){
                    yLowerBound =- 20;
                }else
            yLowerBound = Integer.valueOf(yLowerBoundTextField.getText());
                
            }catch(NumberFormatException e){
                error = true;
                graphErrorField.setText("Virheellinen Y alaraja");
                        
            }
            
            int yUpperBound = 0;
            try{
                if (yUpperBoundTextField.getText().isBlank()) {
                    yUpperBound = 20;
                }else
                    yUpperBound = Integer.valueOf(yUpperBoundTextField.getText());
                
            }catch(NumberFormatException e){
               error = true;
               graphErrorField.setText("Virheellinen Y yläraja");
            }
            
            int xLowerBound = 0;
            try{
                if(xLowerBoundTextField.getText().isBlank()){
                    xLowerBound = -20;
                }else
                    xLowerBound = Integer.valueOf(xLowerBoundTextField.getText());
                
            }catch(NumberFormatException e){
                error = true;
                graphErrorField.setText("Virheellinen X alaraja");
                        
            }
            
            int xUpperBound = 0;
            try{
                if(xUpperBoundTextField.getText().isBlank()){
                    xUpperBound = 20;
                }else
                    xUpperBound = Integer.valueOf(xUpperBoundTextField.getText());
                
            }catch(NumberFormatException e){
                error=true;
                graphErrorField.setText("Virheellinen X yläraja");
                        
            }
            if (xUpperBound <= xLowerBound || yUpperBound <= yLowerBound) {
                graphErrorField.setText("Yläraja ei voi olla pienempi kuin alaraja");
                error=true;
            }
            
            //calculation 
            ArrayList<Double> results = null;
            double precision = 0.1;
            if(!error){
            try {
                precision = Double.valueOf(graphPrecisionLabel.getText());
                results = GraphCalc.results(graphTextField.getText(),xUpperBound+2,xLowerBound-2,precision);
            } catch (Calculator.Exceptions ex) {
               
            
            }catch(Exception e){
                graphErrorField.setText("Virheellinen syöte");
                error = true;
                
            }
            }
            if(!error){
                xCords.setLowerBound(xLowerBound);
                xCords.setUpperBound(xUpperBound);
                yCords.setLowerBound(yLowerBound);
                yCords.setUpperBound(yUpperBound);  
                
        int index = 0;
        for(double i = xLowerBound - 2; i <= xUpperBound + 2; i += precision){
            Double result = results.get(index);
            if (result==200000){
                    graphInd++;
                    System.out.println("h" + graphInd);
                }
            if (result<=yUpperBound&&result>=yLowerBound){
               // System.out.println(result);
                System.out.println(result);
                if (graphInd == 1)
            resultsSeries1.getData().add(new XYChart.Data(i,results.get(index)));
                if (graphInd == 2)
            resultsSeries2.getData().add(new XYChart.Data(i,results.get(index)));
                if (graphInd == 3)
            resultsSeries3.getData().add(new XYChart.Data(i,results.get(index)));
                if (graphInd == 4)
            resultsSeries4.getData().add(new XYChart.Data(i,results.get(index)));
                if (graphInd == 5)
            resultsSeries5.getData().add(new XYChart.Data(i,results.get(index)));
                if (graphInd == 6)
            resultsSeries6.getData().add(new XYChart.Data(i,results.get(index)));
                if (graphInd == 7)
            resultsSeries7.getData().add(new XYChart.Data(i,results.get(index)));
                if (graphInd == 8 )
            resultsSeries8.getData().add(new XYChart.Data(i,results.get(index)));
                if (graphInd == 9)
            resultsSeries9.getData().add(new XYChart.Data(i,results.get(index)));
                if (graphInd == 10)
            resultsSeries10.getData().add(new XYChart.Data(i,results.get(index)));
                if (graphInd > 10) 
                    graphErrorField.setText("Täynnä");
            }
           
            index++;
        }
        graphInd++;
        
           
            //cordLineChart.getData().add(resultsSeries1);
            cordLineChart.getData().addAll(resultsSeries1, resultsSeries2, resultsSeries3, resultsSeries4, resultsSeries5, resultsSeries6,
            resultsSeries7, resultsSeries8, resultsSeries9, resultsSeries10);
      
            }
         });
         
        
       
        graphCalcLayout.setCenter(cordLineChart);
       
        
        //Button layout change
        backToStartScreen.setOnAction((event) -> {
            Layout.setCenter(startingLayout);
            Layout.setTop(null);
            window.setWidth(450);
            window.setHeight(300);
        });
        Layout.setCenter(startingLayout);
        databaseButton.setOnAction((event) -> {
            Layout.setCenter(databaseLayout);
            Layout.setTop(backToStartScreen);
            
        });

        calcButton.setOnAction((event) -> {
            Layout.setCenter(calcLayout);
            Layout.setTop(backToStartScreen);
            window.setWidth(600);
            window.setHeight(400);
        });

        graphCalcButton.setOnAction((event) -> {
            Layout.setCenter(graphCalcLayout);
            Layout.setTop(backToStartScreen);
            window.setWidth(750);
            window.setHeight(750);

        });

        Scene view = new Scene(Layout, 450, 300);
        window.setScene(view);
        window.show();
    }
    public void BinTextField(int i){
        bintextField = i;
    }
    
    
}
