package PresentationLayoud.Controllers;

import BusinessLayer.Model.GestioUser;
import BusinessLayer.Model.MagatzemPartida;
import BusinessLayer.Model.PartidaManager;
import PresentationLayoud.Views.AgeRoyaleView;
import PresentationLayoud.Views.MenuPrincipalView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase encargada de controlar la gestión del menú principal de la partida.
 */
public class CtrlMenuPrincipal implements ActionListener {

    // Atributos que almacenan la vista y los modelos necesarios para que ocurran eventos.
    private final MenuPrincipalView view;
    private final CtrlGuardarPartida ctrlGuardarPartida;
    private final AgeRoyaleView frame;
    private final CtrlPartida ctrlPartida;
    private final CtrlPartidasGuardadas savedGamesController;
    private final GestioUser gestioUser;
    private final CtrlRanking ctrlRanking;
    private final MagatzemPartida magatzemPartida;

    /**
     * Constructor general con parámetros.
     * @param view                  Vista del menú principal.
     * @param frame                 Frame de la partida para poder cambiar de carta.
     * @param ctrlGuardarPartida    Controlador de guardar partida.
     * @param ctrlPartida           Controlador de partida.
     * @param savedGamesController  Controlador de partidas guardadas.
     * @param ctrlRanking           Controlador de ranking.
     * @param gestioUser            Modelo que gestiona los usuarios.
     * @param magatzemPartida       Modelo que gestiona el almacenamiento de partidas.
     */
    public CtrlMenuPrincipal(MenuPrincipalView view, AgeRoyaleView frame, CtrlGuardarPartida ctrlGuardarPartida, CtrlPartida ctrlPartida, CtrlPartidasGuardadas savedGamesController, CtrlRanking ctrlRanking, GestioUser gestioUser, MagatzemPartida magatzemPartida){
        this.view = view;
        this.ctrlGuardarPartida = ctrlGuardarPartida;
        this.frame = frame;
        this.ctrlPartida = ctrlPartida;
        this.savedGamesController = savedGamesController;
        this.gestioUser = gestioUser;
        this.magatzemPartida = magatzemPartida;
        this.ctrlRanking = ctrlRanking;
    }


    /**
     * Se ha recibido un evento de la vista de Menú principal.
     * @param e ActionEvent que ha ocurrido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case MenuPrincipalView.BTN_RANKING:     // Mostrar ranking.
                ctrlRanking.actualizaRanking();
                frame.showRanking();
                break;

            case MenuPrincipalView.BTN_NEW_GAME:    // Mostrar nueva partida.

                ctrlPartida.reiniciaVistaPartida();
                frame.showPartida();                                                        // Mostramos partida.
                gestioUser.afegirPartidaJugada();                                           // Añadimos una partida jugada al usuario.
                ctrlPartida.setPartidaManager(new PartidaManager(gestioUser, ctrlPartida)); // Seteamos controlador de la partida.
                break;

            case MenuPrincipalView.BTN_SAVED_GAME:      // Partidas guardadas.
                savedGamesController.listaPartidasUsuario(magatzemPartida.getPartidesJugador(gestioUser.getUsuari().getNom()));        // Actualizamos partidas del usuario a nivel de vista.
                savedGamesController.setNameUser(gestioUser.getUsuari().getNom());                     // Seteamos el nombre del usuario que se está mostrando el listado de partidas.
                frame.showPartidasGuardadas();  // Mostramos partidas guardadas del usuario.           // Mostramos vista de partidas guardadas.
                break;

            case MenuPrincipalView.BTN_CONFIG:      // Configuración de la cuenta.
                frame.showConfig();                 // Mostramos configuración de la partida.
            break;

            default:
                System.err.println("Unknown action command " + e.getActionCommand());
        }
    }
}

