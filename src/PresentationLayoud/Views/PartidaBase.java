package PresentationLayoud.Views;

import PresentationLayoud.Controllers.CtrlPartida;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Clase encargada de generar un panel que contiene la vista de la partida.
 */
public class PartidaBase extends JPanel {

    private JPanel panelFondo;
    private ImageIcon imgFondo;
    private ImageIcon arqueraImg;
    private ImageIcon esqueletoImg;
    private ImageIcon giganteImg;
    private ImageIcon magoImg;
    private ImageIcon cañonImg;
    private ImageIcon ballestaImg;
    private ImageIcon morteroImg;
    private ArrayList<ImageIcon> elixirImg;
    private ArrayList<ImageIcon> tableroIcons;

    private JPanel panelCentro;
    private JPanel panelSur;
    private JPanel panelJuego;
    private JPanel chessBoard;
    private JPanel gridPanelCartas;
    private JPanel panelOfensivas;
    private JPanel panelDefensivas;
    private JPanel panelElixirOfensivas;
    private JPanel panelElixirDefensivas;
    private JPanel textoDefOf;

    ArrayList<JButton> cardButtons;

    private StatusBar userTroopsSB;
    private StatusBar userLifeSB;
    private JLabel userLife;
    private JLabel userTroops;
    private JLabel userMoney;
    private StatusBar enemyTroopsSB;
    private StatusBar enemyLifeSB;
    private JLabel enemyLife;
    private JLabel enemyTroops;

    private JButton selectedCard;

    private Color lightGreen = new Color(201,226,131);
    private Color darkGreen = new Color(137,156,82);
    private Color lightRed = new Color(238,96,96);
    private Color darkRed = new Color(238,75,75);
    private Color lightBlue = new Color(95,233,238);
    private Color darkBlue = new Color(95,190,200);
    private Color pink = new Color(247,148,242);



    private static final String COLS     = "ABCDEFGH";
    public static final String BTN_PRESS = "BTN_ARQUERA";

    public static final int ARQUERA = 0;
    public static final int ESQUELETO = 1;
    public static final int MAGO = 2;
    public static final int CAÑON = 3;
    private static final int MAX_OF = 2;
    private static final int MAX_DEF = 2;
    private static final int MAX_CELDAS = 70;
    private static final int MAX_COLUMNAS = 7;
    private static final int BOARD_WIDTH = 420;
    private static final int BOARD_HEIGHT = 600;


    /**
     * Constructor por defecto.
     */
    public PartidaBase()
    {

        elixirImg = new ArrayList<>();
        tableroIcons = new ArrayList<>();

        userLifeSB = new StatusBar(lightGreen);
        userTroopsSB = new StatusBar(lightBlue);
        userTroopsSB.setValor(0);
        enemyLifeSB = new StatusBar(lightGreen);
        enemyTroopsSB = new StatusBar(lightBlue);
        enemyTroopsSB.setValor(0);
        userLife = labelConfigurado("100",30,lightGreen);
        userTroops = labelConfigurado("0",30,Color.ORANGE);
        enemyLife = labelConfigurado("100",30,lightGreen);
        enemyTroops = labelConfigurado("0",30,Color.ORANGE);
        userMoney = labelConfigurado("0", 30,pink);

        cargarImagenes();
        configurarFondo();
        configurarBoard();
        configureEast();
        configureWest();
        configurarSur();
        panelFondo.add(panelCentro,BorderLayout.CENTER);
        add(panelFondo);
        this.setLayout(new BorderLayout());
    }

    /**
     * Método encargado de cargar las imágenes necesarias para ejecutar la aplicación.
     */
    private void cargarImagenes() {
        imgFondo = new ImageIcon(new ImageIcon("src/Icons/Clash_Royale_Background.png").getImage());
        arqueraImg = new ImageIcon(new ImageIcon("src/Icons/arquera_90x90.png").getImage());
        esqueletoImg = new ImageIcon(new ImageIcon("src/Icons/esqueleto_90x90.png").getImage());
        magoImg = new ImageIcon(new ImageIcon("src/Icons/mago_90x90.png").getImage());
        cañonImg = new ImageIcon(new ImageIcon("src/Icons/cañon_90x90.png").getImage());

        // Carga imágenes elixir.
        for (int i = 0; i < 9; i++) {
            String pathElixir = "src/Icons/elixir_" + (i+1) + ".png";
            System.out.println(pathElixir);
            elixirImg.add(new ImageIcon(new ImageIcon(pathElixir).getImage()));
        }

        elixirImg.add(new ImageIcon(new ImageIcon("src/Icons/elixir.png").getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT)));

