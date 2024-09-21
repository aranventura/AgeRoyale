package PresentationLayoud.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de generar un gráfica de un StatusBar.
 */
public class StatusBar extends JPanel {

    private int valorX;
    private Color color;
    private static int MAX_HEIGHT = 350;


    /**
     * Constructor general del gráfico selecciondo el color que deseamos que tenga.
     * @param color Color de la StatusBar.
     */
    public StatusBar(Color color) {
        this.color = color;
        this.valorX = MAX_HEIGHT;
        this.setOpaque(false);
    }

    /**
     * Método encargado de setear el valor del gráfico a mostrar.
     * @param valor Valor a mostrar.
     */
    public void setValor(int valor) {
        valorX = ((valor * MAX_HEIGHT)/100);
        repaint();
        revalidate();
    }

    /**
     * Método encargado de pintar el gráfico.
     * @param g Gráfico.
     */
    public void paint(Graphics g) {

        g.setColor(color);
        g.fillRect(((int)this.getSize().getWidth()/2)-25,MAX_HEIGHT-valorX+20,50, valorX);
        //g.fillRoundRect(10,10+valorX,50,(int) altura_max - valorX, 0,0);
    }
}
