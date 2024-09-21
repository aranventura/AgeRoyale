package PresentationLayoud.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase encargada de generar una vista de login.
 */
public class LoginView extends PanelBaseView {

    private JPanel panelCentro;
    private JPanel panelLogin;
    private JPanel loginRegister;

    private JLabel username;
    private JLabel password;

    private JTextField emailTxtF;
    private JTextField passwordTxTF;

    private JButton loginButton;
    private JButton registerButton;

    private ImageIcon loginImg;
    private ImageIcon registerImg;

    private static String username_emailString = "username / e-mail";
    private static String passwordString = "password";
    private static int BUTTON_HEIGHT = 100;
    private static int BUTTON_WIDTH = 350;
    public static final String BTN_LOGIN = "BTN_LOGIN";
    public static final String BTN_REGISTER = "BTN_REGISTER";

    /**
     * Constructor por defecto.
     */
    public LoginView() {
        setTitleImage(PanelBaseView.AGEROYALE_IMAGE);
        cargarImagenes();
        configurarCentro();
    }

    /**
     * Método encargado de cargar las imágenes necesarias para ejecutar la aplicación.
     */
    private void cargarImagenes() {

        loginImg = new ImageIcon(new ImageIcon("src/buttons/login_Button.png").
                getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT));
        registerImg = new ImageIcon(new ImageIcon("src/buttons/register.png").
                getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT));
    }

    /**
     * Método encargado de añadir aquellos paneles correspondientes el centro del BorderLayout principal.
     * Botones y Texfields necesarios para el Login.
     */
    private void configurarCentro() {

        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro,BoxLayout.Y_AXIS));
        panelCentro.setOpaque(false);

        configurarPanelLogin();
        configurarPanelLoginRegister();

        panelCentro.add(labelPadding(50));
        panelCentro.add(panelLogin);
        panelCentro.add(labelPadding(50));
        panelCentro.add(loginRegister);
        getPanelFondo().add(panelCentro,BorderLayout.CENTER);
    }

    /**
     * Método encargado de configurar el panel correspondiente a los comopnentes que se encargan de la escritura de
     * nombre de usuario y contraseña para la realización del login.
     */
    private void configurarPanelLogin() {

        // Componentes del panel.
        username = new LabelConfigurado(username_emailString);
        username.setHorizontalAlignment(JLabel.RIGHT);

        password = new LabelConfigurado(passwordString);
        password.setHorizontalAlignment(JLabel.RIGHT);

        emailTxtF = new TextFieldConfigurado();
        passwordTxTF = new PasswordFieldConfigurado();

        // Panel de login.
        panelLogin = new JPanel();
        panelLogin.setOpaque(false);
        panelLogin.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelLogin.setAlignmentX(JPanel.CENTER_ALIGNMENT);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(0,0,20,50);    // El padding externo del componente.

        // Primera fila - username/e-mail + TextField.
        gbc.gridwidth = 1;                                          // EL número de celdas que utilizará el componente.
        panelLogin.add(username, gbc);                              // Añadimos label.
        gbc.gridwidth = GridBagConstraints.REMAINDER;               // Es el último componente de las columnas.
        panelLogin.add(emailTxtF, gbc);                             // Añadimos textfield.

        // Segunda fila - password + TextField.
        gbc.gridwidth = 1;                                          // EL número de celdas que utilizará el componente.
        panelLogin.add(password, gbc);                              // Añadimos label.
        gbc.gridwidth = GridBagConstraints.REMAINDER;               // Es el último componente de las columnas.
        panelLogin.add(passwordTxTF, gbc);                          // Añadimos textfield.

        panelLogin.setMaximumSize(panelLogin.getPreferredSize());
    }

    /**
     * Método encargado de configurar el panel que contiene el botón de login y register.
     */
    private void configurarPanelLoginRegister() {

        // Componentes.
        loginButton = new JButton();
        loginButton.setActionCommand(BTN_LOGIN);
        loginButton.setHorizontalAlignment(JButton.CENTER); // Set button on center.

        registerButton = new JButton();
        registerButton.setActionCommand(BTN_REGISTER);
        registerButton.setHorizontalAlignment(JButton.CENTER);             // Set button on center.

        // Imagenes a los botones.
        loginButton.setIcon(loginImg);              // Seteamos icono.
        loginButton.setBorderPainted(false);        // Transparencia ON.
        loginButton.setContentAreaFilled(false);

        registerButton.setIcon(registerImg);        // Setemoas icono.
        registerButton.setBorderPainted(false);     // Transparenia ON.
        registerButton.setContentAreaFilled(false);

        // Creamos panel y añadimos sus componentes.
        loginRegister = new JPanel();
        loginRegister.setLayout(new FlowLayout());
        loginRegister.setOpaque(false);

        loginRegister.add(loginButton);             // Añadimos el botón de login
        loginRegister.add(registerButton);          // Añadimos el botón de register
    }

    /**
     * Método encargado de generar un label vacío con un valor de Padding vertical determinado.
     * @param vertical_padding  Altura determinada de padding para el label vacío.
     * @return  Label vacció con una altura especificada.
     */
    private JLabel labelPadding(int vertical_padding) {
        JLabel padding = new JLabel();
        padding.setOpaque(false);
        padding.setPreferredSize(new Dimension(10,vertical_padding));
        return padding;
    }

    /**
     * Método encargado de registrar el controlador a los componentes.
     * @param controller Controlador a registrar.
     */
    public void registerController(ActionListener controller) {
        loginButton.addActionListener(controller);
        registerButton.addActionListener(controller);
    }

    /**
     * Método encargado de resetear los TextFields de la vista.
     */
    public void resetTextFields() {
        emailTxtF.setText("");
        passwordTxTF.setText("");
    }

    /**
     * Getter del texto de e-mail
     * @return  JTextField que contiene el texto del e-mail.
     */
    public JTextField getEmailTxtF() {
        return emailTxtF;
    }

    /**
     * Getter del texto del password.
     * @return  JTextField que contiene el texto del password.
     */
    public JTextField getPasswordTxTF() {
        return passwordTxTF;
    }
}
