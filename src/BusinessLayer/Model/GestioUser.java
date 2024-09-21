package BusinessLayer.Model;

import BusinessLayer.Entities.User;
import PersistanceLayoud.MovimentDAO;
import PersistanceLayoud.PartidaDAO;
import PersistanceLayoud.UsuariDAO;
import PersistanceLayoud.sql.SQLMovimentDAO;
import PersistanceLayoud.sql.SQLPartidaDAO;
import PersistanceLayoud.sql.SQLUsuariDAO;

/**
 * Clase que usa los metodos implementados en {@link SQLPartidaDAO} {@link SQLMovimentDAO} i {@link SQLUsuariDAO}
 *
 * Se encarga de gestionar los datos del usuario
 */
public class GestioUser {
    private final UsuariDAO userDAO;
    private final PartidaDAO partidaDAO;
    private final MovimentDAO movimentDAO;
    private String dataError;
    private User usuari;

    /**
     * Constructor per GestioUser
     */
    public GestioUser(){
        userDAO = new SQLUsuariDAO();
        partidaDAO = new SQLPartidaDAO();
        movimentDAO = new SQLMovimentDAO();
        dataError = "";
    }

    /**
     * Mètode que serveix per comprovar si les dades introduïdes en el signIn són correctes
     * En cas de ser un nou usuari vàlid el crea i guarda a la classe i a la base de dades
     * @param nom String que indica quin es el nom del nou usuari
     * @param correu String que indica quin es el correu del nou usuari
     * @param contra1 String que indica quina es la contrasenya del nou usuari
     * @param contra2 String que indica quina es la contrasenya introduida per sogon cop del nou usuari
     * @return un boolea que indica si el signIn s'ha realitzat de manera correcta
     */
    public boolean signIn (String nom, String correu, String contra1, String contra2) {
        boolean signIn = false;
        boolean correcte = comprovacioSignIn(nom, correu, contra1, contra2);

        if (correcte) {
            User new_usuari = new User(nom, correu, contra1);
            if(userDAO.readUser(nom) == null && userDAO.readUser(correu) == null) {
                userDAO.createUser(new_usuari);
                usuari = new_usuari;
                signIn = true;
            } else {
                dataError = dataError.concat("L'usuari ja s'ha registrat\n");
            }
        }
        return signIn;
    }

    /**
     * Mètode que serveix per comprovar si les dades introduïdes en el logIn són correctes
     * En cas de ser un usuari vàlid es guarda a la classe
     * @param identificador String que indica quin es el nom o correu de l'usuari
     * @param password String que indica quina es la contrasenya de usuari introduit
     * @return un boolea que indica si el logIn s'ha realitzat de manera correcta
     */
    public boolean logIn(String identificador, String password) {
        boolean logIn = false;
        boolean complet = campsOmplerts(identificador, password);
        if (complet) {
            User read_user = userDAO.readUser(identificador);
            if(read_user != null && read_user.getPassword().equals(password)) {
                usuari = read_user;
                logIn = true;
            } else {
                dataError = "El nom d'usuari i/o la contrasenya son incorrectes";
            }
        }

        return logIn;
    }

    /**
     * Mètode que serveix per fer logOut de l'usuari actual
     */
    public void logOut () {
        //hace falta borrarlo de los otros sitios donde se use
        usuari = null;
    }

    /**
     * Mètode que serveix per eliminar l'usuari actual de la classe i la base de dades
     * Per tant, també s'eliminen totes les partides del jugador de la base de dades
     */
    public void deleteUser() {
        //falta eliminar previamente movimientos

        partidaDAO.deletePartidaPerUsuari(usuari.getNom());
        userDAO.deleteUser(usuari.getNom());
        usuari = null;
    }

    /**
     * Mètode que serveix per afegir una partida guanyada a la base de dades del usuari
     */
    public void afegirPartidaGuanyada() {
        userDAO.updateUser(this.usuari, "guanyades", this.usuari.getpGuanyades() + 1);
        //usuari.setpGuanyades(usuari.getpGuanyades() + 1);
    }
    /**
     * Mètode que serveix per afegir una partida jugada a la base de dades del usuari
     */
    public void afegirPartidaJugada() {
        userDAO.updateUser(usuari, "total_partides", this.usuari.getNumPartides() + 1);
        //usuari.setNumPartides(usuari.getNumPartides() + 1);
    }


    //Funcions auxiliars per fer gestio Usuari
    /**
     * Mètode privat que serveix per comprovar que les dades introduïdes en el signIn són correctes
     * @param nom String que indica quin es el nom del nou usuari
     * @param correu String que indica quin es el correu del nou usuari
     * @param contra1 String que indica quina es la contrasenya del nou usuari
     * @param contra2 String que indica quina es la contrasenya introduida per sogon cop del nou usuari
     * @return un boolea que indica si les dades són correctes
     */
    private boolean comprovacioSignIn(String nom, String correu, String contra1, String contra2) {
        boolean signInCorrecte, totesIntroduides, correuCorrecte, contrasenyaCorrecte;

        signInCorrecte = false;
        totesIntroduides = comprovarIntroduccioDades(nom, correu, contra1, contra2);
        if(totesIntroduides) {
            dataError = "";
            correuCorrecte = comprovarCorreu(correu);
            if(correuCorrecte){
                dataError = "";
                contrasenyaCorrecte = comprovacioContrasenya(contra1, contra2);
                if(contrasenyaCorrecte){
                    dataError = "";
                    signInCorrecte = true;
                }
            }
        }

        return signInCorrecte;
    }

