/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.util.List;
import java.util.Scanner;

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
        
        Tablero t = new Tablero(tab,false);
        System.out.println(t.toStringPruebas());
        System.out.println("---------------\n");

        Scanner sc = new Scanner(System.in);
        String linea = "";
        
        int x;
        int y;
        
        System.out.println("¿Qué ficha quieres mover?");
        linea = sc.nextLine();
        
        do{
            do{

                Scanner sc2 = new Scanner(linea);
                sc2.useDelimiter(",");

                x = sc2.nextInt();
                y = sc2.nextInt();
                List<Coordenada> p = Damas.getPosiblesPosiciones(t,x,y,null);

                System.out.println("Puedes mover esa ficha a las siguientes posiciones: ");
                System.out.println(p);
                System.out.print("Escoge una: ");

                linea = sc.nextLine();
            }while(linea.equals("c"));
            
            Scanner sc2 = new Scanner(linea);
            sc2.useDelimiter(",");
            
            t= Damas.mueveJugador(t,x, y, sc2.nextInt(), sc2.nextInt());
            System.out.println(t.toStringPruebas());
            System.out.println("---------------\n");
            
            do{
                t = Damas.mueveMaquina(t,'b');
                System.out.println(t.toStringPruebas());
                System.out.println("---------------\n");
            }while(t.juegaBlanca());
            
            sc2.close();
            
            
            System.out.println("¿Qué ficha quieres mover?");
            linea = sc.nextLine();
        }while(!linea.equals("f"));
        
        sc.close();
    }     
}
