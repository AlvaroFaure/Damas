/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author inftel02
 */
public class Damas {
    
    public static Tablero mueveMaquina(Tablero t, char ficha){
        List<Tablero> tableros = generaPosiblesTableros(t,ficha);
        Random rnd = new Random();
        
        return tableros.get(rnd.nextInt(tableros.size()));
    }
    
    public static Tablero mueveJugador(Tablero t, int x, int y, int x2, int y2){
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
    
    private static List<Tablero> generaPosiblesTableros(Tablero t, char ficha){
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
    
    private static List<Tablero> generaTableros(List<Coordenada> posiciones, int x, int y, Tablero t){
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
    
    public static List<Coordenada> getPosiblesPosiciones(Tablero t, int x, int y, Boolean comer){
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
    
    private static List<Coordenada> comerFicha(Tablero t, char ficha, int x, int y){
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
    
    
    private static List<Coordenada> mover(Tablero t, char ficha, int x, int y){
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
    
    private static boolean puedoComer(Tablero t, char ficha){
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
}
