package BusinessLayer.Entities;

public class Taulell {
    private Casella[][] taulell;
    //money maquina
    //money user
    private final int x = 7;   //columnas
    private final int y = 10; //filas

    //Comentari important: cal establir les Bases, tal fila base maquina, en tota la fila es la base
    // (penasr com posar la base com a conjunt de tota la fila i no fragmentat en caselles)

    public Taulell() {
        this.taulell = new Casella[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                taulell[i][j] = new Casella(i, j, null);
            }
        }

        //Marcar bases
    }

    /**
     * Método que devuelve la ficha de una casilla en concreto
     * @param x
     * @param y
     * @return
     */
    public Fitxa getFitxaCasella(int x, int y) {
        Casella c = taulell[y][x];
        return c.getFitxa();
    }

    /**
     * Método que setea una ficha en el tablero según su posición
     * @param f
     */
    public void setFitxaSegonsPosicioFitxa(Fitxa f) {
        taulell[f.getPosY()][f.getPosX()].setFitxa(f);
    }

    /**
     * Función que mira si dada una posición está dentro los limites o no
     * @param x
     * @param y
     * @return
     */
    public boolean inTheLimit(int x, int y) {
        boolean inTheLimits = false;

        if (x >= 0 && x < this.x) {
            if (y >= 0 && y < this.y) {
                inTheLimits = true;
            }
        }

        return inTheLimits;
    }

    public boolean inMyHalf(int x, int y) {
        boolean inMyHalf = false;

        if (inTheLimit(x, y)) {
            if (x <= 4) {
                inMyHalf = true;
            }
        }

        return inMyHalf;
    }

    public Casella[][] getTaulell() {
        return taulell;
    }

    public void setTaulell(Casella[][] taulell) {
        this.taulell = taulell;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
