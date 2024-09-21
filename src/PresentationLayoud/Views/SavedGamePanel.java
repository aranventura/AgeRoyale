package PresentationLayoud.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de generar un panel en el que se situará la información de la partida en el listado de partidas del usuario.
 */
public class SavedGamePanel extends JPanel {

    private JLabel text;
    private Color green = new Color(113,240,96);
    private Color red = new Color(255,74,74);
    private int savedGameIndex;



    /**
     * Constructo del panel.
     * @param nomPartida    Nombre de la partida.
     * @param dataPartida    Nombre del Usuario.
     * @param win           Parámetro de si ha ganado la partida o no.
     * @param i             i para saber al panel que corresponde en la lista de paneles.
     */
    public SavedGamePanel(String nomPartida, String dataPartida, boolean win, int i) {

        //this.nomPartida = nomPartida;
        //this.nomUsuario = nomUsuario;
        //this.win = win;

        savedGameIndex = i;

        if(win) {
            setBackground(green);
        } else {
            setBackground(red);
        }

        text = new LabelConfigurado(nomPartida + " - " + dataPartida);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        text.setPreferredSize(new Dimension(800,80));
        text.setMaximumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),80));
        setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
        add(text);
    }

    /**
     * Getter del panel.
     * @return índice asignado al panel.
     */
    public int getSavedGameIndex() {
        return savedGameIndex;
    }
}
