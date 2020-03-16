/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaakko
 */
public class KassapaateTest {
    
     Kassapaate kassapaate;
     Maksukortti maksukortti;
    
    @Before
    public void setUp(){
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(700);
        
    }
    @Test
    public void alussaOikeatArvot(){
        assertEquals(kassapaate.kassassaRahaa(),100000);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(),0);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(),0);
        
    }
    @Test
    public void maksuOnRiittava(){
        //edullisesti
        assertEquals(kassapaate.syoEdullisesti(250),10);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(),1);
        assertEquals(kassapaate.kassassaRahaa(),100240);
        //maukkaasti
        assertEquals(kassapaate.syoMaukkaasti(410),10);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(),1);
        assertEquals(kassapaate.kassassaRahaa(),100640);
        
        
    }
    @Test
    public void maksuEiRiittava(){
        //edullisesti
        assertEquals(kassapaate.syoEdullisesti(2),2);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(),0);
        //maukkaasti
          assertEquals(kassapaate.syoMaukkaasti(2),2);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(),0);
    }
    @Test
    public void tarpeeksiRahaaKortillaOsto(){
        //edullisesti
        assertEquals(kassapaate.syoEdullisesti(maksukortti),true);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(),1);
        assertEquals(maksukortti.saldo(),460);
        //maukkaasti
         assertEquals(kassapaate.syoMaukkaasti(maksukortti),true);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(),1);
        assertEquals(maksukortti.saldo(),60);
       
        
    }
    @Test
public void eiTarpeeksiRahaaKortillaOsto(){
    maksukortti.otaRahaa(600);
       assertEquals(kassapaate.syoEdullisesti(maksukortti),false);
       assertEquals(kassapaate.syoMaukkaasti(maksukortti),false);
       assertEquals(kassapaate.maukkaitaLounaitaMyyty(),0);
       assertEquals(kassapaate.edullisiaLounaitaMyyty(),0);
       assertEquals(maksukortti.saldo(),100);
       assertEquals(kassapaate.kassassaRahaa(),100000);
       
       
}
@Test
public void kassaltaRahaaKortille(){
    //postiviinen
    kassapaate.lataaRahaaKortille(maksukortti, 10);
    assertEquals(kassapaate.kassassaRahaa(),100000+10);
    assertEquals(maksukortti.saldo(),710);
    //negatiivinen
    kassapaate.lataaRahaaKortille(maksukortti, -10);
     assertEquals(kassapaate.kassassaRahaa(),100000+10);
    assertEquals(maksukortti.saldo(),710);
   
}
    
  //testit maksukorteilla
    
    
    
    
    
    
    
//    public KassapaateTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
}
