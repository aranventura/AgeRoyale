package BusinessLayer.Model;

import BusinessLayer.Model.ITime;
import BusinessLayer.Model.PartidaManager;
import com.sun.source.tree.IdentifierTree;

public class TimeCounter implements Runnable{
    private PartidaManager pm;
    private boolean detener;

    public TimeCounter(PartidaManager pm, boolean detener) {
        this.pm = pm;
        this.detener = detener;
    }

    /**
     * Método implementado proveniente de runnable que cuenta el tiempo
     */
    @Override
    public void run() {
        while(!detener) {
            try {
                int sleepTime = 1000;
                Thread.sleep(sleepTime);
                pm.updateTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método que detiene el la ejecución del thread
     */
    public void stop(){
        detener = true;
    }
}
