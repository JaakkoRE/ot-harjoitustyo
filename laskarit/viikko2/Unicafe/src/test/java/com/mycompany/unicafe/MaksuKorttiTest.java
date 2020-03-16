package com.mycompany.unicafe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Maksukortti;
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
public class MaksuKorttiTest {
    Maksukortti kortti;
    
    
    @Before
    public void setUp(){
        kortti = new Maksukortti(10000);
    }
    @Test
    public void konstruktoriAsettaaSaldonOikein(){
        assertEquals("saldo: 100.0", kortti.toString());
        assertEquals(10000,kortti.saldo());
    }
        @Test
    public void rahanLataaminenToimii(){
        kortti.lataaRahaa(10);
                assertEquals("saldo: 100.10", kortti.toString());

    }
    @Test
    public void rahanOttaminenToimiiTarpeeksiRahaa(){
        assertEquals(kortti.otaRahaa(5000),true);
        assertEquals("saldo: 50.0", kortti.toString());
        
    }
        @Test
    public void rahanOttaminenToimiiEiTarpeeksiRahaa(){
        assertEquals(kortti.otaRahaa(50000),false);
        assertEquals("saldo: 100.0", kortti.toString());
        
    }
    
    
//    public MaksuKorttiTest() {
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
