package PresentationLayoud.Views;

import BusinessLayer.Entities.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Clase encargada de generar un ranking de usuarios.
 */
public class RankingView extends PanelBaseView {

    private JPanel panelCentro;
    private JButton atrasButton;
    private JButton configButton;

    private ImageIcon settingsIcon;
    private ImageIcon antesIcon;
    private JTable table;
    private JScrollPane sp = new JScrollPane();
    private Font fuenteTable;


    public static final String BTN_CONFIG = "BTN_RANKING";
    public static final String BTN_ATRAS = "BTN_ATRAS";
    private static final String CABECERA_PLAYERS = "PLAYERS";
    private static final String CABECERA_WINS = "WINS";
    private static final String CABECERA_RATIO = "WIN_RATIO";


    /**
     * Constructor pode defecto.
     */
    public RankingView() {

        panelCentro = new JPanel();
        fuenteTable = new Font("Supercell-Magic",Font.PLAIN, 20);       // Declaramos fuente de la tabla a mostrar.
        setTitleImage(PanelBaseView.CROWN_IMAGE);
        cargarImagenes();
        configurarNorte();
    }

    /**
     * Método encargado de cargar las imágenes necesarias para ejecutar en la vista.
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
     * Método encargado de configurar el centro de la vista y de actualizar la tabla cuando pertoque según una lista de usuarios.
     */
    public void configurarTabla(ListSelectionListener controller, ArrayList<User> usuarios) {

        panelCentro.removeAll();
        getPanelFondo().remove(sp);
        panelCentro = new JPanel();                                                     // Declaramos panel central.

        String[] column ={CABECERA_PLAYERS,CABECERA_WINS,CABECERA_RATIO};               // Declaramos cabecera de la tabla a mostrar.
        String[][] data = new String[usuarios.size()][3];                               // Declaramos array de datos a situar en la tabla.

        // Llenamos información de la tabla.
        for (int i = 0; i < usuarios.size(); i++) {
            data[i][0] = usuarios.get(i).getNom();                                  // Nombre del usuario.
            data[i][1] =  Integer.toString(usuarios.get(i).getpGuanyades());        // Num de partidas ganadas.
            DecimalFormat df = new DecimalFormat("#.##");
            data[i][2] = df.format((float) usuarios.get(i).getpGuanyades() / usuarios.get(i).getNumPartides()); // WinRatio.
        }

        configurarScrollPaneTable(data,column);
        getPanelFondo().add(sp,BorderLayout.CENTER);

        table.getSelectionModel().addListSelectionListener(controller);

        panelCentro.revalidate();
        panelCentro.repaint();
        table.revalidate();
        table.repaint();
        getPanelFondo().revalidate();
        getPanelFondo().repaint();

    }

    /**
     * Método encargado de configurar el JScrollPane que contiene la tabla con sus datos pertenecientes.
     * @param data      Datos a llenar en la tabla.
     * @param column    Columnas de la tabla.
     */
    private void configurarScrollPaneTable(String data[][],String column[]) {

        // Declaramos tabla con la nueva información y deshabilitamos funcionalidad de editar valores de las celdas.
        table = new JTable(data,column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);     // Deshabilitamos reordenación del tablero.
        table.setBounds(100,100,200,300);     // Añadimos bounds.
        table.setGridColor(Color.gray);
        sp = new JScrollPane(table);                // Declaramos ScrollPane que contendrá el JTable.

        // Ponemos fuente al Header.
        table.getTableHeader().setFont(fuenteTable);
        table.setFont(fuenteTable);

        // Cambiamos el color de background del heather.
        table.getTableHeader().setBackground(Color.lightGray);

        // Editamos el tamaño de las cells.
        table.setRowHeight(60);
        table.getTableHeader().setPreferredSize(new Dimension(400, 60));

        // Centramos el contenido.
        DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
        centro.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < 3;i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centro);
        }
        sp.setOpaque(false);
        sp.setBorder(new EmptyBorder(20,100,100,100));

        DefaultTableCellRenderer heatherCentro;
        heatherCentro = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        heatherCentro.setHorizontalAlignment(JLabel.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(heatherCentro);

    }

    /**
     * Método encargado de registrar el controlador de la vista.
     * @param controller Controlador que gestoina el ranking.
     */
    public void registerController(ActionListener controller) {
        configButton.addActionListener(controller);
        atrasButton.addActionListener(controller);
    }

    /**
     * Getter de la tabla.
     * @return tabla
     */
    public JTable getTable() {
        return table;
    }
}
