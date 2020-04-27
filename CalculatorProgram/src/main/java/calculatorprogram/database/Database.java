/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatorprogram.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jaakko
 */

public class Database {
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection db;
    public Database()  {
        
        try { 
            this.db = DriverManager.getConnection("jdbc:sqlite:Database.db");
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                
            }
        }
    }
    /**
    * Method creates tables uses passwordSetup 
    * 
    * @return returns method result
    */
    public String creation() {
        try { 
            Statement s = db.createStatement();
            s.execute("CREATE TABLE MemorizedNumbers (id INTEGER PRIMARY KEY, name TEXT UNIQUE, value Double )");
            s.execute("CREATE TABLE Password (id INTEGER PRIMARY KEY, password TEXT)"); 
            passwordSetup();
            return "Tietokannan luonti onnistui";
        } catch (SQLException e) {
            return "Tietokanta on jo luotu";
        } finally {
            closeDatabase();
        }      
    }
    /**
    * Method sets up password so if password isnt set it doesn't cause problems
    */
    public void passwordSetup() {
        try {
            ps = db.prepareStatement("INSERT INTO Password (password) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "");
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            ps = db.prepareStatement("INSERT INTO Password (password) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "true");
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            passwordStatusSetter(true);
        } catch (SQLException e) {
        } finally {
            closeDatabase(); 
        }
    }
    
    /**
    * Method checks if name is valid and checks if password is given and database exists
    * @param name name that is given to value
    * @param value value that is being added to database
    * 
    * @return returns method result
    */
    public String addMemorizedNumber(String name, Double value) {
        if (!isDatabase() || !isPasswordGiven()) {
            return errorMesseger();
        }
        if (!name.matches("[a-zA-Z]+") || name.equals("")) {
            return "Anna nimi jossa on vain kirjaimia";
        }
        if (name.contains("e") || name.contains("x") || name.contains("ä") || name.contains("ö")) {
            return "Nimessä ei saa olla e.tä, x.ää tai ääkkösiä";
        }
        
    
        return addValueToDatabase(name, value);
          
    }
    
    /**
    * Method adds value to database
    * @param name name that is given to value
    * @param value value that is being added to database
    * 
    * @return returns method result
    */
    public String addValueToDatabase(String name, double value) {
        try { 
            ps = db.prepareStatement("INSERT INTO MemorizedNumbers (name,value) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setDouble(2, value);
            ps.executeUpdate();
            return value + " lisätty nimellä " + name;
        } catch (SQLException e) {
            return "Nimi on jo käytössä";
        } finally {
            closeDatabase(); 
        }  
    }
    /**
    * Method checks if name is valid and checks if password is given and database exists
    * finds value on database based on name and returns it
    * 
    * @param name name that is given to value
    * 
    * @return returns value or error messege
    */
    public String getValue(String name) {
        if (!isDatabase() || !isPasswordGiven()) {
            return name;
        }
        try {
            ps = db.prepareStatement("SELECT value FROM MemorizedNumbers WHERE name=?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("value") + ""; 
            } else {
                return "Nimellä ei ole arvoa"; 
            } 
        } catch (SQLException e) {
            return "Tietokantaa ei ole vielä luotu";
        } finally {
            closeDatabase(); 
        }
    }
    /**
    * Method checks if name is valid and checks if password is given and database exists
    * finds value on database based on name and deletes it
    * 
    * @param name name that is given to value
    */
    public void deleteValue(String name) {
        if (!isPasswordGiven()) {
            return;
        }
        try {    
            ps = db.prepareStatement("Delete FROM MemorizedNumbers WHERE name=?");
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeDatabase(); 
        }
    }
    /**
    * Method checks if name is valid and checks if password is given and database exists
    * deletes all values from database
    */
    public void deleteAllValues() {
        if (!isDatabase() || !isPasswordGiven()) {
            return;
        }
        try {    
            ps = db.prepareStatement("Delete FROM MemorizedNumbers");
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeDatabase(); 
        }
    }
    /**
    * Method checks if name is valid and checks if password is given and database exists
    * returns values with names
    * 
    * @return returns HashMap with values based on nama key
    */
    public HashMap<String, Double> getValuesWithNames() {
        if (!isPasswordGiven()) {
            return new HashMap<>();
        } 
        HashMap<String, Double> values = new HashMap<>();
        try {
            ps = db.prepareStatement("SELECT name,value FROM MemorizedNumbers");
            rs = ps.executeQuery();
            while (rs.next()) {
                values.put(rs.getString("name"), rs.getDouble("value")); 
            }  
        } catch (SQLException e) {
        } finally {
            closeDatabase(); 
        }
        return values;
    }
    /**
    * Method checks if name is valid and checks if password is given and database exists
    * gets all names from database
    * 
    * @return returns ArrayList with all names
    */
    public ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        try {
            ps = db.prepareStatement("SELECT name,value FROM MemorizedNumbers");
            rs = ps.executeQuery();
            while (rs.next()) {
                names.add(rs.getString("name")); 
            }  
        } catch (SQLException e) {
        } finally {
            closeDatabase();
        }
        return names;
    }
    
    /**
    * Method checks if password is valid and adds password
    * 
    * @return returns method result
    */
    public String addPassword(String password) {
        if (password.length() < 5 || password.length() > 12) {
            return "salasanan tulee olla 5-12 kirjainta";
        }
        emptyPassWordFields();
        try {
            ps = db.prepareStatement("INSERT INTO Password (password) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, password);
            ps.executeUpdate();
            ps = db.prepareStatement("INSERT INTO Password (password) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "true");
            ps.executeUpdate();
            return "Salasana lisätty";    
        } catch (SQLException e) {
            return "Tietokantaa ei ole vielä luotu";
        } finally {
            closeDatabase();
        }
    }
    
    /**
    * Method puts a status in database which tells if password has been given
    * 
    */
    public void passwordStatusSetter(boolean boolValue) {
        if (getPassword().equals("Salasanaa ei ole olemassa") || !isDatabase()) {
            return;
        }
        try {
            ps = db.prepareStatement("DELETE FROM Password WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 2);
            ps.executeUpdate();
            ps = db.prepareStatement("INSERT INTO Password (password) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, boolValue + "");
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeDatabase();
        }
    }
    /**
    * Method deletes all password fields
    * 
    */
    public void emptyPassWordFields() {
        if (!isDatabase()) {
            return;
        }
        try {
            ps = db.prepareStatement("DELETE FROM Password");
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            closeDatabase();
        }
    }
    /**
    * Method deletes all password
    * 
    */
    public void deletePassword() {
        if (getPassword().equals("Salasanaa ei ole olemassa") || getPassword().equals("Tietokantaa ei ole vielä luotu")) {
            return;
        }
        try {
            ps = db.prepareStatement("DELETE FROM Password");
            ps.executeUpdate();
            passwordSetup();
        } catch (SQLException e) {
        } finally {
            closeDatabase();
        }
    }
    /**
    * @return returns password
    */
    public String getPassword() {
        if (!isDatabase()) {
            return "Tietokantaa ei ole vielä luotu";
        }
        try {
            ps = db.prepareStatement("SELECT password FROM Password");
            rs = ps.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                if (password.equals("")) {
                    return "Salasanaa ei ole olemassa";
                }
                return password; 
            } else {
                return "Salasanaa ei ole olemassa"; 
            }
        } catch (SQLException e) {
            return "Tietokantaa ei ole vielä luotu";
        } finally {
            closeDatabase();
        }
    }
    /**
    * @return returns boolean which tells if password has been given
    */
    public Boolean isPasswordGiven() {
        if (!isDatabase()) {
            return false;
        }
        try {
            ps = db.prepareStatement("SELECT password FROM Password WHERE id=?");
            ps.setInt(1, 2);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals("true")) {
                    return true;
                } 
            } 
        } catch (SQLException e) {
            return false;
        } finally {
            closeDatabase();
        }
        return false;
    }
    /**
    * @return returns boolean which tells if database exists
    */
    public boolean isDatabase() {
        try {
            ps = db.prepareStatement("SELECT * FROM MemorizedNumbers");
            ps = db.prepareStatement("SELECT * FROM Password");
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            closeDatabase();
        } 
    }
    /**Method checks which error messege caused problem
     * 
    * @return returns error messege
    */
    public String errorMesseger() {
        if (!isDatabase()) {
            return "Tietokantaa ei ole vielä luotu";
        }
        if (!isPasswordGiven()) {
            return "Salasanaa ei ole annettu";
        }
        return "";
    }
    /**Method closes database afteruse
    * 
    */
    public void closeDatabase() {
        try {
            rs.close();
            ps.close();
        } catch (Exception e) {       
        }
    }
}