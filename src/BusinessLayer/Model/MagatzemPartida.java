package BusinessLayer.Model;

import BusinessLayer.Entities.Moviment;
import BusinessLayer.Entities.Partida;
import BusinessLayer.Entities.User;
import PersistanceLayoud.MovimentDAO;
import PersistanceLayoud.PartidaDAO;
import PersistanceLayoud.UsuariDAO;
import PersistanceLayoud.sql.SQLMovimentDAO;
import PersistanceLayoud.sql.SQLPartidaDAO;
import PersistanceLayoud.sql.SQLUsuariDAO;

import java.util.ArrayList;

/**
 * Clase que usa los metodos implementados en {@link SQLPartidaDAO} {@link SQLMovimentDAO} i {@link SQLUsuariDAO}
 *
 * Se encarga de gestionar los datos de las partidas
 */
public class MagatzemPartida {
    private final PartidaDAO partidaDAO;
    private final MovimentDAO movimentDAO;
    private final UsuariDAO usuariDAO;
    private final ArrayList<Moviment> partida = new ArrayList<>();
    private int id_moviment;

    /**
     * Constructor per MagatzemPartida
     */
    public MagatzemPartida(){
        partidaDAO = new SQLPartidaDAO();
        movimentDAO = new SQLMovimentDAO();
        usuariDAO = new SQLUsuariDAO();
        id_moviment = 0;
    }

    /**
     * Mètode que serveix per afegir un moviment a la partida que estem guardant
     * @param temps Double que indica el moment en que s'ha fet el moviment
     * @param casella_x enter que indica la posicio x de la fitxa afegida
     * @param casella_y enter que indica la posicio y de la fitxa afegida
     * @param fitxa String que indica quina es la fitxa afegida
     */
    public void afeigirMoviment(double temps, int casella_x, int casella_y, String fitxa, boolean isUser) {
        Moviment new_move = new Moviment("default", temps, casella_x, casella_y, fitxa, isUser);
        //new_move.setId(id_moviment);
        partida.add(new_move);
        //id_moviment++;
    }

    /**
     * Mètode que serveix per guardar la partida a la base de dades
     * @param nomPartida String que indica el nom de la partida
     * @param nomJugador String que indica el nom del jugador de la partida
     * @param guanyador boolea que indica si l'usuari a guanyat
     */
    public void guardarPartida(String nomPartida, String nomJugador, boolean guanyador) {
        Partida new_partida = new Partida(nomPartida, nomJugador, guanyador);
        partidaDAO.addPartida(new_partida);

        //Canviem el nom de la partida pel que l'usuari ha posat i inserim els moviments
        for (int i = 0; i < partida.size(); i++) {
            partida.get(i).setNomPartida(nomPartida);
            movimentDAO.addMovimient(partida.get(i));
        }
    }

    /**
     * Mètode que serveix per comprovar que el nom de la partida no existeix
     * @param nomPartida String que indica quin es el nom de la Partida que es vol utilitzar
     * @return un boolea que indica si el nom introduit es pot utilitzar
     */
    public boolean nomPartidaCorrecte(String nomPartida) {
        boolean correcte = false;

        if(partidaDAO.readPartida(nomPartida) == null) {
            correcte = true;
        }
        return correcte;
    }

    /**
     * Mètode que serveix per fer un reset de la partida
     */
    public void resetPartida() {
        id_moviment = 0;

        if (partida.size() > 0) {
            partida.subList(0, partida.size()).clear();
        }
    }

    /**
     * Mètode que serveix per llegir tots els moviments d'una partida de la base de dades
     * @param nomPartida String que indica el nom de la partida de la que llegir els moviments
     * @return ArrayList de tots els moviements de la partida
     */
    public ArrayList<Moviment> readMovimentsPartida(String nomPartida) {
        return movimentDAO.readAllMovimentsPartida(nomPartida);
    }

    /**
     * Mètode que serveix per llegir totes les partides d'un usuari concret de la base de dades
     * @param nomUser String que indica el nom del usuari del que llegir les partides
     * @return ArrayList de totes les partides de l'usuari introduït
     */
    public ArrayList<Partida> getPartidesJugador(String nomUser) {
        return partidaDAO.getPartidesUsuari(nomUser);
    }

    /**
     * Mètode que serveix per llegir totes les partides de tots els usuaris menys la d'un en concret de la base de dades
     * @param nomUser String que indica el nom del unic usuari del que no es llegiran les partides
     * @return ArrayList de totes les partides del altres jugadors
     */
    public ArrayList<Partida> getPartides(String nomUser) {
        return partidaDAO.getPartides(nomUser);
    }

    /**
     * Mètode que serveix per llegir totes les dades dels usuaris de la base de dades
     * @return ArrayList de tots els usuaris registrats
     */
    public ArrayList<User> getDadesRanking() {
        return usuariDAO.readAllUsers();
    }
}

