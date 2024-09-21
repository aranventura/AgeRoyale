package PresentationLayoud.Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase encargada de generar una vista del menú principal.
 */
public class MenuPrincipalView extends PanelBaseView {

    private JPanel panelCentro;

    private ImageIcon newGameIcon;
    private ImageIcon savedGamesIcon;
    private ImageIcon rankingIcon;
    private ImageIcon settingsIcon;

    private JButton newGameButton;
    private JButton savedGamesButton;
    private JButton rankingButton;
    private JButton configButton;

    private static int BUTTON_HEIGHT = 100;
    private static int BUTTON_WIDTH = 350;
    public static final String BTN_NEW_GAME = "BTN_NEW_GAME";
    public static final String BTN_SAVED_GAME = "BTN_SAVED_GAME";
    public static final String BTN_RANKING = "BTN_RANKING";
    public static final String BTN_CONFIG = "BTN_CONFIG";


    /**
     * Constructor por defecto.
     */
    public MenuPrincipalView() {
        setTitleImage(PanelBaseView.AGEROYALE_IMAGE);
        cargarImagenes();
        configurarNorte();
        configurarCentro();
    }

    /**
     * Método encargado de cargar las imágenes necesarias para ejecutar la aplicación.
     */
    private void cargarImagenes() {

        newGameIcon = new ImageIcon(new ImageIcon("src/buttons/newgame_Button.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT));
        savedGamesIcon = new ImageIcon(new ImageIcon("src/buttons/savedgames_Button.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT));
        rankingIcon = new ImageIcon(new ImageIcon("src/buttons/ranking_Button.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT));
        settingsIcon = new ImageIcon(new ImageIcon("src/buttons/settings_Button.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    }

    /**
     * Método encargado de configurar el panel norte correctamente con su botón de configuración.
     */
    private void configurarNorte() {

        getHerramientasPanel().setLayout(new FlowLayout(FlowLayout.TRAILING));
        configButton = new JButton();                                               // Nuevo botón.
        configButton.setActionCommand(BTN_CONFIG);
        configButton.setIcon(settingsIcon);                                         // Seteamos icono
        configButton.setBorderPainted(false);                                       // Transparencia ON.
        configButton.setHorizontalAlignment(JButton.RIGHT);                         // Situamos el botón a la derecha.
        configButton.setMargin(new Insets(0,0,0,20));          // Ajustamos padding del botón.
        getHerramientasPanel().add(configButton);                                   // Añadimos el botón al panel norte del panel norte del Frame.
    }

    /**
     * Método encargado de añadir aquellos componentes correspondientes el centro del BorderLayout principal.
     * Contendrá los botones de iniciar partida, ranking y mostrar partidas guardadas.
     */
    private void configurarCentro() {

        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro,BoxLayout.Y_AXIS));
        panelCentro.setOpaque(false);

        newGameButton = new JButton();
        rankingButton = new JButton();
        savedGamesButton = new JButton();

        // Inicializar botones.
        newGameButton.setIcon(newGameIcon);        // Seteamos icono.
        newGameButton.setActionCommand(BTN_NEW_GAME);
        newGameButton.setBorderPainted(false);     // Transparenia ON.
        newGameButton.setContentAreaFilled(false);

        rankingButton.setIcon(rankingIcon);        // Seteamos icono.
        rankingButton.setActionCommand(BTN_RANKING);
        rankingButton.setBorderPainted(false);     // Transparenia ON.
        rankingButton.setContentAreaFilled(false);

        savedGamesButton.setIcon(savedGamesIcon);     // Seteamos icono.
        savedGamesButton.setActionCommand(BTN_SAVED_GAME);
        savedGamesButton.setBorderPainted(false);     // Transparenia ON.
        savedGamesButton.setContentAreaFilled(false);

        // Alinear botones.
        newGameButton.setHorizontalAlignment(JButton.CENTER);                // Set button on center.
        savedGamesButton.setHorizontalAlignment(JButton.CENTER);             // Set button on center.
        rankingButton.setHorizontalAlignment(JButton.CENTER);                // Set button on center.
        newGameButton.setAlignmentX(CENTER_ALIGNMENT);
        savedGamesButton.setAlignmentX(CENTER_ALIGNMENT);
        rankingButton.setAlignmentX(CENTER_ALIGNMENT);

        // Añadir botones.
        panelCentro.add(newGameButton);
        panelCentro.add(Box.createVerticalStrut(35));       // Añadimos espacio vertical.
        panelCentro.add(rankingButton);
        panelCentro.add(Box.createVerticalStrut(35));       // Añadimos espacio vertical.
        panelCentro.add(savedGamesButton);
        getPanelFondo().add(panelCentro,BorderLayout.CENTER);
    }

    /**
     * Método encargado de registrar el controlador a los componentes.
     * @param controller Controlador a registrar.
     */
    public void registerController(ActionListener controller) {
        newGameButton.addActionListener(controller);
        rankingButton.addActionListener(controller);
        savedGamesButton.addActionListener(controller);
        configButton.addActionListener(controller);
    }

    /**
     * Método que muestra la vista.
     */
    public void start() {
        setVisible(true);
    }

}
