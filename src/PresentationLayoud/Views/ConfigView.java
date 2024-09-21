package PresentationLayoud.Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel de configuración que permite desloggear y eliminar usuario de la base de datos.
 */
public class ConfigView extends PanelBaseView {

    private JPanel panelCentro;

    private ImageIcon logoutIcon;
    private ImageIcon deleteAccountIcon;
    private ImageIcon settingsIcon;

    private JButton logoutButton;
    private JButton deleteAccountButton;
    JButton configButton;

    private static int BUTTON_HEIGHT = 100;
    private static int BUTTON_WIDTH = 350;
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_DELETE_ACCOUNT = "BTN_DELETE_ACCOUNT";
    public static final String BTN_CONFIG = "BTN_CONFIG";


    /**
     * Constructor del panel de configuración.
     */
    public ConfigView() {
        setTitleImage(PanelBaseView.CROWN_IMAGE);
        cargarImagenes();
        configurarNorte();
        configurarCentro();
    }

    /**
     * Método encargado de cargar las imágenes necesarias para ejecutar la vista.
     */
    public void cargarImagenes() {

        logoutIcon = new ImageIcon(new ImageIcon("src/buttons/logout_Button.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT));
        deleteAccountIcon = new ImageIcon(new ImageIcon("src/buttons/DeleteAccount_Button.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT));
        settingsIcon = new ImageIcon(new ImageIcon("src/buttons/settings_Button.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    }

    /**
     * Método encargado de configurar la vista norte de la pantalla con sus correspondientes botones.
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
     * Método encargado de añadir aquellos paneles correspondientes el centro del BorderLayout principal.
     * Botón de loggout y botón de eliminar cuenta.
     */
    private void configurarCentro() {

        // Panel
        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro,BoxLayout.Y_AXIS));
        panelCentro.setOpaque(false);

        // Botones
        logoutButton = new JButton();
        deleteAccountButton = new JButton();

        logoutButton.setIcon(logoutIcon);                           // Seteamos icono.
        logoutButton.setActionCommand(BTN_LOGOUT);
        logoutButton.setBorderPainted(false);                       // Transparenia ON.
        logoutButton.setContentAreaFilled(false);

        deleteAccountButton.setIcon(deleteAccountIcon);             // Seteamos icono.
        deleteAccountButton.setActionCommand(BTN_DELETE_ACCOUNT);
        deleteAccountButton.setBorderPainted(false);                // Transparenia ON.
        deleteAccountButton.setContentAreaFilled(false);

        // Alineamiento
        logoutButton.setHorizontalAlignment(JButton.CENTER);            // Set button en el centro.
        deleteAccountButton.setHorizontalAlignment(JButton.CENTER);     // Set button en el centro.
        logoutButton.setAlignmentX(CENTER_ALIGNMENT);
        deleteAccountButton.setAlignmentX(CENTER_ALIGNMENT);

        // Añadimos componentes
        panelCentro.add(logoutButton);
        panelCentro.add(Box.createVerticalStrut(35));       // Añadimos espacio vertical.
        panelCentro.add(deleteAccountButton);
        panelCentro.add(Box.createVerticalStrut(35));       // Añadimos espacio vertical.
        getPanelFondo().add(panelCentro,BorderLayout.CENTER);

    }

    /**
     * Método encargado de registrar el controlador a los componentes.
     * @param controller Controlador a registrar.
     */
    public void registerController(ActionListener controller) {
        logoutButton.addActionListener(controller);
        deleteAccountButton.addActionListener(controller);
        configButton.addActionListener(controller);

    }
}
