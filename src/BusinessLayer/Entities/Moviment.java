package BusinessLayer.Entities;

public class Moviment {
    private int id;
    private String nom_partida;
    private double temps;
    private int casella_x;
    private int casella_y;
    private String fitxa;
    private int usuari;

    /**
     * Constructor per Partida per quan es llegeix una partida de la base de dades
     * @param nom_partida nom de la partida
     * @param temps moment en segons de quan s'ha fet el moviment
     * @param casella_x posicio x de la fitxa
     * @param casella_y posicio y de la fitxa
     * @param fitxa nom de la fitxa
     * @param usuari representa si el moviment ha estat fet per l'usuari o no
     */
    public Moviment(String nom_partida, double temps, int casella_x, int casella_y, String fitxa, int usuari) {
        this.nom_partida = nom_partida;
        this.temps = temps;
        this.casella_x = casella_x;
        this.casella_y = casella_y;
        this.fitxa = fitxa;
        this.usuari = usuari;
    }

    /**
     * Constructor per Partida per quan es crea una partida nova
     * @param nom_partida nom de la partida
     * @param temps moment en segons de quan s'ha fet el moviment
     * @param casella_x posicio x de la fitxa
     * @param casella_y posicio y de la fitxa
     * @param fitxa nom de la fitxa
     * @param usuari representa si el moviment ha estat fet per l'usuari o no
     */
    public Moviment(String nom_partida, double temps, int casella_x, int casella_y, String fitxa, boolean usuari) {
        this.nom_partida = nom_partida;
        this.temps = temps;
        this.casella_x = casella_x;
        this.casella_y = casella_y;
        this.fitxa = fitxa;
        this.usuari = boolToInt(usuari);
    }
    /**
     * Getter del id del moviment
     *
     * @return Un enter que identifica inequivocament un moviment
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del id del moviment
     * @param id enter que es guardara com el nou identificador del moviment
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter del nom de la Partida
     *
     * @return Un string que representa el nom de la Partida
     */
    public String getNomPartida() {
        return nom_partida;
    }

    /**
     * Setter del nom de la partida
     * @param nom_partida String que es guardarà com a nou nom de la partida
     */
    public void setNomPartida(String nom_partida) {
        this.nom_partida = nom_partida;
    }

    /**
     * Getter del temps en que s'ha realitzat el moviment
     *
     * @return Un double que representa el moment en que s'ha realitzat el moviment
     */
    public double getTemps() {
        return temps;
    }

    /**
     * Setter del temps en que s'ha realitzat el moviment
     * @param temps double que es guardarà com a nou temps del moviment
     */
    public void setTemps(double temps) {
        this.temps = temps;
    }

    /**
     * Getter de la posicio x de la fitxa en el taulell
     *
     * @return Un enter que representa la posicio x de la fitxa en el taulell
     */
    public int getCasella_x() {
        return casella_x;
    }

    /**
     * Setter de la posicio x de la fitxa en el taulell
     * @param casella_x enter que es guardarà com a nova posicio x de la fitxa
     */
    public void setCasella_x(int casella_x) {
        this.casella_x = casella_x;
    }

    /**
     * Getter de la posicio y de la fitxa en el taulell
     *
     * @return Un enter que representa la posicio y de la fitxa en el taulell
     */
    public int getCasella_y() {
        return casella_y;
    }

    /**
     * Setter de la posicio y de la fitxa en el taulell
     * @param casella_y enter que es guardarà com a nova posicio y de la fitxa
     */
    public void setCasella_y(int casella_y) {
        this.casella_y = casella_y;
    }

    /**
     * Getter del nom de la fitxa
     *
     * @return Un string que representa el nom/tipus de la fitxa
     */
    public String getFitxa() {
        return fitxa;
    }

    /**
     * Setter de la fitxa
     * @param fitxa string que es guardarà com a nou nom de la fitxa
     */
    public void setFitxa(String fitxa) {
        this.fitxa = fitxa;
    }

    /**
     * Getter de l'atribut usuari
     *
     * @return Un enter que representa si el moviment ha estat fet per l'usuari o no
     */
    public int getIntUsuari(){
        return usuari;
    }

    /**
     * Getter de l'atribut usuari
     *
     * @return Un boolea que representa si el moviment ha estat fet per l'usuari o no
     */
    public boolean isUsuari() {
        return intToBool(usuari);
    }

    /**
     * Setter del nom de la partida
     * @param usuari enter que es guardarà com a nou atribut de si es usuari o no
     */
    public void setUsuari(int usuari) {
        this.usuari = usuari;
    }

    /**
     * Mètode privat que passa un enter a boolea
     * @param integer el enter que es vol passar a boolea
     * @return El boolea que representa l'enter entrat
     */
    private boolean intToBool(int integer) {
        boolean bool = false;
        if(integer == 1) {
            bool = true;
        }
        return bool;
    }

    /**
     * Mètode privat que passa un boolea a enter
     * @param bool el boolea que es vol passar a in
     * @return L'enter que representa el boolea entrat
     */
    private int boolToInt(boolean bool){
        int integer = 0;
        if(bool) {
            integer = 1;
        }
        return integer;
    }
}
