/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

/**
 *
 * @author Alecia Franco, Alvaro Garcia, Amir Haddouch, Rafael Hidalgo
 */
public class Tablero {

    private char[][] tablero;
    private Boolean juega;
    
    public Tablero(){
       tablero = creaTablero();
       juega=true;
    }
    
    public Tablero(boolean juega){
       tablero = creaTablero();
       this.juega=juega;
    }
    
    /**
     * @param x Posición x del tablero
     * @param y Posición y del tablero
     * @return Devuelve la posición en el tablero
     */
    public char getPosicion(int x,int y){
        return tablero[x][y];
    }
    /**
     * @param x Añade la posición x al tablero
     * @param y Añade la posición y al tablero
     * @param ficha Añade la ficha a la posicion indicada con x e y
     */
    public void setPosicion(int x, int y, char ficha){
        this.tablero[x][y] = ficha;
    }
    
    /**
     * @return Devuelve True
     */
    public Boolean juegaBlanca() {
        return juega;
    }
    
    /**
     * @return Devuelve False
     */
    public Boolean juegaNegra() {
        return !juega;
    }
    
    /**
     * @return Devuelve el valor de juega
     */
    public Boolean getJuega() {
        return juega;
    }

    /**
     * @param juega Cambiamos el valor de la ficha
     */
    public void setFicha(Boolean juega) {
        this.juega = juega;
    }
    
    /**
     * Cambiamos el turno
     */
    public void cambiaTurno() {
        juega = !juega;
    }
    
    /**
     * @return Devuelve un tablero creado
     */
    public static char[][] creaTablero(){
       return new char[][] {
                                {'X','B','X','B','X','B','X','B'},
                                {'B','X','B','X','B','X','B','X'},
                                {'X','B','X','B','X','B','X','B'},
                                {'X','X','X','X','X','X','X','X'},
                                {'X','X','X','X','X','X','X','X'},
                                {'N','X','N','X','N','X','N','X'},
                                {'X','N','X','N','X','N','X','N'},
                                {'N','X','N','X','N','X','N','X'}
                            };
    }
    
    public String toString(){
        return "";
    }
}