package BusinessLayer.Entities;

import BusinessLayer.Model.CtrlListenner;
import BusinessLayer.Model.PartidaManager;

import java.awt.*;


/**
 * Clase que extiende de Fitxa, implementa métodos de la clase de la cual extiende, y cambia valores según su especificación
 *
 *
 */
public class Arquera extends Fitxa{

    private static final String nomTropa = "Arquera";
    private static final int cost = 8;
    private static final int vida = 100;
    private static final int rang = 1;
    private static final int atac = 60;
    private static final int defensa = 25;

    public Arquera(boolean usuari, int posX, int posY, PartidaManager pm) {
        super(nomTropa, cost, usuari, vida, posX, posY, rang, atac, defensa, null, pm, false, false);
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void ataca() {
        super.ataca();
    }

    @Override
    public void mou() {
        super.mou();
    }

    @Override
    public boolean esPotMoure() {
        return super.esPotMoure();
    }

    @Override
    public boolean hiHaEnemic() {
        return super.hiHaEnemic();
    }

    @Override
    public void stop() {
        super.stop();
    }

}