    /**
     * Mètode privat que serveix per comprovar que tots els camps del LogIn estan omplerts
     * @param identificador String que identifica a l'usuari
     * @param password String que indica la contrasenya de l'usuari
     * @return un boolea que indica si tots els camps estan plens
     */
    private boolean campsOmplerts(String identificador, String password) {
        boolean totesIntroduides = true;
        dataError = "";
        if(identificador.isEmpty() || password.isEmpty()){
            totesIntroduides = false;
            if(identificador.isEmpty()){
                dataError = dataError.concat("Falta omplir el camp de l'usuari\n");
            }
            if(password.isEmpty()){
                dataError = dataError.concat("Falta omplir el camp de contrasenya\n");
            }
        }
        return totesIntroduides;
    }

    /**
     * Mètode privat que serveix per comprovar que s'han introduït totes les dades en el logIn
     * @param nom String que indica quin es el nom de l'usuari
     * @param correu String que indica quin es el correu de l'usuari
     * @param contra1 String que indica quina es la contrasenya de l'usuari
     * @param contra2 String que indica quina es la contrasenya introduida per sogon cop de l'usuari
     * @return un boolea que indica si s'han introduit totes les dades
     */
    private boolean comprovarIntroduccioDades(String nom, String correu, String contra1, String contra2){
        boolean totesIntroduides = true;
            dataError = "";
        if(nom.isEmpty() || correu.isEmpty() || contra1.isEmpty()|| contra2.isEmpty()){
            totesIntroduides = false;
            if(nom.isEmpty()){
                dataError = dataError.concat("Falta omplir el camp de Nom\n");
            }
            if(correu.isEmpty()){
                dataError = dataError.concat("Falta omplir el camp de correu\n");
            }
            if(contra1.isEmpty()){
                dataError = dataError.concat("Falta omplir el camp de contrasenya\n");
            }
            if(contra2.isEmpty()){
                dataError = dataError.concat("Falta omplir el camp de repetir contrasenya\n");
            }
        }
        return totesIntroduides;
    }

    /**
     * Mètode privat que serveix per comprovar que el format del correu es correcte
     * @param correu String que representa el correu a comprovar
     * @return un boolea que indica si el format del correu es correcte
     */
    private boolean comprovarCorreu(String correu){
        boolean correuCorrecte = false, arroba = false;

        for(int i = 0; i < correu.length(); i++){
            if(correu.charAt(i)=='@' || arroba){
                arroba = true;
                if(correu.charAt(i) == '.'){
                    correuCorrecte = true;
                }
            }
        }
        if(!correuCorrecte){
            dataError = dataError.concat("Correu incorrecte\n");
        }
        return correuCorrecte;
    }

    /**
     * Mètode privat que serveix per comprovar que les dues contrasenyes introduïdes són correctes
     * Correctes implica que siguin iguals i que s'adequiin al format de contrasenyes del MIT
     * @param contra1 String que representa la primera introducció de la contrasenya
     * @param contra2 String que representa la segona introducció de la contrasenya
     * @return un boolea que indica si les contrasenyes són correctes
     */
    private boolean comprovacioContrasenya(String contra1, String contra2){
        boolean contrasenyaCorrecte = false;
        boolean conteMajus = false, conteAlfaNum = false, conteNums = false, conteMinus = false;
        int requeriments = 0;

        if(contra1.equals(contra2)){
            if(contra1.length()>=8) {
                for (int i = 0; i < contra1.length(); i++) {
                    if ((contra1.charAt(i) > 64 && contra1.charAt(i) < 91) && conteMajus == false) { //comprovacio majuscules
                        conteMajus = true;
                        requeriments++;
                    }
                    if ((contra1.charAt(i) > 31 && contra1.charAt(i) < 48) && conteAlfaNum == false) { //comprovacio alfanumerics
                        conteAlfaNum = true;
                        requeriments++;
                    }
                    if ((contra1.charAt(i) > 47 && contra1.charAt(i) < 58) && conteNums == false) { //comprovacio numeros
                        conteNums = true;
                        requeriments++;
                    }
                    if ((contra1.charAt(i) > 96 && contra1.charAt(i) < 123) && !conteMinus) {
                        conteMinus = true;
                        requeriments++;
                    }
                }
                if (requeriments > 1) {
                    contrasenyaCorrecte = true;
                } else {
                    dataError = dataError.concat("Contrasenya poc segura\n");
                }
            }else{
                dataError = dataError.concat("Contrasenya massa curta\n");
            }
        }else{
            dataError = dataError.concat("Les contrasenyes no coincideixen\n");
        }
        return contrasenyaCorrecte;
    }

    /**
     * Getter de l'error produit
     *
     * @return Un string que indica quin ha estat l'error concret
     */
    public String getDataError(){
        return dataError;
    }

    /**
     * Getter de l'usuari
     *
     * @return El User que ha fet logIn o signIn correctament
     */
    public User getUsuari() {
        return usuari;
    }

    /**
     * Setter de l'usuari
     * @param usuari User que es guardarà com a nou usuari
     */
    public void setUsuari(User usuari) {
        this.usuari = usuari;
    }
}
