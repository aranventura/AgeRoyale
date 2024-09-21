package BusinessLayer.Entities;

import BusinessLayer.Model.CtrlListenner;
import BusinessLayer.Model.IJugador;
import BusinessLayer.Model.MoneyCounter;
import BusinessLayer.Model.PartidaManager;
import PresentationLayoud.Controllers.CtrlPartida;
import PresentationLayoud.Views.PartidaView;
//import java.util.*;
import java.awt.*;

/**
 * Clase que implemeta los metodos de IJugador y runnable
 *
 *
 */
public class Maquina implements IJugador, Runnable {
    private final int COST_MES_BAIX_TROPA = 5;
    private String nom;
    private int money;
    private int numTropes;
    private int numVides;
    private int tropsCounter = 0;
    private int temps_count = 0;
    private boolean detener;

    private final PartidaManager pm;
    private final CtrlListenner listenner;

    /**
     * Constructor de la classe Maquina
     * @param pm conte totes les dades de la partida, estat del taulell, vida rival etc...
     * @param listenner conte tots els metodes per que la maquina es conecti amb la PresentationLayoud
     */
    public Maquina(PartidaManager pm, CtrlListenner listenner) {
        this.pm = pm;
        this.listenner = listenner;
        //tirar thread past
        this.numVides = 100;
        this.money = 10;
        this.detener = false;
        //startDiners();
    }

