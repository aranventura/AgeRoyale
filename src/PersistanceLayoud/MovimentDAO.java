package PersistanceLayoud;

import BusinessLayer.Entities.Moviment;
import BusinessLayer.Entities.Partida;

import java.util.ArrayList;

public interface MovimentDAO {
    /**
     * Metodo que añade un nuevo movimiento a la bdd
     * @param moviment Movimiento que queremos guardar
     */
    void addMovimient(Moviment moviment);

    /**
     * Metodo que lee todos los movimientos de una partida de la bdd
     * @return ArrayList de todos los movimientos
     */
    ArrayList<Moviment> readAllMovimentsPartida(String nomPartida);

    void updateMoviment(int identificador, Moviment moviment_upd);

    /**
     * Metodo que elimina todos los movimientos de una partida en la bdd
     * @param nomPartida nombre de la partida de la que queremos eliminar los movimientos
     */
    void deleteMovimentsPartida(String nomPartida);

}
