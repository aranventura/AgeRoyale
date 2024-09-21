package PersistanceLayoud;

import BusinessLayer.Entities.Partida;
import BusinessLayer.Entities.User;

import java.util.ArrayList;
import java.util.LinkedList;

public interface UsuariDAO {
    /**
     * Metodo que añade un nuevo usuario a la bdd
     * @param usuari Usuario que queremos guardar
     */
    void createUser(User usuari);

    /**
     * Metodo que lee todos los usuarios de la bdd
     * @return ArrayList de User de todos los usuarios
     */
    ArrayList<User> readAllUsers();

    /**
     * Metodo que lee un usuario de la bdd
     * @param identificador que representa el nombre o correo del usuario que queremos obtener
     * @return El User que se corresponde con el identificador
     */
    User readUser(String identificador);

    /**
     * Metodo que actualiza la información de tipo String de un usuario de la bdd
     * @param usr_old que representa el usuario del que vamos a actualizar los datos
     * @param row_update nombre de la columna que queremos actualizar
     * @param new_valor nuevo valor de tipo String
     */
    void updateUser(User usr_old, String row_update, String new_valor);

    /**
     * Metodo que actualiza la información de tipo int de un usuario de la bdd
     * @param usr_old que representa el usuario del que vamos a actualizar los datos
     * @param row_update nombre de la columna que queremos actualizar
     * @param new_valor nuevo valor de tipo int
     */
    void updateUser(User usr_old, String row_update, int new_valor);

    /**
     * Metodo que elimina un usuario de la bdd
     * @param identificador String que identifica al usuario que queremos eliminar
     */
    void deleteUser(String identificador);



}