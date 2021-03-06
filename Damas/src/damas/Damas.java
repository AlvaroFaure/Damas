/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase abstracta que gestiona el juego de Damas
 *
 * @author Alecia Franco, Alvaro García-Faure, Amir Haddouch, Rafael Hidalgo
 */
public abstract class Damas {
    
    protected Tablero tablero;
    
    public abstract Tablero mueveMaquina(Tablero t, char ficha);
    public abstract char getModo();
    
    
    /**
     * crea una instacia de Tablero recibiendo como parametros un tablero y dos coordenadas
     * @param t contiene un tablero 
     * @param x contiene un entero con la coordenada x de la ficha
     * @param y contiene un entero con la coordenada y de la ficha
     * @param x2 contiene un entero con la coordenada x de la casilla a la que se muevev la ficha 
     * @param y2 contiene un entero con la coordenada y de la casilla a la que se mueve la ficha
     * @return Tablero devuleve un tablero con la nueva posición
     */
    public Tablero mueveJugador(Tablero t, int x, int y, int x2, int y2){
        Tablero tab = Tablero.clona(t);
        tab.moverFicha(x,y,x2,y2);

        if(!comerFicha(tab,t.getPosicion(x,y),x2,y2).isEmpty() && tab.heComido()){
            tab.setTurno(tab.getTurno());
            tab.setRestringido(true);
            tab.setFichaRestringida(new Coordenada(x2,y2));
        }else{
            tab.setTurno(!tab.getTurno());
            tab.setRestringido(false);
            tab.setFichaRestringida(null);
        }
        
        tablero = tab;
        return tab;
    }
    /**
     * crea una lista de posibles tableros
     * @param t contiene un tablero 
     * @param ficha contiene el carácter de la ficha
     * @return devuelve una lista de tableros
     */
    protected List<Tablero> generaPosiblesTableros(Tablero t, char ficha){
        List<Tablero> tableros = new ArrayList<>();
        boolean comer = puedoComer(t,ficha);
        //System.out.println("Soy "+ficha+" y comer: "+comer);
        
        for(int x=0; x<t.getDimension(); x++){
            for(int y=0; y<t.getDimension(); y++){
                if(Character.toUpperCase(t.getPosicion(x, y)) == Character.toUpperCase(ficha)){                    
                    tableros.addAll(generaTableros(getPosiblesPosiciones(Tablero.clona(t),x,y,comer),x,y,Tablero.clona(t)));
                }
            }
        }
        
        /*
        for(Tablero ta : tableros){
            System.out.println(ta.toStringPruebas());
            System.out.println("---------------\n");
        }
        */      
        return tableros;
    }
    /**
     * genera  tableros
     * @param posiciones contiene una lista de coordenadas 
     * @param x contiene un entero con la posicion x 
     * @param y contiene un entero con la posicion y
     * @param t contiene un tablero 
     * @return devuelve una lista de tableros
     */
    private List<Tablero> generaTableros(List<Coordenada> posiciones, int x, int y, Tablero t){
        List<Tablero> tableros = new ArrayList<>();
        
        for(Coordenada c : posiciones){
            Tablero tab = Tablero.clona(t);
            tab.moverFicha(x,y,c.x(),c.y());

            if(!comerFicha(tab,t.getPosicion(x,y),c.x(),c.y()).isEmpty() && tab.heComido()){
                tab.setTurno(tab.getTurno());
                tab.setRestringido(true);
                tab.setFichaRestringida(c);
            }else{
                tab.setTurno(!tab.getTurno());
                tab.setRestringido(false);
                tab.setFichaRestringida(null);
            }
                    
            tableros.add(tab);
        }
        
        return tableros;
    }
    
    /**
     * crea una lista de posibles posiciones
     * @param t contiene un tablero 
     * @param x contiene un entero con la coordenada x
     * @param y contiene un entero con la coordenada y
     * @param comer booleano
     * @return devuelve una lista de coordenadas
     */
    public List<Coordenada> getPosiblesPosiciones(Tablero t, int x, int y, Boolean comer){
        char ficha = t.getPosicion(x, y);
        
        if(comer==null){
            comer = puedoComer(t,ficha);
        }
        
        if(ficha!='X'){
            if(t.esRestringido()){
                if(t.getFichaRestringida().x()==x && t.getFichaRestringida().y()==y){
                    return comerFicha(t,ficha,x,y);
                }else{
                    return new ArrayList<>();
                }
            }else{        
                List<Coordenada> posiciones = comerFicha(t,ficha,x,y);

                if(posiciones.isEmpty() && !comer){
                    posiciones.addAll(mover(t,ficha,x,y));
                }

                return posiciones;
            }
        }else{
            return new ArrayList<>();
        }
    }
    
