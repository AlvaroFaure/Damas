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
    private int movimientos;
    private int reinasTotales;
    
    //CONSTRUCTORES
    
    /**
     * crea una instacia de Tablero
     */    
    public Tablero(){
       tablero = creaTablero();
       turno=false;
       restringido=false;
       fichaRestringida=null;
       reinas=new int[2];
       reinasTotales=0;
       movimientos=0;
    }
    
    /**
     * crea una instacia de Tablero recibiendo una matriz, el turno,si la ficha está restringida,ficha de la ficha restrigida, numero de movimientos totales y numero de reinas
     * @param tablero contiene una matriz bidimensional
     * @param juega es true si el turno es de la máquina, false si es del jugador
     * @param restr es true si la ficha es restringida
     * @param ficha cotiene la coordenada de la ficha restringida, si no lo está, es null
     * @param m contiene un entero que se guarda en el atributo numero de movimientos
     * @param r cotiene un entero que se guarda en atributo numero de reinas
     */ 
    public Tablero(char[][] tablero, boolean juega, boolean restr, Coordenada ficha, int m, int r){
       this.tablero = tablero;
       this.turno=juega;
       restringido=restr;
       fichaRestringida=ficha;
       cuentaReinas();
       reinasTotales=r;
       movimientos=m;
       
    }
    
    /**
     * crea una instacia de Tablero recibiendo como parametros un String
     * @param fich cadena que contiene todos los parametros que se guardan en 
     * los atributos de la clase Tablero
     * @throws FileNotFoundException hrown when an application tries to load in a class through its string name using:
     * The forName method in class Class.
     * The findSystemClass method in class ClassLoader .
     * The loadClass method in class ClassLoader. 
     */
     
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
        movimientos=scinfo.nextInt();
        reinasTotales=scinfo.nextInt();

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
     * devuelve un caracter 
     * @param x Posición x del tablero
     * @param y Posición y del tablero
     * @return Devuelve el carácter contenido en la posición del tablero
     */
    public char getPosicion(int x,int y){
        return tablero[x][y];
    }
    
    
    /**añade ficha a tablero
     * @param x Añade la posición x al tablero
     * @param y Añade la posición y al tablero
     * @param ficha Añade la ficha a la posicion indicada con x e y
     */
    public void setPosicion(int x, int y, char ficha){
        this.tablero[x][y] = ficha;
    }
    
    
    /**
     * Cambia las posiciones de dos fichas
     * @param x1 contiene un entero de la coordenada x de la ficha 1
     * @param y1 contiene un entero de la coordenada y de la ficha 1
     * @param x2 contiene un entero de la coordenada x de la ficha 2
     * @param y2 contiene un entero de la coordenada y de la ficha 2
     */
    public void moverFicha(int x1,int y1, int x2, int y2){
        
        char aux = tablero[x2][y2];
        
        if(x2==0 && tablero[x1][y1]=='n'){
            tablero[x2][y2]='N';
            reinasTotales++;
        }else if(x2==7 && tablero[x1][y1]=='b'){
            tablero[x2][y2]='B';
        }else{
            tablero[x2][y2]=tablero[x1][y1];
        }
        
        if(tablero[x1][y1]=='n' || tablero[x1][y1]=='N'){
            movimientos++;
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
    
    /**
     * Devuelve un booleano
     * @param x contiene un entero de la coordenada x 
     * @param y contiene un entero de la coordenada y
     * @return devuelve true si la posicion esta contenida en el tablero
     */
    public boolean valida(int x, int y){
        return x>=0 && y>=0 && x<8 && y<8;
    }
    /**
     * Devuelve un booleano
     * @param ficha contiene el caracter de la ficha
     * @param x contiene un entero de la coordenada x de la ficha 
     * @param y contiene un entero de la coordenada y de la ficha
     * @return devuelve true si la posicion es valida y es 'X' (casilla vacia dentro del tablero)
     */
    public boolean correcta(char ficha, int x, int y){
        return valida(x,y) && tablero[x][y]=='X';
    }
    /**
     * Devuelve un booleano
     * @param x contiene un entero de la coordenada x  
     * @param y contiene un entero de la coordenada y 
     * @return devuelve true si la coordenada corresponde a una casilla en la que
     * no se puede poner una ficha
     */
    public static boolean prohibida(int x, int y){
        return (x%2==0 && y%2==0) || (x%2!=0 && y%2!=0);
    }
    
     /**
     * Devuelve un booleano 
     * @param ficha contiene el caracter de la ficha
     * @param x contiene un entero de la coordenada x de la ficha  
     * @param y contiene un entero de la coordenada y de la ficha
     * @return devuelve true si la ficha puede comer una ficha contraria saltando a la izquierda y delante
     */
    public boolean comerIzquierdaDelante(char ficha, int x, int y){
        Coordenada n = getDiagonal(true,false,true,ficha,x,y);
        Coordenada d = getDiagonal(true,true,true,ficha,x,y);
        return valida(n.x(),n.y()) && valida(d.x(),d.y()) && fichaContraria(ficha,n.x(),n.y()) && tablero[d.x()][d.y()]=='X';
    }
    
    /**
     * Devuelve un booleano 
     * @param ficha contiene el caracter de la ficha
     * @param x contiene un entero de la coordenada x de la ficha  
     * @param y contiene un entero de la coordenada y de la ficha
     * @return devuelve true si la ficha puede comer una ficha contraria saltando a la derecha y delante
    */
     public boolean comerDerechaDelante(char ficha, int x, int y){
        Coordenada n = getDiagonal(false,false,true,ficha,x,y);
        Coordenada d = getDiagonal(false,true,true,ficha,x,y);
        return valida(n.x(),n.y()) && valida(d.x(),d.y()) && fichaContraria(ficha,n.x(),n.y()) && tablero[d.x()][d.y()]=='X';
    }
    
     /**
     * Devuelve un booleano 
     * @param ficha contiene el caracter de la ficha
     * @param x contiene un entero de la coordenada x de la ficha  
     * @param y contiene un entero de la coordenada y de la ficha
     * @return devuelve true si la ficha puede comer una ficha contraria saltando a la izquierda y detrás (solo reinas)
    */
    public boolean comerIzquierdaDetras(char ficha, int x, int y){
        Coordenada n = getDiagonal(true,false,false,ficha,x,y);
        Coordenada d = getDiagonal(true,true,false,ficha,x,y);
        return valida(n.x(),n.y()) && valida(d.x(),d.y()) && fichaContraria(ficha,n.x(),n.y()) && tablero[d.x()][d.y()]=='X';
    }
    
    /**
     * Devuelve un booleano 
     * @param ficha contiene el caracter de la ficha
     * @param x contiene un entero de la coordenada x de la ficha  
     * @param y contiene un entero de la coordenada y de la ficha
     * @return devuelve true si la ficha puede comer una ficha contraria saltando a la derecha y detrás (solo reinas)
    */
    public boolean comerDerechaDetras(char ficha, int x, int y){
        Coordenada n = getDiagonal(false,false,false,ficha,x,y);
        Coordenada d = getDiagonal(false,true,false,ficha,x,y);
        return valida(n.x(),n.y()) && valida(d.x(),d.y()) && fichaContraria(ficha,n.x(),n.y()) && tablero[d.x()][d.y()]=='X';
    }
    
    /**
     * Devuelve un booleano 
     * @param ficha contiene el caracter de la ficha
     * @param x contiene un entero de la coordenada x de la ficha  
     * @param y contiene un entero de la coordenada y de la ficha
     * @return devuelve true si en la posicion x,y se ecuentra una ficha contraria
    */
    public boolean fichaContraria(char ficha, int x, int y){
        return tablero[x][y]!='X' && tablero[x][y]!=Character.toLowerCase(ficha) && tablero[x][y]!=Character.toUpperCase(ficha);
    }
    
    /**
     * Devuelve una coordenada 
     * @param izquierda contiene un booleano, true si es izquierda, false si es derecha
     * @param doble contiene un booleano, true si es salto(come ficha), false si es solo una casillas(solo mueve)
     * @param delante contiene un booleano, true si avanza hacia delante, false si vuelve para atrás(reina)
     * @param ficha contiene el caracter de la ficha
     * @param x contiene un entero de la coordenada x de la ficha  
     * @param y contiene un entero de la coordenada y de la ficha
     * @return devuelve una coordenada x,y 
    */
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
    
    /**
     * mueve una ficha que recibe como parametro
     * @param izquierda contiene un booleano, true si es izquierda, false si es derecha
     * @param doble contiene un booleano, true si es salto(come ficha), false si es solo una casillas(solo mueve)
     * @param delante contiene un booleano, true si avanza hacia delante, false si vuelve para atrás(reina)
     * @param x contiene un entero de la coordenada x de la ficha  
     * @param y contiene un entero de la coordenada y de la ficha
    */
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
     * cuenta el numero de reinas de cada color que hay en el tablero
     */
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
    
    /**
     * @return Devuelve la representación del tablero como cadena de texto
     */
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
    
    /**
    * Devuelve el tablero
    * @return devuelve una matriz del tablero
    */
    public char[][] getTablero(){
        return tablero;
    }
    /**
    * Modifica el tablero
    * @param tablero Contiene una matriz con el tablero a modificar
    */
    public void setTablero(char[][] tablero){
        this.tablero=tablero;
    }
    
    /**
     * establece el turno 
     * @param turno contiene un booleano,si es true le toca ala maquina, si es false le toca al jugadpr
     */
    public void setTurno(Boolean turno) {
        this.turno = turno;
    }
    
    
    /**
     * Cambia el turno
     */
    public void cambiaTurno() {
        turno = !turno;
    }
    
    
    /**
     * devuelve el turno
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
    
    /**
     * @return Devuelve True si la jugada es restringida
     */
    public boolean esRestringido(){
        return restringido;
    }
    
    /**
     * @param r booleano, r es true si es restringido 
     */
    public void setRestringido(boolean r){
        restringido=r;
    }
    
    /**
     * @return Devuelve la coordenada de la ficha restringida
     */
    public Coordenada getFichaRestringida(){
        return fichaRestringida;
    }
    
    /**
     * @param fich contiene la coordenada de la ficha restringida y lo guarda
     * en el atributo fichaRestringida
     */
    public void setFichaRestringida(Coordenada fich){
        fichaRestringida=fich;
    }
    
    /**
     * @return devuelve el numero de reinas de los dos jugadores 
     */
    public int[] getReinas(){
        return reinas;
    }
    
    /**
     * @param r contiene el numero de reinas de los dos jugadores y lo guarda
     * en el atributo reinas 
     */
    public void setReinas(int[] r){
        reinas=r;
    }
    /**
     * @return devuelve la dimension del tablero
     */
    public int getDimension(){
        return tablero.length;
    }
    
    /**
     * @return devuelve true si se ha eliminado alguna ficha rival
     */
    public boolean heComido(){
        return comido;
    }
    
    /**
     * devuelve ficha contraria
     * @param f contiene el caracter de una ficha
     * @return devuelve el caracter de la ficha contraria
     */
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
    /**
     * clona u tablero
     * @param t contiene un tablero
     * @return devuelve un tablero
     */
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
        
        return new Tablero(tab,t.getTurno(),t.esRestringido(),c,t.getMovimientos(),t.getReinasTotales());
    }
    
    /**
     * escribe en fichero de texto un tablero
     * @param fich contiene una cadena con el nombre del archivo de salida 
     * @param nom contiene una cadena con el nombre del jugador
     * @param modo contiene una clase damas con el modo de juego
     * @throws  FileNotFoundException si el fichero no existe
     */
    public void guardaTablero(String fich, String nom, Damas modo) throws FileNotFoundException{
        PrintWriter pw = new PrintWriter(fich);
        pw.print(turno+",");
        pw.print(restringido+",");
        if(restringido){
            pw.print(fichaRestringida.x()+","+fichaRestringida.y()+",");
        }
        pw.print(nom+",");
        
        if(modo.getModo()=='a'){
            pw.print("a,"+movimientos+","+reinasTotales+"\n");
        }else if(modo.getModo()=='d'){
            pw.print("d,"+movimientos+","+reinasTotales+"\n");
        }else{
            pw.print("r,"+movimientos+","+reinasTotales+"\n");
        }
        
        for(int i=0; i<getDimension();i++){
            for(int j=0; j<getDimension(); j++){
                pw.print(tablero[i][j]+" ");
            }
            pw.print("\n");
        }
        
        pw.close();
    }
    
    /**
    * Devuelve el nombre
    * @return devuelve una cadena con el nombre 
    */
    public String getNombre(){
        return nombre;
    }
    
    /** 
    * @return  Devuelve una instancia de damas con el modo elegido
    */
    public Damas getModo(){
        if(modo=='a'){
            return new DamasAgresivo();
        }else if(modo=='d'){
            return new DamasDefensivo();
        }else{
            return new DamasAleatorio();
        }
    }
    
    /**
    * Devuelve movimientos
    * @return devuelve un entero con los movimientos
    */
    public int getMovimientos(){
        return movimientos;
    }
    
    /**
    * Devuelve el numero total de reinas
    * @return devuelve un entero con las reinas totales 
    */
    public int getReinasTotales(){
        return reinasTotales;
    }
}