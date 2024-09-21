package PresentationLayoud.Controllers;

import BusinessLayer.Entities.Maquina;
import BusinessLayer.Entities.User;
import BusinessLayer.Model.CtrlListenner;
import BusinessLayer.Model.GestioUser;
import BusinessLayer.Model.MagatzemPartida;
import BusinessLayer.Model.PartidaManager;
import PresentationLayoud.Views.AgeRoyaleView;
import PresentationLayoud.Views.CeldaView;
import PresentationLayoud.Views.PartidaView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase encargada de generar un panel que contiene el controlador de la partida.
 */
public class CtrlPartida extends MouseAdapter implements ActionListener, CtrlListenner {

    // Atributos que almacenan la vista y los modelos necesarios para que ocurran eventos.
    private final PartidaView view;
    private final AgeRoyaleView frame;
    private final GestioUser gestioUser;
    private PartidaManager partidaManager;
    private CtrlGuardarPartida ctrlGuardarPartida;

    /**
     * Constructor base del controlador que gestiona la partida.
     * @param view                  Vista de la partida.
     * @param frame                 Frame principal para cambio de vista.
     * @param gestioUser            Modelo de gestión de usuarios.
     * @param ctrlGuardarPartida    Controlador de guardar partida.
     */
    public CtrlPartida(PartidaView view, AgeRoyaleView frame, GestioUser gestioUser, CtrlGuardarPartida ctrlGuardarPartida) {

        this.view = view;
        this.frame = frame;
        this.gestioUser = gestioUser;
        this.ctrlGuardarPartida = ctrlGuardarPartida;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case PartidaView.BTN_PRESS: {

                JButton bot = (JButton) e.getSource();
                view.desSeleccionaCartas();

                view.setSelectedCard((JButton) e.getSource());
                view.desSeleccionaCartas();
                view.getSelectedCard().setBorderPainted(true);
            }
        }
    }



    /**
     * {@inheritDoc}
     *  Evento de mousePressed para gestionar clicks del usuario sobre una celda.
     * @param e Celda clickada por el usuario en la vista.
     */
    @Override
    public void mousePressed(MouseEvent e) {

        super.mousePressed(e);
        CeldaView celda = (CeldaView) e.getSource();        // Obtenemos celda presionada.

        // Miramos si existe una carta seleccionada, si la celda en la que ha clickado es la correcta y si tiene dinero para comprar.
        if (!celda.isOcupada() && celda.getI() > 4 && view.getSelectedCard() != null && partidaManager.getUsuari().getMoney() > 5) {

            // Reconocemos carta seleccionada y miramos si se puede añadir en el modelo y la añadomos.
            if (view.getSelectedCard().equals(view.getCardButtons().get(PartidaView.ARQUERA))) {

                esPotAfegir(PartidaManager.COST_ARQUERA, PartidaView.ARQUERA, PartidaManager.ARQUERA, celda);

            } else if (view.getSelectedCard().equals(view.getCardButtons().get(PartidaView.ESQUELETO))) {

                esPotAfegir(PartidaManager.COST_CABALLERO, PartidaView.ESQUELETO, PartidaManager.CABALLERO, celda);

            } else if (view.getSelectedCard().equals(view.getCardButtons().get(PartidaView.MAGO))) {

                esPotAfegir(PartidaManager.COST_MAGE, PartidaView.MAGO, PartidaManager.MAGA, celda);

            } else if (view.getSelectedCard().equals(view.getCardButtons().get(PartidaView.CAÑON))) {

                esPotAfegir(PartidaManager.COST_CANONERO, PartidaView.CAÑON, PartidaManager.CANONERO, celda);
            }

            celda.setOcupada(true);
            celda.revalidate();
            celda.repaint();
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

    /**
     * Método encargado de setear el partidaManager del controlador.
     * @param partidaManager    Partida manager a setear.
     */
    public void setPartidaManager(PartidaManager partidaManager) {
        this.partidaManager = partidaManager;
    }

    /**
     * Método encargado de mover la ficha de la máquina.
     * @param old_casella Casilla antigua
     * @param new_casella Casilla nueva
     */
    @Override
    public void moveFitxaController(Point old_casella, Point new_casella) {
        view.moveFicha(old_casella.y,old_casella.x, new_casella.y, new_casella.x);
    }

    /**
     * Método encargado de añadir una ficha a la máquina.
     * @param casella_x Posición X
     * @param casella_y Posición Y
     * @param fitxa Tipo de ficha que se ha añadido
     */
    @Override
    public void addFitxaController(int casella_x, int casella_y, int fitxa) {
        view.addFichaEnemy(casella_y, casella_x, fitxa);
    }

    /**
     * Método encargado de actualizar las gráficas del usuario.
     * @param u Usuario del que queremos actualizar sus valores en la vista.
     */
    @Override
    public void actualitzaGrafiquesUsuari(User u) {
        view.actualizaStatusUser(u.getNumVides(), u.getNumTropes(), u.getMoney());
    }

    /**
     * Método encargado de actualizar las gráficas de la máquina.
     * @param m Usuario máquina.
     */
    @Override
    public void actualitzaGrafiquesMaquina(Maquina m) {
        view.actualizaStatusEnemy(m.getNumVides(), m.getTropsCounter());
    }

    /**
     * Método encargado de eliminar un enemigo.
     * @param i Posición i de la casilla
     * @param j Posición j de la casilla
     */
    @Override
    public void eliminarEnemic(int i, int j) {
        view.eliminaFicha(i, j);
    }

    /**
     * Método encargado de avisar de que una partida ha finalizado. Actualiza el estado del CtrlGuardarPartida y cambia de vista.
     * @param user              Usuario que está jugando.
     * @param nom               Nombre del usuario.
     * @param magatzemPartida   modelo de almacenar partida.
     */
    @Override
    public void partidaAcabada(boolean user, String nom, MagatzemPartida magatzemPartida) {
        ctrlGuardarPartida.actualizaGuardarPartida(user, nom, magatzemPartida);
        frame.showGuardarPartida();
    }

    /**
     * Método encargado de repintar la vista de la partida.
     */
    public void reiniciaVistaPartida() {
        view.repintaPartida();
    }
}