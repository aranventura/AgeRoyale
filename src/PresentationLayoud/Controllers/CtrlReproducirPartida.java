package PresentationLayoud.Controllers;

import BusinessLayer.Entities.Maquina;
import BusinessLayer.Entities.Moviment;
import BusinessLayer.Entities.User;
import BusinessLayer.Model.CtrlListenner;
import BusinessLayer.Model.GestioUser;
import BusinessLayer.Model.MagatzemPartida;
import BusinessLayer.Model.PartidaManager;
import PresentationLayoud.Views.AgeRoyaleView;
import PresentationLayoud.Views.CeldaView;
import PresentationLayoud.Views.PartidaRepetidaView;
import PresentationLayoud.Views.PartidaView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Clase encargada de controlar la reproducción de la partida. SIN TERMINAR.
 */
public class CtrlReproducirPartida implements ActionListener, CtrlListenner {

    // Atributos que almacenan la vista y los modelos necesarios para que ocurran eventos.
    private final PartidaRepetidaView view;
    private final AgeRoyaleView frame;
    private PartidaManager partidaManager;


    /**
     * Constructor base del controlador de partida.
     * @param view  Vista de la partida.
     * @param frame Frame principal para cambiar de pantalla.
     */
    public CtrlReproducirPartida(PartidaRepetidaView view, AgeRoyaleView frame) {
        this.view = view;
        this.frame = frame;
    }

    /**
     * Se ha recibido un evento de la vista de Register.
     * @param e ActionEvent que ha ocurrido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

            switch(e.getActionCommand()) {

                case PartidaRepetidaView.BTN_PRESS:
                    break;

                case PartidaRepetidaView.BTN_CONFIG:
                    frame.showConfig();
                    break;

            }
    }


    /**
     * Método encargado de mirara si la ficha se puede añadir.
     * @param tipusCost             Coste de la tropa.
     * @param tipusView             Tipos vista para poderlo añadir en la vista.
     * @param tipusPartidaManager   Tipo de tropa.
     * @param celda                 Celda seleccionada.
     */
    public void esPotAfegir(int tipusCost, int tipusView, int tipusPartidaManager, CeldaView celda){
        if(partidaManager.getUsuari().getMoney() >= tipusCost && partidaManager.getTaulell().getTaulell()[celda.getI()][celda.getJ()].getFitxa() == null){
            partidaManager.getUsuari().setMoney(partidaManager.getUsuari().getMoney() - tipusCost);
            partidaManager.addFitxa(tipusPartidaManager,celda.getJ(),celda.getI(),true);
            view.addFicha(celda,tipusView);
        }
    }


    public void setPartidaManager(ArrayList<Moviment> movimentsPartida, GestioUser gestioUser) {
        this.partidaManager = new PartidaManager(movimentsPartida, gestioUser, this);
    }


    @Override
    public void moveFitxaController(Point old_casella, Point new_casella) {
        view.moveFicha(old_casella.y,old_casella.x, new_casella.y, new_casella.x);
    }

    @Override
    public void addFitxaController(int casella_x, int casella_y, int fitxa) {
        view.addFichaEnemy(casella_y, casella_x, fitxa);
    }

    @Override
    public void actualitzaGrafiquesUsuari(User u) {
        view.actualizaStatusUser(u.getNumVides(), u.getNumTropes(), u.getMoney());
    }

    @Override
    public void actualitzaGrafiquesMaquina(Maquina m) {
        view.actualizaStatusEnemy(m.getNumVides(), m.getTropsCounter());
    }

    @Override
    public void eliminarEnemic(int i, int j) {
        view.eliminaFicha(i, j);
    }

    @Override
    public void partidaAcabada(boolean user, String nom, MagatzemPartida magatzemPartida) {
        frame.showMenuPrincipal();
    }

}
