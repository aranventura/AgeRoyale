package PersistanceLayoud.sql;

import BusinessLayer.Entities.Partida;
import PersistanceLayoud.PartidaDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase que implementa los metodos de {@link PartidaDAO} interficie
 *
 * Implementa la gestion de datos con la base de datos de la entidad partida
 */
public class SQLPartidaDAO implements PartidaDAO {

    @Override
    public void addPartida(Partida partida) {
        String query = "INSERT INTO Partida(nom_partida, usuari, data, user_guanyador) VALUES ('" +
                partida.getNomPartida() + "', '" +
                partida.getNomJugador() + "', '" +
                partida.getData() + "', '" +
                partida.getIntGuanyador() +
                "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public Partida readPartida(String identificador) {
        Partida partida = null;
        String query = "SELECT nom_partida, usuari, data, user_guanyador FROM Partida WHERE nom_partida = '"+identificador+"' OR usuari = '"+identificador+"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            result.next();
            String nom_partida = result.getString("nom_partida");
            String usuari = result.getString("usuari");
            String data = result.getString("data");
            boolean guanyador = result.getBoolean("user_guanyador");

            partida = new Partida(nom_partida, usuari, guanyador, data);

        } catch (SQLException e) {

            //e.printStackTrace();
            //System.err.println("Usuario o e-mail no se encuentra en la base de datos.");
        }

        return partida;
    }

    @Override
    public void updatePartida(String identificador, Partida partida_upd) {

    }

    @Override
    public void deletePartidaPerNom(String nom_partida) {
        String query = "DELETE FROM Partida WHERE nom_partida = '" + nom_partida + "';";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public void deletePartidaPerUsuari(String usuari) {
        String query = "DELETE FROM Partida WHERE usuari = '" + usuari + "';";
        SQLConnector.getInstance().deleteQuery(query);
    }

    @Override
    public ArrayList<Partida> getPartidesUsuari(String user_name) {
        ArrayList<Partida> partidesUsr = new ArrayList<>();
        LinkedList<String> names = new LinkedList<>();
        String query = "SELECT p.nom_partida, p.usuari, p.data, p.user_guanyador FROM Partida AS p, Usuari AS u " +
                "WHERE u.nom = p.usuari AND u.nom = '" + user_name + "';" ;

        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try{
            while (result.next()) {
                //result.next();
                String nom_partida = result.getString("nom_partida");
                String usuari = result.getString("usuari");
                String data = result.getString("data");
                boolean guanyador = result.getBoolean("user_guanyador");

                partidesUsr.add(new Partida(nom_partida, usuari, guanyador, data));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partidesUsr;
    }

    @Override
    public ArrayList<Partida> getPartides(String user_name) {
        ArrayList<Partida> partidesNoUsr = new ArrayList<>();
        LinkedList<String> names = new LinkedList<>();
        String query = "SELECT p.nom_partida, p.usuari, p.data, p.user_guanyador FROM Partida AS p, Usuari AS u " +
                "WHERE u.nom = p.usuari AND u.nom NOT LIKE '" + user_name + "';" ;

        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try{
            while (result.next()) {
                //result.next();
                String nom_partida = result.getString("nom_partida");
                String usuari = result.getString("usuari");
                String data = result.getString("data");
                boolean guanyador = result.getBoolean("user_guanyador");

                partidesNoUsr.add(new Partida(nom_partida, usuari, guanyador, data));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partidesNoUsr;
    }
}
