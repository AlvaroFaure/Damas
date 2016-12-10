/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author inftel03
 */
public class prutest {
    public static void main(String[] args){
       Coordenada c= null;        
        char[][] tab =  new char[][] {
                                {'X','X','X','X','X','b','X','b'},
                                {'b','X','X','X','n','X','X','X'},
                                {'X','X','X','X','X','b','X','X'},
                                {'b','X','X','X','n','X','n','X'},
                                {'X','X','X','X','X','n','b','X'},
                                {'X','X','X','X','n','X','X','n'},
                                {'X','X','X','b','X','n','X','X'},
                                {'X','X','B','X','n','X','n','X'}
                            };
         /*char[][] ta =  new char[][] {
                                {'X','b','X','b','X','b','X','b'},
                                {'b','X','X','X','n','X','X','X'},
                                {'X','X','X','X','X','b','X','X'},
                                {'b','X','X','X','n','X','n','X'},
                                {'X','X','X','X','X','n','b','X'},
                                {'X','X','X','X','n','X','X','n'},
                                {'X','X','X','b','X','n','X','X'},
                                {'X','X','B','X','n','X','n','X'}
                            };*/
        
        //Tablero t = new Tablero("fichero.txt");
        //(char[][] tablero, boolean juega, boolean restr, Coordenada ficha, int m, int r)
        Tablero t = new Tablero(tab, false, false, new Coordenada(7,6),7,1);
        
        
        //Tablero t1 = new Tablero(ta, false, false, new Coordenada(7,8),7,2);
        
         //interroganteSystem.out.print(t.getDiagonal(false, true, true, 'n', 5, 4));
         //System.out.print(t.fichaContraria('n', 3, 0));
        //System.out.print(t.comerDerechaDetras('b', 1, 0));
          //System.out.println((t.getModo())); Este se selecciona con el radio button
        //System.out.println(t.toString());
        //System.out.println("-------------");
        //System.out.print(t1.toString());
        System.out.print(t.getDiagonal(true, false, true, 'n', 0, 0));
        //System.out.print(pru.getPosicion(7,0));
        //System.out.print(pru.cuentaPuntos());// quiero los ptos dado un determinado tablero o numero de blancas y negras 
        //System.out.print(t.toStringPruebas());
        //System.out.print(Arrays.toString(t.cuentaFichas()));
        
        //System.out.print(Arrays.toString(t.getTable[x],));
        
        
    }
}
