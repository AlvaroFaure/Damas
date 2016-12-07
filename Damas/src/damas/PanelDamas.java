/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author inftel02
 */
public class PanelDamas extends JPanel implements VistaDamas {
    
    private JLabel mensajesJL;
    private JTextArea textoJTA;
    private JScrollPane textoJSP;
    private JButton[][] tablero = new JButton[8][8];
    
    private JLabel nombreJL;
    private JTextField nombreJTF;
    private JLabel modoJL;
    private ButtonGroup grupo;
    private JRadioButton agresivoJRB;
    private JRadioButton defensivoJRB;
    private JButton comenzarJB;
    private JButton cargarPartidaJB;
    private JTextField cargarPartidaJTF;
    private JButton guardarPartidaJB;
    private JTextField guardarPartidaJTF;
    private JButton cambiarTurnoJB;
    private JLabel vaciaJL;
    
    public PanelDamas(){
        setLayout(new BorderLayout());
        
        //PANEL GENERAL
        
        //PANEL GENERAL: CENTRO (SECUNDARIO)
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        
        //PANEL SECUNDARIO: CENTRO
        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(8,8));
        Insets buttonMargin = new Insets(0,0,0,0);
        ImageIcon negro = new ImageIcon("negro.jpeg");
        ImageIcon blanco = new ImageIcon("blanco.jpeg");
        int cont =0;
        
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(cont%2==0){
                    tablero[i][j] = new JButton(blanco);
                    tablero[i][j].setMargin(buttonMargin);
                }else{
                    tablero[i][j] = new JButton(negro);
                    tablero[i][j].setMargin(buttonMargin);
                }
                cont++;
                panelTablero.add(tablero[i][j]);
            }
            cont++;
        }
        panelCentral.add(panelTablero,BorderLayout.CENTER);
        
        //PANEL SECUNDARIO: DERECHA
        JPanel panelDerecha = new JPanel();
        panelDerecha.setLayout(new GridLayout(2,1));
        JPanel panelOpciones = new JPanel();
        panelOpciones.setLayout(new GridLayout(6,2));
        
        //Primera fila
        nombreJL = new JLabel("Nombre: ", SwingConstants.CENTER);
        nombreJL.setFont(new Font("Serif", Font.BOLD, 30));
        nombreJTF = new JTextField();
        panelOpciones.add(nombreJL);
        panelOpciones.add(nombreJTF);
        
        //Segunda fila
        modoJL = new JLabel("   Modo de juego: ", SwingConstants.CENTER);
        modoJL.setFont(new Font("Serif", Font.BOLD, 20));
        vaciaJL = new JLabel();
        panelOpciones.add(modoJL);
        panelOpciones.add(vaciaJL);
        
        //Tercera fila y cuarta fila
        JPanel terceraFila = new JPanel();
        terceraFila.setLayout(new GridLayout(2,1));
        grupo = new ButtonGroup();
        agresivoJRB = new JRadioButton("Modo agresivo");
        defensivoJRB = new JRadioButton("Modo defensivo");
        grupo.add(agresivoJRB);
        grupo.add(defensivoJRB);
        terceraFila.add(agresivoJRB);
        terceraFila.add(defensivoJRB);
        panelOpciones.add(terceraFila);
        comenzarJB = new JButton("Comenzar");
        panelOpciones.add(comenzarJB);
        
        //Cuarta fila
        guardarPartidaJB = new JButton("Guardar partida");
        guardarPartidaJTF = new JTextField();
        panelOpciones.add(guardarPartidaJTF);
        panelOpciones.add(guardarPartidaJB);
        
        //Quinta fila
        cargarPartidaJB = new JButton("Cargar partida");
        cargarPartidaJTF = new JTextField();
        panelOpciones.add(cargarPartidaJTF);
        panelOpciones.add(cargarPartidaJB);
        
        //Debajo
        cambiarTurnoJB = new JButton("Mueve máquina");
        vaciaJL = new JLabel();
        panelOpciones.add(cambiarTurnoJB);
        panelOpciones.add(vaciaJL);
        
        panelDerecha.add(panelOpciones);
        
        //PANEL DERECHO ABAJO
        
        textoJTA = new JTextArea(10,20);
        textoJTA.setEditable(false);
        textoJSP = new JScrollPane(textoJTA);
        panelDerecha.add(textoJSP);
        panelCentral.add(panelDerecha,BorderLayout.EAST);
        
        add(panelCentral,BorderLayout.CENTER);
        
        //PANEL GENERAL: SUR
        mensajesJL = new JLabel("Bienvenido. Elige cargar partida o comenzar una nueva", SwingConstants.CENTER);
        mensajesJL.setForeground(Color.BLUE);
        add(mensajesJL,BorderLayout.SOUTH);
    }

    public void controlador(ActionListener ctr) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
