package PresentationLayoud.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de generar un panel con una imágen de fondo.
 */
class ImagePanel extends JPanel {

    private Image img;

    /**
     * Constructor para generar el panel a partir de un "ImageIcon"
     * @param img    Imágen a mostrar de fondo en el panel.
     */
    public ImagePanel(ImageIcon img) {
        this(img.getImage());
    }

    /**
     * Constructor para generar el panel a partir de un "Image"
     * @param img   Imágen a mostrar de fondo en el panel.
     */
    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    /**
     * Método encargado de dibujar el componente.
     * @param g Graficos.
     */
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}