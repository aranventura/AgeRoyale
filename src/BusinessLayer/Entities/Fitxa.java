package BusinessLayer.Entities;

import BusinessLayer.Model.PartidaManager;

import java.awt.*;

public abstract class Fitxa implements Runnable{
    private String nom;
    private int cost;
    private boolean usuari;
    private boolean caracter_defensiu;

    private int vida;
    private int posX;
    private int posY;
    private int ataque;
    private int defensa;

    private Fitxa rival;

    private int rangAccio;
    private PartidaManager pm;
    private boolean detener;


    public Fitxa(String nom, int cost, boolean usuari, int vida, int posX, int posY, int rangAccio, int ataque, int defensa, Fitxa rival, PartidaManager pm, boolean caracter_defensiu, boolean detener) {
        this.nom = nom;
        this.cost = cost;
        this.usuari = usuari;
        this.vida = vida;
        this.posX = posX;
        this.posY = posY;
        this.rangAccio = rangAccio;
        this.ataque = ataque;
        this.defensa = defensa;
        this.rival = rival;
        this.pm = pm;
        this.caracter_defensiu = caracter_defensiu;
        this.detener = detener;
    }

    /**
     * Método que se extrae de la implementacuión de runneable, en este caso consiste en el comportamiento nde la ficha y como actua
     */
    @Override
    public void run() {
        Point old_casella = new Point();
        Point new_casella = new Point();
        rival = null;
        Fitxa aux = null;

        while(esViu() && !detener) {
            old_casella.setLocation(getPosX(), getPosY());

            if(hiHaEnemic()) {
                //ataca
                ataca();
                pm.rivalAtacat(this.rival);

                if(!rival.esViu()) {
                    System.out.println("He matat al meu rival puahahahahahaha" );
                    pm.enemicMort(rival);
                    rival = null;
                }

                long sleepTime = 1000;
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    //System.err.println("User " + name + " couldn't wait for " + sleepTime);
                }

            } else if(esPotMoure() && rival == null) {

                long sleepTime = 2500;
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.err.println(" Exeption mou ");
                }

                if(hiHaEnemic() && rival.esViu()) {
                    //ataca
                    ataca();
                    pm.rivalAtacat(this.rival);
                    if(!rival.esViu()) {
                        aux = rival;
                        rival = null;
                        pm.enemicMort(aux);
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //System.err.println("User " + name + " couldn't wait for " + sleepTime);
                    }

                } else {
                    //mou i guardar nova posicio
                    if(!caracter_defensiu){
                        mou();
                        new_casella.setLocation(getPosX(), getPosY());
                        pm.moveFitxa(old_casella, new_casella);
                    }
                }
            }
        }
    }

    /**
     * Metodo para que la ficha quite vida a su rival
     */
    public void ataca() {
        int damage = getAtaque() / getRival().getDefensa();
        if (rival.getNom().equals("Base")){
            pm.baseAtacada(damage, usuari);
        } else {
            getRival().setVida(getRival().getVida() - (damage * 4));
        }
    }

    /**
     * Método que mueve la ficha de posición
     */
    public void mou() {
        int pos = getPosY();

        if (isUsuari()) {
            pos = pos - 1;
        } else {
            pos = pos + 1;
        }

        setPosY(pos);
    }

    /**
     * Método de comprovación para saber si una ficha puede moverse o no
     * @return True si puede moverse, False si no puede
     */
    public boolean esPotMoure() {
        if (isUsuari()) {
            if (getPartidaManager().getTaulell().inTheLimit(getPosX(), getPosY() - 1) && getPartidaManager().getTaulell().getFitxaCasella(getPosX(), getPosY() - 1) == null) {
                return true;
            }
        } else {
            if (getPartidaManager().getTaulell().inTheLimit(getPosX(), getPosY() + 1) && getPartidaManager().getTaulell().getFitxaCasella(getPosX(), getPosY() + 1) == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que mira si la ficha tiene algun enemgio en su rango de acción
     * @return True si hay enemig, false si no lo hay
     */
    public boolean hiHaEnemic() {
        boolean enemic = false;
        int rang_accio;

        if (this.usuari) {
            rang_accio = -this.rangAccio;
        } else {
            rang_accio = this.rangAccio;
        }

        //mirar dreta(U) / esquerra(M)
        if(esPotAtacar(rang_accio, 0)) {
            enemic = true;
            setRival(getPartidaManager().getTaulell().getFitxaCasella(getPosX() + rang_accio, getPosY()));
            //rival.setRival(this);

            //mirar esquerra
        } else if(esPotAtacar(-rang_accio, 0)){
            enemic = true;
            setRival(getPartidaManager().getTaulell().getFitxaCasella(getPosX() - rang_accio, getPosY()));
            //rival.setRival(this);

            //mirar endavant
        } else if(esPotAtacar(0, rang_accio)){
            enemic = true;
            setRival(getPartidaManager().getTaulell().getFitxaCasella(getPosX(), getPosY() + rang_accio));
            //rival.setRival(this);
        }

        return enemic;
    }

    /**
     * Comprovación de límites
     * @param rangAccioX
     * @param rangAccioY
     * @return boolean
     */
    private boolean comprovarLimit(int rangAccioX, int rangAccioY){
        return getPartidaManager().getTaulell().inTheLimit(getPosX() + rangAccioX, getPosY() + rangAccioY);
    }

    /**
     * Comprovación de si una casilla está libre o no
     * @param rangAccioX
     * @param rangAccioY
     * @return boolean
     */
    private boolean comprovarSiHiHaAlgu(int rangAccioX, int rangAccioY){
        return getPartidaManager().getTaulell().getFitxaCasella(getPosX() + rangAccioX, getPosY() + rangAccioY) != null;
    }

    /**
     * Comprueva si es la ficha que tiene en rango es enemgio o no
     * @param rangAccioX
     * @param rangAccioY
     * @return boolean
     */
    private boolean comprovarSiEsEnemic(int rangAccioX, int rangAccioY){
        return getPartidaManager().getTaulell().getFitxaCasella(getPosX() + rangAccioX, getPosY() + rangAccioY).isUsuari() != usuari;
    }

    /**
     * Método que comrpueva si la ficha puede atacar o no
     * @param rangAccioX
     * @param rangAccioY
     * @return
     */
    private boolean esPotAtacar(int rangAccioX, int rangAccioY){
        if (comprovarLimit(rangAccioX, rangAccioY) && comprovarSiHiHaAlgu(rangAccioX, rangAccioY) && comprovarSiEsEnemic(rangAccioX, rangAccioY)){
            return true;
        }
        return false;
    }

    public void stop(){
        detener = true;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isUsuari() {
        return usuari;
    }

    public void setUsuari(boolean usuari) {
        this.usuari = usuari;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getRangAccio() {
        return rangAccio;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setRangAccio(int rangAccio) {
        this.rangAccio = rangAccio;
    }

    public Fitxa getRival() {
        return rival;
    }

    public void setRival(Fitxa rival) {
        this.rival = rival;
    }

    public boolean esViu(){
        boolean viu = false;
        if (this.vida > 0){
            viu = true;
        }
        return viu;
    }

    public PartidaManager getPartidaManager() {
        return pm;
    }

    public void setPartidaManager(PartidaManager pm) {
        this.pm = pm;
    }

    public boolean isCaracter_defensiu() {
        return caracter_defensiu;
    }

    public void setCaracter_defensiu(boolean caracter_defensiu) {
        this.caracter_defensiu = caracter_defensiu;
    }

    public PartidaManager getPm() {
        return pm;
    }

    public void setPm(PartidaManager pm) {
        this.pm = pm;
    }
}
