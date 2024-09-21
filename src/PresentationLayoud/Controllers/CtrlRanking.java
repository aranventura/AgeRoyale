package PresentationLayoud.Controllers;

import BusinessLayer.Entities.User;
import BusinessLayer.Model.MagatzemPartida;
import PresentationLayoud.Views.AgeRoyaleView;
import PresentationLayoud.Views.RankingView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase encargada de controlar el ranking de la partida.
 */
public class CtrlRanking implements ActionListener, ListSelectionListener {

    // Atributos que almacenan la vista y los modelos necesarios para que ocurran eventos.
    private final RankingView view;
    private final AgeRoyaleView frame;
    private final CtrlPartidasGuardadas savedGamesController;
    private final MagatzemPartida magatzemPartida;

    /**
     * Constructor base con parámetros
     * @param view                  Vista a mostrar.
     * @param frame                 Frame principal a mostrar.
     * @param savedGamesController  Ctrl de partidas guardadas.
     * @param magatzemPartida       Modelo de almacenamiento de partida.
     */
    public CtrlRanking(RankingView view, AgeRoyaleView frame, CtrlPartidasGuardadas savedGamesController, MagatzemPartida magatzemPartida){
        this.view = view;
        this.frame = frame;
        this.savedGamesController = savedGamesController;
        this.magatzemPartida = magatzemPartida;
    }


    /**
     * Se ha recibido un evento de la vista de Ranking
     * @param e ActionEvent que ha ocurrido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case RankingView.BTN_ATRAS:
                frame.showMenuPrincipal();
                break;

            case RankingView.BTN_CONFIG:
                frame.showConfig(); // Vamos a config
                break;


            default:
                System.err.println("Unknown action command " + e.getActionCommand());
        }
    }

    /**
     * Método encargado de actualizar el ranking de la partida de la vista.
     */
    public void actualizaRanking() {

        ArrayList<User> usuarios = this.magatzemPartida.getDadesRanking();                   // Conseguimos lista de usuarios.
        for (int i = 0; i < usuarios.size(); i++) {
            usuarios.get(i).setpGuanyades(i);
            usuarios.get(i).setNumPartides(i+1);
        }
        usuarios.sort(Comparator.comparing(User::getWinRatio).reversed());                  // Ordenamos lista de usuarios por su Win Ratio DESC

        view.configurarTabla(this,usuarios);      // Actualizar vista.
    }

    /**
     * Listener cuando clickamos sobre una fila del ranking.
     * Se encarga de mostrar el ranking y de actualizar la vista.
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {

        // Conseguimos usuario presionado para poder mostrar su lista de partidas guardadas.
        savedGamesController.listaPartidasUsuario(magatzemPartida.getPartidesJugador(view.getTable().getValueAt(view.getTable().getSelectedRow(), 0).toString()));
        savedGamesController.setNameUser(view.getTable().getValueAt(view.getTable().getSelectedRow(), 0).toString());
        frame.showPartidasGuardadas();
    }
}

