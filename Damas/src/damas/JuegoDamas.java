/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author inftel02
 */
public class JuegoDamas{

    public static void main(String[] args) {
        Damas modelo = new DamasAgresivo();
        VistaDamas vista = new PanelDamas();
        ActionListener ctr = new ControladorDamas(vista, modelo);
        vista.controlador(ctr);

        JFrame ventana = new JFrame("Damas Inftel");
        ventana.setSize(1000, 700);
        ventana.setResizable(false);

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setContentPane((JPanel) vista);
        ventana.setVisible(true);
    }
}
