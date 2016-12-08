/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Alecia Franco, Alvaro Garcia, Amir Haddouch, Rafael Hidalgo
 */
public class Tablero {

    private char[][] tablero;
    private boolean turno; //B=true N=false
    private boolean restringido;
    private Coordenada fichaRestringida;
    private boolean comido;
    private int[] reinas; //[B,N]
    private String nombre;
    private char modo;
    
    //CONSTRUCTORES
    
    public Tablero(){
       tablero = creaTablero();
       turno=false;
       restringido=false;
       fichaRestringida=null;
       reinas=new int[2];
    }
    
    public Tablero(boolean juega){
       tablero = creaTablero();
       this.turno=juega;
       restringido=false;
       fichaRestringida=null;
       reinas=new int[2];
    }
    
    
    public Tablero(char[][] tablero, boolean juega){
       this.tablero = tablero;
       this.turno=juega;
       restringido=false;
       fichaRestringida=null;
       reinas=new int[2];
       cuentaReinas();
    }
    
    public Tablero(char[][] tablero, boolean juega, boolean restr, Coordenada ficha){
       this.tablero = tablero;
       this.turno=juega;
       restringido=restr;
       fichaRestringida=ficha;
       cuentaReinas();
    }
    
    public Tablero(String fich) throws FileNotFoundException{
        File f = new File(fich);
        tablero = creaTablero();
        int x =0; int y = 0;
        
        Scanner sc = new Scanner(f);
        String linea = sc.nextLine();

        Scanner scinfo = new Scanner(linea);
        scinfo.useDelimiter(",");

        turno = scinfo.nextBoolean();
        restringido = scinfo.nextBoolean();
        fichaRestringida=null;


        if(restringido){
            fichaRestringida = new Coordenada(scinfo.nextInt(),scinfo.nextInt());
        }
        
        nombre = scinfo.next();
        modo = scinfo.next().charAt(0);

        scinfo.close();

        while(sc.hasNextLine()){
            linea = sc.nextLine();
            Scanner sc2 = new Scanner(linea);
            sc2.useDelimiter(" ");
            while(sc2.hasNext()){
                tablero[x][y]=sc2.next().charAt(0);
                y++;
            }
            System.out.println("");
            x++; y=0;
            sc2.close();
        }

        sc.close();
        cuentaReinas();
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
        
        if(Math.abs(Math.abs(x1)-Math.abs(x2))==2){
            tablero[(x1+x2)/2][(y1+y2)/2]='X';
            comido=true;
        }else{
            comido=false;
        }
       
        tablero[x1][y1]=aux;
        cuentaReinas();
    }
    
    
    public boolean valida(int x, int y){
        return x>=0 && y>=0 && x<8 && y<8;
    }
    
    public boolean correcta(char ficha, int x, int y){
        return valida(x,y) && tablero[x][y]=='X';
    }
    
    public static boolean prohibida(int x, int y){
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
    
    public void cuentaReinas(){
        reinas = new int[2];
        for(int i=0; i<getDimension(); i++){
            for(int j=0; j<getDimension(); j++){
                if(tablero[i][j]=='B'){
                    reinas[0]++;
                }
                if(tablero[i][j]=='N'){
                    reinas[1]++;
                }
            }
        }
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
    
    
    public String toStringPruebas(){
        StringBuilder sb  = new StringBuilder();
        sb.append("  0 1 2 3 4 5 6 7\n");
        for(int i=0; i<tablero.length;i++){
            sb.append(i+" ");
            for(int j=0; j<tablero[0].length;j++){
                if(tablero[i][j]=='X'){
                    sb.append("· ");
                }else{
                    sb.append(tablero[i][j]+" ");
                }
            }
            sb.append("\n");
        }
        
        String st = turno ? "máquina" : "jugador";
        sb.append("Turno de "+st+"\n");
        sb.append("Restringido: "+restringido+". Puedes mover "+fichaRestringida+"\n");
        return sb.toString();
    }
    
    
    /**
     * @return Un array con las puntuaciones de cada uno {B,N}
     */
    public int[] cuentaFichas(){
        int[] fichas = new int[2];
        for(int i=0; i<tablero.length;i++){
            for(int j=0; j<tablero[0].length;j++){
                if(Character.toUpperCase(tablero[i][j])=='B'){
                    fichas[0]++;
                }
                if(Character.toUpperCase(tablero[i][j])=='N'){
                    fichas[1]++;
                }
            }
        }
        return fichas;
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
    
    public boolean esRestringido(){
        return restringido;
    }
    
    public void setRestringido(boolean r){
        restringido=r;
    }
    
    public Coordenada getFichaRestringida(){
        return fichaRestringida;
    }
    
    public void setFichaRestringida(Coordenada fich){
        fichaRestringida=fich;
    }
    
    public int[] getReinas(){
        return reinas;
    }
    
    public void setReinas(int[] r){
        reinas=r;
    }
    
    public int getDimension(){
        return tablero.length;
    }
    
    public boolean heComido(){
        return comido;
    }
    
    public static char getContraria(char f){
        switch (f) {
            case 'b':
                return 'n';
            case 'n':
                return 'b';
            case 'N':
                return 'B';
            default:
                return 'N';
        }
    }
    
    public static Tablero clona(Tablero t){
        char[][] tab = new char[8][8];
        
        for(int i=0; i<t.getDimension(); i++){
            for(int j=0; j<t.getDimension(); j++){
                tab[i][j]=t.getPosicion(i, j);
            }
        }
        
        Coordenada c;
        if(t.getFichaRestringida()==null){
            c=null;
        }else{
            c=new Coordenada(t.getFichaRestringida());
        }
        
        return new Tablero(tab,t.getTurno(),t.esRestringido(),c);
    }
    
    public void guardaTablero(String fich, String nom, Damas modo) throws FileNotFoundException{
        PrintWriter pw = new PrintWriter(fich);
        pw.print(turno+",");
        pw.print(restringido+",");
        if(restringido){
            pw.print(fichaRestringida.x()+","+fichaRestringida.y()+",");
        }
        pw.print(nom+",");
        
        if(modo.getModo()=='a'){
            pw.print("a\n");
        }else{
            pw.print("d\n");
        }
        
        for(int i=0; i<getDimension();i++){
            for(int j=0; j<getDimension(); j++){
                pw.print(tablero[i][j]+" ");
            }
            pw.print("\n");
        }
        
        pw.close();
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public Damas getModo(){
        if(modo=='a'){
            return new DamasAgresivo();
        }else{
            return new DamasDefensivo();
        }
    }
}