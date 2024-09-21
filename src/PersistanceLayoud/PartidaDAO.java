package PersistanceLayoud;

import BusinessLayer.Entities.Partida;

import java.util.ArrayList;
import java.util.LinkedList;

public interface PartidaDAO {

    /**
     * Metodo que añade una  ueva partida a la bdd
     * @param partida Partida que queremos guardar
     */
    void addPartida(Partida partida);

    /**
     * Metodo que lee una partida de la bdd
     * @param identificador que representa el nombre de la partida que queremos obtener
     * @return La Partida que se corresponde con el identificador
     */
    Partida readPartida(String identificador);

    void updatePartida(String identificador, Partida partida_upd);

    /**
     * Metodo que borra la partida de un usuario a partir del nombre dado a la partida
     * @param nom_partida Nombre de la partida
     */
    void deletePartidaPerNom(String nom_partida);

    /**
     * Metodo que borra todas las partidasa que tengan com jugador al usuari
     * @param usuari Nombre del usuario
     */
    void deletePartidaPerUsuari(String usuari);


    /**
     * Metodo que recoje todas las partidas que el usuario ha guardado
     * @param user_name Nombre del usuario
     * @return ArrayList de las partidas del usuario
     */
    ArrayList<Partida> getPartidesUsuari(String user_name);

    /**
     * Metodo que recoje todas las partidas de los otros jugadores que no son el usuario
     * @param user_name Nombre del usuario
     * @return ArrayList de las partidas de los otros jugadores
     */
    ArrayList<Partida> getPartides(String user_name);
}
