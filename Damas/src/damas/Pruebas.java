/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Álvaro
 */
public class Pruebas {
    
    public static void main(String[] args){
        
        char[][] tab =  new char[][] {
                                {'X','X','X','X','X','b','X','b'},
                                {'b','X','X','X','X','X','X','X'},
                                {'X','X','X','X','X','b','X','b'},
                                {'b','X','X','X','X','X','X','X'},
                                {'X','X','X','X','X','n','X','X'},
                                {'X','X','X','X','n','X','X','X'},
                                {'X','X','X','n','X','n','X','X'},
                                {'X','X','B','X','n','X','n','X'}
                            };
        
        
        Tablero t = new Tablero();
        try {
            t = new Tablero("fichero.txt");
            //t = new Tablero(tab,false);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DamasAgresivo dm = new DamasAgresivo();
        
        
        System.out.println(t.toStringPruebas());
        System.out.println("---------------\n");
        
        String linea = "";
        Scanner scFicha = new Scanner(System.in);
        int x=0;
        int y=0;
        List<Coordenada> posiciones;
        
        while(!linea.equals("f")){

            linea="c";
            
            while(linea.equals("c")){
                System.out.println("¿Qué ficha quieres mover?");
                linea = scFicha.nextLine();
                
                if(linea.equals("f")){
                    break;
                }

                Scanner scTrocea = new Scanner(linea);
                scTrocea.useDelimiter(",");

                x = scTrocea.nextInt();
                y = scTrocea.nextInt();

                posiciones = dm.getPosiblesPosiciones(t,x,y,null);
                System.out.println("Puedes mover esa ficha a las siguientes posiciones: ");
                System.out.println(posiciones);
                System.out.print("Escoge una: ");
                linea = scFicha.nextLine();
                System.out.println("");
                scTrocea.close();
            }
            
            Scanner scTrocea = new Scanner(linea);
            scTrocea.useDelimiter(",");
            
            t= dm.mueveJugador(t,x, y, scTrocea.nextInt(), scTrocea.nextInt());
            System.out.println(t.toStringPruebas());
            System.out.println("---------------\n");
            
            scTrocea.close();
            
            while(t.juegaNegra()){
                System.out.println("Sigue jugando. ¿A dónde quieres mover la ficha "+t.getFichaRestringida()+"?");
                posiciones = dm.getPosiblesPosiciones(t,t.getFichaRestringida().x(),t.getFichaRestringida().y(),true);
                System.out.println(posiciones);
                System.out.print("Escoge una: ");
                
                linea = scFicha.nextLine();
                System.out.println("");
                
                scTrocea = new Scanner(linea);
                scTrocea.useDelimiter(",");

                t= dm.mueveJugador(t,t.getFichaRestringida().x(), t.getFichaRestringida().y(), scTrocea.nextInt(), scTrocea.nextInt());
                System.out.println(t.toStringPruebas());
                System.out.println("---------------\n");
                
                scTrocea.close();
            }
            
            do{
                scFicha.nextLine();
                t = dm.mueveMaquina(t,'b');
                System.out.println(t.toStringPruebas());
                System.out.println("---------------\n");
                
            }while(t.juegaBlanca());

            System.out.println("Número de reinas en el tablero: "+t.getReinas()[0]+" - "+ t.getReinas()[1]);
        }
       
        scFicha.close();
    }
    
}
