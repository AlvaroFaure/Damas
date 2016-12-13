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
import java.util.List;
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
 * Panel de la aplicación
 * 
 * @author Alecia Franco, Alvaro García-Faure, Amir Haddouch, Rafael Hidalgo
 */
public class PanelDamas extends JPanel implements VistaDamas {
    
    private JLabel mensajesJL;
    private JTextArea textoJTA;
    private JScrollPane textoJSP;
    private JButton[][] tablero = new JButton[8][8];
    
    private JLabel nombreJL;
    private JLabel modoJL;
    private ButtonGroup grupo;
    private JRadioButton agresivoJRB;
    private JRadioButton defensivoJRB;
    private JRadioButton aleatorioJRB;
    private JButton comenzarJB;
    private JButton cargarPartidaJB;
    private JButton guardarPartidaJB;
    private JButton cambiarTurnoJB;
    private JTextField guardarPartidaJTF;
    private JTextField cargarPartidaJTF;
    private JTextField nombreJTF;
    private JButton englishJB;
    private JButton spanishJB;
    

    public PanelDamas(){
        setLayout(new BorderLayout());
        
        //PANEL GENERAL
        
        //PANEL GENERAL: CENTRO (SECUNDARIO)
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());
        
        //PANEL SECUNDARIO: CENTRO
        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(8,8));
        
        
        int cont =0;
        Insets buttonMargin = new Insets(0,0,0,0);
        ImageIcon blanco = new ImageIcon("blanco.jpeg");
        ImageIcon negro = new ImageIcon("negro.jpeg");
        
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
        //nombreJL = new JLabel("Nombre: ", SwingConstants.CENTER);
        nombreJL = new JLabel(Internacionalizacion.getString("name_label"), SwingConstants.CENTER);
        nombreJL.setFont(new Font("Serif", Font.BOLD, 25));
        nombreJL.setOpaque(true);
        nombreJL.setBackground(new Color(209,180,140));
        nombreJTF = new JTextField();
        nombreJTF.setHorizontalAlignment(JTextField.CENTER);
        nombreJTF.setFont(new Font("Serif", Font.PLAIN, 20));
        nombreJTF.setBackground(new Color(238,228,211));
        panelOpciones.add(nombreJL);
        panelOpciones.add(nombreJTF);
        
        //Segunda fila
        JPanel terceraFila = new JPanel();
        terceraFila.setLayout(new GridLayout(3,1));
        grupo = new ButtonGroup();
        //agresivoJRB = new JRadioButton("Modo agresivo");
        agresivoJRB = new JRadioButton(Internacionalizacion.getString("attack_mode_radio_button"));
        defensivoJRB = new JRadioButton(Internacionalizacion.getString("defensive_mode_radio_button"));
        //defensivoJRB = new JRadioButton("Modo defensivo");
        //aleatorioJRB = new JRadioButton("Modo aleatorio");
        aleatorioJRB = new JRadioButton(Internacionalizacion.getString("random_mode_radio_button"));
        agresivoJRB.setHorizontalAlignment(JButton.CENTER);
        defensivoJRB.setHorizontalAlignment(JButton.CENTER);
        aleatorioJRB.setHorizontalAlignment(JButton.CENTER);
        agresivoJRB.setBackground(new Color(209,180,140));
        defensivoJRB.setBackground(new Color(209,180,140));
        aleatorioJRB.setBackground(new Color(209,180,140));
        grupo.add(agresivoJRB);
        grupo.add(defensivoJRB);
        grupo.add(aleatorioJRB);
        terceraFila.add(agresivoJRB);
        terceraFila.add(defensivoJRB);
        terceraFila.add(aleatorioJRB);
        
        //modoJL = new JLabel("   Modo de juego: ", SwingConstants.CENTER);
        modoJL = new JLabel(Internacionalizacion.getString("play_mode_label"), SwingConstants.CENTER);
        modoJL.setFont(new Font("Serif", Font.BOLD, 25));
        modoJL.setOpaque(true);
        modoJL.setBackground(new Color(209,180,140));
        /*
        vaciaJL = new JLabel();
        vaciaJL.setOpaque(true);
        vaciaJL.setBackground(new Color(209,180,140));
        panelOpciones.add(vaciaJL);
        */
        panelOpciones.add(modoJL);
        panelOpciones.add(terceraFila);

        //Cuarta fila
        //guardarPartidaJB = new JButton("Guardar partida");
        guardarPartidaJB = new JButton(Internacionalizacion.getString("save_game_button"));
        guardarPartidaJB.setFont(new Font("Verdana",Font.BOLD,15));
        guardarPartidaJB.setBackground(new Color(139,69,18));
        guardarPartidaJB.setForeground(Color.WHITE);
        guardarPartidaJTF = new JTextField();
        guardarPartidaJTF.setHorizontalAlignment(JTextField.CENTER);
        guardarPartidaJTF.setFont(new Font("Serif", Font.PLAIN, 20));
        guardarPartidaJTF.setBackground(new Color(238,228,211));
        panelOpciones.add(guardarPartidaJTF);
        panelOpciones.add(guardarPartidaJB);
        
        //Quinta fila
        //cargarPartidaJB = new JButton("Cargar partida");
        cargarPartidaJB = new JButton(Internacionalizacion.getString("charge_game_button"));
        cargarPartidaJB.setFont(new Font("Verdana",Font.BOLD,15));
        cargarPartidaJB.setBackground(new Color(139,69,18));
        cargarPartidaJB.setForeground(Color.WHITE);
        cargarPartidaJTF = new JTextField();
        cargarPartidaJTF.setHorizontalAlignment(JTextField.CENTER);
        cargarPartidaJTF.setFont(new Font("Serif", Font.PLAIN, 20));
        cargarPartidaJTF.setBackground(new Color(238,228,211));
        panelOpciones.add(cargarPartidaJTF);
        panelOpciones.add(cargarPartidaJB);
        
        //Debajo
        //cambiarTurnoJB = new JButton("Mueve máquina");
        cambiarTurnoJB = new JButton(Internacionalizacion.getString("move_machine"));
        cambiarTurnoJB.setFont(new Font("Verdana",Font.BOLD,15));
        cambiarTurnoJB.setBackground(new Color(139,69,18));
        cambiarTurnoJB.setForeground(Color.WHITE);
        panelOpciones.add(cambiarTurnoJB);
        //comenzarJB = new JButton("Comenzar");
        comenzarJB = new JButton(Internacionalizacion.getString("start_button"));
        comenzarJB.setFont(new Font("Verdana",Font.BOLD,15));
        comenzarJB.setBackground(new Color(139,69,18));
        comenzarJB.setForeground(Color.WHITE);
        panelOpciones.add(comenzarJB);
        
        panelDerecha.add(panelOpciones);
        
        // Sexta fila
        //spanishJB = new JButton ("ESPAÑOL");
        spanishJB = new JButton(Internacionalizacion.getString("spanish_button").toUpperCase());
        //englishJB = new JButton ("INGLÉS");
        englishJB = new JButton(Internacionalizacion.getString("English_button").toUpperCase());
        panelOpciones.add(spanishJB);
        panelOpciones.add(englishJB);
        
        //PANEL DERECHO ABAJO
        
        textoJTA = new JTextArea(10,20);
        textoJTA.setEditable(false);
        textoJSP = new JScrollPane(textoJTA);
        panelDerecha.add(textoJSP);
        panelCentral.add(panelDerecha,BorderLayout.EAST);
        
        add(panelCentral,BorderLayout.CENTER);
        
        //PANEL GENERAL: SUR
        mensajesJL=new JLabel(Internacionalizacion.getString("welcome_label"), SwingConstants.CENTER);
        //mensajesJL = new JLabel("Bienvenido. Elige cargar partida o comenzar una nueva", SwingConstants.CENTER);
        mensajesJL.setForeground(Color.BLUE);
        add(mensajesJL,BorderLayout.SOUTH);
    }

    public void controlador(ActionListener ctr) {
        comenzarJB.addActionListener(ctr);
        comenzarJB.setActionCommand("COMENZAR");
        cargarPartidaJB.addActionListener(ctr);
        cargarPartidaJB.setActionCommand("CARGAR");
        guardarPartidaJB.addActionListener(ctr);
        guardarPartidaJB.setActionCommand("GUARDAR");
        cambiarTurnoJB.addActionListener(ctr);
        cambiarTurnoJB.setActionCommand("SIGUIENTE");
        spanishJB.addActionListener(ctr);
        spanishJB.setActionCommand("ESPAÑOL");
        englishJB.addActionListener(ctr);
        englishJB.setActionCommand("INGLÉS");
        
        
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                tablero[i][j].addActionListener(ctr);
                tablero[i][j].setActionCommand("CASILLA");
            }
        }
        
        
    }
    
    @Override
    public void pintaTablero(Tablero t){
        ImageIcon imagen;
        
        for(int i=0; i<t.getDimension(); i++){
            for(int j=0; j<t.getDimension(); j++){
                
                if(t.getPosicion(i,j)=='X'){
                    if(t.prohibida(i, j)){
                        imagen = new ImageIcon("blanco.jpeg");
                        tablero[i][j].setIcon(imagen);
                    }else{
                        imagen = new ImageIcon("negro.jpeg");
                        tablero[i][j].setIcon(imagen);
                    }
                }else if(t.getPosicion(i,j)=='b'){
                    imagen = new ImageIcon("blancaD.jpeg");
                    tablero[i][j].setIcon(imagen);
                }else if(t.getPosicion(i,j)=='B'){
                    imagen = new ImageIcon("blancaR.jpeg");
                    tablero[i][j].setIcon(imagen);
                }else if(t.getPosicion(i,j)=='n'){
                    imagen = new ImageIcon("negraD.jpeg");
                    tablero[i][j].setIcon(imagen);
                }else{
                    imagen = new ImageIcon("negraR.jpeg");
                    tablero[i][j].setIcon(imagen);
                }
                
            }
        }
 
    }
    
    @Override
    public String getNombre(){
        return nombreJTF.getText();
    }
    
    @Override
    public Damas getModo(){
        if(agresivoJRB.isSelected()){
            return new DamasAgresivo();
        }else if(defensivoJRB.isSelected()){
            return new DamasDefensivo();
        }else{
            return new DamasAleatorio();
        }
    }
    
    @Override
    public void mensaje(String str){
        mensajesJL.setText(str);
        mensajesJL.setForeground(Color.BLUE);
    }
    
    @Override
    public void error(String err){
        mensajesJL.setText(err);
        mensajesJL.setForeground(Color.RED);
    }
    
    @Override
    public void resetea(){
        textoJTA.setText("");
    }
    
    @Override
    public String getCargar(){
        return cargarPartidaJTF.getText();
    }
    
    @Override
    public String getGuardar(){
        return guardarPartidaJTF.getText();
    }
    
    @Override
    public Coordenada boton(JButton b){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(b==tablero[i][j]){
                    return new Coordenada(i,j);
                }
            }
        }
        return null;
    }
    
    @Override
    public void setTextArea(String s){
        textoJTA.append(" "+s);
    }
    
    @Override
    public void pintaPosiciones(List<Coordenada> p){
        for(int i=0; i<p.size(); i++){
            ImageIcon imagen = new ImageIcon("negroM.jpeg");
            tablero[p.get(i).x()][p.get(i).y()].setIcon(imagen);
        }
    }
    
    @Override
    public void habilitarSiguiente(boolean b){
        cambiarTurnoJB.setEnabled(b);
    }
    
    @Override
    public void resetea_mensajes() {
        nombreJL.setText(Internacionalizacion.getString("name_label"));
        modoJL.setText(Internacionalizacion.getString("play_mode_label"));
        mensajesJL.setText(Internacionalizacion.getString("language_label"));
        mensajesJL.setForeground(Color.BLUE);
        agresivoJRB.setText(Internacionalizacion.getString("attack_mode_radio_button"));
        defensivoJRB.setText(Internacionalizacion.getString("defensive_mode_radio_button"));
        aleatorioJRB.setText(Internacionalizacion.getString("random_mode_radio_button"));
        modoJL.setText(Internacionalizacion.getString("play_mode_label"));
        guardarPartidaJB.setText(Internacionalizacion.getString("save_game_button"));
        cargarPartidaJB.setText(Internacionalizacion.getString("charge_game_button"));
        cambiarTurnoJB.setText(Internacionalizacion.getString("move_machine"));
        comenzarJB.setText(Internacionalizacion.getString("start_button"));
        spanishJB.setText(Internacionalizacion.getString("spanish_button").toUpperCase());
        englishJB.setText(Internacionalizacion.getString("English_button").toUpperCase());
    }
}
