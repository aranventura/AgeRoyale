package BusinessLayer.Model;

public interface IJugador {

    /**
     * Funcio que llença els fils dels diners de l'usuari i de la maquina
     */
    public void startDiners();

    /**
     * Metode que va actualitzant els diners de l'usuari i de la maquina
     */
    public void updateMoney();

    /**
     * Metode que va actualizant la vida de l'usuari i del la maquina cuan els ataquen
     * @param damage paramatre d'entrada que conte el damage que estan rebent
     */
    public void updateVida(int damage);

    /**
     * Metode que comprova si l'usuari i la maquina poden pagar les fitxes
     * @return retorna un boolea que indica si poden o no poden pagar
     */
    boolean siPotPagar();

}
