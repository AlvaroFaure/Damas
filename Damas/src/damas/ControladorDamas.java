/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;

/**
 *
 * @author inftel02
 */
public class ControladorDamas implements ActionListener {

    private Damas modelo;
    private VistaDamas vista;
    private String nombreUsuario;
    private String ficheroCargar;
    private String ficheroGuardar;
    private boolean ficha;
    private boolean posicion;
    private boolean terminada;
    private List<Coordenada> p;
    private Coordenada cficha;
    private Coordenada cdestino;
    Locale locale;

    public ControladorDamas(VistaDamas v, Damas m) {
        modelo = m;
        vista = v;
        ficha = false;
        terminada = false;
        posicion = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "COMENZAR":
                reinicia();
                nombreUsuario = vista.getNombre();
                modelo = vista.getModo();
                posicion = false;
                terminada = false;
                p = null;

                if (modelo == null || nombreUsuario == null || nombreUsuario.equals("")) {
                    vista.error("Introduce un nombre y selecciona un modo para comenzar a jugar");
                } else {
                    //Trabajar con el nombre
                    vista.mensaje("Hola, " + nombreUsuario + ". Empieza a jugar eligiendo ficha para mover");
                    Tablero t = new Tablero();
                    vista.pintaTablero(t);
                    modelo.setTablero(t);
                    ficha = true;
                }
                break;
            case "CARGAR":
                ficheroCargar = vista.getCargar();
                if (ficheroCargar == null || ficheroCargar.equals("")) {
                    vista.error("Escribe el nombre del fichero que quieres cargar");
                } else {
                    try {
                        Tablero t = new Tablero(ficheroCargar);
                        reinicia();
                        vista.pintaTablero(t);
                        modelo = t.getModo();
                        modelo.setTablero(t);
                        nombreUsuario = t.getNombre();
                        if (t.juegaNegra()) {
                            vista.mensaje("Es tu turno. Selecciona una ficha para mover");
                            ficha = true;
                            posicion = false;
                        } else {
                            ficha = false;
                            posicion = false;
                            vista.habilitarSiguiente(true);
                            vista.mensaje("Es su turno, pulsa el botón para que tu contrincante haga su movimiento");
                        }
                    } catch (FileNotFoundException ex) {
                        vista.error("El fichero que quieres cargar no se encuentra");
                    }

                }
                break;
            case "GUARDAR":
                ficheroGuardar = vista.getGuardar();
                if (ficheroGuardar == null || ficheroGuardar.equals("")) {
                    vista.error("Escribe el nombre del fichero que quieres guardar");
                } else {
                    try {
                        modelo.getTablero().guardaTablero(ficheroGuardar, nombreUsuario, modelo);
                    } catch (FileNotFoundException ex) {
                        vista.error("El fichero que quieres cargar no se encuentra");
                    }
                }

                break;
            case "CASILLA":
                if (terminada) {
                    vista.mensaje("La partida ha terminado. Carga una partida o empieza una nueva");
                } else if (modelo.getTablero().juegaBlanca()) {
                    vista.mensaje("Le toca jugar a tu contrincante, pulsa el botón para que haga su movimiento");
                } else if (!ficha && !posicion) {
                    vista.mensaje("No puedes jugar hasta que no cargues una partida o empieces una nueva");
                } else if (posicion) {
                    cdestino = vista.boton((JButton) e.getSource());
                    if (p.contains(cdestino)) {
                        modelo.mueveJugador(modelo.getTablero(), cficha.x(), cficha.y(), cdestino.x(), cdestino.y());
                        vista.pintaTablero(modelo.getTablero());
                        vista.setTextArea("Has movido tu ficha de la posición " + cficha + " a " + cdestino + "\n");
                        if (modelo.getTablero().juegaNegra()) {
                            cficha = modelo.getTablero().getFichaRestringida();
                            p = modelo.getPosiblesPosiciones(modelo.getTablero(), cficha.x(), cficha.y(), null);
                            vista.mensaje("Puedes seguir comiendo. Escoge dónde quieres moverte con esa ficha");
                            vista.pintaPosiciones(p);
                            ficha = false;
                            vista.habilitarSiguiente(false);
                        } else {
                            vista.mensaje("Ha terminado tu turno. Pulsa el botón para que tu contrincante haga su movimiento");
                            vista.habilitarSiguiente(true);
                            posicion = false;
                            ficha = false;
                        }
                    } else if (Character.toUpperCase(modelo.getTablero().getPosicion(cdestino.x(), cdestino.y())) == 'N') {
                        vista.pintaTablero(modelo.getTablero());
                        cficha = new Coordenada(cdestino.x(), cdestino.y());
                        p = modelo.getPosiblesPosiciones(modelo.getTablero(), cficha.x(), cficha.y(), null);
                        vista.mensaje("Escoge una posición a la que mover la ficha seleccionada (" + cficha.x() + "," + cficha.y() + ")");
                        vista.pintaPosiciones(p);
                        vista.habilitarSiguiente(false);
                    } else {
                        vista.mensaje("Escoge una casilla válida");
                        vista.habilitarSiguiente(false);
                    }
                } else {
                    vista.pintaTablero(modelo.getTablero());
                    cficha = vista.boton((JButton) e.getSource());
                    char f = modelo.getTablero().getPosicion(cficha.x(), cficha.y());
                    if (f == 'N' || f == 'n') {
                        p = modelo.getPosiblesPosiciones(modelo.getTablero(), cficha.x(), cficha.y(), null);
                        vista.mensaje("Escoge una posición a la que mover la ficha seleccionada (" + cficha.x() + "," + cficha.y() + ")");
                        vista.pintaPosiciones(p);
                        posicion = true;
                    } else {
                        vista.mensaje("Tienes que seleccionar una ficha tuya para moverla");
                    }
                    vista.habilitarSiguiente(false);
                }
                break;
            case "SIGUIENTE":
                if (modelo.getTablero().juegaBlanca()) {
                    Tablero t = modelo.mueveMaquina(modelo.getTablero(), 'b');
                    if (t == null) {
                        termina();
                    } else {
                        modelo.setTablero(t);
                        vista.setTextArea("Tu contrincante a respondido a tu movimiento\n");
                        vista.pintaTablero(modelo.getTablero());
                        if (!modelo.hayMovimientos(modelo.getTablero(), 'n')) {
                            termina();
                        } else {
                            vista.mensaje("Es tu turno. Selecciona una ficha para mover");
                            if (modelo.getTablero().juegaNegra()) {
                                vista.habilitarSiguiente(false);
                            }
                            vista.pintaTablero(modelo.getTablero());
                            ficha = true;
                        }
                    }

                } else {
                    vista.mensaje("Es tu turno, hasta que no muevas no puede jugar tu contrincante.");
                }
                break;
            case "ESPAÑOL":
                locale = new Locale("es");
                Internacionalizacion.setMessagesFile(locale);
                vista.resetea_mensajes();
                break;
            case "INGLÉS":
                locale = new Locale("en", "US");
                Internacionalizacion.setMessagesFile(locale);
                vista.resetea_mensajes();
                break;
        }
    }

    public void reinicia() {
        terminada = false;
        posicion = false;
        ficha = false;
        p = null;
        cficha = null;
        cdestino = null;
        modelo = null;
        ficheroCargar = null;
        ficheroGuardar = null;
        vista.habilitarSiguiente(false);
        vista.resetea();
    }

    public void termina() {
        terminada = true;
        ficha = false;
        posicion = false;
        vista.habilitarSiguiente(false);

        int[] puntos = modelo.getTablero().cuentaFichas();
        String resultado;

        //puntos[0] = CPU / puntos[1]=Nosotros
        if (puntos[1] > puntos[0]) {
            vista.setTextArea("\n¡Enhorabuena! ¡Has ganado la partida!\n");
            resultado = "VICTORIA";
        } else if (puntos[1] == puntos[0]) {
            vista.setTextArea("\n¡La partida ha terminado en empate!\n");
            resultado = "EMPATE";
        } else {
            vista.setTextArea("\n¡Oh! ¡Vaya lástima! ¡Has perdido la partida!\n");
            resultado = "DERROTA";
        }
        vista.mensaje("No puedes realizar movimientos. La partida ha terminado. Carga una o empieza una nueva");
        vista.setTextArea("\n  - ESTADÍSTICAS -\n\n");

        try {
            modelo.BBDD(nombreUsuario, modelo.getTablero().getMovimientos(), modelo.getTablero().getReinasTotales(), resultado);
            vista.setTextArea(modelo.mostrarEstadisticas(nombreUsuario));
        } catch (SQLException | ClassNotFoundException ex) {
            vista.error("ERROR: " + ex.getMessage());
        }
    }
}
