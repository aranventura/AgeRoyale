package PresentationLayoud.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterView extends PanelBaseView {

    private JPanel panelCentro;
    private JPanel panelLogin;
    private JPanel loginRegister;

    private JLabel username;
    private JLabel email;
    private JLabel password;
    private JLabel repeat_password;

    private JTextField usernameTxtF;
    private JTextField emailTxtF;
    private JPasswordField passwordTxtF;
    private JPasswordField repeatpasswordTxtF;

    private JButton registerButton;
    private JButton atrasButton;

    private ImageIcon registerImg;
    private ImageIcon antesIcon;

    private static String usernameString = "username";
    private static String emailString = "email";
    private static String passwordString = "password";
    private static String passwordrepeatString = "repeat password";
    private static int BUTTON_HEIGHT = 100;
    private static int BUTTON_WIDTH = 350;
    public static final String BTN_REGISTER = "BTN_REGISTER";
    public static final String BTN_ATRAS = "BTN_ATRAS";


    /**
     * Constructor por defecto.
     */
    public RegisterView() {

        setTitleImage(PanelBaseView.AGEROYALE_IMAGE);
        cargarImagenesRegister();
        configurarCentro();
        configurarNorte();
    }

    private void configurarNorte() {

        // la altura de la parte norte del BorderLayout.
        getHerramientasPanel().setLayout(new FlowLayout(FlowLayout.LEADING));

        // Botón atrás.
        atrasButton = new JButton();

        atrasButton.setPreferredSize(new Dimension(80,80));
        atrasButton.setActionCommand(BTN_ATRAS);
        atrasButton.setIcon(antesIcon);
        atrasButton.setBorderPainted(false);     // Transparenia ON.
        atrasButton.setContentAreaFilled(false);
        getHerramientasPanel().add(atrasButton);
    }

    /**
     * Método encargado de cargar las imágenes necesarias para ejecutar la aplicación.
     */
    private void cargarImagenesRegister() {

        registerImg = new ImageIcon(new ImageIcon("src/buttons/register.png").
                getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT));

        antesIcon = new ImageIcon(new ImageIcon("src/buttons/flecha_Button.png").getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));
    }

    /**
     * Método encargado de añadir aquellos paneles correspondientes el centro del BorderLayout principal.
     */
    private void configurarCentro() {

        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro,BoxLayout.Y_AXIS));
        panelCentro.setOpaque(false);

        configurarPanelLogin();
        configurarPanelRegister();

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
        username = new LabelConfigurado(usernameString);
        username.setHorizontalAlignment(JLabel.RIGHT);
        
        email = new LabelConfigurado(emailString);
        email.setHorizontalAlignment(JLabel.RIGHT);

        password = new LabelConfigurado(passwordString);
        password.setHorizontalAlignment(JLabel.RIGHT);

        repeat_password = new LabelConfigurado(passwordrepeatString);
        repeat_password.setHorizontalAlignment(JLabel.RIGHT);

        usernameTxtF = new TextFieldConfigurado();
        emailTxtF = new TextFieldConfigurado();
        passwordTxtF = new PasswordFieldConfigurado();
        repeatpasswordTxtF = new PasswordFieldConfigurado();

        // Panel de login.
        panelLogin = new JPanel();
        panelLogin.setOpaque(false);
        panelLogin.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panelLogin.setAlignmentX(JPanel.CENTER_ALIGNMENT);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0,0,20,50);    // El padding externo del componente.

        // Primera fila - username/e-mail + TextField.
        gbc.gridwidth = 1;                                          // EL número de celdas que utilizará el componente.
        panelLogin.add(username, gbc);                              // Añadimos label.
        gbc.gridwidth = GridBagConstraints.REMAINDER;               // Es el último componente de las columnas.
        panelLogin.add(usernameTxtF, gbc);                             // Añadimos textfield.

        gbc.gridwidth = 1;                                          // EL número de celdas que utilizará el componente.
        panelLogin.add(email, gbc);                                 // Añadimos label.
        gbc.gridwidth = GridBagConstraints.REMAINDER;               // Es el último componente de las columnas.
        panelLogin.add(emailTxtF, gbc);                             // Añadimos textfield.

        // Segunda fila - password + TextField.
        gbc.gridwidth = 1;                                          // EL número de celdas que utilizará el componente.
        panelLogin.add(password, gbc);                              // Añadimos label.
        gbc.gridwidth = GridBagConstraints.REMAINDER;               // Es el último componente de las columnas.
        panelLogin.add(passwordTxtF, gbc);                          // Añadimos textfield.

        // Segunda fila - password + TextField.
        gbc.gridwidth = 1;                                          // EL número de celdas que utilizará el componente.
        panelLogin.add(repeat_password, gbc);                       // Añadimos label.
        gbc.gridwidth = GridBagConstraints.REMAINDER;               // Es el último componente de las columnas.
        panelLogin.add(repeatpasswordTxtF, gbc);                    // Añadimos textfield.

        panelLogin.setMaximumSize(panelLogin.getPreferredSize());
    }

    /**
     * Método encargado de configurar el panel que contiene el botón de login y register.
     */
    private void configurarPanelRegister() {

        // Componentes.
        registerButton = new JButton();
        registerButton.setActionCommand(BTN_REGISTER);
        registerButton.setHorizontalAlignment(JButton.CENTER);             // Set button on center.

        registerButton.setIcon(registerImg);        // Setemoas icono.
        registerButton.setBorderPainted(false);     // Transparencia ON.
        registerButton.setContentAreaFilled(false);

        // Creamos panel y añadimos sus componentes.
        loginRegister = new JPanel();
        loginRegister.setLayout(new FlowLayout());
        loginRegister.setOpaque(false);

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
     * Método encargado de registrar el controlador de la vista.
     * @param controller Controlador que gestoina el ranking.
     */
    public void registerController(ActionListener controller) {
        registerButton.addActionListener(controller);
        atrasButton.addActionListener(controller);
    }

    /**
     * Método encargado de resetear los TextFields de la vista.
     */
    public void resetTextFields() {
        usernameTxtF.setText("");
        emailTxtF.setText("");
        passwordTxtF.setText("");
        repeatpasswordTxtF.setText("");
    }

    /**
     * Getter del JTextField del email.
     * @return JTextField del email.
     */
    public JTextField getEmailTxtF() {
        return emailTxtF;
    }

    /**
     * Getter del JTextField del password.
     * @return JTextField del password.
     */
    public JTextField getPasswordTxtF() {
        return passwordTxtF;
    }

    /**
     * Getter del JTextField del username.
     * @return JTextField del username.
     */
    public JTextField getUsernameTxtFTxtF() {
        return usernameTxtF;
    }

    /**
     * Getter del JTextField del password repeat.
     * @return JTextField del password repeat.
     */
    public JTextField getRepeatPasswordTxtF() {
        return repeatpasswordTxtF;
    }

}
