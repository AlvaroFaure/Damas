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
    private boolean turno; //B=true N=false
    
    
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
    public void moverFicha(int x1,int y1, int x2, int y2){
        
        char aux = tablero[x2][y2];
        
        if(x2==0 && tablero[x1][y1]=='n'){
            tablero[x2][y2]='N';
        }else if(x2==7 && tablero[x1][y1]=='b'){
            tablero[x2][y2]='B';
        }else{
            tablero[x2][y2]=tablero[x1][y1];
        }
       
        tablero[x1][y1]=aux;
      
    }
    
    
    public boolean valida(int x, int y){
        return x>=0 && y>=0 && x<8 && y<8;
    }
    
    public boolean correcta(char ficha, int x, int y){
        return valida(x,y) && tablero[x][y]=='X';
    }
    
    public boolean prohibida(int x, int y){
        return (x%2==0 && y%2==0) || (x%2!=0 && y%2!=0);
    }
    
    public boolean comerIzquierdaDelante(char ficha, int x, int y){
        Coordenada n = getDiagonal(true,false,true,ficha,x,y);
        Coordenada d = getDiagonal(true,true,true,ficha,x,y);
        return valida(n.x(),n.y()) && valida(d.x(),d.y()) && fichaContraria(ficha,n.x(),n.y()) && tablero[d.x()][d.y()]=='X';
    }
    
    public boolean comerDerechaDelante(char ficha, int x, int y){
        Coordenada n = getDiagonal(false,false,true,ficha,x,y);
        Coordenada d = getDiagonal(false,true,true,ficha,x,y);
        return valida(n.x(),n.y()) && valida(d.x(),d.y()) && fichaContraria(ficha,n.x(),n.y()) && tablero[d.x()][d.y()]=='X';
    }
    
    public boolean comerIzquierdaDetras(char ficha, int x, int y){
        Coordenada n = getDiagonal(true,false,false,ficha,x,y);
        Coordenada d = getDiagonal(true,true,false,ficha,x,y);
        return valida(n.x(),n.y()) && valida(d.x(),d.y()) && fichaContraria(ficha,n.x(),n.y()) && tablero[d.x()][d.y()]=='X';
    }
    
    public boolean comerDerechaDetras(char ficha, int x, int y){
        Coordenada n = getDiagonal(false,false,false,ficha,x,y);
        Coordenada d = getDiagonal(false,true,false,ficha,x,y);
        return valida(n.x(),n.y()) && valida(d.x(),d.y()) && fichaContraria(ficha,n.x(),n.y()) && tablero[d.x()][d.y()]=='X';
    }
    
    public boolean fichaContraria(char ficha, int x, int y){
        return tablero[x][y]!='X' && tablero[x][y]!=Character.toLowerCase(ficha) && tablero[x][y]!=Character.toUpperCase(ficha);
    }
    
    public Coordenada getDiagonal(boolean izquierda, boolean doble, boolean delante, char ficha, int x, int y){
        if(delante){
            if(izquierda){
                if(doble){
                    if(Character.toLowerCase(ficha)=='b'){
                        return new Coordenada(x+2,y+2);
                    }else{
                        return new Coordenada(x-2,y-2);
                    }
                }else{
                    if(Character.toLowerCase(ficha)=='b'){
                        return new Coordenada(x+1,y+1);
                    }else{
                        return new Coordenada(x-1,y-1);
                    }
                }
            }else{
               if(doble){
                    if(Character.toLowerCase(ficha)=='b'){
                        return new Coordenada(x+2,y-2);
                    }else{
                        return new Coordenada(x-2,y+2);
                    }
                }else{
                    if(Character.toLowerCase(ficha)=='b'){
                        return new Coordenada(x+1,y-1);
                    }else{
                        return new Coordenada(x-1,y+1);
                    }
                } 
            }
        }else{
           if(izquierda){
                if(doble){
                    if(Character.toLowerCase(ficha)=='b'){
                        return new Coordenada(x-2,y+2);
                    }else{
                        return new Coordenada(x+2,y-2);
                    }
                }else{
                    if(Character.toLowerCase(ficha)=='b'){
                        return new Coordenada(x-1,y+1);
                    }else{
                        return new Coordenada(x+1,y-1);
                    }
                }
            }else{
               if(doble){
                    if(Character.toLowerCase(ficha)=='b'){
                        return new Coordenada(x-2,y-2);
                    }else{
                        return new Coordenada(x+2,y+2);
                    }
                }else{
                    if(Character.toLowerCase(ficha)=='b'){
                        return new Coordenada(x-1,y-1);
                    }else{
                        return new Coordenada(x+1,y+1);
                    }
                } 
            } 
        }
    }
    
    public void mover(boolean izquierda, boolean doble, boolean delante, int x, int y){
        Coordenada c = getDiagonal(izquierda,doble,delante,tablero[x][y],x,y);
        this.moverFicha(x, y, c.x(), c.y());
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
    public void setTurno(Boolean turno) {
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