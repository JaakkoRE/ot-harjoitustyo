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

import calculatorprogram.calculators.CalculationMethods;
import calculatorprogram.database.Database;
import calculatorprogram.calculators.Calculator;
import calculatorprogram.calculators.GraphicCalculator;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
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
    private int binTextField;
    private int permOrBin;
    private int graphInd;
    private int dataInd;
    private int dataIndCalc;
    private int graphQuickInd;
    private CalculationMethods cm;
    private Database database;
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

        Button databaseButton = new Button("Muistin hallinta");
        Button calcButton = new Button("Laskin");
        Button graphCalcButton = new Button("Graafinen laskin");
        Button exitProgram = new Button("Lopeta");
        exitProgram.setOnAction((event) -> {
           Platform.exit();
           System.exit(0); 
        });
        //Back to menu
        Button backToStartScreen = new Button("Takaisin");

        menuButtons.getChildren().addAll(calcButton,graphCalcButton,databaseButton,exitProgram);

        startingLayout.setTop(new Label("Tervetuloa"));
        startingLayout.setCenter(menuButtons);
        Layout.setCenter(startingLayout);
        //Database layout
        
        database = new Database();
        if(!database.isDatabase())
            database.creation();
        BorderPane databaseLayoutScreens = new BorderPane();
        BorderPane databaseLayout = new BorderPane();
        Label errorMessegeDatabase = new Label("");
        
        VBox guideSetUpAndError = new VBox();
        guideSetUpAndError.setSpacing(5);
        HBox openedGuideLayout = new HBox();
        Button getGuideMessege = new Button("?");
        BorderPane guideMessegeManager = new BorderPane();
        Label guideMessege = new Label("Täällä voit poistaa ja lisätä arvoja muistiin, joita voit käyttää laskimissa." 
                + " Voit käyttää arvoa laskimissa \nkirjottamalla arvon nimen lasku kenttään tai painamalla \"Näytä muistissa olevat arvot\" nappia. ");
        Button closeGuideMessege = new Button("Ok");
        openedGuideLayout.getChildren().addAll(guideMessege,closeGuideMessege);
        
        guideMessegeManager.setLeft(getGuideMessege);
        guideSetUpAndError.getChildren().addAll(guideMessegeManager,errorMessegeDatabase);
        
        VBox databaseManagment = new VBox();
        databaseManagment.setSpacing(10);
        Button createDatabase = new Button("Luo tietokanta");
        HBox valueAddingFields = new HBox();
        valueAddingFields.setSpacing(4);
        Button addValueToDatabase = new Button("Lisää arvo muistiin");
        Label labelForName = new Label("Anna nimi");
        TextField textFieldForName = new TextField("");
        Label labelForValue = new Label("Anna arvo");
        TextField textFieldForValue = new TextField("");
        valueAddingFields.getChildren().addAll(labelForName,textFieldForName,labelForValue,textFieldForValue,addValueToDatabase);
        HBox getValuesOrDelete = new HBox();
        getValuesOrDelete.setSpacing(10);
        Button getValues = new Button("Näytä muistin arvot");
        BorderPane valueDeletion = new BorderPane();
        Button DeleteAll = new Button("Poista kaikki arvot muistista");
        HBox confirmDeletionDatabase = new HBox();
        confirmDeletionDatabase.setSpacing(5);
        Label areYousure = new Label("Oletko varma?");
        Button iAmsureDeleteData = new Button("Kyllä");
        Button cancelDeletionDatabase = new Button("Peru");
        confirmDeletionDatabase.getChildren().addAll(areYousure,iAmsureDeleteData,cancelDeletionDatabase); //täää
        valueDeletion.setCenter(DeleteAll);
        getValuesOrDelete.getChildren().addAll(getValues,valueDeletion);
        Button earlierValues = new Button("Aiemmat");
        earlierValues.setVisible(false);
        Button laterValues = new Button("Myöhemmät");
        laterValues.setVisible(false);
        HBox databaseValueManagment1 = new HBox();
        databaseValueManagment1.setSpacing(5);
        Label databaseValue1 = new Label();
        Button databasedeleteValue1 = new Button("Poista");
        databasedeleteValue1.setVisible(false);
        databaseValueManagment1.getChildren().addAll(databaseValue1,databasedeleteValue1);
        HBox databaseValueManagment2 = new HBox();
        databaseValueManagment2.setSpacing(5);
        Label databaseValue2 = new Label();
        Button databasedeleteValue2 = new Button("Poista");
        databasedeleteValue2.setVisible(false);
        databaseValueManagment2.getChildren().addAll(databaseValue2,databasedeleteValue2);
        HBox databaseValueManagment3 = new HBox();
        databaseValueManagment3.setSpacing(5);
        Label databaseValue3 = new Label();
        Button databasedeleteValue3 = new Button("Poista");
        databasedeleteValue3.setVisible(false);
        databaseValueManagment3.getChildren().addAll(databaseValue3,databasedeleteValue3);
        HBox databaseValueManagment4 = new HBox();
        databaseValueManagment4.setSpacing(5);
        Label databaseValue4 = new Label();
        Button databasedeleteValue4 = new Button("Poista");
        databasedeleteValue4.setVisible(false);
        databaseValueManagment4.getChildren().addAll(databaseValue4,databasedeleteValue4);
        HBox databaseValueManagment5 = new HBox();
        databaseValueManagment5.setSpacing(5);
        Label databaseValue5 = new Label();
        Button databasedeleteValue5 = new Button("Poista");
        databasedeleteValue5.setVisible(false);
        databaseValueManagment5.getChildren().addAll(databaseValue5,databasedeleteValue5);
        
        databaseManagment.getChildren().addAll(valueAddingFields,getValuesOrDelete,earlierValues,databaseValueManagment1,
                databaseValueManagment2,databaseValueManagment3,databaseValueManagment4,databaseValueManagment5,laterValues);
        databaseLayout.setTop(guideSetUpAndError);
        databaseLayout.setCenter(databaseManagment);
        databaseLayoutScreens.setCenter(databaseLayout);
        
        getGuideMessege.setOnAction((event) -> {
            guideMessegeManager.setLeft(openedGuideLayout);
        });
        closeGuideMessege.setOnAction((event) -> {
            guideMessegeManager.setLeft(getGuideMessege);
        });
        //password ui
        BorderPane passwordManagment = new BorderPane();
        Button deletePassWord = new Button("Poista muistinhallinnan salasana");
        HBox passwordView1 = new HBox();
        Button addPassword = new Button("Lisää salasana muistinhallintaan");
        passwordView1.getChildren().addAll(addPassword);
        HBox passwordView2 = new HBox();
        passwordView2.setSpacing(5);
        Label passwordFieldExpl = new Label("Salasana:");
        TextField passwordField = new TextField("");
        Button confirmPassword = new Button("Lisää salasana");
        Button cancelPassword = new Button("Peru");
        passwordView2.getChildren().addAll(passwordFieldExpl,passwordField,confirmPassword,cancelPassword);
        passwordManagment.setCenter(passwordView1);
        if(database.getPassword().equals("Salasanaa ei ole olemassa") || database.getPassword().equals("Tietokantaa ei ole vielä luotu")) {
            databaseLayout.setBottom(passwordManagment);
            database.passwordStatusSetter(true);
        } else {
            //if password is set but not given by user
            database.passwordStatusSetter(false);
            BorderPane givePassword = new BorderPane(); 
            HBox logInFields = new HBox();
            Label givePasswordExpl = new Label("Anna salasana: ");
            TextField passwordTextField = new TextField("");
            Button logInPassword = new Button("Kirjaudu sisään");
            Label logInError = new Label("");
            logInFields.getChildren().addAll(givePasswordExpl,passwordTextField,logInPassword,logInError);
            givePassword.setCenter(logInFields);
            databaseLayoutScreens.setCenter(givePassword);
            logInPassword.setOnAction((event) -> {
                if(database.getPassword().equals(passwordTextField.getText())) {
                    databaseLayoutScreens.setCenter(databaseLayout);
                    database.passwordStatusSetter(true);
                    databaseLayout.setBottom(deletePassWord);
                } else {
                   logInError.setText("  Virheellinen salasana");
                }
            });
        }
       
        addPassword.setOnAction((event) -> {
          passwordManagment.setCenter(passwordView2);
        });
        cancelPassword.setOnAction((event) -> {
          passwordManagment.setCenter(passwordView1);
        });
        confirmPassword.setOnAction((event) -> {
            errorMessegeDatabase.setText(database.addPassword(passwordField.getText()));
            if(errorMessegeDatabase.getText().equals("Salasana lisätty")) {
                 databaseLayout.setBottom(deletePassWord);
            }
        }); 
       deletePassWord.setOnAction((event) -> {
           database.deletePassword();
           errorMessegeDatabase.setText("Salasana poistettu");
           databaseLayout.setBottom(passwordManagment);
           passwordField.setText("");
           
       });
        createDatabase.setOnAction((event) -> {
            errorMessegeDatabase.setText(database.creation());
        });
        addValueToDatabase.setOnAction((event) -> {
            try{
                errorMessegeDatabase.setText(database.addMemorizedNumber(textFieldForName.getText(), Double.valueOf(textFieldForValue.getText())));
               
            } catch (Exception e) {
                errorMessegeDatabase.setText("Virheellinen arvo");
            }
            if(errorMessegeDatabase.getText().contains("lisätty nimellä")) {
               textFieldForName.setText("");
               textFieldForValue.setText("");
            }
    
            
        });
        dataInd=0;
        DeleteAll.setOnAction((event) -> {
            valueDeletion.setCenter(confirmDeletionDatabase);
        });
        cancelDeletionDatabase.setOnAction((event) -> {
            valueDeletion.setCenter(DeleteAll);
        });
        iAmsureDeleteData.setOnAction((event) -> {
           database.deleteAllValues();
           errorMessegeDatabase.setText("Arvot poistettu");
           databasedeleteValue1.setVisible(false);
            databasedeleteValue2.setVisible(false);
            databasedeleteValue3.setVisible(false);
            databasedeleteValue4.setVisible(false);
            databasedeleteValue5.setVisible(false);
            earlierValues.setVisible(false);
            laterValues.setVisible(false);
            databaseValue1.setText("");
            databaseValue2.setText("");
            databaseValue3.setText("");
            databaseValue4.setText("");
            databaseValue5.setText("");
            valueDeletion.setCenter(DeleteAll);
        });
        getValues.setOnAction((event) -> {
            databasedeleteValue1.setVisible(true);
            databasedeleteValue2.setVisible(true);
            databasedeleteValue3.setVisible(true);
            databasedeleteValue4.setVisible(true);
            databasedeleteValue5.setVisible(true);
            earlierValues.setVisible(true);
            laterValues.setVisible(true);
            ArrayList<String> Names = database.getNames();
            HashMap<String,Double> Values = database.getValuesWithNames();
            if(Values.isEmpty()) {
                if(database.isDatabase()) {
                    if(database.isPasswordGiven()==false) {
                        errorMessegeDatabase.setText("Salasanaa ei ole annettu");
                    }
                    else {
                    errorMessegeDatabase.setText("Arvoja ei ole vielä lisätty");
                    }
                }
                else 
                    errorMessegeDatabase.setText("Tietokantaa ei ole vielä luotu");
            }
            else{
                errorMessegeDatabase.setText("");
                for(int i=0;i<=4&&i<Names.size();i++){
                    if(i==0)
                        databaseValue1.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(i==1)
                        databaseValue2.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(i==2)
                        databaseValue3.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(i==3)
                        databaseValue4.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(i==4)
                        databaseValue5.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    dataInd=i;
                }
               
            }
            if (databaseValue1.getText().isBlank()) 
                databasedeleteValue1.setVisible(false);
            else
                databasedeleteValue1.setVisible(true);
            if (databaseValue2.getText().isBlank()) 
                databasedeleteValue2.setVisible(false);
            else
                databasedeleteValue2.setVisible(true);
            if (databaseValue3.getText().isBlank()) 
                databasedeleteValue3.setVisible(false);
            else
                databasedeleteValue3.setVisible(true);
            if (databaseValue4.getText().isBlank()) 
                databasedeleteValue4.setVisible(false);
            else
                databasedeleteValue4.setVisible(true);
            if (databaseValue5.getText().isBlank()) 
                databasedeleteValue5.setVisible(false);
            else
                databasedeleteValue5.setVisible(true);
        });
        
        laterValues.setOnAction((event) -> {
            ArrayList<String> Names = database.getNames();
            HashMap<String,Double> Values = database.getValuesWithNames();
            if(dataInd<Names.size()-1){
            databaseValue1.setText("");
            databaseValue2.setText("");
            databaseValue3.setText("");
            databaseValue4.setText("");
            databaseValue5.setText("");
            if(Values.isEmpty()) {
            if(database.isPasswordGiven()==false) {
                errorMessegeDatabase.setText("Salasanaa ei ole annettu");
            }
               else {
                    errorMessegeDatabase.setText("Arvoja ei ole vielä lisätty");
                    }
            }
            else{
                errorMessegeDatabase.setText(""); 
                int ind = 0;
                dataInd++;
                int dataIndCopy=dataInd;
                for(int i=dataIndCopy;i<=dataIndCopy+4&&i<Names.size();i++){
                    if(ind==0)
                        databaseValue1.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==1)
                        databaseValue2.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==2)
                        databaseValue3.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==3)
                        databaseValue4.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==4)
                        databaseValue5.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    ind++;
                    dataInd=i;
                }
               
            }
            }
            if (databaseValue1.getText().isBlank()) 
                databasedeleteValue1.setVisible(false);
            else
                databasedeleteValue1.setVisible(true);
            if (databaseValue2.getText().isBlank()) 
                databasedeleteValue2.setVisible(false);
            else
                databasedeleteValue2.setVisible(true);
            if (databaseValue3.getText().isBlank()) 
                databasedeleteValue3.setVisible(false);
            else
                databasedeleteValue3.setVisible(true);
            if (databaseValue4.getText().isBlank()) 
                databasedeleteValue4.setVisible(false);
            else
                databasedeleteValue4.setVisible(true);
            if (databaseValue5.getText().isBlank()) 
                databasedeleteValue5.setVisible(false);
            else
                databasedeleteValue5.setVisible(true);
        });
        
        earlierValues.setOnAction((event) -> {
            if(!databaseValue1.getText().isBlank())
                dataInd--;
            if(!databaseValue2.getText().isBlank())
                dataInd--;
            if(!databaseValue3.getText().isBlank())
                dataInd--;
            if(!databaseValue4.getText().isBlank())
                dataInd--;
            if(!databaseValue5.getText().isBlank())
                dataInd--;
            databaseValue1.setText("");
            databaseValue2.setText("");
            databaseValue3.setText("");
            databaseValue4.setText("");
            databaseValue5.setText("");
            ArrayList<String> Names = database.getNames();
            HashMap<String,Double> Values = database.getValuesWithNames();
            if(Values.isEmpty()) {
                if(database.isDatabase()) {
                    if(database.isPasswordGiven()==false) {
                        errorMessegeDatabase.setText("Salasanaa ei ole annettu");
                    }
                    errorMessegeDatabase.setText("Arvoja ei ole vielä lisätty");
                }
                else
                    errorMessegeDatabase.setText("Tietokantaa ei ole vielä luotu");
            }
            else{
                errorMessegeDatabase.setText("");
                int ind = 0;
                dataInd = dataInd - 4;
                if(dataInd<0)
                     dataInd = 0;
                int dataIndCopy=dataInd;
                for(int i=dataIndCopy;i<=dataIndCopy+4&&i<Names.size();i++){
                    if(ind==0)
                        databaseValue1.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==1)
                        databaseValue2.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==2)
                        databaseValue3.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==3)
                        databaseValue4.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==4)
                        databaseValue5.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    ind++;
                    dataInd=i;
                }
               
            }
            if (databaseValue1.getText().isBlank()) 
                databasedeleteValue1.setVisible(false);
            else
                databasedeleteValue1.setVisible(true);
            if (databaseValue2.getText().isBlank()) 
                databasedeleteValue2.setVisible(false);
            else
                databasedeleteValue2.setVisible(true);
            if (databaseValue3.getText().isBlank()) 
                databasedeleteValue3.setVisible(false);
            else
                databasedeleteValue3.setVisible(true);
            if (databaseValue4.getText().isBlank()) 
                databasedeleteValue4.setVisible(false);
            else
                databasedeleteValue4.setVisible(true);
            if (databaseValue5.getText().isBlank()) 
                databasedeleteValue5.setVisible(false);
            else
                databasedeleteValue5.setVisible(true);
        });
        
        databasedeleteValue1.setOnAction((event) -> {
            String[] split = databaseValue1.getText().split(" ");
            database.deleteValue(split[0]);
            databaseValue1.setText("");
            databasedeleteValue1.setVisible(false);
        });
        databasedeleteValue2.setOnAction((event) -> {
            String[] split = databaseValue2.getText().split(" ");
            database.deleteValue(split[0]);
            databaseValue2.setText("");
            databasedeleteValue2.setVisible(false);
        });
        databasedeleteValue3.setOnAction((event) -> {
            String[] split = databaseValue3.getText().split(" ");
            database.deleteValue(split[0]);
            databaseValue3.setText("");
            databasedeleteValue3.setVisible(false);
        });
        databasedeleteValue4.setOnAction((event) -> {
            String[] split = databaseValue1.getText().split(" ");
            database.deleteValue(split[0]);
            databaseValue4.setText("");
            databasedeleteValue4.setVisible(false);
        });
        databasedeleteValue5.setOnAction((event) -> {
            String[] split = databaseValue1.getText().split(" ");
            database.deleteValue(split[0]);
            databaseValue5.setText("");
            databasedeleteValue5.setVisible(false);
        });
        //calc layout
        cm = new CalculationMethods();
        binTextField=0;
        //previous results and database ui calc
        HBox previousResults = new HBox();
        previousResults.setSpacing(135);
        VBox previousResultsVBox = new VBox();
        Label empty = new Label("");
        HBox previousResAndButton1 = new HBox();
        previousResAndButton1.setSpacing(5);
        Label previousResult1 = new Label();
        Button addToDataBaseRes1 = new Button("Lisää muistiin");
        addToDataBaseRes1.setVisible(false);
        previousResAndButton1.getChildren().addAll(previousResult1,addToDataBaseRes1);
        HBox previousResAndButton2 = new HBox();
        previousResAndButton2.setSpacing(5);
        Label previousResult2 = new Label();
        Button addToDataBaseRes2 = new Button("Lisää muistiin");
        addToDataBaseRes2.setVisible(false);
        previousResAndButton2.getChildren().addAll(previousResult2,addToDataBaseRes2);
        HBox previousResAndButton3 = new HBox();
        previousResAndButton3.setSpacing(5);
        Label previousResult3 = new Label();
        Button addToDataBaseRes3 = new Button("Lisää muistiin");
        addToDataBaseRes3.setVisible(false);
        previousResAndButton3.getChildren().addAll(previousResult3,addToDataBaseRes3);
        HBox previousResAndButton4 = new HBox();
        previousResAndButton4.setSpacing(5);
        Label previousResult4 = new Label();
        Button addToDataBaseRes4 = new Button("Lisää muistiin");
        addToDataBaseRes4.setVisible(false);
        previousResAndButton4.getChildren().addAll(previousResult4,addToDataBaseRes4);
        HBox previousResAndButton5 = new HBox();
        previousResAndButton5.setSpacing(5);
        Label previousResult5 = new Label();
        Button addToDataBaseRes5 = new Button("Lisää muistiin");
        addToDataBaseRes5.setVisible(false);
        previousResAndButton5.getChildren().addAll(previousResult5,addToDataBaseRes5);
        previousResultsVBox.getChildren().addAll(previousResAndButton5, previousResAndButton4, previousResAndButton3, previousResAndButton2,
                previousResAndButton1);
        
        HBox giveNameToValue1 = new HBox();
        giveNameToValue1.setSpacing(5);
        Label giveNameExplanation1 = new Label("Anna nimi arvolle");
        TextField nameFieldValue1 = new TextField("");
        Button addValueWithName1 = new Button("Lisää");
        Button cancelValueWithName1 = new Button("Peru");
        giveNameToValue1.getChildren().addAll(giveNameExplanation1,nameFieldValue1,addValueWithName1,cancelValueWithName1);
        giveNameToValue1.setVisible(false);
        previousResAndButton1.getChildren().add(giveNameToValue1);
        
        HBox giveNameToValue2 = new HBox();
        giveNameToValue2.setSpacing(5);
        Label giveNameExplanation2 = new Label("Anna nimi arvolle");
        TextField nameFieldValue2 = new TextField("");
        Button addValueWithName2 = new Button("Lisää");
        Button cancelValueWithName2 = new Button("Peru");
        giveNameToValue2.getChildren().addAll(giveNameExplanation2,nameFieldValue2,addValueWithName2,cancelValueWithName2);
        giveNameToValue2.setVisible(false);
        previousResAndButton2.getChildren().add(giveNameToValue2);
        
        HBox giveNameToValue3 = new HBox();
        giveNameToValue3.setSpacing(5);
        Label giveNameExplanation3 = new Label("Anna nimi arvolle");
        TextField nameFieldValue3 = new TextField("");
        Button addValueWithName3 = new Button("Lisää");
        Button cancelValueWithName3 = new Button("Peru");
        giveNameToValue3.getChildren().addAll(giveNameExplanation3,nameFieldValue3,addValueWithName3,cancelValueWithName3);
        giveNameToValue3.setVisible(false);
        previousResAndButton3.getChildren().add(giveNameToValue3);
        
        HBox giveNameToValue4 = new HBox();
        giveNameToValue4.setSpacing(5);
        Label giveNameExplanation4 = new Label("Anna nimi arvolle");
        TextField nameFieldValue4 = new TextField("");
        Button addValueWithName4 = new Button("Lisää");
        Button cancelValueWithName4 = new Button("Peru");
        giveNameToValue4.getChildren().addAll(giveNameExplanation4,nameFieldValue4,addValueWithName4,cancelValueWithName4);
        giveNameToValue4.setVisible(false);
        previousResAndButton4.getChildren().add(giveNameToValue4);
        
        HBox giveNameToValue5 = new HBox();
        giveNameToValue5.setSpacing(5);
        Label giveNameExplanation5 = new Label("Anna nimi arvolle");
        TextField nameFieldValue5 = new TextField("");
        Button addValueWithName5 = new Button("Lisää");
        Button cancelValueWithName5 = new Button("Peru");
        giveNameToValue5.getChildren().addAll(giveNameExplanation5,nameFieldValue5,addValueWithName5,cancelValueWithName5);
        giveNameToValue5.setVisible(false);
        previousResAndButton5.getChildren().add(giveNameToValue5);
        
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
            if(!(previousResult5.getText().isBlank()))
                addToDataBaseRes5.setVisible(true);
            if(!previousResult4.getText().isBlank())
                addToDataBaseRes4.setVisible(true);
            if(!previousResult3.getText().isBlank())
                addToDataBaseRes3.setVisible(true);
            if(!previousResult2.getText().isBlank())
                addToDataBaseRes2.setVisible(true);
            if(!previousResult1.getText().isBlank())
                addToDataBaseRes1.setVisible(true);
            
            if(previousResult5.getText().equals("Virheellinen syöte") || previousResult5.getText().isBlank() || previousResult5.getText().equals("ääretön") || previousResult5.getText().equals("Arvoa ei löytynyt muistista"))
               addToDataBaseRes5.setVisible(false); 
            if(previousResult4.getText().equals("Virheellinen syöte") || previousResult4.getText().isBlank() || previousResult4.getText().equals("ääretön") || previousResult4.getText().equals("Arvoa ei löytynyt muistista"))
               addToDataBaseRes4.setVisible(false); 
            if(previousResult3.getText().equals("Virheellinen syöte") || previousResult3.getText().isBlank() || previousResult3.getText().equals("ääretön") || previousResult3.getText().equals("Arvoa ei löytynyt muistista"))
               addToDataBaseRes3.setVisible(false); 
            if(previousResult2.getText().equals("Virheellinen syöte") || previousResult2.getText().isBlank() || previousResult2.getText().equals("ääretön") || previousResult2.getText().equals("Arvoa ei löytynyt muistista"))
               addToDataBaseRes2.setVisible(false); 
            if(previousResult1.getText().equals("Virheellinen syöte") || previousResult1.getText().isBlank() || previousResult1.getText().equals("ääretön") || previousResult1.getText().equals("Arvoa ei löytynyt muistista"))
               addToDataBaseRes1.setVisible(false); 
        });
        
       
        //quickselection buttons
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
        BorderPane binomialCalculationField = new BorderPane();
        HBox binomialNCRCalculationField = new HBox();
        Label binDescriptor = new Label("nCr(");
        TextField binomialn = new TextField();
        Label divider = new Label(",");
        TextField binomialk = new TextField();
        Label binParEnd = new Label(")");
        Button binCalculate = new Button("Laske");
        Label binResultField = new Label("");
        binomialCalculationField.setCenter(binomialNCRCalculationField);
        binomialNCRCalculationField.getChildren().addAll(binDescriptor, binomialn, divider, binomialk, binParEnd, binCalculate, binResultField);
        
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
        Button binScreenSwitch1 = new Button("→");
        Button binScreenSwitch2 = new Button("←");
        Button binEmpty = new Button("Tyhjennä");
        Button binQuickButtonAns = new Button("Edellinen vastaus");
        Button binDeleteLastChar = new Button("Poista");
        binButtonFieldRow3.getChildren().addAll(binScreenSwitch1,binScreenSwitch2,binEmpty,binDeleteLastChar,binQuickButtonAns);
   
        binomialLayout.getChildren().addAll(binomialCalculationField,binButtonFieldRow1);
        binomialLayout.getChildren().addAll(binButtonFieldRow2,binButtonFieldRow3);
        binCalculate.setOnAction((event) -> {
            Calculator calc = new Calculator();
                int calculationResult = 0;
                boolean error = false;
                try{
                    if(permOrBin==1)
                        calculationResult = cm.binomial(binomialn.getText(), binomialk.getText());
                    if(permOrBin==2)
                        calculationResult = cm.permutational(binomialn.getText(), binomialk.getText());
                } catch (Exception e) {
                    error = true;
                }
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
            } else
                previousResult1.setText(calculationResultS+"");
     
            
            
            if(!(previousResult5.getText().isBlank()))
                addToDataBaseRes5.setVisible(true);
            if(!previousResult4.getText().isBlank())
                addToDataBaseRes4.setVisible(true);
            if(!previousResult3.getText().isBlank())
                addToDataBaseRes3.setVisible(true);
            if(!previousResult2.getText().isBlank())
                addToDataBaseRes2.setVisible(true);
            if(!previousResult1.getText().isBlank())
                addToDataBaseRes1.setVisible(true);
            
            if(previousResult5.getText().equals("Virheellinen syöte") || previousResult5.getText().isBlank())
               addToDataBaseRes5.setVisible(false); 
            if(previousResult4.getText().equals("Virheellinen syöte") || previousResult4.getText().isBlank())
               addToDataBaseRes4.setVisible(false); 
            if(previousResult3.getText().equals("Virheellinen syöte") || previousResult3.getText().isBlank())
               addToDataBaseRes3.setVisible(false); 
            if(previousResult2.getText().equals("Virheellinen syöte") || previousResult2.getText().isBlank())
               addToDataBaseRes2.setVisible(false); 
            if(previousResult1.getText().equals("Virheellinen syöte") || previousResult1.getText().isBlank())
               addToDataBaseRes1.setVisible(false); 
         });
        binScreenSwitch1.setOnAction((event) -> {
            binomialk.setStyle(null);
            binomialn.setStyle(null);
            binTextField=2;
            binomialk.setStyle("-fx-background-color: grey;");
        });
        binScreenSwitch2.setOnAction((event) -> {
            binomialk.setStyle(null);
            binomialn.setStyle(null);
            binTextField=1;
            binomialn.setStyle("-fx-background-color: grey;");
        });
        binEmpty.setOnAction((event) -> {
            binomialn.setText("");
            binomialk.setText("");
            binResultField.setText("");
        });
        binQuickButton1.setOnAction((event) -> {
            if(binTextField==1)
                binomialn.setText(binomialn.getText() + "1");
            else
                 binomialk.setText(binomialk.getText() + "1");
                
                
        });
        binQuickButton2.setOnAction((event) -> {
             if(binTextField==1)
                binomialn.setText(binomialn.getText() + "2");
            else
                 binomialk.setText(binomialk.getText() + "2");
        });
        binQuickButton3.setOnAction((event) -> {
             if(binTextField == 1)
                binomialn.setText(binomialn.getText() + "3");
            else
                 binomialk.setText(binomialk.getText() + "3");
        });
        binQuickButton4.setOnAction((event) -> {
             if(binTextField == 1)
                binomialn.setText(binomialn.getText() + "4");
            else
                 binomialk.setText(binomialk.getText() + "4");
        });
        binQuickButton5.setOnAction((event) -> {
             if(binTextField == 1)
                binomialn.setText(binomialn.getText() + "5");
            else
                 binomialk.setText(binomialk.getText() + "5");
        });
        binQuickButton6.setOnAction((event) -> {
             if(binTextField == 1)
                binomialn.setText(binomialn.getText() + "6");
            else
                 binomialk.setText(binomialk.getText() + "6");
        });
        binQuickButton7.setOnAction((event) -> {
             if(binTextField == 1)
                binomialn.setText(binomialn.getText() + "7");
            else
                binomialk.setText(binomialk.getText() + "7");

        });
        binQuickButton8.setOnAction((event) -> {
             if(binTextField == 1)
                binomialn.setText(binomialn.getText() + "8");
            else
                binomialk.setText(binomialk.getText() + "8");
        });
        binQuickButton9.setOnAction((event) -> {
             if(binTextField == 1)
                binomialn.setText(binomialn.getText() + "9");
            else
                binomialk.setText(binomialk.getText() + "9");
         });
         binQuickButton0.setOnAction((event) -> {
             if(binTextField == 1)
                binomialn.setText(binomialn.getText() + "0");
            else
                binomialk.setText(binomialk.getText() + "0");
         });
         binQuickButtonAns.setOnAction((event) -> {
            if(binTextField == 1){
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
            if(binTextField == 2){
                if(!(previousResult1.getText().equals("Virheellinen syöte")))     
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
            if(binTextField == 1){
                if (binomialn.getText().length() > 0) {
                    String calcText = binomialn.getText().substring(0, binomialn.getText().length() - 1);
                    binomialn.setText(calcText);
                }
            }    
            if(binTextField == 2){
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
        Button permutationalCalcOpen = new Button("Permutaatio laskin");
        optionsOpened.getChildren().addAll(backFromSelection,basicCalcOperations,binomialCalcOpen,permutationalCalcOpen);
        
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
            binTextField=0;
            calcLayout.setCenter(calcFieldAndButton);
            binomialk.setStyle(null);
            binomialn.setStyle(null);
        });
        binomialCalcOpen.setOnAction((event) -> {
            binTextField=1;
            calcLayout.setCenter(binomialLayout);
            binDescriptor.setText("nCr(");
            permOrBin=1;
            binomialk.setStyle(null);
            binomialn.setStyle(null);
        });
        permutationalCalcOpen.setOnAction((event) -> {
            binTextField=1;
            calcLayout.setCenter(binomialLayout);
            binDescriptor.setText("nPr(");
            permOrBin=2;
            binomialk.setStyle(null);
            binomialn.setStyle(null);
        });
        
       
        //graphCalc layout
        BorderPane graphCalcLayout = new BorderPane();
        GraphicCalculator GraphCalc = new GraphicCalculator();
        //graphCalc calculationField
        graphInd = 1;
        HBox topRowGraphCalc = new HBox();
        topRowGraphCalc.setSpacing(5);
        VBox graphCalcFields = new VBox();
        Label graphErrorField = new Label("");
        HBox graphCalculationField = new HBox();
        Label fx = new Label("F(x)=");
        TextField graphTextField = new TextField();
        Button graphCalcCalculate = new Button("Laske");
        Button graphCalcEmpty = new Button("Poista kuvaajat");
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
                graphPrecisionLabelWarning.setText(" Varoitus hidas");
            
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
        //quick buttons graphic calculator
        VBox quickButtonRows = new VBox();
        HBox graphCalcFieldRow1 = new HBox();
        Button graphCalcQuickButtonUp = new Button("↑");
        Button graphCalcQuickButton1 = new Button("1");
        Button graphCalcQuickButton2 = new Button("2");
        Button graphCalcQuickButton3 = new Button("3");
        Button graphCalcQuickButton4 = new Button("4");
        Button graphCalcQuickButton5 = new Button("5");
        Button graphCalcQuickButtonπ = new Button("π");
        Button graphCalcQuickButtone = new Button("e");
        graphCalcFieldRow1.getChildren().addAll(graphCalcQuickButtonUp,graphCalcQuickButton1, graphCalcQuickButton2, graphCalcQuickButton3, 
                graphCalcQuickButton4, graphCalcQuickButton5,graphCalcQuickButtonπ,graphCalcQuickButtone);

        HBox graphCalcFieldRow2 = new HBox();
        Button graphCalcQuickButtonDown = new Button("↓");
        Button graphCalcQuickButton6 = new Button("6");
        Button graphCalcQuickButton7 = new Button("7");
        Button graphCalcQuickButton8 = new Button("8");
        Button graphCalcQuickButton9 = new Button("9");
        Button graphCalcQuickButton0 = new Button("0");
        Button graphCalcQuickButtonBrac1 = new Button("(");
        Button graphCalcQuickButtonBrac2 = new Button(")");
        graphCalcFieldRow2.getChildren().addAll(graphCalcQuickButtonDown,graphCalcQuickButton6, graphCalcQuickButton7, graphCalcQuickButton8, 
                graphCalcQuickButton9, graphCalcQuickButton0,graphCalcQuickButtonBrac1,graphCalcQuickButtonBrac2);
        
        HBox graphCalcFieldRow3 = new HBox();
        Button graphCalcQuickButtonRight = new Button("→");
        Button graphCalcQuickButtonSum = new Button("+");
        Button graphCalcQuickButtonNeq = new Button("-");
        Button graphCalcQuickButtonProd = new Button("*");
        Button graphCalcQuickButtonEqu = new Button("/");
        Button graphCalcQuickButtonPow = new Button("^");
        Button graphCalcQuickButtonAbs = new Button("||");
        Button graphCalcQuickButtonDot = new Button(".");
        graphCalcFieldRow3.getChildren().addAll(graphCalcQuickButtonRight,graphCalcQuickButtonSum,graphCalcQuickButtonNeq,graphCalcQuickButtonProd,graphCalcQuickButtonEqu,
        graphCalcQuickButtonPow,graphCalcQuickButtonAbs,graphCalcQuickButtonDot);
        
        HBox graphCalcFieldRow4 = new HBox();
        Button graphCalcQuickButtonLeft = new Button("← ");
        Button graphCalcQuickButtonPerc = new Button("%");
        Button graphCalcQuickButtonX = new Button("x");
        Button graphCalcEmptyField = new Button("Tyhjennä");
        Button graphCalcDeleteLastChar = new Button("Poista");
        graphCalcFieldRow4.getChildren().addAll(graphCalcQuickButtonLeft,graphCalcQuickButtonPerc,graphCalcQuickButtonX,graphCalcEmptyField,graphCalcDeleteLastChar);
        quickButtonRows.getChildren().addAll(graphCalcFieldRow1,graphCalcFieldRow2,graphCalcFieldRow3,graphCalcFieldRow4);
        topRowGraphCalc.getChildren().addAll(graphCalcFields,quickButtonRows);
        graphCalcLayout.setTop(topRowGraphCalc); 
        graphQuickInd=1;
        // quick button effects
        graphCalcQuickButton1.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"1");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"1");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"1");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"1");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"1");
        });
        graphCalcQuickButton2.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"2");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"2");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"2");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"2");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"2");
        });
        graphCalcQuickButton3.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"3");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"3");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"3");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"3");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"3");
        });
        graphCalcQuickButton4.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"4");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"4");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"4");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"4");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"4");
        });
        graphCalcQuickButton5.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"5");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"5");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"5");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"5");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"5");
        });
        graphCalcQuickButton6.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"6");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"6");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"6");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"6");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"6");
        });
        graphCalcQuickButton7.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"7");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"7");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"7");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"7");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"7");
        });
        graphCalcQuickButton8.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"8");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"8");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"8");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"8");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"8");
        });
        graphCalcQuickButton9.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"9");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"9");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"9");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"9");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"9");
        });
        graphCalcQuickButton0.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"0");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"0");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"0");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"0");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"0");
        });
        graphCalcQuickButtonSum.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"+");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"+");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"+");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"+");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"+");
        });
        graphCalcQuickButtonNeq.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"-");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"-");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"-");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"-");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"-");
        });
        graphCalcQuickButtonEqu.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"/");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"/");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"/");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"/");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"/");
        });
        graphCalcQuickButtonProd.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"*");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"*");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"*");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"*");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"*");
        });
        graphCalcQuickButtonPow.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"^");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"^");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"^");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"^");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"^");
        });
        graphCalcQuickButtonDot.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+".");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+".");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+".");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+".");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+".");
        });
        graphCalcQuickButtonBrac1.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"(");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"(");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"(");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"(");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"(");
        });
        graphCalcQuickButtonBrac2.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+")");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+")");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+")");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+")");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+")");
        });
        graphCalcQuickButtonAbs.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"|");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"|");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"|");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"|");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"|");
        });
        graphCalcQuickButtonPerc.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"%");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"%");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"%");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"%");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"%");
        });
        graphCalcQuickButtonπ.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"π");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"π");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"π");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"π");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"π");
        });
        graphCalcQuickButtone.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"e");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"e");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"e");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"e");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"e");
        });
        graphCalcQuickButtonX.setOnAction((event) -> {
            if(graphQuickInd==1)
                graphTextField.setText(graphTextField.getText()+"x");
            if(graphQuickInd==2)
                xLowerBoundTextField.setText(xLowerBoundTextField.getText()+"x");
            if(graphQuickInd==3)
                xUpperBoundTextField.setText(xUpperBoundTextField.getText()+"x");
            if(graphQuickInd==4)
                yLowerBoundTextField.setText(yLowerBoundTextField.getText()+"x");
            if(graphQuickInd==5)
                yUpperBoundTextField.setText(yUpperBoundTextField.getText()+"x");
        });
        graphCalcEmptyField.setOnAction((event) -> {
                graphTextField.setText("");
                xLowerBoundTextField.setText("");
                xUpperBoundTextField.setText("");
                yLowerBoundTextField.setText("");
                yUpperBoundTextField.setText("");
        });
        graphCalcDeleteLastChar.setOnAction((event) -> {
            if(graphQuickInd == 1){
                if (graphTextField.getText().length() > 0) {
                    String graphCalcText = graphTextField.getText().substring(0, graphTextField.getText().length() - 1);
                    graphTextField.setText(graphCalcText);
                }
            }    
            if(graphQuickInd == 2){
                if (graphTextField.getText().length() > 0) {
                    String graphCalcText = xLowerBoundTextField.getText().substring(0, xLowerBoundTextField.getText().length() - 1);
                    xLowerBoundTextField.setText(graphCalcText);
                }
            }
            if(graphQuickInd == 3){
                if (xUpperBoundTextField.getText().length() > 0) {
                    String graphCalcText = xUpperBoundTextField.getText().substring(0, xUpperBoundTextField.getText().length() - 1);
                    xUpperBoundTextField.setText(graphCalcText);
                }
            }
            if(graphQuickInd == 4){
                if (yLowerBoundTextField.getText().length() > 0) {
                    String graphCalcText = yLowerBoundTextField.getText().substring(0, yLowerBoundTextField.getText().length() - 1);
                    yLowerBoundTextField.setText(graphCalcText);
                }
            }
            if(graphQuickInd == 5){
                if (yUpperBoundTextField.getText().length() > 0) {
                    String graphCalcText = yUpperBoundTextField.getText().substring(0, yUpperBoundTextField.getText().length() - 1);
                    yUpperBoundTextField.setText(graphCalcText);
                }
            }
        });
        //where quickbuttons put text
        graphCalcQuickButtonUp.setOnAction((event) -> {
            if(!(graphQuickInd==1)){
            graphTextField.setStyle(null);
            xUpperBoundTextField.setStyle(null);
            yUpperBoundTextField.setStyle(null);
            xLowerBoundTextField.setStyle(null);
            yLowerBoundTextField.setStyle(null);
            }
            if(graphQuickInd==5){
                graphQuickInd=3;
                graphTextField.setStyle(null);
                xUpperBoundTextField.setStyle("-fx-background-color: grey;");
            }
            else if(graphQuickInd==4){
                graphQuickInd=2;
                xLowerBoundTextField.setStyle("-fx-background-color: grey;");
            }
            else if(graphQuickInd==2 || graphQuickInd==3){
                graphQuickInd=1;
                graphTextField.setStyle("-fx-background-color: grey;");
            }
        });
        graphCalcQuickButtonDown.setOnAction((event) -> {
            if(!(graphQuickInd==4 || graphQuickInd==5)){
            graphTextField.setStyle(null);
            xUpperBoundTextField.setStyle(null);
            yUpperBoundTextField.setStyle(null);
            xLowerBoundTextField.setStyle(null);
            yLowerBoundTextField.setStyle(null);
            }
            if(graphQuickInd==3){
                graphQuickInd=5;
                yUpperBoundTextField.setStyle("-fx-background-color: grey;");
            }
            else if(graphQuickInd==2){
                graphQuickInd=4;
                yLowerBoundTextField.setStyle("-fx-background-color: grey;");
            }
            else if(graphQuickInd==1){
                graphQuickInd=2;
                xLowerBoundTextField.setStyle("-fx-background-color: grey;");
            }
        });
        graphCalcQuickButtonRight.setOnAction((event) -> {
            if((graphQuickInd==2 || graphQuickInd==4)){
            graphTextField.setStyle(null);
            xUpperBoundTextField.setStyle(null);
            yUpperBoundTextField.setStyle(null);
            xLowerBoundTextField.setStyle(null);
            yLowerBoundTextField.setStyle(null);
            }
           if(graphQuickInd==2){
               graphQuickInd=3;
               xUpperBoundTextField.setStyle("-fx-background-color: grey;");
           }
           else if(graphQuickInd==4){ 
               graphQuickInd=5;
               yUpperBoundTextField.setStyle("-fx-background-color: grey;");
           }
        });
        graphCalcQuickButtonLeft.setOnAction((event) -> {
            if((graphQuickInd==3 || graphQuickInd==5)){
            graphTextField.setStyle(null);
            xUpperBoundTextField.setStyle(null);
            yUpperBoundTextField.setStyle(null);
            xLowerBoundTextField.setStyle(null);
            yLowerBoundTextField.setStyle(null);
            }
           if(graphQuickInd==3){
               graphQuickInd=2;
               xLowerBoundTextField.setStyle("-fx-background-color: grey;");
           }
           else if(graphQuickInd==5){ 
               graphQuickInd=4;
               yLowerBoundTextField.setStyle("-fx-background-color: grey;");
           }
        });
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
                }
         //   if (result<=yUpperBound&&result>=yLowerBound){
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
        //    }
           
            index++;
        }
        graphInd++;
        
           
            //cordLineChart.getData().add(resultsSeries1);
            cordLineChart.getData().addAll(resultsSeries1, resultsSeries2, resultsSeries3, resultsSeries4, resultsSeries5, resultsSeries6,
            resultsSeries7, resultsSeries8, resultsSeries9, resultsSeries10);
      
            }
         });
         
        
       
        graphCalcLayout.setCenter(cordLineChart);
        
         //calc database ui
        

        Label errorMessegeDatabaseCalc = new Label("");
        Button getValuesCalc = new Button("Näytä muistissa olevat arvot");
        Button earlierValuesCalc = new Button("Aiemmat");
        earlierValuesCalc.setVisible(false);
        Button laterValuesCalc = new Button("Myöhemmät");
        laterValuesCalc.setVisible(false);
        HBox calcValueManagment1 = new HBox();
        databaseValueManagment1.setSpacing(5);
        Label calcValue1 = new Label();
        Button calcGetValue1 = new Button("Lisää arvo");
        calcGetValue1.setVisible(false);
        calcValueManagment1.getChildren().addAll(calcValue1,calcGetValue1);
        HBox calcValueManagment2 = new HBox();
        calcValueManagment2.setSpacing(5);
        Label calcValue2 = new Label();
        Button calcGetValue2 = new Button("Lisää arvo");
        calcGetValue2.setVisible(false);
        calcValueManagment2.getChildren().addAll(calcValue2,calcGetValue2);
        HBox calcValueManagment3 = new HBox();
        calcValueManagment3.setSpacing(5);
        Label calcValue3 = new Label();
        Button calcGetValue3 = new Button("Lisää arvo");
        calcGetValue3.setVisible(false);
        calcValueManagment3.getChildren().addAll(calcValue3,calcGetValue3);
        HBox calcValueManagment4 = new HBox();
        calcValueManagment4.setSpacing(5);
        Label calcValue4 = new Label();
        Button calcGetValue4 = new Button("Lisää arvo");
        calcGetValue4.setVisible(false);
        calcValueManagment4.getChildren().addAll(calcValue4,calcGetValue4);
        HBox calcValueManagment5 = new HBox();
        calcValueManagment5.setSpacing(5);
        Label calcValue5 = new Label();
        Button calcGetValue5 = new Button("Lisää arvo");
        calcGetValue5.setVisible(false);
        calcValueManagment5.getChildren().addAll(calcValue5,calcGetValue5);
        VBox calculatorDatabase = new VBox();
        
        calculatorDatabase.getChildren().addAll(errorMessegeDatabaseCalc,getValuesCalc,earlierValuesCalc,calcValueManagment1,calcValueManagment2,calcValueManagment3,calcValueManagment4,calcValueManagment5,laterValuesCalc);
        calcLayout.setRight(calculatorDatabase);
        dataIndCalc=0;
        getValuesCalc.setOnAction((event) -> {
            calcGetValue1.setVisible(true);
            calcGetValue2.setVisible(true);
            calcGetValue3.setVisible(true);
            calcGetValue4.setVisible(true);
            calcGetValue5.setVisible(true);
            earlierValuesCalc.setVisible(true);
            laterValuesCalc.setVisible(true);
            ArrayList<String> Names = database.getNames();
            HashMap<String,Double> Values = database.getValuesWithNames();
            
            if(Values.isEmpty()) {
                if(database.isDatabase()) {
                    if(database.isPasswordGiven()==false) {
                        errorMessegeDatabaseCalc.setText("Salasanaa ei ole annettu");
                    } else {
                    errorMessegeDatabaseCalc.setText("Arvoja ei ole vielä lisätty");
                    }
                }
                else
                    errorMessegeDatabaseCalc.setText("Tietokantaa ei ole vielä luotu");
            }
            else{
                errorMessegeDatabaseCalc.setText("");
                for(int i=0;i<=4&&i<Names.size();i++){
                    if(i==0)
                        calcValue1.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(i==1)
                        calcValue2.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(i==2)
                        calcValue3.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(i==3)
                        calcValue4.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(i==4)
                        calcValue5.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    dataIndCalc=i;
                }
               
            }
            if (calcValue1.getText().isBlank()) 
                calcGetValue1.setVisible(false);
            else
                calcGetValue1.setVisible(true);
            if (calcValue2.getText().isBlank()) 
                calcGetValue2.setVisible(false);
            else
                calcGetValue2.setVisible(true);
            if (calcValue3.getText().isBlank()) 
                calcGetValue3.setVisible(false);
            else
                calcGetValue3.setVisible(true);
            if (calcValue4.getText().isBlank()) 
                calcGetValue4.setVisible(false);
            else
                calcGetValue4.setVisible(true);
            if (calcValue5.getText().isBlank()) 
                calcGetValue5.setVisible(false);
            else
                calcGetValue5.setVisible(true);
        });
        
        laterValuesCalc.setOnAction((event) -> {
            ArrayList<String> Names = database.getNames();
            HashMap<String,Double> Values = database.getValuesWithNames();
            if(dataIndCalc<Names.size()-1){
                calcValue1.setText("");
                calcValue2.setText("");
                calcValue3.setText("");
                calcValue4.setText("");
                calcValue5.setText("");
            if(Values.isEmpty()) {
                if(database.isDatabase()) {
                    if(database.isPasswordGiven()==false) {
                        errorMessegeDatabaseCalc.setText("Salasanaa ei ole annettu");
                    }else {
                    errorMessegeDatabaseCalc.setText("Arvoja ei ole vielä lisätty");
                    }
                }
                else 
                    errorMessegeDatabaseCalc.setText("Tietokantaa ei ole vielä luotu");
            }
            else{
                errorMessegeDatabaseCalc.setText("");
                int ind = 0;
                dataIndCalc++;
                int dataIndCopy=dataIndCalc;
                for(int i=dataIndCopy;i<=dataIndCopy+4&&i<Names.size();i++){
                    if(ind==0)
                        calcValue1.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==1)
                        calcValue2.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==2)
                        calcValue3.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==3)
                        calcValue4.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==4)
                        calcValue5.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    ind++;
                    dataIndCalc=i;
                }
               
            }
            }
            //adding values through result field
            if (calcValue1.getText().isBlank()) 
                calcGetValue1.setVisible(false);
            else
                calcGetValue1.setVisible(true);
            if (calcValue2.getText().isBlank()) 
                calcGetValue2.setVisible(false);
            else
                calcGetValue2.setVisible(true);
            if (calcValue3.getText().isBlank()) 
                calcGetValue3.setVisible(false);
            else
                calcGetValue3.setVisible(true);
            if (calcValue4.getText().isBlank()) 
                calcGetValue4.setVisible(false);
            else
                calcGetValue4.setVisible(true);
            if (calcValue5.getText().isBlank()) 
                calcGetValue5.setVisible(false);
            else
                calcGetValue5.setVisible(true);
        });
        
         addToDataBaseRes1.setOnAction((event) -> {
            giveNameToValue1.setVisible(true);
            addToDataBaseRes1.setVisible(false);
        });
        addToDataBaseRes2.setOnAction((event) -> {
            giveNameToValue2.setVisible(true);
            addToDataBaseRes2.setVisible(false);
        });
        addToDataBaseRes3.setOnAction((event) -> {
            giveNameToValue3.setVisible(true);
            addToDataBaseRes3.setVisible(false);
        });
        addToDataBaseRes4.setOnAction((event) -> {
            giveNameToValue4.setVisible(true);
            addToDataBaseRes4.setVisible(false);
        });
        addToDataBaseRes5.setOnAction((event) -> {
            giveNameToValue5.setVisible(true);
            addToDataBaseRes5.setVisible(false);
        });
        
        cancelValueWithName1.setOnAction((event) -> {
            nameFieldValue1.setText("");
            giveNameToValue1.setVisible(false);
            addToDataBaseRes1.setVisible(true);
            
        });
        cancelValueWithName2.setOnAction((event) -> {
            nameFieldValue2.setText("");
            giveNameToValue2.setVisible(false);
            addToDataBaseRes2.setVisible(true);
            
        });
        cancelValueWithName3.setOnAction((event) -> {
            nameFieldValue3.setText("");
            giveNameToValue3.setVisible(false);
            addToDataBaseRes3.setVisible(true);
            
        });
        cancelValueWithName4.setOnAction((event) -> {
            nameFieldValue4.setText("");
            giveNameToValue4.setVisible(false);
            addToDataBaseRes4.setVisible(true);
            
        });
        cancelValueWithName5.setOnAction((event) -> {
            nameFieldValue5.setText("");
            giveNameToValue5.setVisible(false);
            addToDataBaseRes5.setVisible(true);
            
        });
        addValueWithName1.setOnAction((event) -> {
            try{
            errorMessegeDatabaseCalc.setText(database.addMemorizedNumber(nameFieldValue1.getText(), Double.valueOf(previousResult1.getText())));
            } catch (Exception e) { 
              errorMessegeDatabaseCalc.setText("Virheellinen lisäys");
            }
            nameFieldValue1.setText("");
            giveNameToValue1.setVisible(false);
            addToDataBaseRes1.setVisible(true);
        });
        addValueWithName2.setOnAction((event) -> {
            try{
            errorMessegeDatabaseCalc.setText(database.addMemorizedNumber(nameFieldValue2.getText(), Double.valueOf(previousResult2.getText())));
            } catch (Exception e) { 
              errorMessegeDatabaseCalc.setText("Virheellinen lisäys");
            }
            nameFieldValue2.setText("");
            giveNameToValue2.setVisible(false);
            addToDataBaseRes2.setVisible(true);
        });
        addValueWithName3.setOnAction((event) -> {
            try{
            errorMessegeDatabaseCalc.setText(database.addMemorizedNumber(nameFieldValue3.getText(), Double.valueOf(previousResult3.getText())));
            } catch (Exception e) { 
              errorMessegeDatabaseCalc.setText("Virheellinen lisäys");
            }
            nameFieldValue3.setText("");
            giveNameToValue3.setVisible(false);
            addToDataBaseRes3.setVisible(true);
        });
        addValueWithName4.setOnAction((event) -> {
            try{
            errorMessegeDatabaseCalc.setText(database.addMemorizedNumber(nameFieldValue4.getText(), Double.valueOf(previousResult4.getText())));
            } catch (Exception e) { 
              errorMessegeDatabaseCalc.setText("Virheellinen lisäys");
            }
            nameFieldValue4.setText("");
            giveNameToValue4.setVisible(false);
            addToDataBaseRes4.setVisible(true);
        });
        addValueWithName5.setOnAction((event) -> {
            try{
            errorMessegeDatabaseCalc.setText(database.addMemorizedNumber(nameFieldValue5.getText(), Double.valueOf(previousResult5.getText())));
            } catch (Exception e) { 
              errorMessegeDatabaseCalc.setText("Virheellinen lisäys");
            }
            nameFieldValue5.setText("");
            giveNameToValue5.setVisible(false);
            addToDataBaseRes5.setVisible(true);
            
        });
        //database view in calc
        earlierValuesCalc.setOnAction((event) -> {
            if(!calcValue1.getText().isBlank())
                dataIndCalc--;
            if(!calcValue2.getText().isBlank())
                dataIndCalc--;
            if(!calcValue3.getText().isBlank())
                dataIndCalc--;
            if(!calcValue4.getText().isBlank())
                dataIndCalc--;
            if(!calcValue5.getText().isBlank())
                dataIndCalc--;
            calcValue1.setText("");
            calcValue2.setText("");
            calcValue3.setText("");
            calcValue4.setText("");
            calcValue5.setText("");
            ArrayList<String> Names = database.getNames();
            HashMap<String,Double> Values = database.getValuesWithNames();
            if(Values.isEmpty()) {
                if(database.isDatabase()) {
                    if(database.isPasswordGiven()==false) {
                        
                        errorMessegeDatabaseCalc.setText("Salasanaa ei ole annettu");
                    }
                    else {
                    errorMessegeDatabaseCalc.setText("Arvoja ei ole vielä lisätty");
                    }
                }
                else 
                    errorMessegeDatabaseCalc.setText("Tietokantaa ei ole vielä luotu");
            }
            else{
                errorMessegeDatabaseCalc.setText("");
                int ind = 0;
                dataIndCalc = dataIndCalc - 4;
                if(dataIndCalc<0)
                     dataIndCalc = 0;
                int dataIndCopy=dataIndCalc;
                for(int i=dataIndCopy;i<=dataIndCopy+4&&i<Names.size();i++){
                    if(ind==0)
                        calcValue1.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==1)
                        calcValue2.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==2)
                        calcValue3.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==3)
                        calcValue4.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    if(ind==4)
                        calcValue5.setText(Names.get(i)+" "+Values.get(Names.get(i)));
                    ind++;
                    dataIndCalc=i;
                }
               
            }
            if (calcValue1.getText().isBlank()) 
                calcGetValue1.setVisible(false);
            else
                calcGetValue1.setVisible(true);
            if (calcValue2.getText().isBlank()) 
                calcGetValue2.setVisible(false);
            else
                calcGetValue2.setVisible(true);
            if (calcValue3.getText().isBlank()) 
                calcGetValue3.setVisible(false);
            else
                calcGetValue3.setVisible(true);
            if (calcValue4.getText().isBlank()) 
                calcGetValue4.setVisible(false);
            else
                calcGetValue4.setVisible(true);
            if (calcValue5.getText().isBlank()) 
                calcGetValue5.setVisible(false);
            else
                calcGetValue5.setVisible(true);
        });
        calcGetValue1.setOnAction((event) -> {
            String[] Split = calcValue1.getText().split(" ");
            String[] split2 = Split[1].split("\\.");
            String value = "";
            if(split2[1].equals("0")){
                value = split2[0];
            }
            else {
                value = Split[1];
            } 
            if(binTextField==0)
                calculatable.setText(calculatable.getText()+value);
            if(binTextField==1)
                binomialn.setText(binomialn.getText() + value);
            if(binTextField==2)
                binomialk.setText(binomialk.getText() + value);
            if(binTextField==3)
                if(graphQuickInd==1)
                    graphTextField.setText(graphTextField.getText() + value);
                if(graphQuickInd==2)
                    xLowerBoundTextField.setText(xLowerBoundTextField.getText() + value);
                if(graphQuickInd==3)
                    xUpperBoundTextField.setText(xUpperBoundTextField.getText() + value);
                if(graphQuickInd==4)
                    yLowerBoundTextField.setText(yLowerBoundTextField.getText() + value);
                if(graphQuickInd==5)
                    yUpperBoundTextField.setText(yUpperBoundTextField.getText() + value);
        });
        calcGetValue2.setOnAction((event) -> {
            String[] Split = calcValue2.getText().split(" ");
            String[] split2 = Split[1].split("\\.");
            String value = "";
            if(split2[1].equals("0")){
                value = split2[0];
            }
            else {
                value = Split[1];
            } 
            if(binTextField==0)
                calculatable.setText(calculatable.getText()+value);
            if(binTextField==1)
                binomialn.setText(binomialn.getText() + value);
            if(binTextField==2)
                binomialk.setText(binomialk.getText() + value);
            if(binTextField==3)
                if(graphQuickInd==1)
                    graphTextField.setText(graphTextField.getText() + value);
                if(graphQuickInd==2)
                    xLowerBoundTextField.setText(xLowerBoundTextField.getText() + value);
                if(graphQuickInd==3)
                    xUpperBoundTextField.setText(xUpperBoundTextField.getText() + value);
                if(graphQuickInd==4)
                    yLowerBoundTextField.setText(yLowerBoundTextField.getText() + value);
                if(graphQuickInd==5)
                    yUpperBoundTextField.setText(yUpperBoundTextField.getText() + value);
        });
        calcGetValue3.setOnAction((event) -> {
            String[] Split = calcValue3.getText().split(" ");
             String[] split2 = Split[1].split("\\.");
            String value = "";
            if(split2[1].equals("0")){
                value = split2[0];
            }
            else {
                value = Split[1];
            } 
            if(binTextField==0)
                calculatable.setText(calculatable.getText()+value);
            if(binTextField==1)
                binomialn.setText(binomialn.getText() + value);
            if(binTextField==2)
                binomialk.setText(binomialk.getText() + value);
            if(binTextField==3) {
                if(graphQuickInd==1)
                    graphTextField.setText(graphTextField.getText() + value);
                if(graphQuickInd==2)
                    xLowerBoundTextField.setText(xLowerBoundTextField.getText() + value);
                if(graphQuickInd==3)
                    xUpperBoundTextField.setText(xUpperBoundTextField.getText() + value);
                if(graphQuickInd==4)
                    yLowerBoundTextField.setText(yLowerBoundTextField.getText() + value);
                if(graphQuickInd==5)
                    yUpperBoundTextField.setText(yUpperBoundTextField.getText() + value);
            }
                
        });
        calcGetValue4.setOnAction((event) -> {
            String[] Split = calcValue4.getText().split(" ");
             String[] split2 = Split[1].split("\\.");
            String value = "";
            if(split2[1].equals("0")){
                value = split2[0];
            }
            else {
                value = Split[1];
            } 
            if(binTextField==0)
                calculatable.setText(calculatable.getText()+value);
            if(binTextField==1)
                binomialn.setText(binomialn.getText() + value);
            if(binTextField==2)
                binomialk.setText(binomialk.getText() + value);
            if(binTextField==3)
                if(graphQuickInd==1)
                    graphTextField.setText(graphTextField.getText() + value);
                if(graphQuickInd==2)
                    xLowerBoundTextField.setText(xLowerBoundTextField.getText() + value);
                if(graphQuickInd==3)
                    xUpperBoundTextField.setText(xUpperBoundTextField.getText() + value);
                if(graphQuickInd==4)
                    yLowerBoundTextField.setText(yLowerBoundTextField.getText() + value);
                if(graphQuickInd==5)
                    yUpperBoundTextField.setText(yUpperBoundTextField.getText() + value);
        });
        calcGetValue5.setOnAction((event) -> {
            String[] Split = calcValue5.getText().split(" ");
            String[] split2 = Split[1].split("\\.");
            String value = "";
            if(split2[1].equals("0")){
                value = split2[0];
            }
            else {
                value = Split[1];
            } 
            if(binTextField==0)
                calculatable.setText(calculatable.getText()+value);
            if(binTextField==1)
                binomialn.setText(binomialn.getText() + value);
            if(binTextField==2)
                binomialk.setText(binomialk.getText() + value);
            if(binTextField==3)
                if(graphQuickInd==1)
                    graphTextField.setText(graphTextField.getText() + value);
                if(graphQuickInd==2)
                    xLowerBoundTextField.setText(xLowerBoundTextField.getText() + value);
                if(graphQuickInd==3)
                    xUpperBoundTextField.setText(xUpperBoundTextField.getText() + value);
                if(graphQuickInd==4)
                    yLowerBoundTextField.setText(yLowerBoundTextField.getText() + value);
                if(graphQuickInd==5)
                    yUpperBoundTextField.setText(yUpperBoundTextField.getText() + value);
        });
        
        //Button layout change
        backToStartScreen.setOnAction((event) -> {
            Layout.setCenter(startingLayout);
            Layout.setTop(null);
            window.setWidth(450);
            window.setHeight(300);
            graphTextField.setStyle(null);
            xUpperBoundTextField.setStyle(null);
            yUpperBoundTextField.setStyle(null);
            xLowerBoundTextField.setStyle(null);
            yLowerBoundTextField.setStyle(null);
            binomialk.setStyle(null);
            binomialn.setStyle(null);
            graphQuickInd=1;
        });
        Layout.setCenter(startingLayout);
        
        databaseButton.setOnAction((event) -> {
            Layout.setCenter(databaseLayoutScreens);
            Layout.setTop(backToStartScreen);
            window.setWidth(695);
            window.setHeight(475);
            
        });
        
        calcButton.setOnAction((event) -> {
            Layout.setCenter(calcLayout);
            Layout.setTop(backToStartScreen);
            window.setWidth(810);
            window.setHeight(425);
            calcLayout.setRight(calculatorDatabase);
            binTextField=0;
            calcLayout.setCenter(calcFieldAndButton);
            binomialk.setStyle(null);
            binomialn.setStyle(null);
        });

        graphCalcButton.setOnAction((event) -> {
            Layout.setCenter(graphCalcLayout);
            Layout.setTop(backToStartScreen);
            window.setWidth(985);
            window.setHeight(835);
            graphCalcLayout.setRight(calculatorDatabase);
            binTextField=3;

        });

        Scene view = new Scene(Layout, 450, 300);
        window.setScene(view);
        window.show();
    }
  
    
    
    
}
