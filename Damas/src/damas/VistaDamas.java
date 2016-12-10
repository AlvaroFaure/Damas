/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author inftel02
 */
public interface VistaDamas {
    
    void controlador(ActionListener ctr);
    
    void pintaTablero(Tablero t);
    String getNombre();
    Damas getModo();
    void mensaje(String str);
    void error(String err);
    String getCargar();
    String getGuardar();
    Coordenada boton(JButton b);
    void setTextArea(String s);
    void pintaPosiciones(List<Coordenada> l);
    void habilitarSiguiente(boolean b);
    void resetea();
    void resetea_mensajes();
   
}
