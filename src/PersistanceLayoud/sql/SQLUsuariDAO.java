package PersistanceLayoud.sql;

import BusinessLayer.Entities.User;
import PersistanceLayoud.PartidaDAO;
import PersistanceLayoud.UsuariDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase que implementa los metodos de {@link UsuariDAO} interficie
 *
 * Implementa la gestion de datos con la base de datos de la entidad User
 */
public class SQLUsuariDAO implements UsuariDAO{

    @Override
    public void createUser(User usuari) {
        int partides = 0;
        int guanyades = 0;
        String query = "INSERT INTO Usuari(nom, correu, password, total_partides, guanyades) VALUES ('" +
                usuari.getNom() + "', '" +
                usuari.getCorreu() + "', '" +
                usuari.getPassword() + "', '" +
                partides + "', '" +
                guanyades + "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    public User readUser(String identificador) {
        User usuari = null;
        String query = "SELECT nom, correu, password, total_partides, guanyades FROM Usuari WHERE nom = '"+identificador+"' OR correu = '"+identificador+"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            result.next();
            String nom = result.getString("nom");
            String correu = result.getString("correu");
            String password = result.getString("password");
            int total_partides = result.getInt("total_partides");
            int guanyades = result.getInt("guanyades");

            usuari = new User(nom, correu, password, total_partides, guanyades);

        } catch (SQLException e) {

            //e.printStackTrace();
            System.err.println("Usuario o e-mail no se encuentra en la base de datos.");
        }
        return usuari;
    }

    @Override
    public ArrayList<User> readAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT nom, correu, password, total_partides, guanyades FROM Usuari;";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            while (result.next()) {
                String nom = result.getString("nom");
                String correu = result.getString("correu");
                String password = result.getString("password");
                int total_partides = result.getInt("total_partides");
                int guanyades = result.getInt("guanyades");

                users.add(new User(nom, correu, password, total_partides, guanyades));
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public void updateUser(User usr_old, String row_update, String new_valor) {
        String query = "UPDATE Usuari SET "+row_update+" = '"+new_valor+"' WHERE nom = '"+usr_old.getNom()+"' AND correu = '"+usr_old.getCorreu()+"' ;";
        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void updateUser(User usr_old, String row_update, int new_valor) {
        String query = "UPDATE Usuari SET "+row_update+" = "+new_valor+" WHERE nom = '"+usr_old.getNom()+"' AND correu = '"+usr_old.getCorreu()+"' ;";
        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void deleteUser(String identificador) {
        String query = "DELETE FROM Usuari WHERE nom = '"+identificador+"' OR correu = '"+identificador+"';";
        SQLConnector.getInstance().deleteQuery(query);
    }
}
