package PresentationLayoud.Controllers;

import BusinessLayer.Entities.Moviment;
import BusinessLayer.Entities.Partida;
import BusinessLayer.Model.GestioUser;
import BusinessLayer.Model.MagatzemPartida;
import PresentationLayoud.Views.AgeRoyaleView;
import PresentationLayoud.Views.SavedGamePanel;
import PresentationLayoud.Views.SavedGamesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase encargada de generar un panel que contiene el controlador de las partidas guardadas.
 */
public class CtrlPartidasGuardadas extends MouseAdapter implements ActionListener {

    // Atributos que almacenan la vista y los modelos necesarios para que ocurran eventos.
    private final SavedGamesView view;
    private final AgeRoyaleView frame;
    private GestioUser gestioUser;
    private final MagatzemPartida magatzemPartida;
    private String nameUser;
    private CtrlReproducirPartida ctrlReproducirPartida;

    /**
     * Constructor base del controlador que gestiona las partidas guardadas.
     * @param view                  Vista de las partidas guardadas.
     * @param frame                 Frame principal para cambiar de vista.
     * @param gestioUser            Modelo de gestión de usuario.
     * @param magatzemPartida       Modelo de almacén de partidas.
     * @param ctrlReproducirPartida Controlador de reproducir una partida.
     */
    public CtrlPartidasGuardadas(SavedGamesView view, AgeRoyaleView frame, GestioUser gestioUser, MagatzemPartida magatzemPartida, CtrlReproducirPartida ctrlReproducirPartida){

        this.view = view;
        this.frame = frame;
        this.gestioUser = gestioUser;
        this.magatzemPartida = magatzemPartida;
        this.ctrlReproducirPartida = ctrlReproducirPartida;
    }

    /**
     * Se ha recibido un evento de la vista de Guardar partida.
     * @param e ActionEvent que ha ocurrido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case SavedGamesView.BTN_CONFIG:     // Botón de configuración.
                frame.showConfig();             // Cambio de vista configuración.
                break;

            case SavedGamesView.BTN_ATRAS:      // Botón de ir atrás.
                frame.showMenuPrincipal();      // Cambio de vista menú principal.
                break;

            default:
                System.err.println("Unknown action command " + e.getActionCommand());
        }
    }

    /**
     * Método encargado de gestoinar el ratón presoinado en la vista de partidas guardadas.
     * @param e Panel de partida en el que se ha realizado la acción.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        SavedGamePanel partida = (SavedGamePanel) e.getSource();                            // Obtenemos panel de partida presionada.
        ArrayList<Partida> partidas = magatzemPartida.getPartidesJugador(nameUser);         // Obtenemos lista de partidas del jugador.

        // Partida que ha clickado.
        ArrayList<Moviment> movimentsPartida = magatzemPartida.readMovimentsPartida(partidas.get(partida.getSavedGameIndex()).getNomPartida());

        frame.showPartidaRepetida();                                                        // Mostramos partida a reproducir.
        this.ctrlReproducirPartida.setPartidaManager(movimentsPartida, this.gestioUser);    // Reproducimos partida.

    }

    /**
     * Método encargado de listar las partidas de usuario.
     * @param partidas   Lista de partidas del usuario.
     */
    public void listaPartidasUsuario(ArrayList<Partida> partidas) {

        partidas.sort(Comparator.comparing(Partida::getDate));   // Ordena las partidas por fecha.
        view.configurarCentroGrid(partidas, this);      // Actualizar vista de del listado de partidas.
    }

    /**
     * Método encargado de setear un nombre de usuario para saber el listado de partidas que tenemos que mostrar.
     * @param nameUser Nombre del usuario.
     */
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}


