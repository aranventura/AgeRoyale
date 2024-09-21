package PresentationLayoud.Views;

import BusinessLayer.Entities.Partida;
import PresentationLayoud.Controllers.CtrlPartidasGuardadas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Cñase encargada de generar la vista de partidas guardadas.
 */
public class SavedGamesView extends PanelBaseView {

    private JPanel panelCentro;
    private JButton atrasButton;
    private JButton configButton;

    private ImageIcon settingsIcon;
    private ImageIcon antesIcon;
    private JScrollPane sp;

    public static final String BTN_CONFIG = "BTN_RANKING";
    public static final String BTN_ATRAS = "BTN_ATRAS";


    /**
     * Cosnstructor por defecto.
     */
    public SavedGamesView() {
        panelCentro = new JPanel();
        sp = new JScrollPane();
        setTitleImage(PanelBaseView.CROWN_IMAGE);
        cargarImagenes();
        configurarNorte();
    }

    /**
     * Método encargado de cargar las imágenes necesarias para ejecutar la vista.
     */
    private void cargarImagenes() {
        settingsIcon = new ImageIcon(new ImageIcon("src/buttons/settings_Button.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        antesIcon = new ImageIcon(new ImageIcon("src/buttons/flecha_Button.png").getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));
    }

    /**
     * Método encargado de configurar la parte norte de la partida con su icono y componentes de herramientas a utilizar.
     */
    private void configurarNorte() {

        getHerramientasPanel().setLayout(new GridLayout(0,2));

        // Botón atrás.
        atrasButton = new JButton();
        atrasButton.setPreferredSize(new Dimension(80,80));             // Dimension
        atrasButton.setHorizontalAlignment(JButton.LEFT);                           // Alineamiento LEFT
        atrasButton.setIcon(antesIcon);                                             // Seteamos icono
        atrasButton.setBorderPainted(false);                                        // Transparenia ON.
        atrasButton.setContentAreaFilled(false);                                    // Vaciamos fondo.
        atrasButton.setActionCommand(BTN_ATRAS);                                    // Añadimos comando de acción.
        getHerramientasPanel().add(atrasButton);

        // Botón de ajustes.
        configButton = new JButton();                                               // Nuevo botón.
        configButton.setIcon(settingsIcon);                                         // Seteamos icono
        configButton.setBorderPainted(false);                                       // Transparencia ON.
        configButton.setHorizontalAlignment(JButton.RIGHT);                         // Situamos el botón a la derecha.
        configButton.setMargin(new Insets(0,0,0,20));          // Ajustamos padding del botón.
        configButton.setActionCommand(BTN_CONFIG);                                  // Añadimos comando de acción.
        getHerramientasPanel().add(configButton);                                   // Añadimos el botón al panel norte del panel norte del Frame.

    }

    /**
     * Método encargado de actualizar las partidas mostradas en el panel Central.
     * @param partidas      Lista de partidas a mostrar.
     * @param controller    Controlador a registrar.
     */
    public void configurarCentroGrid(ArrayList<Partida> partidas, CtrlPartidasGuardadas controller) {

        panelCentro.removeAll();
        getPanelFondo().remove(sp);
        panelCentro.setOpaque(false);

        System.out.println("Size: " + partidas.size());
        if(partidas.size() != 0) {
            panelCentro.setLayout(new GridLayout(partidas.size(),0));
        } else {
            panelCentro.setLayout(new GridLayout(1,0));
        }

        for (int i = 0; i < partidas.size(); i++) {

            SavedGamePanel panelGame = new SavedGamePanel(partidas.get(i).getNomPartida(),partidas.get(i).getData(), partidas.get(i).isGuanyador(),i);
            panelGame.addMouseListener(controller);
            panelCentro.add(panelGame);
        }

        panelCentro.revalidate();
        panelCentro.repaint();
        getPanelFondo().revalidate();
        getPanelFondo().repaint();

        System.out.println();
        sp = new JScrollPane(panelCentro);
        sp.setBorder(new EmptyBorder(20,100,100,100));
        sp.setOpaque(false);
        getPanelFondo().add(sp, BorderLayout.CENTER);

    }

    /**
     * Método encargado de registrar el controlador de la vista.
     * @param controller Controlador que gestoina el ranking.
     */
    public void registerController(ActionListener controller) {
        configButton.addActionListener(controller);
        atrasButton.addActionListener(controller);
    }
}
