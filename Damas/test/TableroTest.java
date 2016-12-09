/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import damas.Damas;
import damas.Coordenada;
import damas.Tablero;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author inftel03
 */
public class TableroTest {

    Tablero t;

    public TableroTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        char[][] tab = new char[][]{
            {'X', 'X', 'X', 'X', 'X', 'b', 'X', 'b'},
            {'b', 'X', 'X', 'X', 'n', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'b', 'X', 'X'},
            {'b', 'X', 'X', 'X', 'n', 'X', 'n', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'n', 'b', 'X'},
            {'X', 'X', 'X', 'X', 'n', 'X', 'X', 'n'},
            {'X', 'X', 'X', 'b', 'X', 'n', 'X', 'X'},
            {'X', 'X', 'B', 'X', 'n', 'X', 'n', 'X'}
        };
        t = new Tablero(tab, false, false, new Coordenada(7, 8), 7, 2);
        //t= new Tablero();
    }

    @After
    public void tearDown() {

    }

    /*@Test
    public void getPosicion() {
        Tablero pru = new Tablero();
        char real;
        char res1 = 'b';// pruebo posiciones (0,1)(0,3)(0,5)(0,7)(1,0)(1,2)
        char res2 = 'n';//(7,0)(5,0)(6,1)
        char res3 = 'X'; //
        real = pru.getPosicion(6, 1);
        assertEquals(real, res2);}*/

 /*@Test
    public void Valida() {
             Tablero pru= new Tablero();
             boolean esperado=false;
             boolean obtenido= pru.valida(9, 2);
             assertEquals(obtenido,esperado);
    }*/
 /*@Test
    public void Correcta() {
             Tablero pru= new Tablero();
             boolean esperado=false;
             boolean obtenido= pru.correcta('b', 0, 0);// posicion correcta
             //assertEquals("fallo se esperaban los mismos",obtenido,esperado);
             assertTrue(obtenido);
    }*/
 /*@Test
    public void prohibida() {
     
        boolean obtenido = t.prohibida(0, 2);// Posicion prohibida 
        assertTrue(obtenido);
        boolean obtenid = t.prohibida(0, 1);
        assertFalse(obtenid);        
    
    }*/
 /*@Test
    public void comerIzquierdaDelante() {
    
            Tablero pru= new Tablero();
            boolean esperado= true;
            
            boolean obtenido= pru.comerIzquierdaDelante('b', 2, 1);
            assertEquals(obtenido,esperado);
    }*/
 /*@Test
    public void comerIzquierdaDelante() {
        boolean obtenido = t.comerIzquierdaDelante('b', 2, 5);// posicion correcta
        assertTrue(obtenido);
    }*/
 /*@Test
    public void comerDerechaDelante() {     
        boolean esperado= false;
        boolean obtenido= t.comerDerechaDelante('n', 3, 4);
        assertEquals(esperado,obtenido);
    
    }*/
 /*@Test
    public void comerIzquierdaDetras() {            
        boolean obtenido= t.comerIzquierdaDetras('n', 3, 4);
         assertFalse(obtenido);   
    }*/
 /*@Test
    public void comerDerechaDetras() {            
        boolean obtenido= t.comerDerechaDetras('b', 0, 1);
         assertFalse(obtenido);   }*/
 /*@Test
    public void fichaContraria() {
            boolean esperado= true;
            boolean obtenido= t.fichaContraria('n', 3, 0);
            assertEquals(esperado, obtenido);
    }*/
 /*@Test
    public void getDiagonal() { // Se le pasa int x, int y, y retorna el tablero

        Tablero pru = null;
        Coordenada c= new Coordenada(3,2);
        Coordenada esperada= c;
        //Coordenada obtenida= pru.getDiagonal(true, false, true,'b',2,1);;
        //assertEquals(esperada,obtenida);

        try {
            Coordenada obtenida= pru.getDiagonal(true, false, true,'b',2,1);
           
            
            fail("Se esperaba excepcion NullPointerException");
        } catch (NullPointerException e) {
        }

    }
    
     /*@Test
    public void getDiagonal() { // Se le pasa int x, int y, y retorna el tablero

        Tablero pru = new Tablero();
        Coordenada c= new Coordenada(3,2);
        Coordenada obtenida= c;
        //Coordenada obtenida= pru.getDiagonal(true, false, true,'b',2,1);
        Coordenada esperada= pru.getDiagonal(true, false, true,'b', 2, 1);
        assertEquals(esperada,obtenida);
       
        } interrogante*/
 /*@Test //Interrogante
    @Override
    public String toString() {
        char[][] tab =  new char[][] {
                                {'X','X','X','X','X','b','X','b'},
                                {'b','X','X','X','n','X','X','X'},
                                {'X','X','X','X','X','b','X','X'},
                                {'b','X','X','X','n','X','n','X'},
                                {'X','X','X','X','X','n','b','X'},
                                {'X','X','X','X','n','X','X','n'},
                                {'X','X','X','b','X','n','X','X'},
                                {'X','X','B','X','n','X','n','X'}
                            };
         char[][] ta =  new char[][] {
                                {'X','b','X','b','X','b','X','b'},
                                {'b','X','X','X','n','X','X','X'},
                                {'X','X','X','X','X','b','X','X'},
                                {'b','X','X','X','n','X','n','X'},
                                {'X','X','X','X','X','n','b','X'},
                                {'X','X','X','X','n','X','X','n'},
                                {'X','X','X','b','X','n','X','X'},
                                {'X','X','B','X','n','X','n','X'}
                            };
        t = new Tablero(tab, false, false, new Coordenada(7,8),7,2);
        Tablero t1 = new Tablero(ta, false, false, new Coordenada(7,8),7,2);
        String esperado= t1.toString();
        String obtenido= t.toString();
        
         assertEquals(esperado, obtenido);   //esto tiene que dar false son tableros distintos   
        return null;
    }*/

 /*@Test
    public void cuentaFichas() {
        int[] esperado = {8, 9};
        int[] result = t.cuentaFichas();
        assertArrayEquals(esperado, result);
    }*/
 /*@Test
    public void getDimension() {
        Tablero pru;//dimension del tablero
        pru = new Tablero(true);
        int real = 8;
        int obt = 5;
        int resultado = pru.getDimension();
        assertEquals(obt, resultado);
    }*/
 /*@Test
    public void juegaBlanca() {
        //JuegaBlanca es false
        boolean obtenido = t.juegaBlanca();
        assertFalse(obtenido);
    }*/
 /*@Test
    public void juegaNegra() {
        //JuegaNegra  es true
        boolean obtenido = t.juegaNegra();
        assertTrue(obtenido);
    }*/
    /*@Test
    public void esRestringido() {
        //Restringido es false
        boolean obtenido = t.esRestringido();
        assertFalse(obtenido);
    }*/
    
    /*@Test
    public void getTurno() {
    Coordenada c= new Coordenada(3,2); // Esto debe ser falso la esperada es 7,8 
        Coordenada obtenida= c;
        Coordenada esperada= t.getFichaRestringida();
        assertEquals(esperada,obtenida);
    }*/
    
    /*@Test
    public void getReinas() {
        int[] esperado = {1, 0};//Con el tablero q tengo actualmente hay es B 
        int[] result = t.getReinas();
        assertArrayEquals(esperado, result);
    }*/
    
    
    
    
    @Test
    public void getTurno() {
        //Tablero que estoy usando Tablero t = new Tablero(tab, false, false, new Coordenada(7,8),7,2);
  
        boolean obtenido = t.getTurno();
        assertFalse(obtenido);

    }
}
