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

    //Tablero t;
    Tablero t= new Tablero();

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
            {'X', 'n', 'X', 'b', 'X', 'n', 'X', 'X'},
            {'X', 'X', 'B', 'X', 'n', 'X', 'n', 'X'}
        };
        t = new Tablero(tab, false, false, new Coordenada(3, 4), 7, 1);
        //t= new Tablero();
    }

    @After
    public void tearDown() {

    }

    /*@Test//1
    public void getPosicion() {
       
        char real;
        char res1 = 'b';// pruebo posiciones (0,1)(0,3)(0,5)(0,7)(1,0)(1,2)
        char res2 = 'n';//(7,0)(5,0)(6,1)
        char res3 = 'X'; //
        real = t.getPosicion(6, 1);
        assertEquals("Prueba 1 correcta",real, res2);}

 /*@Test//2
    public void Valida() {
             
             boolean esperado=false; //false porque no esta dentro del tablero
             boolean obtenido= t.valida(9, 2);
             assertEquals(obtenido,esperado);
    }
   /*@Test//3
    public void Correcta() {
            
             boolean esperado=false;
             boolean obtenido= t.correcta('b', 0, 0);// posicion correcta
             //assertEquals("fallo se esperaban los mismos",obtenido,esperado);
             assertTrue(obtenido);
    }*/
 /*@Test//4
    public void prohibida() {
     
        boolean obtenido = t.prohibida(0, 2);// Posicion prohibida 
        assertTrue(obtenido);
        boolean obtenid = t.prohibida(0, 1);
        assertFalse(obtenid);        
    
    }*/
 /*@Test
    public void comerIzquierdaDelante() {
    
            
            boolean esperado= true;
            
            boolean obtenido= t.comerIzquierdaDelante('b', 2, 5);
            assertEquals(obtenido,esperado);
    }*/
 /*@Test
    public void comerDerechaDelante() {     
        boolean esperado= false;// no esta en una posicion de comerderechadelante
        boolean obtenido= t.comerDerechaDelante('n', 2, 4);
        assertEquals(esperado,obtenido);
    
    }*/
 /*@Test
    public void comerIzquierdaDetras() {            
        boolean obtenido= t.comerIzquierdaDetras('n', 3, 4);// me dara false
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

        try {
            Coordenada obtenida= pru.getDiagonal(true, false, true,'b',2,1);           
            
            fail("Se esperaba excepcion NullPointerException");
        } catch (NullPointerException e) {
        }

    }*/
    
    @Test
    public void getDiagonal() { // Se le pasa int x, int y, y retorna el tablero

       
        Coordenada c= new Coordenada(3,6);
        Coordenada obtenida= c;
        //boolean coorc= t.correcta('n',t.getDiagonal(true, false, true, 'n', 2, 3).x(),t.getDiagonal(true, false, true, 'n', 0, 1).y());
        //espera la coordenada 3,6
        Coordenada esperada= t.getDiagonal(false, true, true,'n', 5, 4);
        assertEquals(esperada,obtenida);
    }
}
  
 /*@Test
    public void cuentaFichas() {
        int[] esperado = {8, 10};
        int[] result = t.cuentaFichas();
        assertArrayEquals(esperado, result);
    }*/
    
      /*@Test
    public void getTurno() {
        //Tablero que estoy usando Tablero t = new Tablero(tab, false, false, new Coordenada(7,8),7,2);
  
        boolean obtenido = t.getTurno();
        assertFalse(obtenido);
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
    public void FichaRestringida() {
    Coordenada c= new Coordenada(2,1); // Esto debe ser falso la esperada es 3,4 
        Coordenada obtenida= c;
        Coordenada esperada= t.getFichaRestringida();
        assertEquals(esperada,obtenida);
    } //DA FALSOOOOOOO
    
    /*@Test
    public void getReinas() {
        int[] esperado = {1, 0};//Con el tablero q tengo actualmente hay es B 
        int[] result = t.getReinas();
        assertArrayEquals(esperado, result);
    }*/
    
    /*@Test
    public void getDimension() {
      
        int obt = 8;
        int resultado = t.getDimension();
        assertEquals(obt, resultado);
    }*/
    
    /*@Test
    public void hecomido() {
         boolean obtenido = t.heComido();// he comido inicializa en false
        assertFalse(obtenido);    }*/
    
    /*@Test
    public void clona() {
        //Tablero que estoy usando Tablero t = new Tablero(tab, false, false, new Coordenada(7,8),7,2);
        char[][] tab = new char[][]{
            {'X', 'X', 'X', 'X', 'X', 'b', 'X', 'b'},
            {'b', 'X', 'X', 'X', 'n', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'b', 'X', 'X'},
            {'b', 'X', 'X', 'X', 'n', 'X', 'n', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'n', 'b', 'X'},
            {'X', 'X', 'X', 'X', 'n', 'X', 'X', 'n'},
            {'X', 'n', 'X', 'b', 'X', 'n', 'X', 'X'},
            {'X', 'X', 'B', 'X', 'n', 'X', 'n', 'X'}
        };   
        Tablero obt= Tablero.clona(t);
        Tablero esp= new Tablero(tab, false, false, new Coordenada(7,8),7,2);
        
    }*/
         
    /*@Test
    public void getReinasTotales() {
         int obtenido = t.getReinasTotales();// Reinas al finalizar el juego
         int esperado = 1;
        assertEquals(esperado,obtenido);    }*/
        

    
    

    
    
    
  

