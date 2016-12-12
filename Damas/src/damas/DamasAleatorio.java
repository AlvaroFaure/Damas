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
 * clase que implementa el juego de la maquina en el que los movimientos son aleatorios,
 * salvo que tenga que comer ficha que es obligatorio, 
 *
 * @author Alecia Franco, Alvaro Garcia, Amir Haddouch, Rafael Hidalgo
 */
public class DamasAleatorio extends Damas{
    private char modo = 'r';
    
    /**
     * crea una instacia de Tablero recibiendo como parametros un tablero y el caracter de la ficha
     * @param t contiene un tablero 
     * @param ficha contiene el carácter de la ficha
     */
    public Tablero mueveMaquina(Tablero t, char ficha) {
        List<Tablero> tableros = generaPosiblesTableros(t, ficha);
        Random rnd = new Random();

        if (tableros.isEmpty()) {
            return null;
        } else {
            return tableros.get(rnd.nextInt(tableros.size()));
        }
    }

    /**
     *@return devuelve el modo de juego
     */
    public char getModo(){
        return modo;
    }
}
