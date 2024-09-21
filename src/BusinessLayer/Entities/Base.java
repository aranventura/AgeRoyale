package BusinessLayer.Entities;

import BusinessLayer.Model.CtrlListenner;
import BusinessLayer.Model.PartidaManager;

import java.awt.*;


/**
 * Clase que extiende de Fitxa, no implementa ningun metodo ya que está formado para recrear la base (no tiene caracter)
 *
 *
 */
public class Base extends Fitxa{

    private static final String nomTropa = "Base";
    private static final int vida = 10000;

    public Base(boolean usuari, int posX, int posY, PartidaManager pm) {
        super(nomTropa, 0, usuari, vida, posX, posY, 0, 0, 1, null, pm, false, false);
    }
}
