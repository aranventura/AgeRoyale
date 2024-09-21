package PresentationLayoud.Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Vista de guardar partida permite no guardarla y volver al menú principal o guardarla y volver al menú principal.
 */
public class GuardarPartidaView extends PanelBaseView {

    private JPanel panelCentro;
    private ImageIcon guardarPartidaIcon;
    private ImageIcon continuarIcon;
    private ImageIcon settingsIcon;

    private JButton guardarPartidaButton;
    private JButton continuarButton;
    private JButton configButton;
    private JLabel wonLabel;

    private JTextField nombrePartida;

    private static int BUTTON_HEIGHT = 100;
    private static int BUTTON_WIDTH = 350;
    public static final String BTN_GUARDAR_PARTIDA = "BTN_GUARDAR_PARTIDA";
    public static final String BTN_VOLVER_MENU_PRINCIPAL = "BTN_CONTINUAR";
    public static final String BTN_CONFIG = "BTN_CONFIG";
    public static final String GUARDA_PARTIDA = "BTN_GUARDA_PARTIDA";
    private static final String NOMBRE_PARTIDA = "TÍTULO DE LA PARTIDA";
    private static final String WON = "HAS GANADO!";
    private static final String LOST = "HAS PERDIDO!";



    /**
     * Constructor por defecto.
     */
    public GuardarPartidaView() {

        guardarPartidaButton = new JButton();
        continuarButton = new JButton();
        wonLabel = new JLabel();

        setTitleImage(PanelBaseView.CROWN_IMAGE);
        cargarImagenes();
        configurarNorte();
        configurarCentro();
    }

    /**
     * Método encargado de cargar las imágenes necesarias para ejecutar la vista.
     */
    public void cargarImagenes() {

        guardarPartidaIcon = new ImageIcon(new ImageIcon("src/buttons/guardarPartida_Button.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT));
        continuarIcon = new ImageIcon(new ImageIcon("src/buttons/continuar_Button.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_DEFAULT));
        settingsIcon = new ImageIcon(new ImageIcon("src/buttons/settings_Button.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    }

    /**
     * Método encargado de configurar la vista norte de la pantalla con sus correspondientes botones.
     */
    private void configurarNorte() {

        getHerramientasPanel().setLayout(new FlowLayout(FlowLayout.TRAILING));
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
     * Método encargado de añadir aquellos paneles correspondientes el centro del BorderLayout principal.
     * Botón de guardar partida y de volver al menú principal sin guardarla.
     */
    private void configurarCentro() {



        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro,BoxLayout.Y_AXIS));
        panelCentro.setOpaque(false);

        guardarPartidaButton.setIcon(guardarPartidaIcon);                   // Seteamos icono.
        guardarPartidaButton.setActionCommand(BTN_GUARDAR_PARTIDA);
        guardarPartidaButton.setBorderPainted(false);                       // Transparenia ON.
        guardarPartidaButton.setContentAreaFilled(false);

        continuarButton.setIcon(continuarIcon);                             // Seteamos icono.
        continuarButton.setActionCommand(BTN_VOLVER_MENU_PRINCIPAL);
        continuarButton.setBorderPainted(false);                            // Transparenia ON.
        continuarButton.setContentAreaFilled(false);

        guardarPartidaButton.setHorizontalAlignment(JButton.CENTER);        // Set button on center.
        continuarButton.setHorizontalAlignment(JButton.CENTER);             // Set button on center.
        guardarPartidaButton.setAlignmentX(CENTER_ALIGNMENT);
        continuarButton.setAlignmentX(CENTER_ALIGNMENT);
        wonLabel.setAlignmentX(CENTER_ALIGNMENT);


        panelCentro.add(wonLabel);
        panelCentro.add(Box.createVerticalStrut(35));                   // Añadimos espacio vertical.
        panelCentro.add(guardarPartidaButton);
        panelCentro.add(Box.createVerticalStrut(35));                   // Añadimos espacio vertical.
        panelCentro.add(continuarButton);
        panelCentro.add(Box.createVerticalStrut(35));                   // Añadimos espacio vertical.
        getPanelFondo().add(panelCentro,BorderLayout.CENTER);

    }

    /**
     * Método encargado de registrar el controlador a los componentes.
     * @param controller Controlador a registrar.
     */
    public void registerController(ActionListener controller) {
        guardarPartidaButton.addActionListener(controller);
        continuarButton.addActionListener(controller);
        configButton.addActionListener(controller);

    }

    /**
     * Método encargado de cargar el panel que pide el usuario el nombre de la partida.
     */
    public void pedirNombrePartida() {

        // Paneles.
        getPanelFondo().remove(panelCentro);                                    // Eliminamos panel de botones anterior.
        panelCentro = new JPanel();                                             // Declaramos nuevo panel central.
        guardarPartidaButton.setActionCommand(GUARDA_PARTIDA);                  // Cambiamos action command del botón y lo reaprovechamos.

        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setOpaque(false);
        JPanel panelTitulo = new JPanel(new GridBagLayout());
        panelTitulo.setOpaque(false);

        // Componentes.
        JLabel tituloPartida = new LabelConfigurado(NOMBRE_PARTIDA);
        tituloPartida.setVerticalAlignment(JLabel.CENTER);
        nombrePartida = new TextFieldConfigurado();

        // Añadir Componentes.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(50,0,80,50);    // El padding externo del componente.

        // Primera fila - Titulo de la partida + JTextField
        gbc.gridwidth = 1;                                          // EL número de celdas que utilizará el componente.
        panelTitulo.add(tituloPartida, gbc);                        // Añadimos label.
        gbc.gridwidth = GridBagConstraints.REMAINDER;               // Es el último componente de las columnas.
        panelTitulo.add(nombrePartida, gbc);                        // Añadimos textfield.

        panelTitulo.setMaximumSize(panelTitulo.getPreferredSize());
        panelCentro.add(panelTitulo);
        panelCentro.add(guardarPartidaButton);

        getPanelFondo().add(panelCentro,BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    /**
     * Método encargado de pintar de nuevo el panel de GuardarPartida desde su inicio.
     */
    public void repaintGuardarPartida(boolean heGanado) {
        if(getPanelFondo() != null) {
            getPanelFondo().remove(panelCentro);                        // Eliminamos panel de botones anterior.
        }

        if(heGanado) {
            wonLabel = new LabelConfigurado(WON);
        } else {
            new LabelConfigurado(LOST);
        }
        repaint();
        revalidate();
        configurarCentro();
    }

    public JTextField getNombrePartida() {
        return nombrePartida;
    }
}
