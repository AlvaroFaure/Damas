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
    private boolean turno;
    
    
    //CONSTRUCTORES
    
    public Tablero(){
       tablero = creaTablero();
       turno=true;
    }
    
    public Tablero(boolean juega){
       tablero = creaTablero();
       this.turno=juega;
    }
    
    
    public Tablero(char[][] tablero, boolean juega){
       this.tablero = tablero;
       this.turno=juega;
    }
    
    
    //TRABAJANDO CON LAS POSICIONES
    
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
     * Cambia las posiciones de dos fichas
     */
    public void alternaPosiciones(int x1,int y1, int x2, int y2){
        tablero[x2][y2]=tablero[x1][y1];
        tablero[x1][y1]='X';
    }
    
    //////////////////////////////////////////////////////
    
    /**
     * @return Devuelve un tablero creado
     */
    public static char[][] creaTablero(){
       return new char[][] {
                                {'X','b','X','b','X','b','X','b'},
                                {'b','X','b','X','b','X','b','X'},
                                {'X','b','X','b','X','b','X','b'},
                                {'X','X','X','X','X','X','X','X'},
                                {'X','X','X','X','X','X','X','X'},
                                {'n','X','n','X','n','X','n','X'},
                                {'X','n','X','n','X','n','X','n'},
                                {'n','X','n','X','n','X','n','X'}
                            };
    }
    
    
    /**
     * @return Devuelve la representación del tablero como cadena de texto
     */
    public String toString(){
        StringBuilder sb  = new StringBuilder();
        
        for(int i=0; i<tablero.length;i++){
            for(int j=0; j<tablero[0].length;j++){
                if(tablero[i][j]=='X'){
                    sb.append("· ");
                }else{
                    sb.append(tablero[i][j]+" ");
                }
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    
    /**
     * @return Un array con las puntuaciones de cada uno {B,N}
     */
    public int[] cuentaPuntos(){
        int[] puntos = new int[]{0,0};
        for(int i=0; i<tablero.length;i++){
            for(int j=0; j<tablero[0].length;j++){
                if(tablero[i][j]=='B'){
                    puntos[0]++;
                }else if(tablero[i][j]=='N'){
                    puntos[1]++;
                }
            }
        }
        return puntos;
    }
    
    //SETTERS, GETTERS Y DERIVADOS
    
    public char[][] getTablero(){
        return tablero;
    }
    
    public void setTablero(char[][] tablero){
        this.tablero=tablero;
    }
    
    /**
     * @param turno Cambiamos el valor de la ficha
     */
    public void setFicha(Boolean turno) {
        this.turno = turno;
    }
    
    
    /**
     * Cambiamos el turno
     */
    public void cambiaTurno() {
        turno = !turno;
    }
    
    
    /**
     * @return Devuelve el valor de juega
     */
    public boolean getTurno() {
        return turno;
    }
    
        /**
     * @return Devuelve True
     */
    public boolean juegaBlanca() {
        return turno;
    }
    
    /**
     * @return Devuelve False
     */
    public boolean juegaNegra() {
        return !turno;
    }
}