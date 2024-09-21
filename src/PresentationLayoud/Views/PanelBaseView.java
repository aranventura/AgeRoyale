package PresentationLayoud.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de generar un Panel Base para poder ser extendido por el resto de vistas.
 * Dispone de un panelFondo que contiene la imágen de fondo por predetermianda.
 * Esta panel base tiene un BorderLayout que su parte Norte contiene otro BorderLayour para poder ajustar en la parte superior
 * los componentes correspondientes de herramientas como ir atrás o botón de config.
 */
public class PanelBaseView extends JPanel {

    private JPanel panelNorte;
    private JPanel panelFondo;
    private JPanel herramientasPanel;

    private ImageIcon imgFondo;
    private ImageIcon imgAgeRoyale;
    private ImageIcon imgCrown;
    private ImageIcon frameIcon;

    private JLabel titulo;

    public static final int AGEROYALE_IMAGE = 0;
    public static final int CROWN_IMAGE = 1;


    /**
     * Constructor pode defecto.
     */
    public PanelBaseView() {
        cargarImagenes();
        configurarFondo();
        configurarNorte();
        add(panelFondo);
        this.setLayout(new BorderLayout());
    }

    /**
     * Método encargado de cargar las imágenes necesarias para ejecutar el panel Base.
     */
    private void cargarImagenes() {

        imgFondo = new ImageIcon(new ImageIcon("src/Icons/Clash_Royale_Background.png").getImage());
        imgAgeRoyale = new ImageIcon(new ImageIcon("src/Icons/titleAgeRoyale_Img.png").getImage().getScaledInstance(680,384,Image.SCALE_SMOOTH));
        frameIcon = new ImageIcon("src/Icons/crown_Img.png");
        imgCrown = new ImageIcon(new ImageIcon("src/Icons/crown_Img.png").getImage().getScaledInstance(134,160,Image.SCALE_DEFAULT));
    }

    /**
     * Método encargado de configurar el panel de fondo con su correspondiente Layout e imagen.
     */
    private void configurarFondo() {
        panelFondo = new ImagePanel(imgFondo);
        panelFondo.setLayout(new BorderLayout());
    }

    /**
     * Método encargado de configurar la parte norte de la ventana que contiene el logo de la aplicación.
     * También setea el Layout de la parte Norte con otro BorderLayout para que el resto de vistas puedan añadir sus herramientas correspondientes.
     * Botón de ir atrás o botón de config.
     */
    private void configurarNorte() {

        titulo = new JLabel();
        titulo.setIcon(imgAgeRoyale);                                           // Asignamos icono al Label del título.
        titulo.setHorizontalAlignment(JLabel.CENTER);                           // Situamos el label en el centro en el eje de las X.
        titulo.setVerticalAlignment(JLabel.CENTER);                             // Situamos el label en el centro en el eje de las Y.
        titulo.setPreferredSize(new Dimension (100,300));           // Dimensionamos el componente para ajustar

        panelNorte = new JPanel(new BorderLayout());                            // BorderLayout apra la parte superior del panel Fondo.
        panelNorte.setOpaque(false);

        herramientasPanel = new JPanel();                                       // Panel de herramientas.
        herramientasPanel.setPreferredSize(new Dimension(80,100));
        herramientasPanel.setOpaque(false);

        panelNorte.add(herramientasPanel,BorderLayout.NORTH);
        panelNorte.add(titulo, BorderLayout.CENTER);
        panelFondo.add(panelNorte,BorderLayout.NORTH);

    }

    /**
     * Getter del panel fondo.
     * @return panelFondo a configurar por el resto de vistas.
     */
    public JPanel getPanelFondo() {
        return panelFondo;
    }

    /**
     * Getter del panel Norte.
     * @return panel Norte del panelFondo.
     */
    public JPanel getPanelNorte() {
        return panelNorte;
    }

    /**
     * Getter del panel de herramientas.
     * @return Panel de herramientas.
     */
    public JPanel getHerramientasPanel() {
        return herramientasPanel;
    }

    /**
     * Setter de la imágen a mostrar en la parte superior de la pantalla.
     * @param imageValue    AGEROYALE_IMAGE: Icono "Age Royale"
     *                      CROWN_IMAGE:     Icon de corona.
     */
    public void setTitleImage(int imageValue) {

        switch (imageValue) {
            case AGEROYALE_IMAGE -> titulo.setIcon(imgAgeRoyale);
            case CROWN_IMAGE -> titulo.setIcon(imgCrown);
            default -> titulo.setIcon(imgAgeRoyale);
        }
    }
}
