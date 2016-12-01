/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

/**
 *
 * @author Álvaro
 */
public class Pruebas {
    
    public static void main(String[] args){
        Tablero t = new Tablero();
        System.out.println(t.toString());
        
        t.moverFicha(2, 3, t.getDiagonal(true, false, true, 'b', 2, 3).x(), t.getDiagonal(true, false, true, 'b', 2, 3).y());
        t.moverFicha(5, 6, t.getDiagonal(true, false, true, 'n', 5, 6).x(), t.getDiagonal(true, false, true, 'n', 5, 6).y());
        
        t.moverFicha(3, 4, 4, 5);
        t.setPosicion(2,3,'b');
        t.setPosicion(4,5,'B');
        
        System.out.println(t.toString());
       
        System.out.println(Damas.getPosiblesPosiciones(t, 4, 5));
    }     
}
