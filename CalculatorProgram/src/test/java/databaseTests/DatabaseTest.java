package databaseTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import calculatorprogram.database.Database;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class DatabaseTest {
    Database database;
     @Before
    public void setUp() {
        database = new Database();
        if (!database.isDatabase()) {
            database.creation();
        }
        database.deleteValue("saasasasasrrrsasarppppggggffssssffff");
        database.deleteValue("arrtttyyyuuuiiioooopppssdddffffgg");
        database.addValueToDatabase("saasasasasrrrsasarppppggggffssssffff", 5.0);
        }
     
    @Test
    public void addingValue() {
         assertEquals(database.addMemorizedNumber("arrtttyyyuuuiiioooopppssdddffffgg", 5.),"5.0 lisätty nimellä arrtttyyyuuuiiioooopppssdddffffgg");
         assertEquals(database.addMemorizedNumber("arrtttyyyuuuiiioooopppssdddffffgg", 5.),"Nimi on jo käytössä");
         assertEquals(database.addMemorizedNumber("e", 5.),"Nimessä ei saa olla e.tä tai x.ää"); 
         assertEquals(database.addMemorizedNumber("xar", 5.),"Nimessä ei saa olla e.tä tai x.ää");
         assertEquals(database.addMemorizedNumber("saari2", 5.),"Anna nimi jossa on vain kirjaimia");
    }
    @Test
    public void getValue() {
         assertEquals(database.getValue("saasasasasrrrsasarppppggggffssssffff"),"5.0");
         assertEquals(database.getValue("e"),"Nimellä ei ole arvoa");
    }
    @Test
    public void getNames() {
        assertEquals(database.getNames().get(database.getNames().size()-1),"saasasasasrrrsasarppppggggffssssffff");
    }
    @Test
    public void getValuesWithNames() {
        assertEquals(database.getValuesWithNames().get("saasasasasrrrsasarppppggggffssssffff"),5.0,0.1);
    }
    @Test
    public void isDatabase() {
        assertEquals(database.isDatabase(),true);
    }
    @After
    public void deleteValue() {
        database.deleteValue("arrtttyyyuuuiiioooopppssdddffffgg");
        database.deleteValue("saasasasasrrrsasarppppggggffssssffff");
    }
}
