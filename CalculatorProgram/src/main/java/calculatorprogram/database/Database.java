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
    public String creation() {
        try { 
            Statement s = db.createStatement();
            s.execute("CREATE TABLE MemorizedNumbers (id INTEGER PRIMARY KEY, name TEXT UNIQUE, value Double )");
            s.execute("CREATE TABLE Password (id INTEGER PRIMARY KEY, Password TEXT)"); 
            return "Tietokannan luonti onnistui";
        } catch (SQLException e) {
            return "Tietokanta on jo luotu";
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                
            }
        }      
    }
    
    public String addMemorizedNumber(String name, Double value) {
        if (!isDatabase()) {
            return "Tietokantaa ei ole vielä luotu";
        }
        if (!name.matches("[a-zA-Z]+") || name.equals("")) {
            return "Anna nimi jossa on vain kirjaimia";
        }
        if (name.contains("e") || name.contains("x")) {
            return "Nimessä ei saa olla e.tä tai x.ää";
        }
        
    
        return addValueToDatabase(name, value);
          
    }
    public String addValueToDatabase(String name, double value) {
        try { 
            ps = db.prepareStatement("INSERT INTO MemorizedNumbers (name,value) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setDouble(2, value);
            ps.executeUpdate();
            return value + " lisätty nimellä " + name;
        } catch (SQLException e) {
            System.out.println(e);
            return "Nimi on jo käytössä";
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
            }
        }  
    }
    public String addPassword(String password) throws SQLException {
       
        try {
            ps = db.prepareStatement("INSERT INTO Password (password) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, password);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            return "Salasana lisätty";
            
        } catch (SQLException e) {
            return "Tietokantaa ei ole vielä luotu";
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                
            }
        }
    }
    public String getValue(String name) {
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
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                
            }
        }
    }
    public void deleteValue(String name) {
        try {    
            ps = db.prepareStatement("Delete FROM MemorizedNumbers WHERE name=?");
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                
            }
        }
    }
    public void deleteAllValues() {
        try {    
            ps = db.prepareStatement("Delete FROM MemorizedNumbers");
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                
            }
        }
    }
    public HashMap<String, Double> getValuesWithNames() {
        HashMap<String, Double> values = new HashMap<>();
        try {
            ps = db.prepareStatement("SELECT name,value FROM MemorizedNumbers");
            rs = ps.executeQuery();
            while (rs.next()) {
                values.put(rs.getString("name"), rs.getDouble("value")); 
            }  
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                
            }
        }
        return values;
    }
    public ArrayList<String> getNames() {
        ArrayList<String> values = new ArrayList<>();
        try {
            ps = db.prepareStatement("SELECT name,value FROM MemorizedNumbers");
            rs = ps.executeQuery();
            while (rs.next()) {
                values.add(rs.getString("name")); 
            }  
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                
            }
        }
        return values;
    }
    
        
    
    public boolean isDatabase() {
        try {
            ps = db.prepareStatement("SELECT * FROM MemorizedNumbers");
            ps = db.prepareStatement("SELECT * FROM Password");
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (Exception e) {
                
            }
        } 
    }
}