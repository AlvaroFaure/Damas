/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author inftel02
 */
public abstract class Damas {
    
    public abstract Tablero mueveMaquina(Tablero t, char ficha);
    private String nombreJugador;
    
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
        
        return tab;
    }
    
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
    private void BBDD(String nombreJugador, int movimientos, int numeroReinas, String resultado) throws SQLException{
        boolean existe = false;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch(ClassNotFoundException e){
            System.out.println("Error");
        }
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
    private String mostrarEstadisticas(String nombreJugador) throws SQLException{
        boolean existe = false;
        String salida = "";
        String nombre = "";
        int movimientos = 0;
        int numeroReinas = 0;
        String resultadoPartida = "";
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
            System.out.println("No existe el jugador: "+nombreJugador);
        }else{
            salida = "JUGADOR: "+nombreJugador+"\n PARTIDAS JUGADAS: "+partida+"\n MOVIMIENTOS: "+movimientos+"\n NUMERO DE REINAS: "+numeroReinas+"\n VICTORIAS: "+victoria+"\n EMPATES: "+empate+"\n DERROTAS: "+derrota;
            System.out.println(salida);
        }
        return salida;
    }
}
