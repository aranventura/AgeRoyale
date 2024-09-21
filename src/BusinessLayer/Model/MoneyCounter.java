package BusinessLayer.Model;


import com.sun.source.tree.IdentifierTree;

public class MoneyCounter implements Runnable{

    private IJugador listenner;
    private boolean detener;

    /**
     * Constructor de la classe MoneyCounter
     * @param listenner parametre que conte els metodes per que la classe es conecti amb l'usuari i la maquina
     * @param detener parametre que indica si s'ha fet stop a la partida
     */
    public MoneyCounter(IJugador listenner, boolean detener) {
        this.listenner = listenner;
        this.detener = detener;
    }

    /**
     * Funcio que ve implementada de Runnable que te un comportament determinant
     * En aquest cas el funcionament de MoneyCounter
     */
    public void run() {
        try {
            while(!detener) {
                int sleepTime = 1500;
                Thread.sleep(sleepTime);
                listenner.updateMoney();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metode que atura el proces de run de MoneyCounter
     */
    public void stop(){
        detener = true;
    }
}
