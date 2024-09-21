package PresentationLayoud.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de generar un JLabel configurado a medida para utilizar en las vistas.
 */
public class LabelConfigurado extends JLabel {

    private Font fuenteLabel;
    private static int DEFAULT_SIZE = 20;

    /**
     * Método encargado de generar un JLabel con la fuente de texto y el color de la letra configurado.
     * Fuente de texto: Supercell-Magic | Color: Blanco
     * @param texto Texto a incluir en el JLabel
     *
     */
    public LabelConfigurado(String texto) {
        fuenteLabel = new Font("Supercell-Magic",Font.PLAIN, DEFAULT_SIZE);
        setFont(fuenteLabel);
        setText(texto);
        setForeground(Color.WHITE);
    }
}
