package BusinessLayer.Entities;

import BusinessLayer.Model.CtrlListenner;
import BusinessLayer.Model.IJugador;
import BusinessLayer.Model.MoneyCounter;

public class User implements IJugador {
    private String nom;
    private String correu;
    private String password;
    private int numPartides;
    private int pGuanyades;
    private int money;
    private int numTropes;
    private int numVides;
    private CtrlListenner listenner;

    private static final int VIDA_BASE = 100;


    public User(String nom, String correu, String password) {
        this.nom = nom;
        this.correu = correu;
        this.password = password;
        this.numPartides = 0;
        this.pGuanyades = 0;
        this.money = 10;
        this.numTropes = 0;
        this.numVides = VIDA_BASE;
    }

    public User(String nom, String correu, String password, int numPartides, int pGuanyades){
        this.nom = nom;
        this.correu = correu;
        this.password = password;
        this.numPartides = numPartides;
        this.pGuanyades = pGuanyades;
        this.money = 0;
        this.numTropes = 0;
        this.numVides = 500;
    }

    //Haurem d'utilitzar aquest contructor per omplier tot el qe necesssitem per la partida
    public User(String nom, String correu, String password, int numPartides, int pGuanyades, int money, int numTropes, int numVides) {
        this.nom = nom;
        this.correu = correu;
        this.password = password;
        this.numPartides = numPartides;
        this.pGuanyades = pGuanyades;
        this.money = 0;
        this.numTropes = 0;
        this.numVides = 500;
    }

    //Constructor hecho pa probar
    public User(String nom, String correu, String password, CtrlListenner listenner) {
        this.nom = nom;
        this.correu = correu;
        this.password = password;
        this.numPartides = 0;
        this.pGuanyades = 0;
        this.money = 0;
        this.numTropes = 0;
        this.numVides = VIDA_BASE;
        this.listenner = listenner;
    }

    //Contructor auxiliar para user
    public User(User user, CtrlListenner listenner) {
        this.nom = user.getNom();
        this.money = 10;
        this.numTropes = 0;
        this.numVides = VIDA_BASE;
        this.listenner = listenner;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumPartides() {
        return numPartides;
    }

    public void setNumPartides(int numPartides) {
        this.numPartides = numPartides;
    }

    public int getpGuanyades() {
        return pGuanyades;
    }

    public void setpGuanyades(int pGuanyades) {
        this.pGuanyades = pGuanyades;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNumTropes() {
        return numTropes;
    }

    public void setNumTropes(int numTropes) {
        this.numTropes = numTropes;
    }

    public int getNumVides() {
        return numVides;
    }

    public void setNumVides(int numVides) {
        this.numVides = numVides;
    }

    public float getWinRatio() {
        return (float) getpGuanyades() / getNumPartides();
    }



    @Override
    public void startDiners() {
        new Thread(new MoneyCounter(this, false)).start();
    }


    @Override
    public void updateMoney() {
        money++;
        listenner.actualitzaGrafiquesUsuari(this);
    }

    @Override
    public void updateVida(int damage) {
        numVides = numVides - damage;
    }

    @Override
    public boolean siPotPagar() {
        return false;
    }
}
