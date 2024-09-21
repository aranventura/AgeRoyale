package BusinessLayer.Model;

import BusinessLayer.Entities.Fitxa;
import BusinessLayer.Entities.Maquina;
import BusinessLayer.Entities.User;

import java.awt.*;
import java.util.ArrayList;


/**
 * Clase que conecta el modelo con los controllers para poder notificar a las vista de algun cambio en el modelo que afecte a la vista.
 *
 *
 */
public interface CtrlListenner {

    /**
     * Método que notifica del movimiento de una ficha.
     * @param old_casella Casilla antigua
     * @param new_casella Casilla nueva
     */
    void moveFitxaController(Point old_casella, Point new_casella);

    /**
     * Método que notifica a la vista que se ha añadido una ficha en el tablero
     * @param casella_x
     * @param casella_y
     * @param fitxa Tipo de ficha que se ha añadido
     */
    void addFitxaController(int casella_x, int casella_y, int fitxa);

    /**
     * Método que notifica a la vista segun los canvios del usuario
     * @param u
     */
    void actualitzaGrafiquesUsuari(User u);

    /**
     * Notificar a la vista de que los valores de la maquna han cambiado
     * @param m Maquina
     */
    void actualitzaGrafiquesMaquina(Maquina m);

    /**
     * Notifica a la view que una ficha ha sido eliminada
     * @param i Pos y de la ficha
     * @param j Pos x de la ficha
     */
    void eliminarEnemic(int i, int j);

    /**
     * Notifica la vista de que la partida ha finalizado
     * @param user
     * @param nom
     * @param magatzemPartida
     */
    void partidaAcabada(boolean user, String nom, MagatzemPartida magatzemPartida);


}
