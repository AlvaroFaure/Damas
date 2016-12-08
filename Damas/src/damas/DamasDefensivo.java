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
public class DamasDefensivo extends Damas{
    private char modo = 'd';
    
    public Tablero mueveMaquina(Tablero t, char ficha) {
        List<Tablero> tableros = generaPosiblesTableros(t, ficha);
        List<Tablero> elegidos = new ArrayList<>();
        boolean turno;
        int idFicha;

        if (tableros.isEmpty()) {
            return null;
        } else {
            if (Character.toUpperCase(ficha) == 'B') {
                idFicha = 0;
                turno = true;
            } else {
                idFicha = 1;
                turno = false;
            }

            for (Tablero tab : tableros) {
                if (tab.getTurno() == turno) {
                    elegidos.add(tab);
                }
            }

            if (elegidos.isEmpty()) {
                for (Tablero tab : tableros) {
                    if (!puedoComer(tab, Tablero.getContraria(ficha))) {
                        elegidos.add(tab);
                    }
                }
            }

            if (elegidos.isEmpty()) {
                t.cuentaReinas();
                int numReinas = t.getReinas()[idFicha];

                for (Tablero tab : tableros) {
                    tab.cuentaReinas();
                    if (tab.getReinas()[idFicha] > numReinas) {
                        elegidos.add(tab);
                    }
                }
            }

            if (elegidos.isEmpty()) {
                elegidos.addAll(tableros);
            }

            Random rnd = new Random();
            
            return elegidos.get(rnd.nextInt(elegidos.size()));
        }
    }
 
    public char getModo(){
        return modo;
    }
}
