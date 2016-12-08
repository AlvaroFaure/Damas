/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author inftel02
 */
public class ControladorDamas implements ActionListener{
    private Damas modelo;
    private VistaDamas vista;
    
    public ControladorDamas(VistaDamas v, Damas m){
        modelo=m;
        vista=v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch(comando){
            case "COMENZAR":
                vista.pintaTablero(new Tablero());
                break;
        }
    }
    
}
