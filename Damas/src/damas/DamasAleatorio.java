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
 * @author Álvaro
 */
public class DamasAleatorio extends Damas{
    private char modo = 'r';
    
    public Tablero mueveMaquina(Tablero t, char ficha) {
        List<Tablero> tableros = generaPosiblesTableros(t, ficha);
        Random rnd = new Random();

        if (tableros.isEmpty()) {
            return null;
        } else {
            return tableros.get(rnd.nextInt(tableros.size()));
        }
    }

    
    public char getModo(){
        return modo;
    }
}
