package PersistanceLayoud.sql;

import BusinessLayer.Entities.Moviment;
import PersistanceLayoud.MovimentDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Clase que implementa los metodos de {@link MovimentDAO} interficie
 *
 * Implementa la gestion de datos con la base de datos de la entidad moviment
 */
public class SQLMovimentDAO implements MovimentDAO {

    @Override
    public void addMovimient(Moviment moviment) {
        String query = "INSERT INTO Moviment(nom_partida, temps, casella_x, casella_y, fitxa, usuari) VALUES ('" +
                moviment.getNomPartida() + "', '" +
                moviment.getTemps() + "', '" +
                moviment.getCasella_x() + "', '" +
                moviment.getCasella_y() + "', '" +
                moviment.getFitxa() + "', '" +
                moviment.getIntUsuari() +
                "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public ArrayList<Moviment> readAllMovimentsPartida(String nom_partida) {
        ArrayList<Moviment> moviments = new ArrayList<>();
        String query = "SELECT m.nom_partida, m.temps, m.casella_x, m.casella_y, m.fitxa, m.usuari FROM Moviment AS m " +
                "WHERE m.nom_partida LIKE '" + nom_partida + "';" ;

        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            while (result.next()) {

                String nom = result.getString("nom_partida");
                double temps = result.getDouble("temps");
                int casella_x = result.getInt("casella_x");
                int casella_y = result.getInt("casella_y");
                String fitxa = result.getString("fitxa");
                int isUsuari = result.getInt("usuari");
                moviments.add(new Moviment(nom, temps, casella_x, casella_y, fitxa, isUsuari));
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return moviments;
    }

    @Override
    public void updateMoviment(int identificador, Moviment moviment_upd) {

    }
    @Override
    public void deleteMovimentsPartida(String nom_partida) {
        String query = "DELETE FROM Moviment WHERE nom_partida = " + nom_partida + ";";
        SQLConnector.getInstance().deleteQuery(query);
    }
}