    /**
     * crea una lista de coordenadas en la que se come ficha
     * @param t contiene un tablero 
     * @param ficha contiene el carácter de la ficha
     * @param x devuelve un entero de la coordenada x de la ficha
     * @param y devuelve un entero de la coordenada y de la ficha
     * @return devuelve una lista de coordenadas
     */
    private List<Coordenada> comerFicha(Tablero t, char ficha, int x, int y){
        List<Coordenada> posiciones = new ArrayList<>();
        
        if(t.comerIzquierdaDelante(ficha, x, y)){
            posiciones.add(t.getDiagonal(true, true, true, ficha, x, y));
        }
        
        if(t.comerDerechaDelante(ficha,x,y)){
            posiciones.add(t.getDiagonal(false, true, true, ficha, x, y));
        }
        
        if(ficha =='B' || ficha == 'N'){
            //System.out.println("ficha: "+ficha+"x: "+x+", y: "+y);
            if(t.comerIzquierdaDetras(ficha, x, y)){
                posiciones.add(t.getDiagonal(true, true, false, ficha, x, y));
            }

            if(t.comerDerechaDetras(ficha,x,y)){
                posiciones.add(t.getDiagonal(false, true, false, ficha, x, y));
            }
            
            
            //System.out.println(posiciones);
        }
        
        return posiciones;
    }
    
    /**
     * crea una lista de coordenadas en la que se mueve ficha
     * @param t contiene un tablero 
     * @param ficha contiene el carácter de la ficha
     * @param x devuelve un entero de la coordenada x de la ficha
     * @param y devuelve un entero de la coordenada y de la ficha
     * @return devuelve una lista de coordenadas
     */
    private List<Coordenada> mover(Tablero t, char ficha, int x, int y){
        List<Coordenada> posiciones = new ArrayList<>();
        
        Coordenada moverIzq = t.getDiagonal(true, false, true, ficha, x, y);
        Coordenada moverDer = t.getDiagonal(false, false, true, ficha, x, y);

        if(t.correcta(ficha, moverIzq.x(), moverIzq.y())){
            posiciones.add(moverIzq);
        }

        if(t.correcta(ficha,moverDer.x(),moverDer.y())){
            posiciones.add(moverDer);
        }

        if(ficha =='B' || ficha == 'N'){

            Coordenada moverIzqDetras = t.getDiagonal(true, false, false, ficha, x, y);
            Coordenada moverDerDetras = t.getDiagonal(false, false, false, ficha, x, y);

            if(t.correcta(ficha, moverIzqDetras.x(), moverIzqDetras.y())){
                posiciones.add(moverIzqDetras);
            }

            if(t.correcta(ficha,moverDerDetras.x(),moverDerDetras.y())){
                posiciones.add(moverDerDetras);
            }

        }
        return posiciones;
    }
    
    
    /**
     * @param t contiene un tablero 
     * @param ficha contiene el carácter de la ficha
     * @return devuelve un booleano, true si la ficha puede comer una ficha del adversario
     */
    protected boolean puedoComer(Tablero t, char ficha){
        boolean comer = false;
        
        for(int i=0; i<t.getDimension(); i++){
            for(int j=0; j<t.getDimension(); j++){
                if(Character.toUpperCase(t.getPosicion(i,j)) == Character.toUpperCase(ficha)){
                    //System.out.println("x: "+i+", y: "+j);
                    comer = comer || !comerFicha(t,t.getPosicion(i,j),i,j).isEmpty();
                }
            }
        }
        
        return comer;
    }
    
    /**
     * @return devuelve el tablero
     */
    public Tablero getTablero(){
        return tablero;
    }
    
    /**
     * modifica el tablero recibiendo como parámetro un tablero t
     * @param t contiene un tablero
     */
    public void setTablero(Tablero t){
        tablero=t;
    }
    
    /**
     * @param t contiene un tablero 
     * @param ficha contiene el carácter de la ficha
     * @return devuelve un booleano, true si se puede mover ficha o comer ficha
     */
    public boolean hayMovimientos(Tablero t, char ficha){
        boolean hay=false;
        
        if((t.juegaBlanca() && Character.toUpperCase(ficha)=='N') || (t.juegaNegra() && Character.toUpperCase(ficha)=='B')){
            return hayMovimientos(t,Tablero.getContraria(ficha));
        }else{
            for(int i=0; i<8;i++){
                for(int j=0;j<8;j++){
                    if(Character.toUpperCase(ficha)==Character.toUpperCase(t.getPosicion(i, j))){
                        hay = hay || !getPosiblesPosiciones(t,i,j,null).isEmpty();
                    }
                }
            }
            return hay;
        }
    }
    
