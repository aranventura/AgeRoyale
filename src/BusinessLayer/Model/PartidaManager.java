package BusinessLayer.Model;

import BusinessLayer.Entities.*;

import java.awt.*;
import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * En esta clase se implementa los metodos realizados en las interficies ITime y TropaListenner
 *
 */
public class PartidaManager implements TropaListener, ITime {
    public static final int MAGA = 0;
    public static final int CANONERO = 1;
    public static final int ARQUERA = 2;
    public static final int CABALLERO = 3;

    public static final int COST_ARQUERA = 8;
    public static final int COST_CANONERO = 8;
    public static final int COST_CABALLERO = 6;
    public static final int COST_MAGE = 5;


    private User usuari;
    private Taulell taulell;
    private MagatzemPartida magatzemPartida;
    private GestioUser gestioUser;
    private int temps;
    private ArrayList<Fitxa> llistaFitxes= new ArrayList<>();
    private CtrlListenner listenner;
    private Maquina maquina;
    private TimeCounter timeCounter;
    private MoneyCounter moneyCounterUsuari;
    private MoneyCounter moneyCounterMaquina;
    private boolean esReproduida;


    /**
     * Constructor encargado de realizar una nueva partida para el usuario
     *
     */
    public PartidaManager(GestioUser gestioUser, CtrlListenner listenner) {
        this.esReproduida = false;
        this.magatzemPartida = new MagatzemPartida();
        this.gestioUser = gestioUser;
        comecarPartida(listenner);
    }

    /**
     * Constructor predeterminado para la realización de la reproducción de la partida guardada
     *
     */
    public PartidaManager(ArrayList<Moviment> movimentsPartida, GestioUser gestioUser, CtrlListenner listenner){
        this.gestioUser = gestioUser;
        this.esReproduida = true;
        comecarPartida(listenner);
        reproduirPartida(movimentsPartida);
    }

    /**
     * Método que reproduce una partida guardada
     * @param movimentsPartida
     */
    public void reproduirPartida(ArrayList<Moviment> movimentsPartida){
        int fitxaConst = 0;
        int i = 0;
        while (true){
            if (temps == movimentsPartida.get(i).getTemps()) {
                fitxaConst = stringFitxaToConst(movimentsPartida.get(i).getFitxa());
                addFitxa(fitxaConst, movimentsPartida.get(i).getCasella_x(), movimentsPartida.get(i).getCasella_y(), movimentsPartida.get(i).isUsuari());
                i++;
            }
        }
    }

    /**
     * Método que da pie a la partida
     * @param listenner Conexión entre la el modelo i el controller
     */
    public void comecarPartida(CtrlListenner listenner){
        this.taulell = new Taulell();
        this.listenner = listenner;
        this.temps = 0;

        this.usuari = new User(this.gestioUser.getUsuari(), listenner);

        //Confirguramos las posiciones de las bases
        for (int i = 0; i < 7; i++) {
            this.taulell.setFitxaSegonsPosicioFitxa(new Base(false, i, 0, this));
            this.taulell.setFitxaSegonsPosicioFitxa(new Base(true, i, 9, this));
        }

        //Tiramos el thread del dinero del usuario
        this.moneyCounterUsuari = new MoneyCounter(usuari, false);
        new Thread(moneyCounterUsuari).start();

        this.maquina = new Maquina(this, listenner);
        if(!esReproduida) {
            //Tiramos el thread de la maquina
            new Thread(maquina).start();
        }

        //Tiramos el thread de el dinero de la maquina
        this.moneyCounterMaquina = new MoneyCounter(maquina, false);
        new Thread(moneyCounterMaquina).start();

        //Tiramos el thred del tiempo
        this.timeCounter = new TimeCounter(this, false);
        new Thread(this.timeCounter).start();
    }

    public Taulell getTaulell() {
        return taulell;
    }

    public void setTaulell(Taulell taulell) {
        this.taulell = taulell;
    }

    /**
     * Método que añade una ficha en el tablero
     * @param id_fitxa Tipo de ficha que se ha añadido
     * @param casella_x Posición x
     * @param casella_y Posición y
     * @param user Boolean donde true = usuario y false = maquina (saber de quien es la ficha)
     */
    public synchronized void addFitxa(int id_fitxa, int casella_x, int casella_y, boolean user){
        switch (id_fitxa) {
            case MAGA:
                llistaFitxes.add(new Maga(user, casella_x, casella_y, this));
                break;

            case CANONERO:
                llistaFitxes.add(new Canonero(user, casella_x, casella_y, this));
                break;

            case ARQUERA:
                llistaFitxes.add(new Arquera(user, casella_x, casella_y, this));
                break;

            case CABALLERO:
                llistaFitxes.add(new Caballero(user, casella_x, casella_y, this));
                break;

            default:
                System.out.println("No existeix aquesta fitxa");
                break;
        }

        taulell.setFitxaSegonsPosicioFitxa(llistaFitxes.get(llistaFitxes.size()-1));
        new Thread(llistaFitxes.get(llistaFitxes.size()-1)).start();

        if (user){
            usuari.setNumTropes(usuari.getNumTropes() + 1);
            listenner.actualitzaGrafiquesUsuari(usuari);
        } else {
            maquina.setTropsCounter(maquina.getTropsCounter() + 1);
            maquina.setMoney(maquina.getMoney() - llistaFitxes.get(llistaFitxes.size()-1).getCost());
            listenner.actualitzaGrafiquesMaquina(maquina);
        }

        if (!this.esReproduida) {
            magatzemPartida.afeigirMoviment(temps, casella_x, casella_y, llistaFitxes.get(llistaFitxes.size() - 1).getNom(), llistaFitxes.get(llistaFitxes.size() - 1).isUsuari());
        }
    }