    /**
     * Funcio que ve implementada de Runnable que te un comportament determinant
     * En aquest cas el funcionament de la maquina
     */
    @Override
    public void run() {
        while(!detener) {
           // if(siPotPagar()) {
            if (temps_count == 4) {
                generarTropaOfensiva();
                temps_count = 0;
                try {
                    long sleepTime = 4000;
                    Thread.sleep(sleepTime);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }

            generarTropaDefensiva();

            try {
                long sleepTime = 1000;
                Thread.sleep(sleepTime);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temps_count++;
          /*  }else{
                try {
                    long sleepTime = 4000;
                    Thread.sleep(sleepTime);
                    temps_count = 4;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
        }


    }
    //OFFENSIVE LOGIC//
    /**
     * funcio que coloca una fitxa ofensiva random a una posicio random de la seva meitat del taulell que estigui lliure
     */
    private void generarTropaOfensiva(){//(Taulell taulell, PartidaManager pm, CtrlListenner listenner){
        int max = 1;
        int min = 0;
        int range = max - min + 1;
        int rand;
        Point position;

        rand = (int)(Math.random() * range) + min;
        position = calcPosicioOfensiva(pm.getTaulell());

        if(siPotPagar()) {
            if (rand == 0) {
                pm.addFitxa(PartidaManager.CABALLERO, position.x, position.y, false);
                listenner.addFitxaController(position.x, position.y, PartidaView.ESQUELETO);
            } else {
                pm.addFitxa(PartidaManager.ARQUERA, position.x, position.y, false);
                listenner.addFitxaController(position.x, position.y, PartidaView.ARQUERA);
            }
        }
    }

    /**
     * funcio que busca una posicio random a la meitad del taulell de la maquina per colocar una fitxa ofensiva
     * @param taulell
     * @return
     */
    private Point calcPosicioOfensiva(Taulell taulell){
        Point position = new Point();
        int rangeX = 7;
        int rangeY = 4;
        int randomX, randomY;

        do{
            randomX = (int) (Math.random() * rangeX);
            randomY = (int) (Math.random() * rangeY) + 1;
        }while(taulell.getFitxaCasella(randomX,randomY) != null);

        position.x = randomX;
        position.y = randomY;

        return position;
    }

    //DEFENSIVE LOGIC//

    /**
     * funcio que genera una tropa defensiva y la coloca al taulell
     */
    private void generarTropaDefensiva(){
        int max = 1;
        int min = 0;
        int range = max - min + 1;
        int rand;
        Point position;
        Point enemyPosition;
        boolean aliDefending = false;

        enemyPosition = detectEnemyPosition();
        System.out.println("ENEMY X:"+ enemyPosition.x);
        if(!enemyPosition.toString().contentEquals("java.awt.Point[x=0,y=0]")) {
            if (siPotPagar()) {
                rand = (int)(Math.random() * range) + min;
                position = calcPosTropaDefensiva(enemyPosition);
                //System.out.println("position defensiva:"+position.x+" "+position.y);
                if (rand == 0) {
                    pm.addFitxa(PartidaManager.CANONERO, position.x, position.y, false);
                    listenner.addFitxaController(position.x, position.y, PartidaView.CAÑON);
                } else {
                    pm.addFitxa(PartidaManager.MAGA, position.x, position.y, false);
                    listenner.addFitxaController(position.x, position.y, PartidaView.MAGO);
                }
            }
        }
    }

    /**
     * Función que comprueva si hay algun aliado defendiendo la tropa enemiga detectada
     * @param columna Columna
     * @return boolean
     */
    private boolean hihaAliatDefenent(int columna){
        boolean aliatDef = false;
        for (int h = 1; h < 5; h++) {//y aliat
            if (pm.getTaulell().getFitxaCasella(columna, h) != null) {
                if (pm.getTaulell().getFitxaCasella(columna, h).isUsuari() == false) {
                    aliatDef = true;
                }
            }
        }
        return aliatDef;
    }

    /**
     * Funcio que detecta si hi ha un enemic a la meitad de la maquina
     * que no esta sent defensat per cap fitxa de la maquina
     * @return retorna un Point amb la ubicacio de l'enemic
     */
    private Point detectEnemyPosition(){
        Point position = new Point();
        boolean detectEnemy = false;
        boolean detectAlli = false;

        for (int i = 1; i < 5; i++){//x
            for (int j = 0; j < 7; j++){//y
                if(pm.getTaulell().getFitxaCasella(j,i) != null ){
                    if(pm.getTaulell().getFitxaCasella(j,i).isUsuari() == true && detectEnemy == false) {

                        if(i > 1) {
                            detectAlli= hihaAliatDefenent(j);
                        }
                        if(!detectAlli) {
                            position.x = pm.getTaulell().getFitxaCasella(j, i).getPosX();
                            position.y = pm.getTaulell().getFitxaCasella(j, i).getPosY();
                            detectEnemy = true;
                        }

                    }
                }
            }
        }

        return position;
    }

    /**
     * Funcio que calcula la posicio de la tropa defensiva respecta l'enemic i la base
     * @param enemyPos paramatre que conte la posicio del enemic del que ens anem a defendre
     * @return retorna la posicio de la fitxa que defensara
     */
    private Point calcPosTropaDefensiva(Point enemyPos){
        Point position = new Point();
        int maxY = enemyPos.y-1, minY = 1;
        int rangeY = maxY - minY + 1;
        int randomY;

        if(enemyPos.y == 1){//comprovem si la fitxa enemiga esta ja a la base per posar una fitxa al costat
            if(pm.getTaulell().inTheLimit(enemyPos.x-1,enemyPos.y)){
                if(pm.getTaulell().getFitxaCasella(enemyPos.x-1,enemyPos.y) == null) {
                    position = new Point();
                    position.x = enemyPos.x-1;
                    position.y = enemyPos.y;
                }
            }
            if(pm.getTaulell().inTheLimit(enemyPos.x+1, enemyPos.y)){
                if(pm.getTaulell().getFitxaCasella(enemyPos.x+1,enemyPos.y) == null) {
                    position = new Point();
                    position.x = enemyPos.x + 1;
                    position.y = enemyPos.y;
                }
            }
        }else{//si la fitxa no esta a la base col.loquem una fitxa def en una caseya random entre l'enemic i la base
            do {
                randomY = (int) (Math.random() * rangeY) + minY;
                position = new Point();
                position.y = randomY;
                position.x = enemyPos.x;
            }while(pm.getTaulell().getFitxaCasella(position.x,position.y) != null);
        }

        return position;
    }

    @Override
    public boolean siPotPagar(){
        boolean pucPagar = true;

        if(money < COST_MES_BAIX_TROPA){
            pucPagar = false;
        }
        return pucPagar;
    }

    @Override
    public void startDiners() {
        new Thread(new MoneyCounter(this, false)).start();
    }

    @Override
    public void updateMoney() {
        money++;
        System.out.println("False money: " + money);
    }

    @Override
    public void updateVida(int damage) {
        numVides = numVides - damage;
    }

    public void stop(){
        detener = true;
    }

    public int getTropsCounter() {
        return tropsCounter;
    }

    public void setTropsCounter(int tropsCounter) {
        this.tropsCounter = tropsCounter;
    }

    public int getNumVides() {
        return numVides;
    }

    public void setNumVides(int numVides) {
        this.numVides = numVides;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}