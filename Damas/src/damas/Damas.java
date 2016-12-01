/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inftel02
 */
public class Damas {
    
    
    
    
    
    
    
    
    public static List<Coordenada> getPosiblesPosiciones(Tablero t, int x, int y){
        char ficha = t.getPosicion(x, y);
        
        List<Coordenada> posiciones = comerFicha(t,ficha,x,y);
        
        if(ficha =='B' || ficha == 'N'){
            posiciones.addAll(comerReina(t,ficha,x,y));
        }
        
        if(posiciones.size()==0){
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
            
        }
        
        return posiciones;
    }
    
    private static List<Coordenada> comerFicha(Tablero t, char ficha, int x, int y){
        List<Coordenada> posiciones = new ArrayList<>();
        
        if(t.comerIzquierdaDelante(ficha, x, y)){
            posiciones.add(t.getDiagonal(true, true, true, ficha, x, y));
        }
        
        if(t.comerDerechaDelante(ficha,x,y)){
            posiciones.add(t.getDiagonal(false, true, true, ficha, x, y));
        }
        
        return posiciones;
    }
    
    private static List<Coordenada> comerReina(Tablero t, char ficha, int x, int y){
        List<Coordenada> posiciones = new ArrayList<>();
        
        if(t.comerIzquierdaDetras(ficha, x, y)){
            posiciones.add(t.getDiagonal(true, true, false, ficha, x, y));
        }
        
        if(t.comerDerechaDetras(ficha,x,y)){
            posiciones.add(t.getDiagonal(false, true, false, ficha, x, y));
        }
        
        return posiciones;
    }
    
    
}