    /**
     * Método que mira si el nombre de la partida es correcto
     * @param nomPartida
     * @return Boolean true si es correcto
     */
    public boolean nomPartidaCorrecte(String nomPartida) {
        boolean correcte = false;
        if(magatzemPartida.nomPartidaCorrecte(nomPartida)) {
            correcte = true;
        }
        return correcte;
    }

    public void guardarPartida(String nomPartida, boolean guanyador){
        magatzemPartida.guardarPartida(nomPartida, usuari.getNom(), guanyador);
    }

    @Override
    public synchronized void moveFitxa(Point old_casella, Point new_casella) {
        taulell.getTaulell()[new_casella.y][new_casella.x].setFitxa(taulell.getFitxaCasella(old_casella.x, old_casella.y));
        taulell.getTaulell()[old_casella.y][old_casella.x].setFitxa(null);
        listenner.moveFitxaController(old_casella, new_casella);
    }

    @Override
    public synchronized void enemicMort(Fitxa fitxa) {
        for (int i = 0; i < llistaFitxes.size(); i++) {
            if (llistaFitxes.get(i).getRival() == fitxa) {
                llistaFitxes.get(i).setRival(null);
            }
        }
        taulell.getTaulell()[fitxa.getPosY()][fitxa.getPosX()].setFitxa(null);
        listenner.eliminarEnemic(fitxa.getPosY(), fitxa.getPosX());
        llistaFitxes.remove(fitxa);

        if (fitxa.isUsuari()){
            usuari.setNumTropes(usuari.getNumTropes() - 1);
            maquina.setMoney(maquina.getMoney()+5);
            listenner.actualitzaGrafiquesUsuari(usuari);
        } else {
            maquina.setTropsCounter(maquina.getTropsCounter() - 1);
            usuari.setMoney(maquina.getMoney()+5);
            listenner.actualitzaGrafiquesMaquina(maquina);
        }
    }

    @Override
    public void baseAtacada(int damage, boolean propietariFitxa) {
        if (propietariFitxa) {
            maquina.updateVida(damage/4);

            listenner.actualitzaGrafiquesMaquina(maquina);
            if(maquina.getNumVides() <= 0){
                pararPartida();
                if (!this.esReproduida) {
                    gestioUser.afegirPartidaGuanyada();
                }
                listenner.partidaAcabada(propietariFitxa, usuari.getNom(), magatzemPartida);
            }
        } else {
            usuari.updateVida(damage/4);
            listenner.actualitzaGrafiquesUsuari(usuari);
            if (usuari.getNumVides() <=  0){
                pararPartida();
                listenner.partidaAcabada(propietariFitxa, usuari.getNom(), magatzemPartida);
            }
        }
    }

    /**
     * Método que para los threads lanzados en la partida realizada cuando se termina la partida
     *
     */
    public void pararPartida(){
        for (int i = 0; i < llistaFitxes.size(); i++) {
            llistaFitxes.get(0).stop();
            llistaFitxes.remove(llistaFitxes.get(0));
        }
        timeCounter.stop();
        moneyCounterUsuari.stop();
        moneyCounterMaquina.stop();
        maquina.stop();
        if (llistaFitxes.size() > 0){
            for (int i = 0; i < llistaFitxes.size(); i++) {
                llistaFitxes.get(0).stop();
                llistaFitxes.remove(llistaFitxes.get(0));
            }
        }

    }

    /**
     * Método que segun el nombre de una ficha lo pasamos a string
     * @param nom Nombre de la ficha
     * @return Constante identificativo de la ficha
     */
    private int stringFitxaToConst(String nom){
        int constTropa = 0;
        switch (nom){
            case "Arquera":
                constTropa = ARQUERA;
                break;
            case "Caballero":
                constTropa = CABALLERO;
                break;
            case "Cañonero":
                constTropa = CANONERO;
                break;
            case "Mago":
                constTropa = MAGA;
                break;

            default:
                System.out.println("No existeix aquesta fitxa");
                break;
        }
        return constTropa;
    }

    @Override
    public synchronized void rivalAtacat(Fitxa f) {
        taulell.setFitxaSegonsPosicioFitxa(f);
    }

    @Override
    public void updateTime() {
        temps++;
    }

    public User getUsuari() {
        return usuari;
    }
}
