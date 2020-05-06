package databaseTests;




import calculatorprogram.calculators.Calculator;
import calculatorprogram.calculators.GraphicCalculator;
import calculatorprogram.database.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jaakko
 */
public class DatabaseTest {
    Database database;
    Calculator calc;
    GraphicCalculator graphCalc;
    @Before
    public void setUp() {
        database = new Database(true);
        calc = new Calculator();
        graphCalc = new GraphicCalculator();
        if (!database.isDatabase()) {
            database.creation();
        }
        database.passwordStatusSetter(true);
        database.addPassword("password");
        database.addValueToDatabase("saasasasasrrrsasarppppggggffssssffff", 5.0);
        }
     
    @Test
    public void passWordStatus() {
        database.passwordStatusSetter(false);
        assertEquals(database.isPasswordGiven(),false);
        database.passwordStatusSetter(true);
        assertEquals(database.isPasswordGiven(),true);
    }
    @Test
    public void passWordCreation() {
        assertEquals(database.addPassword("1234"),"salasanan tulee olla 5-12 merkkiä");
        assertEquals(database.addPassword("123456789sarr"),"salasanan tulee olla 5-12 merkkiä");
        assertEquals(database.addPassword("password"),"Salasana lisätty");
        assertEquals(database.getPassword(),"password");
        database.deletePassword();
        assertEquals(database.getPassword(),"Salasanaa ei ole olemassa");
        
    }
    @Test
    public void addingValue() {
         assertEquals(database.addMemorizedNumber("arrtttyyyuuuiiioooopppssdddffffgg", 5.),"5.0 lisätty nimellä arrtttyyyuuuiiioooopppssdddffffgg");
         assertEquals(database.addMemorizedNumber("arrtttyyyuuuiiioooopppssdddffffgg", 5.),"Nimi on jo käytössä");
         assertEquals(database.addMemorizedNumber("e", 5.),"Nimessä ei saa olla e.tä, x.ää tai ääkkösiä"); 
         assertEquals(database.addMemorizedNumber("xar", 5.),"Nimessä ei saa olla e.tä, x.ää tai ääkkösiä");
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
        database.deleteAllValues();
        database.deletePassword();

    }
}