    /** guarda el base de datos las estadisticas de una partida
     * @param nombreJugador contiene una cadena con el nombre del jugador
     * @param movimientos contiene un entero con el numero de moviemientos en la partida
     * @param numeroReinas contiene un entero con el numero de reinas en la partida
     * @param resultado contiene una Strig con la cadena 'VICTORIA','EMPATE' o 'DERROTA'
     * @throws SQLException si no se encuentran los datos
     * @throws ClassNotFoundException  thrown when an application tries to load in a class through its string name using:
     * The forName method in class Class.
     * The findSystemClass method in class ClassLoader .
     * The loadClass method in class ClassLoader. 
     */
    public void BBDD(String nombreJugador, int movimientos, int numeroReinas, String resultado) throws SQLException, ClassNotFoundException{
        boolean existe = false;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:inftel16_12/inftel@olimpia.lcc.uma.es:1521:edgar");
        Statement stmt = con.createStatement();
        ResultSet resBaseDatos = stmt.executeQuery("SELECT nombreJugador FROM Jugador");
        
        while (resBaseDatos.next()) {
            String nombre = resBaseDatos.getString("nombreJugador");
            if (nombre.equals(nombreJugador)){
                existe = true;
            }
        }
        if(!existe){
            stmt.executeUpdate("INSERT INTO Jugador(nombreJugador) VALUES('"+nombreJugador+"')");
            System.out.println("No existe el jugador, introducimos: "+nombreJugador);
        }
        stmt.executeUpdate("INSERT INTO Partida(jugador_nombreJugador, movimientosTotales, numReinas, resultado, idPartida) VALUES('"+nombreJugador+"', "+movimientos+", "+numeroReinas+", '"+resultado+"', SECUENCIA_ID.NEXTVAL)");
        
    }
    
    /**
     * muestra las estadisticas de un jugador
     * @param nombreJugador contiene una cadena con el numbre del jugador
     * @return devuelve un string con las estadisticas totales del jugador
     * @throws SQLException si no se encuentran los datos
     */
    public String mostrarEstadisticas(String nombreJugador) throws SQLException{
        boolean existe = false;
        String salida;
        String nombre;
        
        int movimientos = 0;
        int numeroReinas = 0;
        int partida = 0;
        int victoria = 0;
        int empate = 0;
        int derrota = 0;
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch(ClassNotFoundException e){
            System.out.println("Error");
        }
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:inftel16_12/inftel@olimpia.lcc.uma.es:1521:edgar");
        Statement stmt = con.createStatement();
        ResultSet resConsultaEst = stmt.executeQuery("SELECT jugador_nombrejugador, sum(movimientostotales), count(idpartida), sum(numreinas) FROM Partida where Jugador_nombreJugador = '"+nombreJugador+"' GROUP BY jugador_nombrejugador");
        while (resConsultaEst.next()) {
            nombre = resConsultaEst.getString("jugador_nombreJugador");
            movimientos = resConsultaEst.getInt("sum(movimientosTotales)");
            numeroReinas = resConsultaEst.getInt("sum(numReinas)");
            partida = resConsultaEst.getInt("count(idPartida)");
            if (nombre.equals(nombreJugador)){
                existe = true;
            }
        }
        ResultSet resConsultaVictoria = stmt.executeQuery("SELECT count(resultado) FROM Partida where Jugador_nombreJugador = '"+nombreJugador+"' and resultado = 'VICTORIA'");
        while (resConsultaVictoria.next()) {
            victoria = resConsultaVictoria.getInt("count(resultado)");
        }
        ResultSet resConsultaEmpate = stmt.executeQuery("SELECT count(resultado) FROM Partida where Jugador_nombreJugador = '"+nombreJugador+"' and resultado = 'EMPATE'");
        while (resConsultaEmpate.next()) {
            empate = resConsultaEmpate.getInt("count(resultado)");
        }
        ResultSet resConsultaDerrota = stmt.executeQuery("SELECT count(resultado) FROM Partida where Jugador_nombreJugador = '"+nombreJugador+"' and resultado = 'DERROTA'");
        while (resConsultaDerrota.next()) {
            derrota = resConsultaDerrota.getInt("count(resultado)");
        }
        if(!existe){
            salida = "No existe el Jugador "+nombreJugador;
        }else{
            salida = "JUGADOR: "+nombreJugador+"\n PARTIDAS JUGADAS: "+partida+"\n MOVIMIENTOS: "+movimientos+"\n NUMERO DE REINAS: "+numeroReinas+"\n VICTORIAS: "+victoria+"\n EMPATES: "+empate+"\n DERROTAS: "+derrota;
        }
        return salida;
    }
}
