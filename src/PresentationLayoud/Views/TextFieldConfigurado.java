package PresentationLayoud.Views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Método encargado de generar un JTextField configurado con los parámetros generales necesarios a nivel de vista.
 */
public class TextFieldConfigurado extends JTextField {

    private Font fuenteTextField;
    private static int DEFAULT_SIZE = 16;


    /**
     * Constructor general del TextField configurado.
     */
    public TextFieldConfigurado() {
        this.setColumns(20);
        fuenteTextField = new Font("Supercell-Magic", Font.PLAIN, DEFAULT_SIZE);
        setFont(fuenteTextField);                                                   // Fuente a utilizar en el TextField.
        setForeground(Color.GRAY);                                                  // Color de fondo.
        setPreferredSize(new Dimension(450,40));                        // Dimensión del TextField.

        Border line = BorderFactory.createLineBorder(Color.DARK_GRAY,2);    // Borde del TextField.
        Border empty = new EmptyBorder(0, 10, 0, 10);           // Margen interior del TextField.
        CompoundBorder border = new CompoundBorder(line, empty);                     // Creamos CompoundBorder.
        setBorder(border);

        // Alineamos Textfield.
        setHorizontalAlignment(JTextField.LEFT);
        setAlignmentX(CENTER_ALIGNMENT);
    }
}
