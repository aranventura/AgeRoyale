package PresentationLayoud.Controllers;

import BusinessLayer.Entities.Maga;
import BusinessLayer.Model.GestioUser;
import BusinessLayer.Model.MagatzemPartida;
import BusinessLayer.Model.PartidaManager;
import PresentationLayoud.Views.AgeRoyaleView;
import PresentationLayoud.Views.GuardarPartidaView;
import PresentationLayoud.Views.MenuPrincipalView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase encargada de controlar el almacenamiento de partida tras finalizar una partida.
 */
public class CtrlGuardarPartida implements ActionListener {


    private static final String ERROR_NOMBRE = "El nombre es incorrecto";

    // Atributos que almacenan la vista y los modelos necesarios para que ocurran eventos.
    private final GuardarPartidaView view;
    private final AgeRoyaleView frame;
    private MagatzemPartida magatzemPartida;
    private String nombreUsuario;
    private boolean heGanado;

    /**
     * Constructor con parámetros.
     * @param view                   Vista de login
     * @param magatzemPartida        Modelo que permite el almacenamiento de partidas.
     * @param frame                  Frame principal para cambio de vista.
     */
    public CtrlGuardarPartida(GuardarPartidaView view, AgeRoyaleView frame, MagatzemPartida magatzemPartida){
        this.view = view;
        this.frame = frame;
        this.magatzemPartida = magatzemPartida;
    }


    /**
     * Se ha recibido un evento de la vista de Guardar partida.
     * @param e ActionEvent que ha ocurrido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {


            case GuardarPartidaView.BTN_GUARDAR_PARTIDA:        // Cambiamos de vista y pedimos el nombre de la partida.
                view.pedirNombrePartida();
                break;

            case GuardarPartidaView.BTN_VOLVER_MENU_PRINCIPAL:  // Volvemos al menú principal.
                frame.showMenuPrincipal();
                magatzemPartida.resetPartida();
                break;

            case GuardarPartidaView.BTN_CONFIG:                 // Mostramos la config.
                frame.showConfig();
                break;


            case GuardarPartidaView.GUARDA_PARTIDA:             // Guardamos la partida en la base de datos y volvemos al menú principal.
                if (magatzemPartida.nomPartidaCorrecte(view.getNombrePartida().getText())){                     // Comprobamos nombre de partida.
                    magatzemPartida.guardarPartida(view.getNombrePartida().getText(), nombreUsuario, heGanado);
                    magatzemPartida.resetPartida();
                    frame.showMenuPrincipal();
                } else {
                    JOptionPane frame = new JOptionPane();
                    JOptionPane.showMessageDialog(frame, ERROR_NOMBRE, "Error.",JOptionPane.INFORMATION_MESSAGE);
                }

                break;


            default:
                System.err.println("Unknown action command " + e.getActionCommand());
        }
    }

    /**
     * Método encargado de repintar la vista cuando se quiere volver a entrar a guardar partida y se repinten los paneles.
     * @param heGanado          Indicador de si ha ganado la partida y mostrar mensaje.
     * @param nom               Nombre de la partida.
     * @param magatzemPartida   Modelo que almacena la partida.
     */
    public void actualizaGuardarPartida(boolean heGanado, String nom, MagatzemPartida magatzemPartida) {
        view.repaintGuardarPartida(heGanado);
        this.nombreUsuario = nom;
        this.heGanado = heGanado;
        this.magatzemPartida = magatzemPartida;
    }
}

