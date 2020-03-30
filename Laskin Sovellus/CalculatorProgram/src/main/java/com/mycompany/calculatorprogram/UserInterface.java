package com.mycompany.calculatorprogram;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jaakko
 */
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UserInterface extends Application {
    public int bintextField;
    public void InterFaceStart() {
        launch(UserInterface.class);
    }

    @Override
    public void start(Stage window) {

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
        previousResultsVBox.getChildren().addAll(previousResult5,previousResult4,previousResult3,previousResult2,previousResult1);
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
        buttonRow1.getChildren().addAll(quickButton1, quickButton2, quickButton3,quickButtonDot, quickButtonSum, quickButtonNeg, quickButtonProd, quickButtonQue, quickButtonPow,quickButtonπ,quickButtone);

        HBox buttonRow2 = new HBox();
        Button quickButton4 = new Button("4");
        Button quickButton5 = new Button("5");
        Button quickButton6 = new Button("6");
        Button quickButtonFac = new Button("!");
        Button quickButtonAbs = new Button("||");
        Button quickButtonPerc= new Button("%");
        Button quickButtonAns= new Button(" Edellinen vastaus");
        buttonRow2.getChildren().addAll(quickButton4, quickButton5, quickButton6, quickButtonFac,quickButtonPerc, quickButtonAbs,quickButtonAns);

        HBox buttonRow3 = new HBox();
        Button quickButton7 = new Button("7");
        Button quickButton8 = new Button("8");
        Button quickButton9 = new Button("9");
        Button quickButton0 = new Button("0");
        Button quickButtonPar1 = new Button("(");
        Button quickButtonPar2 = new Button(")");
        Button quickButtonRemoveLastChar = new Button("Poista");
        Button emptyFields = new Button(" Tyhjennä");
        buttonRow3.getChildren().addAll(quickButton7, quickButton8, quickButton9, quickButton0, quickButtonPar1, quickButtonPar2,quickButtonRemoveLastChar,emptyFields);

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
            String Result1=previousResult1.getText();
            String Result2=previousResult2.getText();
            String Result3=previousResult3.getText();
            String Result4=previousResult4.getText();

            previousResult5.setText(Result4);
            previousResult4.setText(Result3);
            previousResult3.setText(Result2);
            previousResult2.setText(Result1);
            previousResult1.setText("Virheellinen syöte");
                noError = false;
            }
            if (noError) {
          //      resultText.setText(calculationResult + "");
            String Result1=previousResult1.getText();
            String Result2=previousResult2.getText();
            String Result3=previousResult3.getText();
            String Result4=previousResult4.getText();

            previousResult5.setText(Result4);
            previousResult4.setText(Result3);
            previousResult3.setText(Result2);
            previousResult2.setText(Result1);
            String[] parts = calculationResult.split("\\.");
            if(parts.length>1){
              if(parts[1].equals("0"))
                previousResult1.setText(parts[0]+""); 
              else
                previousResult1.setText(calculationResult+"");
            }else
                previousResult1.setText(calculationResult+"");
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
            if(!previousResult1.getText().equals("Virheellinen syöte"))
            calculatable.setText(calculatable.getText() + previousResult1.getText());
            else if(!previousResult2.getText().equals("Virheellinen syöte"))
            calculatable.setText(calculatable.getText() + previousResult2.getText());
            else if(!previousResult3.getText().equals("Virheellinen syöte"))
            calculatable.setText(calculatable.getText() + previousResult3.getText());
            else if(!previousResult4.getText().equals("Virheellinen syöte"))
            calculatable.setText(calculatable.getText() + previousResult4.getText());
            else if(!previousResult5.getText().equals("Virheellinen syöte"))
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
        binomialCalculationField.getChildren().addAll(ncr,binomialn,divider,binomialk,binParEnd,binCalculate,binResultField);
        
        HBox binButtonFieldRow1 = new HBox();
        Button binQuickButton1 = new Button("1");
        Button binQuickButton2 = new Button("2");
        Button binQuickButton3 = new Button("3");
        Button binQuickButton4 = new Button("4");
        Button binQuickButton5 = new Button("5");
        binButtonFieldRow1.getChildren().addAll(binQuickButton1,binQuickButton2,binQuickButton3,binQuickButton4,binQuickButton5);

        HBox binButtonFieldRow2 = new HBox();
        Button binQuickButton6 = new Button("6");
        Button binQuickButton7 = new Button("7");
        Button binQuickButton8 = new Button("8");
        Button binQuickButton9 = new Button("9");
        Button binQuickButton0 = new Button("0");
        Button binQuickButtonDot = new Button(".");
        binButtonFieldRow2.getChildren().addAll(binQuickButton6, binQuickButton7, binQuickButton8, binQuickButton9, binQuickButton0,binQuickButtonDot);
         
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
            double calculationResult = calc.binomial(Double.valueOf(binomialn.getText()), Double.valueOf(binomialk.getText()));
         //   binResultField.setText(calculationResult+"");
            String Result1=previousResult1.getText();
            String Result2=previousResult2.getText();
            String Result3=previousResult3.getText();
            String Result4=previousResult4.getText();

            previousResult5.setText(Result4);
            previousResult4.setText(Result3);
            previousResult3.setText(Result2);
            previousResult2.setText(Result1);
            String calculationResultS=calculationResult+"";
            String[] parts = calculationResultS.split("\\.");
            if(parts.length>1){
              if(parts[1].equals("0"))
                previousResult1.setText(parts[0]+""); 
              else
                previousResult1.setText(calculationResult+"");
            }else
                previousResult1.setText(calculationResult+"");
     
             }else{
                String Result1=previousResult1.getText();
            String Result2=previousResult2.getText();
            String Result3=previousResult3.getText();
            String Result4=previousResult4.getText();

            previousResult5.setText(Result4);
            previousResult4.setText(Result3);
            previousResult3.setText(Result2);
            previousResult2.setText(Result1);
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
             if(bintextField==1)
                binomialn.setText(binomialn.getText() + "3");
            else
                 binomialk.setText(binomialk.getText() + "3");
        });
        binQuickButton4.setOnAction((event) -> {
             if(bintextField==1)
                binomialn.setText(binomialn.getText() + "4");
            else
                 binomialk.setText(binomialk.getText() + "4");
        });
        binQuickButton5.setOnAction((event) -> {
             if(bintextField==1)
                binomialn.setText(binomialn.getText() + "5");
            else
                 binomialk.setText(binomialk.getText() + "5");
        });
        binQuickButton6.setOnAction((event) -> {
             if(bintextField==1)
                binomialn.setText(binomialn.getText() + "6");
            else
                 binomialk.setText(binomialk.getText() + "6");
        });
        binQuickButton7.setOnAction((event) -> {
             if(bintextField==1)
                binomialn.setText(binomialn.getText() + "7");
            else
                 binomialk.setText(binomialk.getText() + "7");

        });
        binQuickButton8.setOnAction((event) -> {
             if(bintextField==1)
                binomialn.setText(binomialn.getText() + "8");
            else
                 binomialk.setText(binomialk.getText() + "8");
        });
        binQuickButton9.setOnAction((event) -> {
             if(bintextField==1)
                binomialn.setText(binomialn.getText() + "9");
            else
                 binomialk.setText(binomialk.getText() + "9");
         });
         binQuickButton0.setOnAction((event) -> {
             if(bintextField==1)
                binomialn.setText(binomialn.getText() + "0");
            else
                 binomialk.setText(binomialk.getText() + "0");
         });
         binQuickButtonDot.setOnAction((event) -> {
             if(bintextField==1)
                binomialn.setText(binomialn.getText() + ".");
            else
                 binomialk.setText(binomialk.getText() + ".");
         });
         binQuickButtonAns.setOnAction((event) -> {
             if(bintextField==1){
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
              if(bintextField==2){
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
              
               if(bintextField==1)
            if (binomialn.getText().length() > 0) {
        String calcText = binomialn.getText().substring(0, binomialn.getText().length() - 1);
            binomialn.setText(calcText);
            }
               if(bintextField==2)
             if (binomialk.getText().length() > 0) {
        String calcText = binomialk.getText().substring(0, binomialk.getText().length() - 1);
            binomialk.setText(calcText);
            }
        });
         
         

        //more options
        VBox optionButtons=new VBox();
        Button openOptions = new Button("Lisää");
        optionButtons.getChildren().add(openOptions);
        //once openOptions opened.
        VBox optionsOpened=new VBox();
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

        //Button layout change
        backToStartScreen.setOnAction((event) -> {
            Layout.setCenter(startingLayout);
            Layout.setTop(null);
        });
        Layout.setCenter(startingLayout);
        databaseButton.setOnAction((event) -> {
            Layout.setCenter(databaseLayout);
            Layout.setTop(backToStartScreen);

        });

        calcButton.setOnAction((event) -> {
            Layout.setCenter(calcLayout);
            Layout.setTop(backToStartScreen);

        });

        graphCalcButton.setOnAction((event) -> {
            Layout.setCenter(graphCalcLayout);
            Layout.setTop(backToStartScreen);

        });

        Scene view = new Scene(Layout, 650, 350);

        window.setScene(view);
        window.show();
    }
    public void BinTextField(int i){
        bintextField= i;
    }
    
    
}
