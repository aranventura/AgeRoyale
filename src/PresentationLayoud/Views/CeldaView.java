package PresentationLayoud.Views;

import javax.swing.*;

/**
 * Clase encargada de generar una celda de la vista Partida,
 */
public class CeldaView extends JPanel {

    private JLabel imagenCelda;
    private int i;
    private int j;
    private boolean ocupada;

    /**
     * Constructor de Celda.
     * @param i  Coordenada i de la celda.
     * @param j  Coordenada j de la celda.
     */
    public CeldaView(int i, int j) {
        imagenCelda = new JLabel();
        this.i = i;
        this.j = j;
        this.ocupada = false;
        imagenCelda.setIcon(new ImageIcon());
        imagenCelda.setHorizontalAlignment(JLabel.LEFT);
        imagenCelda.setVerticalAlignment(JLabel.CENTER);
        imagenCelda.setAlignmentX(JLabel.LEFT);
        imagenCelda.setVerticalAlignment(JLabel.CENTER);
        add(imagenCelda);
    }

    /**
     * Setter del Icon de la celda.
     * @param icon Icono a situar
     */
    public void setIconSquare(ImageIcon icon) {
        imagenCelda.setIcon(icon);
    }

    /**
     * Getter del Icon de la celda.
     * @return  Icono
     */
    public Icon getIconSquare(){
        return imagenCelda.getIcon();
    }

    /**
     * Getter de la coordenada i de la celda.
     * @return  i
     */
    public int getI() {
        return i;
    }

    /**
     * Setter de la coordenada i de la celda.
     * @param i Coordenada i
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     * Getter de la coordenada J de la celda.
     * @return j
     */
    public int getJ() {
        return j;
    }

    /**
     * Setter de la coordenada J de la celda.
     * @param j Coordenada j
     */
    public void setJ(int j) {
        this.j = j;
    }

    /**
     * Getter de la celda ocupada.
     * @return isOcupada
     */
    public boolean isOcupada() {
        return ocupada;
    }

    /**
     * Setter de la celda ocupada.
     * @param ocupada Booleano de ocupado.
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
