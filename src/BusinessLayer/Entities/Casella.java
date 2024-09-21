package BusinessLayer.Entities;

import java.awt.*;

public class Casella {
    private int x;
    private int y;
    private Fitxa fitxa;

    public Casella(int x, int y, Fitxa fitxa) {
        this.x = x;
        this.y = y;
        this.fitxa = fitxa;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Fitxa getFitxa() {
        return fitxa;
    }

    public void setFitxa(Fitxa fitxa) {
        this.fitxa = fitxa;
    }
}
