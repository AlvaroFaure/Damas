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
        t.alternaPosiciones(0,1,1,1);
        System.out.println(t.toString());
        System.out.println(t.cuentaPuntos()[0]+" - "+t.cuentaPuntos()[0]);
    }
            
}