        // Carga imágenes de las fichas sobre el tablero.
        for (int i = 0; i < MAX_DEF+MAX_OF+2; i++) {
            String pathTablero = "src/Icons/tableroFicha_" + (i) + ".png";
            System.out.println(pathTablero);
            tableroIcons.add(new ImageIcon(new ImageIcon(pathTablero).getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT)));
        }
    }

    /**
     * Método encargado de configurar el panel de fondo con su correspondiente Layout e imagen.
     */
    private void configurarFondo() {
        panelFondo = new ImagePanel(imgFondo);
        panelFondo.setLayout(new BorderLayout());
    }

    /**
     * Método encargado de cargar el tablero del juego con sus celdas de diferentes colores y las torres inicializadas.
     */
    private void configurarBoard() {

        Dimension boardSize = new Dimension(BOARD_WIDTH, BOARD_HEIGHT); // Dimensión del tablero.

        // Panel central que contendrá el tablero.
        panelCentro = new JPanel(new BorderLayout());
        panelCentro.setOpaque(false);

        // Panel que contendrá el panel de Board.
        panelJuego = new JPanel();
        panelJuego.setPreferredSize(panelJuego.getPreferredSize());
        panelJuego.setOpaque(false);
        panelJuego.setBorder(new EmptyBorder(10, 0, 0, 0));

        // Panel que contendrá las celdas.
        chessBoard = new JPanel();
        chessBoard.setOpaque(false);
        chessBoard.setLayout( new GridLayout(10, 7) );
        chessBoard.setPreferredSize( boardSize );

        // Cargar celdas en el tablero.
        for (int i = 0; i < MAX_CELDAS; i++)
        {
            CeldaView square = new CeldaView(i/MAX_COLUMNAS,i%MAX_COLUMNAS);    // Declaramos celda junto a su coordenada i,j.
            chessBoard.add(square);

            square.setBackground( i % 2 == 0 ? lightGreen : darkGreen );             // Seleccionamos el color de la celda en función de su posición.
            if(i < 7) {
                square.setBackground( i % 2 == 0 ? lightRed : darkRed );             // Color rojo base.
            }
            if(i > 62) {
                square.setBackground( i % 2 == 0 ? lightBlue : darkBlue );          // Color azul base.
            }

            // Situamos castillos en sus celdas correspondientes.
            if(i == 3) {
                square.setIconSquare(tableroIcons.get(5));                          // Castillo rojo.
                square.setOcupada(true);
            }
            if(i == 66) {
                square.setIconSquare(tableroIcons.get(4));                          // Castillo azul.
                square.setOcupada(true);
            }
        }

        // Añadimos Paneles.
        panelJuego.add(chessBoard);
        panelCentro.add(panelJuego,BorderLayout.CENTER);
        panelCentro.setPreferredSize(panelCentro.getPreferredSize());
    }

    /**
     * Método encargado de configurar la parte Este del panel.
     */
    public void configureEast() {

        // Declaramos paneles y Labels a utilizar.
        JPanel eastPanel = new JPanel(new BorderLayout());  // Contiene el panel Este en su totalidad.
        JPanel derechaNorthP = new JPanel();                // Contiene la parte norte del panel este.
        JPanel derechaCenterP = new JPanel();               // Conteien la parte central del panel este.
        JPanel statusPanel = statusPanelConfigurado(userLifeSB,userTroopsSB,userLife,userTroops);   // Panel de estadísticas.
        JLabel imagenElixir = new JLabel();                 // Imágen del elixir.
        imagenElixir.setIcon(elixirImg.get(9));

        // Alineamientos.
        derechaNorthP.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        userMoney.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        imagenElixir.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Layouts.
        derechaNorthP.setLayout(new FlowLayout(FlowLayout.TRAILING));
        derechaCenterP.setLayout(new BoxLayout(derechaCenterP,BoxLayout.Y_AXIS));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        // Opacidades.
        eastPanel.setOpaque(false);
        derechaNorthP.setOpaque(false);
        derechaCenterP.setOpaque(false);
        statusPanel.setOpaque(false);

        // Bordes.
        userMoney.setBorder(new EmptyBorder(0,0,0,15));
        derechaNorthP.setBorder(new EmptyBorder(25,0,25,150));

        // Dimensiones.
        eastPanel.setPreferredSize(new Dimension(400,0));
        derechaNorthP.setPreferredSize(new Dimension(300,130));

        // Añadimos paneles.
        derechaCenterP.add(statusPanel);
        derechaNorthP.add(userMoney);
        derechaNorthP.add(imagenElixir);
        eastPanel.add(derechaCenterP,BorderLayout.CENTER);
        eastPanel.add(derechaNorthP,BorderLayout.NORTH);
        panelCentro.add(eastPanel, BorderLayout.EAST);
    }

    /**
     * Método encargado de configurar la parte West del panel.
     */
    private void configureWest() {

        // Declaramos paneles a y Labels a utilizar.
        JPanel westPanel = new JPanel(new BorderLayout());                          // Contiene el panel West en su totalidad.
        JPanel izquierdaNorthP = new JPanel();                                      // Contiene el panel norte del panel West.
        JPanel izquierdaCenterP = new JPanel();                                     // Contiene el panel centro del panel West.
        JPanel statusPanel = statusPanelConfigurado(enemyLifeSB,enemyTroopsSB,enemyLife,enemyTroops);   // Panel de estadísticas.
        JLabel rivalLabel = labelConfigurado("¡Tu rival!",35,Color.WHITE);      // Label rival.

        // Alineamientos.
        izquierdaNorthP.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        // Layouts.
        izquierdaCenterP.setLayout(new BoxLayout(izquierdaCenterP,BoxLayout.X_AXIS));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));

        // Opacidades.
        westPanel.setOpaque(false);
        izquierdaNorthP.setOpaque(false);
        izquierdaCenterP.setOpaque(false);
        statusPanel.setOpaque(false);

        // Bordes.
        izquierdaNorthP.setBorder(new EmptyBorder(60,0,0,0));

        // Dimensiones.
        westPanel.setPreferredSize(new Dimension(400,0));
        izquierdaNorthP.setPreferredSize(new Dimension(300,130));

        // Añadimos paneles.
        izquierdaCenterP.add(statusPanel);
        izquierdaNorthP.add(rivalLabel);
        westPanel.add(izquierdaCenterP,BorderLayout.CENTER);
        westPanel.add(izquierdaNorthP,BorderLayout.NORTH);
        panelCentro.add(westPanel, BorderLayout.WEST);

    }

    /**
     * Método encargado de generar un panel de estadísticas para añadir en la partida.
     * @param life          Statusbar de vida.
     * @param troops        Statusbar de tropas.
     * @param numLife       Label de cantidad de vida.
     * @param numTroops     Label de cantidad de tropas.
     * @return              JPanel con las estadísticas configuradas.
     */
    private JPanel statusPanelConfigurado(StatusBar life, StatusBar troops, JLabel numLife, JLabel numTroops) {

        // Paneles a utilizar.
        JPanel troopsPanel = new JPanel();
        JPanel lifePanel = new JPanel();
        JPanel statusPanel = new JPanel();

        // Labels a utilizar.
        JLabel lifeLabel = labelConfigurado("Vida",20,Color.WHITE);
        JLabel tropasLabel = labelConfigurado("Tropas",20,Color.WHITE);

        // Layouts.
        lifePanel.setLayout(new BoxLayout(lifePanel,BoxLayout.Y_AXIS));
        troopsPanel.setLayout(new BoxLayout(troopsPanel,BoxLayout.Y_AXIS));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));


        // Opacidades.
        lifePanel.setOpaque(false);
        troopsPanel.setOpaque(false);
        statusPanel.setOpaque(false);

        // Alineamientos.
        lifeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        tropasLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        numLife.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        numTroops.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        // Añadimos a panel de tropas.
        troopsPanel.add(troops);
        troopsPanel.add(tropasLabel);
        troopsPanel.add(numTroops);

        // Añadimos a panel de vida.
        lifePanel.add(life);
        lifePanel.add(lifeLabel);
        lifePanel.add(numLife);

        // Añadimos a panel de status.
        statusPanel.add(lifePanel);
        statusPanel.add(troopsPanel);

        return statusPanel;
    }

    /**
     * Método encargado de configurar el panel Sur.
     */
    public void configurarSur() {

        // Declaramos el panel del sur.
        panelSur = new JPanel();
        panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.Y_AXIS));
        panelSur.setPreferredSize(new Dimension(0,280));    // Dimensión panel del sur.
        panelSur.setOpaque(false);

        // Configuramos sus componentes.
        configurarDefOf();
        configurarGridCartas();

        // Añadimos los componentes.
        panelSur.add(textoDefOf);
        panelSur.add(gridPanelCartas);
        panelCentro.add(panelSur, BorderLayout.SOUTH);

    }

    /**
     * Método encargado de configurar el panel que contiene el texto de Ofensivas y Defensivas.
     */
    private void configurarDefOf() {

        JLabel defensivas;
        JLabel ofensivas;

        textoDefOf = new JPanel(new FlowLayout(FlowLayout.CENTER,375,0));
        textoDefOf.setOpaque(false);
        textoDefOf.setBorder(new EmptyBorder(40,0,0,0));

        // Mensajes panel.
        defensivas = labelConfigurado("Defensivas", 20,Color.WHITE);
        ofensivas = labelConfigurado("Ofensivas",20,Color.WHITE);
        defensivas.setHorizontalAlignment(JLabel.CENTER);
        ofensivas.setHorizontalAlignment(JLabel.CENTER);
        textoDefOf.add(ofensivas);
        textoDefOf.add(defensivas);
        panelSur.add(textoDefOf);
    }

    /**
     * Método encargado de configurar el panel con GridLayout que contiene los diferentes tipos de paneles.
     */
    private void configurarGridCartas() {

        JLabel arqueraE;
        JLabel esqueletoE;
        JLabel magoE;
        JLabel cañonE;


        // Inicializamos panel que contiene las cartas a seleccionar.
        gridPanelCartas = new JPanel(new GridLayout(2,2));
        gridPanelCartas.setOpaque(false);
        gridPanelCartas.setBorder(new EmptyBorder(0, 200, 0, 200));

        // Paneles a utilizar del tipo FlowLayout.
        panelOfensivas = new JPanel();              // Panel ofensivas.
        panelOfensivas.setOpaque(false);
        panelDefensivas = new JPanel();             // Panel defensivas.
        panelDefensivas.setOpaque(false);
        panelElixirOfensivas = new JPanel();        // Panel elixir defensivas.
        panelElixirOfensivas.setOpaque(false);
        panelElixirDefensivas = new JPanel();       // Panel elixir ofensivas.
        panelElixirDefensivas.setOpaque(false);

        // Declaramos botones con icono y los añadimos a lista de botones.
        cardButtons = new ArrayList<JButton>();

        JButton card = getBotonFicha(arqueraImg, BTN_PRESS);
        cardButtons.add(card);
        card = getBotonFicha(esqueletoImg, BTN_PRESS);
        cardButtons.add(card);
        card = getBotonFicha(magoImg, BTN_PRESS);
        cardButtons.add(card);
        card = getBotonFicha(cañonImg, BTN_PRESS);
        cardButtons.add(card);

        arqueraE = getElixir(elixirImg.get(3));
        esqueletoE = getElixir(elixirImg.get(4));
        magoE = getElixir(elixirImg.get(6));
        cañonE = getElixir(elixirImg.get(2));


        // Añadimos cartas en el panel de las ofensivas.
        for (int i = 0; i < 2; i++) {
            panelOfensivas.add(cardButtons.get(i));
        }
        // Añadimos cartas en el panel de las defensivas.
        for (int i = 2; i < 4; i++) {
            panelDefensivas.add(cardButtons.get(i));
        }


        // Añadimos valor de los elixires.
        panelElixirOfensivas.add(arqueraE);
        panelElixirOfensivas.add(esqueletoE);


        // Añadimos valor de los elixires.
        panelElixirDefensivas.add(magoE);
        panelElixirDefensivas.add(cañonE);

        gridPanelCartas.add(panelOfensivas);
        gridPanelCartas.add(panelDefensivas);
        gridPanelCartas.add(panelElixirOfensivas);
        gridPanelCartas.add(panelElixirDefensivas);

    }

    /**
     * Método encargado de generar un botón de ficha configurado con su icono y el string correspondiente a su acción.
     * @param icono             Icono a situar en el botón.
     * @param actionString      String correspondiente al ActionListener del botón.
     * @return  Devuelve botón de ficha configurado.
     */
    private JButton getBotonFicha(ImageIcon icono, String actionString) {
        JButton boton = new JButton();
        boton.setIcon(icono);
        boton.setPreferredSize(new Dimension(90,90));
        boton.setBackground(Color.WHITE);
        boton.setBorder(BorderFactory.createLineBorder(Color.CYAN,3,true));
        boton.setOpaque(false);
        boton.setBorderPainted(false);
        boton.addMouseListener(new MouseAdapter() {
        });
        boton.setActionCommand(actionString);
        return boton;
    }

    /**
     * Método encargado de retornar un JLabel de dimensión 90x90 con una imágen de elixir.
     * @param icono Icono de elixir a situar en el JLabel.
     * @return JLabel configurado con una imágen de elixir.
     */
    private JLabel getElixir(ImageIcon icono) {
        JLabel elixir = new JLabel();
        elixir.setIcon(icono);
        elixir.setPreferredSize(new Dimension(90,90));
        elixir.setBackground(Color.WHITE);
        elixir.setOpaque(false);
        elixir.setHorizontalAlignment(JLabel.CENTER);
        return elixir;
    }

    /**
     * Método encargado de retornar un JLabel configurado con texto, size del mismo y el color de este.
     * @param texto     Texto a poner en el JLabel.
     * @param size      Size del texto.
     * @param color     Color del texto.
     * @return  Label configurado con texto, size y color del mismo.
     */
    private JLabel labelConfigurado(String texto, int size, Color color) {
        Font font = new Font("Supercell-Magic",Font.PLAIN, size);
        JLabel label = new JLabel();
        label.setFont(font);
        label.setForeground(color);
        label.setText(texto);
        return label;
    }

    /**
     * Método encargado de registrar el controllador a los Componentes.
     * @param controller    Controlador a registrar.
     */
    public void registerController(CtrlPartida controller) {

        for (int i = 0; i < MAX_OF+MAX_DEF; i++) {
            cardButtons.get(i).addActionListener(controller);
        }

        for (int i = 0; i < 70; i++) {
            chessBoard.getComponent(i).addMouseListener(controller);
        }
    }

    /**
     * Método encargado de desselcionar todas las cartas.
     */
    public void desSeleccionaCartas() {

        for (JButton cardButton : cardButtons) {
            cardButton.setBorderPainted(false);
        }
    }

    /**
     * Método encargado de actualizar el panel Status del usuario.
     * @param life              Vida del usuario.
     * @param num_troops        Nr de tropas del usuario.
     * @param money             Dinero del usuario.
     */
    public void actualizaStatusUser(int life, int num_troops, int money) {

        int MAX_LIFE = 100;
        int MAX_TROOPS = 7;

        userLifeSB.setValor((life*100) / MAX_LIFE);
        userTroopsSB.setValor((num_troops*100)/MAX_TROOPS);
        userLife.setText(String.valueOf(life));
        userTroops.setText(String.valueOf(num_troops));
        userMoney.setText(String.valueOf(money));

        repaint();
        revalidate();
    }

    /**
     * Método encargado de actualizar el panel Status del enemigo.
     * @param life              Vida del usuario.
     * @param num_troops        Nr de tropas del usuario.
     */
    public void actualizaStatusEnemy(int life, int num_troops) {

        int MAX_LIFE = 100;
        int MAX_TROOPS = 7;

        enemyLifeSB.setValor((life*100) / MAX_LIFE);
        enemyTroopsSB.setValor((num_troops*100)/MAX_TROOPS);
        enemyLife.setText(String.valueOf(life));
        enemyTroops.setText(String.valueOf(num_troops));

        repaint();
        revalidate();
    }

    /**
     * Método encargado de añadir una ficha en la celda.
     * @param celda Celda donde añadir la ficha.
     * @param ficha Código de ficha a añadir.
     */
    public void addFicha(CeldaView celda, int ficha) {

        celda.setIconSquare(getTableroIcons().get(ficha));
        celda.setBackground(Color.CYAN);
        celda.setOcupada(true);

    }

    /**
     * Añadir ficha del enemigo.
     * @param iOrigen   i de la Celda donde añadir.
     * @param jOrigen   j de la Celda donde añadir.
     * @param ficha     Código de ficha a añadir.
     */
    public void addFichaEnemy(int iOrigen, int jOrigen, int ficha) {

        CeldaView celda = (CeldaView) chessBoard.getComponent(iOrigen*MAX_COLUMNAS + jOrigen);
        celda.setIconSquare(getTableroIcons().get(ficha));
        celda.setBackground(Color.RED);
        celda.setOcupada(true);
    }

    /**
     * Mover ficha de una celda origen a una celda destino-
     * @param iOrigen   i de la ficha a mover.
     * @param jOrigen   j de la ficha a mover.
     * @param iDestino  iDestino de la ficha.
     * @param jDestino  jDestino de la ficha.
     */
    public void moveFicha(int iOrigen, int jOrigen, int iDestino, int jDestino) {

        // Conseguir objeto en iOrigen,jDestino es get de (iOrigen*MAX_COLUMNAS + jOrigen)

        // Obtenemos celdas origen y celdas destino
        CeldaView origen = (CeldaView) chessBoard.getComponent(iOrigen*MAX_COLUMNAS + jOrigen);
        CeldaView destino = (CeldaView) chessBoard.getComponent(iDestino*MAX_COLUMNAS+jDestino);

        // Situamos la imagen de la celda origen en la celda destino y actualizamos imágenes.
        destino.setIconSquare((ImageIcon) origen.getIconSquare());
        origen.setIconSquare(null);

        // Actualizamos valor de ocupado en celda de origen.
        origen.setOcupada(false);
        destino.setOcupada(true);

        destino.setBackground(origen.getBackground());
        origen.setBackground( (iOrigen*MAX_COLUMNAS + jOrigen) % 2 == 0 ? lightGreen : darkGreen );    // Seleccionamos el color de la celda en función de su posición.

        // Repintamos panel.
        this.repaint();
        this.revalidate();

    }

    /**
     * Eliminar ficha de una celda.
     * @param iOrigen   i de la celda.
     * @param jOrigen   j de la celda.
     */
    public void eliminaFicha(int iOrigen, int jOrigen) {

        CeldaView celda = (CeldaView) chessBoard.getComponent(iOrigen*MAX_COLUMNAS + jOrigen);
        // Eliminamos icono.
        celda.setIconSquare(null);
        // Actualizamos valor de ocupado en celda de origen.
        celda.setOcupada(false);

        celda.setBackground((iOrigen*MAX_COLUMNAS + jOrigen) % 2 == 0 ? lightGreen : darkGreen );

        this.repaint();
        this.revalidate();
    }

    /**
     * Getter encargado de retornar la carta seleccionada.
     * @param selectedCard  Carta seleccionada.
     */
    public void setSelectedCard(JButton selectedCard) {
        this.selectedCard = selectedCard;
    }

    /**
     * Getter encargado de obtener la carta seleccionada.
     * @return Carta seleccionada.
     */
    public JButton getSelectedCard() {
        return selectedCard;
    }

    /**
     * Getter de la lista de botones de las cartas.
     * @return  ArrayList que contiene los botones de las cartas.
     */
    public ArrayList<JButton> getCardButtons() {
        return cardButtons;
    }

    /**
     * Setter de las cartas a mostrar en la parte inferior.
     * @param cardButtons   Cartas a setear.
     */
    public void setCardButtons(ArrayList<JButton> cardButtons) {
        this.cardButtons = cardButtons;
    }

    /**
     * Getter de los iconos disponibles a añadir en el tablero.
     * @return  ARrayList de iconos con las posibles fichas a añadir.
     */
    public ArrayList<ImageIcon> getTableroIcons() {
        return tableroIcons;
    }

}